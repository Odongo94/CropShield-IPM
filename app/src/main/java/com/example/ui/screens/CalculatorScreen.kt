package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgronomyCatalog
import com.example.data.ThreatCatalog
import com.example.model.ChemicalRecommendation
import com.example.model.CropFertilizerPlan
import com.example.model.CropThreat
import com.example.model.FertilizerPhase
import com.example.model.FertilizerProduct
import com.example.model.KnapsackFertilizerResult
import com.example.ui.components.ChemicalFormulationType
import com.example.ui.components.KnapsackCalculatorComponent

enum class CalculatorMode(val title: String) {
    FERTILIZER("Knapsack Fertilizer"),
    PESTICIDE("Pesticide Dilution"),
    SOIL_CLASSIFIER("Soil Classification")
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    farmSizeHectares: Double = 1.0,
    selectedCropPlan: CropFertilizerPlan = AgronomyCatalog.cropFertilizerPlans.first(),
    selectedKnapsackLiters: Double = 16.0,
    selectedBasalProduct: FertilizerProduct? = null,
    selectedTopdressProduct: FertilizerProduct? = null,
    selectedFoliarProduct: FertilizerProduct? = null,
    knapsackResult: KnapsackFertilizerResult = AgronomyCatalog.calculateKnapsackFertilizer(
        cropName = selectedCropPlan.cropName,
        farmSizeHectares = farmSizeHectares,
        knapsackTankCapacityLiters = selectedKnapsackLiters
    ),
    onHectaresChanged: (Double) -> Unit = {},
    onCropPlanSelected: (CropFertilizerPlan) -> Unit = {},
    onKnapsackLitersSelected: (Double) -> Unit = {},
    onBasalProductSelected: (FertilizerProduct?) -> Unit = {},
    onTopdressProductSelected: (FertilizerProduct?) -> Unit = {},
    onFoliarProductSelected: (FertilizerProduct?) -> Unit = {},
    initialThreat: CropThreat? = null,
    initialChemical: ChemicalRecommendation? = null,
    onNavigateToFieldMap: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var activeMode by remember { mutableStateOf(CalculatorMode.FERTILIZER) }

