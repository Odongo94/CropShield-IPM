package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FilterDrama
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.CropThreat
import com.example.model.LifecycleStage
import com.example.model.StageIconType
import com.example.model.ThreatType
import com.example.model.VulnerabilityLevel

enum class LifecycleDisplayMode {
    STEPPER,
    CAROUSEL
}

@Composable
fun DiseaseLifecycleStepper(
    threat: CropThreat,
    modifier: Modifier = Modifier,
    initialStageIndex: Int = 0,
    onActiveIngredientClick: ((String) -> Unit)? = null
) {
    if (threat.lifecycleStages.isEmpty()) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Lifecycle data unavailable for this organism.",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        return
    }

    var currentStageIndex by remember(threat.id) {
        mutableIntStateOf(initialStageIndex.coerceIn(0, threat.lifecycleStages.lastIndex))
    }
    var displayMode by remember { mutableStateOf(LifecycleDisplayMode.STEPPER) }
    var showInfographicDiagram by remember { mutableStateOf(false) }

    val currentStage = threat.lifecycleStages[currentStageIndex]

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("lifecycle_stepper_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Title & Mode Switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val headerIcon = when (threat.pathogenClass) {
                            com.example.model.PathogenClass.INSECT_PEST -> Icons.Default.BugReport
                            com.example.model.PathogenClass.MITE_PEST -> Icons.Default.BugReport
                            com.example.model.PathogenClass.BACTERIAL -> Icons.Default.Spa
                            com.example.model.PathogenClass.VIRAL -> Icons.Default.FlightTakeoff
                            com.example.model.PathogenClass.NEMATODE -> Icons.Default.BugReport
                            com.example.model.PathogenClass.FUNGAL -> Icons.Default.Coronavirus
                            com.example.model.PathogenClass.WEED_SPECIES -> Icons.Default.Grass
                            com.example.model.PathogenClass.SOIL_PROFILE -> Icons.Default.Terrain
                        }
                        val headerTitle = when (threat.pathogenClass) {
                            com.example.model.PathogenClass.INSECT_PEST -> "Insect Metamorphosis & Life Cycle"
                            com.example.model.PathogenClass.MITE_PEST -> "Acarine Mite Life Cycle"
                            com.example.model.PathogenClass.BACTERIAL -> "Bacterial Infection Cycle"
                            com.example.model.PathogenClass.VIRAL -> "Viral-Vector Infection Cycle"
                            com.example.model.PathogenClass.NEMATODE -> "Nematode Root Infection Cycle"
                            com.example.model.PathogenClass.FUNGAL -> "Fungal Infection & Spore Cycle"
                            com.example.model.PathogenClass.WEED_SPECIES -> "Weed Phenology & Seed Bank Dynamics"
                            com.example.model.PathogenClass.SOIL_PROFILE -> "Soil Genesis, Health & Remediation Cycle"
                        }
                        Icon(
                            imageVector = headerIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = headerTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = "Stage ${currentStage.stageNumber} of ${threat.lifecycleStages.size}: ${currentStage.title}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Stepper vs Carousel mode pills
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(2.dp)
                ) {
                    DisplayModePill(
                        label = "Stepper",
                        isSelected = displayMode == LifecycleDisplayMode.STEPPER,
                        testTag = "mode_stepper_pill",
                        onClick = { displayMode = LifecycleDisplayMode.STEPPER }
                    )
                    DisplayModePill(
                        label = "Carousel",
                        isSelected = displayMode == LifecycleDisplayMode.CAROUSEL,
                        testTag = "mode_carousel_pill",
                        onClick = { displayMode = LifecycleDisplayMode.CAROUSEL }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Stepped Progress Indicator or Carousel Strip
            if (displayMode == LifecycleDisplayMode.STEPPER) {
                SteppedProgressBar(
                    stages = threat.lifecycleStages,
                    currentIndex = currentStageIndex,
                    onStageClick = { index -> currentStageIndex = index }
                )
            } else {
                CarouselStripBar(
                    stages = threat.lifecycleStages,
                    currentIndex = currentStageIndex,
                    onStageClick = { index -> currentStageIndex = index }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Animated Stage Details Card
            AnimatedContent(
                targetState = currentStage,
                transitionSpec = {
                    fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)) togetherWith
                        fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                },
                label = "stage_content"
            ) { stage ->
                StageDetailContent(
                    stage = stage,
                    onActiveIngredientClick = onActiveIngredientClick
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stepper Navigation Footer (Prev / Next Buttons)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        if (currentStageIndex > 0) currentStageIndex--
                    },
                    enabled = currentStageIndex > 0,
                    modifier = Modifier
                        .testTag("prev_stage_button")
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Previous stage",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (currentStageIndex > 0) "Stage ${currentStageIndex}" else "Start",
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = {
                        if (currentStageIndex < threat.lifecycleStages.lastIndex) {
                            currentStageIndex++
                        } else {
                            currentStageIndex = 0 // loop cycle
                        }
                    },
                    modifier = Modifier
                        .testTag("next_stage_button")
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (currentStageIndex < threat.lifecycleStages.lastIndex) "Next Stage" else "Restart Cycle",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Next stage",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Expandable Biological Infographic Diagram Section
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                onClick = { showInfographicDiagram = !showInfographicDiagram },
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Landscape,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (threat.pathogenClass) {
                                com.example.model.PathogenClass.INSECT_PEST, com.example.model.PathogenClass.MITE_PEST -> "View Entomological Life Cycle & Metamorphosis Map"
                                com.example.model.PathogenClass.BACTERIAL -> "View Bacterial Infection & Xylem Wilt Cycle"
                                com.example.model.PathogenClass.VIRAL -> "View Viral Inoculation & Vector Transmission Map"
                                com.example.model.PathogenClass.NEMATODE -> "View Nematode Soil & Root Infection Cycle"
                                com.example.model.PathogenClass.FUNGAL -> "View Fungal Spore & Foliar Infection Cycle"
                                com.example.model.PathogenClass.WEED_SPECIES -> "View Weed Phenology & Seed Bank Dynamics"
                                com.example.model.PathogenClass.SOIL_PROFILE -> "View Pedological Soil Profile & Remediation Cycle"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = if (showInfographicDiagram) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Toggle diagram",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            AnimatedVisibility(visible = showInfographicDiagram) {
                TaxaBiologicalDiagramCard(threat = threat)
            }
        }
    }
}

