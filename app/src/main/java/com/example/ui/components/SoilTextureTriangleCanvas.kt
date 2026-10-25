package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.soil.SoilClassificationCatalog
import com.example.model.soil.SoilCoordinate
import com.example.model.soil.SoilTextureClass
import kotlin.math.sqrt

/**
 * High-precision, interactive USDA Soil Texture Triangle Canvas.
 * Renders all 12 USDA textural classes with distinct colors, percentage grid lines,
 * zone abbreviation labels, touch/drag inspection, and an animated pulsing pointer.
 */
@Composable
fun SoilTextureTriangleCanvas(
    sand: Double,
    silt: Double,
    clay: Double,
    classifiedClass: SoilTextureClass,
    onCoordinateChanged: (sand: Double, silt: Double, clay: Double) -> Unit,
    modifier: Modifier = Modifier
) {
    // Smooth animated position transitions for the pointer
    val animatedSand by animateFloatAsState(
        targetValue = sand.toFloat(),
        animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
        label = "animated_sand"
    )
    val animatedSilt by animateFloatAsState(
        targetValue = silt.toFloat(),
        animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
        label = "animated_silt"
    )
    val animatedClay by animateFloatAsState(
        targetValue = clay.toFloat(),
        animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
        label = "animated_clay"
    )

    // Pulsing animation for pointer
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_transition")
    val pulseRadiusScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_scale"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_alpha"
    )

    val surfaceColor = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val primaryColor = MaterialTheme.colorScheme.primary

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("soil_texture_triangle_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with status indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "USDA Soil Texture Triangle",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Tap or drag on chart to inspect soil class",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = classifiedClass.chartColor.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, classifiedClass.chartColor)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(classifiedClass.chartColor)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = classifiedClass.code,
                            fontWeight = FontWeight.Bold,
                            color = classifiedClass.chartColor,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Main Canvas Area
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.15f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
            ) {
                val boxWidth = constraints.maxWidth.toFloat()
                val boxHeight = constraints.maxHeight.toFloat()

                // Geometry padding
                val marginHorizontal = 45f
                val marginTop = 36f
                val marginBottom = 48f

                // Vertices
                val leftBaseX = marginHorizontal
                val rightBaseX = boxWidth - marginHorizontal
                val baseY = boxHeight - marginBottom
                val apexX = (leftBaseX + rightBaseX) / 2f
                val apexY = marginTop

                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("soil_triangle_canvas")
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                processTouch(
                                    offset = offset,
                                    leftBaseX = leftBaseX,
                                    rightBaseX = rightBaseX,
                                    baseY = baseY,
                                    apexX = apexX,
                                    apexY = apexY,
                                    onCoordinateChanged = onCoordinateChanged
                                )
                            }
                        }
                        .pointerInput(Unit) {
                            detectDragGestures { change, _ ->
                                change.consume()
                                processTouch(
                                    offset = change.position,
                                    leftBaseX = leftBaseX,
                                    rightBaseX = rightBaseX,
                                    baseY = baseY,
                                    apexX = apexX,
                                    apexY = apexY,
                                    onCoordinateChanged = onCoordinateChanged
                                )
                            }
                        }
                ) {
                    // Helper lambdas for ternary coordinate projection
                    fun toPixel(coord: SoilCoordinate): Offset {
                        val s = (coord.sand / 100.0).toFloat()
                        val si = (coord.silt / 100.0).toFloat()
                        val c = (coord.clay / 100.0).toFloat()
                        val px = s * leftBaseX + si * rightBaseX + c * apexX
                        val py = s * baseY + si * baseY + c * apexY
                        return Offset(px, py)
                    }

                    // 1. Draw outer equilateral triangle background
                    val outerPath = Path().apply {
                        moveTo(apexX, apexY)
                        lineTo(rightBaseX, baseY)
                        lineTo(leftBaseX, baseY)
                        close()
                    }
                    drawPath(
                        path = outerPath,
                        color = Color.White.copy(alpha = 0.9f)
                    )

                    // 2. Draw 12 USDA Zones
                    SoilClassificationCatalog.trianglePolygons.forEach { zone ->
                        val isSelected = zone.soilClass == classifiedClass
                        val zonePath = Path().apply {
                            val firstPt = toPixel(zone.vertices.first())
                            moveTo(firstPt.x, firstPt.y)
                            for (i in 1 until zone.vertices.size) {
                                val pt = toPixel(zone.vertices[i])
                                lineTo(pt.x, pt.y)
                            }
                            close()
                        }

                        // Fill zone with distinct color
                        val fillColor = if (isSelected) {
                            zone.soilClass.chartColor.copy(alpha = 0.85f)
                        } else {
                            zone.soilClass.chartColor.copy(alpha = 0.35f)
                        }
                        drawPath(path = zonePath, color = fillColor, style = Fill)

                        // Zone border
                        val strokeColor = if (isSelected) {
                            Color.Black.copy(alpha = 0.8f)
                        } else {
                            Color(0xFF555555).copy(alpha = 0.5f)
                        }
                        drawPath(
                            path = zonePath,
                            color = strokeColor,
                            style = Stroke(width = if (isSelected) 2.5f else 1.0f)
                        )

                        // Draw zone text label (abbreviation)
                        val centerPx = toPixel(zone.centerCoordinate)
                        drawContext.canvas.nativeCanvas.apply {
                            val paint = android.graphics.Paint().apply {
                                color = if (isSelected) {
                                    android.graphics.Color.WHITE
                                } else {
                                    android.graphics.Color.DKGRAY
                                }
                                textSize = if (isSelected) 24f else 20f
                                isFakeBoldText = isSelected
                                textAlign = android.graphics.Paint.Align.CENTER
                                isAntiAlias = true
                            }
                            drawText(zone.soilClass.code, centerPx.x, centerPx.y + 7f, paint)
                        }
                    }

                    // 3. Draw percentage tick lines & labels
                    drawGridTicks(
                        leftBaseX = leftBaseX,
                        rightBaseX = rightBaseX,
                        baseY = baseY,
                        apexX = apexX,
                        apexY = apexY
                    )

                    // 4. Draw outer triangle border
                    drawPath(
                        path = outerPath,
                        color = Color.Black,
                        style = Stroke(width = 2.5f)
                    )

                    // 5. Calculate & Draw the Animated Pointer
                    val currentCoord = SoilCoordinate(
                        sand = animatedSand.toDouble(),
                        silt = animatedSilt.toDouble(),
                        clay = animatedClay.toDouble()
                    )
                    val pointerPx = toPixel(currentCoord)

                    // Draw outer pulsing ripple
                    drawCircle(
                        color = Color(0xFFD32F2F).copy(alpha = pulseAlpha),
                        radius = 16f * pulseRadiusScale,
                        center = pointerPx,
                        style = Stroke(width = 3.5f)
                    )

                    // Target indicator ring
                    drawCircle(
                        color = Color.White,
                        radius = 10f,
                        center = pointerPx,
                        style = Fill
                    )
                    drawCircle(
                        color = Color(0xFFD32F2F),
                        radius = 8f,
                        center = pointerPx,
                        style = Fill
                    )
                    drawCircle(
                        color = Color.Black,
                        radius = 10f,
                        center = pointerPx,
                        style = Stroke(width = 2f)
                    )
                    drawCircle(
                        color = Color.White,
                        radius = 3f,
                        center = pointerPx,
                        style = Fill
                    )

                    // Pointer tooltip text badge
                    val tooltipText = "${classifiedClass.displayName} (${animatedSand.toInt()}% S, ${animatedSilt.toInt()}% Si, ${animatedClay.toInt()}% C)"
                    val tooltipY = (pointerPx.y - 18f).coerceAtLeast(apexY + 12f)
                    val tooltipX = pointerPx.x.coerceIn(marginHorizontal + 70f, boxWidth - marginHorizontal - 70f)

                    drawContext.canvas.nativeCanvas.apply {
                        val textPaint = android.graphics.Paint().apply {
                            color = android.graphics.Color.WHITE
                            textSize = 20f
                            isFakeBoldText = true
                            textAlign = android.graphics.Paint.Align.CENTER
                            isAntiAlias = true
                        }
                        val bgPaint = android.graphics.Paint().apply {
                            color = android.graphics.Color.BLACK
                            style = android.graphics.Paint.Style.FILL
                            isAntiAlias = true
                        }
                        val textWidth = textPaint.measureText(tooltipText)
                        val rectF = android.graphics.RectF(
                            tooltipX - textWidth / 2f - 10f,
                            tooltipY - 22f,
                            tooltipX + textWidth / 2f + 10f,
                            tooltipY + 6f
                        )
                        drawRoundRect(rectF, 8f, 8f, bgPaint)
                        drawText(tooltipText, tooltipX, tooltipY - 4f, textPaint)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Axis Orientation Legend
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "◀ Left: Clay % (0-100)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Bottom: Sand % (100-0) ▶",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Right: Silt % (0-100) ▲",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * Draw percentage ticks along the 3 axes.
 */
private fun DrawScope.drawGridTicks(
    leftBaseX: Float,
    rightBaseX: Float,
    baseY: Float,
    apexX: Float,
    apexY: Float
) {
    val tickLength = 7f
    val tickPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.GRAY
        textSize = 14f
        textAlign = android.graphics.Paint.Align.RIGHT
        isAntiAlias = true
    }

    // Clay ticks (horizontal along left side: 20, 40, 60, 80)
    for (clayPercent in 20..80 step 20) {
        val frac = clayPercent / 100f
        val py = baseY - frac * (baseY - apexY)
        val leftX = leftBaseX + frac * (apexX - leftBaseX)
        val rightX = rightBaseX - frac * (rightBaseX - apexX)

        // Draw faint horizontal guide line
        drawLine(
            color = Color.LightGray.copy(alpha = 0.5f),
            start = Offset(leftX, py),
            end = Offset(rightX, py),
            strokeWidth = 1f
        )

        // Draw tick on left
        drawLine(
            color = Color.DarkGray,
            start = Offset(leftX - tickLength, py),
            end = Offset(leftX, py),
            strokeWidth = 1.5f
        )
        drawContext.canvas.nativeCanvas.drawText(
            "$clayPercent",
            leftX - tickLength - 3f,
            py + 5f,
            tickPaint
        )
    }

    // Sand ticks (along bottom base: 20, 40, 60, 80)
    val sandTickPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.GRAY
        textSize = 14f
        textAlign = android.graphics.Paint.Align.CENTER
        isAntiAlias = true
    }
    for (sandPercent in 20..80 step 20) {
        val frac = sandPercent / 100f
        // Sand = 100 at leftBaseX, 0 at rightBaseX
        val px = rightBaseX - frac * (rightBaseX - leftBaseX)
        drawLine(
            color = Color.DarkGray,
            start = Offset(px, baseY),
            end = Offset(px, baseY + tickLength),
            strokeWidth = 1.5f
        )
        drawContext.canvas.nativeCanvas.drawText(
            "$sandPercent",
            px,
            baseY + tickLength + 14f,
            sandTickPaint
        )
    }
}