    Column(modifier = modifier.fillMaxSize().testTag("calculator_screen")) {
        // Tab Row Switcher
        PrimaryTabRow(
            selectedTabIndex = activeMode.ordinal,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth().testTag("calculator_tab_row")
        ) {
            CalculatorMode.values().forEach { mode ->
                val isSelected = activeMode == mode
                Tab(
                    selected = isSelected,
                    onClick = { activeMode = mode },
                    text = {
                        Text(
                            text = mode.title,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = when (mode) {
                                CalculatorMode.FERTILIZER -> Icons.Default.Agriculture
                                CalculatorMode.PESTICIDE -> Icons.Default.Medication
                                CalculatorMode.SOIL_CLASSIFIER -> Icons.Default.Terrain
                            },
                            contentDescription = mode.title,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier.testTag("tab_${mode.name.lowercase()}")
                )
            }
        }

        when (activeMode) {
            CalculatorMode.FERTILIZER -> {
                KnapsackFertilizerCalculatorContent(
                    farmSizeHectares = farmSizeHectares,
                    selectedCropPlan = selectedCropPlan,
                    selectedKnapsackLiters = selectedKnapsackLiters,
                    result = knapsackResult,
                    onHectaresChanged = onHectaresChanged,
                    onCropPlanSelected = onCropPlanSelected,
                    onKnapsackLitersSelected = onKnapsackLitersSelected,
                    onBasalProductSelected = onBasalProductSelected,
                    onTopdressProductSelected = onTopdressProductSelected,
                    onFoliarProductSelected = onFoliarProductSelected,
                    onNavigateToFieldMap = onNavigateToFieldMap
                )
            }

            CalculatorMode.PESTICIDE -> {
                PesticideCalculatorContent(
                    initialThreat = initialThreat,
                    initialChemical = initialChemical
                )
            }

            CalculatorMode.SOIL_CLASSIFIER -> {
                com.example.ui.screens.soil.SoilClassificationScreen()
            }
        }
    }
}

// =============================================================================
// KNAPSACK FERTILIZER CALCULATOR CONTENT
// =============================================================================
@Composable
private fun KnapsackFertilizerCalculatorContent(
    farmSizeHectares: Double,
    selectedCropPlan: CropFertilizerPlan,
    selectedKnapsackLiters: Double,
    result: KnapsackFertilizerResult,
    onHectaresChanged: (Double) -> Unit,
    onCropPlanSelected: (CropFertilizerPlan) -> Unit,
    onKnapsackLitersSelected: (Double) -> Unit,
    onBasalProductSelected: (FertilizerProduct?) -> Unit,
    onTopdressProductSelected: (FertilizerProduct?) -> Unit,
    onFoliarProductSelected: (FertilizerProduct?) -> Unit,
    onNavigateToFieldMap: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 32.dp)
    ) {
        // Hero Header Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Agriculture,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Knapsack Fertilizer Calculator",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Calculate farm requirement in hectares with Kenyan brands, rations & knapsack refills",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 1. FARM SIZE IN HECTARES
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "1. Farm Size in Hectares:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            OutlinedButton(
                onClick = onNavigateToFieldMap,
                modifier = Modifier.testTag("action_calc_open_field_map")
            ) {
                Icon(imageVector = Icons.Default.Terrain, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Map Field (Offline)", style = MaterialTheme.typography.labelSmall)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${String.format("%.2f", farmSizeHectares)} Hectares",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "≈ ${String.format("%.2f", result.farmSizeAcres)} Acres (${String.format("%.0f", farmSizeHectares * 10000)} m²)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(
                        onClick = { if (farmSizeHectares > 0.5) onHectaresChanged(farmSizeHectares - 0.5) },
                        modifier = Modifier.testTag("btn_minus_ha")
                    ) {
                        Text("- 0.5")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onHectaresChanged(farmSizeHectares + 0.5) },
                        modifier = Modifier.testTag("btn_plus_ha")
                    ) {
                        Text("+ 0.5")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Quick Preset Chips for Hectares
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(0.25, 0.5, 1.0, 2.0, 3.0, 5.0, 10.0).forEach { ha ->
                val isSelected = kotlin.math.abs(farmSizeHectares - ha) < 0.05
                FilterChip(
                    selected = isSelected,
                    onClick = { onHectaresChanged(ha) },
                    label = { Text("$ha ha") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.testTag("chip_ha_$ha")
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. CROP SELECTOR
        Text(
            text = "2. Select Crop:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(AgronomyCatalog.cropFertilizerPlans) { plan ->
                val isSelected = plan.cropName == selectedCropPlan.cropName
                OutlinedCard(
                    onClick = { onCropPlanSelected(plan) },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                    ),
                    modifier = Modifier.testTag("crop_chip_${plan.cropName.take(6).lowercase()}")
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = plan.cropName,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = plan.cropCategory.displayName,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. SPRAY TANK CAPACITY SELECTION (Knapsack, Motorized, Tractor Boom 500L - 2000L)
        Text(
            text = "3. Spray Tank Capacity (Knapsacks, Motorized & 500L - 2000L Booms):",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(16.0, 20.0, 100.0, 200.0, 500.0, 800.0, 1000.0, 1500.0, 2000.0).forEach { liters ->
                val isSelected = kotlin.math.abs(selectedKnapsackLiters - liters) < 0.1
                FilterChip(
                    selected = isSelected,
                    onClick = { onKnapsackLitersSelected(liters) },
                    label = {
                        Text(
                            text = when (liters) {
                                16.0 -> "16L Knapsack"
                                20.0 -> "20L Knapsack"
                                100.0 -> "100L Power Tank"
                                200.0 -> "200L Drum Sprayer"
                                500.0 -> "500L Tractor Boom"
                                800.0 -> "800L Boom Sprayer"
                                1000.0 -> "1,000L Central Tank"
                                1500.0 -> "1,500L Farm Tanker"
                                2000.0 -> "2,000L Commercial Tank"
                                else -> "${liters.toInt()}L"
                            }
                        )
                    },
                    leadingIcon = {
                        Icon(Icons.Default.WaterDrop, contentDescription = null, modifier = Modifier.size(16.dp))
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.testTag("tank_size_${liters.toInt()}l")
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. REGIONAL KENYAN FERTILIZER BRANDS & FORMULATIONS
        Text(
            text = "4. Kenyan Brands & Fertilizer Rations:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))

        // Basal Product Picker
        BrandPicker(
            label = "Basal Starter Fertilizer:",
            currentProduct = result.selectedBasalProduct,
            products = AgronomyCatalog.fertilizerProducts.filter { it.phase == FertilizerPhase.BASAL_PLANTING },
            onSelect = onBasalProductSelected
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Top-Dress Product Picker
        BrandPicker(
            label = "Top-Dressing Fertilizer:",
            currentProduct = result.selectedTopdressProduct,
            products = AgronomyCatalog.fertilizerProducts.filter { it.phase == FertilizerPhase.TOP_DRESSING_1 },
            onSelect = onTopdressProductSelected
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Foliar Knapsack Product Picker
        BrandPicker(
            label = "Knapsack Foliar & Soluble Booster:",
            currentProduct = result.selectedFoliarProduct,
            products = AgronomyCatalog.fertilizerProducts.filter { it.phase == FertilizerPhase.FOLIAR_FERTIGATION },
            onSelect = onFoliarProductSelected
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 5. KNAPSACK & BULK FERTILIZER RESULTS DASHBOARD
        Text(
            text = "5. Fertilizer & Knapsack Spray Requirement:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth().testTag("knapsack_result_card")
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${result.cropName} (${String.format("%.2f", result.farmSizeHectares)} ha)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Knapsack Tank: ${result.knapsackTankCapacityLiters.toInt()}L • Spray Vol: ${result.sprayVolumeLitersPerHa.toInt()} L/ha",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // KNAPSACK APPLICATION SUMMARY CARD
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE)),
                    border = BorderStroke(1.dp, Color(0xFF81D4FA)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.WaterDrop,
                                contentDescription = null,
                                tint = Color(0xFF0277BD),
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Knapsack Foliar Application Plan",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF01579B)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Total Knapsack Refills:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF0277BD)
                                )
                                Text(
                                    text = "${String.format("%.1f", result.totalKnapsackTanks)} Tanks",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF01579B)
                                )
                                Text(
                                    text = "Total Water: ${String.format("%.0f", result.totalWaterLitersNeeded)} Liters",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF0288D1)
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Dose Per ${result.knapsackTankCapacityLiters.toInt()}L Tank:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF0277BD)
                                )
                                Text(
                                    text = "${String.format("%.0f", result.foliarDosePerTankGrams)} grams",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF2E7D32)
                                )
                                Text(
                                    text = "Total Foliar: ${String.format("%.1f", result.totalFoliarFertilizerKg)} kg",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF388E3C)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Foliar Product: ${result.selectedFoliarProduct.name} (${result.selectedFoliarProduct.npkRatio})",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF01579B)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // BULK SOIL FERTILIZER (BASAL & TOPDRESS)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Basal
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                        border = BorderStroke(1.dp, Color(0xFFA5D6A7)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Basal Starter (${result.selectedBasalProduct.name})",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${String.format("%.1f", result.basal50kgBags)} Bags",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF1B5E20)
                            )
                            Text(
                                text = "Total: ${String.format("%.0f", result.basalKgTotal)} kg",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }

                    // Topdress
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                        border = BorderStroke(1.dp, Color(0xFFFFCC80)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Top-Dress (${result.selectedTopdressProduct.name})",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE65100),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${String.format("%.1f", result.topdress50kgBags)} Bags",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFBF360C)
                            )
                            Text(
                                text = "Total: ${String.format("%.0f", result.topdressKgTotal)} kg",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                color = Color(0xFFE65100)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Pure Elemental Nutrients
                Text(
                    text = "Total Pure Elemental Nutrients Delivered:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NutrientMiniBadge("Nitrogen (N)", "${String.format("%.1f", result.totalNitrogenKg)} kg", Color(0xFF388E3C))
                    NutrientMiniBadge("Phosphate (P₂O₅)", "${String.format("%.1f", result.totalPhosphateKg)} kg", Color(0xFFE65100))
                    NutrientMiniBadge("Potassium (K₂O)", "${String.format("%.1f", result.totalPotassiumKg)} kg", Color(0xFF6A1B9A))
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Knapsack Calibration Advice
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Knapsack Calibration & Spray Technique:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = result.knapsackCalibrationAdvice,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Soil Acidity Alert
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color(0xFFF57F17),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Regional Soil Acidity Guidance:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE65100)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = result.soilAcidityAdvice,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF5D4037)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BrandPicker(
    label: String,
    currentProduct: FertilizerProduct,
    products: List<FertilizerProduct>,
    onSelect: (FertilizerProduct?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedCard(
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${currentProduct.brand} • ${currentProduct.name}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "NPK: ${currentProduct.npkRatio}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand"
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = currentProduct.decisionRule,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                products.forEach { prod ->
                    val isCurrent = prod.id == currentProduct.id
                    Card(
                        onClick = {
                            onSelect(prod)
                            expanded = false
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCurrent) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        ),
                        border = if (isCurrent) BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary) else BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${prod.brand} - ${prod.name}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = prod.priceRangeKes,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Text(
                                text = "NPK: ${prod.npkRatio}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NutrientMiniBadge(title: String, amount: String, color: Color) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        modifier = Modifier.width(105.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp,
                color = color,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
        }
    }
}

// =============================================================================
// PESTICIDE / CHEMICAL MIX CONTENT
// =============================================================================
@Composable
private fun PesticideCalculatorContent(
    initialThreat: CropThreat? = null,
    initialChemical: ChemicalRecommendation? = null
) {
    val allChemicals = remember {
        ThreatCatalog.allThreats.flatMap { threat ->
            threat.ipmPlan.chemicalControls.map { chem -> threat to chem }
        }
    }

    var selectedIndex by remember {
        val found = if (initialChemical != null) {
            allChemicals.indexOfFirst { it.second.activeIngredient == initialChemical.activeIngredient }
        } else -1
        mutableIntStateOf(if (found >= 0) found else 0)
    }

    val selectedPair = allChemicals.getOrNull(selectedIndex) ?: allChemicals.first()
    val threat = selectedPair.first
    val chemical = selectedPair.second

    val isLiquid = chemical.dosagePerKnapsack.contains("ml", ignoreCase = true)
    val parsedConcentration = if (isLiquid) {
        if (chemical.dosagePerKnapsack.contains("5ml")) 0.35 else 0.5
    } else {
        if (chemical.dosagePerKnapsack.contains("50g")) 3.1 else 2.5
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 32.dp)
    ) {
        // Chemical Dilution Header
        Text(
            text = "Pesticide Dilution & Tank Refills",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Calibrate active ingredients, knapsack volume, and safety pre-harvest intervals",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Pre-fill From Catalog Chemical
        Text(
            text = "Pre-fill From Catalog Chemical (Optional):",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            allChemicals.forEachIndexed { index, (t, c) ->
                val isSelected = selectedIndex == index
                Card(
                    onClick = { selectedIndex = index },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    ),
                    border = if (isSelected) BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary) else null,
                    modifier = Modifier.width(220.dp).testTag("chemical_chip_$index")
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = c.activeIngredient,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "For ${t.name}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = c.dosagePerKnapsack,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selected chemical strip
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Selected Active: ${chemical.activeIngredient}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Standard Label Rate: ${chemical.dosagePerKnapsack} • PHI: ${chemical.phiDays} days",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // KNAPSACK CALCULATOR COMPONENT
        KnapsackCalculatorComponent(
            initialTankLiters = 16.0,
            initialConcentration = parsedConcentration,
            initialFormulationType = if (isLiquid) ChemicalFormulationType.LIQUID else ChemicalFormulationType.POWDER,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pre-Harvest Interval (PHI) reminder
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.HourglassBottom,
                    contentDescription = null,
                    tint = Color(0xFFC62828),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Pre-Harvest Interval (PHI) Caution:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC62828)
                    )
                    Text(
                        text = "For ${chemical.activeIngredient}, observe at least ${chemical.phiDays} days before harvesting for human consumption.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB71C1C)
                    )
                }
            }
        }
    }
}
