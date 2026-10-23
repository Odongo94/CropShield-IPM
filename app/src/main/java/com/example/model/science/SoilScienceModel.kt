package com.example.model.science

import com.example.model.DeveloperInfo

/**
 * Soil pH classification categories based on standard edaphology.
 */
enum class SoilPhCategory(val label: String, val typicalRange: String, val colorHex: Long) {
    VERY_STRONGLY_ACIDIC("Very Strongly Acidic", "pH < 4.8", 0xFFD32F2F),
    STRONGLY_ACIDIC("Strongly Acidic", "pH 4.8 - 5.5", 0xFFF57C00),
    MODERATELY_ACIDIC("Moderately Acidic", "pH 5.6 - 6.2", 0xFFFBC02D),
    OPTIMAL_NEUTRAL("Near Neutral / Optimal", "pH 6.3 - 7.2", 0xFF388E3C),
    MODERATELY_ALKALINE("Moderately Alkaline", "pH 7.3 - 8.2", 0xFF1976D2),
    STRONGLY_ALKALINE_SODIC("Strongly Alkaline / Sodic", "pH > 8.2", 0xFF7B1FA2)
}

/**
 * Soil bio-indicator ecological profile associated with a specific weed species.
 */
data class SoilIndicatorProfile(
    val weedId: String,
    val commonName: String,
    val botanicalName: String,
    val soilPhCategory: SoilPhCategory,
    val soilPhRangeText: String, // e.g. "4.2 – 5.0 (Extreme Acidity)"
    val inferredSoilType: String, // e.g. "Humic Nitisol / Leached Volcanic Red Soil"
    val nutrientStatusSummary: String, // e.g. "Critically Nitrogen Deficient & Low Organic Matter"
    val physicalStructure: String, // e.g. "Compacted Plowpan, Low Infiltration"
    val deficienciesIndicated: List<String>, // e.g. ["Nitrogen (N)", "Available Phosphorus (P)", "Organic Carbon (OC)"]
    val toxicitiesOrExcesses: String, // e.g. "High Exchangeable Aluminum (Al3+) & Iron (Fe2+) Toxicity"
    val recommendedAmendments: List<String>, // e.g. ["Apply 2.5 t/ha Agricultural Lime (CaCO3)", "Incorporate 5-8 t/ha Well-Rotted Farmyard Manure"]
    val recommendedFertilizers: List<String>, // e.g. ["Minjingu Hyper Phosphate (contains 38% CaO buffer)", "Mavuno Planting NPK 10:26:10", "CAN 26% N"]
    val cropScientistExplanation: String // Curated by Odongo Vincent explaining the biochemical and ecological correlation
)

/**
 * Composite diagnostic report generated when one or more weeds are observed on a farm.
 */
data class SoilDiagnosticReport(
    val selectedWeeds: List<SoilIndicatorProfile>,
    val dominantPhCategory: SoilPhCategory,
    val estimatedPhRange: String,
    val inferredSoilTypes: List<String>,
    val diagnosedNutrientDeficiencies: List<String>,
    val diagnosedNutrientExcesses: List<String>,
    val diagnosedPhysicalConditions: List<String>,
    val urgentSoilAmendments: List<String>,
    val recommendedCommercialFertilizers: List<String>,
    val agronomicRehabilitationPlan: List<String>,
    val technicalAdvisorySignoff: String = "${DeveloperInfo.NAME} • ${DeveloperInfo.TITLE} (${DeveloperInfo.PHONE} | ${DeveloperInfo.EMAIL})"
)

/**
 * Educational and diagnostic models for the 5 scientific pillars.
 */
data class SoilSciencePillar(
    val title: String = "Soil Science & Edaphology",
    val kenyanSoilTaxonomy: List<KenyanSoilTypeInfo>,
    val chemicalParameters: List<SoilChemistryParam>,
    val limeRequirementGuidelines: String
)