@Composable
fun TaxaBiologicalDiagramCard(threat: CropThreat) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        when (threat.pathogenClass) {
            com.example.model.PathogenClass.INSECT_PEST, com.example.model.PathogenClass.MITE_PEST -> {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFBE9E7)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.BugReport,
                                contentDescription = null,
                                tint = Color(0xFFD84315),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pest Metamorphosis & Critical Intervention Point",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFBF360C)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Sequence chips
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetamorphosisStepBadge(step = "1. Egg Mass", desc = "Oviposition under leaves / whorls", isCritical = false)
                            MetamorphosisStepBadge(step = "2. Early Instar (1–2)", desc = "VULNERABLE: Leaf chewing, bio-sprays effective", isCritical = true)
                            MetamorphosisStepBadge(step = "3. Late Instar / Borer", desc = "Inside stems/buds, high chemical resistance", isCritical = false)
                            MetamorphosisStepBadge(step = "4. Pupa / Cocoon", desc = "Soil/silk cocoon, deep tillage control", isCritical = false)
                            MetamorphosisStepBadge(step = "5. Adult Moth/Fly", desc = "Flight, pheromone & light traps", isCritical = false)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "CRITICAL WINDOW: Always target Instars 1–2 while caterpillars or nymphs feed exposed on leaf foliage. Once borers enter maize stems, tomato fruits, or flower buds, chemical efficacy drops by over 80%.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4E342E)
                        )
                    }
                }
            }

            com.example.model.PathogenClass.BACTERIAL -> {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Spa,
                                contentDescription = null,
                                tint = Color(0xFF00695C),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Bacterial Ingress & Vascular Wilt Cycle",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF004D40)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetamorphosisStepBadge(step = "1. Survival", desc = "Soil, seed coat, crop debris", isCritical = false)
                            MetamorphosisStepBadge(step = "2. Hydathode Ingress", desc = "Rain splash & pruning wounds", isCritical = true)
                            MetamorphosisStepBadge(step = "3. Xylem Biofilm", desc = "Bacterial slime plugs water vessels", isCritical = false)
                            MetamorphosisStepBadge(step = "4. Rapid Wilt & Ooze", desc = "Foliar collapse, milky ooze stream", isCritical = false)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "BACTERIAL RULE: Bacterial infections cannot be cured once xylem vessels are clogged. Apply preventative copper bactericides before storms and maintain strict tool disinfections.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF004D40)
                        )
                    }
                }
            }

            com.example.model.PathogenClass.VIRAL -> {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.FlightTakeoff,
                                contentDescription = null,
                                tint = Color(0xFFF57F17),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Viral Inoculation & Vector Transmission Cycle",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE65100)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetamorphosisStepBadge(step = "1. Vector Acquisition", desc = "Insects feed on reservoir weed", isCritical = false)
                            MetamorphosisStepBadge(step = "2. Stylet Inoculation", desc = "Sap transmission to healthy crop", isCritical = true)
                            MetamorphosisStepBadge(step = "3. Phloem Replication", desc = "Systemic viral multiplication", isCritical = false)
                            MetamorphosisStepBadge(step = "4. Mosaic & Stunting", desc = "Severe yield loss & dead-heart", isCritical = false)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "VIRUS RULE: Plant viruses have no chemical cure. Total control relies on controlling the insect vectors (thrips, aphids, whiteflies) and planting certified disease-free seed.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4E342E)
                        )
                    }
                }
            }

            com.example.model.PathogenClass.NEMATODE -> {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.BugReport,
                                contentDescription = null,
                                tint = Color(0xFF512DA8),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Nematode Root Infection & Gall Cycle",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF311B92)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetamorphosisStepBadge(step = "1. Soil J2 Juvenile", desc = "Free living infective stage", isCritical = true)
                            MetamorphosisStepBadge(step = "2. Root Penetration", desc = "Enters root tip behind cap", isCritical = false)
                            MetamorphosisStepBadge(step = "3. Giant Feeding Cell", desc = "Induces hypertrophy and root gall", isCritical = false)
                            MetamorphosisStepBadge(step = "4. Egg Mass Release", desc = "Hundreds of eggs in gelatin matrix", isCritical = false)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "NEMATODE RULE: Target soil J2 juveniles before root penetration using bio-nematicides (Purpureocillium lilacinum), tagetes (marigold) biofumigation, and solarization.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF311B92)
                        )
                    }
                }
            }

            com.example.model.PathogenClass.FUNGAL -> {
                Image(
                    painter = painterResource(id = R.drawable.img_disease_lifecycle),
                    contentDescription = "Infographic diagram of plant disease lifecycle",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Fungal & oomycete pathogens repeat this cycle thousands of times per acre during favorable moisture windows. Break the cycle at Stage 1 (Spore Inoculum) or Stage 2 (Penetration) to save yield.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            com.example.model.PathogenClass.WEED_SPECIES -> {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Grass,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Weed Seed Bank Phenology & Depletion Window",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B5E20)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetamorphosisStepBadge(step = "1. Seed Bank Dormancy", desc = "Deep buried seeds, solarization", isCritical = false)
                            MetamorphosisStepBadge(step = "2. Germination / Rosette", desc = "VULNERABLE: Hand rogueing or post-emergence spray", isCritical = true)
                            MetamorphosisStepBadge(step = "3. Flowering Spurt", desc = "Prevent flower opening before seed set", isCritical = true)
                            MetamorphosisStepBadge(step = "4. Seed Rain Dispersal", desc = "Thousands of viable seeds shed", isCritical = false)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "WEED MANAGEMENT RULE: Intervene during the early seedling/rosette stage before flowering. One plant allowed to set seed contaminates the soil bank for up to 10–20 seasons.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF1B5E20)
                        )
                    }
                }
            }

            com.example.model.PathogenClass.SOIL_PROFILE -> {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Terrain,
                                contentDescription = null,
                                tint = Color(0xFF512DA8),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pedological Soil Fertility & Remediation Cycle",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF311B92)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetamorphosisStepBadge(step = "1. Soil Baseline", desc = "Texture, natural CEC & mineralogy", isCritical = false)
                            MetamorphosisStepBadge(step = "2. Acid / Compaction Stress", desc = "Al toxicity, P fixation, crusting", isCritical = false)
                            MetamorphosisStepBadge(step = "3. Soil Amendment", desc = "CRITICAL: Agricultural lime, rock phosphate, BBF", isCritical = true)
                            MetamorphosisStepBadge(step = "4. Fertile Equilibrium", desc = "High organic carbon, active soil microbiology", isCritical = false)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "SOIL CONSERVATION RULE: Soil chemical and physical rehabilitation requires multi-season management: apply calcitic/dolomitic lime on Nitisols to liberate fixed Phosphorus, and form Broadbed & Furrows (BBF) on Vertisols for drainage.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF311B92)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MetamorphosisStepBadge(
    step: String,
    desc: String,
    isCritical: Boolean
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (isCritical) Color(0xFFFFCCBC) else MaterialTheme.colorScheme.surface,
        border = if (isCritical) androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD84315)) else null,
        modifier = Modifier.width(150.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = step,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = if (isCritical) Color(0xFFBF360C) else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 13.sp
            )
        }
    }
}

