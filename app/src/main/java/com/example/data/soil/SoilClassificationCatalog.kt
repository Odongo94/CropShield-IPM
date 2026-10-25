package com.example.data.soil

import com.example.model.soil.CropRotationFamily
import com.example.model.soil.CropRotationStage
import com.example.model.soil.RotationHealthEvaluation
import com.example.model.soil.SoilClassificationProfile
import com.example.model.soil.SoilCoordinate
import com.example.model.soil.SoilCropSuitability
import com.example.model.soil.SoilFertilityAdvisory
import com.example.model.soil.SoilRoutineActivity
import com.example.model.soil.SoilTextureClass
import com.example.model.soil.SoilWaterAdvisory

object SoilClassificationCatalog {

    /**
     * Authoritative USDA Soil Texture Classification Algorithm.
     * Takes sand%, silt%, clay% (normalized to 100%) and returns the exact SoilTextureClass.
     */
    fun classifySoil(sand: Double, silt: Double, clay: Double): SoilTextureClass {
        // Enforce normalization
        val total = sand + silt + clay
        val s = if (total > 0) (sand / total) * 100.0 else 33.33
        val si = if (total > 0) (silt / total) * 100.0 else 33.33
        val c = if (total > 0) (clay / total) * 100.0 else 33.34

        return when {
            // Sand: sand >= 85, silt + 1.5*clay < 15
            si + 1.5 * c < 15.0 -> SoilTextureClass.SAND
            // Loamy Sand: sand >= 70, silt + 2*clay < 30
            si + 2.0 * c < 30.0 && s >= 70.0 -> SoilTextureClass.LOAMY_SAND
            // Clay: clay >= 40, sand <= 45, silt < 40
            c >= 40.0 && s <= 45.0 && si < 40.0 -> SoilTextureClass.CLAY
            // Silty Clay: clay >= 40, silt >= 40
            c >= 40.0 && si >= 40.0 -> SoilTextureClass.SILTY_CLAY
            // Sandy Clay: clay >= 35, sand >= 45
            c >= 35.0 && s >= 45.0 -> SoilTextureClass.SANDY_CLAY
            // Silty Clay Loam: clay 27-40, sand <= 20
            c in 27.0..40.0 && s <= 20.0 -> SoilTextureClass.SILTY_CLAY_LOAM
            // Clay Loam: clay 27-40, sand 20-45, silt 15-53
            c in 27.0..40.0 && s in 20.0..45.0 -> SoilTextureClass.CLAY_LOAM
            // Sandy Clay Loam: clay 20-35, silt < 28, sand >= 45
            c in 20.0..35.0 && si < 28.0 && s >= 45.0 -> SoilTextureClass.SANDY_CLAY_LOAM
            // Silt: silt >= 80, clay < 12
            si >= 80.0 && c < 12.0 -> SoilTextureClass.SILT
            // Silt Loam: silt >= 50 && clay < 27, or silt 50-80 && clay < 12
            (si >= 50.0 && c < 27.0) || (si in 50.0..80.0 && c < 12.0) -> SoilTextureClass.SILT_LOAM
            // Loam: clay 7-27, silt 28-50, sand <= 52
            c in 7.0..27.0 && si in 28.0..50.0 && s <= 52.0 -> SoilTextureClass.LOAM
            // Sandy Loam: clay < 20, sand > 43-52, or silt < 50
            else -> SoilTextureClass.SANDY_LOAM
        }
    }

    /**
     * Polygon vertex coordinates in ternary (Sand, Silt, Clay) percentages for rendering the 12 USDA zones.
     */
    data class TriangleZonePolygon(
        val soilClass: SoilTextureClass,
        val vertices: List<SoilCoordinate>,
        val centerCoordinate: SoilCoordinate
    )

