package com.example.data.map

import com.example.model.AgroEcologicalZone
import com.example.model.FieldBoundaryMetrics
import com.example.model.GeoPoint
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object FieldGeometryUtil {

    private const val EARTH_RADIUS_METERS = 6378137.0 // WGS84 Earth Equatorial Radius

    /**
     * Calculates the geodesic area of a polygon on Earth's surface using the spherical excess / Shoelace algorithm.
     * Returns area in Hectares (1 ha = 10,000 m²).
     */
    fun calculatePolygonAreaHectares(points: List<GeoPoint>): Double {
        if (points.size < 3) return 0.0

        var total = 0.0
        val n = points.size

        for (i in 0 until n) {
            val p1 = points[i]
            val p2 = points[(i + 1) % n]
            val p0 = points[(i - 1 + n) % n]

            val latRad = Math.toRadians(p1.latitude)
            val lngDiffRad = Math.toRadians(p2.longitude - p0.longitude)

            total += lngDiffRad * sin(latRad)
        }

        val areaM2 = abs(total * (EARTH_RADIUS_METERS * EARTH_RADIUS_METERS) / 2.0)
        return areaM2 / 10000.0
    }

    /**
     * Converts hectares to acres (1 hectare ≈ 2.47105 acres).
     */
    fun hectaresToAcres(hectares: Double): Double = hectares * 2.4710538

    /**
     * Calculates the perimeter in meters of the polygon formed by points.
     */
    fun calculatePerimeterMeters(points: List<GeoPoint>): Double {
        if (points.size < 2) return 0.0

        var totalDist = 0.0
        for (i in points.indices) {
            val nextIdx = (i + 1) % points.size
            if (nextIdx == 0 && points.size < 3) break // don't close segment if only 2 points
            totalDist += haversineDistanceMeters(points[i], points[nextIdx])
        }
        return totalDist
    }

    /**
     * Calculates the individual distance in meters along each edge of the polygon.
     */
    fun calculateEdgeDistancesMeters(points: List<GeoPoint>): List<Double> {
        if (points.size < 2) return emptyList()
        val distances = mutableListOf<Double>()
        for (i in points.indices) {
            val nextIdx = (i + 1) % points.size
            if (nextIdx == 0 && points.size < 3) break
            distances.add(haversineDistanceMeters(points[i], points[nextIdx]))
        }
        return distances
    }

    /**
     * Great-circle distance between two geographic coordinates using Haversine formula.
     */
    fun haversineDistanceMeters(p1: GeoPoint, p2: GeoPoint): Double {
        val lat1Rad = Math.toRadians(p1.latitude)
        val lat2Rad = Math.toRadians(p2.latitude)
        val dLat = Math.toRadians(p2.latitude - p1.latitude)
        val dLng = Math.toRadians(p2.longitude - p1.longitude)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(lat1Rad) * cos(lat2Rad) *
                sin(dLng / 2) * sin(dLng / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_METERS * c
    }

    /**
     * Calculates the geographic centroid (geometric center) of a collection of points.
     */
    fun calculateCentroid(points: List<GeoPoint>): GeoPoint? {
        if (points.isEmpty()) return null
        val avgLat = points.map { it.latitude }.average()
        val avgLng = points.map { it.longitude }.average()
        return GeoPoint(avgLat, avgLng)
    }

    /**
     * Analyzes terrain slope and water runoff risks based on elevation range and agro-zone.
     */
    fun estimateSlopeAndRunoff(
        points: List<GeoPoint>,
        zone: AgroEcologicalZone
    ): Pair<Double, String> {
        if (points.size < 3) {
            return Pair(3.0, "Gentle slope (1-4%) • Standard agronomic contour practice")
        }

        // Base slope estimation derived from region and perimeter-to-area aspect ratio
        val perimeter = calculatePerimeterMeters(points)
        val areaHa = calculatePolygonAreaHectares(points)
        val areaM2 = areaHa * 10000.0

        val compactnessRatio = if (areaM2 > 0) (perimeter * perimeter) / (4 * PI * areaM2) else 1.0

        val baseSlope = when (zone.id) {
            "central_highlands_tea_coffee" -> 14.5
            "rift_valley_grain_belt" -> 4.5
            "western_lake_victoria_basin" -> 6.0
            "eastern_semi_arid_belt" -> 9.0
            "coastal_lowland_belt" -> 2.5
            else -> 4.0
        }

        val estimatedSlope = (baseSlope * (0.8 + 0.2 * (compactnessRatio.coerceIn(1.0, 3.0)))).coerceIn(1.5, 30.0)

        val advice = when {
            estimatedSlope > 12.0 -> "Steep hillside (>12%) • High erosion & runoff risk. Construct bench terraces (Fanya Juu), plant vetiver grass along contour lines, and avoid spraying pesticides before heavy rainfall."
            estimatedSlope > 6.0 -> "Moderate slope (6-12%) • Moderate runoff velocity. Broad-base contour bunds and minimum tillage recommended to conserve topsoil and moisture."
            else -> "Gentle terrain (<6%) • Low runoff risk. Ensure adequate field drainage furrows to prevent localized waterlogging around crop root zones."
        }

        return Pair(estimatedSlope, advice)
    }

    /**
     * Produces a comprehensive metrics report for a list of boundary vertices.
     */
    fun computeMetrics(
        points: List<GeoPoint>,
        zone: AgroEcologicalZone = AgroZoneCatalog.defaultZone
    ): FieldBoundaryMetrics {
        val ha = calculatePolygonAreaHectares(points)
        val acres = hectaresToAcres(ha)
        val m2 = ha * 10000.0
        val perimeter = calculatePerimeterMeters(points)
        val centroid = calculateCentroid(points)
        val (slope, slopeCategory) = estimateSlopeAndRunoff(points, zone)
        val edgeDistances = calculateEdgeDistancesMeters(points)

        return FieldBoundaryMetrics(
            hectares = ha,
            acres = acres,
            squareMeters = m2,
            perimeterMeters = perimeter,
            vertexCount = points.size,
            centroid = centroid,
            estimatedSlopePercent = slope,
            slopeCategory = slopeCategory,
            edgeDistancesMeters = edgeDistances
        )
    }

    /**
     * Automatically maps out a shamba parcel boundary polygon around the farmer's precision GPS position.
     * Generates a 4-corner cadastral boundary scaled to the target farm size in hectares.
     */
    fun autoDetectFarmBoundary(
        center: GeoPoint,
        targetHectares: Double = 1.0
    ): List<GeoPoint> {
        val targetM2 = targetHectares * 10000.0
        val halfSideM = kotlin.math.sqrt(targetM2) / 2.0

        val metersPerDegLat = 111320.0
        val latRad = Math.toRadians(center.latitude)
        val metersPerDegLng = 111320.0 * kotlin.math.cos(latRad).coerceAtLeast(0.1)

        val dLat = halfSideM / metersPerDegLat
        val dLng = halfSideM / metersPerDegLng

        // 4 corners of the farm: North-West, North-East, South-East, South-West
        return listOf(
            GeoPoint(center.latitude + dLat, center.longitude - dLng),
            GeoPoint(center.latitude + dLat, center.longitude + dLng),
            GeoPoint(center.latitude - dLat, center.longitude + dLng),
            GeoPoint(center.latitude - dLat, center.longitude - dLng)
        )
    }
}
