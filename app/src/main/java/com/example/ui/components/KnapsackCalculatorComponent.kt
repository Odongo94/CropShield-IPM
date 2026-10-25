package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

enum class ChemicalFormulationType {
    LIQUID, // ml / Liter
    POWDER  // g / Liter
}

enum class AreaUnitType(val displayName: String, val toAcresMultiplier: Double) {
    ACRES("Acres", 1.0),
    HECTARES("Hectares", 2.47105),
    SQUARE_METERS("m²", 0.000247105)
}

enum class CanopySprayVolume(val label: String, val litersPerAcre: Double) {
    LIGHT("Young / Sparse", 75.0),
    STANDARD("Normal Canopy", 110.0),
    DENSE("Dense / Tall", 160.0)
}

@Composable
fun KnapsackCalculatorComponent(
    modifier: Modifier = Modifier,
    initialTankLiters: Double = 16.0,
    initialConcentration: Double = 2.5,
    initialFormulationType: ChemicalFormulationType = ChemicalFormulationType.LIQUID,
    initialAreaSize: Double = 1.0,
    initialAreaUnit: AreaUnitType = AreaUnitType.ACRES,
    onCalculationChanged: ((amountPerTank: Double, totalChemical: Double, totalTanks: Double) -> Unit)? = null
) {
    // 1. Knapsack capacity state
    var tankCapacityInput by remember { mutableStateOf(initialTankLiters.toString().removeSuffix(".0")) }
    val tankCapacityLiters by remember {
        derivedStateOf { tankCapacityInput.toDoubleOrNull()?.coerceIn(1.0, 5000.0) ?: 16.0 }
    }

    // 2. Chemical concentration state (ml/L or g/L)
    var formulationType by remember { mutableStateOf(initialFormulationType) }
    var concentrationInput by remember { mutableStateOf(initialConcentration.toString()) }
    val concentrationVal by remember {
        derivedStateOf { concentrationInput.toDoubleOrNull()?.coerceIn(0.01, 100.0) ?: 2.5 }
    }

    // 3. Area size & unit state
    var areaUnit by remember { mutableStateOf(initialAreaUnit) }
    var areaInput by remember { mutableStateOf(initialAreaSize.toString().removeSuffix(".0")) }
    val areaVal by remember {
        derivedStateOf { areaInput.toDoubleOrNull()?.coerceIn(0.01, 10000.0) ?: 1.0 }
    }

    // Canopy volume density
    var canopyVolume by remember { mutableStateOf(CanopySprayVolume.STANDARD) }

    // Quick concentration presets
    val quickPresets = remember(formulationType) {
        if (formulationType == ChemicalFormulationType.LIQUID) {
            listOf(0.5, 1.0, 1.5, 2.0, 2.5, 3.0) // ml/L
        } else {
            listOf(1.0, 2.0, 2.5, 3.0, 4.0, 5.0) // g/L
        }
    }

    // Mathematical Calculations
    // A. Amount per single tank: Capacity (L) * Concentration (ml/L or g/L)
    val amountPerTank = remember(tankCapacityLiters, concentrationVal) {
        tankCapacityLiters * concentrationVal
    }

    // B. Total area in acres
    val totalAcres = remember(areaVal, areaUnit) {
        areaVal * areaUnit.toAcresMultiplier
    }

    // C. Total spray water volume required for entire area
    val totalWaterLiters = remember(totalAcres, canopyVolume) {
        (totalAcres * canopyVolume.litersPerAcre).coerceAtLeast(1.0)
    }

    // D. Total knapsack tank refills required
    val totalTanksNeeded = remember(totalWaterLiters, tankCapacityLiters) {
        totalWaterLiters / tankCapacityLiters
    }

    // E. Total pesticide required for entire plot
    val totalChemicalRequired = remember(totalWaterLiters, concentrationVal) {
        totalWaterLiters * concentrationVal
    }

    val chemicalUnitLabel = if (formulationType == ChemicalFormulationType.LIQUID) "ml" else "grams"
    val concentrationUnitLabel = if (formulationType == ChemicalFormulationType.LIQUID) "ml/L" else "g/L"

    // Callback if provided
    remember(amountPerTank, totalChemicalRequired, totalTanksNeeded) {
        onCalculationChanged?.invoke(amountPerTank, totalChemicalRequired, totalTanksNeeded)
        true
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("knapsack_calculator_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = "Knapsack calculator",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = if (tankCapacityLiters >= 500.0) "Commercial Tank & Boom Sprayer (${tankCapacityLiters.toInt()}L)" else "Knapsack & Commercial Tank Sprayer (16L – 2000L)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Calculates exact chemical ml, grams, or kg for 16L knapsacks up to 2000L central farm tanks",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SECTION 1: Knapsack / Tank Capacity
            Text(
                text = "1. Tank Capacity (16L Knapsack to 2000L Commercial/Tractor Tank):",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(16.0, 20.0, 100.0, 200.0, 500.0, 800.0, 1000.0, 1500.0, 2000.0).forEach { size ->
                    val isSelected = kotlin.math.abs(tankCapacityLiters - size) < 0.1
                    val bgColor by animateColorAsState(
                        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                        label = "tank_btn"
                    )
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = bgColor,
                        modifier = Modifier
                            .clickable { tankCapacityInput = size.toInt().toString() }
                            .testTag("tank_preset_${size.toInt()}")
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (size >= 500.0) "${size.toInt()}L (Boom/Tank)" else "${size.toInt()}L",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = tankCapacityInput,
                onValueChange = { tankCapacityInput = it },
                label = { Text("Custom Tank Size (Liters)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("knapsack_capacity_input")
            )

            Spacer(modifier = Modifier.height(16.dp))

            // SECTION 2: Recommended Chemical Concentration
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "2. Chemical Concentration ($concentrationUnitLabel):",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                // Toggle: Liquid (ml/L) vs Powder (g/L)
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(2.dp)
                ) {
                    ConcentrationTypePill(
                        label = "Liquid (ml/L)",
                        icon = Icons.Default.Opacity,
                        isSelected = formulationType == ChemicalFormulationType.LIQUID,
                        testTag = "unit_toggle_liquid",
                        onClick = { formulationType = ChemicalFormulationType.LIQUID }
                    )
                    ConcentrationTypePill(
                        label = "Powder (g/L)",
                        icon = Icons.Default.Scale,
                        isSelected = formulationType == ChemicalFormulationType.POWDER,
                        testTag = "unit_toggle_powder",
                        onClick = { formulationType = ChemicalFormulationType.POWDER }
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Quick Concentration Presets
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                quickPresets.forEach { preset ->
                    val isSelected = concentrationVal == preset
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .clickable { concentrationInput = preset.toString() }
                            .testTag("preset_conc_$preset")
                    ) {
                        Text(
                            text = "$preset $concentrationUnitLabel",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = concentrationInput,
                onValueChange = { concentrationInput = it },
                label = { Text("Recommended Rate ($concentrationUnitLabel)") },
                placeholder = { Text("e.g. 2.5") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("chemical_concentration_input")
            )

            Spacer(modifier = Modifier.height(16.dp))

            // SECTION 3: Area Size & Unit
            Text(
                text = "3. Target Field Area:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AreaUnitType.values().forEach { unit ->
                    val isSelected = areaUnit == unit
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { areaUnit = unit }
                            .testTag("area_unit_${unit.name.lowercase(Locale.ROOT)}")
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = unit.displayName,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = areaInput,
                onValueChange = { areaInput = it },
                label = { Text("Area Size (${areaUnit.displayName})") },
                placeholder = { Text("e.g. 1.0") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("area_size_input")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Canopy Density Volume Switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Canopy Growth Stage:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    CanopySprayVolume.values().forEach { stage ->
                        val isSelected = canopyVolume == stage
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable { canopyVolume = stage }
                        ) {
                            Text(
                                text = stage.label,
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CALCULATION RESULTS CARD
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("calculator_result_container")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Knapsack Mixing Results",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Result 1: Dosage per single knapsack tank
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .testTag("calculate_result_per_tank"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.WaterDrop,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Dose Per ${"%.1f".format(Locale.US, tankCapacityLiters)}L Knapsack Tank:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "${"%.1f".format(Locale.US, amountPerTank)} $chemicalUnitLabel",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Result 2: Total tanks and total chemical for entire field
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Total Chemical Needed
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("calculate_result_total_chemical")
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Total Product:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                val formattedTotal = if (totalChemicalRequired >= 1000.0) {
                                    val inKgOrLiters = totalChemicalRequired / 1000.0
                                    val majorUnit = if (formulationType == ChemicalFormulationType.LIQUID) "L" else "kg"
                                    "${"%.2f".format(Locale.US, inKgOrLiters)} $majorUnit"
                                } else {
                                    "${"%.1f".format(Locale.US, totalChemicalRequired)} $chemicalUnitLabel"
                                }
                                Text(
                                    text = formattedTotal,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Text(
                                    text = "For $areaVal ${areaUnit.displayName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Total Tank Refills
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("calculate_result_total_tanks")
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Knapsack Refills:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "${"%.1f".format(Locale.US, totalTanksNeeded)} Tanks",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${"%.0f".format(Locale.US, totalWaterLiters)}L Total Water",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Step-by-Step Tank Mixing Instructions
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "How to Mix in Your Knapsack:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "1. Fill tank half-full with clean water (${"%.0f".format(Locale.US, tankCapacityLiters / 2)}L).",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "2. Add exactly ${"%.1f".format(Locale.US, amountPerTank)} $chemicalUnitLabel of product into a small bucket with water first to dissolve.",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "3. Pour into tank, stir, then top up with water to the ${"%.0f".format(Locale.US, tankCapacityLiters)}L mark.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Safety PPE Checklist
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.HealthAndSafety,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Always wear chemical gloves, eye goggles, boots, and face mask when measuring and spraying.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ConcentrationTypePill(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        label = "conc_pill_bg"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "conc_pill_txt"
    )

    Row(
        modifier = Modifier
            .testTag(testTag)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = contentColor
        )
    }
}