@Composable
private fun DisplayModePill(
    label: String,
    isSelected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        label = "mode_bg"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "mode_text"
    )

    Box(
        modifier = Modifier
            .testTag(testTag)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = contentColor
        )
    }
}

/**
 * Stepped Progress Bar Component with interactive nodes, connecting lines,
 * and vulnerability status badges.
 */
@Composable
fun SteppedProgressBar(
    stages: List<LifecycleStage>,
    currentIndex: Int,
    onStageClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        stages.forEachIndexed { index, stage ->
            val isCurrent = index == currentIndex
            val isPassed = index < currentIndex

            val circleColor by animateColorAsState(
                targetValue = when {
                    isCurrent -> MaterialTheme.colorScheme.primary
                    isPassed -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                },
                label = "step_circle"
            )

            val scale by animateFloatAsState(
                targetValue = if (isCurrent) 1.15f else 1.0f,
                label = "step_scale"
            )

            // Step Node Circle
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .testTag("step_node_${stage.stageNumber}")
                    .clickable { onStageClick(index) }
                    .padding(horizontal = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .scale(scale)
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(circleColor)
                        .border(
                            width = if (isCurrent) 2.5.dp else 1.dp,
                            color = if (isCurrent) MaterialTheme.colorScheme.secondary else Color.Transparent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isPassed) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Completed stage",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    } else {
                        Icon(
                            imageVector = getStageVectorIcon(stage.iconType),
                            contentDescription = stage.title,
                            tint = if (isCurrent) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "S${stage.stageNumber}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                    color = if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Connecting Track Line (except after last step)
            if (index < stages.lastIndex) {
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (index < currentIndex) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            }
                        )
                )
            }
        }
    }
}

