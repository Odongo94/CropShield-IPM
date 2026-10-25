package com.example.ui.screens.science

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.science.AgriculturalSciencesCatalog
import com.example.model.DeveloperInfo

import androidx.compose.material.icons.filled.MenuBook
import com.example.ui.screens.science.AgriculturalJournalsView

enum class SciencePillarTab(val title: String, val icon: ImageVector) {
    SOIL_SCIENCE("Soil Science", Icons.Default.Terrain),
    PATHOLOGY("Crop Pathology", Icons.Default.Coronavirus),
    ENTOMOLOGY("Entomology", Icons.Default.BugReport),
    AGRONOMY("Agronomy", Icons.Default.Agriculture),
    CONSERVATION("Conservation", Icons.Default.WaterDrop),
    JOURNALS_LEARNING("Journals & Quiz", Icons.Default.MenuBook)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AgriculturalSciencesView(
    modifier: Modifier = Modifier
) {
    var activeTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("agricultural_sciences_view")
    ) {
        // Tab selector for the Science Pillars & Journals
        SecondaryTabRow(
            selectedTabIndex = activeTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth().testTag("science_pillars_tab_row")
        ) {
            SciencePillarTab.values().forEachIndexed { index, tab ->
                val selected = activeTab == index
                Tab(
                    selected = selected,
                    onClick = { activeTab = index },
                    icon = { Icon(imageVector = tab.icon, contentDescription = tab.title, modifier = Modifier.size(18.dp)) },
                    text = {
                        Text(
                            text = tab.title,
                            maxLines = 1,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        if (SciencePillarTab.values()[activeTab] == SciencePillarTab.JOURNALS_LEARNING) {
            AgriculturalJournalsView(modifier = Modifier.fillMaxSize())
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                when (SciencePillarTab.values()[activeTab]) {
                // =============================================================
                // PILLAR 1: SOIL SCIENCE & EDAPHOLOGY
                // =============================================================
                SciencePillarTab.SOIL_SCIENCE -> {
                    item {
                        PillarHeroCard(
                            title = "Soil Science & Pedology",
                            subtitle = "Kenyan Soil Taxonomy, Soil Chemistry, pH & CEC Management",
                            icon = Icons.Default.Terrain
                        )
                    }

                    item {
                        SectionHeader(title = "Major Kenyan Agricultural Soil Types")
                    }

                    items(AgriculturalSciencesCatalog.soilSciencePillar.kenyanSoilTaxonomy) { soil ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = soil.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Regions: ${soil.distribution}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(text = "Physical: ${soil.physicalCharacteristics}", style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Chemistry: ${soil.fertilityAndChemistry}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                                Spacer(modifier = Modifier.height(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Management: ${soil.managementAdvice}",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Key Soil Chemistry Parameters")
                    }

                    items(AgriculturalSciencesCatalog.soilSciencePillar.chemicalParameters) { param ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = param.parameterName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                    Text(text = param.optimalRange, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = param.agriculturalSignificance, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Correction: ${param.deficiencyCorrection}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "Agricultural Liming Formula (Odongo Vincent):", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = AgriculturalSciencesCatalog.soilSciencePillar.limeRequirementGuidelines, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }

                // =============================================================
                // PILLAR 2: CROP PATHOLOGY
                // =============================================================
                SciencePillarTab.PATHOLOGY -> {
                    item {
                        PillarHeroCard(
                            title = "Crop Pathology (Phytopathology)",
                            subtitle = "Disease Triangle, Etiology, Fungal/Bacterial Cycles & FRAC Groups",
                            icon = Icons.Default.Coronavirus
                        )
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(text = "The Plant Disease Triangle Principle:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = AgriculturalSciencesCatalog.cropPathologyPillar.diseaseTriangleConcept, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Major Plant Pathogen Classes")
                    }

                    items(AgriculturalSciencesCatalog.cropPathologyPillar.pathogenClasses) { path ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = path.classification, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall)
                                Text(text = "Examples: ${path.keyExamples}", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Transmission: ${path.transmissionVectors}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Control: ${path.chemicalAndBiologicalControls}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Fungicide Resistance Action Committee (FRAC) Modes of Action")
                    }

                    items(AgriculturalSciencesCatalog.cropPathologyPillar.fracFungicideGuide) { frac ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = frac.fracCode, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = if (frac.resistanceRisk.contains("High")) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(text = frac.resistanceRisk, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(4.dp))
                                    }
                                }
                                Text(text = frac.modeOfAction, style = MaterialTheme.typography.bodySmall)
                                Text(text = "Actives: ${frac.activeIngredients}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "Disease Severity Index (DSI) Formula for Scouts:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = AgriculturalSciencesCatalog.cropPathologyPillar.diseaseSeverityIndexFormula, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }

                // =============================================================
                // PILLAR 3: AGRICULTURAL ENTOMOLOGY
                // =============================================================
                SciencePillarTab.ENTOMOLOGY -> {
                    item {
                        PillarHeroCard(
                            title = "Agricultural Entomology & IPM",
                            subtitle = "Insect Orders, Feeding Guilds, Beneficial Predators & IRAC Modes of Action",
                            icon = Icons.Default.BugReport
                        )
                    }

                    item {
                        SectionHeader(title = "Major Economic Insect Orders")
                    }

                    items(AgriculturalSciencesCatalog.entomologyPillar.insectOrders) { order ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = order.orderName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall)
                                Text(text = "Damage: ${order.mouthpartsAndDamage}", style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Kenyan Pests: ${order.majorKenyanPests}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "IPM Strategy: ${order.scoutingAndIpmStrategy}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Beneficial Biological Control Agents (Natural Enemies)")
                    }

                    items(AgriculturalSciencesCatalog.entomologyPillar.beneficialPredators) { benef ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = benef.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                                }
                                Text(text = "Pests Consumed: ${benef.targetPests}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                                Text(text = benef.habitatConservation, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Insecticide Resistance Action Committee (IRAC) Modes of Action")
                    }

                    items(AgriculturalSciencesCatalog.entomologyPillar.iracInsecticideGuide) { irac ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = irac.iracCode, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                Text(text = irac.targetSite, style = MaterialTheme.typography.bodySmall)
                                Text(text = "Actives: ${irac.activeIngredients}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary)
                                Text(text = "Stewardship: ${irac.resistanceManagement}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "Economic Threshold Level (ETL) & Injury Level (EIL):", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = AgriculturalSciencesCatalog.entomologyPillar.economicThresholdFormula, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }

                // =============================================================
                // PILLAR 4: AGRONOMY & CROP PHYSIOLOGY
                // =============================================================
                SciencePillarTab.AGRONOMY -> {
                    item {
                        PillarHeroCard(
                            title = "Agronomy & Crop Physiology",
                            subtitle = "C3/C4 Photosynthesis, Leaf Area Index, Planting Densities & CPWC",
                            icon = Icons.Default.Agriculture
                        )
                    }

                    item {
                        SectionHeader(title = "Crop Physiology Fundamentals")
                    }

                    items(AgriculturalSciencesCatalog.agronomyPillar.physiologyPrinciples) { phys ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = phys.principle, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = phys.description, style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "Field Application: ${phys.fieldApplication}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(8.dp))
                                }
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Standard Planting Densities & Geometry (Kenya Standards)")
                    }

                    items(AgriculturalSciencesCatalog.agronomyPillar.plantingGeometryStandards) { geom ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = geom.cropName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = "Spacing: ${geom.spacingCm}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                                    Text(text = "Seed: ${geom.seedRateKgPerHa}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = "Per Hectare: ${geom.populationPerHa}", style = MaterialTheme.typography.bodySmall)
                                    Text(text = "Per Acre: ${geom.populationPerAcre}", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "Critical Period for Weed Control (CPWC):", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = AgriculturalSciencesCatalog.agronomyPillar.criticalPeriodWeedControl, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }

                // =============================================================
                // PILLAR 5: SOIL & WATER CONSERVATION AND MANAGEMENT
                // =============================================================
                SciencePillarTab.CONSERVATION -> {
                    item {
                        PillarHeroCard(
                            title = "Soil & Water Conservation and Management",
                            subtitle = "Fanya Juu/Chini, Terracing, Push-Pull, Vetiver Dams & Water Harvesting",
                            icon = Icons.Default.WaterDrop
                        )
                    }

                    item {
                        SectionHeader(title = "Physical & Engineering Soil Conservation Structures")
                    }

                    items(AgriculturalSciencesCatalog.conservationPillar.engineeringStructures) { struct ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = struct.structureName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall)
                                Text(text = "Suitable Slopes: ${struct.suitableSlopes}", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Specifications: ${struct.constructionSpecifications}", style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Vegetation: ${struct.maintenanceAndVegetation}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Biological Conservation & Vegetative Hedgerows")
                    }

                    items(AgriculturalSciencesCatalog.conservationPillar.biologicalMeasures) { bio ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = bio.measureName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                                Text(text = "Species: ${bio.scientificSpecies}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = bio.mechanism, style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "Co-Benefits: ${bio.coBenefits}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    item {
                        SectionHeader(title = "Water Harvesting & Moisture Retention Techniques")
                    }

                    items(AgriculturalSciencesCatalog.conservationPillar.moistureRetentionTechniques) { tech ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = tech.technique, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall)
                                Text(text = tech.specifications, style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                                ) {
                                    Text(text = "Efficiency: ${tech.waterSavingEfficiency}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(6.dp))
                                }
                            }
                        }
                    }
                }
                SciencePillarTab.JOURNALS_LEARNING -> {
                    // Handled in top branch
                }
            }
        }
    }
}
}

@Composable
private fun PillarHeroCard(
    title: String,
    subtitle: String,
    icon: ImageVector
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f))
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
