package com.example.data.weather

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.example.data.AgronomyCatalog
import com.example.model.AgronomicWeatherAdvisory
import com.example.model.CurrentWeather
import com.example.model.DailyForecastItem
import com.example.model.KenyaAgriculturalCounty
import com.example.model.SprayStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class CompleteWeatherResult(
    val countyName: String,
    val latitude: Double,
    val longitude: Double,
    val currentWeather: CurrentWeather,
    val dailyForecast: List<DailyForecastItem>,
    val advisory: AgronomicWeatherAdvisory
)

class WeatherRepository(private val context: Context) {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    @SuppressLint("MissingPermission")
    fun detectDeviceLocation(): Pair<Double, Double>? {
        return try {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
                ?: return null

            val providers = listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER, LocationManager.PASSIVE_PROVIDER)
            var bestLocation: Location? = null

            for (provider in providers) {
                if (locationManager.isProviderEnabled(provider)) {
                    val loc = locationManager.getLastKnownLocation(provider)
                    if (loc != null) {
                        if (bestLocation == null || loc.accuracy < bestLocation.accuracy) {
                            bestLocation = loc
                        }
                    }
                }
            }

            bestLocation?.let { Pair(it.latitude, it.longitude) }
        } catch (e: Exception) {
            Log.w("WeatherRepository", "Could not get last known location: ${e.message}")
            null
        }
    }

    suspend fun fetchWeatherForecast(
        county: KenyaAgriculturalCounty? = null,
        customLat: Double? = null,
        customLng: Double? = null
    ): Result<CompleteWeatherResult> = withContext(Dispatchers.IO) {
        try {
            val selectedCounty = county ?: AgronomyCatalog.kenyanCounties.first()
            val lat = customLat ?: selectedCounty.latitude
            val lng = customLng ?: selectedCounty.longitude
            val locationLabel = if (customLat != null && county == null) "Detected Farm Location" else selectedCounty.name

            val url = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=$lat&longitude=$lng" +
                "&current=temperature_2m,relative_humidity_2m,precipitation,weather_code,wind_speed_10m,wind_direction_10m" +
                "&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_probability_max" +
                "&timezone=Africa%2FNairobi"

            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            val response = okHttpClient.newCall(request).execute()
            if (!response.isSuccessful) {
                return@withContext Result.failure(Exception("Weather server returned HTTP ${response.code}"))
            }

            val bodyString = response.body?.string() ?: return@withContext Result.failure(Exception("Empty weather response"))
            val json = JSONObject(bodyString)

            val currentObj = json.getJSONObject("current")
            val temp = currentObj.getDouble("temperature_2m")
            val humidity = currentObj.getInt("relative_humidity_2m")
            val windSpeed = currentObj.getDouble("wind_speed_10m")
            val windDir = currentObj.optDouble("wind_direction_10m", 0.0)
            val precip = currentObj.optDouble("precipitation", 0.0)
            val weatherCode = currentObj.getInt("weather_code")
            val condition = decodeWmoWeatherCode(weatherCode)

            val currentWeather = CurrentWeather(
                temperatureCelsius = temp,
                relativeHumidity = humidity,
                windSpeedKmh = windSpeed,
                windDirection = windDir,
                precipitationMm = precip,
                weatherCode = weatherCode,
                conditionText = condition
            )

            // Parse Daily Forecast
            val dailyObj = json.optJSONObject("daily")
            val dailyList = mutableListOf<DailyForecastItem>()
            var sevenDayRainSum = 0.0

            if (dailyObj != null) {
                val dates = dailyObj.getJSONArray("time")
                val maxTemps = dailyObj.getJSONArray("temperature_2m_max")
                val minTemps = dailyObj.getJSONArray("temperature_2m_min")
                val rainProbs = dailyObj.optJSONArray("precipitation_probability_max")
                val rainSums = dailyObj.optJSONArray("precipitation_sum")
                val codes = dailyObj.getJSONArray("weather_code")

                for (i in 0 until dates.length()) {
                    val date = dates.getString(i)
                    val maxT = maxTemps.getDouble(i)
                    val minT = minTemps.getDouble(i)
                    val rProb = rainProbs?.optInt(i, 20) ?: 20
                    val rSum = rainSums?.optDouble(i, 0.0) ?: 0.0
                    val wCode = codes.getInt(i)

                    sevenDayRainSum += rSum
                    dailyList.add(
                        DailyForecastItem(
                            date = date,
                            maxTemp = maxT,
                            minTemp = minT,
                            rainProbabilityMax = rProb,
                            precipitationSumMm = rSum,
                            weatherCode = wCode,
                            conditionText = decodeWmoWeatherCode(wCode)
                        )
                    )
                }
            }

            val advisory = generateAgronomicAdvisory(currentWeather, dailyList, sevenDayRainSum)

            Result.success(
                CompleteWeatherResult(
                    countyName = locationLabel,
                    latitude = lat,
                    longitude = lng,
                    currentWeather = currentWeather,
                    dailyForecast = dailyList,
                    advisory = advisory
                )
            )
        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error fetching weather", e)
            Result.failure(e)
        }
    }

    private fun generateAgronomicAdvisory(
        current: CurrentWeather,
        daily: List<DailyForecastItem>,
        sevenDayRainSum: Double
    ): AgronomicWeatherAdvisory {
        // Spray Status calculation
        val sprayStatus: SprayStatus
        val sprayReason: String

        if (current.windSpeedKmh > 18.0) {
            sprayStatus = SprayStatus.HAZARDOUS
            sprayReason = "High wind speed (${current.windSpeedKmh} km/h > 18 km/h limit). Extreme droplet drift risk; spray will miss target foliage and contaminate neighboring crops."
        } else if (current.precipitationMm > 0.5) {
            sprayStatus = SprayStatus.HAZARDOUS
            sprayReason = "Active rainfall detected (${current.precipitationMm} mm). Chemical wash-off will occur before pesticides dry or penetrate leaves."
        } else if (daily.firstOrNull()?.rainProbabilityMax ?: 0 > 65) {
            sprayStatus = SprayStatus.MARGINAL
            sprayReason = "Rain probability is high (> 65%) today. Ensure sprays contain a rainfast sticker/adjuvant or wait for clear skies."
        } else if (current.temperatureCelsius > 28.0 && current.relativeHumidity < 45) {
            sprayStatus = SprayStatus.MARGINAL
            sprayReason = "High temperature (${current.temperatureCelsius}°C) and dry air. Fine spray droplets evaporate rapidly in flight. Spray in early morning (6:30 AM–9:30 AM) or late evening."
        } else {
            sprayStatus = SprayStatus.OPTIMAL
            sprayReason = "Optimal spray window: Gentle wind (${current.windSpeedKmh} km/h), favorable temperature (${current.temperatureCelsius}°C), and low rain probability allow perfect chemical deposition."
        }

        // Disease Risk
        val diseaseAlert = if (current.relativeHumidity >= 80 && current.temperatureCelsius in 14.0..22.0) {
            "🚨 CRITICAL FUNGAL BLIGHT / RUST WARNING: High relative humidity (${current.relativeHumidity}%) and cool temperatures create the ideal microclimate for Late Blight (Phytophthora) and Cereal/Bean Rust spore germination. Apply protective multi-site fungicides (Mancozeb, Copper) immediately before rain."
        } else if (current.relativeHumidity >= 70) {
            "MODERATE INFECTION RISK: Moderate morning moisture. Scout cut flowers, French beans, and tomatoes for early foliar flecking."
        } else {
            "LOW DISEASE PRESSURE: Dry weather suppresses fungal sporulation. Inspect fields for sap-sucking pests (spider mites, thrips, aphids) which flourish in dry heat."
        }

        // Fertilizer Application Window
        val fertilizerWindow = if (sevenDayRainSum in 6.0..25.0) {
            "🌱 EXCELLENT TOP-DRESSING WINDOW: Upcoming moderate showers (~${sevenDayRainSum.toInt()}mm total) will dissolve CAN or Urea granules smoothly into the crop root zone without causing nutrient leaching."
        } else if (sevenDayRainSum > 35.0) {
            "⚠️ HEAVY RAINFALL WARNING: Over ${sevenDayRainSum.toInt()}mm rain forecast. Delay broadcasting Urea or nitrate fertilizers to prevent severe leaching into subsoil and surface soil erosion."
        } else {
            "DRY TOP-DRESSING ADVISORY: Low precipitation forecast. If applying CAN, incorporate into the soil with shallow hand-hoeing or apply directly through drip irrigation."
        }

        val soilMoisture = if (sevenDayRainSum > 20.0) "Moist to Wet Soil Conditions" else "Moderate to Dry Topsoil"

        return AgronomicWeatherAdvisory(
            sprayStatus = sprayStatus,
            sprayReason = sprayReason,
            diseaseRiskAlert = diseaseAlert,
            fertilizerTopdressWindow = fertilizerWindow,
            soilMoistureContext = soilMoisture
        )
    }

    private fun decodeWmoWeatherCode(code: Int): String {
        return when (code) {
            0 -> "Clear Sky (Sunny)"
            1 -> "Mainly Clear"
            2 -> "Partly Cloudy"
            3 -> "Overcast"
            45, 48 -> "Fog / Persistent Mist"
            51, 53, 55 -> "Light to Moderate Drizzle"
            61 -> "Slight Rain Showers"
            63 -> "Moderate Rainfall"
            65 -> "Heavy Downpour"
            80, 81, 82 -> "Rain Showers"
            95, 96, 99 -> "Thunderstorm & Rain"
            else -> "Variable Clouds"
        }
    }
}
