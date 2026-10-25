package com.example.data

import com.example.R
import com.example.model.CropCategory
import com.example.model.CropPostHarvestGuide
import com.example.model.CropProductionProfile
import com.example.model.ProductionStageGuide
import com.example.model.StageFertilizerApplication

/**
 * Commercial Kenyan Floriculture Profiles (Cut Roses, Carnations, Chrysanthemums, Gypsophila, Hypericum, Lilies).
 * Covers Naivasha, Nakuru, Mount Kenya, and Eldoret export flower production guidelines.
 */
object FloricultureCropProfiles {

    val list: List<CropProductionProfile> = listOf(
        // 1. CARNATIONS (Dianthus caryophyllus)
        CropProductionProfile(
            id = "crop_carnations",
            cropName = "Carnations (Standard & Spray)",
            botanicalName = "Dianthus caryophyllus L.",
            commonKenyanNames = "Carnation, Ua la Carnation",
            category = CropCategory.ORNAMENTAL_CUT_FLOWER,
            heroSummary = "Major export cut flower grown in cool Kenyan highland greenhouses (Nyandarua, Timau, Naivasha). Requires continuous netting support, pinching to stimulate multiple flowering stems, and precise potassium-to-calcium nutrition to prevent weak calyx splitting.",
            imageResId = R.drawable.sample_rose_powdery_mildew,
            ecologicalAltitudeMeters = "1,900m - 2,700m ASL",
            ecologicalRainfallMm = "Controlled greenhouse drip fertigation",
            optimalTemperatureCelsius = "15°C - 22°C day / 10°C - 13°C night (cool nights intensify bloom colors)",
            preferredSoilTypeAndTexture = "High-porosity pumice beds, coco-peat, or deep well-drained volcanic sandy loam",
            optimalSoilPhRange = "6.0 - 6.8 (EC 1.2 - 1.6 mS/cm)",
            spacingBetweenRowsCm = 15,
            spacingWithinRowCm = 12,
            plantPopulationPerAcre = "110,000 - 130,000 rooted cuttings/acre (on raised mesh-supported beds)",
            seedRatePerAcre = "Certified tissue-culture or apical rooted cuttings",
            averageYieldPerAcre = "350 - 500 export stems per m² annually",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Bed Preparation, Inoculation & Planting",
                    durationOrTiming = "Week 1 to 2",
                    comprehensiveNotes = "Steam or bio-fumigate beds to eradicate Fusarium oxysporum f.sp. dianthi. Plant rooted cuttings shallowly (do not bury crown). Drench immediately with Trichoderma harzianum and propamocarb.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Starter Drip Feed",
                        timingText = "From day 4 after planting",
                        recommendedBrand = "Calcium Nitrate (YaraTera CALCINIT) + MAP (12-61-0)",
                        npkRatio = "15-11-0 + 19% Ca",
                        ratePerHectare = "25 kg/ha/week via drip",
                        ratePerAcre = "10 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.0 gram soluble salts per litre irrigation water (EC 1.2 mS/cm)",
                        placementAndMethod = "Micro-drip lines with 10cm emitter spacing along plant rows.",
                        primaryFunction = "Stimulates adventitious root initiation while avoiding stem crown rot."
                    ),
                    agronomicPractices = listOf(
                        "Install 4-5 layers of wire support netting on vertical corner posts before growth",
                        "Maintain relative humidity below 80% to suppress Alternaria dianthi",
                        "Scout for Thrips and Two-Spotted Spider Mites daily"
                    ),
                    keyRiskFactors = "Fusarium wilt causing unilateral branch wilting; Rhizoctonia stem rot from deep planting."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Pinching & Canopy Architecture",
                    durationOrTiming = "Weeks 4 to 6 after planting",
                    comprehensiveNotes = "Pinch out terminal apical growing point above 5 to 6 pairs of leaves (single pinch) to break apical dominance and trigger 4 to 6 vigorous lateral flowering shoots.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative Branching Feed",
                        timingText = "Continuous fertigation during branch flush",
                        recommendedBrand = "Potassium Nitrate (13-0-46) + Magnesium Sulfate + YaraTera CALCINIT",
                        npkRatio = "16-8-24 + 4Mg + TE",
                        ratePerHectare = "40 kg/ha/week",
                        ratePerAcre = "16 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.5g/L water (EC 1.4 mS/cm, pH 6.0)",
                        placementAndMethod = "Fertigated through drip pulses 4-6 times per day.",
                        primaryFunction = "Fuels lateral shoot elongation and thick fibrous stem internodes."
                    ),
                    agronomicPractices = listOf(
                        "Single-pinch terminal shoot when 6th leaf pair fully unfolds",
                        "Raise support netting layers progressively as stems elongate",
                        "Inspect for Carnation Rust (Uromyces dianthi) chocolate-brown pustules"
                    ),
                    keyRiskFactors = "Uneven pinch timing resulting in non-uniform harvest flushes; Rust infection."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Disbudding & Calyx Management",
                    durationOrTiming = "Weeks 12 to 18",
                    comprehensiveNotes = "For Standard carnations, remove all lateral side buds while miniature to produce a single massive terminal bloom. For Spray carnations, pinch out the terminal king bud to encourage uniform side spray florets. Ensure adequate calcium and boron to prevent calyx splitting.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Calyx Firming & Bloom Feed",
                        timingText = "At bud swelling stage",
                        recommendedBrand = "High Calcium-Potassium + Solubor (YaraLiva + Krista SOP)",
                        npkRatio = "14-6-28 + 10% Ca + 0.2% B",
                        ratePerHectare = "45 kg/ha/week",
                        ratePerAcre = "18 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.8g/L irrigation water (EC 1.6 mS/cm)",
                        placementAndMethod = "Drip fertigation with morning calcium foliar spray.",
                        primaryFunction = "Strengthens calyx ring cell walls, completely preventing calyx splitting disorder."
                    ),
                    agronomicPractices = listOf(
                        "Disbud side buds when 5mm diameter by rolling sideways with thumb",
                        "Avoid sudden day-night temperature swings (>12°C delta causes calyx split)",
                        "Spray Spinetoram or Bacillus thuringiensis for Helicoverpa bud borers"
                    ),
                    keyRiskFactors = "Calyx splitting causing unmarketable deformed blooms; Flower thrips petals browning."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Standard Carnation: Paintbrush stage (petals emerging straight up 15-20mm above calyx)",
                    "Spray Carnation: At least 2-3 florets open to cup stage, remaining buds well developed",
                    "Firm green calyx without vertical tears"
                ),
                harvestingGuidelines = "Harvest in early morning by snapping stems cleanly at a node junction 10cm above bed surface. Immediately place in clean buckets containing 1-MCP / STS pulse solution.",
                curingAndFieldDrying = "Do not sun dry. Hydro-cool or forced-air cool down to 2°C - 4°C within 60 minutes of harvest to halt ethylene production.",
                gradingAndStandardization = listOf(
                    "Select Grade: Stem length >= 65cm, thick stem diameter > 5mm, perfect bloom diameter > 60mm",
                    "Standard Grade: Length 50 - 60cm, bloom diameter 50 - 60mm",
                    "Rejects: Split calyx, crooked stem, thrips streaking, Botrytis petals"
                ),
                packagingAndStorageCondition = "Sleeved in 20-stem bunches with micro-perforated polypropylene film. Shipped in telescopic export flower cartons at 1°C - 3°C and 90% RH.",
                storagePestAndLossMitigation = "Carnations are hyper-sensitive to ethylene; silver thiosulfate (STS) 4-hour pulse or EthylBloc fumigation prevents premature petal sleepiness (wilting).",
                commercialValueAddition = "Mixed supermarket ready bouquets, tinted dyed carnations, and buttonhole corsages."
            )
        ),

        // 2. CHRYSANTHEMUMS (Chrysanthemum morifolium)
        CropProductionProfile(
            id = "crop_chrysanthemums",
            cropName = "Chrysanthemums (Spray & Disbud)",
            botanicalName = "Chrysanthemum morifolium Ramat.",
            commonKenyanNames = "Chrysanthemum, Mums, Maua ya Mums",
            category = CropCategory.ORNAMENTAL_CUT_FLOWER,
            heroSummary = "High-value photoperiod-controlled export cut flower. Short-day plant requiring night-break cyclic lighting during vegetative growth and blackouts during floral induction. Major export lines in Naivasha and Mount Kenya.",
            imageResId = R.drawable.sample_rose_powdery_mildew,
            ecologicalAltitudeMeters = "1,800m - 2,500m ASL",
            ecologicalRainfallMm = "Controlled greenhouse drip irrigation",
            optimalTemperatureCelsius = "18°C - 24°C day / 14°C - 16°C night",
            preferredSoilTypeAndTexture = "Light, fertile sandy loam or cocopeat substrate with rapid drainage",
            optimalSoilPhRange = "6.0 - 6.5 (EC 1.3 - 1.7 mS/cm)",
            spacingBetweenRowsCm = 12,
            spacingWithinRowCm = 12,
            plantPopulationPerAcre = "180,000 - 220,000 cuttings/acre (64 plants per m²)",
            seedRatePerAcre = "Certified disease-free rooted terminal cuttings (CVI / PBZ certified)",
            averageYieldPerAcre = "3 to 4 crop cycles per year yielding 600,000 stems/acre/year",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Long-Day Vegetative Phase & Night Lighting",
                    durationOrTiming = "Weeks 1 to 4 after planting",
                    comprehensiveNotes = "Maintain vegetative growth by interrupting natural darkness with cyclic night lighting (100 lux artificial light for 4 hours each night from 10 PM to 2 AM). This prevents premature bud setting and builds 70-80cm stem height.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative High-N Drip Feed",
                        timingText = "Daily through drip irrigation",
                        recommendedBrand = "Calcium Nitrate + NPK 19-19-19 + Micronutrients",
                        npkRatio = "20-10-20 + Mg + TE",
                        ratePerHectare = "35 kg/ha/week",
                        ratePerAcre = "14 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.2g/L irrigation water (EC 1.4 mS/cm)",
                        placementAndMethod = "Multiple short daily irrigation pulses under drip lines.",
                        primaryFunction = "Stimulates rapid stem extension, node multiplication, and lush foliage."
                    ),
                    agronomicPractices = listOf(
                        "Provide 4 hours nightly artificial cyclic lighting to maintain vegetative state",
                        "Raise support mesh as stems reach 20cm",
                        "Scout for White Rust (Puccinia horiana) quarantine fungal pathogen"
                    ),
                    keyRiskFactors = "Chrysanthemum White Rust (Puccinia horiana - quarantine zero tolerance in EU); Leafminers (Liriomyza trifolii)."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Short-Day Blackout & Floral Induction",
                    durationOrTiming = "Weeks 5 to 9",
                    comprehensiveNotes = "Turn off night lights and pull automatic blackout screens over crops from 6 PM to 8 AM (providing 14 hours uninterrupted darkness). This triggers rapid floral bud initiation across all stems.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Bud Induction Potassium Feed",
                        timingText = "Throughout short-day blackout phase",
                        recommendedBrand = "Potassium Nitrate (13-0-46) + Monoammonium Phosphate (12-61-0)",
                        npkRatio = "14-12-30 + Mg + B",
                        ratePerHectare = "45 kg/ha/week",
                        ratePerAcre = "18 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.6g/L water (EC 1.6 mS/cm)",
                        placementAndMethod = "Drip fertigation with balanced micronutrients.",
                        primaryFunction = "Initiates multiple terminal flower buds with uniform floret opening."
                    ),
                    agronomicPractices = listOf(
                        "Strict 14-hour daily blackout screen closure without light leaks",
                        "For Spray mums, pinch out the central apical terminal bud when pea-sized to produce uniform umbrella spray",
                        "Deploy yellow sticky traps for western flower thrips and leafminer flies"
                    ),
                    keyRiskFactors = "Light leaks delaying flower bud set; Botrytis blight under humid blackout curtains."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Spray Chrysanthemums: 3 to 4 outer florets fully open, inner central florets showing true color",
                    "Disbud (Standard): Outer ray florets unfurled to 45° angle",
                    "Foliage dark green down to within 10cm of stem cut base"
                ),
                harvestingGuidelines = "Cut stems 10cm above ground with sharp floral secateurs. Strip lower 15cm leaves immediately to avoid leaves decaying in water buckets.",
                curingAndFieldDrying = "Hydrate immediately in clean plastic buckets with Chrysal / TOG chlorine water (pH 4.0). Pre-cool to 3°C - 5°C.",
                gradingAndStandardization = listOf(
                    "Standard Grade: Length 70 - 80cm, stem weight >= 75g per stem, minimum 5 open blooms per spray",
                    "Medium Grade: Length 60 - 70cm, stem weight >= 60g, minimum 4 open blooms",
                    "Strict zero-tolerance for Chrysanthemum White Rust pustules or leafminer tunnels"
                ),
                packagingAndStorageCondition = "Bunched in 5 or 10 stems with protective perforated cellophane sleeves. Packed in 80cm floral cartons and air-freighted at 2°C - 4°C.",
                storagePestAndLossMitigation = "Post-harvest hydration with Chrysal AVB prevents vascular bacterial clogging and petal droop.",
                commercialValueAddition = "Direct-to-retail supermarket mixed bouquets, floral arrangements, and pot mums."
            )
        ),

        // 3. GYPSOPHILA / BABY'S BREATH (Gypsophila paniculata)
        CropProductionProfile(
            id = "crop_gypsophila",
            cropName = "Gypsophila (Baby's Breath)",
            botanicalName = "Gypsophila paniculata L.",
            commonKenyanNames = "Gypsophila, Gyp, Million Stars, Baby's Breath",
            category = CropCategory.ORNAMENTAL_CUT_FLOWER,
            heroSummary = "Kenya's leading export filler cut flower (Million Stars, White Love, Danglory). Characterized by thousands of snow-white cloud-like miniature blooms on delicate branched panicles. Requires supplemental gibberellic acid (GA3) and long-day lighting.",
            imageResId = R.drawable.sample_rose_powdery_mildew,
            ecologicalAltitudeMeters = "1,800m - 2,400m ASL (Naivasha, Thika, Athi River)",
            ecologicalRainfallMm = "Greenhouse drip fertigation",
            optimalTemperatureCelsius = "20°C - 26°C day / 12°C - 15°C night",
            preferredSoilTypeAndTexture = "Extremely light, well-aerated sandy loam with excellent hydraulic drainage",
            optimalSoilPhRange = "6.5 - 7.5 (tolerates slightly alkaline soils better than other cut flowers)",
            spacingBetweenRowsCm = 40,
            spacingWithinRowCm = 30,
            plantPopulationPerAcre = "18,000 - 24,000 plants/acre",
            seedRatePerAcre = "Certified tissue-culture or grafted rooted cuttings",
            averageYieldPerAcre = "180,000 - 250,000 export stems per acre annually",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Establishment & Pinching",
                    durationOrTiming = "Weeks 1 to 5",
                    comprehensiveNotes = "Plant rooted cuttings in raised beds. Pinch terminal shoot 4 weeks after transplanting leaving 4 to 5 pairs of basal leaves to encourage 8-12 basal flowering stems.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative Basal Drip Feed",
                        timingText = "From week 2 onwards",
                        recommendedBrand = "NPK 19-19-19 + Calcium Nitrate + Magnesium Sulfate",
                        npkRatio = "18-10-18 + 5Ca + TE",
                        ratePerHectare = "30 kg/ha/week",
                        ratePerAcre = "12 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.2g/L water (EC 1.3 mS/cm)",
                        placementAndMethod = "Drip fertigation through two drip lines per bed.",
                        primaryFunction = "Encourages vigorous basal crown shoot breaks."
                    ),
                    agronomicPractices = listOf(
                        "Pinch terminal shoot when 15cm high",
                        "Install 3 layers of wide-mesh support netting",
                        "Scout for Phytophthora and Pythium root rot"
                    ),
                    keyRiskFactors = "Root rot in heavy waterlogged soils; Spider mites bronzing fine leaves."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Elongation, GA3 Application & Night Lighting",
                    durationOrTiming = "Weeks 6 to 12",
                    comprehensiveNotes = "Gypsophila is an obligate long-day plant. Provide 4 hours night-break lighting (10 PM to 2 AM) and apply Gibberellic Acid (GA3 @ 150-200 ppm) when shoots are 10-15cm to stimulate massive internode elongation and uniform flowering panicles.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Stem Elongation & Flowering Feed",
                        timingText = "During bolting and panicle expansion",
                        recommendedBrand = "Potassium Nitrate (13-0-46) + Multi-K + Micronutrients",
                        npkRatio = "15-8-28 + TE",
                        ratePerHectare = "40 kg/ha/week",
                        ratePerAcre = "16 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.5g/L water (EC 1.5 mS/cm)",
                        placementAndMethod = "Drip fertigation paired with foliar GA3 sprays.",
                        primaryFunction = "Drives rigid stem panicles and maximizes florets per stem."
                    ),
                    agronomicPractices = listOf(
                        "Spray GA3 (150 ppm) in cool morning hours at 15cm stem height",
                        "Maintain night-break lighting until first florets show color",
                        "Control powdery mildew and thrips"
                    ),
                    keyRiskFactors = "Failure to bolt (rosetting) if lighting or GA3 is insufficient; Powdery mildew."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Export Standard: 20% to 30% of miniature florets open (White cloud appearance)",
                    "Terminal florets white, crisp, and firmly attached without browning",
                    "Remaining 70% buds plump and ready to open in vase"
                ),
                harvestingGuidelines = "Cut stems in early morning when turgid. Shake gently to untangle adjacent stems. Place immediately into clean sugar-preservative pulse solution (8-10% sucrose + TOG).",
                curingAndFieldDrying = "Do not dry. Hydro-cool to 4°C. Pulse with sugar solution for 12 hours in cold store to ensure all tight buds open fully in customer vase.",
                gradingAndStandardization = listOf(
                    "Grade A: Stem length >= 70cm, weight >= 35g per stem, voluminous head cloud",
                    "Grade B: Length 60 - 70cm, weight 25 - 35g",
                    "Rejects: Yellowing stems, broken panicle branches, Botrytis grey mold"
                ),
                packagingAndStorageCondition = "Bunched in 250g or 300g bunches with protective paper wrap. Shipped in telescopic floral boxes at 2°C - 4°C.",
                storagePestAndLossMitigation = "Pulsing with chlorine dioxide prevents bacterial slime plugging xylem vessels.",
                commercialValueAddition = "Tinted rainbow gypsophila (dip-dyed pink, blue, lavender, gold) which fetch 200% premium in Europe and Gulf states."
            )
        ),

        // 4. HYPERICUM / ST. JOHN'S WORT (Hypericum androsaemum)
        CropProductionProfile(
            id = "crop_hypericum",
            cropName = "Hypericum (Cut Berry Stems)",
            botanicalName = "Hypericum androsaemum L.",
            commonKenyanNames = "Hypericum, Berries, Maua ya Berries",
            category = CropCategory.ORNAMENTAL_CUT_FLOWER,
            heroSummary = "Major Kenyan export cut flower prized not for petals, but for its glossy, jewel-like ornamental berries in red, pink, peach, brown, and green hues. Hardwood perennial bush grown under open shade nets in Nakuru, Naivasha, and Nyandarua.",
            imageResId = R.drawable.sample_rose_spider_mite,
            ecologicalAltitudeMeters = "1,900m - 2,600m ASL (cool altitudes intensify berry coloration)",
            ecologicalRainfallMm = "900mm - 1,400mm or drip irrigation",
            optimalTemperatureCelsius = "16°C - 24°C",
            preferredSoilTypeAndTexture = "Deep, fertile volcanic loam with good drainage and rich organic matter",
            optimalSoilPhRange = "5.5 - 6.5",
            spacingBetweenRowsCm = 50,
            spacingWithinRowCm = 30,
            plantPopulationPerAcre = "16,000 - 20,000 bushes/acre",
            seedRatePerAcre = "Certified vegetative rooted cuttings (Flair, Magical, Coco series)",
            averageYieldPerAcre = "120,000 - 180,000 berry stems/acre annually across 2-3 flushes",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Stumping / Pruning & Vegetative Flush",
                    durationOrTiming = "Weeks 1 to 6 post-harvest cycle",
                    comprehensiveNotes = "After each berry harvest, bushes are hard-pruned (stumped) back to 10cm above ground to initiate a flush of 8-12 vigorous, upright, straight unbranched stems.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Vegetative Stumping Feed",
                        timingText = "Immediately after stumping and weeding",
                        recommendedBrand = "YaraMila CEREAL (23-10-5) or CAN (26% N) + DAP",
                        npkRatio = "23-10-5 or 26-0-0",
                        ratePerHectare = "150 kg/ha",
                        ratePerAcre = "60 kg/acre (1.2 x 50kg bags)",
                        ratePerPlantOrKnapsack = "4 - 5 grams per bush incorporated around crown",
                        placementAndMethod = "Ring placement 15cm from stump, covered with mulch and irrigated.",
                        primaryFunction = "Stimulates rapid basal bud-burst and upright cane extension."
                    ),
                    agronomicPractices = listOf(
                        "Hard prune old wood to 10cm using clean disinfected shears",
                        "Paint cut wounds with copper paste to prevent Botryosphaeria stem canker",
                        "Scout for Rust (Melampsora hypericorum) causing bright yellow-orange pustules"
                    ),
                    keyRiskFactors = "Hypericum Rust causing leaf dropping and bare stems; Botryosphaeria dieback."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Flowering, Berry Set & Berry Sizing",
                    durationOrTiming = "Weeks 8 to 14",
                    comprehensiveNotes = "Yellow flowers open at stem terminals and drop petals within 5 days, exposing miniature ovaries that rapidly swell into glossy, waxy berries. Potassium and calcium are critical for firm, crack-free berries with high gloss.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Berry Swelling High-K Fertilizer",
                        timingText = "From petal fall until harvest",
                        recommendedBrand = "YaraMila WINNER (15-9-20) or Potassium Sulfate (0-0-50) + Boron",
                        npkRatio = "15-9-20 or 0-0-50 + TE",
                        ratePerHectare = "180 kg/ha",
                        ratePerAcre = "75 kg/acre",
                        ratePerPlantOrKnapsack = "5 - 6 grams per plant side-dressed",
                        placementAndMethod = "Band beside plant row and irrigate regularly.",
                        primaryFunction = "Expands berry diameter (>12mm) and deposits thick waxy cuticle that resists desiccation."
                    ),
                    agronomicPractices = listOf(
                        "De-leaf bottom 15cm foliage to improve air circulation",
                        "Maintain steady irrigation (erratic watering causes berry skin splitting)",
                        "Apply preventive azoxystrobin or difenoconazole against Anthracnose fruit spots"
                    ),
                    keyRiskFactors = "Berry splitting; Anthracnose black sunken spots on berry skins; Thrips scarring."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Berries fully expanded to mature diameter (10 - 15mm depending on variety)",
                    "Full characteristic variety color developed (Glossy scarlet red, cherry, peach, green)",
                    "Petals and stamens completely detached and cleaned off calyx",
                    "Minimum 8 - 14 berries per stem head cluster"
                ),
                harvestingGuidelines = "Cut stems 60-80cm in early morning. Strip bottom 15cm leaves and immediately place stems in clean acidified water with chlorine dioxide.",
                curingAndFieldDrying = "Do not dry. Hydro-cool to 3°C - 5°C in packhouse.",
                gradingAndStandardization = listOf(
                    "Grade 1 (Export): Length >= 70cm, minimum 10 clean unblemished berries per head, zero rust on leaves",
                    "Grade 2: Length 55 - 65cm, minimum 7 berries per head",
                    "Rejects: Split berries, rust pustules on leaves, thrips scabs"
                ),
                packagingAndStorageCondition = "Sleeved in 10-stem bunches. Packed in export cartons at 2°C - 4°C with 90% RH.",
                storagePestAndLossMitigation = "Pre-cooling prevents berry mold and dehydration during air transit to Dutch flower auctions.",
                commercialValueAddition = "Autumn and Christmas floral arrangements, luxury bouquets with roses and lilies."
            )
        ),

        // 5. ASIATIC & ORIENTAL LILIES (Lilium spp.)
        CropProductionProfile(
            id = "crop_lilies",
            cropName = "Lilies (Asiatic & Oriental)",
            botanicalName = "Lilium longiflorum / Lilium hybrids",
            commonKenyanNames = "Lily, Maua ya Yungiyungi",
            category = CropCategory.ORNAMENTAL_CUT_FLOWER,
            heroSummary = "Premium high-value greenhouse cut flower known for massive trumpet and star-shaped blooms with intense fragrance. Grown from imported vernalized bulbs (frozen bulbs) planted in raised boxes or ground beds in Naivasha and Limuru.",
            imageResId = R.drawable.sample_rose_powdery_mildew,
            ecologicalAltitudeMeters = "1,800m - 2,600m ASL",
            ecologicalRainfallMm = "Controlled greenhouse drip irrigation",
            optimalTemperatureCelsius = "18°C - 22°C day / 12°C - 15°C night",
            preferredSoilTypeAndTexture = "Light, deep, well-aerated sandy loam with low salt content (EC < 1.0 mS/cm)",
            optimalSoilPhRange = "5.8 - 6.5 for Asiatic; 6.2 - 6.8 for Oriental",
            spacingBetweenRowsCm = 15,
            spacingWithinRowCm = 12,
            plantPopulationPerAcre = "120,000 - 140,000 bulbs/acre (50-60 bulbs per m²)",
            seedRatePerAcre = "Imported certified vernalized bulbs (Size 14/16 or 16/18 cm circumference)",
            averageYieldPerAcre = "110,000 - 130,000 export stems per single crop cycle (80-100 days)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Bulb Thawing, Planting & Stem Rooting",
                    durationOrTiming = "Weeks 1 to 4",
                    comprehensiveNotes = "Thaw frozen bulbs slowly at 10°C - 12°C in the dark. Plant deeply (8-10cm soil above bulb nose) because lilies form critical adventitious stem roots above the bulb that take up 80% of nutrients and water.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Stem Root Establishment Feed",
                        timingText = "From week 3 when shoots emerge",
                        recommendedBrand = "Calcium Nitrate (YaraTera CALCINIT) + Balanced NPK",
                        npkRatio = "15-5-20 + 19% Ca",
                        ratePerHectare = "25 kg/ha/week",
                        ratePerAcre = "10 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.0g/L water (EC 1.1 mS/cm)",
                        placementAndMethod = "Drip fertigation under shade net.",
                        primaryFunction = "Stimulates adventitious stem roots and prevents upper leaf scorching."
                    ),
                    agronomicPractices = listOf(
                        "Plant bulbs at 10cm depth to ensure stem roots remain underground",
                        "Keep soil evenly moist; avoid wet/dry cycling",
                        "Install 2-3 layers of support netting before shoots exceed 30cm"
                    ),
                    keyRiskFactors = "Basal plate rot (Fusarium oxysporum f.sp. lilii); Pythium root rot."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Bud Elongation & Upper Leaf Scorch Prevention",
                    durationOrTiming = "Weeks 5 to 10",
                    comprehensiveNotes = "Rapid stem elongation and flower bud cluster emergence. Calcium deficiency under rapid transpiration causes Upper Leaf Scorch (young leaf tips collapse and burn). Maintain high calcium and steady greenhouse humidity.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Bud Swelling & Calcium Nutrition",
                        timingText = "From visible bud stage until harvest",
                        recommendedBrand = "YaraLiva NITRABOR (15.4-0-0 + Ca + B) + Potassium Nitrate",
                        npkRatio = "16-6-26 + 12% Ca + 0.1% B",
                        ratePerHectare = "35 kg/ha/week",
                        ratePerAcre = "14 kg/acre/week",
                        ratePerPlantOrKnapsack = "1.4g/L water (EC 1.4 mS/cm)",
                        placementAndMethod = "Drip fertigation with early morning calcium foliar sprays.",
                        primaryFunction = "Hardens flower bud pedicels and completely eliminates leaf scorch."
                    ),
                    agronomicPractices = listOf(
                        "Shade greenhouse (30-40% shade) during intense midday sun to lower transpiration rate",
                        "Scout for Aphids and Botrytis elliptica (Lily Fire leaf blight)",
                        "Ensure support netting keeps massive flower heads perfectly straight"
                    ),
                    keyRiskFactors = "Upper leaf scorch from calcium transpiration deficit; Botrytis elliptica orange-brown leaf spots."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Puffy colored bud stage: Bottom first flower bud fully swollen and showing vibrant color, but NOT yet popped open",
                    "Minimum 3 to 7 buds per stem depending on bulb grade (14/16 = 3-5 buds; 16/18 = 5-8 buds)",
                    "Leaves crisp, dark green, and free of yellowing"
                ),
                harvestingGuidelines = "Harvest by cutting stem 10cm above bulb level. Place immediately in clean buckets with cold water containing silver thiosulfate (STS) and gibberellic acid (GA4+7) to prevent leaf yellowing.",
                curingAndFieldDrying = "Do not dry. Hydro-cool down to 1°C - 3°C in cold room.",
                gradingAndStandardization = listOf(
                    "Grade 1: Stem length >= 80cm, minimum 4-5 well-formed buds, zero blemishes",
                    "Grade 2: Length 70 - 80cm, 3-4 buds",
                    "Rejects: Popped open blooms (pollen stains petals), leaf scorch burns, Botrytis spots"
                ),
                packagingAndStorageCondition = "Sleeved in 5-stem bunches with bud protection netting. Shipped vertically in water packs or dry floral cartons at 1°C - 3°C.",
                storagePestAndLossMitigation = "Pre-treatment with Chrysal BVB (anti-leaf-yellowing hormone solution) keeps foliage dark green for 3 weeks in vase.",
                commercialValueAddition = "Wedding floristry, luxury church events, and upscale international hotel arrangements."
            )
        )
    )
}
