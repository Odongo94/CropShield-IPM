package com.example.model.soil

import androidx.compose.ui.graphics.Color

/**
 * 12 USDA Soil Texture Classification Classes with agronomic metadata,
 * characteristic colors, Kenyan soil taxonomy correlations, and physical attributes.
 */
enum class SoilTextureClass(
    val code: String,
    val displayName: String,
    val kenyanSoilEquivalent: String,
    val chartColor: Color,
    val highlightColor: Color,
    val cecRating: String, // Cation Exchange Capacity: Low, Medium, High, Very High
    val infiltrationMmPerHour: Double,
    val availableWaterCapacityMmPerM: Int, // mm/m of soil depth
    val generalDescription: String
) {
    CLAY(
        code = "C",
        displayName = "Clay Soil",
        kenyanSoilEquivalent = "Vertisols (Black Cotton Soils) & Heavy Nitisols",
        chartColor = Color(0xFFD9534F), // Rich Terracotta Crimson
        highlightColor = Color(0xFFFF6B6B),
        cecRating = "Very High (> 30 meq/100g)",
        infiltrationMmPerHour = 3.5,
        availableWaterCapacityMmPerM = 190,
        generalDescription = "Fine-textured soil composed of >40% clay particles. High nutrient retention and extreme plasticity when wet; prone to waterlogging, swelling, and deep cracking upon drying."
    ),
    SILTY_CLAY(
        code = "SiC",
        displayName = "Silty Clay",
        kenyanSoilEquivalent = "Fluvisols & Lacustrine alluvial deposits (Lake Victoria basin, Tana delta)",
        chartColor = Color(0xFFE07A5F), // Deep Warm Coral
        highlightColor = Color(0xFFFF8E72),
        cecRating = "High (25 - 35 meq/100g)",
        infiltrationMmPerHour = 4.5,
        availableWaterCapacityMmPerM = 200,
        generalDescription = "Smooth, dense soil with high silt and clay fractions. Holds moisture tenaciously with moderate compaction vulnerability; slow drainage requiring surface ridging."
    ),
    SANDY_CLAY(
        code = "SC",
        displayName = "Sandy Clay",
        kenyanSoilEquivalent = "Ferralsols with quartz sand admixture (Trans Nzoia, Uasin Gishu)",
        chartColor = Color(0xFFD08C5C), // Rust Amber
        highlightColor = Color(0xFFEAA777),
        cecRating = "Medium to High (20 - 30 meq/100g)",
        infiltrationMmPerHour = 6.0,
        availableWaterCapacityMmPerM = 160,
        generalDescription = "Sticky and gritty texture combination. Better internal drainage than pure clay while maintaining strong cation retention for prolonged feeding."
    ),
    CLAY_LOAM(
        code = "CL",
        displayName = "Clay Loam",
        kenyanSoilEquivalent = "Eutric Nitisols & Luvisols (Kiambu, Murang'a, Kirinyaga volcanic foot-slopes)",
        chartColor = Color(0xFFF2A65A), // Ochre Amber
        highlightColor = Color(0xFFFFBF7F),
        cecRating = "High (22 - 30 meq/100g)",
        infiltrationMmPerHour = 8.0,
        availableWaterCapacityMmPerM = 210,
        generalDescription = "Balanced heavy loam with 27-40% clay. Exceptional nutrient bank with favorable soil structure; premier soil for intensive horticulture when managed with organic matter."
    ),
    SILTY_CLAY_LOAM(
        code = "SiCL",
        displayName = "Silty Clay Loam",
        kenyanSoilEquivalent = "Gleysols & Alluvial valley bottoms (Nyando, Kano Plains)",
        chartColor = Color(0xFFB5739D), // Muted Plum Orchid
        highlightColor = Color(0xFFD492BC),
        cecRating = "High (20 - 28 meq/100g)",
        infiltrationMmPerHour = 7.0,
        availableWaterCapacityMmPerM = 215,
        generalDescription = "Velvety smooth soil with substantial clay and high silt. High water-holding capacity; susceptible to surface crusting if left bare without protective mulch."
    ),
    SANDY_CLAY_LOAM(
        code = "SCL",
        displayName = "Sandy Clay Loam",
        kenyanSoilEquivalent = "Ferric Acrisols & Arenic Luvisols (Machakos, Kitui undulating plains)",
        chartColor = Color(0xFFC9A227), // Desert Bronze
        highlightColor = Color(0xFFE5BE42),
        cecRating = "Medium (15 - 22 meq/100g)",
        infiltrationMmPerHour = 12.0,
        availableWaterCapacityMmPerM = 170,
        generalDescription = "Resilient gritty-clay blend. Easy root penetration, good aeration, and reliable moisture storage; responsive to mineral fertilizers and organic mulching."
    ),
    LOAM(
        code = "L",
        displayName = "Loam Soil",
        kenyanSoilEquivalent = "Humic Nitisols & Andosols (Mt. Kenya & Aberdare highlands, Kisii volcanic ridges)",
        chartColor = Color(0xFF4E9F3D), // Emerald Forest
        highlightColor = Color(0xFF6DC85A),
        cecRating = "Medium-High (18 - 25 meq/100g)",
        infiltrationMmPerHour = 15.0,
        availableWaterCapacityMmPerM = 200,
        generalDescription = "Ideal agronomic soil texture. Equal balance of sand, silt, and clay providing optimal aeration, water retention, and microbial activity for almost all crop species."
    ),
    SILT_LOAM(
        code = "SiL",
        displayName = "Silt Loam",
        kenyanSoilEquivalent = "Andosols (Volcanic Ash Soils of Nakuru, Naivasha, Mau Narok)",
        chartColor = Color(0xFF45B69C), // Teal Mint
        highlightColor = Color(0xFF67D8BE),
        cecRating = "Medium (15 - 22 meq/100g)",
        infiltrationMmPerHour = 13.0,
        availableWaterCapacityMmPerM = 220,
        generalDescription = "Floury, soft soil with excellent water reservoir. Highly fertile and friable; vulnerable to water and wind erosion if cultivated on steep inclines without terracing."
    ),
    SILT(
        code = "Si",
        displayName = "Silt Soil",
        kenyanSoilEquivalent = "Fluvial Silt Deposits & Riverine benches (Tana River Basin)",
        chartColor = Color(0xFF3BCEAC), // Vibrant Aqua
        highlightColor = Color(0xFF5FF0CE),
        cecRating = "Medium (12 - 18 meq/100g)",
        infiltrationMmPerHour = 10.0,
        availableWaterCapacityMmPerM = 225,
        generalDescription = "Very rare in pure form; composed of >80% silt grains. Extremely smooth feel; easily compacted into dense surface crusts that impede seedling emergence."
    ),
    SANDY_LOAM(
        code = "SL",
        displayName = "Sandy Loam",
        kenyanSoilEquivalent = "Ferralsols & Cambisols (Embu lower zones, Meru sandy belts, Kajiado plains)",
        chartColor = Color(0xFFE9C46A), // Golden Sun Sand
        highlightColor = Color(0xFFFFDC89),
        cecRating = "Low to Medium (10 - 15 meq/100g)",
        infiltrationMmPerHour = 25.0,
        availableWaterCapacityMmPerM = 140,
        generalDescription = "Light, warm, gritty soil that warms quickly in season. Rapid root development; requires regular organic amendments and split fertilizer applications to curb leaching."
    ),
    LOAMY_SAND(
        code = "LS",
        displayName = "Loamy Sand",
        kenyanSoilEquivalent = "Arenosols & coastal paleodunes (Kilifi, Kwale, Lamu coconut-cashew belts)",
        chartColor = Color(0xFFF4A261), // Warm Terracotta Sand
        highlightColor = Color(0xFFFFB87B),
        cecRating = "Low (5 - 10 meq/100g)",
        infiltrationMmPerHour = 45.0,
        availableWaterCapacityMmPerM = 100,
        generalDescription = "Coarse soil with 70-85% sand. High drainage and aeration, but rapid moisture depletion; drip irrigation and heavy mulch are essential for commercial yields."
    ),
    SAND(
        code = "S",
        displayName = "Sand Soil",
        kenyanSoilEquivalent = "Arenosols & Regosols (Garissa, Turkana, Coastal beach ridges)",
        chartColor = Color(0xFFF3C053), // Desert Sand
        highlightColor = Color(0xFFFFD575),
        cecRating = "Very Low (1 - 5 meq/100g)",
        infiltrationMmPerHour = 60.0,
        availableWaterCapacityMmPerM = 65,
        generalDescription = "Loose, single-grained soil with >85% sand particles. Virtually no nutrient buffering or moisture retention; nutrients leach rapidly without organic matter addition."
    )
}

