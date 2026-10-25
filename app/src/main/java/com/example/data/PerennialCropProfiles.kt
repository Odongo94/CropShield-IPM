package com.example.data

import com.example.R
import com.example.model.CropCategory
import com.example.model.CropPostHarvestGuide
import com.example.model.CropProductionProfile
import com.example.model.ProductionStageGuide
import com.example.model.StageFertilizerApplication

/**
 * Commercial Kenyan Perennial Cash Crops & High-Value Fruits
 * (Arabica Coffee, Tea, Bananas, Mangoes, Capsicum, Watermelon).
 */
object PerennialCropProfiles {

    val list: List<CropProductionProfile> = listOf(
        // 1. ARABICA COFFEE (Coffea arabica)
        CropProductionProfile(
            id = "crop_coffee",
            cropName = "Arabica Coffee (Kahawa)",
            botanicalName = "Coffea arabica L.",
            commonKenyanNames = "Kahawa, Coffee (SL28, SL34, Ruiru 11, Batian)",
            category = CropCategory.FRUITS,
            heroSummary = "Kenya's renowned export beverage crop commanding global auction premiums for its bright phosphoric acidity and blackcurrant aroma. Grown across Central, Eastern, and Rift Valley volcanic slopes. Demands systematic pruning, copper sprays for CBD, and balanced N-K nutrition.",
            imageResId = R.drawable.sample_bean_rust,
            ecologicalAltitudeMeters = "1,400m - 2,100m ASL (High altitude develops dense, aromatic AA beans)",
            ecologicalRainfallMm = "1,200mm - 1,800mm bi-modal rainfall",
            optimalTemperatureCelsius = "16°C - 24°C",
            preferredSoilTypeAndTexture = "Deep, friable, porous red volcanic loam (Nitisols > 2m deep)",
            optimalSoilPhRange = "5.0 - 6.0 (Apply agricultural lime if pH < 4.8)",
            spacingBetweenRowsCm = 275,
            spacingWithinRowCm = 275,
            plantPopulationPerAcre = "540 trees/acre (standard 2.75m x 2.75m) or 1,000 trees/acre (high density 2m x 2m for Ruiru 11/Batian)",
            seedRatePerAcre = "Certified grafted or clonal seedlings from Coffee Research Institute (CRI - Ruiru)",
            averageYieldPerAcre = "10 - 20 kg cherry per tree per year (5,000 - 10,000 kg cherry/acre)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Main Flowering & Canopy Rejuvenation",
                    durationOrTiming = "March - April (onset of Long Rains)",
                    comprehensiveNotes = "Showers trigger synchronous bloom of fragrant white star blossoms. Prune dead wood, suckers, and crossing branches. Spray preventive copper hydroxide or chlorothalonil to shield young pinhead berries from Coffee Berry Disease (CBD).",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Post-Bloom Fertilizer (Long Rains)",
                        timingText = "April - May when topsoil is moist",
                        recommendedBrand = "YaraMila CEREAL (23-10-5) or CAN (26% N) / NPK 20-10-10",
                        npkRatio = "23-10-5 or 20-10-10",
                        ratePerHectare = "250 kg/ha",
                        ratePerAcre = "100 kg/acre (2 x 50kg bags)",
                        ratePerPlantOrKnapsack = "200 grams per tree spread under foliage drip-line",
                        placementAndMethod = "Broadcast in 1m-wide circular band under drip-line, incorporated into mulch.",
                        primaryFunction = "Stimulates active vegetative flush and rapid berry expansion."
                    ),
                    agronomicPractices = listOf(
                        "Pruning: remove non-productive suckers, water shoots, and dead twigs",
                        "Preventative spray against Coffee Berry Disease (Colletotrichum kahawae)",
                        "Maintain thick organic mulch (grass/stover) to conserve soil moisture"
                    ),
                    keyRiskFactors = "Coffee Berry Disease causing black anthracnose lesions on green pinheads; Coffee Leaf Rust."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Berry Expansion & Main Top-Dress (Short Rains)",
                    durationOrTiming = "October - November",
                    comprehensiveNotes = "Green berries expand and fill the bean cavity with endosperm. Potassium demand peaks to maximize bean density and test weight.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "2nd Berry Bulking High-K Fertilizer",
                        timingText = "October during short rains",
                        recommendedBrand = "YaraMila WINNER (15-9-20 + Mg + S) or NPK 17-17-17",
                        npkRatio = "15-9-20 or 17-17-17",
                        ratePerHectare = "250 kg/ha",
                        ratePerAcre = "100 kg/acre",
                        ratePerPlantOrKnapsack = "200 grams per tree under drip-line",
                        placementAndMethod = "Circular band under tree canopy incorporated into topsoil.",
                        primaryFunction = "Powers carbohydrate transfer to expanding bean endosperm (producing Grade AA/AB size)."
                    ),
                    agronomicPractices = listOf(
                        "Scout for Antestia Bug (Antestiopsis spp.) causing black bean defect and zebra striping",
                        "Inspect for Coffee Leaf Rust (Hemileia vastatrix) orange powdery spots under leaves",
                        "Ensure trees are well mulched before the dry December season"
                    ),
                    keyRiskFactors = "Antestia bug damage causing potato-taste defect; Coffee Berry Borer (Hypothenemus hampei)."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Cherry reaches uniform deep ruby-red color (ripe cherry)",
                    "Soft to gentle thumb pressure without green shoulders",
                    "Never pick green, under-ripe, or black over-ripe cherries"
                ),
                harvestingGuidelines = "Selective hand-picking of ripe red cherries only. Strip pickers destroy quality. Deliver to cooperative wet mill factory on the same day of picking within 8 hours.",
                curingAndFieldDrying = "Wet processing: disc pulping to remove skin, fermentation for 24-36 hours to break down mucilage, clean mountain water washing, followed by sun-drying on raised African beds until parchment moisture reaches 10.5% - 11.5%.",
                gradingAndStandardization = listOf(
                    "Grade AA: Bean screen size 18 (7.2mm), large, dense, zero defects",
                    "Grade AB: Screen size 16 (6.8mm), premium quality",
                    "Grade PB (Peaberry): Single round oval bean",
                    "Grade C, TT, T, Buni: Low grade / unpulped cherry dry lots"
                ),
                packagingAndStorageCondition = "Store dry parchment in clean sisal bags inside cool, dark, well-ventilated cooperative stores away from chemicals and diesel fumes.",
                storagePestAndLossMitigation = "Maintaining parchment moisture strictly between 10.5% and 11.5% prevents mold growth and preserves vibrant enzymatic cupping scores.",
                commercialValueAddition = "Direct specialty trade export to micro-roasters in Europe/US, local roasting and packing of single-origin branded ground coffee."
            )
        ),

        // 2. TEA (Camellia sinensis)
        CropProductionProfile(
            id = "crop_tea",
            cropName = "Tea (Chai)",
            botanicalName = "Camellia sinensis (L.) Kuntze",
            commonKenyanNames = "Chai, Tea (TRFK clones: 303/577, 31/8, Purple Tea TRFK 306)",
            category = CropCategory.FRUITS,
            heroSummary = "Kenya's premier foreign exchange agricultural export crop. Cultivated along high-rainfall volcanic ridges east and west of the Rift Valley (Kericho, Bomet, Nandi, Kiambu, Nyeri, Kisii). Demands strictly acidic soil (pH 4.5 - 5.5) and balanced annual NPK 26-5-5 fertilizer.",
            imageResId = R.drawable.sample_bean_rust,
            ecologicalAltitudeMeters = "1,500m - 2,700m ASL",
            ecologicalRainfallMm = "1,400mm - 2,500mm well-distributed annual rainfall",
            optimalTemperatureCelsius = "13°C - 22°C (cool temperatures enhance rich theaflavin aroma)",
            preferredSoilTypeAndTexture = "Deep, highly permeable, well-aerated red volcanic Nitisols (>2m depth)",
            optimalSoilPhRange = "4.5 - 5.5 (tea is an acidophilic plant; calcification or liming is fatal to tea bushes)",
            spacingBetweenRowsCm = 120,
            spacingWithinRowCm = 75,
            plantPopulationPerAcre = "4,400 - 5,000 bushes/acre (standard 4ft x 2.5ft rectangular planting)",
            seedRatePerAcre = "Certified vegetative single-node clonal cuttings from KTDA/TRFK nurseries",
            averageYieldPerAcre = "8,000 - 14,000 kg green leaf/acre annually",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Plucking Table Maintenance & Basal Flush",
                    durationOrTiming = "Year-round with peak flushes during Long & Short Rains",
                    comprehensiveNotes = "Maintain a flat, horizontal plucking table at waist height (75-80cm). Pluck strictly 'two leaves and a bud' every 7 to 10 days. Apply specialized NPK 26-5-5 or 25-5-5 tea fertilizer annually.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Annual NPK Tea Fertilizer",
                        timingText = "Applied in split doses during onset of rains (April & October)",
                        recommendedBrand = "NPK 26-5-5 (KTDA Standard Tea Fertilizer) or YaraMila TEA",
                        npkRatio = "26-5-5 (High Nitrogen to fuel continuous leaf shoot flush)",
                        ratePerHectare = "400 - 500 kg/ha annually",
                        ratePerAcre = "160 - 200 kg/acre annually (3 to 4 x 50kg bags)",
                        ratePerPlantOrKnapsack = "40 - 50 grams per mature bush per year",
                        placementAndMethod = "Broadcast evenly under plucking canopy when soil is wet; never apply on dry soil.",
                        primaryFunction = "Fuels continuous regeneration of tender, succulent two-leaves-and-a-bud shoots."
                    ),
                    agronomicPractices = listOf(
                        "Selective hand plucking: strictly pluck two tender leaves and terminal apical bud",
                        "Maintain plucking rounds of 7-10 days in wet warm weather (14 days in dry cold)",
                        "Pruning every 4 years: cut bushes down to 45-50cm to rejuvenate table architecture"
                    ),
                    keyRiskFactors = "Coarse plucking (taking 3-4 hard leaves) reducing factory quality; Tea Thrips and Red Crevice Mites."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Two young tender leaves and one unopened terminal bud",
                    "Soft succulent leaf texture, not hard or leathery",
                    "Minimum 70% fine leaf plucking standard at factory collection centers"
                ),
                harvestingGuidelines = "Pluck into ventilated woven baskets or plastic mesh crates. Never compress or sit on green leaf bags. Transport to KTDA tea factory within 6 hours of plucking to avoid internal fermentation scorch.",
                curingAndFieldDrying = "Factory processing: Controlled withering (12-18 hrs), CTC (Crush, Tear, Curl) maceration, continuous oxidation (fermentation) to develop deep copper theaflavins, fluid bed drying down to 3.0% moisture, and electrostatic fiber extraction.",
                gradingAndStandardization = listOf(
                    "Primary Grades: BP1 (Broken Pekoe 1), PF1 (Pekoe Fanning 1 - Kenya's main export grade), PD (Pekoe Dust), D1 (Dust 1)",
                    "Secondary Grades: Fannings, Dust 2, BMF (Broken Mixed Fannings)"
                ),
                packagingAndStorageCondition = "Packed in multi-layer paper sacks with inner aluminum foil lining (50kg export sacks). Stored at < 3.0% moisture away from moisture and sunlight.",
                storagePestAndLossMitigation = "Hermetic aluminum foil prevents moisture absorption and oxidation degradation.",
                commercialValueAddition = "Specialty orthodox teas, purple tea (rich in anthocyanins and health polyphenols), green tea, and flavored tea bags."
            )
        ),

        // 3. BANANAS & PLANTAINS (Musa acuminata)
        CropProductionProfile(
            id = "crop_banana",
            cropName = "Bananas & Plantains (Ndizi)",
            botanicalName = "Musa acuminata Colla / Musa balbisiana",
            commonKenyanNames = "Ndizi, Matoke (Cooking), Kisii Banana, Grand Nain, Williams, Fhia",
            category = CropCategory.FRUITS,
            heroSummary = "Kenya's premier food security and commercial fruit crop (Kisii, Meru, Murang'a, Embu, Kakamega). Demands tissue-culture seedlings, large composted planting pits (3ft x 3ft x 3ft), heavy potassium nutrition, and desuckering to 3 stems per stool.",
            imageResId = R.drawable.sample_late_blight,
            ecologicalAltitudeMeters = "0m - 2,000m ASL",
            ecologicalRainfallMm = "1,200mm - 2,200mm (or trench/drip irrigation)",
            optimalTemperatureCelsius = "22°C - 30°C",
            preferredSoilTypeAndTexture = "Deep, rich alluvial or volcanic loam (>1m depth) with massive organic matter",
            optimalSoilPhRange = "5.5 - 6.8",
            spacingBetweenRowsCm = 300,
            spacingWithinRowCm = 300,
            plantPopulationPerAcre = "450 - 500 stools/acre (at 3m x 3m spacing)",
            seedRatePerAcre = "Certified disease-free Tissue Culture (TC) plantlets (KALRO / JKUAT)",
            averageYieldPerAcre = "20 - 35 tonnes/acre annually (average bunch weight: 35 - 65 kg)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Hole Digging, Compost Incorporation & Planting",
                    durationOrTiming = "At planting (onset of rains)",
                    comprehensiveNotes = "Dig massive 90cm x 90cm x 90cm (3ft x 3ft x 3ft) pits. Separate topsoil and subsoil. Mix topsoil with 2-3 wheelbarrows (40-60kg) of well-decomposed cattle manure, 200g lime (if pH < 5.5), and 200g phosphorus fertilizer. Plant tissue culture seedling at 10cm deeper than nursery bag level.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Hole Fertilizer",
                        timingText = "In planting hole thoroughly mixed with topsoil & manure",
                        recommendedBrand = "DAP (18-46-0) or Minjingu Mazao Plus (0-31-0) / NPK 17-17-17",
                        npkRatio = "18-46-0 or 17-17-17",
                        ratePerHectare = "100 kg/ha",
                        ratePerAcre = "45 - 50 kg/acre (1 x 50kg bag)",
                        ratePerPlantOrKnapsack = "200 grams per planting pit",
                        placementAndMethod = "Thoroughly mixed with 40kg manure in the bottom half of the hole.",
                        primaryFunction = "Stimulates rapid corm anchor establishment and thick feeder root fan."
                    ),
                    agronomicPractices = listOf(
                        "Plant certified tissue culture plantlets to guarantee freedom from Panama disease and nematodes",
                        "Construct sunken water retention basins around each hole to harvest rainwater",
                        "Apply thick 15cm organic mulch keeping 15cm clear of pseudo-stem"
                    ),
                    keyRiskFactors = "Banana Xanthomonas Wilt (BXW - bacterial wilt causing yellow bacterial ooze and rotting fruit); Banana Weevil."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Stool Management (Desuckering) & Heavy Potassium Top-Dress",
                    durationOrTiming = "Ongoing maintenance (every 3 months)",
                    comprehensiveNotes = "Strict stool management rule: Maintain only 3 stems per stool at any time: 1 Mother (bearing bunch), 1 Daughter (half grown), and 1 Granddaughter (follower sucker). Cut off all extra suckers at soil level.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Quarterly High-K Top-Dress",
                        timingText = "Every 3 months (4 applications per year)",
                        recommendedBrand = "YaraMila WINNER (15-9-20) or Muriate of Potash (MOP 0-0-60) + CAN",
                        npkRatio = "15-9-20 or 0-0-60",
                        ratePerHectare = "300 kg/ha annually",
                        ratePerAcre = "120 kg/acre annually (2.5 bags/acre/year)",
                        ratePerPlantOrKnapsack = "250 grams per stool per application",
                        placementAndMethod = "Broadcast in 1m circle around stool base under mulch; water thoroughly.",
                        primaryFunction = "Potassium builds massive bunches (>50kg), thick fingers, and high sugar content."
                    ),
                    agronomicPractices = listOf(
                        "Desuckering: remove excess suckers to maintain exactly 3 stems per mat",
                        "De-leafing: prune dead and hanging leaves to allow sunlight and reduce Black Sigatoka spores",
                        "Propping: support heavy bunches with double bamboo or wooden Y-poles to prevent toppling in wind"
                    ),
                    keyRiskFactors = "Wind toppling of unpropped heavy bunch trees; Black Sigatoka (Mycosphaerella fijiensis) leaf streaks."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Fingers become well rounded and lose angular ridge ribs (3/4 full to full maturity)",
                    "Flower bracts and dead floral remnants dry up and fall off finger tips",
                    "Inner pulp turns light cream to pale yellow"
                ),
                harvestingGuidelines = "Nick pseudo-stem halfway up and ease the bunch down gently. Cut bunch stalk leaving 30cm handle. Never drop bunch on hard ground; catch with foam pads.",
                curingAndFieldDrying = "Do not dry. Wash bunch in clean water containing 1% alum to remove latex sap stain. Grade and de-hand into clusters.",
                gradingAndStandardization = listOf(
                    "Grade 1: Clean, unbruised fingers, finger length > 20cm, uniform bunch > 40kg",
                    "Grade 2: Medium bunch 25 - 39kg, finger length 15 - 20cm",
                    "Rejects: Bruised fingers, sunscald, BXW bacterial internal discoloration"
                ),
                packagingAndStorageCondition = "Pack de-handed clusters in 18kg corrugated cardboard telescopic cartons with foam lining. Transport in cushioned trucks.",
                storagePestAndLossMitigation = "Alum water wash prevents sap burn on peel; maintain cool storage at 13°C - 15°C (never store below 12°C to prevent chilling injury).",
                commercialValueAddition = "Vacuum-sealed peeled matoke chunks, banana crisps (chips), dried banana flour, and fermented banana juice/wine."
            )
        ),

        // 4. WATERMELON (Citrullus lanatus)
        CropProductionProfile(
            id = "crop_watermelon",
            cropName = "Watermelon (Tikiti Maji)",
            botanicalName = "Citrullus lanatus (Thunb.) Matsum. & Nakai",
            commonKenyanNames = "Tikiti Maji, Sukari F1, Sweet Rose, Asali F1",
            category = CropCategory.FRUITS,
            heroSummary = "Fast-growing high-margin horticultural cash crop (Machakos, Embu, Kirinyaga, Garissa, Baringo, Kilifi). Takes 75-90 days from planting to harvest. Demands sandy loam soils, high early phosphorus, abundant potassium for brix sweetness, and strict fruit fly protection.",
            imageResId = R.drawable.sample_late_blight,
            ecologicalAltitudeMeters = "0m - 1,500m ASL (Loves hot, sunny lowland climates)",
            ecologicalRainfallMm = "400mm - 600mm with furrow or drip irrigation",
            optimalTemperatureCelsius = "24°C - 35°C (high heat accelerates sugar accumulation)",
            preferredSoilTypeAndTexture = "Deep, loose, fertile sandy loam with superior drainage",
            optimalSoilPhRange = "6.0 - 6.8",
            spacingBetweenRowsCm = 200,
            spacingWithinRowCm = 100,
            plantPopulationPerAcre = "2,000 - 2,500 plants/acre (1-2 seeds per station at 2m x 1m)",
            seedRatePerAcre = "500 - 700 grams/acre (Hybrid seed: Sukari F1, Sweet Rose F1, Asali F1, Julie F1)",
            averageYieldPerAcre = "20 - 35 tonnes/acre (3,000 - 4,000 marketable melons of 8-12kg each)",
            stages = listOf(
                ProductionStageGuide(
                    stageNumber = 1,
                    stageName = "Land Preparation, Basin Digging & Planting",
                    durationOrTiming = "At planting",
                    comprehensiveNotes = "Dig shallow basins (30cm x 30cm) spaced 2m x 1m. Incorporate 1-2 shovels of well-rotted manure and 20g basal phosphorus fertilizer per basin. Plant 2 seeds per basin at 2cm depth.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Basal Planting Fertilizer",
                        timingText = "In planting basins mixed with topsoil & manure",
                        recommendedBrand = "YaraMila UNIK 17 (17-17-17) or DAP (18-46-0) / Mavuno Planting",
                        npkRatio = "17-17-17 or 18-46-0",
                        ratePerHectare = "150 kg/ha",
                        ratePerAcre = "60 kg/acre (1.2 x 50kg bags)",
                        ratePerPlantOrKnapsack = "20 - 25 grams per basin",
                        placementAndMethod = "Mixed thoroughly with soil and manure at bottom of basin before sowing seeds.",
                        primaryFunction = "Stimulates rapid taproot penetration to access subsoil moisture."
                    ),
                    agronomicPractices = listOf(
                        "Drench basins with imidacloprid to protect emerging seedlings from cutworms and crickets",
                        "Thin to exactly 1 vigorous plant per basin at 2 weeks after emergence",
                        "Ensure immediate irrigation after seed sowing"
                    ),
                    keyRiskFactors = "Cutworms severing young cotyledons; Damping-off in waterlogged basins."
                ),
                ProductionStageGuide(
                    stageNumber = 2,
                    stageName = "Vine Running & 1st Top-Dress",
                    durationOrTiming = "3 to 4 weeks after emergence (vines reach 30-50cm)",
                    comprehensiveNotes = "Vines branch and run vigorously across the ground. Nitrogen top-dressing drives strong vegetative canopy framework before flowering.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "1st Top-Dress (Vine Runner Booster)",
                        timingText = "21 to 25 days after emergence",
                        recommendedBrand = "CAN (26% N) or YaraLiva NITRABOR (15.4-0-0 + Ca + B)",
                        npkRatio = "15.4-0-0 + Ca + B",
                        ratePerHectare = "125 kg/ha",
                        ratePerAcre = "50 kg/acre (1 x 50kg bag)",
                        ratePerPlantOrKnapsack = "20 grams per plant ringed 20cm from crown",
                        placementAndMethod = "Ring dress around vine crown and water in thoroughly.",
                        primaryFunction = "Drives rapid vine branching and sets robust flowering nodes."
                    ),
                    agronomicPractices = listOf(
                        "Weed thoroughly before vines cover the ground",
                        "Scout for Melon Fruit Fly (Bactrocera cucurbitae) using cue-lure pheromone traps",
                        "Apply preventive spray for Powdery Mildew and Downy Mildew"
                    ),
                    keyRiskFactors = "Melon Fruit Flies stinging young ovary flowers; Powdery Mildew."
                ),
                ProductionStageGuide(
                    stageNumber = 3,
                    stageName = "Fruit Bulking, Sugar Setting & 2nd Top-Dress",
                    durationOrTiming = "6 to 10 weeks after emergence",
                    comprehensiveNotes = "Baby melons swell rapidly. Potassium is decisive for high brix sweetness (sugar content > 11%), deep crimson red flesh, and thick rind to resist transit cracking. Place clean straw under fruits to prevent soil rot.",
                    fertilizerRecommendation = StageFertilizerApplication(
                        stageLabel = "Fruit Bulking High-K Fertilizer",
                        timingText = "At onset of fruit swelling, repeated at 14 days",
                        recommendedBrand = "YaraMila WINNER (15-9-20) or Potassium Sulfate (0-0-50) + Multi-K foliar",
                        npkRatio = "15-9-20 or 0-0-50",
                        ratePerHectare = "150 kg/ha",
                        ratePerAcre = "60 kg/acre",
                        ratePerPlantOrKnapsack = "25 grams per plant banded",
                        placementAndMethod = "Side-dress along drip lines; spray foliar Multi-K (50g/20L knapsack).",
                        primaryFunction = "Maximizes melon diameter, sugar brix level, and deep red lycopene pigment."
                    ),
                    agronomicPractices = listOf(
                        "Prune to 2-3 well-shaped melons per vine; remove deformed and misshapen fruits",
                        "Place clean dry straw or cardboard under melons to keep them off damp soil",
                        "Gradually reduce irrigation 10 days before harvest to concentrate natural fruit sugars"
                    ),
                    keyRiskFactors = "Fruit fly maggots rotting inside melons; Sunburn / sunscald on exposed fruit tops."
                )
            ),
            postHarvest = CropPostHarvestGuide(
                maturityIndices = listOf(
                    "Tendril (curled curly pigtail wire) closest to fruit stem turns completely dry and brown",
                    "Ground spot (belly where melon rests on soil) turns from white/pale green to creamy buttery yellow",
                    "Dull hollow thud sound when thumped with knuckles (metallic ringing sound indicates unripeness)",
                    "Waxy sheen on rind disappears, replaced by a slightly dull, rough texture"
                ),
                harvestingGuidelines = "Harvest on a dry sunny morning by cutting the fruit stem leaving 3-5cm stalk attached with sharp shears. Never tear or pull fruit from the vine. Move to shaded assembly shelter immediately.",
                curingAndFieldDrying = "Do not sun dry. Cool melons in shaded packing sheds at 12°C - 15°C with 85% RH.",
                gradingAndStandardization = listOf(
                    "Large Grade: Weight 9 - 14 kg, symmetrical oblong/round shape, blemish-free",
                    "Medium Grade: Weight 6 - 8.5 kg",
                    "Small Grade: Weight 4 - 5.5 kg",
                    "Rejects: Fruit fly puncture scars, cracked rind, blossom end rot"
                ),
                packagingAndStorageCondition = "Loaded in bulk into trucks lined with thick straw padding or in large cardboard pallet bins. Transported at 13°C - 15°C.",
                storagePestAndLossMitigation = "Handle gently to prevent internal bruising; store away from ethylene-emitting fruits (bananas/avocados) which cause rapid rind softening.",
                commercialValueAddition = "Fresh sliced chilled watermelon cups, cold-pressed watermelon juice, and gourmet dried watermelon candies."
            )
        )
    )
}
