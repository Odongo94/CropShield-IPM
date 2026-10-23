package com.example.data.local

import com.example.data.ThreatCatalog
import com.example.data.local.entities.ChemicalSolutionEntity
import com.example.data.local.entities.IpmNonChemicalEntity
import com.example.data.local.entities.LifecycleStageEntity
import com.example.data.local.entities.ThreatEntity
import com.example.data.local.entities.ThreatWithDetails
import com.example.model.ChemicalRecommendation
import com.example.model.CropCategory
import com.example.model.CropThreat
import com.example.model.IpmPlan
import com.example.model.LifecycleStage
import com.example.model.StageIconType
import com.example.model.ThreatType
import com.example.model.VulnerabilityLevel

object ThreatDatabaseMapper {

    private const val DELIMITER = "|||"

    fun toDomainModel(details: ThreatWithDetails): CropThreat {
        val t = details.threat

        val stages = details.lifecycleStages
            .sortedBy { it.stageNumber }
            .map { s ->
                LifecycleStage(
                    stageNumber = s.stageNumber,
                    title = s.title,
                    scientificSub = s.scientificSub,
                    iconType = try {
                        StageIconType.valueOf(s.iconType)
                    } catch (e: Exception) {
                        StageIconType.SPORE
                    },
                    duration = s.duration,
                    vulnerability = try {
                        VulnerabilityLevel.valueOf(s.vulnerability)
                    } catch (e: Exception) {
                        VulnerabilityLevel.HIGH
                    },
                    description = s.description,
                    symptomsToSpot = s.symptomsToSpot,
                    targetAction = s.targetAction,
                    recommendedActiveIngredients = splitString(s.recommendedActiveIngredientsJson)
                )
            }

        val chemicals = details.chemicalSolutions.map { c ->
            ChemicalRecommendation(
                activeIngredient = c.activeIngredient,
                chemicalGroup = c.chemicalGroup,
                classification = c.classification,
                targetStage = c.targetStage,
                dosagePerKnapsack = c.dosagePerKnapsack,
                dosagePerHectare = c.dosagePerHectare,
                phiDays = c.phiDays,
                reiHours = c.reiHours,
                applicationTiming = c.applicationTiming,
                resistanceManagement = c.resistanceManagement,
                safetyPpe = splitString(c.safetyPpeJson)
            )
        }

        val cultural = details.nonChemicalSolutions
            .filter { it.category == "CULTURAL" }
            .map { it.measureDescription }

        val mechanical = details.nonChemicalSolutions
            .filter { it.category == "MECHANICAL" }
            .map { it.measureDescription }

        val biological = details.nonChemicalSolutions
            .filter { it.category == "BIOLOGICAL" }
            .map { it.measureDescription }

        val catalogThreat = ThreatCatalog.getThreatById(t.id)

        return CropThreat(
            id = t.id,
            name = t.name,
            scientificName = t.scientificName,
            type = if (t.type == "PEST") ThreatType.PEST else ThreatType.DISEASE,
            cropCategory = catalogThreat?.cropCategory ?: com.example.model.CropCategory.VEGETABLES,
            targetCrops = splitString(t.targetCropsJson),
            affectedParts = splitString(t.affectedPartsJson),
            severityLevel = t.severityLevel,
            summary = t.summary,
            diagnosticKeys = splitString(t.diagnosticKeysJson),
            favorableConditions = t.favorableConditions,
            imageResId = if (catalogThreat != null) catalogThreat.imageResId else t.imageResId,
            lifecycleStages = if (catalogThreat != null && catalogThreat.lifecycleStages.isNotEmpty()) catalogThreat.lifecycleStages else stages,
            ipmPlan = IpmPlan(
                culturalControls = if (cultural.isNotEmpty()) cultural else catalogThreat?.ipmPlan?.culturalControls ?: emptyList(),
                physicalMechanicalControls = if (mechanical.isNotEmpty()) mechanical else catalogThreat?.ipmPlan?.physicalMechanicalControls ?: emptyList(),
                biologicalControls = if (biological.isNotEmpty()) biological else catalogThreat?.ipmPlan?.biologicalControls ?: emptyList(),
                chemicalControls = if (chemicals.isNotEmpty()) chemicals else catalogThreat?.ipmPlan?.chemicalControls ?: emptyList(),
                economicThreshold = t.economicThreshold.ifBlank { catalogThreat?.ipmPlan?.economicThreshold ?: "" }
            ),
            scoutChecklist = splitString(t.scoutChecklistJson).ifEmpty { catalogThreat?.scoutChecklist ?: emptyList() },
            infonetPractices = catalogThreat?.infonetPractices ?: emptyList(),
            cabiBioProtections = catalogThreat?.cabiBioProtections ?: emptyList(),
            pcpbChemicals = catalogThreat?.pcpbChemicals ?: emptyList()
        )
    }