/**
 * Ternary percentage coordinate for soil texture.
 * sand + silt + clay must equal 100.0%.
 */
data class SoilCoordinate(
    val sand: Double,
    val silt: Double,
    val clay: Double
) {
    val isValid: Boolean get() = (sand + silt + clay) in 99.0..101.0
}

/**
 * Crop suitability advice for a classified soil class.
 */
data class SoilCropSuitability(
    val highlySuitableCrops: List<String>,
    val moderateWithAmendments: List<String>,
    val unsuitableOrHighRisk: List<String>,
    val agronomicJustification: String
)

/**
 * Soil fertility guidance tailored to nutrient buffering and leaching tendencies.
 */
data class SoilFertilityAdvisory(
    val cecExplanation: String,
    val basalFertilizerStrategy: String,
    val topdressingSchedule: String,
    val organicManureTonsPerHa: Double,
    val phosphorusFixationRisk: String,
    val limingOrPhAdjustment: String,
    val micronutrientWatchlist: List<String>
)

/**
 * Water application and irrigation management for the classified soil.
 */
data class SoilWaterAdvisory(
    val infiltrationRateText: String,
    val availableWaterCapacityText: String,
    val recommendedIrrigationSystem: String,
    val irrigationFrequencyAndDuration: String,
    val waterloggingRisk: String,
    val drainageInfrastructureNeeded: String
)

