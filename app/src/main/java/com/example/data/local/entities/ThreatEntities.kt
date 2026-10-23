package com.example.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "threats")
data class ThreatEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val scientificName: String,
    val type: String, // DISEASE or PEST
    val targetCropsJson: String, // Comma or JSON separated
    val affectedPartsJson: String,
    val severityLevel: String,
    val summary: String,
    val diagnosticKeysJson: String,
    val favorableConditions: String,
    val economicThreshold: String,
    val scoutChecklistJson: String,
    val imageResId: Int?
)

@Entity(
    tableName = "lifecycle_stages",
    foreignKeys = [
        ForeignKey(
            entity = ThreatEntity::class,
            parentColumns = ["id"],
            childColumns = ["threatId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["threatId"])]
)
data class LifecycleStageEntity(
    @PrimaryKey(autoGenerate = true)
    val stageId: Long = 0,
    val threatId: String,
    val stageNumber: Int,
    val title: String,
    val scientificSub: String,
    val iconType: String,
    val duration: String,
    val vulnerability: String,
    val description: String,
    val symptomsToSpot: String,
    val targetAction: String,
    val recommendedActiveIngredientsJson: String
)

@Entity(
    tableName = "chemical_solutions",
    foreignKeys = [
        ForeignKey(
            entity = ThreatEntity::class,
            parentColumns = ["id"],
            childColumns = ["threatId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["threatId"])]
)
data class ChemicalSolutionEntity(
    @PrimaryKey(autoGenerate = true)
    val chemicalId: Long = 0,
    val threatId: String,
    val activeIngredient: String,
    val chemicalGroup: String,
    val classification: String,
    val targetStage: String,
    val dosagePerKnapsack: String,
    val dosagePerHectare: String,
    val phiDays: Int,
    val reiHours: Int,
    val applicationTiming: String,
    val resistanceManagement: String,
    val safetyPpeJson: String
)

@Entity(
    tableName = "ipm_non_chemical_solutions",
    foreignKeys = [
        ForeignKey(
            entity = ThreatEntity::class,
            parentColumns = ["id"],
            childColumns = ["threatId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["threatId"])]
)
data class IpmNonChemicalEntity(
    @PrimaryKey(autoGenerate = true)
    val solutionId: Long = 0,
    val threatId: String,
    val category: String, // CULTURAL, MECHANICAL, BIOLOGICAL
    val measureDescription: String
)

data class ThreatWithDetails(
    @Embedded
    val threat: ThreatEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "threatId"
    )
    val lifecycleStages: List<LifecycleStageEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "threatId"
    )
    val chemicalSolutions: List<ChemicalSolutionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "threatId"
    )
    val nonChemicalSolutions: List<IpmNonChemicalEntity>
)
