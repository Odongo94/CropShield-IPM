package com.example.data

import com.example.R
import com.example.model.CropCategory
import com.example.model.CropPostHarvestGuide
import com.example.model.CropProductionProfile
import com.example.model.ProductionStageGuide
import com.example.model.StageFertilizerApplication

/**
 * Commercial Kenyan Cereal & Grain Crop Profiles (Wheat, Barley, Sorghum, Finger Millet, Rice).
 * Provides complete agronomic production guides from seedbed prep to post-harvest storage.
 */
object CerealCropProfiles {

    val list: List<CropProductionProfile> = listOf(
        // 1. WHEAT & BARLEY (Triticum aestivum & Hordeum vulgare)
        CropProductionProfile(
            id = "crop_wheat_barley",
            cropName = "Wheat & Barley (Ngano & Shayiri)",
            botanicalName = "Triticum aestivum L. / Hordeum vulgare L.",
            commonKenyanNames = "Ngano, Shayiri, Wheat, Malting Barley",
            category = CropCategory.CEREALS_GRAINS,
            heroSummary = "Major cool highland commercial grain crops (Narok, Uasin Gishu, Nakuru, Timau, Meru). Demands fine seedbed tilth, balanced phosphorus and copper at planting, timely nitrogen top-dressing at tillering, and vigilant scouting for Stem Rust (Ug99) and Yellow Stripe Rust.",
            imageResId = R.drawable.sample_cereal_rust,
            ecologicalAltitudeMeters = "1,800m - 2,800m ASL",
            ecologicalRainfallMm = "600mm - 1,000mm well-distributed during vegetative growth",
            optimalTemperatureCelsius = "15°C - 22°C (cool temperatures stimulate heavy tillering)",
            preferredSoilTypeAndTexture = "Deep, fertile loam or clay loam with neutral pH and high organic matter",
            optimalSoilPhRange = "6.0 - 7.5 (highly sensitive to aluminum toxicity in acidic soils)",
            spacingBetweenRowsCm = 20,
            spacingWithinRowCm = 5,
            plantPopulationPerAcre = "1,000,000 - 1,200,000 plants/acre (drill sown at 20cm row spacing)",
            seedRatePerAcre = "45 - 50 kg/acre (Certified seed: Kenya Wren, Robin, Hawk, Mwamba, Ngamia Barley)",
            averageYieldPerAcre = "18 - 30 bags (90kg bags) under sound agronomy",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Land Preparation, Drill Sowing & Basal Placement",
                    durationOrTiming = "At planting (onset of rains)",
                    comprehensiveNotes = "Create a medium-fine, weed-free seedbed using disc plough and harrow. Seed drill placement at 3-4cm depth. If soil is acidic (pH < 5.8), broadcast 1.5 t/ha agricultural lime 30 days prior. Add copper sulfate (5kg/ha) if copper deficient.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer at Sowing",
                        timingText = "Drill-placed with seed at sowing",
                        recommendedBrand = "YaraMila CEREAL (23-10-5 + S + Zn) or DAP (18-46-0) + MEA Cereal",
                        npkRatio = "23-10-5 or 18-46-0",
                        ratePerHectare = "150 - 180 kg/ha",
                        ratePerAcre = "60 - 75 kg/acre (1.2 to 1.5 x 50kg bags)",
                        ratePerPlantOrKnapsack = "Drill application (30g per 10m row)",
                        placementAndMethod = "Drilled 2-3cm below seed row using seed drill fertilizer box.",
                        primaryFunction = "Stimulates primary seminal root growth and early crown root initiation."
                    ),
                    agronomicPractices = listOf(
                        "Calibrate grain drill for accurate 50 kg/acre seed rate and 70 kg/acre fertilizer",
                        "Treat seed with thiram + carboxin or imidacloprid to prevent loose smut and wireworms",
                        "Ensure firm seedbed rolling if soil is loose to improve seed-to-soil moisture contact"
                    ),
                    keyRiskFactors = "Seed placed too deep (>5cm) causing poor emergence; Wireworms and cutworms severing seedlings."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Tillering & 1st Top-Dress (GS 21 - GS 29)",
                    durationOrTiming = "3 to 4 weeks after emergence (plants produce 3-5 tillers)",
                    comprehensiveNotes = "Wheat crowns produce lateral tillers that will form secondary grain ears. Weed competition must be eliminated early using selective broadleaf herbicides (e.g. 2,4-D or Buctril D).",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Tillering Top-Dress",
                        timingText = "At onset of tillering (soil moist)",
                        recommendedBrand = "CAN (26% N) or YaraBela SULFAN (24-0-0 + 6S)",
                        npkRatio = "26-0-0 or 24-0-0 + S",
                        ratePerHectare = "125 - 150 kg/ha",
                        ratePerAcre = "50 - 60 kg/acre (1 x 50kg bag)",
                        ratePerPlantOrKnapsack = "Broadcast evenly with tractor spinner spreader",
                        placementAndMethod = "Even broadcast across field during rain or before morning dew dries.",
                        primaryFunction = "Maximizes productive tiller number and builds thick leaf canopy."
                    ),
                    agronomicPractices = listOf(
                        "Spray selective post-emergence herbicide for broadleaf and grass weeds",
                        "Scout for Russian Wheat Aphid (Diuraphis noxia) causing purple leaf rolling streaks",
                        "Scout for Yellow Stripe Rust (Puccinia striiformis) yellow pustule stripes on leaves"
                    ),
                    keyRiskFactors = "Yellow stripe rust explosion during cool moist mornings; Russian wheat aphid toxin."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Stem Elongation, Flag Leaf & Grain Filling (GS 37 - GS 75)",
                    durationOrTiming = "7 to 10 weeks after emergence",
                    comprehensiveNotes = "The final top leaf (Flag Leaf) emerges. The flag leaf generates 75% of carbohydrates for grain filling! Protecting the flag leaf from Stem Rust (Puccinia graminis f.sp. tritici / Ug99) is decisive for grain yield.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Flag Leaf Foliar Micronutrient Boost",
                        timingText = "Flag leaf fully emerged to early milk stage",
                        recommendedBrand = "High-Potassium Foliar + Copper + Zinc (YaraVita Cereal Boost / Multi-K)",
                        npkRatio = "10-5-35 + Mg + Cu + Zn",
                        ratePerHectare = "2.5 L/ha in 200L water",
                        ratePerAcre = "1.0 L/acre",
                        ratePerPlantOrKnapsack = "50 ml per 20L knapsack sprayer",
                        placementAndMethod = "Boom sprayer mist applied during calm early morning hours.",
                        primaryFunction = "Maintains flag leaf stay-green and increases 1,000-grain test weight."
                    ),
                    agronomicPractices = listOf(
                        "Preventative or curative fungicide spray for Stem Rust (Tebuconazole + Azoxystrobin)",
                        "Inspect for Barley Yellow Dwarf Virus (BYDV) transmitted by cereal aphids",
                        "Maintain field drainage in valley bottoms to prevent lodging"
                    ),
                    keyRiskFactors = "Stem rust (Ug99) pustules rupturing stalks causing catastrophic lodging; Fusarium head blight."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Heads turn golden straw-yellow and droop slightly",
                    "Grain moisture content drops below 14%",
                    "Grains resist thumbnail indentation and crack cleanly between teeth"
                ),
                harvestingGuidelines = "Combine harvest on warm, clear, sunny days. Adjust combine cylinder speed and concave clearance to avoid grain cracking or throwing unthreshed heads into chaff.",
                curingAndFieldDrying = "If grain is harvested above 13.5% moisture, run through mechanical grain dryers (air temp < 45°C for malting barley to protect germination vigor).",
                gradingAndStandardization = listOf(
                    "Grade 1 Wheat: Hectolitre weight >= 76 kg/hl, moisture <= 13.0%, protein >= 12.5%, falling number > 250s",
                    "Malting Barley: Germination energy >= 95%, protein strictly 9.5% - 11.5%",
                    "Rejects: Sprouted grains, moldy/fusarium pink grains, insect bored grains"
                ),
                packagingAndStorageCondition = "Store in bulk grain silos with aeration fans or standard 90kg polypropylene bags stacked on pallets in clean dry warehouses.",
                storagePestAndLossMitigation = "Dust with Actellic Gold (Pirimiphos-methyl) or phosphine fumigation in sealed silos controls Granary Weevil (Sitophilus granarius).",
                commercialValueAddition = "Milling into baker's flour, semolina pasta, animal bran, and malting for brewing industry."
            )
        ),

        // 2. SORGHUM & FINGER MILLET (Sorghum bicolor & Eleusine coracana)
        CropProductionProfile(
            id = "crop_sorghum_millet",
            cropName = "Sorghum & Finger Millet (Mtama & Wimbi)",
            botanicalName = "Sorghum bicolor (L.) Moench / Eleusine coracana",
            commonKenyanNames = "Mtama, Wimbi, Sorghum, Finger Millet, Bulo",
            category = CropCategory.CEREALS_GRAINS,
            heroSummary = "Kenya's climate-resilient staple food and commercial brewing grains (Gadam, Serena, Sila, U-15). Highly drought-tolerant with C4 photosynthesis. Demands early weed control to suppress Striga parasitic weed and protection against head smut.",
            imageResId = R.drawable.sample_cereal_rust,
            ecologicalAltitudeMeters = "0m - 2,000m ASL (Thrives in semi-arid Eastern, Coast, and Rift Valley)",
            ecologicalRainfallMm = "400mm - 800mm (exceptional water-use efficiency)",
            optimalTemperatureCelsius = "24°C - 32°C",
            preferredSoilTypeAndTexture = "Wide adaptability; clay loam, sandy loam, or Vertisols with good aeration",
            optimalSoilPhRange = "5.5 - 7.8 (moderately tolerant of saline and alkaline soils)",
            spacingBetweenRowsCm = 60,
            spacingWithinRowCm = 20,
            plantPopulationPerAcre = "33,000 plants/acre (Sorghum); Millet broadcast or 30cm drills",
            seedRatePerAcre = "3 - 4 kg/acre (Sorghum); 2 - 3 kg/acre (Finger Millet)",
            averageYieldPerAcre = "15 - 25 bags (90kg bags) under sound management",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Land Prep, Sowing & Basal Application",
                    durationOrTiming = "At planting (onset of short/long rains)",
                    comprehensiveNotes = "Plough and harrow to a fine seedbed because seeds are small. Plant sorghum at 2.5cm depth and millet at 1-2cm depth. In acidic or low-fertility soils, basal phosphorus is essential for early root anchoring.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer at Planting",
                        timingText = "In planting furrows 5cm beside seed",
                        recommendedBrand = "YaraMila CEREAL (23-10-5) or DAP (18-46-0) / Minjingu Mazao",
                        npkRatio = "23-10-5 or 18-46-0",
                        ratePerHectare = "125 - 150 kg/ha",
                        ratePerAcre = "50 - 60 kg/acre (1 x 50kg bag)",
                        ratePerPlantOrKnapsack = "2 - 3 grams per planting station",
                        placementAndMethod = "Side-band along furrow and cover before dropping seed.",
                        primaryFunction = "Stimulates extensive fibrous root establishment to resist drought."
                    ),
                    agronomicPractices = listOf(
                        "Intercrop with Green Grams or Desmodium in Push-Pull to suppress Striga hermonthica",
                        "Thin sorghum to 1-2 vigorous seedlings per hill at 2 weeks after emergence",
                        "Maintain seed depth <= 2.5cm to avoid emergence failure"
                    ),
                    keyRiskFactors = "Seedlings buried too deep failing to emerge; Striga weed parasitizing root system."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Vegetative Growth & Top-Dressing",
                    durationOrTiming = "4 weeks after emergence (knee-high stage)",
                    comprehensiveNotes = "Plants expand rapidly and establish extensive deep root networks. Weeding is critical: young sorghum and millet seedlings are uncompetitive against invasive weeds.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative Top-Dress",
                        timingText = "At knee-high stage when soil is moist",
                        recommendedBrand = "CAN (26% N) or Urea (46% N) / YaraBela SULFAN",
                        npkRatio = "26-0-0 or 46-0-0",
                        ratePerHectare = "100 - 125 kg/ha",
                        ratePerAcre = "40 - 50 kg/acre",
                        ratePerPlantOrKnapsack = "3 grams per plant banded",
                        placementAndMethod = "Side-dress 10cm from base of plants and lightly hoe in.",
                        primaryFunction = "Promotes thick culm development and large panicle primordia."
                    ),
                    agronomicPractices = listOf(
                        "Clean weeding or shallow hoeing before canopy closes",
                        "Scout for Sorghum Shoot Fly (Atherigona soccata) causing dead hearts in young plants",
                        "Hand rogue any flowering Striga weeds before they set millions of seeds"
                    ),
                    keyRiskFactors = "Sorghum shoot fly larval dead heart; Quelea bird scouting."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Heading, Grain Milk Stage & Quelea Bird Guarding",
                    durationOrTiming = "8 to 14 weeks after planting",
                    comprehensiveNotes = "Compact or open panicles emerge and flower. Grains fill with starch through milk and dough stages. Red-billed Quelea birds (*Quelea quelea*) pose catastrophic flock destruction if unmonitored.",
                    fertilizerRecommendation = null,
                    agronomicPractices = listOf(
                        "Deploy active bird scaring from dawn to dusk during dough stage",
                        "Plant bird-resistant brown high-tannin varieties (e.g. Serena) in high-bird pressure zones",
                        "Inspect panicles for Head Smut (Sphacelotheca reiliana) and Sorghum Midge"
                    ),
                    keyRiskFactors = "Quelea quelea flock devouring entire fields in hours; Head smut converting grain to black dust."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Grains turn variety color (chalk white for brewing Gadam; bronze/brown for Serena/Wimbi)",
                    "Black dot appears at the hilum at base of grain",
                    "Grain moisture drops below 13%"
                ),
                harvestingGuidelines = "Cut panicles with sharp sickles. Do not pile wet panicles. Spread on clean tarpaulins in full sunlight for 3-5 days.",
                curingAndFieldDrying = "Thresh by beating panicles in sacks or running through stationary grain threshers when moisture is 12.5%. Winnow out chaff.",
                gradingAndStandardization = listOf(
                    "White Malting Sorghum (Gadam): Clean, zero tannins, moisture <= 12.5%, germination >= 90%",
                    "Brown Table Sorghum: Pure whole grain, insect-free",
                    "Finger Millet: Clean sifted grains, zero sand/stones, moisture <= 12.0%"
                ),
                packagingAndStorageCondition = "Store in multi-layer hermetic bags (PICS bags) or clean 90kg gunny bags on raised wooden pallets in a cool dry store.",
                storagePestAndLossMitigation = "Airtight hermetic bags kill grain weevils and flour beetles through oxygen depletion without chemical insecticides.",
                commercialValueAddition = "Commercial brewing malt (EABL Senator Keg), composite nutritious porridge flour (wimbi unga), and infant food mixes."
            )
        )
    )
}