/**
 * Routine field agronomic management practices.
 */
data class SoilRoutineActivity(
    val tillageMoistureWindow: String,
    val mulchingRecommendation: String,
    val compactionPrevention: String,
    val routineSoilTestingFrequency: String,
    val erosionControlMeasures: String
)

/**
 * Complete agronomic package for a classified soil type.
 */
data class SoilClassificationProfile(
    val soilClass: SoilTextureClass,
    val cropSuitability: SoilCropSuitability,
    val fertilityAdvisory: SoilFertilityAdvisory,
    val waterAdvisory: SoilWaterAdvisory,
    val routineActivities: SoilRoutineActivity
)

/**
 * Crop Rotation Families used in designing agronomic patterns.
 */
enum class CropRotationFamily(
    val title: String,
    val agronomicRole: String,
    val badgeColor: Color,
    val representativeCrops: List<String>,
    val nitrogenImpact: String, // Consumes Heavy, Replenishes, Consumes Light, Builds Humus
    val rootDepthType: String // Shallow, Medium, Deep, Taproot
) {
    HEAVY_FEEDER(
        title = "Heavy Feeders (Grains & Brassicas)",
        agronomicRole = "High nutrient demand; extracts deep nitrogen and potassium for heavy biomass.",
        badgeColor = Color(0xFFD9534F),
        representativeCrops = listOf("Maize", "Cabbage", "Irish Potatoes", "Broccoli", "Tomatoes", "Sorghum", "Spinach"),
        nitrogenImpact = "High Depletion (-N)",
        rootDepthType = "Medium-Deep (60-120 cm)"
    ),
    LEGUME_FIXER(
        title = "Legumes & Nitrogen Fixers",
        agronomicRole = "Symbiotic Rhizobia nodulation fixes atmospheric N; restores soil biological nitrogen reserve.",
        badgeColor = Color(0xFF2E7D32),
        representativeCrops = listOf("Common Beans", "Garden Peas", "Cowpeas", "Soybeans", "Desmodium", "Lucerne / Alfalfa", "Chickpeas"),
        nitrogenImpact = "Net Enrichment (+N)",
        rootDepthType = "Medium Taproot (45-90 cm)"
    ),
    LIGHT_FEEDER(
        title = "Light Feeders & Root Crops",
        agronomicRole = "Moderate nutrient demand; scavenges residual phosphorus; loosens subsoil and breaks crusts.",
        badgeColor = Color(0xFFF57C00),
        representativeCrops = listOf("Carrots", "Bulb Onions", "Garlic", "Beetroot", "Sweet Potatoes", "Cassava"),
        nitrogenImpact = "Low Depletion (-mild N)",
        rootDepthType = "Taproot / Tuberous (30-60 cm)"
    ),
    SOIL_BUILDER(
        title = "Cover Crops & Soil Conditioners",
        agronomicRole = "Produces high fibrous biomass; suppresses nematodes, halts erosion, and accumulates organic humus.",
        badgeColor = Color(0xFF0288D1),
        representativeCrops = listOf("Brachiaria Grass", "Sunn Hemp", "Forage Oats", "Grazing Vetch", "Buckwheat", "Mucuna"),
        nitrogenImpact = "High Organic Carbon (+C/N)",
        rootDepthType = "Fibrous & Deep Bio-drilling (90-150 cm)"
    )
}

/**
 * A single stage in a 4-season crop rotation pattern.
 */
data class CropRotationStage(
    val seasonIndex: Int,
    val seasonName: String,
    val family: CropRotationFamily,
    val selectedCrop: String,
    val targetFieldBenefit: String
)

/**
 * Diagnostic health score for a crop rotation sequence.
 */
data class RotationHealthEvaluation(
    val pestBreakScore: Int, // 0 - 100
    val nitrogenBalanceScore: Int, // 0 - 100
    val weedSuppressionScore: Int, // 0 - 100
    val overallRating: String, // Excellent, Good, Risky, Unsustainable
    val keyStrengths: List<String>,
    val alertsAndWarnings: List<String>,
    val recommendedAdjustments: List<String>
)