    val trianglePolygons: List<TriangleZonePolygon> = listOf(
        // 1. CLAY
        TriangleZonePolygon(
            soilClass = SoilTextureClass.CLAY,
            vertices = listOf(
                SoilCoordinate(sand = 45.0, silt = 0.0, clay = 55.0),
                SoilCoordinate(sand = 45.0, silt = 15.0, clay = 40.0),
                SoilCoordinate(sand = 20.0, silt = 40.0, clay = 40.0),
                SoilCoordinate(sand = 0.0, silt = 40.0, clay = 60.0),
                SoilCoordinate(sand = 0.0, silt = 0.0, clay = 100.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 20.0, silt = 20.0, clay = 60.0)
        ),
        // 2. SILTY CLAY
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SILTY_CLAY,
            vertices = listOf(
                SoilCoordinate(sand = 20.0, silt = 40.0, clay = 40.0),
                SoilCoordinate(sand = 0.0, silt = 40.0, clay = 60.0),
                SoilCoordinate(sand = 0.0, silt = 60.0, clay = 40.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 7.0, silt = 47.0, clay = 46.0)
        ),
        // 3. SANDY CLAY
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SANDY_CLAY,
            vertices = listOf(
                SoilCoordinate(sand = 65.0, silt = 0.0, clay = 35.0),
                SoilCoordinate(sand = 45.0, silt = 0.0, clay = 55.0),
                SoilCoordinate(sand = 45.0, silt = 20.0, clay = 35.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 52.0, silt = 7.0, clay = 41.0)
        ),
        // 4. CLAY LOAM
        TriangleZonePolygon(
            soilClass = SoilTextureClass.CLAY_LOAM,
            vertices = listOf(
                SoilCoordinate(sand = 45.0, silt = 15.0, clay = 40.0),
                SoilCoordinate(sand = 45.0, silt = 28.0, clay = 27.0),
                SoilCoordinate(sand = 20.0, silt = 53.0, clay = 27.0),
                SoilCoordinate(sand = 20.0, silt = 40.0, clay = 40.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 32.0, silt = 34.0, clay = 34.0)
        ),
        // 5. SILTY CLAY LOAM
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SILTY_CLAY_LOAM,
            vertices = listOf(
                SoilCoordinate(sand = 20.0, silt = 40.0, clay = 40.0),
                SoilCoordinate(sand = 20.0, silt = 53.0, clay = 27.0),
                SoilCoordinate(sand = 0.0, silt = 73.0, clay = 27.0),
                SoilCoordinate(sand = 0.0, silt = 60.0, clay = 40.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 10.0, silt = 56.0, clay = 34.0)
        ),
        // 6. SANDY CLAY LOAM
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SANDY_CLAY_LOAM,
            vertices = listOf(
                SoilCoordinate(sand = 80.0, silt = 0.0, clay = 20.0),
                SoilCoordinate(sand = 65.0, silt = 0.0, clay = 35.0),
                SoilCoordinate(sand = 45.0, silt = 20.0, clay = 35.0),
                SoilCoordinate(sand = 45.0, silt = 28.0, clay = 27.0),
                SoilCoordinate(sand = 52.0, silt = 28.0, clay = 20.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 60.0, silt = 13.0, clay = 27.0)
        ),
        // 7. LOAM
        TriangleZonePolygon(
            soilClass = SoilTextureClass.LOAM,
            vertices = listOf(
                SoilCoordinate(sand = 52.0, silt = 28.0, clay = 20.0),
                SoilCoordinate(sand = 45.0, silt = 28.0, clay = 27.0),
                SoilCoordinate(sand = 23.0, silt = 50.0, clay = 27.0),
                SoilCoordinate(sand = 43.0, silt = 50.0, clay = 7.0),
                SoilCoordinate(sand = 50.0, silt = 43.0, clay = 7.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 42.0, silt = 40.0, clay = 18.0)
        ),
        // 8. SILT LOAM
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SILT_LOAM,
            vertices = listOf(
                SoilCoordinate(sand = 23.0, silt = 50.0, clay = 27.0),
                SoilCoordinate(sand = 0.0, silt = 73.0, clay = 27.0),
                SoilCoordinate(sand = 0.0, silt = 88.0, clay = 12.0),
                SoilCoordinate(sand = 20.0, silt = 80.0, clay = 0.0),
                SoilCoordinate(sand = 50.0, silt = 50.0, clay = 0.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 18.0, silt = 67.0, clay = 15.0)
        ),
        // 9. SILT
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SILT,
            vertices = listOf(
                SoilCoordinate(sand = 20.0, silt = 80.0, clay = 0.0),
                SoilCoordinate(sand = 0.0, silt = 88.0, clay = 12.0),
                SoilCoordinate(sand = 0.0, silt = 100.0, clay = 0.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 7.0, silt = 89.0, clay = 4.0)
        ),
        // 10. SANDY LOAM
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SANDY_LOAM,
            vertices = listOf(
                SoilCoordinate(sand = 85.0, silt = 0.0, clay = 15.0),
                SoilCoordinate(sand = 80.0, silt = 0.0, clay = 20.0),
                SoilCoordinate(sand = 52.0, silt = 28.0, clay = 20.0),
                SoilCoordinate(sand = 50.0, silt = 50.0, clay = 0.0),
                SoilCoordinate(sand = 70.0, silt = 30.0, clay = 0.0),
                SoilCoordinate(sand = 70.0, silt = 15.0, clay = 15.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 67.0, silt = 22.0, clay = 11.0)
        ),
        // 11. LOAMY SAND
        TriangleZonePolygon(
            soilClass = SoilTextureClass.LOAMY_SAND,
            vertices = listOf(
                SoilCoordinate(sand = 90.0, silt = 0.0, clay = 10.0),
                SoilCoordinate(sand = 85.0, silt = 0.0, clay = 15.0),
                SoilCoordinate(sand = 70.0, silt = 15.0, clay = 15.0),
                SoilCoordinate(sand = 70.0, silt = 30.0, clay = 0.0),
                SoilCoordinate(sand = 85.0, silt = 15.0, clay = 0.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 80.0, silt = 12.0, clay = 8.0)
        ),
        // 12. SAND
        TriangleZonePolygon(
            soilClass = SoilTextureClass.SAND,
            vertices = listOf(
                SoilCoordinate(sand = 100.0, silt = 0.0, clay = 0.0),
                SoilCoordinate(sand = 85.0, silt = 15.0, clay = 0.0),
                SoilCoordinate(sand = 90.0, silt = 0.0, clay = 10.0)
            ),
            centerCoordinate = SoilCoordinate(sand = 92.0, silt = 5.0, clay = 3.0)
        )
    )

    /**
     * Comprehensive Agronomic Profiles for each USDA Soil Class.
     */
    val soilProfiles: Map<SoilTextureClass, SoilClassificationProfile> = mapOf(
        SoilTextureClass.CLAY to SoilClassificationProfile(
            soilClass = SoilTextureClass.CLAY,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Paddy Rice (Flooded)", "Sugar Cane", "Wheat", "Cabbage & Broccoli", "Fodder Sorghum", "Napier Grass / Rhodes Grass"),
                moderateWithAmendments = listOf("Maize (requires deep furrows/raised beds)", "French Beans (prone to Rhizoctonia root rot if waterlogged)", "Avocados (strictly on 30-50 cm ridges)"),
                unsuitableOrHighRisk = listOf("Carrots (root forking & stunted taproots)", "Irish Potatoes (tuber rot & severe digging compaction)", "Groundnuts (pegging failure in hard dry clay)"),
                agronomicJustification = "Clay retains immense moisture and nutrients but exhibits low aeration and high compaction when wet. Shallow and tuber crops suffer severe deformation unless planted on raised beds with heavy compost incorporation."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "CEC is very high (>30 meq/100g); holds potassium, calcium, and magnesium firmly against leaching.",
                basalFertilizerStrategy = "Apply basal DAP or NPK (e.g. 23:23:0) precisely in furrow bands. Avoid broadcasting to reduce phosphorus fixation by clay minerals.",
                topdressingSchedule = "Topdress CAN or Urea in 1 to 2 targeted doses when soil is moist but not saturated. Do not apply into standing water to avoid denitrification losses.",
                organicManureTonsPerHa = 15.0,
                phosphorusFixationRisk = "High P-Fixation: Acidic clay bonds with phosphate ions. Use organic manure or bio-fertilizers to buffer phosphate.",
                limingOrPhAdjustment = "Apply Agricultural Gypsum (Calcium Sulfate) at 1.5 - 2.5 t/ha if soil is alkaline/sodic (Vertisol), or Calcitic Lime at 2.0 t/ha if strongly acidic Nitisol (pH < 5.2).",
                micronutrientWatchlist = listOf("Zinc (Zn)", "Iron (Fe) in high pH Vertisols", "Boron (B)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Very Slow (1 - 5 mm/hr). High risk of surface pooling.",
                availableWaterCapacityText = "High (180 - 200 mm/m). Huge reservoir of moisture held against gravity.",
                recommendedIrrigationSystem = "Low-rate Drip Irrigation (0.8 - 1.2 L/hr emitters) or Furrow Irrigation on leveled basins.",
                irrigationFrequencyAndDuration = "Infrequent, long application cycles (e.g. Once every 4-6 days for 3-5 hours). Avoid short daily watering which causes surface puddling.",
                waterloggingRisk = "Critical Risk: Extended saturation suffocates roots and triggers Pythium damping-off and Phytophthora wilt.",
                drainageInfrastructureNeeded = "Construct broad-bed furrows (BBF), cambered beds, and deep field perimeter drainage ditches (45-60 cm depth)."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Extremely Narrow Window: Never plow when wet (creates massive indestructible plastic clods) or bone dry (too hard for implements). Cultivate only when friable (crumbly).",
                mulchingRecommendation = "Apply 5-8 cm dry grass or maize stover mulch to prevent surface cracking and preserve capillary moisture.",
                compactionPrevention = "Restrict heavy machinery; practice controlled traffic farming to avoid creating an impermeable subsoil plow pan.",
                routineSoilTestingFrequency = "Every 2 years for CEC, exchangeable sodium percentage (ESP), and active pH.",
                erosionControlMeasures = "Contour bunds with vetiver grass hedgerows on gentle slopes to stop sheet wash."
            )
        ),

        SoilTextureClass.CLAY_LOAM to SoilClassificationProfile(
            soilClass = SoilTextureClass.CLAY_LOAM,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Maize", "Tomatoes", "Cabbages & Kales", "French Beans", "Tea & Coffee", "Bananas", "Roses & Cut Flowers"),
                moderateWithAmendments = listOf("Irish Potatoes (requires ridging to loosen harvest zone)", "Sweet Potatoes", "Bulb Onions"),
                unsuitableOrHighRisk = listOf("Direct-seeded baby carrots (delicate radicle struggles without fine tilth)"),
                agronomicJustification = "Top-tier horticultural soil in East Africa. Possesses enough clay for nutrient exchange and water storage, with sufficient sand/silt for root aeration and friability."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "High CEC (22 - 30 meq/100g); resilient nutrient buffering capacity with minimal cation leaching.",
                basalFertilizerStrategy = "Standard basal DAP or NPK 17:17:17 at 150-200 kg/ha placed 5 cm beside and below the seed.",
                topdressingSchedule = "Split topdressing: 50% CAN at knee-high (V6 stage) and 50% at early tasseling/flowering.",
                organicManureTonsPerHa = 10.0,
                phosphorusFixationRisk = "Moderate to High in volcanic soils: Mix rock phosphate or DAP with compost to improve availability.",
                limingOrPhAdjustment = "Calcitic lime at 1.0 - 1.5 t/ha if pH drops below 5.5 to unlock bound calcium and magnesium.",
                micronutrientWatchlist = listOf("Boron (B)", "Molybdenum (Mo)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Moderate (5 - 12 mm/hr). Absorbs steady rains without excessive runoff.",
                availableWaterCapacityText = "Exceptional (200 - 220 mm/m). Superb moisture retention during dry spells.",
                recommendedIrrigationSystem = "Drip irrigation with 1.6 - 2.0 L/hr emitters or micro-sprinklers for nursery crops.",
                irrigationFrequencyAndDuration = "Irrigate every 2 to 3 days for 1.5 - 2 hours depending on evapo-transpiration.",
                waterloggingRisk = "Moderate: Only prolonged flash storms cause saturation in low spots.",
                drainageInfrastructureNeeded = "Shallow surface ditches along field margins; standard raised vegetable beds (15 cm height)."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Moderate Window: Work soil when top 10 cm can be squeezed into a ball that readily shatters when pressed.",
                mulchingRecommendation = "Mulch with wheat straw, coffee husks, or dry grass to conserve soil moisture and reduce weeding.",
                compactionPrevention = "Subsoil or rip every 3 seasons at 35 cm depth to break secondary plow pan.",
                routineSoilTestingFrequency = "Annual soil test before main planting season.",
                erosionControlMeasures = "Grass strips (Napier or Desmodium) spaced across slope contours."
            )
        ),

        SoilTextureClass.LOAM to SoilClassificationProfile(
            soilClass = SoilTextureClass.LOAM,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Maize", "Irish Potatoes", "Carrots & Beetroot", "Tomatoes & Capsicum", "French Beans & Peas", "Cut Flowers (Roses, Carnations)", "Avocados & Macadamia"),
                moderateWithAmendments = listOf("Paddy Rice (requires excessive water due to good drainage)"),
                unsuitableOrHighRisk = listOf("None: Loam is universally adaptable for virtually all temperate and tropical crops."),
                agronomicJustification = "The agronomic gold standard. Balanced porosity provides ideal root respiration, high capillary water retention, and excellent biological cycling of humus."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Optimal CEC (18 - 25 meq/100g); balanced retention of Ca++, Mg++, K+, and NH4+ cations.",
                basalFertilizerStrategy = "Apply balanced compound NPK (e.g. 17:17:17 or 15:15:15) at 175-225 kg/ha based on crop nutrient extraction.",
                topdressingSchedule = "Standard split applications of CAN (Calcium Ammonium Nitrate) or Urea during peak vegetative growth.",
                organicManureTonsPerHa = 8.0,
                phosphorusFixationRisk = "Low to Moderate: Phosphate is readily plant-accessible.",
                limingOrPhAdjustment = "Maintain target pH between 6.0 - 6.8 with maintenance liming (500 kg/ha lime every 3 years).",
                micronutrientWatchlist = listOf("Boron (B) for brassicas", "Manganese (Mn) for legumes")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Optimal (12 - 20 mm/hr). Rainwater infiltrates cleanly with minimal puddle formation.",
                availableWaterCapacityText = "High (190 - 210 mm/m). High percentage of plant-available water.",
                recommendedIrrigationSystem = "Drip irrigation, overhead central pivots, or rain gun systems.",
                irrigationFrequencyAndDuration = "Apply 25-30 mm of water every 3-4 days during peak vegetative and flowering stages.",
                waterloggingRisk = "Low: Natural percolation prevents stagnant water.",
                drainageInfrastructureNeeded = "Standard field contour furrows for stormwater redirection."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Wide Window: Easy cultivation across broad moisture levels without clodding or dust formation.",
                mulchingRecommendation = "Biodegradable organic mulching to maintain soil microbiome and regulate root zone temperature.",
                compactionPrevention = "Maintain minimum tillage or strip-till practices; rotate with deep-rooting cover crops.",
                routineSoilTestingFrequency = "Every 2-3 years for complete macro and micronutrient analysis.",
                erosionControlMeasures = "Standard contour plowing and cover crop canopy cover."
            )
        ),

        SoilTextureClass.SANDY_LOAM to SoilClassificationProfile(
            soilClass = SoilTextureClass.SANDY_LOAM,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Carrots & Sweet Potatoes", "Irish Potatoes", "Bulb Onions & Garlic", "Tomatoes", "Watermelon", "Groundnuts", "French Beans"),
                moderateWithAmendments = listOf("Maize (requires split nitrogen to prevent pale leaf chlorosis)", "Cabbage (requires heavy irrigation)"),
                unsuitableOrHighRisk = listOf("Paddy Rice (percolation rate too rapid to maintain standing water)"),
                agronomicJustification = "Fabulous soil for root and tuber development due to minimal mechanical resistance. Crops develop smooth, straight roots and clean tuber skins with zero harvesting injury."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Low to Moderate CEC (10 - 15 meq/100g); lower buffering capacity means nitrogen and potassium leach during heavy downpours.",
                basalFertilizerStrategy = "Place basal NPK bands close to roots. Use coated or slow-release fertilizer blends where available.",
                topdressingSchedule = "Split nitrogen into 3-4 smaller doses rather than 1-2 heavy bursts (e.g. 30 kg/ha CAN every 14 days).",
                organicManureTonsPerHa = 12.0,
                phosphorusFixationRisk = "Low P-Fixation: Phosphorus remains available, but mobile cations (K+, Mg++) leach rapidly.",
                limingOrPhAdjustment = "Light lime applications (500 - 800 kg/ha). Avoid over-liming which easily raises pH too fast in low-CEC soils.",
                micronutrientWatchlist = listOf("Magnesium (Mg)", "Sulfur (S)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Rapid (20 - 35 mm/hr). Water penetrates quickly with zero surface runoff.",
                availableWaterCapacityText = "Moderate (120 - 150 mm/m). Dries out significantly faster than clay or loam.",
                recommendedIrrigationSystem = "Drip irrigation with close emitter spacing (20-30 cm) and low flow rates (1.0 L/hr).",
                irrigationFrequencyAndDuration = "Frequent, short pulses (e.g. Daily or every 2 days for 45-60 minutes) to keep top 30 cm moist.",
                waterloggingRisk = "Very Low: Over-watering simply drains past root zone into deep subsoil.",
                drainageInfrastructureNeeded = "Minimal drainage required; focus instead on water retention basins and contour swales."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Very Wide: Can be tilled quickly after rain without sticking to tools or clodding.",
                mulchingRecommendation = "Critical Practice: Apply thick 8-10 cm mulch to shade soil, suppress evaporation, and build topsoil organic carbon.",
                compactionPrevention = "Wheel tracks can create subsurface compaction; incorporate crop residues annually.",
                routineSoilTestingFrequency = "Annual soil testing, specifically monitoring potassium and organic carbon.",
                erosionControlMeasures = "Windbreaks and vegetative cover to halt wind erosion during dry breezy periods."
            )
        ),

        SoilTextureClass.SAND to SoilClassificationProfile(
            soilClass = SoilTextureClass.SAND,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Watermelon & Melons", "Cassava", "Cashew Nuts & Coconut", "Groundnuts / Peanuts", "Sweet Potatoes (drought-tolerant varieties)", "Cowpeas"),
                moderateWithAmendments = listOf("Tomatoes (only under automated drip fertigation)", "Capsicum (requires constant feeding)", "Onions (high irrigation demand)"),
                unsuitableOrHighRisk = listOf("Maize (high risk of mid-season drought scorch)", "Cabbage & Brassicas (heavy water and nitrogen starvation)", "Paddy Rice"),
                agronomicJustification = "Pure sand has high macroporosity and low capillary suction. Nutrients and moisture wash straight through. Highly productive only under intensive drip fertigation or drought-hardy taproots."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Very Low CEC (1 - 5 meq/100g); practically no negative clay charges to hold ammonium, potassium, or calcium.",
                basalFertilizerStrategy = "Avoid applying heavy dry synthetic granules at planting. Apply minimal starter dose and rely on fertigation or spoon-fed soluble nutrients.",
                topdressingSchedule = "Frequent micro-doses: Apply small weekly or bi-weekly liquid fertilizer feeds (e.g. through drip lines).",
                organicManureTonsPerHa = 20.0,
                phosphorusFixationRisk = "Negligible P-Fixation, but high leaching of soluble orthophosphates into deep subsoil.",
                limingOrPhAdjustment = "Use Dolomitic Lime in micro-doses to supply both Calcium and Magnesium without shocking the pH.",
                micronutrientWatchlist = listOf("Boron (B)", "Copper (Cu)", "Iron (Fe)", "Manganese (Mn)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Excessive (> 50 mm/hr). Water drains straight through like a sieve.",
                availableWaterCapacityText = "Very Low (50 - 80 mm/m). Holds minimal plant-available water.",
                recommendedIrrigationSystem = "Automated Drip Irrigation with electronic timer or pulse controller.",
                irrigationFrequencyAndDuration = "Multiple short daily pulses (e.g. 2 to 3 pulses of 20-30 minutes each day) during hot afternoons.",
                waterloggingRisk = "Non-existent unless an impermeable subterranean hardpan exists.",
                drainageInfrastructureNeeded = "No surface drainage required; install sub-surface moisture retention barriers or biochar trenches."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Cultivatable anytime; very low traction resistance.",
                mulchingRecommendation = "Mandatory: Heavy organic mulch (grass, leaves, plastic mulch sheets) to reduce extreme root-zone heating and evaporation.",
                compactionPrevention = "Incorporate biochar, compost, and green manures (Sunn hemp, Mucuna) to bind sand grains into water-holding aggregates.",
                routineSoilTestingFrequency = "Twice per year (organic matter and active soluble salts).",
                erosionControlMeasures = "Live shelterbelts, perimeter hedges, and continuous ground cover to prevent wind sandstorms."
            )
        ),

        SoilTextureClass.SILT_LOAM to SoilClassificationProfile(
            soilClass = SoilTextureClass.SILT_LOAM,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Irish Potatoes", "Wheat & Barley", "Maize", "Carrots", "Peas & Beans", "Apples & Pears", "Pyrethrum"),
                moderateWithAmendments = listOf("Sugar Cane", "Cabbage (manage surface crusting after overhead irrigation)"),
                unsuitableOrHighRisk = listOf("None: Silt loam is among the most naturally productive agricultural soils globally."),
                agronomicJustification = "Characteristic of rich volcanic ash soils in the Rift Valley highlands. Superb available water capacity and crumbly tilth, though fragile when un-mulched against torrential rain impact."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Medium CEC (15 - 22 meq/100g); holds balanced nutrients well with steady mineralization.",
                basalFertilizerStrategy = "Standard basal DAP or NPK blend. Mix well with soil to stimulate early seedling root branching.",
                topdressingSchedule = "Standard 2-stage split topdressing with CAN or Urea during active vegetative elongation.",
                organicManureTonsPerHa = 10.0,
                phosphorusFixationRisk = "Moderate in volcanic Andosols: Apply organic humates to unlock fixed volcanic phosphate.",
                limingOrPhAdjustment = "Lime as dictated by soil pH test (usually 1.0 t/ha every 3-4 years in highland acid soils).",
                micronutrientWatchlist = listOf("Boron (B)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Moderate (10 - 18 mm/hr). Good water uptake when topsoil surface is un-crusted.",
                availableWaterCapacityText = "Highest of all textures (210 - 230 mm/m). Massive storage of plant-accessible water.",
                recommendedIrrigationSystem = "Low-pressure Drip or gentle fine-droplet overhead sprinklers.",
                irrigationFrequencyAndDuration = "Irrigate every 3-5 days; deep water reservoir supports crops through dry spells.",
                waterloggingRisk = "Low to Moderate in flat plains.",
                drainageInfrastructureNeeded = "Contour trenches and grassed waterways to safely channel torrential runoff on highland slopes."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Moderate Window: Avoid working when excessively wet to prevent surface smearing.",
                mulchingRecommendation = "Critical to prevent rain-drop impact crusting (soil capping) which blocks germinating seedlings.",
                compactionPrevention = "Avoid rotary tillers that over-pulverize silt into powder; prefer tine cultivators or disc harrows.",
                routineSoilTestingFrequency = "Every 2 years for pH, available P, and exchangeable bases.",
                erosionControlMeasures = "High Priority: Silt particles detach easily in runoff; install contour grass hedges and terracing."
            )
        ),

        SoilTextureClass.LOAMY_SAND to SoilClassificationProfile(
            soilClass = SoilTextureClass.LOAMY_SAND,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Watermelons", "Cassava", "Sweet Potatoes", "Groundnuts", "Cowpeas", "Cashews & Citrus"),
                moderateWithAmendments = listOf("Tomatoes (with heavy drip irrigation)", "Onions", "French Beans"),
                unsuitableOrHighRisk = listOf("Maize (high risk of water-stress abortion during tasseling)", "Paddy Rice"),
                agronomicJustification = "Light, fast-draining soil with just enough silt and clay (15-30%) to hold modest nutrients. Ideal for deep taproot species and early-market vegetables under irrigation."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Low CEC (5 - 10 meq/100g); prone to rapid leaching of nitrate and potassium during heavy rains.",
                basalFertilizerStrategy = "Place moderate basal fertilizer right in planting hole; combine with farmyard manure.",
                topdressingSchedule = "Split into 3 smaller topdressings to avoid nutrient loss through drainage.",
                organicManureTonsPerHa = 15.0,
                phosphorusFixationRisk = "Low P-Fixation; maintain continuous organic matter to boost cation retention.",
                limingOrPhAdjustment = "Apply small doses of dolomitic lime (600 kg/ha) if pH drops below 5.5.",
                micronutrientWatchlist = listOf("Magnesium (Mg)", "Boron (B)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Very Fast (35 - 50 mm/hr). Water drains rapidly below surface.",
                availableWaterCapacityText = "Low (80 - 110 mm/m). Requires steady replenishing.",
                recommendedIrrigationSystem = "Drip irrigation with 20-30 cm emitter spacing.",
                irrigationFrequencyAndDuration = "Daily or every other day in short 45-minute cycles.",
                waterloggingRisk = "Extremely Low.",
                drainageInfrastructureNeeded = "None needed; create retention ditches to capture seasonal rainfall."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Extremely wide window; easy pulling for draft animals and tractors.",
                mulchingRecommendation = "Thick grass mulch (8 cm) to preserve moisture and suppress weeds.",
                compactionPrevention = "Add green manure crops like Sunn Hemp or Velvet Bean to build stable humus.",
                routineSoilTestingFrequency = "Annual check of potassium and nitrogen reserves.",
                erosionControlMeasures = "Windbreaks and inter-row vegetative cover."
            )
        ),

        SoilTextureClass.SANDY_CLAY_LOAM to SoilClassificationProfile(
            soilClass = SoilTextureClass.SANDY_CLAY_LOAM,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Maize", "Sorghum", "Pigeon Peas", "Cowpeas", "Citrus & Mangoes", "Tomatoes", "Capsicum"),
                moderateWithAmendments = listOf("Carrots (slightly gritty for fine export carrots)", "Irish Potatoes"),
                unsuitableOrHighRisk = listOf("Paddy Rice"),
                agronomicJustification = "A workhorse agricultural soil in semi-arid and transitional agro-ecological zones. Sand provides aeration and water intake, while clay fractions lock in nutrients."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Medium CEC (15 - 22 meq/100g); reliable nutrient reservoir that holds applied fertilizers well.",
                basalFertilizerStrategy = "Standard basal DAP or NPK 23:23:0 at 150 kg/ha in seed furrow.",
                topdressingSchedule = "2-stage split application of CAN (at 4 weeks and 7 weeks after emergence).",
                organicManureTonsPerHa = 10.0,
                phosphorusFixationRisk = "Moderate: Responsive to soluble phosphates.",
                limingOrPhAdjustment = "Apply 1.0 t/ha lime if soil acidity drops below pH 5.4.",
                micronutrientWatchlist = listOf("Zinc (Zn)", "Boron (B)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Moderate-Fast (10 - 18 mm/hr). Absorbs rain without capping.",
                availableWaterCapacityText = "Good (150 - 180 mm/m). Moderate resilience to dry spells.",
                recommendedIrrigationSystem = "Drip irrigation or drag-hose sprinkler sets.",
                irrigationFrequencyAndDuration = "Irrigate every 2 to 3 days for 1.5 - 2 hours.",
                waterloggingRisk = "Low: Drains well unless subsoil has heavy clay layer.",
                drainageInfrastructureNeeded = "Contour furrows and shallow diversion ditches."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Fairly wide workability window.",
                mulchingRecommendation = "Mulch with sorghum or maize stover to reduce soil surface evaporation.",
                compactionPrevention = "Rip subsoil every 3 seasons to eliminate plow pan.",
                routineSoilTestingFrequency = "Every 2 years.",
                erosionControlMeasures = "Contour bunds and water-harvesting tie-ridges."
            )
        ),

        SoilTextureClass.SILTY_CLAY to SoilClassificationProfile(
            soilClass = SoilTextureClass.SILTY_CLAY,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Paddy Rice", "Sugar Cane", "Wheat", "Fodder Sorghum", "Cabbage"),
                moderateWithAmendments = listOf("Maize (requires raised beds)", "Soybeans (requires strict drainage)"),
                unsuitableOrHighRisk = listOf("Carrots", "Potatoes", "Groundnuts"),
                agronomicJustification = "Dense, heavy soil rich in fine silt and clay. Exceptional moisture holding, but sluggish drainage and poor aeration when wet."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "High CEC (25 - 35 meq/100g); very strong nutrient retention with zero leaching.",
                basalFertilizerStrategy = "Band-apply basal fertilizer to avoid phosphate immobilization.",
                topdressingSchedule = "Topdress in moist soil; avoid applying before heavy storm to prevent runoff.",
                organicManureTonsPerHa = 15.0,
                phosphorusFixationRisk = "High P-Fixation: Combine with compost or biological inoculants.",
                limingOrPhAdjustment = "Apply agricultural gypsum if sodic or calcitic lime if acidic.",
                micronutrientWatchlist = listOf("Iron (Fe)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Slow (3 - 6 mm/hr). Puddles easily.",
                availableWaterCapacityText = "High (190 - 210 mm/m). Holds abundant water.",
                recommendedIrrigationSystem = "Furrow basins or micro-drip with slow emitters.",
                irrigationFrequencyAndDuration = "Deep, infrequent irrigation every 5-7 days.",
                waterloggingRisk = "High: Standing water causes root asphyxiation.",
                drainageInfrastructureNeeded = "Deep field drains (50 cm depth) and cambered beds."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Narrow window: Work only when friable to avoid clods.",
                mulchingRecommendation = "Apply mulch to stop soil baking and surface crusting.",
                compactionPrevention = "Do not drive heavy machinery on wet soil.",
                routineSoilTestingFrequency = "Every 2 years.",
                erosionControlMeasures = "Drainage ditches and vegetative runoff buffers."
            )
        ),

        SoilTextureClass.SANDY_CLAY to SoilClassificationProfile(
            soilClass = SoilTextureClass.SANDY_CLAY,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Sugar Cane", "Maize", "Sorghum", "Bananas", "Napier Grass"),
                moderateWithAmendments = listOf("French Beans", "Tomatoes", "Cassava"),
                unsuitableOrHighRisk = listOf("Carrots", "Direct-seeded fine seeds"),
                agronomicJustification = "A gritty yet heavy clay soil. Better root aeration than pure clay, but can form a hard crust if allowed to dry rapidly after rain."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Medium to High CEC (20 - 30 meq/100g); holds cations and micronutrients well.",
                basalFertilizerStrategy = "Standard basal NPK application in seed furrow.",
                topdressingSchedule = "Split topdress at 4 and 8 weeks after planting.",
                organicManureTonsPerHa = 12.0,
                phosphorusFixationRisk = "Moderate to High.",
                limingOrPhAdjustment = "Apply lime as indicated by soil testing.",
                micronutrientWatchlist = listOf("Zinc (Zn)", "Boron (B)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Moderate-Slow (4 - 8 mm/hr).",
                availableWaterCapacityText = "Moderate-High (150 - 175 mm/m).",
                recommendedIrrigationSystem = "Drip irrigation or low-volume sprinklers.",
                irrigationFrequencyAndDuration = "Irrigate every 3-4 days.",
                waterloggingRisk = "Moderate risk in low-lying depressions.",
                drainageInfrastructureNeeded = "Contour ditches and furrows."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Moderate window.",
                mulchingRecommendation = "Mulch with field residues to prevent crusting.",
                compactionPrevention = "Subsoil ripping every 3 years.",
                routineSoilTestingFrequency = "Every 2 years.",
                erosionControlMeasures = "Contour bunds."
            )
        ),

        SoilTextureClass.SILTY_CLAY_LOAM to SoilClassificationProfile(
            soilClass = SoilTextureClass.SILTY_CLAY_LOAM,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Wheat", "Barley", "Maize", "Cabbage & Broccoli", "Soybeans", "Pyrethrum"),
                moderateWithAmendments = listOf("Irish Potatoes (requires high ridges)", "Beans"),
                unsuitableOrHighRisk = listOf("Direct-seeded carrots (surface capping hinders germination)"),
                agronomicJustification = "Fine, smooth soil with high moisture retention and nutrient capacity. Highly fertile but requires protection against rain crusting."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "High CEC (20 - 28 meq/100g); exceptional retention of applied nutrients.",
                basalFertilizerStrategy = "Band-apply basal DAP or NPK compound.",
                topdressingSchedule = "Split CAN topdressing at tillering/vegetative stages.",
                organicManureTonsPerHa = 12.0,
                phosphorusFixationRisk = "Moderate.",
                limingOrPhAdjustment = "Lime if acidic (1.0 t/ha).",
                micronutrientWatchlist = listOf("Molybdenum (Mo)", "Boron (B)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Moderate-Slow (5 - 9 mm/hr).",
                availableWaterCapacityText = "Very High (200 - 225 mm/m).",
                recommendedIrrigationSystem = "Drip or fine-mist overhead sprinklers.",
                irrigationFrequencyAndDuration = "Every 3-5 days in deep watering cycles.",
                waterloggingRisk = "Moderate.",
                drainageInfrastructureNeeded = "Surface drainage furrows."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Moderate window.",
                mulchingRecommendation = "Essential: Light mulch prevents raindrop capping.",
                compactionPrevention = "Avoid excessive pulverization during harrowing.",
                routineSoilTestingFrequency = "Every 2 years.",
                erosionControlMeasures = "Contour farming."
            )
        ),

        SoilTextureClass.SILT to SoilClassificationProfile(
            soilClass = SoilTextureClass.SILT,
            cropSuitability = SoilCropSuitability(
                highlySuitableCrops = listOf("Maize", "Wheat", "Peas", "Apples & Plums", "Vegetables (with mulch)"),
                moderateWithAmendments = listOf("Root crops (requires crust-breaking)"),
                unsuitableOrHighRisk = listOf("None, but prone to wind and water erosion."),
                agronomicJustification = "Silky smooth soil with maximum water holding capacity. Delicate structure easily destroyed by aggressive rotary tillage."
            ),
            fertilityAdvisory = SoilFertilityAdvisory(
                cecExplanation = "Medium CEC (12 - 18 meq/100g). Holds nutrients evenly.",
                basalFertilizerStrategy = "Standard basal fertilizer incorporation.",
                topdressingSchedule = "Split topdress during vegetative growth.",
                organicManureTonsPerHa = 12.0,
                phosphorusFixationRisk = "Low to Moderate.",
                limingOrPhAdjustment = "Light liming if needed.",
                micronutrientWatchlist = listOf("Boron (B)", "Zinc (Zn)")
            ),
            waterAdvisory = SoilWaterAdvisory(
                infiltrationRateText = "Moderate (8 - 14 mm/hr).",
                availableWaterCapacityText = "Highest (210 - 230 mm/m).",
                recommendedIrrigationSystem = "Drip irrigation.",
                irrigationFrequencyAndDuration = "Irrigate every 4-5 days.",
                waterloggingRisk = "Moderate in hollows.",
                drainageInfrastructureNeeded = "Contour drainage swales."
            ),
            routineActivities = SoilRoutineActivity(
                tillageMoistureWindow = "Moderate window; do not over-till.",
                mulchingRecommendation = "Mandatory mulch to prevent severe rain crusting.",
                compactionPrevention = "Incorporate organic matter to build soil crumb structure.",
                routineSoilTestingFrequency = "Every 2 years.",
                erosionControlMeasures = "Terracing and windbreaks."
            )
        )
    )

    fun getProfile(soilClass: SoilTextureClass): SoilClassificationProfile {
        return soilProfiles[soilClass] ?: soilProfiles.values.first()
    }

    /**
     * Preset Validated Agronomic Crop Rotation Patterns.
     */
    data class PresetRotationPattern(
        val name: String,
        val targetSoilGroup: String,
        val agronomicObjective: String,
        val stages: List<CropRotationStage>
    )

    val presetPatterns: List<PresetRotationPattern> = listOf(
        PresetRotationPattern(
            name = "Commercial Vegetable & Pulse Cycle",
            targetSoilGroup = "Loam, Sandy Loam, Clay Loam",
            agronomicObjective = "Breaks bacterial wilt, balances heavy nitrogen uptake with legume fixers, and conditions soil with deep roots.",
            stages = listOf(
                CropRotationStage(1, "Season 1 (Long Rains)", CropRotationFamily.HEAVY_FEEDER, "Cabbage / Broccoli", "Heavy biomass yield; consumes high initial NPK"),
                CropRotationStage(2, "Season 2 (Short Rains)", CropRotationFamily.LEGUME_FIXER, "French Beans / Garden Peas", "Rhizobia nodulation replenishes soil N; breaks brassica pests"),
                CropRotationStage(3, "Season 3 (Dry/Irri)", CropRotationFamily.LIGHT_FEEDER, "Bulb Onions / Garlic", "Scavenges residual P/K; allicin root exudates suppress nematodes"),
                CropRotationStage(4, "Season 4 (Cover Season)", CropRotationFamily.SOIL_BUILDER, "Sunn Hemp / Oats", "Fixes up to 120 kg N/ha; deep bio-drilling and weed suppression")
            )
        ),
        PresetRotationPattern(
            name = "Highland Cereal & Tuber Sequence",
            targetSoilGroup = "Silt Loam, Loam, Andosols",
            agronomicObjective = "Prevents potato cyst nematode (PCN) & late blight carryover, preserves volcanic friability.",
            stages = listOf(
                CropRotationStage(1, "Season 1 (Long Rains)", CropRotationFamily.HEAVY_FEEDER, "Irish Potatoes", "High tuber production in loose volcanic soil"),
                CropRotationStage(2, "Season 2 (Short Rains)", CropRotationFamily.LEGUME_FIXER, "Common Beans / Broad Beans", "Breaks solanaceous pathogen cycle (Phytophthora)"),
                CropRotationStage(3, "Season 3 (Dry/Irri)", CropRotationFamily.LIGHT_FEEDER, "Carrots / Beetroot", "Deep taproot utilizes subsoil nutrients; loosens compaction"),
                CropRotationStage(4, "Season 4 (Cover Season)", CropRotationFamily.SOIL_BUILDER, "Forage Oats / Grazing Vetch", "Protects against highland slope erosion; adds green organic carbon")
            )
        ),
        PresetRotationPattern(
            name = "Grain, Pulse & Fodder System",
            targetSoilGroup = "Sandy Clay Loam, Clay, Nitisols",
            agronomicObjective = "High staple food security, soil structure rejuvenation, and livestock fodder integration.",
            stages = listOf(
                CropRotationStage(1, "Season 1 (Long Rains)", CropRotationFamily.HEAVY_FEEDER, "Maize / Grain Sorghum", "Staple food grain; deep root biomass"),
                CropRotationStage(2, "Season 2 (Short Rains)", CropRotationFamily.LEGUME_FIXER, "Cowpeas / Desmodium", "Inter-season N enrichment; breaks stem borer pupation"),
                CropRotationStage(3, "Season 3 (Dry/Irri)", CropRotationFamily.LIGHT_FEEDER, "Sweet Potatoes / Cassava", "Drought-hardy starch; dense groundcover smothers weed seeds"),
                CropRotationStage(4, "Season 4 (Cover Season)", CropRotationFamily.SOIL_BUILDER, "Brachiaria Grass / Sunn Hemp", "Deep soil conditioning; unlocks bound phosphorus")
            )
        ),
        PresetRotationPattern(
            name = "Nematode & Bacterial Wilt Break Cycle",
            targetSoilGroup = "Sandy Loam, Loamy Sand, Sand",
            agronomicObjective = "Bio-fumigation of root-knot nematodes (Meloidogyne spp.) and suppression of soil-borne fungi.",
            stages = listOf(
                CropRotationStage(1, "Season 1 (Long Rains)", CropRotationFamily.SOIL_BUILDER, "Sunn Hemp / Mustard Green Manure", "Bio-fumigates soil with glucosinolates; kills nematode larvae"),
                CropRotationStage(2, "Season 2 (Short Rains)", CropRotationFamily.HEAVY_FEEDER, "Sweet Corn / Maize", "Feeds on decomposed organic nitrogen; non-host to brassica pests"),
                CropRotationStage(3, "Season 3 (Dry/Irri)", CropRotationFamily.LEGUME_FIXER, "Soybeans / Pigeon Peas", "Deep taproot N-fixation; mycorrhizal fungal colonization"),
                CropRotationStage(4, "Season 4 (Dry/Irri)", CropRotationFamily.LIGHT_FEEDER, "Carrots / Sweet Potatoes", "Produces smooth, clean roots in biologically cleansed soil")
            )
        )
    )

    /**
     * Diagnostic analysis engine for a custom 4-season crop rotation pattern.
     */
    fun evaluateRotationPattern(stages: List<CropRotationStage>): RotationHealthEvaluation {
        if (stages.size < 4) {
            return RotationHealthEvaluation(
                pestBreakScore = 50,
                nitrogenBalanceScore = 50,
                weedSuppressionScore = 50,
                overallRating = "Incomplete Pattern",
                keyStrengths = listOf("Pattern requires 4 full seasons for comprehensive analysis."),
                alertsAndWarnings = emptyList(),
                recommendedAdjustments = listOf("Configure all 4 seasons to calculate rotation metrics.")
            )
        }

        val families = stages.map { it.family }
        val crops = stages.map { it.selectedCrop }

        val strengths = mutableListOf<String>()
        val warnings = mutableListOf<String>()
        val adjustments = mutableListOf<String>()

        var pestScore = 75
        var nScore = 70
        var weedScore = 70

        // 1. Check for back-to-back same family or consecutive Solanaceae
        for (i in 0 until stages.size) {
            val current = stages[i]
            val next = stages[(i + 1) % stages.size]

            if (current.family == next.family && current.family != CropRotationFamily.SOIL_BUILDER) {
                pestScore -= 20
                warnings.add("Consecutive ${current.family.title} in Season ${current.seasonIndex} & Season ${next.seasonIndex} risks pest and pathogen buildup.")
                adjustments.add("Separate Season ${current.seasonIndex} and ${next.seasonIndex} with a Legume or Cover Crop.")
            }

            // Solanaceae check
            val isCurrentSolanaceous = current.selectedCrop.contains("Potato") || current.selectedCrop.contains("Tomato")
            val isNextSolanaceous = next.selectedCrop.contains("Potato") || next.selectedCrop.contains("Tomato")
            if (isCurrentSolanaceous && isNextSolanaceous) {
                pestScore -= 30
                warnings.add("CRITICAL PATHOGEN RISK: Consecutive Solanaceae (${current.selectedCrop} -> ${next.selectedCrop}) drastically increases Late Blight, Bacterial Wilt (Ralstonia), and Root-Knot Nematodes!")
                adjustments.add("Separate Solanaceous crops by at least 2 non-host seasons (e.g. Grass/Maize followed by Legume).")
            }
        }

        // 2. Check for Legume presence
        val hasLegume = families.contains(CropRotationFamily.LEGUME_FIXER)
        if (hasLegume) {
            nScore += 20
            strengths.add("Includes nitrogen-fixing legumes: Replenishes biological soil nitrogen through Rhizobia symbiotic nodulation.")
        } else {
            nScore -= 30
            warnings.add("No Legume in the rotation cycle: Soil will suffer chronic nitrogen deficit and require higher chemical topdressing.")
            adjustments.add("Introduce Common Beans, Peas, Cowpeas, or Desmodium in at least one season.")
        }

        // 3. Check for Cover Crop / Soil Builder
        val hasSoilBuilder = families.contains(CropRotationFamily.SOIL_BUILDER)
        if (hasSoilBuilder) {
            weedScore += 20
            pestScore += 10
            strengths.add("Features cover crop / green manure: Adds organic matter, breaks hardpans, and interrupts weed reproduction cycles.")
        } else {
            weedScore -= 10
            adjustments.add("Consider integrating a cover crop (Sunn hemp, Oats, or Brachiaria) to build soil organic carbon.")
        }

        // 4. Check for Heavy Feeder count
        val heavyFeederCount = families.count { it == CropRotationFamily.HEAVY_FEEDER }
        if (heavyFeederCount >= 3) {
            nScore -= 25
            warnings.add("Excessive Heavy Feeders ($heavyFeederCount out of 4 seasons): High nutrient exhaustion will deplete subsoil reserves.")
            adjustments.add("Limit Heavy Feeders to maximum 2 seasons per 4-season cycle.")
        } else if (heavyFeederCount in 1..2) {
            strengths.add("Balanced heavy nutrient extraction with recovery intervals.")
        }

        // Clamp scores between 0 and 100
        val finalPestScore = pestScore.coerceIn(10, 98)
        val finalNScore = nScore.coerceIn(15, 96)
        val finalWeedScore = weedScore.coerceIn(20, 95)

        val overallRating = when {
            finalPestScore >= 80 && finalNScore >= 75 -> "Excellent Agronomic Balance"
            finalPestScore >= 60 && finalNScore >= 55 -> "Good Sustainable Rotation"
            finalPestScore >= 45 -> "Moderate Risk Pattern"
            else -> "High Risk / Depleting Pattern"
        }

        if (strengths.isEmpty()) {
            strengths.add("Provides crop diversity across seasons.")
        }

        return RotationHealthEvaluation(
            pestBreakScore = finalPestScore,
            nitrogenBalanceScore = finalNScore,
            weedSuppressionScore = finalWeedScore,
            overallRating = overallRating,
            keyStrengths = strengths,
            alertsAndWarnings = warnings,
            recommendedAdjustments = adjustments
        )
    }
}
