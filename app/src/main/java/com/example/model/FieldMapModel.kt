package com.example.model

/**
 * Geographical coordinate point for offline mapping.
 */
data class GeoPoint(
    val latitude: Double,
    val longitude: Double
) {
    fun toFormattedString(): String {
        val latDir = if (latitude >= 0) "N" else "S"
        val lngDir = if (longitude >= 0) "E" else "W"
        return String.format("%.4f°%s, %.4f°%s", Math.abs(latitude), latDir, Math.abs(longitude), lngDir)
    }
}

/**
 * Kenyan Agro-Ecological Zone definition with agronomic, soil, and climate parameters.
 */
data class AgroEcologicalZone(
    val id: String,
    val code: String, // e.g. "AEZ II-LH1"
    val name: String,
    val localZoneName: String,
    val elevationRangeMeters: String,
    val annualRainfallMm: String,
    val soilType: String,
    val soilCharacteristics: String,
    val primaryCrops: List<String>,
    val fertilizerAdvice: String,
    val drainageSlopeAdvice: String,
    val tileColorHex: Long, // Color tint for offline rendering
    val contourIntervalMeters: Int = 20
)

/**
 * Offline map layer visual style.
 */
enum class MapLayerType(val title: String, val description: String) {
    GOOGLE_SATELLITE("Google Satellite", "Real satellite imagery of your farm & fields"),
    SATELLITE_HYBRID("Satellite Hybrid", "Satellite earth texture & parcel survey"),
    AGRO_ECOLOGICAL("Agro-Zones", "Soil capability & rainfall zones"),
    TOPOGRAPHIC("Terrain Topo", "Elevation shading & contours"),
    CADASTRAL("Plot Cadastre", "High-contrast shamba grid")
}

/**
 * Precision GPS satellite telemetry data.
 */
data class GpsPrecisionTelemetry(
    val latitude: Double,
    val longitude: Double,
    val altitudeMeters: Double = 0.0,
    val accuracyMeters: Float = 0.0f,
    val speedKmh: Float = 0.0f,
    val bearingDegrees: Float = 0.0f,
    val isHighPrecision: Boolean = false,
    val provider: String = "GPS (Satellite)",
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Geometric metrics calculated for a mapped farm polygon.
 */
data class FieldBoundaryMetrics(
    val hectares: Double,
    val acres: Double,
    val squareMeters: Double,
    val perimeterMeters: Double,
    val vertexCount: Int,
    val centroid: GeoPoint?,
    val estimatedSlopePercent: Double,
    val slopeCategory: String,
    val edgeDistancesMeters: List<Double>
)

/**
 * Saved field boundary representation for the UI.
 */
data class SavedFieldBoundary(
    val id: Long = 0,
    val name: String,
    val cropName: String,
    val points: List<GeoPoint>,
    val hectares: Double,
    val acres: Double,
    val perimeterMeters: Double,
    val zoneName: String,
    val soilType: String,
    val dateCreated: String,
    val notes: String = ""
)
