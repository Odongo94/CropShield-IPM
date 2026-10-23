package com.example.data

import com.example.R
import com.example.model.BioProtectionType
import com.example.model.CabiBioProduct
import com.example.model.ChemicalRecommendation
import com.example.model.CropCategory
import com.example.model.CropThreat
import com.example.model.InfonetPractice
import com.example.model.IpmPlan
import com.example.model.LifecycleStage
import com.example.model.PcpbRegisteredChemical
import com.example.model.StageIconType
import com.example.model.ThreatType
import com.example.model.VulnerabilityLevel

object DiverseCropThreats {

    val threats: List<CropThreat> = listOf(
        // =========================================================================
        // 1. BEAN ANTHRACNOSE (Colletotrichum lindemuthianum) - LEGUMES
        // =========================================================================
        CropThreat(
            id = "bean_anthracnose",
            name = "Bean Anthracnose",
            scientificName = "Colletotrichum lindemuthianum",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.LEGUMES,
            targetCrops = listOf("French Beans", "Common Beans", "Runner Beans", "Broad Beans"),
            affectedParts = listOf("Pods", "Leaves", "Stem", "Seed"),
            severityLevel = "High",
            summary = "Major seed-borne fungal disease of legumes in cool, wet highland zones (13°C–26°C). Causes sunken circular dark brown cankers with reddish borders on bean pods, leading to total post-harvest rejection for export French beans.",
            diagnosticKeys = listOf(
                "Brick-red to dark purplish-black discoloration along leaf veins on the underside of leaves.",
                "Circular, sunken, dark brown to black cankers on pods with raised reddish-brown or purplish margins.",
                "Gelatinous pinkish-orange spore masses (conidia) oozing in lesion centers during humid mornings.",
                "Elongated dark sunken lesions on seedling hypocotyls causing collar rot and damping off.",
                "Infected dry seeds showing dark sunken yellowish-brown blemishes."
            ),
            favorableConditions = "Frequent rainfall, high relative humidity (>92%), temperatures 15°C–22°C, and water splash dispersal from soil or infected debris.",
            imageResId = R.drawable.img_bean_anthracnose,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Seed-Borne Inoculum & Rain Splash",
                    scientificSub = "Conidial Release",
                    iconType = StageIconType.SPORE,
                    duration = "3 – 6 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Raindrops splash mucilaginous conidia from seed coats or infested crop residue onto emerging seedlings.",
                    symptomsToSpot = "Dark rusty flecks along seedling veins; cotyledon rot.",
                    targetAction = "Use certified clean seed; seed dressing with Thiram/Mancozeb before planting.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Copper Hydroxide 538 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Direct Cuticular Penetration",
                    scientificSub = "Appressorial Peg Entry",
                    iconType = StageIconType.PENETRATION,
                    duration = "6 – 12 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Conidia form melanized appressoria that exert mechanical pressure and cutinase enzymes to puncture leaf cuticle.",
                    symptomsToSpot = "Microscopic penetration inside leaf and pod tissue.",
                    targetAction = "Apply systemic strobilurin or contact protective barrier fungicide.",
                    recommendedActiveIngredients = listOf("Azoxystrobin 250 g/L", "Propineb 700 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Sub-Cuticular Necrosis & Canker Formation",
                    scientificSub = "Acervuli Eruption",
                    iconType = StageIconType.LESION,
                    duration = "5 – 10 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Fungal hyphae collapse pod and leaf cells, forming sunken saucer-shaped necrotic cankers containing acervuli.",
                    symptomsToSpot = "Sunken dark circular cankers with pink slimy spore centers on French bean pods.",
                    targetAction = "Apply systemic curative fungicide; remove diseased pods prior to grading.",
                    recommendedActiveIngredients = listOf("Pyraclostrobin 187.5 g/kg", "Difenoconazole 250 g/L")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Plant certified disease-free bean seed (pathogen-tested). Anthracnose is predominantly seed-borne.",
                    "Practice 3-year crop rotation with non-legumes (cereals, brassicas, root crops).",
                    "Do not cultivate, weed, or harvest French beans when plants are wet with dew or rain.",
                    "Plow under or burn infected bean haulms immediately following harvest to destroy overwintering acervuli."
                ),
                physicalMechanicalControls = listOf(
                    "Hot water seed treatment at 50°C for 15 minutes before planting to eliminate internal seed-borne mycelium."
                ),
                biologicalControls = listOf(
                    "Apply Trichoderma koningii bio-antagonist as a seed dressing to suppress early hypocotyl infection.",
                    "Foliar spray with Bacillus subtilis (Serenade ASO) at flowering."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Pyraclostrobin 187.5 g/kg + Dimethomorph 500 g/kg",
                        chemicalGroup = "FRAC Group 11 (QoI) + FRAC Group 40 (CAA)",
                        classification = "Translaminar & Systemic Preventive/Curative",
                        targetStage = "Stages 1 and 2",
                        dosagePerKnapsack = "20g per 20L knapsack",
                        dosagePerHectare = "1.0 kg/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at flower bud stage and pod setting.",
                        resistanceManagement = "Rotate with multi-site contact fungicides (Mancozeb). Maximum 2 sprays.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Cabrio Team 187.5 WDG",
                        pcpbRegNo = "PCPB(CR)0834",
                        formulation = "Water Dispersible Granules (WG)",
                        lastingIpmRule = "Lasting solution: Broad-spectrum control protecting pods from both Anthracnose and Downy Mildew."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Propineb 700 g/kg",
                        chemicalGroup = "FRAC Group M02 (Multi-site Dithiocarbamate)",
                        classification = "Contact Multi-Site Protective with Zinc nutrition",
                        targetStage = "Stage 1 (Spore Inoculum)",
                        dosagePerKnapsack = "40g - 50g per 20L knapsack",
                        dosagePerHectare = "2.0 kg/ha",
                        phiDays = 7,
                        reiHours = 24,
                        applicationTiming = "Apply preventatively every 7-10 days in wet highland seasons.",
                        resistanceManagement = "Multi-site activity; zero risk of fungal resistance buildup.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Boots"),
                        tradeName = "Antracol 70 WP",
                        pcpbRegNo = "PCPB(CR)0085",
                        formulation = "Wettable Powder (WP)",
                        lastingIpmRule = "Lasting solution: Supplies bio-available Zinc that strengthens legume foliage against fungal penetration."
                    )
                ),
                economicThreshold = "Presence of any pod lesions on 2% of plants during pod filling in export French beans."
            ),
            scoutChecklist = listOf(
                "Examine undersides of leaves along main and secondary veins for dark brick-red necrosis.",
                "Inspect green pods for circular sunken dark lesions with pinkish center cores.",
                "Check seedling stems at ground level for elongated dark cankers."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Certified Seed & Varietal Selection",
                    category = "Seed Health",
                    description = "Anthracnose is primary transmitted via infected seed. Certified seed from registered seed merchants prevents field epidemics.",
                    recipeOrMethod = "Source KALRO or Simlaw certified legume seed varieties with anthracnose tolerance."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Trianum-P",
                    activeAgent = "Trichoderma harzianum strain T-22",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Colletotrichum lindemuthianum and soil-borne damping off",
                    targetCrops = listOf("French Beans", "Common Beans", "Legumes"),
                    modeOfAction = "Colonizes roots and foliar surface, secreting lytic enzymes that destroy fungal germ tubes.",
                    applicationRate = "15g per 20L knapsack drench or seed coating.",
                    safetyMrl = "Zero MRL; 0 days PHI.",
                    supplierOrDistributor = "Koppert Biological Systems / CABI Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Cabrio Team 187.5 WDG",
                    activeIngredient = "Pyraclostrobin 187.5 g/kg + Dimethomorph 500 g/kg",
                    pcpbRegNo = "PCPB(CR)0834",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("French Beans", "Snow Peas", "Legumes"),
                    fracIracGroup = "FRAC Group 11 + FRAC Group 40",
                    knapsackDosage = "20g per 20L knapsack",
                    perHaDosage = "1.0 kg/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "BASF East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Premium pod finish for export market with dual mode of action."
                )
            )
        ),

        // =========================================================================
        // 2. LEGUME POD BORER (Maruca vitrata) - LEGUMES & PULSES
        // =========================================================================
        CropThreat(
            id = "maruca_pod_borer",
            name = "Legume Pod Borer",
            scientificName = "Maruca vitrata",
            type = ThreatType.PEST,
            cropCategory = CropCategory.LEGUMES,
            targetCrops = listOf("French Beans", "Cowpeas", "Pigeon Peas", "Chickpeas", "Green Grams"),
            affectedParts = listOf("Flowers", "Flower Buds", "Pods", "Seeds"),
            severityLevel = "Extreme",
            summary = "Pernicious pantropical pest of grain legumes. Larvae web flowers, leaves, and young pods together with dense silk and excrement, boring into pods and chewing developing seeds, causing up to 80% loss in grain and export legumes.",
            diagnosticKeys = listOf(
                "Flowers webbed together into unsightly messy clusters filled with dark larval frass.",
                "Premature flower bud shedding and blossom drop leaving bare racemes.",
                "Clean, circular entry holes on green pods with brown larval frass protruding outside.",
                "Whitish to pale cream-colored caterpillars with distinct rows of dark brown to black spots along the body.",
                "Brown moth with white translucent wing patches and a wingspan of 20-25mm resting on lower foliage."
            ),
            favorableConditions = "Warm humid tropical conditions (24°C–32°C), high legume flowering density, and continuous legume planting.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Laying on Flower Buds",
                    scientificSub = "Oviposition at Flowering",
                    iconType = StageIconType.EGG,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Moths lay tiny translucent eggs on sepals, terminal shoots, and flower buds at dusk.",
                    symptomsToSpot = "Moths active around flowers at sunset; tiny oval eggs on sepals.",
                    targetAction = "Apply biological ovicide or neem seed extract at 10% flower opening.",
                    recommendedActiveIngredients = listOf("Neem extract (Azadirachtin)", "Bacillus thuringiensis")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Flower Feeding & Webbing 1st-2nd Instars",
                    scientificSub = "Blossom Webbing Phase",
                    iconType = StageIconType.LARVA,
                    duration = "3 – 5 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Young spotted caterpillars chew floral organs and spin protective silken webs around floral clusters.",
                    symptomsToSpot = "Flowers webbed together; dark granular frass inside blossoms.",
                    targetAction = "Apply translaminar diamide or spinosyn before larvae bore into pods.",
                    recommendedActiveIngredients = listOf("Chlorantraniliprole 100 g/L", "Flubendiamide 480 g/L", "Spinetoram 120 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Internal Pod Boring 3rd-5th Instars",
                    scientificSub = "Seed Destruction Phase",
                    iconType = StageIconType.LARVA,
                    duration = "6 – 10 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Larger spotted larvae bore directly through pod walls, consuming green seeds internally protected inside pods.",
                    symptomsToSpot = "Pods with entry holes plugged with frass; hollowed seeds inside.",
                    targetAction = "Target systemic or translaminar ingestion insecticide; rogue heavily webbed pods.",
                    recommendedActiveIngredients = listOf("Belt 480 SC (Flubendiamide)", "Ampligo 150 ZC")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Intercrop cowpeas or beans with non-host cereals (maize, sorghum) in a 2:1 or 2:2 row arrangement.",
                    "Plant early in the season to ensure flowering precedes regional moth population surges.",
                    "Promptly hand-pick and destroy webbed flower and pod clusters during early scouting rounds.",
                    "Destroy wild legume weed hosts (Crotalaria, Tephrosia) around field borders that serve as continuous breeding reservoirs."
                ),
                physicalMechanicalControls = listOf(
                    "Deploy light traps or pheromone delta traps (1 per 1,000m²) to monitor male moth flights and trigger spray timings."
                ),
                biologicalControls = listOf(
                    "Apply Bacillus thuringiensis subsp. kurstaki (Dipel 2X) at flower initiation in late afternoon.",
                    "Spray 5% Neem Kernel Water Extract (NKWE) to act as a powerful antifeedant and oviposition deterrent.",
                    "Conserve native larval parasitoids (Phanerotoma leucobasis, Braunsia kriechbaumeri)."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Flubendiamide 480 g/L",
                        chemicalGroup = "IRAC Group 28 (Ryanodine Receptor Modulator)",
                        classification = "Translaminar Stomach Ingestion Poison",
                        targetStage = "Larvae Stages 1, 2, and 3",
                        dosagePerKnapsack = "5ml - 7.5ml per 20L knapsack",
                        dosagePerHectare = "0.15 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at 10% flowering and repeat 10 days later at pod formation.",
                        resistanceManagement = "Rotate with IRAC 5 (Spinosyns) or IRAC 6. Maximum 2 sprays per season.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Belt 480 SC",
                        pcpbRegNo = "PCPB(CR)0912",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: Selective lepidopteran larvicide with excellent safety for bees and parasitoid wasps."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Chlorantraniliprole 100g/L + Lambda-cyhalothrin 50g/L",
                        chemicalGroup = "IRAC Group 28 (Diamide) + IRAC Group 3A (Pyrethroid)",
                        classification = "Translaminar & Contact Knockdown with Extended Residual",
                        targetStage = "Eggs, Young Larvae & Adult Moths",
                        dosagePerKnapsack = "10ml - 12ml per 20L knapsack",
                        dosagePerHectare = "0.25 L/ha",
                        phiDays = 3,
                        reiHours = 24,
                        applicationTiming = "Apply at evening when moths are active around flowering spikes.",
                        resistanceManagement = "Do not use consecutively; rotate with bio-pesticides (Bt or neem).",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Ampligo 150 ZC",
                        pcpbRegNo = "PCPB(CR)0994",
                        formulation = "Mixed Formulation of CS and SC (ZC)",
                        lastingIpmRule = "Lasting solution: Micro-encapsulated formulation delivers immediate knockdown and 14-day pod protection."
                    )
                ),
                economicThreshold = "Presence of 2 larvae per 10 flowers or 5% of webbed flower buds during scouting."
            ),
            scoutChecklist = listOf(
                "Sample 20 plants across the field, focusing on newly opened flowers and terminal racemes.",
                "Gently open webbed blossoms and look for active spotted caterpillars and dark frass pellets.",
                "Examine green pod surfaces for small brown circular entry holes.",
                "Record count of damaged flowers versus healthy flowers to calculate threshold percentage."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Cereal Intercropping & Push-Pull Strategy",
                    category = "Cultural & Companion Planting",
                    description = "Intercropping cowpeas or French beans with maize or sorghum reduces Maruca pod damage by up to 45% compared to mono-crops by physically impeding moth flight and harboring predatory ants.",
                    recipeOrMethod = "Plant 2 rows of cowpea alternating with 2 rows of maize."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Dipel 2X",
                    activeAgent = "Bacillus thuringiensis subsp. kurstaki",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Maruca vitrata, pod borers, and loopers",
                    targetCrops = listOf("French Beans", "Cowpeas", "Legumes"),
                    modeOfAction = "Midgut membrane paralysis upon ingestion of crystal proteins.",
                    applicationRate = "25g - 30g per 20L knapsack.",
                    safetyMrl = "Zero MRL; 0 days PHI; certified organic.",
                    supplierOrDistributor = "Sumitomo Chemical / CABI Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Belt 480 SC",
                    activeIngredient = "Flubendiamide 480 g/L",
                    pcpbRegNo = "PCPB(CR)0912",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("French Beans", "Snow Peas", "Cowpeas"),
                    fracIracGroup = "IRAC Group 28 (Diamides)",
                    knapsackDosage = "5ml - 7.5ml per 20L knapsack",
                    perHaDosage = "0.15 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: High safety profile for beneficial pollinators with powerful stomach poison activity."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Ampligo 150 ZC",
                    activeIngredient = "Chlorantraniliprole 100g/L + Lambda-cyhalothrin 50g/L",
                    pcpbRegNo = "PCPB(CR)0994",
                    formulation = "Mixed Formulation (ZC)",
                    targetCrops = listOf("French Beans", "Common Beans", "Horticulture"),
                    fracIracGroup = "IRAC Group 28 + IRAC Group 3A",
                    knapsackDosage = "10ml - 12ml per 20L knapsack",
                    perHaDosage = "0.25 L/ha",
                    phiDays = 3,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Combines fast contact knockdown with systemic ovi-larvicidal protection."
                )
            )
        ),

        // =========================================================================
        // 3. WHITEFLIES & TYLCV (Bemisia tabaci) - VEGETABLES & CUT FLOWERS
        // =========================================================================
        CropThreat(
            id = "whiteflies",
            name = "Whiteflies (Silverleaf & Sweetpotato)",
            scientificName = "Bemisia tabaci",
            type = ThreatType.PEST,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Tomato", "Roses (Cut Flowers)", "French Beans", "Pepper", "Poinsettia", "Cucurbits"),
            affectedParts = listOf("Leaves", "Petals", "Growing Shoots"),
            severityLevel = "Extreme",
            summary = "Pervasive phloem-feeding pest in greenhouses and open fields. Secretes copious honeydew leading to black sooty mold on export roses and vegetables, and acts as the vector for Tomato Yellow Leaf Curl Virus (TYLCV), which causes 100% crop loss in tomatoes.",
            diagnosticKeys = listOf(
                "Clouds of tiny white moth-like insects (1mm) fluttering when plant foliage is disturbed or brushed.",
                "Leaf undersides encrusted with immobile oval, translucent yellowish scale-like nymphs and puparia.",
                "Copious sticky, glossy honeydew coating leaves and flower petals, rapidly followed by black sooty mold fungus.",
                "Tomato plants showing upward curling of leaf margins, severe chlorosis, thickening of leaf lamina, and extreme stunting (TYLCV virus symptoms).",
                "Cut flower roses showing dirty black sooty deposits across petals and calyces."
            ),
            favorableConditions = "Hot dry conditions (25°C–35°C), sheltered greenhouse environments, absence of driving rain, and continuous overlapping crops.",
            imageResId = R.drawable.img_whiteflies,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Clusters on Leaf Undersides",
                    scientificSub = "Oviposition",
                    iconType = StageIconType.EGG,
                    duration = "4 – 8 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Females lay tiny pear-shaped eggs vertically anchored by a pedicel into leaf tissue, often in circular patterns.",
                    symptomsToSpot = "Tiny yellow-to-brownish upright eggs on lower leaf surface.",
                    targetAction = "Apply horticultural petroleum oil or potassium soap to suffocate egg chorion.",
                    recommendedActiveIngredients = listOf("Potassium salts of fatty acids", "Petroleum spray oil")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Mobile Crawler Instar",
                    scientificSub = "1st Instar Wandering",
                    iconType = StageIconType.LARVA,
                    duration = "1 – 3 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "The newly hatched crawler searches for a suitable vein, inserts its stylet to suck sap, and molts into an immobile scale.",
                    symptomsToSpot = "Tiny crawling nymphs visible with a 10x hand lens.",
                    targetAction = "Apply systemic lipid biosynthesis inhibitor or translaminar bio-insecticide.",
                    recommendedActiveIngredients = listOf("Spiromesifen 240 g/L", "Spirotetramat 150 g/L", "Beauveria bassiana")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Sessile Feeding Nymphs & Puparium",
                    scientificSub = "Fixed Scale Stages 2-4",
                    iconType = StageIconType.LARVA,
                    duration = "8 – 14 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Flattened scale-like nymphs suck phloem sap continuously, excreting sugar-rich honeydew.",
                    symptomsToSpot = "Sticky honeydew, yellow leaf chlorosis, black sooty mold forming on upper leaves.",
                    targetAction = "Release Encarsia formosa parasitoids; apply two-way systemic insecticide.",
                    recommendedActiveIngredients = listOf("Spirotetramat 150 g/L", "Sulfoxaflor 240 g/L", "Pyriproxyfen 100 g/L")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Adult Moth-Like Whiteflies",
                    scientificSub = "Vector & Dispersal Phase",
                    iconType = StageIconType.ADULT,
                    duration = "14 – 30 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Adults flutter actively between crops, feeding on young apical leaves and vectoring geminiviruses (TYLCV).",
                    symptomsToSpot = "White clouds of flying adults, vectoring viral leaf curl.",
                    targetAction = "Yellow sticky roll traps; adulticide knockdown spray with anti-resistance rotation.",
                    recommendedActiveIngredients = listOf("Sulfoxaflor 240 g/L", "Flonicamid 500 g/kg", "Acetamiprid 200 g/L")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Install 50-mesh insect netting on all greenhouse ventilation openings and double-door entry airlocks.",
                    "Plant TYLCV-resistant tomato hybrids (e.g. Kilele F1, Anna F1, Terminator F1, Zara F1).",
                    "Maintain a strict 2-month host-free period between consecutive tomato or cucurbit plantings.",
                    "Surround field with barrier crops like maize, sorghum, or sunflower to deflect incoming whitefly swarms."
                ),
                physicalMechanicalControls = listOf(
                    "Deploy bright yellow sticky cards (1 card per 20m² in greenhouses; 100 per hectare in open fields) hung at canopy height.",
                    "Use reflective silver plastic mulches that disorient flying whiteflies and prevent early settling."
                ),
                biologicalControls = listOf(
                    "Introduce parasitic wasps Encarsia formosa or Eretmocerus eremicus (parasitized nymphs turn black or golden).",
                    "Apply entomopathogenic fungus Beauveria bassiana (Naturalis / Botanigard) in humid conditions.",
                    "Release predatory mirid bugs (Macrolophus pygmaeus / Nesidiocoris tenuis) in commercial greenhouse crops."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Spirotetramat 150 g/L",
                        chemicalGroup = "IRAC Group 23 (Tetramic Acid Derivative)",
                        classification = "Two-Way Systemic (Phloem & Xylem Mobile)",
                        targetStage = "All Nymphal Stages & Female Sterility",
                        dosagePerKnapsack = "10ml - 12ml per 20L knapsack",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at early population buildup; moves into new shoot growth and flower buds.",
                        resistanceManagement = "Rotate with IRAC 4C (Sulfoxaflor) or IRAC 9B. Maximum 2 sprays per crop.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Movento 150 OD",
                        pcpbRegNo = "PCPB(CR)1024",
                        formulation = "Oil Dispersion (OD)",
                        lastingIpmRule = "Lasting solution: Unique two-way systemic translocation reaches hidden nymphs inside curled leaves and buds."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Sulfoxaflor 240 g/L",
                        chemicalGroup = "IRAC Group 4C (Sulfoximines)",
                        classification = "Systemic & Translaminar Fast Knockdown",
                        targetStage = "Adults & Nymphs",
                        dosagePerKnapsack = "6ml - 8ml per 20L knapsack",
                        dosagePerHectare = "0.2 L/ha",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply when adult counts exceed threshold; effective against neonicotinoid-resistant strains.",
                        resistanceManagement = "Strictly maximum 2 applications per year. Highly toxic to bees; spray after sunset.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Closer 240 SC",
                        pcpbRegNo = "PCPB(CR)1187",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: Breaks cross-resistance to standard neonicotinoids (imidacloprid, thiamethoxam)."
                    )
                ),
                economicThreshold = "5 adults per tomato leaf at vegetative stage; 1 adult per leaf in TYLCV epidemic areas."
            ),
            scoutChecklist = listOf(
                "Gently turn over the 3rd and 4th youngest fully expanded leaves on 20 plants across the field.",
                "Count adult whiteflies before they flutter away.",
                "Examine lower leaves for black sooty mold and scale-like nymphs.",
                "Check yellow sticky traps weekly to track immigration surges."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Yellow Sticky Trap Mass-Trapping & Monitoring",
                    category = "Physical & Trapping",
                    description = "Adult whiteflies are strongly attracted to bright yellow wavelengths (580nm). Mass trapping reduces populations without chemical residues.",
                    recipeOrMethod = "Coat yellow plastic sheets or 20L yellow buckets with motor oil or castor oil; suspend 10cm above plant canopy."
                ),
                InfonetPractice(
                    title = "Potassium Soap Foliar Washes",
                    category = "Organic Contact Soap",
                    description = "Potassium salts of fatty acids dissolve the protective waxy epicuticle of whitefly scales, causing desiccation, while washing off sticky honeydew.",
                    recipeOrMethod = "Dissolve 50g mild potassium bar soap in 10L clean water. Spray leaf undersides with high-pressure nozzles."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Naturalis-L",
                    activeAgent = "Beauveria bassiana strain ATCC 74040",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Bemisia tabaci, thrips, and mites",
                    targetCrops = listOf("Tomato", "Roses", "Cut Flowers", "Vegetables"),
                    modeOfAction = "Fungal conidia germinate on insect cuticle, penetrating the hemolymph and mummifying the whitefly within 3-5 days.",
                    applicationRate = "15ml - 20ml per 20L knapsack.",
                    safetyMrl = "Zero MRL; 0 days PHI; leave zero bloom blemish.",
                    supplierOrDistributor = "CBC Biogard / CABI BioProtection Portal",
                    compatibilityNotes = "Do not tank-mix with synthetic fungicides."
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Movento 150 OD",
                    activeIngredient = "Spirotetramat 150 g/L",
                    pcpbRegNo = "PCPB(CR)1024",
                    formulation = "Oil Dispersion (OD)",
                    targetCrops = listOf("Tomato", "Roses", "French Beans"),
                    fracIracGroup = "IRAC Group 23",
                    knapsackDosage = "10ml - 12ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Two-way xylem and phloem translocation controls hidden scales inside dense foliage."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Closer 240 SC",
                    activeIngredient = "Sulfoxaflor 240 g/L",
                    pcpbRegNo = "PCPB(CR)1187",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Vegetables", "Flowers", "Tomato"),
                    fracIracGroup = "IRAC Group 4C",
                    knapsackDosage = "6ml - 8ml per 20L knapsack",
                    perHaDosage = "0.2 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Corteva Agriscience East Africa",
                    lastingIpmRule = "Lasting solution: Instant knockdown of adults; controls resistant populations without cross-resistance."
                )
            )
        ),

        // =========================================================================
        // 4. MAIZE LETHAL NECROSIS DISEASE (MLND) - CEREALS & GRAINS
        // Maize Chlorotic Mottle Virus (MCMV) + Potyvirus (SCMV)
        // =========================================================================
        CropThreat(
            id = "mlnd",
            name = "Maize Lethal Necrosis Disease (MLND)",
            scientificName = "MCMV + SCMV (Synergistic Viral Complex)",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetCrops = listOf("Maize", "Sweetcorn", "Sorghum"),
            affectedParts = listOf("Leaves", "Whorl", "Stalk", "Cobs / Grains"),
            severityLevel = "Extreme",
            summary = "Devastating viral complex in East Africa caused by co-infection of Maize Chlorotic Mottle Virus (MCMV) with a potyvirus (Sugarcane Mosaic Virus). Transmitted by vector thrips and beetles, causing bright chlorotic leaf mottling, dead-heart necrosis of the whorl, barren cobs, and complete field wipeout.",
            diagnosticKeys = listOf(
                "Bright chlorotic mottling and mosaic stripes starting at the base of young whorl leaves.",
                "Drying and necrosis of leaf margins ('firing') progressing from leaf tips toward the stalk.",
                "'Dead-heart' necrosis where central young whorl leaves rot and die while outer leaves remain pale green.",
                "Severe stunting and failure of maize plants to tassel or silk.",
                "Deformed, small, barren cobs with little to no grain fill and premature husk senescence."
            ),
            favorableConditions = "Continuous un-synchronized year-round maize farming, high populations of vector corn flea beetles and western flower thrips, and planting uncertified seed.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Vector Inoculation (Thrips & Beetles)",
                    scientificSub = "Viral Transmission",
                    iconType = StageIconType.PENETRATION,
                    duration = "Minutes to Hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Maize thrips (Frankliniella williamsi) and chrysomelid beetles acquire the viruses from infected wild grasses and transmit them into young maize seedlings.",
                    symptomsToSpot = "Tiny thrips or flea beetles feeding inside the young whorl cup.",
                    targetAction = "Apply systemic neonicotinoid seed dressing and vector control sprays.",
                    recommendedActiveIngredients = listOf("Thiamethoxam 350 g/L", "Imidacloprid 600 g/L")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Systemic Viral Replication & Phloem Transport",
                    scientificSub = "Endophytic Viral Proliferation",
                    iconType = StageIconType.HYPHAE,
                    duration = "5 – 10 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "The two viruses multiply synergistically, producing viral loads up to 50 times higher than single infections and moving through the phloem to all growing meristems.",
                    symptomsToSpot = "Chlorotic yellow mosaic speckles emerging along veins of youngest whorl leaves.",
                    targetAction = "Immediately rogue and bury symptomatic plants to prevent secondary field spread.",
                    recommendedActiveIngredients = listOf("Rogue infected plants immediately")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Dead-Heart Necrosis & Total Senescence",
                    scientificSub = "Vascular Collapse",
                    iconType = StageIconType.LESION,
                    duration = "10 – 20 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Whorl tissue completely collapses with necrosis; plants fail to produce pollen or develop grain.",
                    symptomsToSpot = "Bleached, dry, necrotic whorl leaves ('dead-heart'); barren, rot-prone ears.",
                    targetAction = "Harvest remaining green fodder for silage; plow and leave field fallow for 2 months.",
                    recommendedActiveIngredients = listOf("Field fallow and vector break")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Plant only certified MLND-free seed with official KEPHIS / KALRO certification tags.",
                    "Enforce a strict community-wide 2-month closed season without maize to break vector life cycles.",
                    "Practice immediate rogueing: uproot symptomatic plants at the first sign of chlorotic mottling, place in sacks, and bury at least 50cm deep.",
                    "Rotate maize with non-grass crops such as legumes (beans, cowpeas, groundnuts), sunflowers, or potatoes.",
                    "Synchronize planting dates within each farming cluster so all crops mature together."
                ),
                physicalMechanicalControls = listOf(
                    "Deep post-harvest plowing to bury volunteer maize seedlings and wild grass hosts."
                ),
                biologicalControls = listOf(
                    "Encourage natural predators of vector thrips (minute pirate bugs Orius spp., predatory mites).",
                    "Spray neem seed extracts to repel vector beetles and suppress vector oviposition."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Thiamethoxam 350 g/L",
                        chemicalGroup = "IRAC Group 4A (Neonicotinoid)",
                        classification = "Systemic Seed Dressing & Vector Control",
                        targetStage = "Stage 1 (Vector Feeding on Seedling)",
                        dosagePerKnapsack = "10ml per 20L knapsack or seed dressing",
                        dosagePerHectare = "0.2 L/ha",
                        phiDays = 60,
                        reiHours = 24,
                        applicationTiming = "Treat seed prior to planting or spray early vegetative whorl.",
                        resistanceManagement = "Rotate with diamide or pyrethroid vector sprays.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Actara 25 WG",
                        pcpbRegNo = "PCPB(CR)0567",
                        formulation = "Water Dispersible Granules (WG)",
                        lastingIpmRule = "Lasting solution: Protects seedlings during the critical 1-4 week window when viral infection causes total dead-heart."
                    )
                ),
                economicThreshold = "Zero tolerance (0%) for seed production; rogue any single symptomatic plant immediately."
            ),
            scoutChecklist = listOf(
                "Inspect 100 plants in 5 separate field zones at 2, 4, and 6 weeks after emergence.",
                "Look closely into the central whorl for chlorotic mosaic speckling and leaf margin necrosis.",
                "Examine inner whorl folds for vector thrips or corn flea beetles.",
                "Carry a heavy-duty bag to safely remove and bury any identified MLND-infected plants."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Mandatory Community Maize-Free Closed Season",
                    category = "Community IPM",
                    description = "Neither MCMV nor its vectors can survive long periods without host plants. A 2-month maize-free window breaks the transmission cycle across whole districts.",
                    recipeOrMethod = "Enforce a designated 60-day break without green maize between main season and short rains."
                ),
                InfonetPractice(
                    title = "Immediate Rogueing & Deep Burial",
                    category = "Sanitation",
                    description = "Infected maize plants act as pathogen reservoirs for vector thrips. Leaving them standing allows vectors to transmit virus to entire adjacent fields.",
                    recipeOrMethod = "Uproot infected plant including roots, place in airtight bag, and bury in a 50cm pit outside the field."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Pyregard 5 EC",
                    activeAgent = "Natural Pyrethrins 50 g/L",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Vector thrips and flea beetles",
                    targetCrops = listOf("Maize", "Cereals", "Horticulture"),
                    modeOfAction = "Fast neurotoxic knockdown of vector insects with zero synthetic chemical residues.",
                    applicationRate = "20ml - 30ml per 20L knapsack.",
                    safetyMrl = "Zero MRL; 1 day PHI; organic compliant.",
                    supplierOrDistributor = "Botanical Resources / CABI Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Actara 25 WG",
                    activeIngredient = "Thiamethoxam 250 g/kg",
                    pcpbRegNo = "PCPB(CR)0567",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("Maize", "Cereals", "Horticulture"),
                    fracIracGroup = "IRAC Group 4A",
                    knapsackDosage = "10g per 20L knapsack",
                    perHaDosage = "0.2 kg/ha",
                    phiDays = 45,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Systemic xylem protection halts vector sap-sucking before virus can be injected."
                )
            )
        ),

        // =========================================================================
        // 5. BLACK ROT OF CRUCIFERS (Xanthomonas campestris) - VEGETABLES
        // Cabbage, Kale (Sukuma Wiki), Broccoli
        // =========================================================================
        CropThreat(
            id = "black_rot",
            name = "Black Rot of Crucifers",
            scientificName = "Xanthomonas campestris pv. campestris",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Cabbage", "Kale", "Broccoli", "Cauliflower"),
            affectedParts = listOf("Leaves", "Vascular Stem", "Cabbage Head"),
            severityLevel = "High",
            summary = "Most important bacterial disease of brassicas worldwide. Enters through leaf hydathodes and wounds, producing distinctive V-shaped yellow necrotic lesions at leaf margins with blackened leaf veins, causing systemic wilt and foul-smelling soft rot.",
            diagnosticKeys = listOf(
                "Characteristic V-shaped chlorotic yellow lesions pointing toward the leaf base, starting at hydathodes along leaf margins.",
                "Leaf veins within the yellow necrotic area turning distinctly black or dark brown.",
                "Cross-section of the cabbage stem showing a characteristic black ring of plugged vascular bundles.",
                "Infected leaves dropping prematurely; remaining heads stunted and rotting during storage.",
                "Bacterial slime oozing from vascular strands when cut stem is placed in water."
            ),
            favorableConditions = "Warm wet conditions (25°C–30°C), heavy rainfall, morning dew, overhead sprinkler irrigation, and working in wet fields.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Seed-Borne Inoculum & Rain Splash",
                    scientificSub = "Bacterial Ingress via Hydathodes",
                    iconType = StageIconType.PENETRATION,
                    duration = "4 – 12 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Bacteria splash from infected seed or soil onto leaf margins, swimming into hydathodes in morning guttation droplets.",
                    symptomsToSpot = "No visible symptoms; bacteria multiply in hydathode water pores.",
                    targetAction = "Hot water seed treatment; preventative copper bactericide spray.",
                    recommendedActiveIngredients = listOf("Copper Hydroxide 538 g/kg", "Cuprous Oxide 750 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Vascular Colonization & Xylem Plugging",
                    scientificSub = "Xanthan Gum Occlusion",
                    iconType = StageIconType.HYPHAE,
                    duration = "7 – 14 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Bacteria produce extracellular xanthan slime that clogs xylem vessels, preventing water movement and causing V-shaped leaf necrosis.",
                    symptomsToSpot = "V-shaped yellow marginal lesions with blackened veins.",
                    targetAction = "Apply systemic copper bactericide; remove diseased plants immediately.",
                    recommendedActiveIngredients = listOf("Copper Sulfate Pentahydrate", "Copper Hydroxide")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Use certified disease-free cabbage and kale seed.",
                    "Practice 3-year crop rotation with non-brassica crops (solanaceae, legumes, cereals).",
                    "Avoid overhead sprinkler irrigation; use drip irrigation to prevent bacterial splash.",
                    "Never work in or weed brassica fields when foliage is wet with rain or dew."
                ),
                physicalMechanicalControls = listOf(
                    "Hot water seed treatment at 50°C for 25 minutes for cabbage, 20 minutes for broccoli/kale to eradicate seed-borne bacteria."
                ),
                biologicalControls = listOf(
                    "Apply Bacillus amyloliquefaciens bio-bactericide to seedling nursery beds.",
                    "Soil drench with Trichoderma harzianum to promote root vigor."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Copper Hydroxide 538 g/kg",
                        chemicalGroup = "FRAC Group M01 (Inorganic Copper)",
                        classification = "Multi-Site Preventative Protective Bactericide",
                        targetStage = "Stage 1 (Hydathode Ingress)",
                        dosagePerKnapsack = "40g - 50g per 20L knapsack",
                        dosagePerHectare = "2.0 kg/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply preventatively every 7-10 days in warm rainy weather.",
                        resistanceManagement = "Multi-site inorganic copper; no resistance buildup.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Kocide 3000",
                        pcpbRegNo = "PCPB(CR)0943",
                        formulation = "Dry Flowable (DF)",
                        lastingIpmRule = "Lasting solution: High copper ion bioavailability provides long-lasting rain-fast leaf protection."
                    )
                ),
                economicThreshold = "Presence of V-shaped lesions on 5% of scouted brassica plants at vegetative stage."
            ),
            scoutChecklist = listOf(
                "Inspect leaf margins of 20 cabbage plants for V-shaped yellow lesions with black veins.",
                "Cut a suspicious leaf petiole and examine for black vascular ring.",
                "Check nursery beds carefully prior to transplanting; discard any seedlings with yellowing cotyledons."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Hot Water Seed Treatment Protocol",
                    category = "Seed Sanitation",
                    description = "Soaking brassica seeds in precisely controlled hot water destroys internal Xanthomonas bacteria without lowering germination.",
                    recipeOrMethod = "Immerse seed in cloth bag into water maintained at exactly 50°C for 25 minutes (cabbage) or 20 minutes (kale). Immediately cool in cold water and dry in shade."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Kocide 3000 Bio-Protect",
                    activeAgent = "Copper Hydroxide (Bio-derived copper formulation)",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Xanthomonas campestris and bacterial blights",
                    targetCrops = listOf("Cabbage", "Kale", "Brassicas"),
                    modeOfAction = "Multi-site denaturation of bacterial proteins and cell wall disruption.",
                    applicationRate = "40g per 20L knapsack.",
                    safetyMrl = "Exempt from standard MRL; 3 days PHI.",
                    supplierOrDistributor = "Certis USA / CABI Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Kocide 3000",
                    activeIngredient = "Copper Hydroxide 538 g/kg",
                    pcpbRegNo = "PCPB(CR)0943",
                    formulation = "Dry Flowable (DF)",
                    targetCrops = listOf("Cabbage", "Kale", "Vegetables"),
                    fracIracGroup = "FRAC Group M01",
                    knapsackDosage = "40g - 50g per 20L knapsack",
                    perHaDosage = "2.0 kg/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Arysta LifeScience Kenya",
                    lastingIpmRule = "Lasting solution: Micronized copper particles provide maximum leaf coverage against bacterial entry."
                )
            )
        )
    )
}
