package com.example.data

import com.example.R
import com.example.model.CropCategory
import com.example.model.CropPostHarvestGuide
import com.example.model.CropProductionProfile
import com.example.model.ProductionStageGuide
import com.example.model.StageFertilizerApplication

/**
 * Comprehensive Crop Production Catalog providing complete agronomic guidelines
 * from land preparation to post-harvest handling for all commercial & food security crops in Kenya.
 * Features exact fertilizer formulations, NPK ratios, and application rates at every stage.
 */
object CropProductionCatalog {

    val crops: List<CropProductionProfile> = listOf(
        // 1. MAIZE (Zea mays)
        CropProductionProfile(
            id = "crop_maize",
            cropName = "Maize (Mahindi)",
            botanicalName = "Zea mays L.",
            commonKenyanNames = "Mahindi, Pocho, Bando, Kiumbu",
            category = CropCategory.CEREALS_GRAINS,
            heroSummary = "Kenya's primary staple cereal. High yields require balanced phosphorus at planting for root establishment, timely nitrogen top-dressing at knee-high and tasseling, and vigilant fall armyworm scouting.",
            imageResId = R.drawable.img_fall_armyworm,
            ecologicalAltitudeMeters = "1,200m - 2,400m ASL",
            ecologicalRainfallMm = "800mm - 1,500mm bi-modal rainfall",
            optimalTemperatureCelsius = "18°C - 28°C",
            preferredSoilTypeAndTexture = "Deep, well-drained fertile loam to sandy-clay loam",
            optimalSoilPhRange = "5.5 - 7.0 (Apply agricultural lime if pH < 5.2)",
            spacingBetweenRowsCm = 75,
            spacingWithinRowCm = 25,
            plantPopulationPerAcre = "21,333 plants/acre (1 seed per hill at 75cm x 25cm)",
            seedRatePerAcre = "10 kg/acre (Certified hybrid seed like H614D, H6213, DK777, SC Duma 43)",
            averageYieldPerAcre = "25 - 40 bags (90 kg bags) under sound agronomic management",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Land Preparation & Basal Application",
                    durationOrTiming = "3-4 weeks prior to onset of rains",
                    comprehensiveNotes = "Deep plough to 25-30cm depth to break hardpans, followed by harrowing to achieve a medium tilth. If soil is acidic (pH < 5.5), broadcast 500kg - 1,000kg/acre of agricultural lime 30 days before planting and harrow in. Open furrows 75cm apart.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal / Planting",
                        timingText = "At seed placement in furrow/hole",
                        recommendedBrand = "YaraMila CEREAL (23-10-5 + Mg + S + Zn) or DAP (18-46-0) / Mavuno Planting (10-26-10)",
                        npkRatio = "23-10-5 or 18-46-0",
                        ratePerHectare = "185 - 250 kg/ha",
                        ratePerAcre = "75 - 100 kg/acre (1.5 to 2 x 50kg bags)",
                        ratePerPlantOrKnapsack = "5 - 7 grams per planting hole (one soda bottle top full)",
                        placementAndMethod = "Place fertilizer 5cm away and 5cm below the seed; mix lightly with soil to prevent seed burn.",
                        primaryFunction = "Stimulates rapid embryonic root radicle elongation and early seedling vigor."
                    ),
                    agronomicPractices = listOf(
                        "Deep ploughing to aerate soil and eliminate dormant weeds",
                        "Secondary harrowing for medium seedbed tilth",
                        "Soil sampling and pH testing",
                        "Ridge construction in poorly drained plots to prevent waterlogging"
                    ),
                    keyRiskFactors = "Seed scorch from direct contact with concentrated DAP fertilizer; poor germination from cloddy seedbeds."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "First Top-Dressing (Knee-High Stage / V4-V6)",
                    durationOrTiming = "3 to 4 weeks after crop emergence (40-50 cm plant height)",
                    comprehensiveNotes = "Maize enters rapid vegetative expansion and initiates tassel primordia inside the growing point. Weeding must be conducted cleanly prior to fertilizer application to eliminate nutrient competition.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Top-Dress (Knee-High)",
                        timingText = "Crop at knee height (4-6 expanded leaves)",
                        recommendedBrand = "CAN (Calcium Ammonium Nitrate 26% N) or YaraVera AMIDAS (40-0-0 + 5.5S)",
                        npkRatio = "26-0-0 + 8% Ca or 40-0-0",
                        ratePerHectare = "185 - 200 kg/ha",
                        ratePerAcre = "75 - 80 kg/acre (1.5 x 50kg bags)",
                        ratePerPlantOrKnapsack = "4 - 5 grams per plant (ring placement 10cm from stalk)",
                        placementAndMethod = "Side-dress in a ring or band 10-15cm from maize stem; lightly cover with soil during weeding.",
                        primaryFunction = "Fuels chlorophyll synthesis, stalk thickness, and robust photosynthetic canopy formation."
                    ),
                    agronomicPractices = listOf(
                        "Thorough 1st weeding (mechanical or selective post-emergence herbicide like Harness/Auxo)",
                        "Thinning multiple seedlings to exactly 1 plant per station",
                        "Fall Armyworm scouting: inspect plant whorls for fresh frass and pin-hole damage"
                    ),
                    keyRiskFactors = "Fall armyworm whorl infestation; nitrogen volatilization if CAN is left uncovered in dry hot soil."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Second Top-Dressing (Tasseling / V10-V12)",
                    durationOrTiming = "7 to 8 weeks after planting (waist-high to early tassel)",
                    comprehensiveNotes = "Critical phase determining final ear size, cob length, and grain row count. Moisture stress or nitrogen starvation at this stage causes tip abortion and poor kernel set.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "2nd Top-Dress (Early Tassel)",
                        timingText = "Just before tassel emergence when soil is moist",
                        recommendedBrand = "CAN (26% N) or Urea (46% N) / YaraBela SULFAN (24-0-0 + 6S)",
                        npkRatio = "26-0-0 or 46-0-0",
                        ratePerHectare = "125 - 150 kg/ha",
                        ratePerAcre = "50 kg/acre (1 x 50kg bag)",
                        ratePerPlantOrKnapsack = "3 grams per plant in band along the furrow",
                        placementAndMethod = "Band placement between rows into moist soil; avoid applying in dry dry-spell without rain.",
                        primaryFunction = "Sustains stay-green canopy trait, maximizes silk emergence synchrony, and prevents cob tip barrenness."
                    ),
                    agronomicPractices = listOf(
                        "Light hilling up (earthing up) around stem base to improve lodging resistance",
                        "Scout for Gray Leaf Spot (Cercospora zeae-maydis) and Maize Streak Virus",
                        "Maintain rogueing of any chlorotic or virus-infected volunteer plants"
                    ),
                    keyRiskFactors = "Severe yield loss from stalk lodging in high winds if unhilled; stem borer tunnel damage."
                ),
                ProductionStageGuide(
                    stageNumber = 4,
                    stageName = "Grain Filling & Physiological Maturity",
                    durationOrTiming = "10 to 16 weeks after planting (milk stage to black layer formation)",
                    comprehensiveNotes = "Kernels accumulate starch until physiological maturity, marked by the formation of a dark black abscission layer at the kernel tip. Husks turn papery and dry, and leaves senesce naturally.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Foliar Grain Booster (Optional)",
                        timingText = "Post-silking / Early dough stage",
                        recommendedBrand = "High-Potassium Foliar Booster (e.g. YaraVita Crop Boost or Multi-K 13-0-46)",
                        npkRatio = "10-5-40 or 13-0-46",
                        ratePerHectare = "2.5 - 3.0 Litres/ha or 2.5 kg/ha",
                        ratePerAcre = "1.0 - 1.2 Litres/acre in 100L water",
                        ratePerPlantOrKnapsack = "50 ml / 20L knapsack sprayer",
                        placementAndMethod = "Foliar mist over upper leaves in calm morning hours.",
                        primaryFunction = "Accelerates carbohydrate translocation to grains, boosting individual test weight."
                    ),
                    agronomicPractices = listOf(
                        "Stooking (cutting and stacking in tepees) in high-altitude zones to accelerate drying",
                        "Inspect ears for Greater Grain Weevil and Ear Rot (Fusarium / Aspergillus)",
                        "Keep fields clean of late climbing weeds (e.g. wandering jew)"
                    ),
                    keyRiskFactors = "Aflatoxin infection from delayed harvesting in humid wet conditions; cob rot."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Black layer formation at base of kernel embryo",
                    "Dry, straw-colored papery husks tightly drooping downwards",
                    "Kernel moisture drop below 20% in the field",
                    "Kernels resist fingernail indentation"
                ),
                harvestingGuidelines = "De-husk cobs immediately upon plucking from dry stalks. Avoid throwing cobs directly onto damp soil; use clean tarpaulins to prevent fungal spore contamination.",
                curingAndFieldDrying = "Sun-dry cobs on raised wooden cribs or UV-treated tarpaulins until kernel moisture drops to 13.5% (tested using a calibrated moisture meter or the salt-jar test).",
                gradingAndStandardization = listOf(
                    "Grade 1: Clean whole grains, moisture <= 13.5%, aflatoxin < 10 ppb, insect damaged grains < 3%",
                    "Discard moldy, discolored, diseased, or broken kernels",
                    "Screen foreign matter, cob chaff, and stones"
                ),
                packagingAndStorageCondition = "Store in multi-layer hermetic bags (PICS / Super Grain Bags) tied airtight, or in well-ventilated galvanized metal silos raised 15cm off the floor on wooden pallets.",
                storagePestAndLossMitigation = "Dust grain with Actellic Gold Dust (Pirimiphos-methyl + Thiamethoxam) at 50g per 90kg bag if storing in conventional gunny bags; airtight hermetic bags kill weevils through oxygen depletion without chemical dust.",
                commercialValueAddition = "Milling into fortified sifted maize flour (unga), livestock feed bran (kienyeji mash), or industrial corn starch."
            )
        ),

        // 2. TOMATO (Solanum lycopersicum)
        CropProductionProfile(
            id = "crop_tomato",
            cropName = "Tomato (Nyanya)",
            botanicalName = "Solanum lycopersicum L.",
            commonKenyanNames = "Nyanya, Amatamat",
            category = CropCategory.VEGETABLES,
            heroSummary = "High-value commercial horticultural crop grown in open field and greenhouses. Demands systematic calcium nutrition to prevent blossom end rot and strict fungicide rotations against late blight.",
            imageResId = R.drawable.sample_late_blight,
            ecologicalAltitudeMeters = "0m - 2,000m ASL",
            ecologicalRainfallMm = "600mm - 1,200mm (requires supplemental drip irrigation)",
            optimalTemperatureCelsius = "20°C - 27°C (pollen aborts above 32°C)",
            preferredSoilTypeAndTexture = "Deep, friable sandy loam rich in organic matter",
            optimalSoilPhRange = "6.0 - 6.8",
            spacingBetweenRowsCm = 60,
            spacingWithinRowCm = 45,
            plantPopulationPerAcre = "10,000 - 12,000 plants/acre (single stem staked) or 8,000 (determinate field)",
            seedRatePerAcre = "30 - 50 grams/acre (hybrid seed like Zara F1, Anna F1, Terminator F1, Rio Grande)",
            averageYieldPerAcre = "25 - 40 tonnes/acre (open field); 60 - 80 tonnes/acre (greenhouse)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Nursery Propagation & Seedbed Raising",
                    durationOrTiming = "25 to 30 days before transplanting",
                    comprehensiveNotes = "Raise seedlings in peat moss / cocopeat propagation trays (128-cell trays) or raised sterile nursery beds. Drench with Trichoderma harzianum or metalaxyl to guard against Pythium damping off.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Nursery Starter Feed",
                        timingText = "14 days after germination",
                        recommendedBrand = "Soluble NPK 19-19-19 + Micronutrients or YaraMila UNIVERSAL",
                        npkRatio = "19-19-19 + TE",
                        ratePerHectare = "N/A (Nursery tray application)",
                        ratePerAcre = "500 grams per nursery block",
                        ratePerPlantOrKnapsack = "20 grams in 20L water sprayed over nursery trays",
                        placementAndMethod = "Foliar drench through fine rose can, washed off leaves with clean water.",
                        primaryFunction = "Builds dense fibrous root plug ready to withstand transplanting shock."
                    ),
                    agronomicPractices = listOf(
                        "Cover trays with 50% shade net",
                        "Harden seedlings 7 days before transplanting by reducing irrigation and lifting shade",
                        "Inspect for silverleaf whiteflies and Tuta absoluta leaf pinholes"
                    ),
                    keyRiskFactors = "Damping-off fungal complex; early viral inoculation by whitefly vectors."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Transplanting & Basal Establishment",
                    durationOrTiming = "Week 1 after field transplanting",
                    comprehensiveNotes = "Transplant sturdy 4-true-leaf seedlings in late afternoon into moist furrows or drip-irrigated planting holes. Add well-composted cattle/poultry manure (5-10 tonnes/acre) thoroughly decomposed.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer",
                        timingText = "At transplanting hole preparation",
                        recommendedBrand = "YaraMila WINNER (15-9-20 + Mg + S + B + Zn) or DAP (18-46-0) + MOP",
                        npkRatio = "15-9-20 or 18-46-0",
                        ratePerHectare = "200 - 250 kg/ha",
                        ratePerAcre = "80 - 100 kg/acre (2 x 50kg bags)",
                        ratePerPlantOrKnapsack = "8 - 10 grams per plant hole mixed with 2 handfuls of manure",
                        placementAndMethod = "Thoroughly mix fertilizer and manure with soil at bottom of hole, cover with 3cm topsoil before placing root ball.",
                        primaryFunction = "Provides slow-release phosphorus and balanced potassium for root system establishment."
                    ),
                    agronomicPractices = listOf(
                        "Drench root zone with Imidacloprid + Metalaxyl at transplanting",
                        "Install plastic mulch or straw mulch around crop lines to suppress soil-splash diseases",
                        "Ensure immediate drip irrigation within 1 hour of planting"
                    ),
                    keyRiskFactors = "Transplant wilt shock; cutworms severing young stems at soil level."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Vegetative Growth & Staking",
                    durationOrTiming = "Weeks 3 to 5 after transplanting",
                    comprehensiveNotes = "Plants establish rapid leafy canopies and side shoots. Indeterminate greenhouse varieties must be pruned to single or twin stems and trained along vertical twine. Erect sturdy wooden stakes in open field.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Top-Dress (Vegetative Booster)",
                        timingText = "21 days after transplanting",
                        recommendedBrand = "CAN (Calcium Ammonium Nitrate 26% N) or YaraLiva NITRABOR (15.4-0-0 + 25.6 CaO + 0.3 B)",
                        npkRatio = "15.4-0-0 + Boron + Calcium",
                        ratePerHectare = "150 - 200 kg/ha",
                        ratePerAcre = "60 - 80 kg/acre",
                        ratePerPlantOrKnapsack = "6 - 8 grams per plant in ring 15cm from stem",
                        placementAndMethod = "Ring application covered with soil; water thoroughly.",
                        primaryFunction = "Provides readily absorbable nitrate nitrogen and soluble calcium for cell wall elongation."
                    ),
                    agronomicPractices = listOf(
                        "Pruning indeterminate suckers from leaf axils once weekly",
                        "Treillising / tying stems with soft sisal twine or tomato clips",
                        "Preventative copper hydroxide / mancozeb sprays against Early and Late Blight"
                    ),
                    keyRiskFactors = "Early blight leaf concentric rings; Tuta absoluta serpentine leaf mines."
                ),
                ProductionStageGuide(
                    stageNumber = 4,
                    stageName = "Flowering & Fruit Setting (Calcium & Boron Phase)",
                    durationOrTiming = "Weeks 6 to 9 after transplanting",
                    comprehensiveNotes = "Abundant flowering trusses emerge. Calcium and Boron availability is decisive: calcium deficiency directly causes black sunken leather rot on fruit blossom ends (Blossom End Rot).",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Flowering & Calcium Boost",
                        timingText = "At first flower cluster opening, repeated every 14 days",
                        recommendedBrand = "YaraLiva NITRABOR alternating with Foliar Cal-Bor (e.g. Stopit or Calmax)",
                        npkRatio = "15-0-0 + 26% CaO + 0.3% B",
                        ratePerHectare = "150 kg/ha granular + 3L/ha foliar",
                        ratePerAcre = "60 kg/acre soil + 1.2L foliar Cal-Bor",
                        ratePerPlantOrKnapsack = "5 grams per plant soil; 50ml Cal-Bor per 20L knapsack foliar",
                        placementAndMethod = "Soil ring dressing accompanied by bi-weekly early morning foliar calcium spray on flowers.",
                        primaryFunction = "Boron assists pollen tube viability and pollination; calcium solidifies fruit pectin walls."
                    ),
                    agronomicPractices = listOf(
                        "Regular drip irrigation to maintain uniform soil moisture (fluctuating drought causes blossom end rot)",
                        "Deploy Delta pheromone traps for Tuta absoluta monitoring (4 traps/acre)",
                        "De-leaf senescing lower bottom leaves to promote air ventilation and lower humidity"
                    ),
                    keyRiskFactors = "Flower abortion due to high heat (>32°C); severe Late Blight (Phytophthora infestans) during foggy mornings."
                ),
                ProductionStageGuide(
                    stageNumber = 5,
                    stageName = "Fruit Bulking, Ripening & Continuous Harvest",
                    durationOrTiming = "Weeks 10 to 22 after transplanting",
                    comprehensiveNotes = "Fruits expand to mature green, breaker, turning, and firm red ripe stages. Potassium demand peaks to drive sugar accumulation, firmness, skin color, and uniform ripening.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Fruiting / Bulking High-K Top-Dress",
                        timingText = "Applied every 14 days throughout harvesting cycle",
                        recommendedBrand = "YaraMila WINNER (15-9-20) or Potassium Nitrate (13-0-46) fertigation / Multi-K foliar",
                        npkRatio = "15-9-20 or 13-0-46",
                        ratePerHectare = "200 kg/ha (soil) or 5 kg/ha weekly fertigation",
                        ratePerAcre = "80 kg/acre granular or 2 kg/acre fertigation",
                        ratePerPlantOrKnapsack = "8 grams per plant or 50g soluble Multi-K per 20L sprayer",
                        placementAndMethod = "Side-dress under drippers or inject through Venturi fertigation unit.",
                        primaryFunction = "Maximizes fruit diameter, deep red lycopene coloration, brix sweetness, and shelf life."
                    ),
                    agronomicPractices = listOf(
                        "Harvest at breaker stage for long-distance transport (Nairobi/Mombasa markets)",
                        "Strict observance of pesticide Pre-Harvest Intervals (PHI 1-3 days for Cypermethrin/Azoxystrobin)",
                        "Continue systematic pruning of dead tips and diseased foliage"
                    ),
                    keyRiskFactors = "Fruit fly oviposition punctures; fruit cracking and splitting from sudden irrigation surges."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Breaker stage: yellow-pink blush appearing at blossom end (10% color)",
                    "Turning stage: 10-30% pink/red coloration for distant commercial dispatch",
                    "Firm red ripe for local farm-gate and direct processing"
                ),
                harvestingGuidelines = "Harvest by twisting the calyx gently in early cool morning hours. Place single or double layers into plastic vented crates lined with foam; NEVER pack tomatoes in deep heavy sisal bags that cause internal crushing.",
                curingAndFieldDrying = "Do not sun-dry fresh tomatoes. Keep in cool shaded grading shed at 13°C - 15°C with 85-90% relative humidity. Never store below 10°C to prevent chilling injury.",
                gradingAndStandardization = listOf(
                    "Grade 1 (Super): Uniform shape, blemish-free, firm, diameter > 65mm",
                    "Grade 2: Slight shape irregularity, diameter 50-65mm, clean skin",
                    "Reject: Sun-scalded, cracked, worm-bored, or diseased fruits"
                ),
                packagingAndStorageCondition = "Market in standard 30kg or 50kg rigid plastic crates. Stack crates crosswise with ventilation chimneys.",
                storagePestAndLossMitigation = "Keep field crates off soil floor; maintain strict warehouse sanitation against Drosophila vinegar flies and Rhizopus soft rot.",
                commercialValueAddition = "Sun-dried tomato strips in olive oil, tomato paste, puree, ketchup, and canned whole peeled tomatoes."
            )
        ),

        // 3. IRISH POTATO (Solanum tuberosum)
        CropProductionProfile(
            id = "crop_potato",
            cropName = "Irish Potato (Viazi Mviringo)",
            botanicalName = "Solanum tuberosum L.",
            commonKenyanNames = "Viazi Mviringo, Waru, Ngwaci",
            category = CropCategory.TUBERS_ROOTS,
            heroSummary = "Kenya's second most important food crop. Demands certified chitted seed tubers (Shangi, Dutch Robyjn, Unica), deep ridge earthing up, and heavy phosphorus and potassium for tuber initiation and bulking.",
            imageResId = R.drawable.sample_early_blight,
            ecologicalAltitudeMeters = "1,500m - 3,000m ASL (Nyandarua, Meru, Nakuru, Narok, Elgeyo Marakwet)",
            ecologicalRainfallMm = "850mm - 1,400mm well distributed",
            optimalTemperatureCelsius = "15°C - 20°C (tuberization stops above 25°C soil temp)",
            preferredSoilTypeAndTexture = "Light, deep, well-aerated sandy loam with high organic humus",
            optimalSoilPhRange = "5.0 - 6.2 (prevents Common Scab)",
            spacingBetweenRowsCm = 75,
            spacingWithinRowCm = 30,
            plantPopulationPerAcre = "17,777 plants/acre",
            seedRatePerAcre = "16 - 20 bags (50kg bags) of certified Size-1 seed tubers (35-45mm)",
            averageYieldPerAcre = "100 - 180 bags (50kg bags) under good management",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Seed Tuber Sprouting (Chitting) & Furrow Prep",
                    durationOrTiming = "3 to 4 weeks prior to planting",
                    comprehensiveNotes = "Store certified seed tubers in a Diffuse Light Store (DLS) to develop short, sturdy, dark-green sprouts (1-2 cm). Avoid planting unsprouted or blind dormant tubers.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer at Furrow",
                        timingText = "In open furrows before tuber placement",
                        recommendedBrand = "YaraMila UNIK 17 (17-17-17) or NPK 17-17-17 / DAP (18-46-0) + MOP",
                        npkRatio = "17-17-17 or 18-46-0",
                        ratePerHectare = "250 - 300 kg/ha",
                        ratePerAcre = "100 - 120 kg/acre (2 to 2.5 x 50kg bags)",
                        ratePerPlantOrKnapsack = "10 - 12 grams per planting station in furrow",
                        placementAndMethod = "Distribute along the base of furrow and lightly cover with 2cm soil before laying seed tubers sprouts-facing-up.",
                        primaryFunction = "Fuels vigorous primary root initiation and multiple main stem emergence."
                    ),
                    agronomicPractices = listOf(
                        "Plough deeply to 30cm to create soft clod-free soil bed for unconstrained tuber expansion",
                        "Orient rows across the slope to prevent erosion",
                        "Treat seed tubers with fludioxonil or imidacloprid seed dressing"
                    ),
                    keyRiskFactors = "Sprout breakage from rough handling; seed tuber rot from waterlogged furrows."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Emergence & First Earthing Up (Hilling)",
                    durationOrTiming = "3 to 4 weeks after planting (plants 15-20 cm high)",
                    comprehensiveNotes = "Stems emerge vigorously. The first earthing up creates a wide trapezoidal ridge that covers lower nodes where underground stolons and tubers will initiate.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Top-Dress with Hilling",
                        timingText = "At first weeding and earthing up",
                        recommendedBrand = "CAN (26% N) or YaraBela SULFAN (24-0-0 + 6S)",
                        npkRatio = "26-0-0 or 24-0-0 + S",
                        ratePerHectare = "150 - 175 kg/ha",
                        ratePerAcre = "60 - 70 kg/acre",
                        ratePerPlantOrKnapsack = "5 grams banded beside each plant before soil ridge is mounded",
                        placementAndMethod = "Band 10cm from base of stems; immediately throw soil over fertilizer to build high ridge.",
                        primaryFunction = "Stimulates foliar leaf canopy and stolon elongation."
                    ),
                    agronomicPractices = listOf(
                        "First mechanical weeding and building wide ridges (20cm high)",
                        "Preventative spray against Late Blight (Mancozeb 80WP or Chlorothalonil)",
                        "Inspect for potato aphids vectoring Potato Leaf Roll Virus (PLRV)"
                    ),
                    keyRiskFactors = "Late blight epidemic during cool rainy weather; aphid virus transmission."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Tuber Initiation & Flowering",
                    durationOrTiming = "6 to 8 weeks after planting",
                    comprehensiveNotes = "Stolon tips swell into baby tubers. Potassium demand surges to power starch synthesis. Second earthing up must build deep mounds to shield developing tubers from direct sunlight and potato tuber moth.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Tuber Bulking Fertilizer",
                        timingText = "At flowering and final earthing up",
                        recommendedBrand = "YaraMila WINNER (15-9-20 + Mg + S) or Potassium Sulfate (SOP 0-0-50)",
                        npkRatio = "15-9-20 or 0-0-50 (Sulfate of Potash preferred over MOP to prevent chloride toxicity)",
                        ratePerHectare = "175 - 200 kg/ha",
                        ratePerAcre = "70 - 80 kg/acre",
                        ratePerPlantOrKnapsack = "6 - 8 grams per plant applied before final soil mounding",
                        placementAndMethod = "Side-dress along ridge shoulders and mound soil tightly over the ridge.",
                        primaryFunction = "Promotes rapid tuber swelling, high dry matter percentage, and starch density."
                    ),
                    agronomicPractices = listOf(
                        "Second high earthing up (30cm high mounds) to eliminate greening of tubers by sunlight",
                        "Systemic curative fungicide spray if Late Blight lesions are spotted (Metalaxyl + Mancozeb)",
                        "Maintain soil moisture at 70-80% field capacity"
                    ),
                    keyRiskFactors = "Tuber greening (solanine toxicity); Potato Tuber Moth larvae crawling into cracked soil to oviposit on tubers."
                ),
                ProductionStageGuide(
                    stageNumber = 4,
                    stageName = "De-Haulming (Destruction of Foliage) & Skin Hardening",
                    durationOrTiming = "10 to 14 days before harvest (crop leaves turn yellow)",
                    comprehensiveNotes = "Cut off all above-ground foliage (de-haulming) using a slasher or approved desiccant 2 weeks before harvest. Tubers remain undisturbed in soil to set and harden their outer periderm skin.",
                    fertilizerRecommendation = null,
                    agronomicPractices = listOf(
                        "Slashing stems at 5cm above soil level and removing haulms from field",
                        "Leaving tubers in dry soil for 14 days for complete skin setting",
                        "Never harvest immediately after slashing or skin will peel easily"
                    ),
                    keyRiskFactors = "Skinning and bruising of soft tubers during digging; bacterial soft rot infection through skin wounds."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Foliage naturally senesces and dries down",
                    "Skin cannot be rubbed off by moderate thumb pressure (fully set periderm)",
                    "Tubers detach cleanly from stolons"
                ),
                harvestingGuidelines = "Harvest on a clear sunny morning using blunt potato forks or tractor diggers. Avoid piercing tubers. Leave dug potatoes on dry soil surface for 2 hours to dry adhering soil, then gather into shaded collection crates.",
                curingAndFieldDrying = "Cure tubers for 7-10 days in a dark, well-ventilated barn at 15°C - 18°C and 90% RH to heal minor harvesting nicks and suberize cuts.",
                gradingAndStandardization = listOf(
                    "Chatts / Seed Size: 25 - 35mm (Size 2) and 35 - 55mm (Size 1 certified seed)",
                    "Ware Potatoes (Table consumption): 55 - 80mm",
                    "Processing / French Fries (Crisps): > 75mm oblong, high dry matter (>20%), low reducing sugars",
                    "Culls: Green, damaged, cut, rotten, or insect-bored tubers"
                ),
                packagingAndStorageCondition = "Store ware potatoes in the dark to prevent greening. Use 50kg standard jute bags or ventilated wooden crates. Stacking limit: 8 bags high on pallets.",
                storagePestAndLossMitigation = "Dust seed potatoes with Pyrethrum 0.2% powder against Potato Tuber Moth (Phthorimaea operculella); maintain absolute darkness for table potatoes.",
                commercialValueAddition = "Frozen french fries (chips), potato crisps, potato starch, and vacuum-sealed pre-cut culinary potatoes."
            )
        ),

        // 4. FRENCH BEANS (Phaseolus vulgaris)
        CropProductionProfile(
            id = "crop_french_beans",
            cropName = "French Beans (Mishiri)",
            botanicalName = "Phaseolus vulgaris L.",
            commonKenyanNames = "Mishiri, Fine Beans, Extra Fine",
            category = CropCategory.LEGUMES,
            heroSummary = "Major foreign exchange export horticultural crop grown for European supermarket export. Requires stringent maximum residue limit (MRL) compliance, strict pod straightness, and frequent hand pickings.",
            imageResId = R.drawable.sample_bean_rust,
            ecologicalAltitudeMeters = "1,000m - 2,100m ASL",
            ecologicalRainfallMm = "900mm - 1,200mm (or furrow/drip irrigation)",
            optimalTemperatureCelsius = "18°C - 24°C",
            preferredSoilTypeAndTexture = "Well-drained sandy loam or clay loam rich in organic matter",
            optimalSoilPhRange = "6.0 - 6.8",
            spacingBetweenRowsCm = 50,
            spacingWithinRowCm = 15,
            plantPopulationPerAcre = "53,333 plants/acre (single seed per hole at 50cm x 15cm)",
            seedRatePerAcre = "20 - 25 kg/acre (Export varieties like Serengeti, Samantha, Teresa, Amy)",
            averageYieldPerAcre = "3 - 5 tonnes/acre of export grade pods",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Land Prep, Inoculation & Basal Sowing",
                    durationOrTiming = "At planting",
                    comprehensiveNotes = "Create fine tilth beds. Inoculate legume seeds with Rhizobium leguminosarum bio-fertilizer (e.g. Biofix) to stimulate nitrogen fixation nodules. Ensure soil is moist at planting.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer",
                        timingText = "In planting furrows 5cm beside seed",
                        recommendedBrand = "DAP (18-46-0) or NPK 17-17-17 / YaraMila UNIK 17",
                        npkRatio = "18-46-0 or 17-17-17",
                        ratePerHectare = "150 - 200 kg/ha",
                        ratePerAcre = "60 - 80 kg/acre (1.5 x 50kg bags)",
                        ratePerPlantOrKnapsack = "3 - 4 grams per planting station",
                        placementAndMethod = "Side-band along furrow and cover before dropping seed. Avoid direct seed-fertilizer contact.",
                        primaryFunction = "Provides available phosphorus for rapid taproot development and nodule initiation."
                    ),
                    agronomicPractices = listOf(
                        "Inoculate seeds with Biofix in shade and plant within 4 hours",
                        "Plant at uniform 2.5 - 3.0 cm depth",
                        "Scout for Bean Fly (Ophiomyia phaseoli) stem pupae at base of hypocotyl"
                    ),
                    keyRiskFactors = "Bean fly larvae mining down seedling stems causing wilting and death; seed rot in waterlogged furrows."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Vegetative Growth & 1st Top-Dress",
                    durationOrTiming = "14 to 21 days after emergence (trifoliate leaf stage)",
                    comprehensiveNotes = "Crop develops dense trifoliate leaves and branches. Modest nitrogen top-dressing is beneficial before native Rhizobium nodules become fully functional.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative Top-Dress",
                        timingText = "At first weeding (2-3 trifoliate leaves)",
                        recommendedBrand = "CAN (26% N) or YaraLiva NITRABOR (15.4-0-0 + Ca + B)",
                        npkRatio = "26-0-0 or 15.4-0-0",
                        ratePerHectare = "100 - 125 kg/ha",
                        ratePerAcre = "40 - 50 kg/acre (1 x 50kg bag)",
                        ratePerPlantOrKnapsack = "2 - 3 grams per plant in band",
                        placementAndMethod = "Side-dress 8cm from plant row, followed by light hoeing and irrigation.",
                        primaryFunction = "Strengthens branch framework and photosynthetic capacity without causing excessive lushness."
                    ),
                    agronomicPractices = listOf(
                        "Shallow weeding to avoid damaging delicate surface feeder roots",
                        "Preventative spray for Bean Rust (Uromyces appendiculatus) and Anthracnose",
                        "Deploy yellow sticky traps for Whitefly and Thrips monitoring"
                    ),
                    keyRiskFactors = "Bean rust pustules on undersides of leaves; excessive vegetative nitrogen causing flower drop."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Flowering & Pod Setting",
                    durationOrTiming = "35 to 45 days after planting",
                    comprehensiveNotes = "Flowers open in rapid succession. Pod straightness and green coloration determine export eligibility. Flower thrips (Frankliniella occidentalis) feeding on blossoms cause curly, twisted, unmarketable pods.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Flowering Foliar Micro-Feed",
                        timingText = "At 10% flower bloom",
                        recommendedBrand = "High-Potassium + Boron Foliar Feed (e.g. Multi-K 13-0-46 + Solubor or Easygro Flower & Fruit)",
                        npkRatio = "13-0-46 or 14-10-34 + TE",
                        ratePerHectare = "2.5 kg/ha in 400L water",
                        ratePerAcre = "1.0 kg/acre in 150L water",
                        ratePerPlantOrKnapsack = "40 grams per 20L knapsack sprayer",
                        placementAndMethod = "Fine droplet foliar spray during morning hours (7am - 10am).",
                        primaryFunction = "Boron enhances pollen germination; potassium ensures straight, crisp, non-fibrous pod growth."
                    ),
                    agronomicPractices = listOf(
                        "Scout flowers for Western Flower Thrips daily; apply bio-pesticides (e.g. Spinosad, Neem) respecting strict EU MRLs",
                        "Maintain steady soil moisture to prevent flower and baby pod abortion",
                        "Never spray systemic organophosphates during flowering phase"
                    ),
                    keyRiskFactors = "Thrips twisting pods making them fail supermarket grade; Angular Leaf Spot."
                ),
                ProductionStageGuide(
                    stageNumber = 4,
                    stageName = "Harvesting (Every 2 to 3 Days)",
                    durationOrTiming = "50 to 70 days after planting (lasts 3-4 weeks)",
                    comprehensiveNotes = "Harvest early in the morning when pods are turgid and cool. Pick every 48 hours to ensure pods remain young, tender, and snap cleanly without showing visible seed bulges.",
                    fertilizerRecommendation = null,
                    agronomicPractices = listOf(
                        "Hand harvest by holding stem with one hand and snapping pod stalk with thumb",
                        "Place pods directly in clean, shaded plastic crates lined with damp linen",
                        "Keep picked crates in mobile charcoal cooler sheds in the shamba"
                    ),
                    keyRiskFactors = "Delayed harvest causing fibrous, stringy pods with swollen seeds rejected at packhouse."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Extra Fine: Pod diameter < 6.5mm, perfectly straight, tender, seedless",
                    "Fine: Pod diameter 6.5mm - 9.0mm, flexible, snap with clean crisp sound",
                    "Seed embryo barely visible inside pod cavity"
                ),
                harvestingGuidelines = "Harvest between 6:30 AM and 10:30 AM. Never expose picked beans to direct sunlight. Transfer to farm packhouse cold room within 2 hours of harvest.",
                curingAndFieldDrying = "Hydro-cool or forced-air cool pods down to 4°C - 7°C immediately to remove field heat and stop moisture transpiration.",
                gradingAndStandardization = listOf(
                    "Extra Fine: Length 10-14cm, straight, green, diameter < 6.5mm, zero blemishes",
                    "Fine: Length 11-15cm, diameter 6.5-9mm",
                    "Rejects: Rust spots, thrips curling, mechanical bruises, broken tips, overgrown seeds"
                ),
                packagingAndStorageCondition = "Packed in 1.5kg, 2.5kg, or 5kg cardboard export cartons with perforated inner plastic liners; stored at 4°C - 6°C at 95% relative humidity.",
                storagePestAndLossMitigation = "Rapid pre-cooling prevents Botrytis grey mold and transit nesting rot during air freight to Europe.",
                commercialValueAddition = "Pre-cut stringless trimmed microwave packs, frozen green beans (IQF), and canned wax beans."
            )
        ),

        // 5. CABBAGE & SUKUMA WIKI (Brassica oleracea)
        CropProductionProfile(
            id = "crop_brassicas",
            cropName = "Cabbage & Sukuma Wiki (Kales)",
            botanicalName = "Brassica oleracea L.",
            commonKenyanNames = "Cabbage, Sukuma Wiki, Kales, Kanyari",
            category = CropCategory.VEGETABLES,
            heroSummary = "Dominant staple vegetable consumed daily in Kenya. Heavy nitrogen, sulfur, and boron consumers that require strict Diamondback Moth (DBM) and Black Rot bacterial control.",
            imageResId = R.drawable.img_fall_armyworm,
            ecologicalAltitudeMeters = "800m - 2,500m ASL",
            ecologicalRainfallMm = "750mm - 1,200mm",
            optimalTemperatureCelsius = "15°C - 22°C (cool temperatures favor tight head formation)",
            preferredSoilTypeAndTexture = "Deep, rich loam with abundant organic matter and good water retention",
            optimalSoilPhRange = "6.0 - 7.5 (acidic soils < 6.0 predispose to Clubroot disease)",
            spacingBetweenRowsCm = 60,
            spacingWithinRowCm = 45,
            plantPopulationPerAcre = "14,800 plants/acre (at 60cm x 45cm)",
            seedRatePerAcre = "100 - 150 grams/acre (Hybrids: Gloria F1, Pruktor F1, Riana F1, Queen F1, Kinale Kales)",
            averageYieldPerAcre = "25 - 45 tonnes/acre (cabbage heads) or continuous 15-20 picking bags/week (kales)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Nursery Raising & Land Preparation",
                    durationOrTiming = "28 to 35 days in nursery",
                    comprehensiveNotes = "Raise seedlings in raised nursery beds or seedling trays. Drench with copper hydroxide + metalaxyl to avoid black leg and damping off. Deep plough shamba and incorporate 10 tonnes/acre manure.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer at Transplanting",
                        timingText = "In transplanting holes",
                        recommendedBrand = "DAP (18-46-0) or YaraMila UNIK 17 (17-17-17) / Mavuno Planting",
                        npkRatio = "18-46-0 or 17-17-17",
                        ratePerHectare = "200 kg/ha",
                        ratePerAcre = "80 kg/acre (1.5 x 50kg bags)",
                        ratePerPlantOrKnapsack = "5 - 7 grams per hole mixed with soil",
                        placementAndMethod = "Mix thoroughly with topsoil at base of 15cm hole before planting seedling.",
                        primaryFunction = "Accelerates rapid root anchor establishment and early leaf frame expansion."
                    ),
                    agronomicPractices = listOf(
                        "Broadcast agricultural lime if soil pH < 6.0 to prevent Clubroot (Plasmodiophora brassicae)",
                        "Harden seedlings 5 days before transplanting",
                        "Transplant in late evening to minimize sun scorch"
                    ),
                    keyRiskFactors = "Clubroot infection leading to swollen galled roots and wilting; Cutworm damage."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Vegetative Foliage Expansion & 1st Top-Dress",
                    durationOrTiming = "3 to 4 weeks after transplanting",
                    comprehensiveNotes = "Brassicas are massive nitrogen feeders. Stems thicken and outer wrapper leaves expand rapidly. Diamondback moth (Plutella xylostella) caterpillars start chewing window panes in leaves.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Top-Dress (Nitrogen + Sulfur)",
                        timingText = "21 to 28 days after transplanting",
                        recommendedBrand = "CAN (26% N) or YaraBela SULFAN (24-0-0 + 6S)",
                        npkRatio = "24-0-0 + 6% S or 26-0-0",
                        ratePerHectare = "150 - 180 kg/ha",
                        ratePerAcre = "60 - 75 kg/acre",
                        ratePerPlantOrKnapsack = "4 - 5 grams per plant ringed 10cm from stalk",
                        placementAndMethod = "Ring dress and weed in lightly, followed by furrow or sprinkler irrigation.",
                        primaryFunction = "Sulfur stimulates essential mustard glucosinolates and nitrogen fuels leafy biomass."
                    ),
                    agronomicPractices = listOf(
                        "First comprehensive weeding before canopy closes",
                        "Scout undersides of leaves for DBM larvae; spray Bacillus thuringiensis (Bt) or Emamectin benzoate",
                        "Inspect for Cabbage Aphids (Brevicoryne brassicae) forming mealy gray colonies"
                    ),
                    keyRiskFactors = "DBM caterpillar defoliation; Black Rot (Xanthomonas campestris) V-shaped yellow leaf margins."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Cabbage Head Formation (Cupping) & 2nd Top-Dress",
                    durationOrTiming = "6 to 8 weeks after transplanting",
                    comprehensiveNotes = "Wrapper leaves begin to cup inward to pack tight heads. Boron and potassium are essential: boron deficiency causes hollow stem syndrome and internal black rot in cabbage heads.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "2nd Top-Dress (Head Compacting & Boron)",
                        timingText = "At onset of head cupping / leaf fold",
                        recommendedBrand = "YaraLiva NITRABOR (15.4-0-0 + 25.6 CaO + 0.3 B) or NPK 17-17-17",
                        npkRatio = "15.4-0-0 + Ca + B",
                        ratePerHectare = "150 kg/ha",
                        ratePerAcre = "60 kg/acre (1.2 x 50kg bags)",
                        ratePerPlantOrKnapsack = "4 grams per plant side-dressed into moist soil",
                        placementAndMethod = "Side-dress around drip line; avoid getting fertilizer grains inside cabbage cup.",
                        primaryFunction = "Calcium prevents tip burn of inner head leaves; boron prevents hollow brown stems."
                    ),
                    agronomicPractices = listOf(
                        "Maintain steady irrigation: dry spells followed by heavy water cause head splitting",
                        "Maintain rogueing of any Black Rot infected plants",
                        "For Kales (Sukuma Wiki), initiate selective harvesting of mature bottom leaves every 7 days"
                    ),
                    keyRiskFactors = "Cabbage head bursting/cracking; Hollow stem disorder from boron deficiency."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Cabbage: Head feels solid and firm to fist pressure",
                    "Head reaches variety-specific weight (2.5 - 4.5 kg)",
                    "Kales: Leaves broad, deep green, tender before turning leathery or yellowing"
                ),
                harvestingGuidelines = "Cut cabbage head with a sharp knife leaving 2-3 outer green wrapper leaves to protect the compact white head during transit. For kales, snap bottom leaves upwards leaving the growing terminal crown intact.",
                curingAndFieldDrying = "Do not dry. Move immediately to shaded packing shelters. Avoid dropping heads to prevent internal bruising.",
                gradingAndStandardization = listOf(
                    "Large Grade: 3.0kg - 4.5kg per head, solid, round/flat-globe",
                    "Medium Grade: 1.8kg - 2.8kg per head",
                    "Rejects: Split heads, insect boreholes, soft bacterial rot at stem cut"
                ),
                packagingAndStorageCondition = "Transported in bulk or in ventilated mesh bags (50kg). Stored at 0°C - 2°C at 95% relative humidity for up to 6 weeks.",
                storagePestAndLossMitigation = "Trimming butt end cleanly and dipping in alum or lime paste prevents Erwinia soft rot during humid transit.",
                commercialValueAddition = "Fresh pre-shredded coleslaw mix, vacuum-packed fermented sauerkraut, and dehydrated vegetable flakes."
            )
        ),

        // 6. CUT ROSES (Rosa hybrida)
        CropProductionProfile(
            id = "crop_roses",
            cropName = "Cut Roses (Waridi)",
            botanicalName = "Rosa hybrida L.",
            commonKenyanNames = "Waridi, Cut Roses (Floriculture)",
            category = CropCategory.ORNAMENTAL_CUT_FLOWER,
            heroSummary = "Kenya's premier export flower industry centered around Lake Naivasha, Nakuru, and Mount Kenya. Requires strict automated fertigation, precise EC/pH management, and continuous scouting for spider mites and powdery mildew.",
            imageResId = R.drawable.sample_rose_powdery_mildew,
            ecologicalAltitudeMeters = "1,800m - 2,600m ASL (High altitude enhances large bud size and intense coloration)",
            ecologicalRainfallMm = "Controlled under greenhouse plastic covers (drip fertigation)",
            optimalTemperatureCelsius = "16°C - 26°C day / 12°C - 15°C night",
            preferredSoilTypeAndTexture = "Soil beds or soilless substrate (Coco peat / volcanic pumice / rockwool)",
            optimalSoilPhRange = "5.5 - 6.2 (Drainage water EC: 1.4 - 1.8 mS/cm)",
            spacingBetweenRowsCm = 20,
            spacingWithinRowCm = 15,
            plantPopulationPerAcre = "32,000 - 36,000 bushes/acre (80,000 bushes/ha on raised beds)",
            seedRatePerAcre = "Certified grafted rootstocks (Natal Briar / Rosa indica)",
            averageYieldPerAcre = "200 - 280 export quality stems per m² per year",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Bending, Arching & Canopy Framework Building",
                    durationOrTiming = "Months 1 to 3 after planting rootstocks",
                    comprehensiveNotes = "Primary shoots are bent horizontally outwards into the pathway (bending/arching) to form a dense photosynthetic 'factory' of leaves without producing harvestable stems. This drives strong basal bud break (water shoots/bull heads).",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative Arching Fertigation Feed",
                        timingText = "Daily continuous fertigation drip cycles",
                        recommendedBrand = "Calcium Nitrate + Potassium Nitrate + Monoammonium Phosphate (MAP) + Chelated Iron (Fe-EDDHA)",
                        npkRatio = "Feed recipe EC: 1.4 mS/cm, pH 5.6 - 5.8",
                        ratePerHectare = "Automated computer dosing (150 - 200 kg total salts/ha/week)",
                        ratePerAcre = "60 - 80 kg total soluble salts/acre/week",
                        ratePerPlantOrKnapsack = "2.0 - 2.5 ml fertilizer stock per litre irrigation water",
                        placementAndMethod = "Injected via automated A/B fertilizer tanks through compensated drip emitters (1.2 L/hr).",
                        primaryFunction = "Stimulates continuous basal bottom shoots and high leaf chlorophyll."
                    ),
                    agronomicPractices = listOf(
                        "Bend thin and non-productive shoots at right angle into the aisles",
                        "Maintain greenhouse humidity at 65-75% RH to optimize stomatal opening",
                        "Scout for Two-Spotted Spider Mites (Tetranychus urticae) on undersides of bent leaves"
                    ),
                    keyRiskFactors = "Red spider mite webbing; Root asphyxiation from over-irrigation in heavy pumice."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Shooting, Stem Elongation & Disbudding",
                    durationOrTiming = "Weeks 4 to 6 of stem flush cycle (lasts 50-65 days per flush)",
                    comprehensiveNotes = "Strong upright stems shoot from the crown. All lateral side-buds and side-shoots must be manually disbudded with fingers while still miniature (pea-sized) to direct all assimilates into the central king bloom.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Stem Elongation & Head Sizing",
                        timingText = "Daily fertigation during flush expansion",
                        recommendedBrand = "Balanced NPK + Silicon + Soluble Calcium + Micronutrients (YaraTera CALCINIT + KRISTA K)",
                        npkRatio = "N: 180ppm, P: 45ppm, K: 220ppm, Ca: 140ppm, Mg: 40ppm",
                        ratePerHectare = "Continuous drip injection (Target EC 1.6 - 1.8 mS/cm)",
                        ratePerAcre = "70 kg/acre/week dissolved in drip irrigation",
                        ratePerPlantOrKnapsack = "3.0 ml concentrate/litre",
                        placementAndMethod = "Distributed evenly across 8-12 short irrigation pulses daily.",
                        primaryFunction = "Ensures thick, rigid, straight stems (60-80cm) and prevents bent neck disorder."
                    ),
                    agronomicPractices = listOf(
                        "Manual disbudding of lateral side-buds and side-shoots twice weekly",
                        "Preventative night sulfur sublimation or bio-fungicide sprays for Powdery Mildew (Podosphaera pannosa)",
                        "Deploy yellow and blue sticky roller traps along greenhouse perimeter for western flower thrips"
                    ),
                    keyRiskFactors = "Powdery mildew white talcum-like coating on leaves; Downy mildew (Peronospora sparsa) causing sudden defoliation."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Harvesting (Cut Stage Cut Point)",
                    durationOrTiming = "Twice daily (early morning & late afternoon)",
                    comprehensiveNotes = "Harvest stems at exact variety cut stages (Cut Stage 2 to 3.5: sepals reflexed, petals beginning to unfurl). Cut with clean sanitized shears leaving 1-2 five-leaflet eyes on the stub for the next flush.",
                    fertilizerRecommendation = null,
                    agronomicPractices = listOf(
                        "Disinfect secateurs in 70% ethanol or Virkon-S between beds to prevent Crown Gall transmission",
                        "Place cut stems immediately into clean buckets containing post-harvest hydration solution (Chrysal / TOG-6) within 10 minutes",
                        "Transfer buckets to on-farm cold store (4°C) without delay"
                    ),
                    keyRiskFactors = "Bent neck from air embolism in xylem vessels; Botrytis cinerea ghost spots on petals."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Cut Stage 2: Sepals completely reflexed downwards away from bud",
                    "Cut Stage 2.5 - 3.0: Outer 2-3 petals softening and beginning to loosen",
                    "Minimum stem length of 40cm (standard export lengths: 50cm, 60cm, 70cm, 80cm)"
                ),
                harvestingGuidelines = "Cut with sharp floral shears at 1cm above a five-leaflet junction. Place in clean, sanitized buckets with acidified water (pH 3.5 - 4.5) containing chlorine dioxide to inhibit vascular bacterial blockage.",
                curingAndFieldDrying = "Pre-cool stems in forced-air cooling rooms to 2°C - 3°C within 1 hour of harvest to arrest respiration and ethylene release.",
                gradingAndStandardization = listOf(
                    "Graded by stem length (40cm to 90cm in 10cm increments)",
                    "Graded by head size (bud height > 4.5cm, diameter > 3.0cm)",
                    "Strict zero-tolerance for thrips streaks, spider mite eggs, or Botrytis lesions"
                ),
                packagingAndStorageCondition = "Bunched in 10 or 20 stems with cardboard corrugated wrap and protective head sleeves. Shipped in telescopic floral cartons at 2°C - 4°C via temperature-controlled reefers to JKIA airport.",
                storagePestAndLossMitigation = "Pre-treatment with anti-ethylene gaseous 1-MCP (EthylBloc) or silver thiosulfate (STS) pulses prevents premature petal dropping.",
                commercialValueAddition = "Direct supermarket bouquets (mixed bouqs with gypsophila/hypericum), dyed rainbow roses, and preserved freeze-dried eternal roses."
            )
        ),

        // 7. BULB ONION & GARLIC (Allium cepa & Allium sativum)
        CropProductionProfile(
            id = "crop_onion",
            cropName = "Bulb Onion & Garlic (Vitunguu)",
            botanicalName = "Allium cepa L. / Allium sativum L.",
            commonKenyanNames = "Kitunguu Maji, Kitunguu Saumu",
            category = CropCategory.VEGETABLES,
            heroSummary = "High-demand commercial kitchen staple. Requires sandy loam soil, high phosphorus basal start, balanced sulfur for pungent allicin oil formation, and 3-week field curing for firm paper scales.",
            imageResId = R.drawable.sample_early_blight,
            ecologicalAltitudeMeters = "500m - 2,000m ASL",
            ecologicalRainfallMm = "500mm - 800mm (requires dry ripening period)",
            optimalTemperatureCelsius = "18°C - 28°C (high temperatures trigger bulbing in short-day varieties)",
            preferredSoilTypeAndTexture = "Loose, fertile sandy loam; free of heavy stones that deform bulbs",
            optimalSoilPhRange = "6.0 - 6.8",
            spacingBetweenRowsCm = 30,
            spacingWithinRowCm = 10,
            plantPopulationPerAcre = "133,000 plants/acre",
            seedRatePerAcre = "1.0 - 1.2 kg/acre (Hybrid varieties: Red Pinoy F1, Red Saria F1, Neptune F1, Jamba F1)",
            averageYieldPerAcre = "15 - 25 tonnes/acre of dried commercial grade bulbs",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Nursery Raising & Seedbed Prep",
                    durationOrTiming = "40 to 45 days in seedbed",
                    comprehensiveNotes = "Sow seeds in shallow drills 15cm apart on raised nursery beds. Seedlings are ready when they reach pencil thickness (5-7mm diameter) with 4-5 green leaves.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Fertilizer at Transplanting",
                        timingText = "In planting furrows on raised beds",
                        recommendedBrand = "YaraMila CEREAL (23-10-5) or DAP (18-46-0) / Mavuno Planting",
                        npkRatio = "23-10-5 or 18-46-0",
                        ratePerHectare = "200 kg/ha",
                        ratePerAcre = "80 kg/acre (1.5 x 50kg bags)",
                        ratePerPlantOrKnapsack = "2 - 3 grams per meter of furrow",
                        placementAndMethod = "Broadcast into furrows and incorporate into top 5cm soil before transplanting.",
                        primaryFunction = "Stimulates root expansion without stunting shallow root systems."
                    ),
                    agronomicPractices = listOf(
                        "Trim top 1/3 of green foliage and long roots slightly at transplanting to balance transpiration",
                        "Plant shallowly (1.5 - 2.0 cm depth); deep planting causes elongated necked bulbs instead of round bulbs",
                        "Irrigate immediately after planting"
                    ),
                    keyRiskFactors = "Deep planting causing bottle-necked bulbs; Onion Thrips (Thrips tabaci) colonizing leaf sheaths."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Vegetative Foliage & 1st Top-Dress",
                    durationOrTiming = "3 to 4 weeks after transplanting",
                    comprehensiveNotes = "Plants establish multiple tubular green leaves. Each leaf corresponds to one fleshy scale inside the bulb; more healthy leaves produce larger final bulbs.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Top-Dress (Nitrogen + Sulfur)",
                        timingText = "21 days after transplanting",
                        recommendedBrand = "CAN (26% N) or Ammonium Sulfate (21% N + 24% S) / YaraBela SULFAN",
                        npkRatio = "21-0-0 + 24% S or 26-0-0",
                        ratePerHectare = "150 kg/ha",
                        ratePerAcre = "60 kg/acre",
                        ratePerPlantOrKnapsack = "2 grams per plant banded between rows",
                        placementAndMethod = "Side-band along row middles and water in.",
                        primaryFunction = "Sulfur synthesizes allyl propyl disulfide oils responsible for onion flavor and pungency."
                    ),
                    agronomicPractices = listOf(
                        "Weed regularly: shallow onion roots cannot compete with aggressive weeds like blackjack or nutgrass",
                        "Scout for Purple Blotch (Alternaria porri) forming sunken purple lesions on leaves",
                        "Maintain thrips control using spinetoram or profenofos"
                    ),
                    keyRiskFactors = "Purple blotch lesions girdling leaves; severe thrips silvering."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Bulb Swelling & 2nd Top-Dress (Potassium Booster)",
                    durationOrTiming = "7 to 9 weeks after transplanting",
                    comprehensiveNotes = "The base of the plant starts swelling into a round bulb. Cease all nitrogen application now: late nitrogen causes thick spongy necks that fail to seal and rot in storage.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Bulbing Fertilizer (High Potassium)",
                        timingText = "At onset of bulb enlargement",
                        recommendedBrand = "YaraMila WINNER (15-9-20) or Sulfate of Potash (SOP 0-0-50)",
                        npkRatio = "15-9-20 or 0-0-50",
                        ratePerHectare = "150 - 180 kg/ha",
                        ratePerAcre = "60 - 75 kg/acre",
                        ratePerPlantOrKnapsack = "3 grams per plant banded",
                        placementAndMethod = "Band between rows and irrigate; stop fertilizer 3 weeks before harvest.",
                        primaryFunction = "Drives rapid starch translocation into bulb scales, hardening protective papery skin."
                    ),
                    agronomicPractices = listOf(
                        "Scrape excess soil away from developing bulb shoulders (exposing top half to sunlight hardens color)",
                        "Gradually taper off irrigation as bulbs reach mature size",
                        "Completely withdraw irrigation 14 days before harvest"
                    ),
                    keyRiskFactors = "Thick neck disorder from late nitrogen; bulb splitting from erratic late watering."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "75% of plant tops soften at the neck and collapse/fall over naturally",
                    "Outer bulb scales turn dry, papery, and characteristic deep red or copper-brown",
                    "Neck tissue feels thin and dry when squeezed"
                ),
                harvestingGuidelines = "Pull bulbs gently on dry sunny days. Windrow bulbs in the field with leaves overlapping and covering adjacent bulbs to prevent sun-scald.",
                curingAndFieldDrying = "Field cure for 7 to 14 days on raised mesh or dry ground until necks are completely dry and papery. Clip tops 2.5cm above the bulb with clean shears.",
                gradingAndStandardization = listOf(
                    "Large: Diameter > 65mm, tight thin neck, solid dry skins",
                    "Medium: Diameter 45 - 65mm (standard Kenyan retail grade)",
                    "Picklers: Diameter < 35mm",
                    "Rejects: Thick-necked bulbs, doubles/splits, sprouted, or soft neck rot"
                ),
                packagingAndStorageCondition = "Store in open-mesh netted bags (10kg or 25kg red nets) suspended in well-ventilated dry sheds at 0°C - 4°C with 65-70% RH.",
                storagePestAndLossMitigation = "Adequate curing prevents Neck Rot (Botrytis allii) and Black Mold (Aspergillus niger). Never store in closed plastic bags.",
                commercialValueAddition = "Dehydrated onion flakes, onion powder, pickled cocktail onions, and extracted allicin oil."
            )
        ),

        // 8. HASS AVOCADO (Persea americana)
        CropProductionProfile(
            id = "crop_avocado",
            cropName = "Hass Avocado (Parachichi)",
            botanicalName = "Persea americana Mill.",
            commonKenyanNames = "Parachichi, Hass, Fuerte",
            category = CropCategory.FRUITS,
            heroSummary = "Kenya's leading commercial fruit export to European and Asian markets. Demands grafted certified trees, excellent deep soil drainage to avoid Phytophthora root rot, and strict dry matter maturity standards (>23%).",
            imageResId = R.drawable.sample_late_blight,
            ecologicalAltitudeMeters = "1,400m - 2,200m ASL (Murang'a, Kiambu, Meru, Kisii, Nakuru)",
            ecologicalRainfallMm = "1,000mm - 1,600mm well distributed",
            optimalTemperatureCelsius = "16°C - 24°C",
            preferredSoilTypeAndTexture = "Deep, loose, well-aerated volcanic loam (>1.5m deep without hardpan)",
            optimalSoilPhRange = "5.5 - 6.5 (avocados are extremely sensitive to saline and waterlogged soils)",
            spacingBetweenRowsCm = 600,
            spacingWithinRowCm = 500,
            plantPopulationPerAcre = "130 - 150 trees/acre (standard 6m x 5m) or 200 trees/acre (high density 5m x 4m)",
            seedRatePerAcre = "Certified grafted seedlings on Duke 7 / indigenous rootstock",
            averageYieldPerAcre = "6 - 10 tonnes/acre at maturity (year 5+; ~300-600 fruits per mature tree)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Land Preparation, Digging Holes & Basal Planting",
                    durationOrTiming = "At planting (onset of long rains)",
                    comprehensiveNotes = "Dig generous 60cm x 60cm x 60cm holes. Separate topsoil from subsoil. Mix topsoil with 2 debis (40kg) of well-rotted manure, 250g lime (if acidic), and 250g phosphorus fertilizer. Plant on raised mounds in heavy soils.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Planting Hole Fertilizer",
                        timingText = "In planting hole mixed with topsoil",
                        recommendedBrand = "DAP (18-46-0) or Minjingu Rock Phosphate (28% P2O5) / NPK 17-17-17",
                        npkRatio = "18-46-0 or 0-28-0",
                        ratePerHectare = "35 kg/ha",
                        ratePerAcre = "15 - 20 kg/acre (for 135 trees)",
                        ratePerPlantOrKnapsack = "200 - 250 grams per tree planting hole",
                        placementAndMethod = "Thoroughly blend with topsoil and compost in the bottom two-thirds of the hole.",
                        primaryFunction = "Stimulates extensive taproot and mycorrhizal feeder root establishment."
                    ),
                    agronomicPractices = listOf(
                        "Plant grafted seedling keeping graft union 15cm above soil level",
                        "Stake young trees with bamboo to prevent wind snapping",
                        "Apply thick dry organic grass mulch (keeping 10cm clear of tree trunk)",
                        "Paint tree trunk white with water-based paint to prevent sun-scald"
                    ),
                    keyRiskFactors = "Phytophthora cinnamomi root rot in stagnant water; False Codling Moth (FCM)."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Vegetative Establishment & Pruning (Years 1 to 2)",
                    durationOrTiming = "Year 1 and Year 2 maintenance",
                    comprehensiveNotes = "Pinch terminal leader shoot at 1.0m height to encourage multiple strong lateral scaffold branches. Remove all flowers appearing during the first two years to channel all energy into tree architecture.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Young Tree Quarterly Nitrogen/Calcium Feed",
                        timingText = "Split into 3 applications per year (onset of rains)",
                        recommendedBrand = "CAN (26% N) or YaraLiva NITRABOR (15.4-0-0 + 25.6 CaO + 0.3 B)",
                        npkRatio = "15.4-0-0 + Ca + B",
                        ratePerHectare = "40 - 50 kg/ha",
                        ratePerAcre = "20 - 25 kg/acre",
                        ratePerPlantOrKnapsack = "150 - 200 grams per tree per application",
                        placementAndMethod = "Broadcast evenly under tree drip-line, extending 30cm beyond foliage rim; mulch over.",
                        primaryFunction = "Builds robust branch skeleton and thick dark green leathery canopy."
                    ),
                    agronomicPractices = listOf(
                        "Maintain thick 15cm organic mulch to conserve moisture and suppress weeds",
                        "Formative structural pruning to open tree canopy to sunlight",
                        "Paint or paste copper oxychloride on any pruning cut wounds"
                    ),
                    keyRiskFactors = "Wind injury; Thrips scarring tender flush leaves."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Flowering, Fruit Set & Mature Bearing (Year 3+)",
                    durationOrTiming = "Annual cycle (July-August flowering, March-June harvest)",
                    comprehensiveNotes = "Millions of small flowers bloom in panicles (synchronous protogynous dichogamy). Beehives placed in orchards drastically boost pollination fruit set. Heavy potassium and boron are demanded.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Mature Fruit Bearing NPK + Micronutrients",
                        timingText = "Split: 50% at flowering, 50% at fruit bulking",
                        recommendedBrand = "YaraMila WINNER (15-9-20 + Mg + S) or NPK 17-17-17 + Solubor foliar",
                        npkRatio = "15-9-20 + TE",
                        ratePerHectare = "150 - 200 kg/ha",
                        ratePerAcre = "60 - 80 kg/acre (for 135 trees)",
                        ratePerPlantOrKnapsack = "500 - 800 grams per mature tree per year",
                        placementAndMethod = "Spread in 1-meter wide band around tree canopy drip-line, incorporated into mulch.",
                        primaryFunction = "Boron ensures pollen viability; potassium builds fruit oil content and heavy weight."
                    ),
                    agronomicPractices = listOf(
                        "Introduce 2-3 beehives per acre during blossom opening to maximize cross-pollination",
                        "Install pheromone traps (Chempac / Decis traps) for False Codling Moth (Thaumatotibia leucotreta)",
                        "Prune dead wood and water shoots immediately after main harvest"
                    ),
                    keyRiskFactors = "False Codling Moth larvae boring into fruit causing export rejection; Anthracnose spots."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Minimum Dry Matter Content of 23% (tested by oven drying fruit pulp sample)",
                    "Fruit stem (pedicel) turns yellow-green and loses gloss",
                    "Skin color changes from glossy bright green to dull dark green with visible raised pebbling",
                    "Inner seed coat turns dark brown and papery"
                ),
                harvestingGuidelines = "Harvest using pole clippers with canvas catching bags. Clip pedicel leaving 3-5mm stalk intact on fruit. Never pull fruit by hand or allow them to drop to the ground.",
                curingAndFieldDrying = "Do not dry. Transfer fruit within 2 hours to shaded packhouse. Cool rapidly down to 5.5°C in packhouse forced-air pre-coolers.",
                gradingAndStandardization = listOf(
                    "Count 12 to Count 24 (Weights: 170g to 350g per fruit)",
                    "Zero blemishes, no scale insects, no FCM pinholes, no sun-scald",
                    "Dry matter strictly >= 23.0% for export compliance"
                ),
                packagingAndStorageCondition = "Packed in 4.0kg single-layer export cartons. Shipped in Controlled Atmosphere (CA) sea containers (O2: 2-5%, CO2: 4-10%, Temp: 5.5°C) to Europe/China/Middle East.",
                storagePestAndLossMitigation = "Hot water dip (46°C for 5 minutes) or prochloraz post-harvest fungicide bath controls latent Anthracnose and Stem-End Rot.",
                commercialValueAddition = "Cold-pressed extra virgin avocado oil for culinary and cosmetics, frozen guacamole pulp, and avocado facial oil."
            )
        )
    ) + FloricultureCropProfiles.list + CerealCropProfiles.list + PerennialCropProfiles.list

    /**
     * Search crop library by query text (matches crop name, botanical name, category, or practices).
     */
    fun searchCrops(query: String, selectedCategory: CropCategory = CropCategory.ALL): List<CropProductionProfile> {
        val cleanQuery = query.trim().lowercase()
        return crops.filter { crop ->
            val matchesCategory = selectedCategory == CropCategory.ALL || crop.category == selectedCategory
            val matchesQuery = cleanQuery.isEmpty() ||
                    crop.cropName.lowercase().contains(cleanQuery) ||
                    crop.botanicalName.lowercase().contains(cleanQuery) ||
                    crop.commonKenyanNames.lowercase().contains(cleanQuery) ||
                    crop.heroSummary.lowercase().contains(cleanQuery) ||
                    crop.stages.any { stage ->
                        stage.stageName.lowercase().contains(cleanQuery) ||
                        stage.comprehensiveNotes.lowercase().contains(cleanQuery) ||
                        (stage.fertilizerRecommendation?.recommendedBrand?.lowercase()?.contains(cleanQuery) == true)
                    }
            matchesCategory && matchesQuery
        }
    }
}