/**
 * Calculate Barycentric coordinates from touch/drag offset and fire callback.
 */
private fun processTouch(
    offset: Offset,
    leftBaseX: Float,
    rightBaseX: Float,
    baseY: Float,
    apexX: Float,
    apexY: Float,
    onCoordinateChanged: (Double, Double, Double) -> Unit
) {
    val totalHeight = baseY - apexY
    if (totalHeight <= 0) return

    // Clay fraction is direct vertical proportion
    val rawClay = ((baseY - offset.y) / totalHeight).coerceIn(0f, 1f)

    // Base width
    val totalBaseWidth = rightBaseX - leftBaseX
    if (totalBaseWidth <= 0) return

    val rawSilt = (((offset.x - leftBaseX) * totalHeight - (baseY - offset.y) * (apexX - leftBaseX)) /
            (totalBaseWidth * totalHeight)).coerceIn(0f, 1f)

    val rawSand = (1f - rawClay - rawSilt).coerceIn(0f, 1f)

    val sum = rawSand + rawSilt + rawClay
    if (sum > 0f) {
        val s = (rawSand / sum * 100.0).coerceIn(0.0, 100.0)
        val si = (rawSilt / sum * 100.0).coerceIn(0.0, 100.0)
        val c = (100.0 - s - si).coerceIn(0.0, 100.0)
        onCoordinateChanged(
            Math.round(s * 10.0) / 10.0,
            Math.round(si * 10.0) / 10.0,
            Math.round(c * 10.0) / 10.0
        )
    }
}
