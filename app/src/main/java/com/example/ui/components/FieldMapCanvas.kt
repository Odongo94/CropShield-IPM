package com.example.ui.components

import android.graphics.Paint as AndroidPaint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import com.example.data.map.FieldGeometryUtil
import com.example.data.map.OfflineTileProvider
import com.example.model.GeoPoint
import com.example.model.MapLayerType
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun FieldMapCanvas(
    centerLat: Double,
    centerLng: Double,
    zoomLevel: Int,
    layerType: MapLayerType,
    tileProvider: OfflineTileProvider,
    boundaryPoints: List<GeoPoint>,
    selectedPointIndex: Int?,
    currentGpsLocation: GeoPoint?,
    onPanDelta: (deltaX: Float, deltaY: Float) -> Unit,
    onZoomChange: (deltaZoom: Float) -> Unit,
    onMapTapped: (GeoPoint) -> Unit,
    onPointSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tileRevision: Int = 0
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseScale"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .testTag("field_map_canvas")
            .pointerInput(centerLat, centerLng, zoomLevel) {
                detectTransformGestures { _, pan, zoomFactor, _ ->
                    if (pan != Offset.Zero) {
                        onPanDelta(pan.x, pan.y)
                    }
                    if (zoomFactor != 1.0f) {
                        val zoomDelta = if (zoomFactor > 1.0f) 0.15f else -0.15f
                        onZoomChange(zoomDelta)
                    }
                }
            }
            .pointerInput(boundaryPoints, centerLat, centerLng, zoomLevel) {
                detectTapGestures { tapOffset ->
                    val width = size.width
                    val height = size.height

                    // Check if tap hit near an existing vertex (within 32dp)
                    val (centerWorldX, centerWorldY) = tileProvider.latLngToWorldPixel(centerLat, centerLng, zoomLevel)
                    var tappedPointIdx: Int? = null

                    for (i in boundaryPoints.indices) {
                        val (pWorldX, pWorldY) = tileProvider.latLngToWorldPixel(
                            boundaryPoints[i].latitude,
                            boundaryPoints[i].longitude,
                            zoomLevel
                        )
                        val screenX = (pWorldX - centerWorldX + width / 2).toFloat()
                        val screenY = (pWorldY - centerWorldY + height / 2).toFloat()

                        val dist = (tapOffset.x - screenX) * (tapOffset.x - screenX) +
                                (tapOffset.y - screenY) * (tapOffset.y - screenY)

                        if (dist < 40f * 40f) { // ~40px radius touch target
                            tappedPointIdx = i
                            break
                        }
                    }

                    if (tappedPointIdx != null) {
                        onPointSelected(tappedPointIdx)
                    } else {
                        // Convert screen tap offset to GeoPoint
                        val tapWorldX = centerWorldX - width / 2 + tapOffset.x
                        val tapWorldY = centerWorldY - height / 2 + tapOffset.y
                        val tappedGeoPoint = tileProvider.worldPixelToLatLng(tapWorldX, tapWorldY, zoomLevel)
                        onMapTapped(tappedGeoPoint)
                    }
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val _rev = tileRevision
            val canvasWidth = size.width
            val canvasHeight = size.height

            val (centerWorldX, centerWorldY) = tileProvider.latLngToWorldPixel(centerLat, centerLng, zoomLevel)

            // 1. RENDER OFFLINE TILES
            renderOfflineTiles(
                canvasWidth = canvasWidth,
                canvasHeight = canvasHeight,
                centerWorldX = centerWorldX,
                centerWorldY = centerWorldY,
                zoom = zoomLevel,
                layerType = layerType,
                tileProvider = tileProvider
            )

            // 2. RENDER FIELD BOUNDARY POLYGON
            if (boundaryPoints.isNotEmpty()) {
                renderFieldPolygon(
                    points = boundaryPoints,
                    selectedPointIndex = selectedPointIndex,
                    centerWorldX = centerWorldX,
                    centerWorldY = centerWorldY,
                    canvasWidth = canvasWidth,
                    canvasHeight = canvasHeight,
                    zoom = zoomLevel,
                    tileProvider = tileProvider,
                    pulseScale = pulseScale,
                    pulseAlpha = pulseAlpha
                )
            }

            // 3. RENDER CURRENT GPS LOCATION MARKER
            if (currentGpsLocation != null) {
                val (gpsWorldX, gpsWorldY) = tileProvider.latLngToWorldPixel(
                    currentGpsLocation.latitude,
                    currentGpsLocation.longitude,
                    zoomLevel
                )
                val gpsScreenX = (gpsWorldX - centerWorldX + canvasWidth / 2).toFloat()
                val gpsScreenY = (gpsWorldY - centerWorldY + canvasHeight / 2).toFloat()

                // Accuracy pulse
                drawCircle(
                    color = Color(0x332196F3),
                    radius = 24f * pulseScale,
                    center = Offset(gpsScreenX, gpsScreenY)
                )
                drawCircle(
                    color = Color.White,
                    radius = 9f,
                    center = Offset(gpsScreenX, gpsScreenY)
                )
                drawCircle(
                    color = Color(0xFF1976D2),
                    radius = 6f,
                    center = Offset(gpsScreenX, gpsScreenY)
                )
            }

            // 4. RENDER SCALE BAR
            renderScaleBar(
                centerLat = centerLat,
                zoom = zoomLevel,
                canvasWidth = canvasWidth,
                canvasHeight = canvasHeight
            )
        }
    }
}

