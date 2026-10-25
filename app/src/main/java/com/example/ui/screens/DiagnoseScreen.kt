package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ThreatCatalog
import com.example.model.CropThreat
import com.example.model.ThreatType

/**
 * Factual diagnostic symptom key pairing physical field signs directly to verified threats.
 */
data class FactualDiagnosticKey(
    val id: String,
    val plantOrgan: String, // Foliage, Stem/Stalk, Flower/Bud, Fruit/Pod, Whole Plant, Soil/Root
    val primarySymptom: String,
    val detailedSignDescription: String,
    val targetThreatId: String,
    val targetCrops: List<String>
)

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DiagnoseScreen(
    onThreatClick: (CropThreat) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeSubTab by remember { mutableIntStateOf(0) } // 0: Factual Diagnostic Key, 1: Searchable A-Z Compendium

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("diagnose_screen")
    ) {
        // Tab selector
        PrimaryTabRow(
            selectedTabIndex = activeSubTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth().testTag("diagnose_tabs")
        ) {
            Tab(
                selected = activeSubTab == 0,
                onClick = { activeSubTab = 0 },
                icon = { Icon(Icons.Default.Verified, contentDescription = null, modifier = Modifier.size(18.dp)) },
                text = { Text("Factual Symptom Key", fontWeight = if (activeSubTab == 0) FontWeight.Bold else FontWeight.Medium) }
            )
            Tab(
                selected = activeSubTab == 1,
                onClick = { activeSubTab = 1 },
                icon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp)) },
                text = { Text("A-Z Pest & Disease Library", fontWeight = if (activeSubTab == 1) FontWeight.Bold else FontWeight.Medium) }
            )
        }

        if (activeSubTab == 0) {
            FactualSymptomKeyView(onThreatClick = onThreatClick)
        } else {
            FactualCompendiumSearchView(onThreatClick = onThreatClick)
        }
    }
}

