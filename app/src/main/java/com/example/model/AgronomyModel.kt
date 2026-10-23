package com.example.model

enum class FertilizerPhase(val displayName: String) {
    BASAL_PLANTING("Basal / Planting Application"),
    TOP_DRESSING_1("1st Top-Dressing (Vegetative Stage)"),
    TOP_DRESSING_2("2nd Top-Dressing (Bulbing / Flowering)"),
    FOLIAR_FERTIGATION("Foliar Nutrition & Fertigation")
}

data class FertilizerProduct(
    val id: String,
    val name: String,
    val brand: String, // Yara East Africa, MEA Fertilizers, Mavuno Fertilizers, Minjingu Mines, Haifa Group, Sympal
    val npkRatio: String, // e.g. "14:14:24 + 1.2S + 0.1B + 0.1Zn"
    val nPercent: Double,
    val pPercent: Double,
    val kPercent: Double,
    val secondaryNutrients: String, // Ca, Mg, S, B, Zn
    val phase: FertilizerPhase,
    val soilAcidImpact: String, // Neutral, Acidifying, Buffering/Liming
    val bagWeightKg: Int = 50,
    val standardRatePerHa: String,
    val decisionRule: String, // Why & when a farmer should choose this brand
    val priceRangeKes: String = "KES 3,800 - 5,500"
)

data class CropFertilizerPlan(
    val cropName: String,
    val botanicalName: String = "",
    val cropCategory: CropCategory,
    val targetNPerHa: Double, // kg N/ha
    val targetPPerHa: Double, // kg P2O5/ha
    val targetKPerHa: Double, // kg K2O/ha
    val recommendedBasalBrand: String,
    val basalRateKgPerHa: Double,
    val recommendedTopdressBrand: String,
    val topdressRateKgPerHa: Double,
    val splitSchedule: List<String>,
    val acidityAlert: String,
    val agronomicGuidance: List<String>
)

data class FertilizerCalculationResult(
    val farmSizeHectares: Double,
    val farmSizeAcres: Double,
    val cropName: String,
    val selectedBasalProduct: FertilizerProduct,
    val selectedTopdressProduct: FertilizerProduct,
    val basalKgTotal: Double,
    val basal50kgBags: Double,
    val topdressKgTotal: Double,
    val topdress50kgBags: Double,
    val totalNitrogenKg: Double,
    val totalPhosphateKg: Double,
    val totalPotassiumKg: Double,
    val applicationSchedule: List<String>,
    val soilAcidityManagement: String,
    val brandDecisionRationale: String
)

data class KnapsackFertilizerResult(
    val farmSizeHectares: Double,
    val farmSizeAcres: Double,
    val cropName: String,
    val knapsackTankCapacityLiters: Double,
    val sprayVolumeLitersPerHa: Double,
    val totalWaterLitersNeeded: Double,
    val totalKnapsackTanks: Double,
    val foliarDosePerLiterGrams: Double,
    val foliarDosePerTankGrams: Double,
    val totalFoliarFertilizerKg: Double,
    val selectedBasalProduct: FertilizerProduct,
    val selectedTopdressProduct: FertilizerProduct,
    val selectedFoliarProduct: FertilizerProduct,
    val basalKgTotal: Double,
    val basal50kgBags: Double,
    val topdressKgTotal: Double,
    val topdress50kgBags: Double,
    val totalNitrogenKg: Double,
    val totalPhosphateKg: Double,
    val totalPotassiumKg: Double,
    val applicationTimeline: List<String>,
    val soilAcidityAdvice: String,
    val knapsackCalibrationAdvice: String
)

enum class WeedType(val displayName: String) {
    BROADLEAF("Broadleaf Weed"),
    GRASS("Grassy Weed"),
    SEDGE("Sedge"),
    PARASITIC("Parasitic Weed")
}

enum class HerbicideTiming(val displayName: String) {
    PRE_EMERGENCE("Pre-Emergence (Soil-Applied Before Weed Erupts)"),
    POST_EMERGENCE("Post-Emergence (Foliar Contact / Systemic)"),
    NON_SELECTIVE_BURNDOWN("Non-Selective Total Burndown")
}

data class RegisteredHerbicide(
    val tradeName: String,
    val registrant: String,
    val activeIngredient: String,
    val formulation: String, // e.g. 480 SL, 960 EC, 75 WG, 537.5 SE
    val timing: HerbicideTiming,
    val selectivity: String, // e.g. Selective in Maize & Beans
    val dosagePerKnapsack: String, // e.g. "50ml - 60ml per 20L knapsack"
    val dosagePerHectare: String, // e.g. "1.5 L/ha"
    val applicationGuidelines: String,
    val pcpbRegNo: String = "",
    val perHaRate: String = dosagePerHectare // backward compatibility alias
)

data class WeedModel(
    val id: String,
    val commonName: String,
    val botanicalName: String,
    val localKenyanNames: List<String>, // e.g. Kayongo, Mukengeria, Kipkoleit, Ndago, Terere
    val type: WeedType,
    val isPerennial: Boolean,
    val cropsAttacked: List<String>,
    val competitiveImpact: String,
    val culturalManagement: List<String>,
    val registeredHerbicides: List<RegisteredHerbicide>,
    val imageResId: Int? = null,
    val soilIndicator: com.example.model.science.SoilIndicatorProfile? = null
)

data class KenyaAgriculturalCounty(
    val name: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    val primaryCrops: String
)

data class CurrentWeather(
    val temperatureCelsius: Double,
    val relativeHumidity: Int,
    val windSpeedKmh: Double,
    val windDirection: Double,
    val precipitationMm: Double,
    val weatherCode: Int,
    val conditionText: String
)

data class DailyForecastItem(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,
    val rainProbabilityMax: Int,
    val precipitationSumMm: Double,
    val weatherCode: Int,
    val conditionText: String
)

enum class SprayStatus(val label: String) {
    OPTIMAL("Optimal Spray Window"),
    MARGINAL("Marginal Conditions - Caution"),
    HAZARDOUS("Do Not Spray - Poor Conditions")
}

data class AgronomicWeatherAdvisory(
    val sprayStatus: SprayStatus,
    val sprayReason: String,
    val diseaseRiskAlert: String,
    val fertilizerTopdressWindow: String,
    val soilMoistureContext: String
)
