package com.example.data.science

import com.example.model.science.SoilDiagnosticReport
import com.example.model.science.SoilIndicatorProfile
import com.example.model.science.SoilPhCategory

object WeedSoilScienceCatalog {

    val allIndicators: List<SoilIndicatorProfile> = listOf(
        // 1. Striga (Witchweed)
        SoilIndicatorProfile(
            weedId = "striga",
            commonName = "Witchweed (Striga)",
            botanicalName = "Striga hermonthica / asiatica",
            soilPhCategory = SoilPhCategory.MODERATELY_ACIDIC,
            soilPhRangeText = "5.2 – 6.2 (Moderately Acidic)",
            inferredSoilType = "Degraded Leached Ferralsols & Arenosols (Common in Lake Basin, Western & Coastal lowlands)",
            nutrientStatusSummary = "Critically Nitrogen Deficient • Depleted Organic Matter & Phosphorus",
            physicalStructure = "Exhausted topsoil with depleted humus, high erosion vulnerability, and weak crumb stability",
            deficienciesIndicated = listOf(
                "Nitrogen (N) - Severely Depleted",
                "Available Phosphorus (P)",
                "Soil Organic Carbon (SOC < 1.2%)",
                "Microbial Biomass"
            ),
            toxicitiesOrExcesses = "Strigolactone root exudate accumulation stimulating parasitic haustoria attachment",
            recommendedAmendments = listOf(
                "Apply 6 – 10 tonnes/ha well-rotted cattle or poultry farmyard manure to rebuild soil organic carbon",
                "Implement Push-Pull intercropping with Desmodium uncinatum (Desmodium roots secrete allelochemicals that cause suicidal Striga germination and fix up to 100 kg N/ha)",
                "Rotate with trap crops (Soybeans, Cowpeas, Groundnuts, Cotton) for minimum 3 consecutive seasons"
            ),
            recommendedFertilizers = listOf(
                "Basal: Mavuno Planting NPK 10:26:10 + 4S + 8CaO + 4MgO + Micronutrients (200 kg/ha)",
                "Top-dressing: CAN 26% N (split-applied at knee-high and tasseling @ 150 kg/ha)",
                "Minjingu Hyper Phosphate (Rock Phosphate + 38% CaO buffer)"
            ),
            cropScientistExplanation = "Striga seed germination strictly depends on strigolactone hormones released by cereal roots under severe Nitrogen and Phosphorus starvation. When soil nitrogen levels are replenished and organic humus is restored, host roots cease excess strigolactone exudation, dramatically suppressing Striga attachment."
        ),

        // 2. Bracken Fern (Pteridium aquilinum)
        SoilIndicatorProfile(
            weedId = "bracken_fern",
            commonName = "Bracken Fern",
            botanicalName = "Pteridium aquilinum",
            soilPhCategory = SoilPhCategory.VERY_STRONGLY_ACIDIC,
            soilPhRangeText = "3.8 – 4.8 (Very Strongly Acidic)",
            inferredSoilType = "Humic Nitisol & Andosol of High-Altitude Highlands (Aberdares, Mau Forest buffer, Mt. Kenya foothills)",
            nutrientStatusSummary = "Severe Calcium & Magnesium Depletion • High Phosphorus Fixation",
            physicalStructure = "Leached, high rainfall hillside soil with strong subsoil acidity and exchangeable aluminum dominance",
            deficienciesIndicated = listOf(
                "Calcium (Ca) - Extreme Deficiency",
                "Available Phosphorus (P fixed into insoluble Aluminum Phosphate)",
                "Magnesium (Mg)",
                "Molybdenum (Mo)"
            ),
            toxicitiesOrExcesses = "High Exchangeable Aluminum (Al3+ > 30% cation saturation) and Manganese (Mn2+) Toxicity (causes root tip necrosis in cereals and legumes)",
            recommendedAmendments = listOf(
                "Broadcast 2.5 – 4.0 tonnes/ha of finely ground Agricultural Lime (CaCO3) or Dolomitic Lime (CaMg(CO3)2) 30 days prior to planting",
                "Incorporate lime thoroughly into the top 15–20 cm plow layer using disc harrowing",
                "Avoid using physiological acidifying fertilizers like DAP or straight Urea until soil pH rises above 5.8"
            ),
            recommendedFertilizers = listOf(
                "Minjingu Mazao NPK 10:20:10 + 5S + 20CaO (contains natural calcium buffer)",
                "YaraLiva Nitrabor (15.5% N + 26% CaO + 0.3% B) as non-acidifying top-dresser",
                "Triple Super Phosphate (TSP 46% P2O5) or Minjingu Rock Phosphate"
            ),
            cropScientistExplanation = "Bracken fern possesses specialized aluminum-tolerant root physiology and exclusively colonizes soils where severe leaching has stripped basic cations (Ca, Mg, K). Aluminum toxicity at this pH restricts regular crop root elongation, resulting in clubbed root systems and severe phosphorus starvation."
        ),

        // 3. Broadleaf Woodsorrel (Oxalis)
        SoilIndicatorProfile(
            weedId = "oxalis",
            commonName = "Broadleaf Woodsorrel (Oxalis)",
            botanicalName = "Oxalis latifolia",
            soilPhCategory = SoilPhCategory.STRONGLY_ACIDIC,
            soilPhRangeText = "4.8 – 5.5 (Strongly Acidic)",
            inferredSoilType = "Acid Volcanic Red Clay / Nitisol (High-potential tea, coffee, and horticultural zones)",
            nutrientStatusSummary = "Acid Soil with Phosphorus Lockup • Moderate Organic Matter • Low Calcium",
            physicalStructure = "Moist, fine-textured clay loam with good moisture capacity but constrained nutrient availability due to low pH",
            deficienciesIndicated = listOf(
                "Available Phosphorus (P)",
                "Calcium (Ca)",
                "Boron (B)",
                "Zinc (Zn)"
            ),
            toxicitiesOrExcesses = "Moderate Exchangeable Aluminum (Al3+) saturation (15% - 25%)",
            recommendedAmendments = listOf(
                "Apply 1.5 – 2.5 tonnes/ha Agricultural Lime (CaCO3) to neutralize exchangeable acidity",
                "Incorporate well-composted organic matter @ 5 tonnes/ha to chelate active aluminum and liberate bound phosphates",
                "Use repeated shallow cultivation during dry spells to expose and desiccate Oxalis underground bulbils"
            ),
            recommendedFertilizers = listOf(
                "Mavuno Vegetable Planting NPK 15:10:18 + Secondary Nutrients",
                "YaraBela Sulfan (24% N + 6% S + 10% CaO) to prevent nitrogen leaching while supplying calcium",
                "Single Super Phosphate (SSP 18% P2O5 + 12% S + 20% CaO)"
            ),
            cropScientistExplanation = "Oxalis species thrive in damp, fertile but chemically acidic soils. Its presence indicates that while soil physical structure and organic carbon are adequate, applied phosphates from standard DAP fertilizer will be rapidly fixed into insoluble iron and aluminum compounds unless limed."
        ),

        // 4. Stinging Nettle (Urtica dioica / Thabai)
        SoilIndicatorProfile(
            weedId = "stinging_nettle",
            commonName = "Stinging Nettle (Thabai)",
            botanicalName = "Urtica dioica",
            soilPhCategory = SoilPhCategory.OPTIMAL_NEUTRAL,
            soilPhRangeText = "6.2 – 7.2 (Near Neutral / Optimal)",
            inferredSoilType = "Rich Humic Andosol / Alluvial Loam (Old homestead areas, volcanic slopes, enriched forest borders)",
            nutrientStatusSummary = "Exceptionally High Natural Fertility • Abundant Nitrogen, Phosphorus & Active Humus",
            physicalStructure = "Deep, friable, well-aerated crumb loam with high cation exchange capacity (CEC > 25 cmol/kg) and rapid infiltration",
            deficienciesIndicated = listOf(
                "No primary macronutrient deficiencies",
                "Monitor for Potassium depletion if intensive vegetable harvesting occurs"
            ),
            toxicitiesOrExcesses = "High nitrate reserves; minimal salt or aluminum toxicity risks",
            recommendedAmendments = listOf(
                "Maintain optimal soil health through conservation tillage and crop residue retention",
                "Practice regular crop rotation to prevent specific horticultural disease build-up"
            ),
            recommendedFertilizers = listOf(
                "Balanced maintenance fertilization: YaraMila Power 14:14:24 @ 150 kg/ha",
                "CAN 26% N @ 100 kg/ha for leafy greens",
                "Organic bio-slurry or vermicompost"
            ),
            cropScientistExplanation = "Stinging nettle is the world's gold standard bio-indicator for rich, biologically active, high-nitrogen soils with balanced moisture and optimal neutral pH. Any cash crop, potato, tomato, or brassica will produce peak yields on soil colonized by nettles with minimal chemical inputs."
        ),

        // 5. Yellow & Purple Nutsedge (Cyperus esculentus / rotundus)
        SoilIndicatorProfile(
            weedId = "nutsedge",
            commonName = "Nutsedge / Watergrass (Ndago)",
            botanicalName = "Cyperus esculentus / rotundus",
            soilPhCategory = SoilPhCategory.MODERATELY_ACIDIC,
            soilPhRangeText = "5.6 – 7.0 (Moderate to Neutral)",
            inferredSoilType = "Vertisol (Black Cotton Soil) / Heavily Compacted Lowland Clay with Subsoil Hardpan",
            nutrientStatusSummary = "Anaerobic Soil Condition • Nitrogen Denitrification Loss • Iron Reduction",
            physicalStructure = "Severe compaction, surface water ponding, impermeable subsoil plowpan, poor aeration and oxygen starvation",
            deficienciesIndicated = listOf(
                "Available Nitrogen (lost via anaerobic bacterial denitrification)",
                "Oxygen in root zone (hypoxia / anoxia)",
                "Sulfur (S)"
            ),
            toxicitiesOrExcesses = "Ferrous iron (Fe2+) and sulfide accumulation under prolonged waterlogging",
            recommendedAmendments = listOf(
                "Deep subsoiling (ripping to 45–60 cm depth) during dry season to shatter plowpan and restore vertical drainage",
                "Construct raised planting beds (cambered beds 1.0–1.2 m wide) or deep perimeter drainage ditches",
                "Apply Gypsum (Calcium Sulfate @ 1.5 – 2.0 tonnes/ha) if soil has high sodium dispersibility (sodic vertisol)"
            ),
            recommendedFertilizers = listOf(
                "Use ammonium-based or slow-release nitrogen fertilizers to reduce denitrification losses",
                "MEA Basal NPK 23:23:0",
                "YaraBela Sulfan (supplies sulfate to counter anaerobic reduction)"
            ),
            cropScientistExplanation = "Nutsedge forms a dense underground network of basal bulbs and tubers that tolerate complete oxygen depletion. Its presence warns farmers of subsoil hardpan compaction and poor drainage, where root respiration is choked and valuable nitrogen fertilizer turns into dinitrogen gas."
        ),

        // 6. Pigweed / Slender Amaranth (Amaranthus hybridus / spinosus)
        SoilIndicatorProfile(
            weedId = "pigweed",
            commonName = "Pigweed / Slender Amaranth (Terere)",
            botanicalName = "Amaranthus hybridus / spinosus",
            soilPhCategory = SoilPhCategory.OPTIMAL_NEUTRAL,
            soilPhRangeText = "6.0 – 7.0 (Slightly Acidic to Neutral)",
            inferredSoilType = "Fertile Agricultural Loams & Nitisols with High Biological Activity",
            nutrientStatusSummary = "High Available Nitrogen & Phosphorus • Rich in Exchangeable Potassium",
            physicalStructure = "Deep, loose, fertile seedbed with moderate to high organic carbon and excellent rooting depth",
            deficienciesIndicated = listOf(
                "Mild Potassium drawdown under repeated continuous cropping",
                "Minor Zinc (Zn) in high-phosphorus spots"
            ),
            toxicitiesOrExcesses = "High nitrates; very low soil toxicities",
            recommendedAmendments = listOf(
                "Crop rotation with heavy-feeding brassicas or solanaceous vegetables",
                "Light mulching to maintain surface soil moisture"
            ),
            recommendedFertilizers = listOf(
                "YaraMila Winner 15:9:20 + Mg + B + Zn",
                "Mavuno Topdressing 26:0:0 + Secondary Nutrients",
                "Foliar Potassium Nitrate during fruiting stage"
            ),
            cropScientistExplanation = "Amaranthus species are nitrophilous weeds that accumulate nitrates rapidly. Their vigorous presence signals high soil nitrogen mineralization and warm, aerated topsoil ideal for demanding horticultural crops like tomatoes, cabbages, and capsicums."
        ),

        // 7. Blackjack (Bidens pilosa)
        SoilIndicatorProfile(
            weedId = "blackjack",
            commonName = "Blackjack (Muchege / Nyabaras)",
            botanicalName = "Bidens pilosa",
            soilPhCategory = SoilPhCategory.MODERATELY_ACIDIC,
            soilPhRangeText = "5.5 – 6.8 (Moderately Acidic to Neutral)",
            inferredSoilType = "Variable Loam to Clay Loam prone to surface capping and dry crusting",
            nutrientStatusSummary = "Moderate Nitrogen & Potassium • Prone to Surface Phosphorus Runoff",
            physicalStructure = "Crusted topsoil after heavy rain, surface sealing, moderate moisture retention",
            deficienciesIndicated = listOf(
                "Phosphorus (P) in seedling root zone",
                "Organic matter on surface crust"
            ),
            toxicitiesOrExcesses = "None significant",
            recommendedAmendments = listOf(
                "Apply organic surface mulching (maize stover or grass straw) to buffer raindrop impact and prevent soil capping",
                "Implement minimum tillage (ripping) with residue retention"
            ),
            recommendedFertilizers = listOf(
                "Standard balanced basal: DAP 18:46:0 or Mavuno Planting 10:26:10",
                "CAN 26% N split-applied"
            ),
            cropScientistExplanation = "Blackjack is an opportunistic colonizer of bare, disturbed topsoils with moderate fertility. Its presence indicates adequate baseline nutrition, but highlights soil vulnerability to surface compaction, erosion crusting, and rapid moisture evaporation."
        ),

        // 8. Wandering Jew (Commelina benghalensis)
        SoilIndicatorProfile(
            weedId = "wandering_jew",
            commonName = "Wandering Jew (Mukengeria)",
            botanicalName = "Commelina benghalensis",
            soilPhCategory = SoilPhCategory.MODERATELY_ACIDIC,
            soilPhRangeText = "5.5 – 6.5 (Moderately Acidic)",
            inferredSoilType = "Moist Alluvial Soils, Silt Loams, Riparian Basins & Humid Hillsides",
            nutrientStatusSummary = "High Moisture & Nitrogen Availability • Fungal Disease Reservoir",
            physicalStructure = "Shaded, continuously moist surface soil with high humidity retention and delayed morning drying",
            deficienciesIndicated = listOf(
                "Soil aeration during monsoon seasons",
                "Calcium in damp root zones"
            ),
            toxicitiesOrExcesses = "High fungal spore load (acts as alternate host for damping off and root rots)",
            recommendedAmendments = listOf(
                "Improve air drainage and canopy spacing of surrounding crops",
                "Dig perimeter runoff diversion furrows to drain excess standing surface moisture"
            ),
            recommendedFertilizers = listOf(
                "YaraLiva Tropicote (Calcium Nitrate) to strengthen plant cell walls against damp rot",
                "Avoid excessive raw nitrogen that induces soft, succulent foliage"
            ),
            cropScientistExplanation = "Commelina establishes on moist, shaded, fertile soils. While chemical fertility is usually favorable, its presence warns the agronomist of microclimatic conditions conducive to fungal pathogens like Late Blight and Rhizoctonia root rot."
        ),

        // 9. African Couch Grass (Digitaria scalarum)
        SoilIndicatorProfile(
            weedId = "couch_grass",
            commonName = "African Couch Grass (Lumbugu / Omusi)",
            botanicalName = "Digitaria scalarum",
            soilPhCategory = SoilPhCategory.STRONGLY_ACIDIC,
            soilPhRangeText = "4.9 – 5.8 (Strongly to Moderately Acidic)",
            inferredSoilType = "Leached Sandy Clay Loams, Weathered Ferralsols & Overworked Soils",
            nutrientStatusSummary = "Low Nitrogen • Depleted Potassium • High Root Zone Nutrient Competition",
            physicalStructure = "Compacted subsoil with high rhizomatous root mat binding the plow layer",
            deficienciesIndicated = listOf(
                "Nitrogen (N) - Severely Depleted by dense rhizome network",
                "Potassium (K)",
                "Available Moisture during grain fill"
            ),
            toxicitiesOrExcesses = "Allelopathic exudates that suppress neighboring crop germination",
            recommendedAmendments = listOf(
                "Deep moldboard plowing during hot dry periods to expose rhizomes to solar desiccation",
                "Integrate competitive green manure cover crops (Mucuna pruriens or Canavalia ensiformis) to smother couch grass",
                "Apply Agricultural Lime if pH is verified below 5.5"
            ),
            recommendedFertilizers = listOf(
                "Basal: Mavuno Planting 10:26:10 + 4S + 8CaO + 4MgO",
                "Top-dressing: CAN 26% N @ 175 kg/ha to out-compete residual grass competition"
            ),
            cropScientistExplanation = "Couch grass dominates continuously cropped, nutrient-depleted fields where traditional hoe weeding merely fragments rhizomes. It signals exhausted soil that requires systematic weed starvation and restorative fertility inputs."
        ),

        // 10. Mexican Marigold (Tagetes minuta)
        SoilIndicatorProfile(
            weedId = "mexican_marigold",
            commonName = "Mexican Marigold (Bhangi ya Kienyeji)",
            botanicalName = "Tagetes minuta",
            soilPhCategory = SoilPhCategory.MODERATELY_ACIDIC,
            soilPhRangeText = "5.4 – 6.6 (Moderately Acidic to Neutral)",
            inferredSoilType = "Drought-prone Sandy Loam, Degraded Hillside & Disturbed Fallow Land",
            nutrientStatusSummary = "Low Phosphorus & Nitrogen • High Root-Knot Nematode Pressure in Region",
            physicalStructure = "Coarse texture, low water-holding capacity, rapid drainage with propensity to drought stress",
            deficienciesIndicated = listOf(
                "Phosphorus (P)",
                "Nitrogen (N)",
                "Organic Matter (poor water retention)"
            ),
            toxicitiesOrExcesses = "Presence frequently correlates with sandy soil vulnerable to Meloidogyne root-knot nematodes",
            recommendedAmendments = listOf(
                "Chop and incorporate mature Tagetes foliage into soil as a bio-fumigant (releases natural alpha-terthienyl nematicide)",
                "Incorporate compost or biochar to increase soil water retention capacity"
            ),
            recommendedFertilizers = listOf(
                "Minjingu Mazao NPK 10:20:10",
                "YaraMila Cereal 23:10:5 + 3S + 0.3Zn"
            ),
            cropScientistExplanation = "Mexican Marigold colonizes dry, light-textured soils of low fertility. Interestingly, its root exudates and plant tissue contain natural bio-nematicides. Its spontaneous emergence often reflects an ecological response to nematode buildup in degraded soils."
        ),

        // 11. Common Purslane (Portulaca oleracea)
        SoilIndicatorProfile(
            weedId = "purslane",
            commonName = "Common Purslane",
            botanicalName = "Portulaca oleracea",
            soilPhCategory = SoilPhCategory.OPTIMAL_NEUTRAL,
            soilPhRangeText = "6.2 – 7.4 (Near Neutral to Mildly Alkaline)",
            inferredSoilType = "Rich Garden Loam, Sandy Loams with High Available Phosphorus & Potassium",
            nutrientStatusSummary = "Abundant Phosphorus & Potassium • Moderate Nitrogen • Drought Tolerant",
            physicalStructure = "Warm, well-drained, sandy loam with good pore space and excellent rooting aeration",
            deficienciesIndicated = listOf(
                "Moisture deficit during dry seasons (weed uses CAM photosynthesis to survive)",
                "Requires balanced nitrogen maintenance"
            ),
            toxicitiesOrExcesses = "Accumulates oxalates in tissue; minimal soil toxicity",
            recommendedAmendments = listOf(
                "Surface mulch with crop residues to conserve water in light sandy soils",
                "Standard crop rotation with grain legumes"
            ),
            recommendedFertilizers = listOf(
                "YaraMila Power 14:14:24 @ 150 kg/ha",
                "CAN 26% N for vegetative top-dressing"
            ),
            cropScientistExplanation = "Purslane is an indicator of biologically rich, phosphorus-abundant soils with excellent drainage and neutral pH. It thrives in high-value vegetable gardens and warm, well-managed horticultural beds."
        ),

        // 12. Sheep Sorrel / Curled Dock (Rumex spp.)
        SoilIndicatorProfile(
            weedId = "sheep_sorrel",
            commonName = "Sheep Sorrel / Curled Dock",
            botanicalName = "Rumex acetosella / crispus",
            soilPhCategory = SoilPhCategory.VERY_STRONGLY_ACIDIC,
            soilPhRangeText = "4.2 – 5.0 (Very Strongly Acidic)",
            inferredSoilType = "Severely Acidified, Leached Highland Clays or Wet Depleted Sands",
            nutrientStatusSummary = "Extreme Calcium Deficit • Low Nitrogen • High Soluble Aluminum",
            physicalStructure = "Poorly drained, damp, highly weathered subsoil with hardpan layering",
            deficienciesIndicated = listOf(
                "Calcium (Ca) - Severe",
                "Magnesium (Mg)",
                "Available Phosphorus (P)",
                "Nitrogen (N)"
            ),
            toxicitiesOrExcesses = "Soluble Aluminum (Al3+) and Iron (Fe2+) causing phosphorus fixation",
            recommendedAmendments = listOf(
                "Broadcast 3.0 – 4.5 tonnes/ha Agricultural Lime (CaCO3) or Dolomite",
                "Deep plowing and drainage improvement",
                "Add well-rotted farmyard manure @ 6 tonnes/ha"
            ),
            recommendedFertilizers = listOf(
                "Minjingu Hyper Phosphate (38% CaO buffer)",
                "Calcium Ammonium Nitrate (CAN 26% N)",
                "Avoid DAP and straight Urea"
            ),
            cropScientistExplanation = "Rumex species possess extreme tolerance to hydrogen ion activity (acidity) and aluminum saturation. Their extensive proliferation serves as a definitive ecological sign that standard crops will suffer severe stunting unless intensive agricultural liming is implemented."
        )
    )