/**
 * Carousel Strip Bar allowing swipe/click across stage preview mini-cards.
 */
@Composable
fun CarouselStripBar(
    stages: List<LifecycleStage>,
    currentIndex: Int,
    onStageClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stages.forEachIndexed { index, stage ->
            val isSelected = index == currentIndex

            Card(
                onClick = { onStageClick(index) },
                modifier = Modifier
                    .width(130.dp)
                    .testTag("carousel_card_${stage.stageNumber}"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    }
                ),
                border = if (isSelected) {
                    androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                } else null
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Stage ${stage.stageNumber}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Icon(
                            imageVector = getStageVectorIcon(stage.iconType),
                            contentDescription = null,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stage.title,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

/**
 * High-fidelity details of the selected lifecycle stage.
 */
@Composable
fun StageDetailContent(
    stage: LifecycleStage,
    onActiveIngredientClick: ((String) -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
            .padding(14.dp)
    ) {
        // Top Badges: Stage #, Duration, and Vulnerability Window
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Duration Badge
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.HourglassTop,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stage.duration,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // Vulnerability Level Badge
            VulnerabilityBadge(stage.vulnerability)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Stage Title & Scientific Subheading
        Text(
            text = stage.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stage.scientificSub,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Biological description
        Text(
            text = stage.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Field Visual Symptom Callout (Magnifying Glass)
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "What Farmers Spot in Field:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = stage.symptomsToSpot,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // IPM Cycle Breaking Action Callout (Shield)
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = when (stage.vulnerability) {
                    VulnerabilityLevel.CRITICAL -> Color(0xFFFFF3E0)
                    VulnerabilityLevel.HIGH -> Color(0xFFE8F5E9)
                    else -> MaterialTheme.colorScheme.surface
                }
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = when (stage.vulnerability) {
                        VulnerabilityLevel.CRITICAL -> Color(0xFFE65100)
                        VulnerabilityLevel.HIGH -> Color(0xFF2E7D32)
                        else -> MaterialTheme.colorScheme.primary
                    },
                    modifier = Modifier
                        .size(18.dp)
                        .padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "IPM Action to Break Cycle:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = when (stage.vulnerability) {
                            VulnerabilityLevel.CRITICAL -> Color(0xFFE65100)
                            VulnerabilityLevel.HIGH -> Color(0xFF2E7D32)
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )
                    Text(
                        text = stage.targetAction,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Active Ingredients for this stage
        if (stage.recommendedActiveIngredients.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Target Chemistry for this Stage:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                stage.recommendedActiveIngredients.forEach { ai ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.clickable { onActiveIngredientClick?.invoke(ai) }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Medication,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = ai,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VulnerabilityBadge(level: VulnerabilityLevel) {
    val (bgColor, textColor, label, icon) = when (level) {
        VulnerabilityLevel.CRITICAL -> Quad(
            Color(0xFFFFEBEE),
            Color(0xFFC62828),
            "Critical Window",
            Icons.Default.Warning
        )
        VulnerabilityLevel.HIGH -> Quad(
            Color(0xFFE8F5E9),
            Color(0xFF2E7D32),
            "High Vulnerability",
            Icons.Default.CheckCircle
        )
        VulnerabilityLevel.MODERATE -> Quad(
            Color(0xFFFFF8E1),
            Color(0xFFF57F17),
            "Moderate Resistance",
            Icons.Default.HelpOutline
        )
        VulnerabilityLevel.LOW -> Quad(
            Color(0xFFECEFF1),
            Color(0xFF455A64),
            "Protected Stage",
            Icons.Default.Shield
        )
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = bgColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

private data class Quad<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

fun getStageVectorIcon(iconType: StageIconType): ImageVector {
    return when (iconType) {
        StageIconType.SPORE -> Icons.Default.Grain
        StageIconType.PENETRATION -> Icons.Default.WaterDrop
        StageIconType.HYPHAE -> Icons.Default.Spa
        StageIconType.LESION -> Icons.Default.Coronavirus
        StageIconType.DISPERSAL -> Icons.Default.FilterDrama
        StageIconType.EGG -> Icons.Default.Egg
        StageIconType.LARVA -> Icons.Default.BugReport
        StageIconType.PUPA -> Icons.Default.Park
        StageIconType.ADULT -> Icons.Default.FlightTakeoff
        StageIconType.BACTERIA_SURVIVAL -> Icons.Default.Spa
        StageIconType.BACTERIA_ENTRY -> Icons.Default.WaterDrop
        StageIconType.VASCULAR_COLONIZATION -> Icons.Default.Grass
        StageIconType.SYSTEMIC_COLLAPSE -> Icons.Default.Warning
        StageIconType.VECTOR_TRANSMISSION -> Icons.Default.FlightTakeoff
        StageIconType.VIRAL_REPLICATION -> Icons.Default.Coronavirus
        StageIconType.NEMATODE_JUVENILE -> Icons.Default.BugReport
        StageIconType.ROOT_GALL -> Icons.Default.Spa
        StageIconType.SEED_BANK -> Icons.Default.Grain
        StageIconType.GERMINATION_EMERGENCE -> Icons.Default.Spa
        StageIconType.VEGETATIVE_ROSETTE -> Icons.Default.Grass
        StageIconType.FLOWERING_SEED_SET -> Icons.Default.Eco
        StageIconType.PERENNATION_DISPERSAL -> Icons.Default.FilterDrama
        StageIconType.SOIL_BASELINE -> Icons.Default.Landscape
        StageIconType.SOIL_DEGRADATION -> Icons.Default.Warning
        StageIconType.CRUSTING_EROSION -> Icons.Default.WaterDrop
        StageIconType.REMEDIATION_AMENDMENT -> Icons.Default.Engineering
        StageIconType.REHABILITATED_FERTILE -> Icons.Default.WorkspacePremium
    }
}
