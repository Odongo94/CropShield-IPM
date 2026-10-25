package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.ZoomIn
import com.example.data.science.WeedSoilScienceCatalog
import com.example.ui.screens.science.AgriculturalSciencesView
import com.example.ui.screens.science.WeedSoilDiagnosticianView
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.AgronomyCatalog
import com.example.data.weather.CompleteWeatherResult
import com.example.model.CropFertilizerPlan
import com.example.model.FertilizerCalculationResult
import com.example.model.FertilizerProduct
import com.example.model.HerbicideTiming
import com.example.model.KenyaAgriculturalCounty
import com.example.model.RegisteredHerbicide
import com.example.model.SprayStatus
import com.example.model.WeedModel
import com.example.model.WeedType
import com.example.viewmodel.WeatherUiState

enum class AgronomySubTab(val title: String, val icon: ImageVector) {
    CROP_GUIDES("Crop Library", Icons.Default.MenuBook),
    WEED_SOIL("Weed & Soil", Icons.Default.Grass),
    SCIENCES("Agri-Science", Icons.Default.Science),
    FERTILIZER("Fertilizer Calc", Icons.Default.Agriculture),
    WEATHER("Farm Weather", Icons.Default.Cloud)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgronomyScreen(
    farmSizeHectares: Double,
    selectedCropPlan: CropFertilizerPlan,
    selectedBasalProduct: FertilizerProduct?,
    selectedTopdressProduct: FertilizerProduct?,
    fertilizerResult: FertilizerCalculationResult,
    weedList: List<WeedModel>,
    weedQuery: String,
    selectedWeedType: WeedType?,
    selectedCounty: KenyaAgriculturalCounty,
    weatherState: WeatherUiState,
    onHectaresChanged: (Double) -> Unit,
    onCropPlanSelected: (CropFertilizerPlan) -> Unit,
    onBasalProductSelected: (FertilizerProduct?) -> Unit,
    onTopdressProductSelected: (FertilizerProduct?) -> Unit,
    onWeedQueryChanged: (String) -> Unit,
    onWeedTypeSelected: (WeedType?) -> Unit,
    onCountySelected: (KenyaAgriculturalCounty) -> Unit,
    onAutoDetectLocation: () -> Unit,
    onNavigateToFieldMap: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var activeTab by remember { mutableStateOf(AgronomySubTab.WEED_SOIL) }
    var weedSubSection by remember { mutableStateOf(0) } // 0: Soil Diagnostician, 1: Weed Catalog

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("agronomy_screen")
    ) {
        // Sub-navigation tabs
        PrimaryTabRow(
            selectedTabIndex = activeTab.ordinal,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth().testTag("agronomy_tab_row")
        ) {
            AgronomySubTab.values().forEach { tab ->
                val selected = activeTab == tab
                Tab(
                    selected = selected,
                    onClick = { activeTab = tab },
                    text = {
                        Text(
                            text = tab.title,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
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

        when (activeTab) {
            AgronomySubTab.CROP_GUIDES -> {
                CropProductionLibraryView(
                    onCalculateFertilizer = { cropName ->
                        val matchedPlan = AgronomyCatalog.cropFertilizerPlans.find {
                            it.cropName.contains(cropName, ignoreCase = true) || cropName.contains(it.cropName, ignoreCase = true)
                        }
                        if (matchedPlan != null) {
                            onCropPlanSelected(matchedPlan)
                        }
                        activeTab = AgronomySubTab.FERTILIZER
                    }
                )
            }

            AgronomySubTab.WEED_SOIL -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    PrimaryTabRow(
                        selectedTabIndex = weedSubSection,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        contentColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth().testTag("weed_sub_section_tabs")
                    ) {
                        Tab(
                            selected = weedSubSection == 0,
                            onClick = { weedSubSection = 0 },
                            text = {
                                Text(
                                    "Soil Texture & Rotation",
                                    fontWeight = if (weedSubSection == 0) FontWeight.Bold else FontWeight.Medium,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        )
                        Tab(
                            selected = weedSubSection == 1,
                            onClick = { weedSubSection = 1 },
                            text = {
                                Text(
                                    "Soil Nutrition Diagnostician",
                                    fontWeight = if (weedSubSection == 1) FontWeight.Bold else FontWeight.Medium,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        )
                        Tab(
                            selected = weedSubSection == 2,
                            onClick = { weedSubSection = 2 },
                            text = {
                                Text(
                                    "Kenya Weed Catalog & Herbicides",
                                    fontWeight = if (weedSubSection == 2) FontWeight.Bold else FontWeight.Medium,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        )
                    }

                    when (weedSubSection) {
                        0 -> com.example.ui.screens.soil.SoilClassificationScreen()
                        1 -> WeedSoilDiagnosticianView()
                        else -> {
                            WeedManagementTab(
                                weeds = weedList,
                                searchQuery = weedQuery,
                                selectedType = selectedWeedType,
                                onSearchChanged = onWeedQueryChanged,
                                onTypeSelected = onWeedTypeSelected
                            )
                        }
                    }
                }
            }

            AgronomySubTab.SCIENCES -> {
                AgriculturalSciencesView()
            }

            AgronomySubTab.FERTILIZER -> {
                FertilizerCalculatorTab(
                    farmSizeHectares = farmSizeHectares,
                    selectedCropPlan = selectedCropPlan,
                    selectedBasalProduct = selectedBasalProduct,
                    selectedTopdressProduct = selectedTopdressProduct,
                    result = fertilizerResult,
                    onHectaresChanged = onHectaresChanged,
                    onCropPlanSelected = onCropPlanSelected,
                    onBasalProductSelected = onBasalProductSelected,
                    onTopdressProductSelected = onTopdressProductSelected,
                    onNavigateToFieldMap = onNavigateToFieldMap
                )
            }

            AgronomySubTab.WEATHER -> {
                WeatherForecastingTab(
                    selectedCounty = selectedCounty,
                    weatherState = weatherState,
                    onCountySelected = onCountySelected,
                    onAutoDetectLocation = onAutoDetectLocation
                )
            }
        }
    }
}

// =============================================================================
// TAB 1: FERTILIZER CALCULATOR (HECTARES & KENYAN BRANDS)
// =============================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FertilizerCalculatorTab(
    farmSizeHectares: Double,
    selectedCropPlan: CropFertilizerPlan,
    selectedBasalProduct: FertilizerProduct?,
    selectedTopdressProduct: FertilizerProduct?,
    result: FertilizerCalculationResult,
    onHectaresChanged: (Double) -> Unit,
    onCropPlanSelected: (CropFertilizerPlan) -> Unit,
    onBasalProductSelected: (FertilizerProduct?) -> Unit,
    onTopdressProductSelected: (FertilizerProduct?) -> Unit,
    onNavigateToFieldMap: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 32.dp)
    ) {
        // Section Header
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
                            text = "Kenyan Farm Fertilizer Calculator",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Precision calculation in hectares with Kenyan brands (Yara, MEA, Mavuno, Minjingu)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Offline Field Boundary & Agro-Zone Banner Card
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.35f)),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToFieldMap() }
                .testTag("banner_open_field_map")
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Terrain,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Map Field Boundaries & Agro-Zones (Offline)",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Walk or tap boundaries using lightweight offline tiles to calculate exact farm hectares.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open Map",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 1. FARM SIZE IN HECTARES
        Text(
            text = "1. Farm Size in Hectares (ha):",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))

        // Display hectares and conversion to acres
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
            text = "2. Select Target Crop:",
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
                    modifier = Modifier.testTag("crop_plan_${plan.cropName.take(6).lowercase()}")
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

        // 3. FERTILIZER BRAND RATION & DECISION SELECTOR
        Text(
            text = "3. Kenyan Fertilizer Brands & Formulations:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Compare NPK rations, secondary nutrients, and soil acidity impacts",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Basal Fertilizer Picker
        BrandSelectionSection(
            title = "A) Basal Planting Fertilizer:",
            currentProduct = result.selectedBasalProduct,
            availableProducts = AgronomyCatalog.fertilizerProducts.filter { it.phase == com.example.model.FertilizerPhase.BASAL_PLANTING },
            onSelect = onBasalProductSelected
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Topdress Fertilizer Picker
        BrandSelectionSection(
            title = "B) Top-Dressing Fertilizer:",
            currentProduct = result.selectedTopdressProduct,
            availableProducts = AgronomyCatalog.fertilizerProducts.filter { it.phase == com.example.model.FertilizerPhase.TOP_DRESSING_1 || it.phase == com.example.model.FertilizerPhase.FOLIAR_FERTIGATION },
            onSelect = onTopdressProductSelected
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 4. CALCULATION RESULTS DASHBOARD
        Text(
            text = "4. Fertilizer Requirement for Your Farm:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${result.cropName} (${String.format("%.2f", result.farmSizeHectares)} ha)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${String.format("%.1f", result.farmSizeAcres)} acres",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Basal requirement card
                RequirementRow(
                    label = "Basal Fertilizer (${result.selectedBasalProduct.name})",
                    kgTotal = result.basalKgTotal,
                    bags50kg = result.basal50kgBags,
                    ratio = result.selectedBasalProduct.npkRatio,
                    accentColor = Color(0xFF2E7D32)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Topdress requirement card
                RequirementRow(
                    label = "Top-Dress (${result.selectedTopdressProduct.name})",
                    kgTotal = result.topdressKgTotal,
                    bags50kg = result.topdress50kgBags,
                    ratio = result.selectedTopdressProduct.npkRatio,
                    accentColor = Color(0xFF1565C0)
                )

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Elemental Nutrients Delivered
                Text(
                    text = "Elemental Pure Nutrients Delivered:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NutrientBadge("Nitrogen (N)", "${String.format("%.1f", result.totalNitrogenKg)} kg", Color(0xFF388E3C))
                    NutrientBadge("Phosphate (P₂O₅)", "${String.format("%.1f", result.totalPhosphateKg)} kg", Color(0xFFE65100))
                    NutrientBadge("Potassium (K₂O)", "${String.format("%.1f", result.totalPotassiumKg)} kg", Color(0xFF6A1B9A))
                }

                Spacer(modifier = Modifier.height(16.dp))

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
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Soil Acidity & pH Context:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE65100)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = result.soilAcidityManagement,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF5D4037)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Split Application Schedule
                Text(
                    text = "Split Application Schedule:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                result.applicationSchedule.forEachIndexed { idx, step ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${idx + 1}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = step,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Brand Decision Rationale
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Agronomic Decision Rationale:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = result.brandDecisionRationale,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BrandSelectionSection(
    title: String,
    currentProduct: FertilizerProduct,
    availableProducts: List<FertilizerProduct>,
    onSelect: (FertilizerProduct?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
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
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${currentProduct.brand} • ${currentProduct.name}",
                            style = MaterialTheme.typography.titleSmall,
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
                        contentDescription = "Expand brands"
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = currentProduct.decisionRule,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Soil Acidity: ",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = currentProduct.soilAcidImpact,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (currentProduct.soilAcidImpact.contains("Acidifying", ignoreCase = true)) Color(0xFFC62828) else Color(0xFF2E7D32)
                    )
                }
            }
        }

        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                availableProducts.forEach { product ->
                    val isCurrent = product.id == currentProduct.id
                    Card(
                        onClick = {
                            onSelect(product)
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
                                    text = "${product.brand} - ${product.name}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = product.priceRangeKes,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Text(
                                text = "NPK: ${product.npkRatio}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = product.decisionRule,
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RequirementRow(
    label: String,
    kgTotal: Double,
    bags50kg: Double,
    ratio: String,
    accentColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = accentColor.copy(alpha = 0.08f)),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${String.format("%.1f", bags50kg)} Bags (50kg)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = accentColor
                    )
                    Text(
                        text = "Total: ${String.format("%.0f", kgTotal)} kg",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = ratio,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun NutrientBadge(title: String, amount: String, color: Color) {
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
// TAB 2: WEED MANAGEMENT & REGISTERED HERBICIDES OF KENYA
// =============================================================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WeedManagementTab(
    weeds: List<WeedModel>,
    searchQuery: String,
    selectedType: WeedType?,
    onSearchChanged: (String) -> Unit,
    onTypeSelected: (WeedType?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChanged,
            placeholder = { Text("Search weed (Kayongo, Mukengeria, Ndago, Blackjack...)") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchChanged("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear search")
                    }
                }
            },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("weed_search_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Weed Type Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedType == null,
                onClick = { onTypeSelected(null) },
                label = { Text("All Weeds (${weeds.size})") },
                modifier = Modifier.testTag("weed_type_all")
            )
            WeedType.values().forEach { type ->
                FilterChip(
                    selected = selectedType == type,
                    onClick = { onTypeSelected(if (selectedType == type) null else type) },
                    label = { Text(type.displayName) },
                    modifier = Modifier.testTag("weed_type_${type.name.lowercase()}")
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Weed List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 32.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(weeds) { weed ->
                WeedItemCard(weed = weed)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WeedItemCard(weed: WeedModel) {
    var expanded by remember { mutableStateOf(false) }
    var showImageDialog by remember { mutableStateOf(false) }

    if (showImageDialog && weed.imageResId != null) {
        Dialog(onDismissRequest = { showImageDialog = false }) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = weed.commonName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = weed.botanicalName,
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = { showImageDialog = false }) {
                            Icon(Icons.Default.Close, contentDescription = "Close preview")
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Image(
                        painter = painterResource(id = weed.imageResId),
                        contentDescription = weed.commonName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    if (weed.localKenyanNames.isNotEmpty()) {
                        Text(
                            text = "Kenyan Names: ${weed.localKenyanNames.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("weed_card_${weed.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Weed Image Banner
            if (weed.imageResId != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { showImageDialog = true }
                ) {
                    Image(
                        painter = painterResource(id = weed.imageResId),
                        contentDescription = weed.commonName,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Zoom indicator badge
                    Card(
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f)
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ZoomIn,
                                contentDescription = "Zoom weed photo",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "View Photo",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = weed.commonName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = weed.botanicalName,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Type badge
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = when (weed.type) {
                            WeedType.PARASITIC -> Color(0xFFFFEBEE)
                            WeedType.SEDGE -> Color(0xFFFFF3E0)
                            WeedType.GRASS -> Color(0xFFE8F5E9)
                            WeedType.BROADLEAF -> Color(0xFFE1F5FE)
                        }
                    )
                ) {
                    Text(
                        text = weed.type.displayName,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = when (weed.type) {
                            WeedType.PARASITIC -> Color(0xFFC62828)
                            WeedType.SEDGE -> Color(0xFFE65100)
                            WeedType.GRASS -> Color(0xFF2E7D32)
                            WeedType.BROADLEAF -> Color(0xFF0277BD)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Kenyan Vernacular Names
            if (weed.localKenyanNames.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Kenyan Names: ",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = weed.localKenyanNames.joinToString(", "),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Soil Science Bio-Indicator Banner
            val soilIndicator = remember(weed.id) { WeedSoilScienceCatalog.getIndicatorForWeed(weed.id) }
            if (soilIndicator != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(soilIndicator.soilPhCategory.colorHex).copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, Color(soilIndicator.soilPhCategory.colorHex).copy(alpha = 0.35f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Science,
                                contentDescription = null,
                                tint = Color(soilIndicator.soilPhCategory.colorHex),
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Soil Indicator: ${soilIndicator.soilPhRangeText}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(soilIndicator.soilPhCategory.colorHex)
                            )
                        }
                        Text(
                            text = "Status: ${soilIndicator.nutrientStatusSummary}",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Prescription: ${soilIndicator.recommendedAmendments.firstOrNull() ?: ""}",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Crops attacked
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                weed.cropsAttacked.forEach { crop ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = crop,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weed.competitiveImpact,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Expand / Collapse Action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (expanded) "Hide Management & Herbicides" else "View Cultural & Chemical Control (${weed.registeredHerbicides.size} Herbicides)",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    // Cultural management
                    Text(
                        text = "Cultural & Mechanical Control (IPM):",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    weed.culturalManagement.forEach { cm ->
                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                            Text("• ", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                            Text(
                                text = cm,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Chemical control & PCPB Herbicides
                    Text(
                        text = "PCPB-Registered Chemical Control & Herbicides:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    weed.registeredHerbicides.forEach { herb ->
                        HerbicideCard(herbicide = herb)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun HerbicideCard(herbicide: RegisteredHerbicide) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = herbicide.tradeName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = herbicide.registrant,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Active: ${herbicide.activeIngredient} (${herbicide.formulation})",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Knapsack Dose:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0277BD)
                    )
                    Text(
                        text = herbicide.dosagePerKnapsack,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Per Hectare Rate:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                    Text(
                        text = herbicide.dosagePerHectare,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Timing: ${herbicide.timing.displayName}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Selectivity: ${herbicide.selectivity}",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = herbicide.applicationGuidelines,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// =============================================================================
// TAB 3: LIVE WEATHER FORECASTING & AGRONOMIC GUIDANCE
// =============================================================================
@Composable
private fun WeatherForecastingTab(
    selectedCounty: KenyaAgriculturalCounty,
    weatherState: WeatherUiState,
    onCountySelected: (KenyaAgriculturalCounty) -> Unit,
    onAutoDetectLocation: () -> Unit
) {
    val context = LocalContext.current
    var countyDropdownExpanded by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val fine = perms[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarse = perms[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        if (fine || coarse) {
            onAutoDetectLocation()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 32.dp)
    ) {
        // Location Selector Header
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Current Location / Hub",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                            Text(
                                text = selectedCounty.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    // Auto-Detect GPS Button
                    IconButton(
                        onClick = {
                            val fineGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            val coarseGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            if (fineGranted || coarseGranted) {
                                onAutoDetectLocation()
                            } else {
                                permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
                            }
                        },
                        modifier = Modifier.testTag("btn_auto_detect_gps")
                    ) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = "Detect Farm GPS",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // County selector button
                Box {
                    OutlinedButton(
                        onClick = { countyDropdownExpanded = true },
                        modifier = Modifier.fillMaxWidth().testTag("btn_select_county")
                    ) {
                        Text("Switch Agricultural Hub (${selectedCounty.region})")
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.ExpandMore, contentDescription = null)
                    }

                    DropdownMenu(
                        expanded = countyDropdownExpanded,
                        onDismissRequest = { countyDropdownExpanded = false }
                    ) {
                        AgronomyCatalog.kenyanCounties.forEach { county ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(county.name, fontWeight = FontWeight.Bold)
                                        Text(county.primaryCrops, style = MaterialTheme.typography.bodySmall, fontSize = 11.sp)
                                    }
                                },
                                onClick = {
                                    onCountySelected(county)
                                    countyDropdownExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (weatherState) {
            is WeatherUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Detecting weather for ${selectedCounty.name}...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            is WeatherUiState.Error -> {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Weather Connection Notice",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = weatherState.message,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { onCountySelected(selectedCounty) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Retry Weather")
                        }
                    }
                }
            }

            is WeatherUiState.Success -> {
                val data = weatherState.result
                WeatherDashboard(weather = data)
            }
        }
    }
}

@Composable
private fun WeatherDashboard(weather: CompleteWeatherResult) {
    val cur = weather.currentWeather
    val adv = weather.advisory

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // 1. Current Weather Conditions Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${cur.temperatureCelsius.toInt()}°C",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = cur.conditionText,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Icon(
                        imageVector = if (cur.precipitationMm > 0.1) Icons.Default.WaterDrop else if (cur.temperatureCelsius > 24.0) Icons.Default.WbSunny else Icons.Default.Cloud,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(54.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Metric pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherMetricPill(
                        icon = Icons.Default.WaterDrop,
                        label = "Humidity",
                        value = "${cur.relativeHumidity}%",
                        color = Color(0xFF0277BD)
                    )
                    WeatherMetricPill(
                        icon = Icons.Default.Air,
                        label = "Wind Speed",
                        value = "${cur.windSpeedKmh} km/h",
                        color = Color(0xFF00695C)
                    )
                    WeatherMetricPill(
                        icon = Icons.Default.WaterDrop,
                        label = "Precipitation",
                        value = "${cur.precipitationMm} mm",
                        color = Color(0xFF4527A0)
                    )
                }
            }
        }

        // 2. AGRONOMIC GUIDANCE & ADVISORY
        Text(
            text = "Agronomic Guidance & Advisory:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Spray Status Card
        val (sprayBg, sprayText, sprayBorder) = when (adv.sprayStatus) {
            SprayStatus.OPTIMAL -> Triple(Color(0xFFE8F5E9), Color(0xFF2E7D32), Color(0xFFA5D6A7))
            SprayStatus.MARGINAL -> Triple(Color(0xFFFFF8E1), Color(0xFFE65100), Color(0xFFFFE082))
            SprayStatus.HAZARDOUS -> Triple(Color(0xFFFFEBEE), Color(0xFFC62828), Color(0xFFFFCDD2))
        }

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = sprayBg),
            border = BorderStroke(1.dp, sprayBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (adv.sprayStatus == SprayStatus.OPTIMAL) Icons.Default.CheckCircle else Icons.Default.Warning,
                        contentDescription = null,
                        tint = sprayText,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = adv.sprayStatus.label,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = sprayText
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = adv.sprayReason,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF263238)
                )
            }
        }

        // Disease Alert Card
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5)),
            border = BorderStroke(1.dp, Color(0xFFCE93D8)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Crop Disease Risk Alert (Blight / Rust):",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A1B9A)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = adv.diseaseRiskAlert,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF311B92)
                )
            }
        }

        // Fertilizer Application Window
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
            border = BorderStroke(1.dp, Color(0xFF80CBC4)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Fertilizer Top-Dressing Window:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004D40)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = adv.fertilizerTopdressWindow,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF004D40)
                )
            }
        }

        // 3. 7-Day Agricultural Forecast Strip
        Text(
            text = "7-Day Farm Weather Forecast:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(weather.dailyForecast) { day ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier.width(115.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = day.date.takeLast(5),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(
                            imageVector = if (day.precipitationSumMm > 1.0) Icons.Default.WaterDrop else Icons.Default.WbSunny,
                            contentDescription = null,
                            tint = if (day.precipitationSumMm > 1.0) Color(0xFF0277BD) else Color(0xFFF57F17),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${day.maxTemp.toInt()}° / ${day.minTemp.toInt()}°",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${day.rainProbabilityMax}% Rain",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 11.sp,
                            color = if (day.rainProbabilityMax > 50) Color(0xFFC62828) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${String.format("%.1f", day.precipitationSumMm)} mm",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WeatherMetricPill(
    icon: ImageVector,
    label: String,
    value: String,
    color: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.labelSmall, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = value, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}