data class KenyanSoilTypeInfo(
    val name: String, // e.g. "Nitisols (Red Volcanic Soils)"
    val distribution: String, // e.g. "Central Highlands, Kisii, Kericho, Mt. Kenya foothills"
    val physicalCharacteristics: String,
    val fertilityAndChemistry: String,
    val managementAdvice: String
)

data class SoilChemistryParam(
    val parameterName: String, // e.g. "Cation Exchange Capacity (CEC)"
    val optimalRange: String, // e.g. "15 - 25 cmol(+)/kg"
    val agriculturalSignificance: String,
    val deficiencyCorrection: String
)

data class CropPathologyPillar(
    val title: String = "Crop Pathology (Phytopathology)",
    val diseaseTriangleConcept: String,
    val pathogenClasses: List<PathogenClassInfo>,
    val fracFungicideGuide: List<FracGroupInfo>,
    val diseaseSeverityIndexFormula: String
)

data class PathogenClassInfo(
    val classification: String, // "Oomycetes", "Ascomycetes", "Bacteria", "Viruses", "Nematodes"
    val keyExamples: String, // e.g. "Phytophthora infestans (Late Blight), Pythium (Damping off)"
    val transmissionVectors: String,
    val chemicalAndBiologicalControls: String
)

data class FracGroupInfo(
    val fracCode: String, // e.g. "FRAC 4", "FRAC 11", "FRAC M03"
    val modeOfAction: String,
    val activeIngredients: String, // e.g. "Metalaxyl-M, Mefenoxam"
    val resistanceRisk: String // "High", "Medium", "Low / Multi-site"
)

data class EntomologyPillar(
    val title: String = "Agricultural Entomology & Insect Pest Science",
    val insectOrders: List<InsectOrderInfo>,
    val beneficialPredators: List<BeneficialOrganismInfo>,
    val iracInsecticideGuide: List<IracGroupInfo>,
    val economicThresholdFormula: String
)

data class InsectOrderInfo(
    val orderName: String, // e.g. "Lepidoptera (Moths & Butterflies)"
    val mouthpartsAndDamage: String,
    val majorKenyanPests: String,
    val scoutingAndIpmStrategy: String
)

data class BeneficialOrganismInfo(
    val name: String, // e.g. "Ladybird Beetles (Coccinellidae)"
    val targetPests: String, // e.g. "Aphids, Mealybugs, Soft Scales, Mites"
    val habitatConservation: String
)

data class IracGroupInfo(
    val iracCode: String, // e.g. "Group 4A", "Group 28"
    val targetSite: String,
    val activeIngredients: String,
    val resistanceManagement: String
)

data class AgronomyPillar(
    val title: String = "Agronomy & Crop Physiology",
    val physiologyPrinciples: List<CropPhysiologyPrinciple>,
    val plantingGeometryStandards: List<CropGeometryStandard>,
    val criticalPeriodWeedControl: String
)

data class CropPhysiologyPrinciple(
    val principle: String,
    val description: String,
    val fieldApplication: String
)

data class CropGeometryStandard(
    val cropName: String,
    val spacingCm: String,
    val populationPerHa: String,
    val populationPerAcre: String,
    val seedRateKgPerHa: String
)

data class SoilWaterConservationPillar(
    val title: String = "Soil & Water Conservation and Management",
    val engineeringStructures: List<ConservationStructureInfo>,
    val biologicalMeasures: List<BiologicalConservationInfo>,
    val moistureRetentionTechniques: List<MoistureTechniqueInfo>
)

data class ConservationStructureInfo(
    val structureName: String, // e.g. "Fanya Juu Terraces"
    val suitableSlopes: String,
    val constructionSpecifications: String,
    val maintenanceAndVegetation: String
)

data class BiologicalConservationInfo(
    val measureName: String, // e.g. "Push-Pull Technology"
    val scientificSpecies: String, // "Desmodium uncinatum + Pennisetum purpureum / Brachiaria"
    val mechanism: String,
    val coBenefits: String
)

data class MoistureTechniqueInfo(
    val technique: String, // e.g. "Zai Pits (Water-Harvesting Micro-Basins)"
    val specifications: String,
    val waterSavingEfficiency: String
)
