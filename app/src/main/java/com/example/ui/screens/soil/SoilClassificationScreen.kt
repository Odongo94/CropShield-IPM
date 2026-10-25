package com.example.ui.screens.soil

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.soil.SoilClassificationCatalog
import com.example.model.soil.SoilTextureClass
import com.example.ui.components.CropRotationDesignerComponent
import com.example.ui.components.SoilTextureTriangleCanvas
import kotlin.math.roundToInt

enum class SoilScreenTab(val title: String, val icon: ImageVector) {
    CALCULATOR("Texture Triangle", Icons.Default.Terrain),
    ADVISORY("Crop & Soil Advice", Icons.Default.Agriculture),
    ROTATION("Rotation Designer", Icons.Default.Loop)
}

enum class SoilInputMethod(val title: String) {
    PERCENTAGE_SLIDERS("Particle Sliders (%)"),
    JAR_SEDIMENTATION("Sedimentation Jar Test")
}

@OptIn(ExperimentalLayoutApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun SoilClassificationScreen(
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var activeTab by remember { mutableStateOf(SoilScreenTab.CALCULATOR) }
    var inputMethod by remember { mutableStateOf(SoilInputMethod.PERCENTAGE_SLIDERS) }

    // Particle percentages
    var sandPercent by remember { mutableDoubleStateOf(42.0) }
    var siltPercent by remember { mutableDoubleStateOf(38.0) }
    var clayPercent by remember { mutableDoubleStateOf(20.0) }

    // Jar test inputs in mm
    var jarSandMm by remember { mutableStateOf("25") }
    var jarSiltMm by remember { mutableStateOf("20") }
    var jarClayMm by remember { mutableStateOf("15") }

    // Real-time classification
    val classifiedClass = remember(sandPercent, siltPercent, clayPercent) {
        SoilClassificationCatalog.classifySoil(sandPercent, siltPercent, clayPercent)
    }

    val soilProfile = remember(classifiedClass) {
        SoilClassificationCatalog.getProfile(classifiedClass)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("soil_classification_screen")
    ) {
        // Top Navigation Tabs
        PrimaryTabRow(
            selectedTabIndex = activeTab.ordinal,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth().testTag("soil_screen_tab_row")
        ) {
            SoilScreenTab.values().forEach { tab ->
                val isSelected = activeTab == tab
                Tab(
                    selected = isSelected,
                    onClick = { activeTab = tab },
                    text = {
                        Text(
                            text = tab.title,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.title,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier.testTag("tab_${tab.name.lowercase()}")
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            when (activeTab) {
                SoilScreenTab.CALCULATOR -> {
                    // Method Selector Pill
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Row(modifier = Modifier.padding(4.dp)) {
                                SoilInputMethod.values().forEach { method ->
                                    val isSelected = inputMethod == method
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                        modifier = Modifier
                                            .clickable { inputMethod = method }
                                            .testTag("method_${method.name.lowercase()}")
                                    ) {
                                        Text(
                                            text = method.title,
                                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Input controls
                    if (inputMethod == SoilInputMethod.PERCENTAGE_SLIDERS) {
                        PercentageSlidersCard(
                            sand = sandPercent,
                            silt = siltPercent,
                            clay = clayPercent,
                            onSandChanged = { sandPercent = it },
                            onSiltChanged = { siltPercent = it },
                            onClayChanged = { clayPercent = it },
                            onAutoBalance = {
                                val total = sandPercent + siltPercent + clayPercent
                                if (total > 0) {
                                    sandPercent = Math.round((sandPercent / total * 100.0) * 10.0) / 10.0
                                    siltPercent = Math.round((siltPercent / total * 100.0) * 10.0) / 10.0
                                    clayPercent = Math.round((100.0 - sandPercent - siltPercent) * 10.0) / 10.0
                                }
                            }
                        )
                    } else {
                        JarSedimentationCard(
                            sandMm = jarSandMm,
                            siltMm = jarSiltMm,
                            clayMm = jarClayMm,
                            onSandMmChanged = { jarSandMm = it },
                            onSiltMmChanged = { jarSiltMm = it },
                            onClayMmChanged = { jarClayMm = it },
                            onApply = { s, si, c ->
                                sandPercent = s
                                siltPercent = si
                                clayPercent = c
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Soil Triangle Canvas with Pointer
                    SoilTextureTriangleCanvas(
                        sand = sandPercent,
                        silt = siltPercent,
                        clay = clayPercent,
                        classifiedClass = classifiedClass,
                        onCoordinateChanged = { s, si, c ->
                            sandPercent = s
                            siltPercent = si
                            clayPercent = c
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Classification Result Highlight Banner
                    ClassifiedResultCard(
                        classifiedClass = classifiedClass,
                        onViewAdvisoryClick = { activeTab = SoilScreenTab.ADVISORY }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quick Soil Type Presets
                    Text(
                        text = "Common Regional Soil Presets:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth().testTag("regional_presets_row")
                    ) {
                        item {
                            PresetChip("Loam (42% S, 40% Si, 18% C)") {
                                sandPercent = 42.0; siltPercent = 40.0; clayPercent = 18.0
                            }
                        }
                        item {
                            PresetChip("Black Cotton Vertisol (15% S, 25% Si, 60% C)") {
                                sandPercent = 15.0; siltPercent = 25.0; clayPercent = 60.0
                            }
                        }
                        item {
                            PresetChip("Volcanic Ash Silt Loam (18% S, 67% Si, 15% C)") {
                                sandPercent = 18.0; siltPercent = 67.0; clayPercent = 15.0
                            }
                        }
                        item {
                            PresetChip("Kikuyu Red Clay Nitisol (30% S, 25% Si, 45% C)") {
                                sandPercent = 30.0; siltPercent = 25.0; clayPercent = 45.0
                            }
                        }
                        item {
                            PresetChip("Coastal Arenosol Sand (88% S, 7% Si, 5% C)") {
                                sandPercent = 88.0; siltPercent = 7.0; clayPercent = 5.0
                            }
                        }
                    }
                }

                SoilScreenTab.ADVISORY -> {
                    // Top banner with current soil class
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = classifiedClass.chartColor.copy(alpha = 0.15f),
                        border = BorderStroke(1.5.dp, classifiedClass.chartColor),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(classifiedClass.chartColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = classifiedClass.code,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Agronomic Advisory for ${classifiedClass.displayName}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = classifiedClass.kenyanSoilEquivalent,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 1. Crop Suitability Section
                    CropSuitabilityCard(suitability = soilProfile.cropSuitability)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 2. Fertility Management Section
                    FertilityAdvisoryCard(advisory = soilProfile.fertilityAdvisory)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 3. Water Application & Irrigation Management
                    WaterAdvisoryCard(advisory = soilProfile.waterAdvisory)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 4. Routine Field Activities
                    RoutineActivitiesCard(activities = soilProfile.routineActivities)

                    Spacer(modifier = Modifier.height(20.dp))

                    // Button to launch rotation designer
                    Button(
                        onClick = { activeTab = SoilScreenTab.ROTATION },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_switch_to_rotation"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Loop, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Design Crop Rotation Pattern for this Soil")
                    }
                }

                SoilScreenTab.ROTATION -> {
                    // Interactive Crop Rotation Pattern Designer
                    CropRotationDesignerComponent()
                }
            }
        }
    }
}

/**
 * Sliders card for Sand %, Silt %, Clay %.
 */
@Composable
private fun PercentageSlidersCard(
    sand: Double,
    silt: Double,
    clay: Double,
    onSandChanged: (Double) -> Unit,
    onSiltChanged: (Double) -> Unit,
    onClayChanged: (Double) -> Unit,
    onAutoBalance: () -> Unit
) {
    val total = (sand + silt + clay).roundToInt()
    val isBalanced = total in 99..101

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("percentage_sliders_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Soil Particle Fractions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isBalanced) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                    border = BorderStroke(1.dp, if (isBalanced) Color(0xFF4CAF50) else Color(0xFFE57373))
                ) {
                    Text(
                        text = "Total: $total% ${if (isBalanced) "✓" else "≠ 100%"}",
                        color = if (isBalanced) Color(0xFF2E7D32) else Color(0xFFC62828),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Sand Slider
            ParticleSlider(
                label = "Sand (0.05 - 2.0 mm)",
                value = sand,
                color = Color(0xFFE9C46A),
                onValueChange = onSandChanged
            )

            // Silt Slider
            ParticleSlider(
                label = "Silt (0.002 - 0.05 mm)",
                value = silt,
                color = Color(0xFF45B69C),
                onValueChange = onSiltChanged
            )

            // Clay Slider
            ParticleSlider(
                label = "Clay (< 0.002 mm)",
                value = clay,
                color = Color(0xFFD9534F),
                onValueChange = onClayChanged
            )

            if (!isBalanced) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = onAutoBalance,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("btn_auto_balance"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Auto-Balance to 100%", fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
private fun ParticleSlider(
    label: String,
    value: Double,
    color: Color,
    onValueChange: (Double) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${value.toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(Math.round(it * 10.0) / 10.0) },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth().testTag("slider_${label.split(" ").first().lowercase()}")
        )
    }
}

/**
 * Sedimentation Jar Test Card (Field measurement in mm).
 */
@Composable
private fun JarSedimentationCard(
    sandMm: String,
    siltMm: String,
    clayMm: String,
    onSandMmChanged: (String) -> Unit,
    onSiltMmChanged: (String) -> Unit,
    onClayMmChanged: (String) -> Unit,
    onApply: (Double, Double, Double) -> Unit
) {
    val sMm = sandMm.toDoubleOrNull() ?: 0.0
    val siMm = siltMm.toDoubleOrNull() ?: 0.0
    val cMm = clayMm.toDoubleOrNull() ?: 0.0
    val totalMm = sMm + siMm + cMm

    val calcSand = if (totalMm > 0) Math.round((sMm / totalMm * 100.0) * 10.0) / 10.0 else 0.0
    val calcSilt = if (totalMm > 0) Math.round((siMm / totalMm * 100.0) * 10.0) / 10.0 else 0.0
    val calcClay = if (totalMm > 0) Math.round((cMm / totalMm * 100.0) * 10.0) / 10.0 else 0.0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("jar_sedimentation_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Science,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Sedimentation Jar Test (Field Method)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Measure settled layer heights in mm inside a glass jar.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = sandMm,
                    onValueChange = onSandMmChanged,
                    label = { Text("Sand (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f).testTag("input_sand_mm"),
                    singleLine = true
                )
                OutlinedTextField(
                    value = siltMm,
                    onValueChange = onSiltMmChanged,
                    label = { Text("Silt (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f).testTag("input_silt_mm"),
                    singleLine = true
                )
                OutlinedTextField(
                    value = clayMm,
                    onValueChange = onClayMmChanged,
                    label = { Text("Clay (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f).testTag("input_clay_mm"),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Calculated fractions summary
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total: ${totalMm.toInt()} mm", style = MaterialTheme.typography.labelMedium)
                    Text("Sand: $calcSand%", style = MaterialTheme.typography.labelMedium, color = Color(0xFFC9A227))
                    Text("Silt: $calcSilt%", style = MaterialTheme.typography.labelMedium, color = Color(0xFF45B69C))
                    Text("Clay: $calcClay%", style = MaterialTheme.typography.labelMedium, color = Color(0xFFD9534F))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onApply(calcSand, calcSilt, calcClay) },
                enabled = totalMm > 0,
                modifier = Modifier.fillMaxWidth().testTag("btn_apply_jar_test"),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Apply Derived Percentages to Triangle")
            }
        }
    }
}

/**
 * Result highlight banner of the classified soil.
 */
@Composable
private fun ClassifiedResultCard(
    classifiedClass: SoilTextureClass,
    onViewAdvisoryClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("classified_result_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = classifiedClass.chartColor.copy(alpha = 0.12f)
        ),
        border = BorderStroke(1.5.dp, classifiedClass.chartColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(classifiedClass.chartColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = classifiedClass.code,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Classified: ${classifiedClass.displayName}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = classifiedClass.kenyanSoilEquivalent,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = classifiedClass.generalDescription,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AttributePill("CEC: ${classifiedClass.cecRating.split("(").first().trim()}", Icons.Default.Eco)
                AttributePill("Infiltration: ${classifiedClass.infiltrationMmPerHour} mm/hr", Icons.Default.Speed)
                AttributePill("AWC: ${classifiedClass.availableWaterCapacityMmPerM} mm/m", Icons.Default.Opacity)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onViewAdvisoryClick,
                modifier = Modifier.fillMaxWidth().testTag("btn_view_full_advisory"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = classifiedClass.chartColor
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("View Tailored Crop, Fertility & Water Advice", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun AttributePill(text: String, icon: ImageVector) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(13.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text, style = MaterialTheme.typography.labelSmall, fontSize = 11.sp)
        }
    }
}

@Composable
private fun PresetChip(text: String, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            fontWeight = FontWeight.Medium
        )
    }
}

/**
 * Advisory Tab Components
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CropSuitabilityCard(suitability: com.example.model.soil.SoilCropSuitability) {
    Card(
        modifier = Modifier.fillMaxWidth().testTag("crop_suitability_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Agriculture, contentDescription = null, tint = Color(0xFF2E7D32))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Crop Suitability Evaluation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Highly Suitable
            Text("★ Highly Recommended Crops:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                suitability.highlySuitableCrops.forEach { crop ->
                    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFE8F5E9), border = BorderStroke(1.dp, Color(0xFF81C784))) {
                        Text(text = "✓ $crop", color = Color(0xFF1B5E20), style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Moderate with amendments
            Text("▲ Moderate (Requires Amendments / Ridges):", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                suitability.moderateWithAmendments.forEach { crop ->
                    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFFFF3E0), border = BorderStroke(1.dp, Color(0xFFFFB74D))) {
                        Text(text = "▲ $crop", color = Color(0xFFE65100), style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Unsuitable / High Risk
            Text("✕ Unsuitable or High Agronomic Risk:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFFD32F2F))
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                suitability.unsuitableOrHighRisk.forEach { crop ->
                    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFFFEBEE), border = BorderStroke(1.dp, Color(0xFFE57373))) {
                        Text(text = "✕ $crop", color = Color(0xFFB71C1C), style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Agronomic Justification:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = suitability.agronomicJustification, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
        }
    }
}

@Composable
private fun FertilityAdvisoryCard(advisory: com.example.model.soil.SoilFertilityAdvisory) {
    Card(
        modifier = Modifier.fillMaxWidth().testTag("fertility_advisory_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Science, contentDescription = null, tint = Color(0xFFF57C00))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Soil Fertility & Dosing Management", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            AdvisoryPoint("Cation Exchange Capacity (CEC)", advisory.cecExplanation)
            AdvisoryPoint("Basal Fertilizer Application", advisory.basalFertilizerStrategy)
            AdvisoryPoint("Topdressing Timing & Split-Dosing", advisory.topdressingSchedule)
            AdvisoryPoint("Organic Manure Requirement", "${advisory.organicManureTonsPerHa} metric tonnes/ha of well-cured compost or farmyard manure.")
            AdvisoryPoint("Phosphorus Fixation Risk", advisory.phosphorusFixationRisk)
            AdvisoryPoint("Liming & pH Soil Conditioning", advisory.limingOrPhAdjustment)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Micronutrient Watchlist: ${advisory.micronutrientWatchlist.joinToString(", ")}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun WaterAdvisoryCard(advisory: com.example.model.soil.SoilWaterAdvisory) {
    Card(
        modifier = Modifier.fillMaxWidth().testTag("water_advisory_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.WaterDrop, contentDescription = null, tint = Color(0xFF0288D1))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Water Application & Irrigation Scheduling", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            AdvisoryPoint("Infiltration Rate", advisory.infiltrationRateText)
            AdvisoryPoint("Available Water Capacity (AWC)", advisory.availableWaterCapacityText)
            AdvisoryPoint("Recommended Irrigation Method", advisory.recommendedIrrigationSystem)
            AdvisoryPoint("Irrigation Frequency & Cycle", advisory.irrigationFrequencyAndDuration)
            AdvisoryPoint("Waterlogging Risk", advisory.waterloggingRisk)
            AdvisoryPoint("Drainage Infrastructure", advisory.drainageInfrastructureNeeded)
        }
    }
}

@Composable
private fun RoutineActivitiesCard(activities: com.example.model.soil.SoilRoutineActivity) {
    Card(
        modifier = Modifier.fillMaxWidth().testTag("routine_activities_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Construction, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Routine Soil Management Activities", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            AdvisoryPoint("Tillage Moisture Window", activities.tillageMoistureWindow)
            AdvisoryPoint("Mulching & Surface Protection", activities.mulchingRecommendation)
            AdvisoryPoint("Compaction & Hardpan Prevention", activities.compactionPrevention)
            AdvisoryPoint("Routine Laboratory Soil Testing", activities.routineSoilTestingFrequency)
            AdvisoryPoint("Erosion Control Measures", activities.erosionControlMeasures)
        }
    }
}

@Composable
private fun AdvisoryPoint(title: String, description: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Text(text = description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 17.sp)
        Spacer(modifier = Modifier.height(4.dp))
    }
}