private fun DrawScope.renderOfflineTiles(
    canvasWidth: Float,
    canvasHeight: Float,
    centerWorldX: Double,
    centerWorldY: Double,
    zoom: Int,
    layerType: MapLayerType,
    tileProvider: OfflineTileProvider
) {
    val tileSize = OfflineTileProvider.TILE_SIZE.toDouble()
    val maxTiles = 2.0.pow(zoom.toDouble()).toInt()

    val minWorldX = centerWorldX - canvasWidth / 2
    val maxWorldX = centerWorldX + canvasWidth / 2
    val minWorldY = centerWorldY - canvasHeight / 2
    val maxWorldY = centerWorldY + canvasHeight / 2

    val minTileX = floor(minWorldX / tileSize).toInt().coerceIn(0, maxTiles - 1)
    val maxTileX = floor(maxWorldX / tileSize).toInt().coerceIn(0, maxTiles - 1)
    val minTileY = floor(minWorldY / tileSize).toInt().coerceIn(0, maxTiles - 1)
    val maxTileY = floor(maxWorldY / tileSize).toInt().coerceIn(0, maxTiles - 1)

    for (tileX in minTileX..maxTileX) {
        for (tileY in minTileY..maxTileY) {
            val tileBitmap = tileProvider.getTile(tileX, tileY, zoom, layerType)
            val screenX = (tileX * tileSize - minWorldX).toFloat()
            val screenY = (tileY * tileSize - minWorldY).toFloat()

            drawImage(
                image = tileBitmap,
                topLeft = Offset(screenX, screenY)
            )
        }
    }
}

