package com.example.model

/**
 * Fertilizer application details for a specific phenological growth stage of a crop.
 */
data class StageFertilizerApplication(
    val stageLabel: String,
    val timingText: String,
    val recommendedBrand: String,
    val npkRatio: String,
    val ratePerHectare: String,
    val ratePerAcre: String,
    val ratePerPlantOrKnapsack: String,
    val placementAndMethod: String,
    val primaryFunction: String
)

/**
 * Detailed step-by-step crop production stage guideline from land prep to harvest.
 */
data class ProductionStageGuide(
    val stageNumber: Int,
    val stageName: String,
    val durationOrTiming: String,
    val comprehensiveNotes: String,
    val fertilizerRecommendation: StageFertilizerApplication?,
    val agronomicPractices: List<String>,
    val keyRiskFactors: String,
    val imageResId: Int? = null
)

/**
 * Post-harvest handling, curing, grading, packaging, and storage pest control.
 */
data class CropPostHarvestGuide(
    val maturityIndices: List<String>,
    val harvestingGuidelines: String,
    val curingAndFieldDrying: String,
    val gradingAndStandardization: List<String>,
    val packagingAndStorageCondition: String,
    val storagePestAndLossMitigation: String,
    val commercialValueAddition: String
)

/**
 * Complete agronomic production and library profile for a commercial crop.
 */
data class CropProductionProfile(
    val id: String,
    val cropName: String,
    val botanicalName: String,
    val commonKenyanNames: String,
    val category: CropCategory,
    val heroSummary: String,
    val imageResId: Int?,
    // Ecological requirements
    val ecologicalAltitudeMeters: String,
    val ecologicalRainfallMm: String,
    val optimalTemperatureCelsius: String,
    val preferredSoilTypeAndTexture: String,
    val optimalSoilPhRange: String,
    // Spacing & Population
    val spacingBetweenRowsCm: Int,
    val spacingWithinRowCm: Int,
    val plantPopulationPerAcre: String,
    val seedRatePerAcre: String,
    val averageYieldPerAcre: String,
    // Step-by-step production stages
    val stages: List<ProductionStageGuide>,
    // Post-harvest
    val postHarvest: CropPostHarvestGuide,
    val leadAgronomistNotes: String = "Curated by Lead Crop Scientist Odongo Vincent (0741886046 | odongovincento@gmail.com)"
)
