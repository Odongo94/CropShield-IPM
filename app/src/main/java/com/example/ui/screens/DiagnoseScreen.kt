package com.example.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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

data class SymptomOption(
    val id: String,
    val title: String,
    val description: String,
    val associatedThreatIds: List<String>
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DiagnoseScreen(
    onThreatClick: (CropThreat) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCrop by remember { mutableStateOf("Tomato") }
    var selectedPart by remember { mutableStateOf("Leaves") }

    val crops = listOf("Tomato", "Potato", "Maize", "Beans", "French Beans", "Cabbage / Kale", "Cut Roses", "Cotton", "Sorghum", "All Field Soils")
    val plantParts = listOf("Leaves", "Stem", "Fruit", "Whorl", "Roots", "Soil Surface & Profile")

    val availableSymptoms = remember {
        listOf(
            SymptomOption(
                id = "water_soaked_white_halo",
                title = "Water-soaked lesions with white fuzzy mold underside",
                description = "Dark irregular spots turning greasy brown, white cottony spores in morning dew.",
                associatedThreatIds = listOf("late_blight")
            ),
            SymptomOption(
                id = "frass_ragged_whorl",
                title = "Ragged chewed leaves & moist sawdust-like frass in whorl",
                description = "Window-paning, gunshot holes, inverted Y mark on caterpillar head.",
                associatedThreatIds = listOf("fall_armyworm")
            ),
            SymptomOption(
                id = "blotch_transparent_mines",
                title = "Transparent blister blotch mines with dark frass inside",
                description = "Silvery papery patches on foliage, pinhead fruit entry punctures.",
                associatedThreatIds = listOf("tuta_absoluta")
            ),
            SymptomOption(
                id = "caterpillar_fruit_entry_holes",
                title = "Circular boreholes in fruit/pods with frass contamination",
                description = "Caterpillars boring into tomato fruits, legume pods, cotton bolls, causing secondary rot.",
                associatedThreatIds = listOf("african_bollworm")
            ),
            SymptomOption(
                id = "stem_tunnels_dead_heart",
                title = "Shot-hole perforations, stalk tunneling & 'dead heart' wilting",
                description = "Internal stems bored with frass sawdust, central whorl wilting in young cereals.",
                associatedThreatIds = listOf("maize_stem_borer")
            ),
            SymptomOption(
                id = "pink_purple_parasite_chlorosis",
                title = "Pink-purple spiked parasitic plants around cereal root crowns",
                description = "Host cereal shows severe drought-like scorching, interveinal chlorosis, low N/P soil sign.",
                associatedThreatIds = listOf("weed_striga")
            ),
            SymptomOption(
                id = "triangular_stem_rhizome_tubers",
                title = "Triangular erect stems, purple spikelets & underground tuber chain",
                description = "Aggressive sedge forming persistent tuber chains, indicator of plowpan soil compaction.",
                associatedThreatIds = listOf("weed_purple_nutsedge")
            ),
            SymptomOption(
                id = "red_volcanic_phosphorus_fixation",
                title = "Deep dusky red volcanic soil with phosphorus deficiency signs",
                description = "Acidic red clay loam profile, high P fixation, purpling of lower crop foliage.",
                associatedThreatIds = listOf("soil_humic_nitisol")
            ),
            SymptomOption(
                id = "black_cotton_deep_cracking_waterlog",
                title = "Dark cracking clay (Black Cotton) with seasonal waterlogging",
                description = "Deep summer dessication cracks, extreme plasticity, stunted root aeration in rainy season.",
                associatedThreatIds = listOf("soil_pellic_vertisol")
            ),
            SymptomOption(
                id = "concentric_bullseye_rings",
                title = "Concentric 'bullseye' target rings with yellow halo",
                description = "Starts on bottom older foliage, leaves dry up like paper and drop.",
                associatedThreatIds = listOf("early_blight")
            ),
            SymptomOption(
                id = "midday_green_wilt",
                title = "Rapid wilting during daytime heat while leaves stay green",
                description = "Plant wilts suddenly, recovers at night early on, milky threads on stem test.",
                associatedThreatIds = listOf("bacterial_wilt")
            ),
            SymptomOption(
                id = "curled_sticky_honeydew",
                title = "Curled crinkled leaves with sticky shiny honeydew & ants",
                description = "Dense clusters of soft tiny green/black insects on shoot tips.",
                associatedThreatIds = listOf("aphids")
            )
        )
    }

    val selectedSymptoms = remember { mutableStateListOf<String>() }

    // Diagnostic calculation
    val diagnosticMatches = remember(selectedCrop, selectedPart, selectedSymptoms.toList()) {
        ThreatCatalog.allThreats.map { threat ->
            var score = 0

            // Crop match bonus
            if (threat.targetCrops.any { it.equals(selectedCrop, ignoreCase = true) }) {
                score += 30
            }

            // Part match bonus
            if (threat.affectedParts.any { it.equals(selectedPart, ignoreCase = true) }) {
                score += 20
            }

            // Symptom match bonus
            selectedSymptoms.forEach { symptomId ->
                val symptom = availableSymptoms.find { it.id == symptomId }
                if (symptom?.associatedThreatIds?.contains(threat.id) == true) {
                    score += 50
                }
            }

            val confidencePercent = (score).coerceIn(0, 100)
            threat to confidencePercent
        }
            .filter { it.second >= 30 }
            .sortedByDescending { it.second }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 24.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Offline Field Diagnostic Assistant",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Match field damage signs to identify pests or pathogens instantly",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (selectedSymptoms.isNotEmpty()) {
                IconButton(
                    onClick = { selectedSymptoms.clear() },
                    modifier = Modifier.testTag("reset_symptoms_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reset symptoms",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step 1: Crop Selection
        Text(
            text = "1. Select Affected Crop:",
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
            crops.forEach { crop ->
                val isSelected = selectedCrop == crop
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .clickable { selectedCrop = crop }
                        .testTag("crop_pill_$crop")
                ) {
                    Text(
                        text = crop,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step 2: Plant Part Selection
        Text(
            text = "2. Select Plant Part Showing Symptoms:",
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
            plantParts.forEach { part ->
                val isSelected = selectedPart == part
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .clickable { selectedPart = part }
                        .testTag("part_pill_$part")
                ) {
                    Text(
                        text = part,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Step 3: Check Visible Symptoms
        Text(
            text = "3. Check All Visible Field Symptoms:",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(6.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            availableSymptoms.forEach { symptom ->
                val isChecked = selectedSymptoms.contains(symptom.id)
                Card(
                    onClick = {
                        if (isChecked) selectedSymptoms.remove(symptom.id)
                        else selectedSymptoms.add(symptom.id)
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    ),
                    border = if (isChecked) {
                        androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                    } else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("symptom_checkbox_${symptom.id}")
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                if (checked) selectedSymptoms.add(symptom.id)
                                else selectedSymptoms.remove(symptom.id)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = symptom.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isChecked) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = symptom.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = if (isChecked) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Diagnosis Results Section
        Text(
            text = "Diagnostic Match Results (${diagnosticMatches.size}):",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (diagnosticMatches.isEmpty()) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Select a crop, plant part, and check matching symptoms above to compute match probability.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                diagnosticMatches.forEach { (threat, confidence) ->
                    Card(
                        onClick = { onThreatClick(threat) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("diagnose_result_${threat.id}")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = if (threat.type == ThreatType.DISEASE) Icons.Default.Coronavirus else Icons.Default.BugReport,
                                        contentDescription = null,
                                        tint = if (threat.type == ThreatType.DISEASE) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = threat.name,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = threat.scientificName,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = when {
                                        confidence >= 80 -> Color(0xFFE8F5E9)
                                        confidence >= 50 -> Color(0xFFFFF3E0)
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    }
                                ) {
                                    Text(
                                        text = "$confidence% Match",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = when {
                                            confidence >= 80 -> Color(0xFF2E7D32)
                                            confidence >= 50 -> Color(0xFFE65100)
                                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                                        },
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = threat.summary,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${threat.lifecycleStages.size} Lifecycle Stages • ${threat.ipmPlan.chemicalControls.size} Active Ingredients",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "View Life Cycle & IPM",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