private fun DrawScope.renderFieldPolygon(
    points: List<GeoPoint>,
    selectedPointIndex: Int?,
    centerWorldX: Double,
    centerWorldY: Double,
    canvasWidth: Float,
    canvasHeight: Float,
    zoom: Int,
    tileProvider: OfflineTileProvider,
    pulseScale: Float,
    pulseAlpha: Float
) {
    val screenPoints = points.map { pt ->
        val (wX, wY) = tileProvider.latLngToWorldPixel(pt.latitude, pt.longitude, zoom)
        Offset(
            x = (wX - centerWorldX + canvasWidth / 2).toFloat(),
            y = (wY - centerWorldY + canvasHeight / 2).toFloat()
        )
    }

    // 1. Draw filled polygon if 3 or more points
    if (screenPoints.size >= 3) {
        val polyPath = Path().apply {
            moveTo(screenPoints[0].x, screenPoints[0].y)
            for (i in 1 until screenPoints.size) {
                lineTo(screenPoints[i].x, screenPoints[i].y)
            }
            close()
        }

        // Semi-transparent field fill
        drawPath(
            path = polyPath,
            color = Color(0x662E7D32), // Emerald 40% alpha
            style = Fill
        )

        // Bold boundary border
        drawPath(
            path = polyPath,
            color = Color(0xFF1B5E20), // Dark green stroke
            style = Stroke(
                width = 4.5f,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // Draw edge measurement annotations
        val edgeDistances = FieldGeometryUtil.calculateEdgeDistancesMeters(points)
        val paint = AndroidPaint().apply {
            color = android.graphics.Color.WHITE
            textSize = 28f
            isAntiAlias = true
            textAlign = AndroidPaint.Align.CENTER
            setShadowLayer(4f, 1f, 1f, android.graphics.Color.BLACK)
        }

        for (i in screenPoints.indices) {
            val p1 = screenPoints[i]
            val p2 = screenPoints[(i + 1) % screenPoints.size]
            val midX = (p1.x + p2.x) / 2
            val midY = (p1.y + p2.y) / 2

            if (i < edgeDistances.size) {
                val distM = edgeDistances[i]
                val text = if (distM >= 1000) String.format("%.2fkm", distM / 1000.0) else String.format("%.0fm", distM)
                drawContext.canvas.nativeCanvas.drawText(text, midX, midY - 6f, paint)
            }
        }
    } else if (screenPoints.size == 2) {
        // Line between 2 points
        drawLine(
            color = Color(0xFF1B5E20),
            start = screenPoints[0],
            end = screenPoints[1],
            strokeWidth = 4.5f,
            cap = StrokeCap.Round
        )
    }

    // 2. Draw numbered vertex markers
    val numberPaint = AndroidPaint().apply {
        color = android.graphics.Color.WHITE
        textSize = 26f
        isFakeBoldText = true
        isAntiAlias = true
        textAlign = AndroidPaint.Align.CENTER
    }

    for (i in screenPoints.indices) {
        val pt = screenPoints[i]
        val isSelected = selectedPointIndex == i

        // Selection ring
        if (isSelected) {
            drawCircle(
                color = Color(0xFFFFA000).copy(alpha = pulseAlpha),
                radius = 26f * pulseScale,
                center = pt
            )
            drawCircle(
                color = Color(0xFFFFA000),
                radius = 16f,
                center = pt,
                style = Stroke(width = 3f)
            )
        }

        // Marker base
        drawCircle(
            color = if (isSelected) Color(0xFFFF6F00) else Color(0xFF2E7D32),
            radius = 14f,
            center = pt
        )
        drawCircle(
            color = Color.White,
            radius = 14f,
            center = pt,
            style = Stroke(width = 2.5f)
        )

        // Draw point index number
        drawContext.canvas.nativeCanvas.drawText(
            (i + 1).toString(),
            pt.x,
            pt.y + 9f,
            numberPaint
        )
    }
}

private fun DrawScope.renderScaleBar(
    centerLat: Double,
    zoom: Int,
    canvasWidth: Float,
    canvasHeight: Float
) {
    // Meters per pixel calculation on Web Mercator
    val latRad = Math.toRadians(centerLat)
    val metersPerPixel = (156543.03392 * cos(latRad)) / 2.0.pow(zoom.toDouble())

    // Target scale width around 80-140 dp
    val targetPixels = 100f
    val roughMeters = metersPerPixel * targetPixels

    // Round roughMeters to a neat agricultural unit (20m, 50m, 100m, 200m, 500m, 1km, 2km)
    val scaleMeters = when {
        roughMeters < 35 -> 25.0
        roughMeters < 75 -> 50.0
        roughMeters < 150 -> 100.0
        roughMeters < 350 -> 250.0
        roughMeters < 750 -> 500.0
        roughMeters < 1500 -> 1000.0
        else -> 2000.0
    }

    val actualPixelWidth = (scaleMeters / metersPerPixel).toFloat()
    val marginX = 24f
    val marginY = canvasHeight - 32f

    // Scale line
    drawLine(
        color = Color(0xCC000000),
        start = Offset(marginX, marginY),
        end = Offset(marginX + actualPixelWidth, marginY),
        strokeWidth = 4f,
        cap = StrokeCap.Square
    )
    // Left & right ticks
    drawLine(
        color = Color(0xCC000000),
        start = Offset(marginX, marginY - 6f),
        end = Offset(marginX, marginY + 6f),
        strokeWidth = 3f
    )
    drawLine(
        color = Color(0xCC000000),
        start = Offset(marginX + actualPixelWidth, marginY - 6f),
        end = Offset(marginX + actualPixelWidth, marginY + 6f),
        strokeWidth = 3f
    )

    val labelText = if (scaleMeters >= 1000) "${(scaleMeters / 1000).roundToInt()} km" else "${scaleMeters.roundToInt()} m"
    val textPaint = AndroidPaint().apply {
        color = android.graphics.Color.DKGRAY
        textSize = 24f
        isFakeBoldText = true
        isAntiAlias = true
        textAlign = AndroidPaint.Align.LEFT
    }
    drawContext.canvas.nativeCanvas.drawText(
        labelText,
        marginX + 6f,
        marginY - 8f,
        textPaint
    )
}