/**
 * TAB 1: Step-by-Step Factual Symptom Diagnostic Key
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FactualSymptomKeyView(
    onThreatClick: (CropThreat) -> Unit
) {
    val plantOrgans = listOf("Foliage & Leaves", "Stalks & Stems", "Flowers & Buds", "Fruits & Pods", "Whole Plant & Crown", "Soil & Roots")
    var selectedOrgan by remember { mutableStateOf("Foliage & Leaves") }
    var selectedCropFilter by remember { mutableStateOf("All Crops") }
    var selectedKeyId by remember { mutableStateOf<String?>(null) }

    val cropsList = listOf("All Crops", "Tomato & Potato", "Maize & Cereals", "Beans & Legumes", "Cabbage & Brassicas", "Roses & Carnations", "Soil & General")

    // Master Factual Botanical & Entomological Keys
    val factualKeys = remember {
        listOf(
            // FOLIAGE
            FactualDiagnosticKey(
                id = "key_spider_mite_stippling",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Fine silken webbing + chlorotic yellow stippling",
                detailedSignDescription = "Pinhead yellow specks on upper leaf surface, fine silken webbing under leaves and shoot tips, foliar bronzing and paper-dry feel.",
                targetThreatId = "spider_mites",
                targetCrops = listOf("Roses & Carnations", "Tomato & Potato", "Beans & Legumes", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_late_blight_lesions",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Water-soaked greasy lesions + white mold underside",
                detailedSignDescription = "Irregular pale green to brown water-soaked lesions expanding rapidly from leaf margins; white cottony mildew ring on underside in morning dew.",
                targetThreatId = "late_blight",
                targetCrops = listOf("Tomato & Potato", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_tuta_blotch_mines",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Transparent blister blotch mines with dark frass pellets",
                detailedSignDescription = "Irregular transparent/silvery mines across leaf lamina containing black larval frass; foliage desiccates without yellow halos.",
                targetThreatId = "tuta_absoluta",
                targetCrops = listOf("Tomato & Potato", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_faw_windowpaning",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Ragged chewed holes + moist sawdust frass in whorl",
                detailedSignDescription = "Early 'window-paning' where epidermis is scraped thin; large gunshot feeding perforations and heavy accumulations of moist frass in whorl.",
                targetThreatId = "fall_armyworm",
                targetCrops = listOf("Maize & Cereals", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_dbm_skeletonizing",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Window-pane leaf feeding + wriggling green larvae on silk",
                detailedSignDescription = "Lower epidermis eaten away leaving thin papery windows; larvae drop on fine silk threads when disturbed; cabbage heads pierced.",
                targetThreatId = "diamondback_moth",
                targetCrops = listOf("Cabbage & Brassicas", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_powdery_mildew_talcum",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "White talcum-powder fungal patches on upper leaf surface",
                detailedSignDescription = "Circular white powdery colonies coalescing across leaves and flower buds; young leaves curl, blister, and distort.",
                targetThreatId = "rose_powdery_mildew",
                targetCrops = listOf("Roses & Carnations", "Tomato & Potato", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_rust_pustules_cinnamon",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Reddish-brown / cinnamon powdery pustules (uredinia)",
                detailedSignDescription = "Small raised blisters rupturing lower epidermis releasing powdery rust-colored spores; encircled by distinct chlorotic yellow halos.",
                targetThreatId = "bean_rust",
                targetCrops = listOf("Beans & Legumes", "Maize & Cereals", "Roses & Carnations", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_downy_mildew_angular",
                plantOrgan = "Foliage & Leaves",
                primarySymptom = "Purplish-brown angular spots delimited by leaf veins",
                detailedSignDescription = "Geometric angular purplish-red to brown lesions bounded strictly by veins; greyish-white downy sporulation underneath under high RH.",
                targetThreatId = "rose_downy_mildew",
                targetCrops = listOf("Roses & Carnations", "All Crops")
            ),

            // STEMS & STALKS
            FactualDiagnosticKey(
                id = "key_stem_borer_dead_heart",
                plantOrgan = "Stalks & Stems",
                primarySymptom = "Stem tunneling, sawdust frass pellets & 'dead heart' wilting",
                detailedSignDescription = "Larvae bore into central stem, extruding granular sawdust frass from entry holes; central growing whorl dries and dies while lower leaves stay green.",
                targetThreatId = "maize_stem_borer",
                targetCrops = listOf("Maize & Cereals", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_bacterial_wilt_vascular",
                plantOrgan = "Stalks & Stems",
                primarySymptom = "Sudden daytime wilting + brown vascular ring & bacterial streaming",
                detailedSignDescription = "Rapid flaccid wilting during midday sun while foliage remains green; cut stem in clear water releases milky white bacterial streaming threads.",
                targetThreatId = "bacterial_wilt",
                targetCrops = listOf("Tomato & Potato", "All Crops")
            ),

            // FLOWERS & BUDS
            FactualDiagnosticKey(
                id = "key_thrips_silvering",
                plantOrgan = "Flowers & Buds",
                primarySymptom = "Silvery petal patches, dark fecal flecks & distorted buds",
                detailedSignDescription = "Tiny slender yellow/brown insects moving rapidly inside flower petals; silvery bleached scars on petals and shoot tips with black speckling.",
                targetThreatId = "western_flower_thrips",
                targetCrops = listOf("Roses & Carnations", "Beans & Legumes", "Tomato & Potato", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_botrytis_grey_mold",
                plantOrgan = "Flowers & Buds",
                primarySymptom = "Grey fluffy mold sporulation + water-soaked petal spots",
                detailedSignDescription = "Small pinpoint water-soaked flecks on petals turning into mass of grey velvety fungal spores; flower buds rot and collapse.",
                targetThreatId = "botrytis_blight",
                targetCrops = listOf("Roses & Carnations", "Beans & Legumes", "Tomato & Potato", "All Crops")
            ),

            // FRUITS & PODS
            FactualDiagnosticKey(
                id = "key_bollworm_boreholes",
                plantOrgan = "Fruits & Pods",
                primarySymptom = "Circular boreholes in fruits/pods + internal frass cavities",
                detailedSignDescription = "Caterpillars boring half-submerged into tomato fruits, French bean pods, or bolls; cavities contaminated with frass leading to secondary soft rot.",
                targetThreatId = "african_bollworm",
                targetCrops = listOf("Tomato & Potato", "Beans & Legumes", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_maruca_pod_webbing",
                plantOrgan = "Fruits & Pods",
                primarySymptom = "Legume pod borer with webbed flower clusters & pod boreholes",
                detailedSignDescription = "Larvae web flowers, leaves, and pods together with silk; bore into green pods leaving frass-plugged holes; flowers drop prematurely.",
                targetThreatId = "maruca_pod_borer",
                targetCrops = listOf("Beans & Legumes", "All Crops")
            ),

            // WHOLE PLANT & CROWN
            FactualDiagnosticKey(
                id = "key_striga_parasitic_spikes",
                plantOrgan = "Whole Plant & Crown",
                primarySymptom = "Pink-purple spiked parasitic plants emerging at cereal root crowns",
                detailedSignDescription = "Host maize/sorghum shows severe stunting, drought-like leaf scorching, and chlorosis; bright pinkish-purple flowers clustered around host roots.",
                targetThreatId = "weed_striga",
                targetCrops = listOf("Maize & Cereals", "Soil & General", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_nutsedge_tubers",
                plantOrgan = "Whole Plant & Crown",
                primarySymptom = "Erect triangular stems, purple spikelets & underground tuber chain",
                detailedSignDescription = "Aggressive sedge with sharp three-sided stem; extensive underground rhizome network with hard nut-like dormant tubers; indicates plow pan compaction.",
                targetThreatId = "weed_purple_nutsedge",
                targetCrops = listOf("Soil & General", "All Crops")
            ),

            // SOIL & ROOTS
            FactualDiagnosticKey(
                id = "key_nitisol_p_fixation",
                plantOrgan = "Soil & Roots",
                primarySymptom = "Deep dusky red volcanic soil + severe phosphorus purpling",
                detailedSignDescription = "Friable red clay loam (Nitisol) with pH < 5.2; active iron/aluminum oxides fix broadcast DAP; lower crop leaves turn reddish-purple.",
                targetThreatId = "soil_humic_nitisol",
                targetCrops = listOf("Soil & General", "All Crops")
            ),
            FactualDiagnosticKey(
                id = "key_vertisol_cracking_waterlog",
                plantOrgan = "Soil & Roots",
                primarySymptom = "Black cracking clay + severe seasonal waterlogging & root suffocation",
                detailedSignDescription = "Deep summer dessication cracks and heavy plastic stickiness when wet (Pellic Vertisol); poor root aeration during rainy season.",
                targetThreatId = "soil_pellic_vertisol",
                targetCrops = listOf("Soil & General", "All Crops")
            )
        )
    }

    val filteredKeys = remember(selectedOrgan, selectedCropFilter) {
        factualKeys.filter { key ->
            val organMatch = key.plantOrgan == selectedOrgan
            val cropMatch = (selectedCropFilter == "All Crops") || key.targetCrops.contains(selectedCropFilter) || key.targetCrops.contains("All Crops")
            organMatch && cropMatch
        }
    }

    val selectedThreat = remember(selectedKeyId) {
        val key = factualKeys.find { it.id == selectedKeyId }
        if (key != null) {
            ThreatCatalog.allThreats.find { it.id == key.targetThreatId }
        } else null
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Header card
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Factual Botanical Symptom Key",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "100% verified pathogen & pest diagnostics without false guesses",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        item {
            // Step 1: Select Affected Plant Organ
            Text(
                text = "1. Select Plant Organ Showing Symptoms:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                plantOrgans.forEach { organ ->
                    FilterChip(
                        selected = selectedOrgan == organ,
                        onClick = {
                            selectedOrgan = organ
                            selectedKeyId = null
                        },
                        label = { Text(organ, style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.testTag("chip_organ_${organ.take(4)}")
                    )
                }
            }
        }

        item {
            // Step 2: Crop Filter
            Text(
                text = "2. Filter by Crop Context:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                cropsList.forEach { crop ->
                    FilterChip(
                        selected = selectedCropFilter == crop,
                        onClick = {
                            selectedCropFilter = crop
                            selectedKeyId = null
                        },
                        label = { Text(crop, style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.testTag("chip_crop_${crop.take(4)}")
                    )
                }
            }
        }

        item {
            Text(
                text = "3. Tap Exact Observable Field Sign (${filteredKeys.size} options):",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(filteredKeys, key = { it.id }) { key ->
            val isSelected = selectedKeyId == key.id
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedKeyId = if (isSelected) null else key.id
                    }
                    .testTag("symptom_key_${key.id}")
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = key.primarySymptom,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = key.detailedSignDescription,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Factual Verified Diagnostic Result Card
        if (selectedThreat != null) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier.fillMaxWidth().testTag("verified_diagnostic_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primary
                            ) {
                                Text(
                                    text = "100% FACTUAL MATCH",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                            Text(
                                text = selectedThreat.type.label,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = selectedThreat.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = selectedThreat.scientificName,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                        Spacer(modifier = Modifier.height(8.dp))

                        // Economic threshold
                        Text(
                            text = "Scouting & Economic Threshold:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = selectedThreat.economicThresholdInfo.ifBlank { "Intervene immediately upon first symptom detection" },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // PCPB Registered Control
                        val pcpbChem = selectedThreat.pcpbChemicals.firstOrNull()
                        if (pcpbChem != null) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "Official PCPB Registered Agrochemical:",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = "${pcpbChem.tradeName} (${pcpbChem.activeIngredient})",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = "Knapsack Dose: ${pcpbChem.knapsackDosage} • Per Ha: ${pcpbChem.perHaDosage}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = "PHI: ${pcpbChem.phiDays} days • REI: ${pcpbChem.reiHours} hours • Reg: ${pcpbChem.pcpbRegNo}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                    )
                                }
                            }
                        }

                        // CABI Biocontrol
                        val cabiBio = selectedThreat.cabiBioProtections.firstOrNull()
                        if (cabiBio != null) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFE8F5E9),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "CABI Biological Control Agent:",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF2E7D32)
                                    )
                                    Text(
                                        text = "${cabiBio.tradeName} (${cabiBio.activeAgent})",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1B5E20)
                                    )
                                    Text(
                                        text = "${cabiBio.modeOfAction}. Rate: ${cabiBio.applicationRate}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = { onThreatClick(selectedThreat) },
                            modifier = Modifier.fillMaxWidth().testTag("view_full_dossier_btn")
                        ) {
                            Text("Open Complete Dossier & Lifecycle Stages")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/**
 * TAB 2: Searchable A-Z Pest, Disease, Weed & Soil Compendium
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FactualCompendiumSearchView(
    onThreatClick: (CropThreat) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedThreatType by remember { mutableStateOf<ThreatType?>(null) }

    val allThreats = ThreatCatalog.allThreats

    val filteredThreats = remember(searchQuery, selectedThreatType) {
        allThreats.filter { threat ->
            val matchesType = selectedThreatType == null || threat.type == selectedThreatType
            val matchesSearch = searchQuery.isBlank() ||
                    threat.name.contains(searchQuery, ignoreCase = true) ||
                    threat.scientificName.contains(searchQuery, ignoreCase = true) ||
                    threat.targetCrops.any { it.contains(searchQuery, ignoreCase = true) } ||
                    threat.summary.contains(searchQuery, ignoreCase = true) ||
                    threat.diagnosticKeys.any { it.contains(searchQuery, ignoreCase = true) }
            matchesType && matchesSearch
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by pest, disease, crop or symptom...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("compendium_search_input")
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = selectedThreatType == null,
                    onClick = { selectedThreatType = null },
                    label = { Text("All (${allThreats.size})") },
                    modifier = Modifier.testTag("chip_threat_all")
                )
                FilterChip(
                    selected = selectedThreatType == ThreatType.PEST,
                    onClick = { selectedThreatType = if (selectedThreatType == ThreatType.PEST) null else ThreatType.PEST },
                    label = { Text("Insect & Mite Pests") },
                    leadingIcon = { Icon(Icons.Default.BugReport, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    modifier = Modifier.testTag("chip_threat_pest")
                )
                FilterChip(
                    selected = selectedThreatType == ThreatType.DISEASE,
                    onClick = { selectedThreatType = if (selectedThreatType == ThreatType.DISEASE) null else ThreatType.DISEASE },
                    label = { Text("Fungal & Bacterial Diseases") },
                    leadingIcon = { Icon(Icons.Default.Coronavirus, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    modifier = Modifier.testTag("chip_threat_disease")
                )
                FilterChip(
                    selected = selectedThreatType == ThreatType.WEED,
                    onClick = { selectedThreatType = if (selectedThreatType == ThreatType.WEED) null else ThreatType.WEED },
                    label = { Text("Invasive Weeds") },
                    leadingIcon = { Icon(Icons.Default.Grass, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    modifier = Modifier.testTag("chip_threat_weed")
                )
                FilterChip(
                    selected = selectedThreatType == ThreatType.SOIL,
                    onClick = { selectedThreatType = if (selectedThreatType == ThreatType.SOIL) null else ThreatType.SOIL },
                    label = { Text("Soil Health & Orders") },
                    leadingIcon = { Icon(Icons.Default.Terrain, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    modifier = Modifier.testTag("chip_threat_soil")
                )
            }
        }

        items(filteredThreats, key = { it.id }) { threat ->
            Card(
                onClick = { onThreatClick(threat) },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("threat_item_${threat.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = when (threat.type) {
                                ThreatType.PEST -> MaterialTheme.colorScheme.errorContainer
                                ThreatType.DISEASE -> MaterialTheme.colorScheme.primaryContainer
                                ThreatType.WEED -> MaterialTheme.colorScheme.tertiaryContainer
                                ThreatType.SOIL -> MaterialTheme.colorScheme.secondaryContainer
                            }
                        ) {
                            Text(
                                text = threat.type.label,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                        Text(
                            text = "Severity: ${threat.severityLevel}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (threat.severityLevel == "Extreme" || threat.severityLevel == "Severe") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = threat.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = threat.scientificName,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = threat.summary,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Target Crops: ${threat.targetCrops.take(3).joinToString(", ")}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
