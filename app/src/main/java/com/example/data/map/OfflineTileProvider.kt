package com.example.data.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.model.AgroEcologicalZone
import com.example.model.GeoPoint
import com.example.model.MapLayerType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

/**
 * Lightweight, zero-connectivity offline tile provider with live Google Satellite imagery streaming.
 * Loads actual high-resolution Google Satellite tiles when online with disk and memory caching,
 * and seamlessly renders procedural Web Mercator agricultural raster tiles on-device when offline.
 */
class OfflineTileProvider(
    private val diskCacheDir: File? = null
) {

    companion object {
        const val TILE_SIZE = 256
        const val MIN_ZOOM = 10
        const val MAX_ZOOM = 19
        const val DEFAULT_ZOOM = 16
    }

    // High performance memory cache for rendered offline and downloaded satellite tiles
    private val tileMemoryCache = LruCache<String, ImageBitmap>(256)

    // Tracks in-flight tile downloads to prevent duplicate network calls
    private val inFlightRequests = ConcurrentHashMap<String, Boolean>()

    // Callback invoked when a background satellite tile arrives so UI can redraw
    var onTileLoaded: (() -> Unit)? = null

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private val satelliteCacheFolder by lazy {
        diskCacheDir?.let {
            val folder = File(it, "satellite_tiles")
            if (!folder.exists()) folder.mkdirs()
            folder
        }
    }

    /**
     * Converts a geographic latitude/longitude to global world pixel coordinates at the given zoom level.
     */
    fun latLngToWorldPixel(lat: Double, lng: Double, zoom: Int): Pair<Double, Double> {
        val scale = TILE_SIZE * 2.0.pow(zoom.toDouble())
        val x = (lng + 180.0) / 360.0 * scale
        val sinLat = sin(Math.toRadians(lat.coerceIn(-85.0511, 85.0511)))
        val y = (0.5 - ln((1.0 + sinLat) / (1.0 - sinLat)) / (4.0 * PI)) * scale
        return Pair(x, y)
    }

    /**
     * Converts global world pixel coordinates back to geographic latitude/longitude.
     */
    fun worldPixelToLatLng(pixelX: Double, pixelY: Double, zoom: Int): GeoPoint {
        val scale = TILE_SIZE * 2.0.pow(zoom.toDouble())
        val lng = (pixelX / scale) * 360.0 - 180.0
        val n = PI - 2.0 * PI * (pixelY / scale)
        val lat = Math.toDegrees(atan(0.5 * (exp(n) - exp(-n))))
        return GeoPoint(lat.coerceIn(-85.0, 85.0), lng.coerceIn(-180.0, 180.0))
    }

    /**
     * Retrieves or procedurally generates a tile for the given Web Mercator (x, y, zoom) coordinates.
     * When layerType is GOOGLE_SATELLITE, fetches genuine Google Satellite tiles.
     */
    fun getTile(tileX: Int, tileY: Int, zoom: Int, layerType: MapLayerType): ImageBitmap {
        val cacheKey = "${layerType.name}_${zoom}_${tileX}_$tileY"
        val cached = tileMemoryCache.get(cacheKey)
        if (cached != null) {
            return cached
        }

        // Check persistent disk cache for Satellite tiles
        if (layerType == MapLayerType.GOOGLE_SATELLITE && satelliteCacheFolder != null) {
            val diskFile = File(satelliteCacheFolder, "sat_${zoom}_${tileX}_${tileY}.jpg")
            if (diskFile.exists() && diskFile.length() > 0) {
                try {
                    val bitmap = BitmapFactory.decodeFile(diskFile.absolutePath)
                    if (bitmap != null) {
                        val img = bitmap.asImageBitmap()
                        tileMemoryCache.put(cacheKey, img)
                        return img
                    }
                } catch (_: Throwable) {}
            }

            // Asynchronously fetch live Google satellite tile in background
            fetchOnlineSatelliteTile(tileX, tileY, zoom, cacheKey, diskFile)
        }

        // Immediate procedural render fallback (realistic satellite earth texture / soils)
        val renderedTile = renderProceduralTile(
            tileX,
            tileY,
            zoom,
            if (layerType == MapLayerType.GOOGLE_SATELLITE) MapLayerType.SATELLITE_HYBRID else layerType
        )
        tileMemoryCache.put(cacheKey, renderedTile)
        return renderedTile
    }

    private fun fetchOnlineSatelliteTile(
        tileX: Int,
        tileY: Int,
        zoom: Int,
        cacheKey: String,
        targetFile: File
    ) {
        if (inFlightRequests.putIfAbsent(cacheKey, true) != null) return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Primary: Google Satellite Tile (hybrid with road/place overlays)
                // Alternate: Google Satellite photo or ESRI World Imagery
                val serverNum = ((tileX + tileY) % 4).coerceIn(0, 3)
                val primaryUrl = "https://mt$serverNum.google.com/vt/lyrs=y&x=$tileX&y=$tileY&z=$zoom"
                val fallbackUrl = "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/$zoom/$tileY/$tileX"

                var downloadedBytes = downloadTileBytes(primaryUrl)
                if (downloadedBytes == null) {
                    downloadedBytes = downloadTileBytes(fallbackUrl)
                }

                if (downloadedBytes != null && downloadedBytes.isNotEmpty()) {
                    try {
                        targetFile.writeBytes(downloadedBytes)
                    } catch (_: Exception) {}

                    val decodedBitmap = BitmapFactory.decodeByteArray(downloadedBytes, 0, downloadedBytes.size)
                    if (decodedBitmap != null) {
                        val imageBitmap = decodedBitmap.asImageBitmap()
                        tileMemoryCache.put(cacheKey, imageBitmap)
                        withContext(Dispatchers.Main) {
                            onTileLoaded?.invoke()
                        }
                    }
                }
            } catch (_: Throwable) {
                // Graceful offline fallback remains active
            } finally {
                inFlightRequests.remove(cacheKey)
            }
        }
    }

    private fun downloadTileBytes(url: String): ByteArray? {
        return try {
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36")
                .header("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                .build()
            val response = httpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.bytes()
            } else {
                null
            }
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Procedurally renders an offline tile tailored to Kenyan agro-ecological geography.
     */
    private fun renderProceduralTile(
        tileX: Int,
        tileY: Int,
        zoom: Int,
        layerType: MapLayerType
    ): ImageBitmap {
        val bitmap = Bitmap.createBitmap(TILE_SIZE, TILE_SIZE, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Calculate latitude and longitude bounding box for this tile
        val northWest = worldPixelToLatLng(
            (tileX * TILE_SIZE).toDouble(),
            (tileY * TILE_SIZE).toDouble(),
            zoom
        )
        val southEast = worldPixelToLatLng(
            ((tileX + 1) * TILE_SIZE).toDouble(),
            ((tileY + 1) * TILE_SIZE).toDouble(),
            zoom
        )

        val centerLat = (northWest.latitude + southEast.latitude) / 2.0
        val centerLng = (northWest.longitude + southEast.longitude) / 2.0
        val zone = AgroZoneCatalog.findZoneForLocation(centerLat, centerLng)

        // Check if tile intersects known water bodies (Lake Victoria, Lake Naivasha, Lake Nakuru)
        val isWaterBody = (centerLng < 34.5 && centerLat in -1.0..0.4) || // Lake Victoria
                (centerLng in 36.3..36.45 && centerLat in -0.85..-0.7) || // Lake Naivasha
                (centerLng in 36.05..36.12 && centerLat in -0.4..-0.3) // Lake Nakuru

        when (layerType) {
            MapLayerType.GOOGLE_SATELLITE,
            MapLayerType.SATELLITE_HYBRID -> {
                drawSatelliteHybridTile(canvas, zone, isWaterBody, tileX, tileY, zoom)
            }
            MapLayerType.AGRO_ECOLOGICAL -> {
                drawAgroEcologicalTile(canvas, zone, isWaterBody, tileX, tileY, zoom)
            }
            MapLayerType.TOPOGRAPHIC -> {
                drawTopographicTile(canvas, zone, isWaterBody, tileX, tileY, zoom)
            }
            MapLayerType.CADASTRAL -> {
                drawCadastralTile(canvas, zone, isWaterBody, tileX, tileY, zoom)
            }
        }

        return bitmap.asImageBitmap()
    }

    private fun drawAgroEcologicalTile(
        canvas: Canvas,
        zone: AgroEcologicalZone,
        isWater: Boolean,
        tileX: Int,
        tileY: Int,
        zoom: Int
    ) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        if (isWater) {
            paint.color = Color.rgb(66, 133, 244) // Lake blue
            canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)
            // Ripple wave lines
            paint.color = Color.argb(60, 255, 255, 255)
            paint.strokeWidth = 1.5f
            for (y in 20..TILE_SIZE step 40) {
                canvas.drawLine(0f, y.toFloat(), TILE_SIZE.toFloat(), y.toFloat(), paint)
            }
            return
        }

        // Base soil & agro-zone tint
        val baseColor = zone.tileColorHex.toInt()
        val r = (baseColor shr 16) and 0xFF
        val g = (baseColor shr 8) and 0xFF
        val b = baseColor and 0xFF

        // Vary lightness slightly by hash to simulate natural agricultural landscape variation
        val noise = ((tileX * 73 + tileY * 37) % 15) - 7
        val rAdj = (r + noise * 2).coerceIn(40, 240)
        val gAdj = (g + noise * 3).coerceIn(40, 240)
        val bAdj = (b + noise * 2).coerceIn(40, 240)

        paint.color = Color.rgb(rAdj, gAdj, bAdj)
        canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)

        // Shamba / Field parcel boundaries (simulated parcel patchwork)
        if (zoom >= 13) {
            val parcelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(45, 255, 255, 255)
                strokeWidth = 1.5f
                style = Paint.Style.STROKE
            }

            val step = if (zoom >= 16) 64f else 128f
            var x = 0f
            while (x <= TILE_SIZE) {
                canvas.drawLine(x, 0f, x, TILE_SIZE.toFloat(), parcelPaint)
                x += step
            }
            var y = 0f
            while (y <= TILE_SIZE) {
                canvas.drawLine(0f, y, TILE_SIZE.toFloat(), y, parcelPaint)
                y += step
            }

            // Draw subtle hedgerow dots / boundary trees
            if (zoom >= 15) {
                val treePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.argb(70, 27, 94, 32)
                    style = Paint.Style.FILL
                }
                var tx = step / 2
                while (tx < TILE_SIZE) {
                    var ty = step / 2
                    while (ty < TILE_SIZE) {
                        canvas.drawCircle(tx, ty, 3f, treePaint)
                        ty += step
                    }
                    tx += step
                }
            }
        }

        // Rural road / access trace
        if ((tileX + tileY) % 3 == 0) {
            val roadPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(90, 245, 222, 179) // Wheat farm track
                strokeWidth = if (zoom >= 15) 4f else 2f
                style = Paint.Style.STROKE
            }
            canvas.drawLine(0f, 40f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat() - 40f, roadPaint)
        }
    }

    private fun drawTopographicTile(
        canvas: Canvas,
        zone: AgroEcologicalZone,
        isWater: Boolean,
        tileX: Int,
        tileY: Int,
        zoom: Int
    ) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        if (isWater) {
            paint.color = Color.rgb(41, 128, 185)
            canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)
            return
        }

        // Topographic warm organic earth tone
        paint.color = Color.rgb(238, 232, 213) // Solarized light earth
        canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)

        // Elevation contour lines
        val contourPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(80, 160, 120, 80)
            strokeWidth = 1.2f
            style = Paint.Style.STROKE
        }

        val path = Path()
        val spacing = 32f
        var offset = 0f
        while (offset < TILE_SIZE * 2) {
            path.reset()
            path.moveTo(0f, offset)
            path.cubicTo(
                TILE_SIZE * 0.35f, offset - 20f,
                TILE_SIZE * 0.65f, offset + 20f,
                TILE_SIZE.toFloat(), offset
            )
            canvas.drawPath(path, contourPaint)
            offset += spacing
        }

        // Shaded relief gradient
        val reliefPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(25, 0, 0, 0)
            style = Paint.Style.FILL
        }
        canvas.drawRect(RectF(0f, 0f, TILE_SIZE.toFloat(), 12f), reliefPaint)
    }

    private fun drawCadastralTile(
        canvas: Canvas,
        zone: AgroEcologicalZone,
        isWater: Boolean,
        tileX: Int,
        tileY: Int,
        zoom: Int
    ) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        // High contrast dark chalkboard or crisp field grid
        paint.color = Color.rgb(24, 32, 28) // Deep dark field canvas
        canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)

        if (isWater) {
            paint.color = Color.argb(120, 33, 150, 243)
            canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)
            return
        }

        // Primary cadastral grid
        val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(60, 76, 175, 80) // Emerald grid
            strokeWidth = 1f
            style = Paint.Style.STROKE
        }

        val step = 32f
        var x = 0f
        while (x <= TILE_SIZE) {
            canvas.drawLine(x, 0f, x, TILE_SIZE.toFloat(), gridPaint)
            x += step
        }
        var y = 0f
        while (y <= TILE_SIZE) {
            canvas.drawLine(0f, y, TILE_SIZE.toFloat(), y, gridPaint)
            y += step
        }

        // 100m major block demarcation
        val majorGridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(120, 129, 199, 132)
            strokeWidth = 2f
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(floatArrayOf(6f, 4f), 0f)
        }
        canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), majorGridPaint)
    }

    /**
     * Renders realistic agricultural satellite imagery with field hedgerows, furrow tracks, and survey boundaries.
     */
    private fun drawSatelliteHybridTile(
        canvas: Canvas,
        zone: AgroEcologicalZone,
        isWater: Boolean,
        tileX: Int,
        tileY: Int,
        zoom: Int
    ) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        if (isWater) {
            paint.color = Color.rgb(20, 52, 84) // Deep satellite ocean/lake dark navy
            canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)
            // Water glint
            paint.color = Color.argb(40, 80, 180, 240)
            paint.strokeWidth = 1f
            for (y in 10..TILE_SIZE step 25) {
                canvas.drawLine(0f, y.toFloat(), TILE_SIZE.toFloat(), y.toFloat(), paint)
            }
            return
        }

        // Deep satellite farm landscape (natural chlorophyll green mixed with plowed rich soil patches)
        val hash = (tileX * 31 + tileY * 17)
        val isPlowedField = (hash % 5 == 0)
        val isDryFallow = (hash % 7 == 0)

        val baseSatColor = when {
            isPlowedField -> Color.rgb(58, 42, 30) // Dark rich volcanic plow loam
            isDryFallow -> Color.rgb(84, 76, 54) // Golden wheat harvest / dry pasture
            else -> Color.rgb(36, 68, 38) // Deep satellite crop canopy emerald
        }

        paint.color = baseSatColor
        canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), paint)

        // Crop furrow texture (parallel cultivation rows visible in satellite imagery)
        if (zoom >= 14) {
            val furrowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(35, 18, 38, 18)
                strokeWidth = 1.2f
            }
            val furrowSpacing = if (zoom >= 16) 12f else 24f
            var fx = 0f
            while (fx <= TILE_SIZE) {
                canvas.drawLine(fx, 0f, fx, TILE_SIZE.toFloat(), furrowPaint)
                fx += furrowSpacing
            }
        }

        // Natural winding river or drainage waterway
        if (hash % 11 == 0) {
            val streamPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(160, 40, 90, 120)
                strokeWidth = if (zoom >= 16) 8f else 4f
                style = Paint.Style.STROKE
            }
            val path = Path().apply {
                moveTo(0f, 40f)
                cubicTo(80f, 120f, 180f, 60f, TILE_SIZE.toFloat(), 200f)
            }
            canvas.drawPath(path, streamPaint)
        }

        // Shamba / Field Parcel Hedgerow Boundaries (high resolution satellite boundary tree lines)
        if (zoom >= 13) {
            val hedgerowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(180, 16, 44, 16) // Dense green hedgerow shelterbelt
                strokeWidth = if (zoom >= 16) 4f else 2f
            }
            canvas.drawLine(0f, 0f, TILE_SIZE.toFloat(), 0f, hedgerowPaint)
            canvas.drawLine(0f, 0f, 0f, TILE_SIZE.toFloat(), hedgerowPaint)

            // Cadastral glowing vector boundary overlay (Yellow-green survey line)
            val surveyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(140, 255, 235, 59) // High-contrast GPS survey yellow
                strokeWidth = 1.5f
                style = Paint.Style.STROKE
                pathEffect = DashPathEffect(floatArrayOf(12f, 8f), 0f)
            }
            canvas.drawRect(0f, 0f, TILE_SIZE.toFloat(), TILE_SIZE.toFloat(), surveyPaint)
        }

        // Farm roads and access paths
        if ((tileX + tileY) % 4 == 0) {
            val roadPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(190, 180, 150, 110) // Dust/murram farm track
                strokeWidth = if (zoom >= 16) 6f else 3f
                style = Paint.Style.STROKE
            }
            canvas.drawLine(30f, 0f, TILE_SIZE.toFloat() - 30f, TILE_SIZE.toFloat(), roadPaint)
        }
    }

    /**
     * Clears cached tiles to free memory.
     */
    fun clearCache() {
        tileMemoryCache.evictAll()
    }
}