    private val indicatorMap: Map<String, SoilIndicatorProfile> = allIndicators.associateBy { it.weedId }

    fun getIndicatorForWeed(weedId: String): SoilIndicatorProfile? {
        return indicatorMap[weedId]
    }

    /**
     * Synthesizes an aggregate Soil Chemistry and Fertility Diagnostic Report
     * based on multiple weeds observed on a farmer's shamba.
     */
    fun diagnoseSoilFromWeeds(weedIds: List<String>): SoilDiagnosticReport {
        val selectedProfiles = weedIds.mapNotNull { indicatorMap[it] }.ifEmpty {
            // Default to Striga + Oxalis sample if none specified
            listOf(indicatorMap["striga"]!!, indicatorMap["oxalis"]!!)
        }

        // Compute dominant pH category
        val phCategoryScores = selectedProfiles.groupBy { it.soilPhCategory }
        val dominantPhCategory = phCategoryScores.maxByOrNull { it.value.size }?.key ?: SoilPhCategory.STRONGLY_ACIDIC

        val estimatedPhRange = when (dominantPhCategory) {
            SoilPhCategory.VERY_STRONGLY_ACIDIC -> "pH 4.0 – 4.8 (Very Strongly Acidic • Urgent Liming Required)"
            SoilPhCategory.STRONGLY_ACIDIC -> "pH 4.9 – 5.5 (Strongly Acidic • Significant P-Fixation)"
            SoilPhCategory.MODERATELY_ACIDIC -> "pH 5.6 – 6.2 (Moderately Acidic • Standard Buffering Needed)"
            SoilPhCategory.OPTIMAL_NEUTRAL -> "pH 6.3 – 7.2 (Near Neutral • Optimal Nutrient Availability)"
            SoilPhCategory.MODERATELY_ALKALINE -> "pH 7.3 – 8.0 (Moderately Alkaline • Micronutrient Deficiencies Likely)"
            SoilPhCategory.STRONGLY_ALKALINE_SODIC -> "pH > 8.0 (Sodic / Saline • Gypsum Treatment Needed)"
        }

        val allSoilTypes = selectedProfiles.map { it.inferredSoilType }.distinct()
        val allDeficiencies = selectedProfiles.flatMap { it.deficienciesIndicated }.distinct()
        val allToxicities = selectedProfiles.map { it.toxicitiesOrExcesses }.distinct()
        val allPhysical = selectedProfiles.map { it.physicalStructure }.distinct()
        val allAmendments = selectedProfiles.flatMap { it.recommendedAmendments }.distinct()
        val allFertilizers = selectedProfiles.flatMap { it.recommendedFertilizers }.distinct()

        val rehabilitationPlan = mutableListOf<String>()

        // 1. Acidity & Chemical Correction
        if (dominantPhCategory == SoilPhCategory.VERY_STRONGLY_ACIDIC || dominantPhCategory == SoilPhCategory.STRONGLY_ACIDIC) {
            rehabilitationPlan.add("Phase 1 (Immediate Soil Chemistry Correction): Broadcast 2.5 - 3.5 t/ha Agricultural Lime (CaCO3) 30 days before the planting rains. Disc-harrow to 15-20 cm to neutralize toxic exchangeable aluminum and unlock fixed phosphorus.")
            rehabilitationPlan.add("Fertilizer Adjustment: Discontinue acidifying fertilizers (DAP 18:46:0, Urea 46% N) and transition immediately to Calcium Ammonium Nitrate (CAN) and Minjingu Hyper Phosphate.")
        } else if (dominantPhCategory == SoilPhCategory.OPTIMAL_NEUTRAL) {
            rehabilitationPlan.add("Soil Chemistry Maintenance: Soil pH is in the optimal 6.3 - 7.2 agronomic zone. Maintain balanced organic cycling and apply non-acidifying NPK blends.")
        } else {
            rehabilitationPlan.add("Soil Buffering: Apply moderate dolomitic lime (1.0 - 1.5 t/ha) or Minjingu Mazao to prevent gradual acidification.")
        }

        // 2. Organic Matter & Biological Restoration
        if (allDeficiencies.any { it.contains("Nitrogen", ignoreCase = true) || it.contains("Organic", ignoreCase = true) }) {
            rehabilitationPlan.add("Phase 2 (Organic Matter & Nitrogen Regeneration): Incorporate 5 - 8 tonnes/ha well-rotted farmyard manure or compost. Introduce legume green manures (Desmodium, Mucuna, or Cowpeas) into crop rotations to fix biological nitrogen.")
        }

        // 3. Physical Compaction & Drainage
        if (allPhysical.any { it.contains("Compacted", ignoreCase = true) || it.contains("Waterlog", ignoreCase = true) || it.contains("Hardpan", ignoreCase = true) }) {
            rehabilitationPlan.add("Phase 3 (Physical Soil Structure & Drainage Restoration): Perform deep ripping / subsoiling to 45 cm depth during dry season to break subsurface plowpan. Establish cambered beds and contour drainage furrows to eliminate waterlogging.")
        }

        // 4. Targeted Nutrient Balancing
        rehabilitationPlan.add("Phase 4 (Targeted Crop Nutrition): Apply recommended basal fertilizers in the planting furrow and split-apply nitrogen top-dressing at vegetative (knee-high) and flowering stages according to CropShield crop recommendations.")

        return SoilDiagnosticReport(
            selectedWeeds = selectedProfiles,
            dominantPhCategory = dominantPhCategory,
            estimatedPhRange = estimatedPhRange,
            inferredSoilTypes = allSoilTypes,
            diagnosedNutrientDeficiencies = allDeficiencies,
            diagnosedNutrientExcesses = allToxicities,
            diagnosedPhysicalConditions = allPhysical,
            urgentSoilAmendments = allAmendments,
            recommendedCommercialFertilizers = allFertilizers,
            agronomicRehabilitationPlan = rehabilitationPlan
        )
    }
}