    fun toEntities(threat: CropThreat): ThreatDbBundle {
        val threatEntity = ThreatEntity(
            id = threat.id,
            name = threat.name,
            scientificName = threat.scientificName,
            type = threat.type.name,
            targetCropsJson = joinList(threat.targetCrops),
            affectedPartsJson = joinList(threat.affectedParts),
            severityLevel = threat.severityLevel,
            summary = threat.summary,
            diagnosticKeysJson = joinList(threat.diagnosticKeys),
            favorableConditions = threat.favorableConditions,
            economicThreshold = threat.ipmPlan.economicThreshold,
            scoutChecklistJson = joinList(threat.scoutChecklist),
            imageResId = threat.imageResId
        )

        val stageEntities = threat.lifecycleStages.map { s ->
            LifecycleStageEntity(
                threatId = threat.id,
                stageNumber = s.stageNumber,
                title = s.title,
                scientificSub = s.scientificSub,
                iconType = s.iconType.name,
                duration = s.duration,
                vulnerability = s.vulnerability.name,
                description = s.description,
                symptomsToSpot = s.symptomsToSpot,
                targetAction = s.targetAction,
                recommendedActiveIngredientsJson = joinList(s.recommendedActiveIngredients)
            )
        }

        val chemicalEntities = threat.ipmPlan.chemicalControls.map { c ->
            ChemicalSolutionEntity(
                threatId = threat.id,
                activeIngredient = c.activeIngredient,
                chemicalGroup = c.chemicalGroup,
                classification = c.classification,
                targetStage = c.targetStage,
                dosagePerKnapsack = c.dosagePerKnapsack,
                dosagePerHectare = c.dosagePerHectare,
                phiDays = c.phiDays,
                reiHours = c.reiHours,
                applicationTiming = c.applicationTiming,
                resistanceManagement = c.resistanceManagement,
                safetyPpeJson = joinList(c.safetyPpe)
            )
        }

        val nonChemicalEntities = mutableListOf<IpmNonChemicalEntity>()
        threat.ipmPlan.culturalControls.forEach {
            nonChemicalEntities.add(IpmNonChemicalEntity(threatId = threat.id, category = "CULTURAL", measureDescription = it))
        }
        threat.ipmPlan.physicalMechanicalControls.forEach {
            nonChemicalEntities.add(IpmNonChemicalEntity(threatId = threat.id, category = "MECHANICAL", measureDescription = it))
        }
        threat.ipmPlan.biologicalControls.forEach {
            nonChemicalEntities.add(IpmNonChemicalEntity(threatId = threat.id, category = "BIOLOGICAL", measureDescription = it))
        }

        return ThreatDbBundle(
            threat = threatEntity,
            stages = stageEntities,
            chemicals = chemicalEntities,
            nonChemicals = nonChemicalEntities
        )
    }

    private fun joinList(list: List<String>): String = list.joinToString(DELIMITER)

    private fun splitString(str: String): List<String> {
        if (str.isBlank()) return emptyList()
        return str.split(DELIMITER).map { it.trim() }.filter { it.isNotEmpty() }
    }
}

data class ThreatDbBundle(
    val threat: ThreatEntity,
    val stages: List<LifecycleStageEntity>,
    val chemicals: List<ChemicalSolutionEntity>,
    val nonChemicals: List<IpmNonChemicalEntity>
)
