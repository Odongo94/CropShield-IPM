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

object ThreatCatalog {

    private val baseThreats: List<CropThreat> = listOf(
        // =========================================================================
        // 1. LATE BLIGHT (Phytophthora infestans) - VEGETABLES & TUBERS
        // Sources: Infonet-Biovision, PCPB Kenya (pcpb.go.ke), CABI BioProtection
        // =========================================================================
        CropThreat(
            id = "late_blight",
            name = "Late Blight",
            scientificName = "Phytophthora infestans",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Tomato", "Potato", "Pepper", "Eggplant"),
            affectedParts = listOf("Leaves", "Stem", "Fruit", "Tuber"),
            severityLevel = "Extreme",
            summary = "Devastating oomycete water-mold disease. Destroys tomato canopies and potato crops within 7–10 days in cool humid weather (15°C–20°C, >90% RH). Documented on Infonet-Biovision and PCPB Kenya crops register.",
            diagnosticKeys = listOf(
                "Irregular water-soaked pale-green to brown lesions on leaf margins and tips.",
                "Delicate white cottony mildew ring on leaf undersides during moist mornings.",
                "Dark brown to black necrotic girdling lesions on stems and petioles.",
                "Greasy, firm bronze/brown marbled rot on tomato fruits; dry brownish rot beneath potato skin."
            ),
            favorableConditions = "Prolonged leaf wetness (>6 hours), temperatures 12°C–22°C, relative humidity above 90%, frequent rain or foggy mornings.",
            imageResId = R.drawable.sample_late_blight,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Sporangia Inoculum & Air Drift",
                    scientificSub = "Asexual Sporangia Dispersal",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 6 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Wind and water-splash transfer caducous sporangia from volunteer potatoes and infected nightshades to healthy leaves.",
                    symptomsToSpot = "Leaves appear healthy but carry microscopic surface inoculum on wet foliage.",
                    targetAction = "Apply multi-site protective barrier before rainfall or persistent mist.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Chlorothalonil 720 g/L", "Copper Hydroxide 538 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Zoospore Release & Stomatal Entry",
                    scientificSub = "Biflagellate Zoospore Penetration",
                    iconType = StageIconType.PENETRATION,
                    duration = "4 – 10 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "At cool temperatures (<18°C), sporangia release 8 motile zoospores that swim to stomata and penetrate leaf cuticle.",
                    symptomsToSpot = "Minute translucent flecks visible under strong backlighting.",
                    targetAction = "Apply translaminar or locally systemic penetrant with kickback activity.",
                    recommendedActiveIngredients = listOf("Cymoxanil + Mancozeb", "Dimethomorph 500 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Vascular Colonization & Haustoria",
                    scientificSub = "Intercellular Mycelial Invasion",
                    iconType = StageIconType.HYPHAE,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Fungal hyphae colonize the mesophyll and send haustoria into plant cells, enzymatically dissolving cell membranes.",
                    symptomsToSpot = "Chlorotic pale yellow halos coalesce; leaves turn flaccid.",
                    targetAction = "Emergency systemic curative spray to halt internal hyphal expansion.",
                    recommendedActiveIngredients = listOf("Metalaxyl-M + Mancozeb", "Propamocarb + Fosetyl-Al")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Lesion Expansion & Stem Girdling",
                    scientificSub = "Rapid Cell Lysis & Necrosis",
                    iconType = StageIconType.LESION,
                    duration = "24 – 72 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Rapid cell death produces dark water-soaked necrotic patches. Lesions expand without respect to leaf veins.",
                    symptomsToSpot = "Large dark greasy blighted patches with distinct rotting odor; stem collapse.",
                    targetAction = "Prune and bury heavily blighted stems; isolate field with anti-sporulant fungicide.",
                    recommendedActiveIngredients = listOf("Azoxystrobin + Difenoconazole", "Fluopicolide + Propamocarb")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Sporulation & Secondary Epidemic",
                    scientificSub = "Sporangiophore Eruption",
                    iconType = StageIconType.DISPERSAL,
                    duration = "Continuous in wet weather",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Under high humidity, millions of sporangia erupt through stomata on lesion margins, ready for airborne dissemination.",
                    symptomsToSpot = "White frost-like downy growth bordering dead tissue on leaf undersides.",
                    targetAction = "Containment spray with anti-sporulant chemistry; avoid working in wet crops.",
                    recommendedActiveIngredients = listOf("Mandipropamid 250 g/L", "Zoxamide + Mancozeb")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Destroy potato cull piles, volunteer potatoes, and wild Solanum weeds before planting.",
                    "Ridge hilling (earthing up) with at least 10–15 cm of soil cover over potato tubers to prevent spore wash.",
                    "Plant certified disease-free seed tubers and tolerant varieties (e.g. Shangi, Asante, Kenya Karibu).",
                    "Maintain 60cm x 60cm row spacing to facilitate canopy ventilation and rapid foliage drying.",
                    "Avoid sprinkler irrigation; use drip lines at the soil base."
                ),
                physicalMechanicalControls = listOf(
                    "Rogue out and bury initial infected plants in deep pits away from fields; do not compost blighted haulms.",
                    "Cut and burn potato haulms 14 days before harvest to prevent tuber contamination during lifting."
                ),
                biologicalControls = listOf(
                    "Trichoderma harzianum soil and foliar colonization to outcompete oomycete zoospores.",
                    "Bacillus subtilis foliar bio-fungicide preventative sprays on young foliage.",
                    "Copper octanoate (fatty acid copper) bio-approved multi-site protectant."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Ridomil Gold MZ 68WG",
                        pcpbRegNo = "PCPB(CR)0488",
                        formulation = "68 WG (Water Dispersible Granules)",
                        activeIngredient = "Metalaxyl-M (40 g/kg) + Mancozeb (640 g/kg)",
                        chemicalGroup = "FRAC Group 4 (PA-fungicide) + FRAC Group M03 (Multi-site)",
                        classification = "Systemic Curative & Protective Barrier",
                        targetStage = "Early Lesions & Mycelial Colonization (Stages 1-3)",
                        dosagePerKnapsack = "50g in 20L knapsack (2.5g/L water)",
                        dosagePerHectare = "2.5 kg/ha in 500L water",
                        phiDays = 7,
                        reiHours = 24,
                        applicationTiming = "At first warning of cool humid weather or trace lesion appearance.",
                        resistanceManagement = "Maximum 2 applications per season. Always rotate with non-FRAC 4 chemistries (e.g. Ortiva Top or Revus) to preserve phenylamide efficacy.",
                        safetyPpe = listOf("Nitrile chemical gloves", "Cartridge respirator/mask", "Goggles", "Coveralls", "Waterproof rubber boots")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Ortiva Top 325 SC",
                        pcpbRegNo = "PCPB(CR)1152",
                        formulation = "325 SC (Suspension Concentrate)",
                        activeIngredient = "Azoxystrobin (200 g/L) + Difenoconazole (125 g/L)",
                        chemicalGroup = "FRAC Group 11 (QoI Strobilurin) + FRAC Group 3 (DMI Triazole)",
                        classification = "Translaminar Anti-Sporulant & Systemic Curative",
                        targetStage = "Infection Arrest & Sporulation Inhibition (Stages 3-5)",
                        dosagePerKnapsack = "20ml in 20L knapsack (1.0ml/L water)",
                        dosagePerHectare = "1.0 L/ha in 500L water",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply when conditions favor rapid secondary spread.",
                        resistanceManagement = "Rotate after maximum 2 consecutive applications. Do not mix with other strobilurins.",
                        safetyPpe = listOf("Eye protection", "Chemical splash apron", "Nitrile gloves", "Face mask")
                    )
                ),
                economicThreshold = "Zero tolerance in commercial crops: 1 blighted plant per 100 plants warrants immediate preventative and curative IPM intervention."
            ),
            scoutChecklist = listOf(
                "Inspect lower canopy leaves in moist, shaded depression areas early in the morning.",
                "Check leaf undersides for fine white sporulation rings while dew is present.",
                "Examine stems near node forks for dark greasy brown lesions.",
                "Assess potato tuber integrity before lifting; inspect for sunken brown flesh."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Bordeaux Mixture Preparation (Infonet Organic Standard)",
                    category = "Botanical & Mineral Barrier",
                    description = "Traditional 1:1:100 Bordeaux copper sulfate and slaked lime mixture providing broad-spectrum protectant barrier.",
                    recipeOrMethod = "Dissolve 100g copper sulfate in 5L water in plastic bucket; slake 100g builder's lime in 5L water. Pour copper solution into lime while stirring. Make up to 10L. Apply with knapsack sprayer within 4 hours."
                ),
                InfonetPractice(
                    title = "Crop Rotation & Solanaceae Break Period",
                    category = "Cultural Prevention",
                    description = "Infonet-Biovision 3-year rotation protocol to starve soil-borne and volunteer inoculum.",
                    recipeOrMethod = "Never rotate tomato after potato, eggplant, or pepper. Follow potato/tomato with maize, beans, cabbages, or grass for at least 3 consecutive crop cycles."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Trianum-P",
                    activeAgent = "Trichoderma harzianum strain T-22",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Phytophthora infestans & Pythium damping-off",
                    targetCrops = listOf("Tomato", "Potato", "Pepper"),
                    modeOfAction = "Competitive exclusion, mycoparasitism, and induction of systemic plant defense (ISR).",
                    applicationRate = "15g per 20L knapsack; drench or soil incorporate at transplanting.",
                    safetyMrl = "Zero MRL; 0 days PHI; Certified Organic (OMRI & IFOAM).",
                    supplierOrDistributor = "Koppert Biological Systems / CABI Portal"
                ),
                CabiBioProduct(
                    tradeName = "Serenade ASO",
                    activeAgent = "Bacillus subtilis strain QST 713",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Oomycete sporangia and bacterial spot",
                    targetCrops = listOf("Tomato", "Potato", "Vegetables"),
                    modeOfAction = "Antifungal lipopeptide metabolites that disrupt oomycete cell membranes.",
                    applicationRate = "50ml - 80ml in 20L knapsack foliar spray.",
                    safetyMrl = "Exempt from residue tolerances; 0 days PHI.",
                    supplierOrDistributor = "Bayer CropScience Biologics / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Ridomil Gold MZ 68WG",
                    activeIngredient = "Metalaxyl-M 40g/kg + Mancozeb 640g/kg",
                    pcpbRegNo = "PCPB(CR)0488",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("Tomato", "Potato"),
                    fracIracGroup = "FRAC 4 (PA) + FRAC M03",
                    knapsackDosage = "50g per 20L knapsack",
                    perHaDosage = "2.5 kg/ha",
                    phiDays = 7,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Maximum 2 applications per cropping season. Alternate with contact multi-site fungicides (Mancozeb or Copper) to avoid resistant Phytophthora strains."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Ortiva Top 325 SC",
                    activeIngredient = "Azoxystrobin 200g/L + Difenoconazole 125g/L",
                    pcpbRegNo = "PCPB(CR)1152",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Tomato", "Potato", "Vegetables"),
                    fracIracGroup = "FRAC 11 + FRAC 3",
                    knapsackDosage = "20ml per 20L knapsack",
                    perHaDosage = "1.0 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Do not apply consecutively more than 2 times. Rotate with FRAC 40 (Dimethomorph) or FRAC 43 (Fluopicolide)."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Infinito 687.5 SC",
                    activeIngredient = "Fluopicolide 62.5g/L + Propamocarb-HCl 625g/L",
                    pcpbRegNo = "PCPB(CR)0892",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Potato", "Tomato"),
                    fracIracGroup = "FRAC 43 + FRAC 28",
                    knapsackDosage = "30ml - 40ml per 20L knapsack",
                    perHaDosage = "1.6 L/ha",
                    phiDays = 7,
                    reiHours = 24,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Excellent translaminar kick-back; breaks resistance against phenylamides."
                )
            )
        ),

        // =========================================================================
        // 2. ROSE POWDERY MILDEW (Podosphaera pannosa) - CUT FLOWERS & ORNAMENTALS
        // Sources: CABI BioProtection Portal, PCPB Kenya Floriculture Registrations
        // =========================================================================
        CropThreat(
            id = "rose_powdery_mildew",
            name = "Rose Powdery Mildew",
            scientificName = "Podosphaera pannosa",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Chrysanthemums", "Ornamentals"),
            affectedParts = listOf("Leaves", "Flower Buds", "Young Stems", "Petals"),
            severityLevel = "Extreme",
            summary = "Primary fungal disease in commercial cut rose greenhouses. Covers tender leaves, young shoots, and flower buds with white powdery conidia, causing leaf curling, severe distortion, and total rejection of export-grade flower stems.",
            diagnosticKeys = listOf(
                "White, talcum-like powdery fungal patches on upper and lower surfaces of young rose leaves.",
                "Curling, blistering, and purplish discoloration of young expanding foliage.",
                "Infected flower buds fail to open or open deformed with discolored outer petals.",
                "Dense white mycelial cushions coating young green stems and calyx."
            ),
            favorableConditions = "Warm daytime temperatures (20°C–25°C) followed by humid nights (relative humidity 90%–99%). Unlike late blight, spore germination does not require free water film.",
            imageResId = R.drawable.sample_rose_powdery_mildew,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Conidia Inoculum & Germination",
                    scientificSub = "Aerial Conidiospore Landing",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 4 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Windblown conidia land on young succulent foliage and rapidly produce germ tubes under high night humidity.",
                    symptomsToSpot = "Invisible to naked eye; microscopic spore chains landing on tender apical shoots.",
                    targetAction = "Apply preventive anti-germination wash or bio-antagonist before high-humidity nightfall.",
                    recommendedActiveIngredients = listOf("Potassium Bicarbonate", "Sulfur 800 g/kg", "Bupirimate")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Appressorium & Cuticle Penetration",
                    scientificSub = "Epidermal Cell Entry",
                    iconType = StageIconType.PENETRATION,
                    duration = "4 – 8 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Fungus penetrates only the outer epidermal cells and forms feeding haustoria without invading deep vascular tissue.",
                    symptomsToSpot = "Faint chlorotic yellowish spots on upper leaf surface.",
                    targetAction = "Translaminar or vapor-active fungicide to eradicate surface appressoria.",
                    recommendedActiveIngredients = listOf("Penconazole 100 g/L", "Trifloxystrobin 500 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Epiphytic Mycelium Proliferation",
                    scientificSub = "Surface Mycelium Expansion",
                    iconType = StageIconType.HYPHAE,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "White fungal threads branch across the cuticle, drawing nutrients directly from epidermal cells.",
                    symptomsToSpot = "Distinct white powdery colonies forming circular patches.",
                    targetAction = "Curative systemic triazole or DMI fungicide to collapse haustoria.",
                    recommendedActiveIngredients = listOf("Difenoconazole", "Tebuconazole + Trifloxystrobin")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Tissue Distortion & Bud Infection",
                    scientificSub = "Cell Stunting & Petal Blight",
                    iconType = StageIconType.LESION,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Infected leaves curl upwards and cup; stems become brittle; flower sepals stick together, preventing export harvest.",
                    symptomsToSpot = "Twisted, blistered leaves coated with thick white felt; unopened stunted buds.",
                    targetAction = "Prune infected shoots; apply tank mix of systemic curative and contact eradicator.",
                    recommendedActiveIngredients = listOf("Boscalid + Pyraclostrobin", "Cyflufenamid")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Sporulation & Greenhouse Epidemic",
                    scientificSub = "Conidiophore Chains",
                    iconType = StageIconType.DISPERSAL,
                    duration = "Continuous under dry day/humid night cycles",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Chains of upright conidia are carried by greenhouse ventilation drafts across adjacent benches.",
                    symptomsToSpot = "Clouds of powdery white spores dislodged by gentle tapping of rose stems.",
                    targetAction = "Night sulfur vaporization; daytime bio-control spray with hyperparasite Ampelomyces.",
                    recommendedActiveIngredients = listOf("Ampelomyces quisqualis", "Orange Oil / Terpenes")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Maintain greenhouse night ventilation and heating to prevent relative humidity exceeding 85%.",
                    "Use sulfur vaporizers (evaporators) operated during unoccupied night hours (4–6 hours/night).",
                    "Avoid excessive nitrogen fertilization which produces overly succulent, highly susceptible new flushes.",
                    "Disinfect pruners between flower beds to prevent mechanical transfer of conidia.",
                    "Grow tolerant cut rose cultivars in greenhouse blocks with known high humidity."
                ),
                physicalMechanicalControls = listOf(
                    "Prune out infected rose shoots at weekly flush pinching; dispose in sealed plastic disposal bags.",
                    "Wash foliage with high-volume clean water mist early in the morning to wash off and burst dry conidia before germination."
                ),
                biologicalControls = listOf(
                    "Ampelomyces quisqualis hyperparasitic fungus (CABI Portal registered AQ10) which parasitizes powdery mildew hyphae.",
                    "Bacillus amyloliquefaciens / Bacillus subtilis (Serenade) bio-fungicide protective coating.",
                    "Potassium hydrogen carbonate (Karma / Armicarb) osmotic contact eradicant causing instant cell burst.",
                    "Sweet orange peel extract (Prev-Am / Limonene) natural desiccant and penetrant."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Topas 100 EC",
                        pcpbRegNo = "PCPB(CR)0477",
                        formulation = "100 EC (Emulsifiable Concentrate)",
                        activeIngredient = "Penconazole (100 g/L)",
                        chemicalGroup = "FRAC Group 3 (DMI Triazole)",
                        classification = "Systemic Protective & Curative Eradicant",
                        targetStage = "Epiphytic Hyphae & Haustoria (Stages 2-4)",
                        dosagePerKnapsack = "10ml - 15ml in 20L knapsack (0.5ml - 0.75ml/L water)",
                        dosagePerHectare = "0.5 L/ha in 1000L water (high volume ornamental spray)",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "At first sign of white powdery spots on tender flush foliage.",
                        resistanceManagement = "Maximum 2 applications per flush. Rotate with FRAC 11 (Strobilurin) or FRAC 8 (Quinoxyfen/Bupirimate) to avoid DMI resistance.",
                        safetyPpe = listOf("Chemical safety goggles", "Nitrile gloves", "Overalls", "Vapor mask")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Bellis 38 WG",
                        pcpbRegNo = "PCPB(CR)0831",
                        formulation = "38 WG (Water Dispersible Granules)",
                        activeIngredient = "Boscalid (252 g/kg) + Pyraclostrobin (128 g/kg)",
                        chemicalGroup = "FRAC Group 7 (SDHI) + FRAC Group 11 (QoI)",
                        classification = "Translaminar Anti-Sporulant & Kickback",
                        targetStage = "Spore Germination & Mycelial Cushions (Stages 1-5)",
                        dosagePerKnapsack = "16g - 20g in 20L knapsack (0.8g - 1.0g/L water)",
                        dosagePerHectare = "0.8 - 1.0 kg/ha in 1000L water",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply during high risk weather periods.",
                        resistanceManagement = "Maximum 2 applications per season. Dual mode of action provides robust anti-resistance protection.",
                        safetyPpe = listOf("Nitrile gloves", "Face shield", "Coverall", "Rubber boots")
                    )
                ),
                economicThreshold = "Cut flower export threshold is near ZERO: 1 infected leaflet per 100 stems triggers corrective eradicant intervention."
            ),
            scoutChecklist = listOf(
                "Inspect youngest 3 leaves on the upper third of rose shoots.",
                "Check for curling or slight reddening/purpling of young leaves before white powder becomes dense.",
                "Examine calyx and flower buds under bright light.",
                "Monitor greenhouse perimeter beds near cold draft vents where condensation forms."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Baking Soda & Horticultural Soap Foliar Wash",
                    category = "Organic Contact Eradicant",
                    description = "Infonet organic sodium bicarbonate solution that elevates leaf surface pH above 8.0, disrupting fungal cell walls.",
                    recipeOrMethod = "Mix 50g sodium or potassium bicarbonate + 20ml mild liquid soap in 10L water. Spray thoroughly over rose leaves every 5–7 days in late afternoon."
                ),
                InfonetPractice(
                    title = "Cow Milk Foliar Spray (10% Dilution)",
                    category = "Biological Solar Eradicant",
                    description = "Fermented milk proteins generate antiseptic free radicals under sunlight that lyse powdery mildew conidia.",
                    recipeOrMethod = "Mix 1L fresh skimmed cow milk with 9L clean water (1:9 ratio). Spray in direct morning sunlight for maximum photoreactive activity."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Karma 85 SP",
                    activeAgent = "Potassium hydrogen carbonate (850 g/kg)",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Podosphaera pannosa (Rose Powdery Mildew)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Chrysanthemums"),
                    modeOfAction = "Contact shock: rapidly alters osmotic pressure and collapses fungal hyphae and conidia within hours.",
                    applicationRate = "60g in 20L knapsack (3g/L water).",
                    safetyMrl = "Zero MRL; exempt from residue limits; certified for organic and zero-residue cut flower export.",
                    supplierOrDistributor = "Certis / CABI BioProtection Portal"
                ),
                CabiBioProduct(
                    tradeName = "Prev-Am",
                    activeAgent = "Cold-pressed orange oil / Terpenes (60 g/L)",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Powdery mildew mycelia, spider mites, thrips",
                    targetCrops = listOf("Cut Roses", "Carnations", "Summer Flowers"),
                    modeOfAction = "Natural terpene dissolves the protective chitin and mycelial wax layer, desiccating the pathogen.",
                    applicationRate = "40ml in 20L knapsack (2ml/L water).",
                    safetyMrl = "Zero PHI; leaves no visible residue on flower petals.",
                    supplierOrDistributor = "Oro Agri / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Topas 100 EC",
                    activeIngredient = "Penconazole 100g/L",
                    pcpbRegNo = "PCPB(CR)0477",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Ornamentals"),
                    fracIracGroup = "FRAC Group 3 (DMI Triazole)",
                    knapsackDosage = "10ml - 15ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Limit to 2 consecutive applications. Rotate with FRAC 11 (Flint 50 WG) or FRAC 7 (Bellis) to prevent DMI resistance buildup."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Score 250 EC",
                    activeIngredient = "Difenoconazole 250g/L",
                    pcpbRegNo = "PCPB(CR)0322",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Roses", "Carnations", "Vegetables"),
                    fracIracGroup = "FRAC Group 3",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Strong acropetal systemic curative movement; ideal for cleaning established colonies."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Nimrod 250 EC",
                    activeIngredient = "Bupirimate 250g/L",
                    pcpbRegNo = "PCPB(CR)0214",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Ornamentals"),
                    fracIracGroup = "FRAC Group 8 (Hydroxypyrimidine)",
                    knapsackDosage = "20ml per 20L knapsack",
                    perHaDosage = "1.0 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Adama Agricultural Solutions",
                    lastingIpmRule = "Lasting solution: Vapor-active eradicant; completely distinct mode of action for rotating against Triazoles."
                )
            )
        ),

        // =========================================================================
        // 3. TWO-SPOTTED RED SPIDER MITE (Tetranychus urticae) - CUT FLOWERS & VEGETABLES
        // Sources: CABI BioProtection Portal, PCPB Kenya, Infonet-Biovision
        // =========================================================================
        CropThreat(
            id = "spider_mites",
            name = "Two-Spotted Spider Mite",
            scientificName = "Tetranychus urticae",
            type = ThreatType.PEST,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Chrysanthemums", "Tomato", "French Beans", "Pepper"),
            affectedParts = listOf("Leaves", "Flower Buds", "Shoot Tips"),
            severityLevel = "Extreme",
            summary = "Most prolific greenhouse pest worldwide. Thousands of mites feed on the underside of rose and vegetable leaves, producing fine silk webbing, chlorotic speckling, severe bronzing, and complete defoliation.",
            diagnosticKeys = listOf(
                "Fine yellow/white stippled chlorotic flecks on the upper surface of leaves.",
                "Silken webbing stretching across leaf undersides, stem forks, and flower buds.",
                "Tiny (0.5mm) pale yellow-green or dark red mites with two dark lateral spots visible with a 10x hand lens.",
                "Leaves turn bronze, dry out like parchment, and drop prematurely."
            ),
            favorableConditions = "Hot, dry conditions (temperatures >25°C, relative humidity <55%). Under 30°C, a generation completes in only 7–10 days.",
            imageResId = R.drawable.sample_rose_spider_mite,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Cluster on Leaf Underside",
                    scientificSub = "Spherical Translucent Eggs",
                    iconType = StageIconType.EGG,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Females lay up to 20 spherical eggs per day glued to silk threads on the lower leaf surface.",
                    symptomsToSpot = "Minute glass-like pearl spheres nestled within silk webbing on lower leaf surface.",
                    targetAction = "Apply specific ovicide or release predatory mites (Phytoseiulus persimilis).",
                    recommendedActiveIngredients = listOf("Hexythiazox 100 g/kg", "Bifenazate", "Clofentezine")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Six-Legged Larva & Protonymph",
                    scientificSub = "Immature Feeding Instars",
                    iconType = StageIconType.LARVA,
                    duration = "3 – 5 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Newly hatched larvae and nymphs begin puncturing individual plant epidermal cells to suck chlorophyll.",
                    symptomsToSpot = "Light yellow pinprick speckles on top of leaves.",
                    targetAction = "Target with translaminar METI acaricides or beneficial biocontrol release.",
                    recommendedActiveIngredients = listOf("Abamectin 18 g/L", "Spirodiclofen 240 g/L", "Milbemectin")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Deutonymph & Quiescent Chlamydospore",
                    scientificSub = "Premature Moult Stage",
                    iconType = StageIconType.PUPA,
                    duration = "1 – 2 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Mites anchor to leaf silk and become inactive while developing into reproductive adults.",
                    symptomsToSpot = "Motionless mites attached to leaf veins.",
                    targetAction = "Broad-spectrum contact botanical oils or neem extracts.",
                    recommendedActiveIngredients = listOf("Azadirachtin (Neem)", "Orange Oil")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Reproductive Adults & Webbing Colonies",
                    scientificSub = "Adult Oviposition & Dispersal",
                    iconType = StageIconType.ADULT,
                    duration = "14 – 21 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Adults feed voraciously and spin dense web sheets. Mites cluster on shoot tips (web balls) to catch wind drafts.",
                    symptomsToSpot = "Heavy silk canopy across shoot tips; thousands of crawling reddish mites.",
                    targetAction = "Emergency knockdown knock-out acaricide tank mixed with ovicide.",
                    recommendedActiveIngredients = listOf("Bifenazate 240 g/L", "Acequinocyl", "Fenpyroximate")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Maintain greenhouse relative humidity above 65% through misting or wet floor walkways to slow mite egg laying.",
                    "Overhead misting / sprinkler washes in the morning to knock mites off foliage and reduce temperature.",
                    "Remove dusty road borders near greenhouse ventilation openings (mites thrive in dust deposits).",
                    "Eradicate weeds around greenhouse walls (Datura, Black nightshade, amaranth) which host overwintering spider mites."
                ),
                physicalMechanicalControls = listOf(
                    "Prune heavily webbed 'hot-spot' rose shoots and burn them immediately; do not carry through clean benches.",
                    "High-pressure clean water spray directed at leaf undersides to physically wash off mites and tear webs."
                ),
                biologicalControls = listOf(
                    "Phytoseiulus persimilis predatory mites (CABI BioProtection registered Spidex / Phytoline): Voracious obligate predator consuming 20 mite eggs or 5 adults per day.",
                    "Neoseiulus californicus (Spical) predatory mite capable of surviving on pollen when spider mite populations drop.",
                    "Beauveria bassiana entomopathogenic fungus (Bb-Protec / BroadBand) that infects mite cuticle.",
                    "Cold-pressed neem oil (Azadirachtin) disrupting mite hormone receptors and oviposition."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Dynamec 1.8 EC",
                        pcpbRegNo = "PCPB(CR)0311",
                        formulation = "18 g/L EC",
                        activeIngredient = "Abamectin (18 g/L)",
                        chemicalGroup = "IRAC Group 6 (Avermectins - Chloride channel activators)",
                        classification = "Translaminar Contact & Stomach Acaricide",
                        targetStage = "Larvae, Nymphs & Feeding Adults (Stages 2 & 4)",
                        dosagePerKnapsack = "10ml in 20L knapsack (0.5ml/L water)",
                        dosagePerHectare = "0.5 L/ha in 1000L water",
                        phiDays = 3,
                        reiHours = 24,
                        applicationTiming = "Apply at first sign of stippling before dense webbing accumulates.",
                        resistanceManagement = "Maximum 2 applications per crop cycle. Always rotate with different IRAC groups (e.g. Floramite or Envidor) as spider mites rapidly develop resistance to abamectin.",
                        safetyPpe = listOf("Nitrile gloves", "Respirator mask", "Goggles", "Coveralls", "Boots")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Floramite 240 SC",
                        pcpbRegNo = "PCPB(CR)0728",
                        formulation = "240 SC (Suspension Concentrate)",
                        activeIngredient = "Bifenazate (240 g/L)",
                        chemicalGroup = "IRAC Group 20D (Mitochondrial Complex III electron transport inhibitor)",
                        classification = "Fast Knockdown & Residual Acaricide",
                        targetStage = "All Mobile Stages & Eggs (Stages 1, 2 & 4)",
                        dosagePerKnapsack = "8ml - 10ml in 20L knapsack (0.4ml - 0.5ml/L water)",
                        dosagePerHectare = "0.4 L/ha in 1000L water",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply for fast knockdown of heavy populations.",
                        resistanceManagement = "Strictly 1 application per year/season to prevent resistance development.",
                        safetyPpe = listOf("Chemical gloves", "Eye protection", "Protective clothing")
                    )
                ),
                economicThreshold = "In cut roses: 1 mite per 20 leaves inspected requires biocontrol release or chemical spot spray."
            ),
            scoutChecklist = listOf(
                "Check undersides of mid-canopy leaves on 20 plants across every greenhouse bay.",
                "Look for fine chlorotic speckling on upper leaf surface using bright sunlight.",
                "Scan shoot tips for silk strands and web balled colonies.",
                "Check plants bordering paths, heaters, and ventilation intake louvers."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Neem Seed Kernel Extract (NSKE 5%) Spray",
                    category = "Botanical Insecticide",
                    description = "Infonet organic preparation: Azadirachtin blocks mite moulting and acts as an anti-feedant.",
                    recipeOrMethod = "Pound 500g clean dry neem seeds into coarse powder. Soak in 10L clean water overnight. Filter through fine cloth; add 10ml liquid soap. Spray leaf undersides thoroughly."
                ),
                InfonetPractice(
                    title = "Garlic & Hot Chili Liquid Extract",
                    category = "Botanical Repellent",
                    description = "Infonet pungent repellent causing mite irritation and reducing oviposition.",
                    recipeOrMethod = "Crush 100g hot chili peppers and 100g garlic cloves. Soak in 1L water with 1 tbsp vegetable oil for 24 hours. Strain and dilute into 10L water with soap. Apply twice weekly."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Spidex Vital",
                    activeAgent = "Phytoseiulus persimilis (Predatory Mite)",
                    type = BioProtectionType.MACROBIAL_PREDATOR,
                    targetOrganism = "Tetranychus urticae (Two-Spotted Spider Mite)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Chrysanthemums", "Tomato"),
                    modeOfAction = "Adult predators actively hunt and consume spider mite eggs, nymphs, and adults across foliage.",
                    applicationRate = "Preventative: 2 mites/m²; Curative hot-spots: 10–20 mites/m².",
                    safetyMrl = "Beneficial macrobial insect; 0 days PHI; completely chemical free.",
                    supplierOrDistributor = "Koppert Biological Systems / CABI Portal"
                ),
                CabiBioProduct(
                    tradeName = "Bb-Protec",
                    activeAgent = "Beauveria bassiana strain R444 (1x10^10 conidia/g)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Spider mites, Whiteflies, Thrips",
                    targetCrops = listOf("Cut Flowers", "Vegetables", "Horticulture"),
                    modeOfAction = "Conidia germinate on mite cuticle, penetrate using chitinase enzymes, and kill the host within 3–5 days.",
                    applicationRate = "20g in 20L knapsack (1g/L water).",
                    safetyMrl = "Exempt from MRL; Certified Organic.",
                    supplierOrDistributor = "Real IPM Kenya / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Dynamec 1.8 EC",
                    activeIngredient = "Abamectin 18g/L",
                    pcpbRegNo = "PCPB(CR)0311",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Horticulture"),
                    fracIracGroup = "IRAC Group 6 (Avermectins)",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 3,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Limit to 2 sprays per crop cycle. Never use sub-lethal dosages. Alternate with IRAC 20D (Floramite) or IRAC 23 (Envidor)."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Floramite 240 SC",
                    activeIngredient = "Bifenazate 240g/L",
                    pcpbRegNo = "PCPB(CR)0728",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Vegetables"),
                    fracIracGroup = "IRAC Group 20D",
                    knapsackDosage = "8ml - 10ml per 20L knapsack",
                    perHaDosage = "0.4 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "UPL East Africa",
                    lastingIpmRule = "Lasting solution: Safe to beneficial predatory mites (Phytoseiulus persimilis); ideal bridge for IPM programs."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Envidor 240 SC",
                    activeIngredient = "Spirodiclofen 240g/L",
                    pcpbRegNo = "PCPB(CR)0798",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Roses", "Vegetables", "Citrus"),
                    fracIracGroup = "IRAC Group 23 (Lipid synthesis inhibitors)",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "0.4 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Potent ovicide and nymphicide; stops egg fertility in female mites."
                )
            )
        ),

        // =========================================================================
        // 4. WESTERN FLOWER THRIPS (Frankliniella occidentalis) - CUT FLOWERS & VEGETABLES
        // Sources: CABI BioProtection Portal, PCPB Kenya, Infonet-Biovision
        // =========================================================================
        CropThreat(
            id = "western_flower_thrips",
            name = "Western Flower Thrips",
            scientificName = "Frankliniella occidentalis",
            type = ThreatType.PEST,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Chrysanthemums", "Lisianthus", "French Beans", "Tomato", "Pepper"),
            affectedParts = listOf("Flowers/Buds", "Petals", "Leaves", "Growing Tips"),
            severityLevel = "Extreme",
            summary = "Premier pest of cut flower export operations and vector of Tomato Spotted Wilt Virus (TSWV). Feeds deep inside flower buds and petals, causing silvering scars, necrotic petal edges, deformed blooms, and rapid quarantine interception.",
            diagnosticKeys = listOf(
                "Silvery, bleached feeding patches on flower petals with tiny black specks of tar-like fecal droplets.",
                "Brown, necrotic petal margins (petal scorch) rendering cut roses unmarketable.",
                "Curled, scarred, and puckered young leaves with distorted growth points.",
                "Slender (1.2mm–1.5mm) pale yellow to dark brown active insects scurrying inside flower petals when blown gently."
            ),
            favorableConditions = "Warm greenhouse climates (22°C–28°C), dry air, presence of flowering weed borders.",
            imageResId = R.drawable.img_flower_thrips,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Inserted into Plant Flesh",
                    scientificSub = "Endophytic Egg Deposition",
                    iconType = StageIconType.EGG,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Females cut slits into petals, sepals, and leaf tissue with their saw-like ovipositor to embed individual kidney-shaped eggs.",
                    symptomsToSpot = "Minute blister bumps on petal surfaces; eggs are shielded from contact insecticides.",
                    targetAction = "Systemic insecticides with translaminar action or beneficial predatory bugs.",
                    recommendedActiveIngredients = listOf("Spinetoram", "Spinosad", "Fipronil")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "First & Second Instar Larvae",
                    scientificSub = "Folivory & Petal Rasping",
                    iconType = StageIconType.LARVA,
                    duration = "4 – 6 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Wingless pale-yellow larvae feed aggressively deep within flower bud crevices, rasping cell contents.",
                    symptomsToSpot = "Fine silver streaks and tiny black frass specks on interior petal bases.",
                    targetAction = "Apply predatory mites (Amblyseius swirskii) or Spinosad/Spinetoram spray.",
                    recommendedActiveIngredients = listOf("Delegate 250 WG", "Tracer 480 SC")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Prepupa & Pupa in Soil / Growing Medium",
                    scientificSub = "Subterranean Metamorphosis",
                    iconType = StageIconType.PUPA,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Mature second instars drop from flowers into the soil or coco-peat substrate to pupate in darkness.",
                    symptomsToSpot = "Insects disappear from foliage to undergo pupation in root mulch.",
                    targetAction = "Apply soil predatory mites (Hypoaspis miles / Stratiolaelaps) or entomopathogenic nematodes.",
                    recommendedActiveIngredients = listOf("Entomopathogenic Nematodes (Steinernema feltiae)", "Metarhizium anisopliae")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Winged Adults & Virus Transmission",
                    scientificSub = "Dispersal & TSWV Vectoring",
                    iconType = StageIconType.ADULT,
                    duration = "20 – 30 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Narrow-winged adults fly between flower heads, laying up to 200 eggs and transmitting Tomato Spotted Wilt Virus.",
                    symptomsToSpot = "Swarming insects in flower blooms; blue sticky roller traps covered with thrips.",
                    targetAction = "Mass trapping with blue sticky roller traps + pheromone lures; adulticide spray.",
                    recommendedActiveIngredients = listOf("Radiant 120 SC", "Match 050 EC")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Install insect-proof netting (0.15mm thrips mesh) over all greenhouse ventilation openings.",
                    "Hang blue sticky cards and blue sticky roller tape (1 trap per 100m²; blue is more attractive to Western Flower Thrips than yellow).",
                    "Harvest cut flowers at proper stage; avoid leaving open over-mature blooms in greenhouse blocks.",
                    "Remove flowering weeds (especially dandelion and clover) around greenhouse perimeters."
                ),
                physicalMechanicalControls = listOf(
                    "Mass trapping with blue sticky rolls integrated with thrips aggregation pheromone dispensers (Thripline / Phero-Tape).",
                    "Use double-door airlock entry systems into production greenhouses with positive pressure fans."
                ),
                biologicalControls = listOf(
                    "Amblyseius swirskii (Swirski-Mite) predatory mite: Excellent predator of thrips larvae in cut flowers.",
                    "Orius laevigatus (Minute Pirate Bug): Aggressive predator that enters deep flower buds to consume adult thrips.",
                    "Hypoaspis miles (Stratiolaelaps scimitus): Soil-dwelling predatory mite that hunts pupating thrips in coco peat/soil.",
                    "Metarhizium anisopliae (Campaign / Met 69) and Beauveria bassiana bio-insecticides."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Delegate 250 WG",
                        pcpbRegNo = "PCPB(CR)1098",
                        formulation = "250 WG (Water Dispersible Granules)",
                        activeIngredient = "Spinetoram (250 g/kg)",
                        chemicalGroup = "IRAC Group 5 (Spinosyns - Nicotinic acetylcholine receptor allosteric modulators)",
                        classification = "Translaminar Contact & Ingestion",
                        targetStage = "Larvae & Adults (Stages 2 & 4)",
                        dosagePerKnapsack = "4g - 6g in 20L knapsack (0.2g - 0.3g/L water)",
                        dosagePerHectare = "0.2 - 0.3 kg/ha in 1000L water",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply early morning when adults are active in open blooms.",
                        resistanceManagement = "Maximum 2 applications per crop cycle. Rotate with IRAC 15 (Lufenuron) or IRAC 4A (ACTARA) to protect spinosyn susceptibility.",
                        safetyPpe = listOf("Nitrile gloves", "Dust/mist respirator", "Eye goggles", "Coveralls")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Match 050 EC",
                        pcpbRegNo = "PCPB(CR)0354",
                        formulation = "050 EC (Emulsifiable Concentrate)",
                        activeIngredient = "Lufenuron (50 g/L)",
                        chemicalGroup = "IRAC Group 15 (Benzoylureas - Chitin biosynthesis inhibitors)",
                        classification = "Insect Growth Regulator (IGR) & Ovi-larvicide",
                        targetStage = "Egg Viability & Larval Moulting (Stages 1 & 2)",
                        dosagePerKnapsack = "20ml in 20L knapsack (1.0ml/L water)",
                        dosagePerHectare = "1.0 L/ha in 1000L water",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply as population builder preventive to disrupt lifecycle moulting.",
                        resistanceManagement = "Rotate after 1-2 sprays. Safe to adult beneficial predators.",
                        safetyPpe = listOf("Gloves", "Protective eyewear", "Boots")
                    )
                ),
                economicThreshold = "Cut flower export standard: 1–2 thrips per 10 flowers sampled warrants immediate corrective measures."
            ),
            scoutChecklist = listOf(
                "Tap flower blooms over a white sheet of paper or scouting clipboard; count scurrying thrips.",
                "Check blue sticky trap counts weekly to detect population surges.",
                "Inspect petal tips for brown margin scorch and silver streaks.",
                "Check for signs of TSWV (ring spots, chlorotic streaking) on young shoots."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Pyrethrum Flower Natural Extract",
                    category = "Natural Botanical Knockdown",
                    description = "Infonet certified organic Kenyan pyrethrum extract causing rapid sodium channel paralysis.",
                    recipeOrMethod = "Mix 20ml natural pyrethrum liquid (2% pyrethrins) with 10ml liquid castile soap in 10L clean water. Spray at dusk because pyrethrins degrade rapidly in UV sunlight."
                ),
                InfonetPractice(
                    title = "Blue Sticky Mass Trapping Protocol",
                    category = "Physical Mechanical Control",
                    description = "Infonet mass trapping technique using specific blue wavelength cards attractive to thrips.",
                    recipeOrMethod = "Hang blue plastic sheets (20cm x 30cm) coated with entomological glue or engine grease at canopy level every 5 meters along cut flower benches."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Swirski-Mite",
                    activeAgent = "Amblyseius swirskii (Predatory Mite)",
                    type = BioProtectionType.MACROBIAL_PREDATOR,
                    targetOrganism = "Frankliniella occidentalis (Thrips) and Whitefly",
                    targetCrops = listOf("Cut Roses", "Carnations", "Chrysanthemums", "Sweet Peppers"),
                    modeOfAction = "Active predator that feeds on thrips first instar larvae and whitefly eggs.",
                    applicationRate = "100–250 mites/m²; release in breeding sachets with carrier mite food.",
                    safetyMrl = "Macro-organism; zero residue; zero export withholding.",
                    supplierOrDistributor = "Koppert Biological Systems / CABI Portal"
                ),
                CabiBioProduct(
                    tradeName = "Campaign (Met 69)",
                    activeAgent = "Metarhizium anisopliae strain ICIPE 69",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Thrips, Mealybugs, Fruit flies",
                    targetCrops = listOf("Cut Flowers", "Horticultural Crops"),
                    modeOfAction = "Entomopathogenic fungus; fungal spores attach to thrips cuticle, germinate and kill within 48–72 hours.",
                    applicationRate = "20ml in 20L knapsack.",
                    safetyMrl = "Zero MRL; Organic certified; native Kenyan ICIPE isolate.",
                    supplierOrDistributor = "Real IPM Kenya / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Delegate 250 WG",
                    activeIngredient = "Spinetoram 250g/kg",
                    pcpbRegNo = "PCPB(CR)1098",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Horticulture"),
                    fracIracGroup = "IRAC Group 5 (Spinosyns)",
                    knapsackDosage = "4g - 6g per 20L knapsack",
                    perHaDosage = "0.2 - 0.3 kg/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Corteva Agriscience East Africa",
                    lastingIpmRule = "Lasting solution: Highly potent with translaminar penetration; rotate with IRAC 15 (Match) or IRAC 28 (Coragen)."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Tracer 480 SC",
                    activeIngredient = "Spinosad 480g/L",
                    pcpbRegNo = "PCPB(CR)0532",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Cut Flowers", "Vegetables"),
                    fracIracGroup = "IRAC Group 5",
                    knapsackDosage = "5ml - 8ml per 20L knapsack",
                    perHaDosage = "0.2 - 0.3 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Corteva Agriscience",
                    lastingIpmRule = "Lasting solution: Derived from natural soil actinomycete; organic approved formulation in many markets."
                )
            )
        ),

        // =========================================================================
        // 5. ROSE DOWNY MILDEW (Peronospora sparsa) - CUT FLOWERS
        // Sources: PCPB Kenya Floriculture Registrations, CABI BioProtection Portal
        // =========================================================================
        CropThreat(
            id = "rose_downy_mildew",
            name = "Rose Downy Mildew",
            scientificName = "Peronospora sparsa",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Summer Flowers"),
            affectedParts = listOf("Leaves", "Stems", "Flower Calyx"),
            severityLevel = "Extreme",
            summary = "The most catastrophic disease in high-altitude cut rose production. Causes sudden, severe defoliation within 48–72 hours under cool, saturated humidity. Can destroy an entire rose greenhouse block in days.",
            diagnosticKeys = listOf(
                "Angular, vein-delimited purplish-red to dark brown necrotic spots on upper leaf surface.",
                "Sparse, delicate whitish-grey downy felt on leaf undersides (often fleeting and drying quickly).",
                "Severe premature leaf drop: leaves drop at the slightest touch, leaving bare thorny stems ('goose neck').",
                "Dark purple-black longitudinal cankers on flower stems."
            ),
            favorableConditions = "Cool temperatures (15°C–18°C) accompanied by saturated relative humidity (>85%) for at least 4 consecutive hours (dew, ceiling drip, poor ventilation).",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Oospore Overwintering & Sporangia Drift",
                    scientificSub = "Primary Oospore Germination",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 4 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Windblown sporangia or rain/condensation splashes land on moist rose leaves from latent stem cankers.",
                    symptomsToSpot = "No visible foliar spots; microscopic sporangia present in ceiling condensation drips.",
                    targetAction = "Apply multi-site protective barrier before night cooling.",
                    recommendedActiveIngredients = listOf("Mancozeb", "Copper Hydroxide", "Chlorothalonil")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Stomatal Penetration & Internal Growth",
                    scientificSub = "Haustoria Penetration",
                    iconType = StageIconType.PENETRATION,
                    duration = "4 – 8 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Germ tube enters through lower leaf stomata and rapidly establishes intercellular mycelium between spongy mesophyll cells.",
                    symptomsToSpot = "Angular pale green or purplish flecks bounded sharply by leaf veins.",
                    targetAction = "Apply systemic oomycete fungicide with acropetal and basipetal translocation.",
                    recommendedActiveIngredients = listOf("Fosetyl-Al + Propamocarb (Previcur Energy)", "Mandipropamid")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Purplish Angular Lesions & Defoliation",
                    scientificSub = "Abscission Layer Activation",
                    iconType = StageIconType.LESION,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Infected cells trigger premature ethylene synthesis, causing the leaf petiole abscission layer to dissolve.",
                    symptomsToSpot = "Entire green and purplish leaves drop en masse when greenhouse bench is brushed.",
                    targetAction = "Emergency curative spray; sweep and bag fallen leaves immediately.",
                    recommendedActiveIngredients = listOf("Dimethomorph", "Cymoxanil + Mancozeb")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Stem Cankering & Sporulation",
                    scientificSub = "Peronospora Sporulation",
                    iconType = StageIconType.DISPERSAL,
                    duration = "Continuous under >90% RH",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Mycelium invades stem cortex causing permanent purple-black cankers where resting oospores survive.",
                    symptomsToSpot = "Long purple-brown sunken marks on flowering stems; export rejection.",
                    targetAction = "Prune infected canes down to healthy crown tissue; apply systemic anti-sporulant.",
                    recommendedActiveIngredients = listOf("Fluopicolide", "Zoxamide")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Maintain greenhouse heating (minimum 16°C) and activate ridge ventilation 30 minutes before sunset to eliminate condensation.",
                    "Fix leaking roof plastic and condensation ceiling gutters to prevent dripping on rose canopies.",
                    "Drip irrigation only; irrigate early morning so soil surface dries before nightfall.",
                    "Disinfect rose pruning shears with 70% alcohol or 10% bleach between varieties."
                ),
                physicalMechanicalControls = listOf(
                    "Sweep and incinerate all fallen rose leaves daily; do not leave blighted foliage on walkway floors.",
                    "Prune out canes showing purple longitudinal cankers."
                ),
                biologicalControls = listOf(
                    "Bacillus amyloliquefaciens foliar biofilm protectant.",
                    "Phosphite salts (Potassium phosphite) inducing systemic acquired resistance (SAR).",
                    "Chitosan oligosaccharide bio-stimulant strengthening rose leaf epidermal cell walls."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Previcur Energy 840 SL",
                        pcpbRegNo = "PCPB(CR)0845",
                        formulation = "840 SL (Soluble Liquid)",
                        activeIngredient = "Propamocarb-HCl (530 g/L) + Fosetyl-Al (310 g/L)",
                        chemicalGroup = "FRAC Group 28 (Carbamate) + FRAC Group P07 (Phosphonate)",
                        classification = "Fully Systemic Curative & Defensive Booster",
                        targetStage = "Internal Colonization & Stem Infection (Stages 2 & 3)",
                        dosagePerKnapsack = "40ml in 20L knapsack (2.0ml/L water)",
                        dosagePerHectare = "2.0 L/ha in 1000L water",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "At first indication of angular purplish spots or high-humidity forecast.",
                        resistanceManagement = "Dual multi-pathway active ingredients drastically reduce resistance development. Rotate with FRAC 40 (Revus).",
                        safetyPpe = listOf("Chemical resistant apron", "Nitrile gloves", "Face shield", "Boots")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Revus 250 SC",
                        pcpbRegNo = "PCPB(CR)0984",
                        formulation = "250 SC (Suspension Concentrate)",
                        activeIngredient = "Mandipropamid (250 g/L)",
                        chemicalGroup = "FRAC Group 40 (CAA - Carboxylic acid amides)",
                        classification = "Translaminar Anti-Sporulant & Protectant",
                        targetStage = "Sporangia Germination & Haustoria (Stages 1 & 2)",
                        dosagePerKnapsack = "12ml in 20L knapsack (0.6ml/L water)",
                        dosagePerHectare = "0.6 L/ha in 1000L water",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Preventative application during cool rainy periods.",
                        resistanceManagement = "Rotate after maximum 2 applications. Never use curatively on completely defoliated blocks.",
                        safetyPpe = listOf("Nitrile gloves", "Goggles", "Coveralls")
                    )
                ),
                economicThreshold = "Zero tolerance: 1 confirmed Downy Mildew lesion in a rose greenhouse requires immediate block-wide containment."
            ),
            scoutChecklist = listOf(
                "Inspect under-gutter rows and low-lying cold spots in the greenhouse early morning.",
                "Gently tap branches to see if leaflets drop abnormally without resistance.",
                "Examine leaf undersides with a hand lens for angular purple boundaries and faint downy sporulation.",
                "Check stems for dark purplish oval cankers."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Greenhouse Dew-Point Management Protocol",
                    category = "Cultural Prevention",
                    description = "Infonet environmental climate control preventing free water condensation on rose leaves.",
                    recipeOrMethod = "Keep heating pipes at 40°C–45°C during dawn and dusk while keeping ridge vents open 10% to purge humid air and prevent temperature drops below the dew point."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Serenade ASO",
                    activeAgent = "Bacillus subtilis strain QST 713",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Peronospora sparsa (Downy Mildew)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Vegetables"),
                    modeOfAction = "Colonizes leaf stomata and synthesizes lipopeptides (surfactins, iturins) that prevent oomycete spore germination.",
                    applicationRate = "60ml in 20L knapsack.",
                    safetyMrl = "Zero MRL; exempt from PHI.",
                    supplierOrDistributor = "Bayer CropScience Biologics / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Previcur Energy 840 SL",
                    activeIngredient = "Propamocarb 530g/L + Fosetyl-Al 310g/L",
                    pcpbRegNo = "PCPB(CR)0845",
                    formulation = "Soluble Liquid (SL)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Ornamentals", "Vegetables"),
                    fracIracGroup = "FRAC Group 28 + FRAC P07",
                    knapsackDosage = "40ml per 20L knapsack",
                    perHaDosage = "2.0 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Acropetal and basipetal systemic movement protects new flush flushes; rotate with Mandipropamid (Revus)."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Revus 250 SC",
                    activeIngredient = "Mandipropamid 250g/L",
                    pcpbRegNo = "PCPB(CR)0984",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Roses", "Vegetables", "Potatoes"),
                    fracIracGroup = "FRAC Group 40 (CAA)",
                    knapsackDosage = "12ml per 20L knapsack",
                    perHaDosage = "0.6 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Binds to leaf wax within 1 hour; provides rainfast anti-sporulant protection."
                )
            )
        ),

        // =========================================================================
        // 6. FALL ARMYWORM (Spodoptera frugiperda) - CEREALS & MAIZE
        // Sources: Infonet-Biovision, PCPB Kenya, CABI BioProtection Portal
        // =========================================================================
        CropThreat(
            id = "fall_armyworm",
            name = "Fall Armyworm",
            scientificName = "Spodoptera frugiperda",
            type = ThreatType.PEST,
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetCrops = listOf("Maize", "Sorghum", "Sweet Corn", "Rice", "Wheat"),
            affectedParts = listOf("Whorl", "Leaves", "Tassel", "Maize Cob", "Ear"),
            severityLevel = "Extreme",
            summary = "Invasive trans-boundary moth pest across Africa. Caterpillars feed aggressively deep within maize whorls, shredding leaves, severing growing points ('deadheart'), and boring directly into developing ears.",
            diagnosticKeys = listOf(
                "Shredded, ragged leaves with large irregular holes emerging from the central maize whorl.",
                "Copious mounds of sawdust-like yellowish-brown frass (caterpillar excrement) packed inside the whorl.",
                "Mature caterpillars with distinct inverted 'Y' pale suture on head capsule and four raised black dots arranged in a square on the 8th abdominal segment.",
                "Bored entrance holes in maize cobs with rotting kernels."
            ),
            favorableConditions = "Warm humid conditions (25°C–32°C). High egg-laying flushes following onset of rains.",
            imageResId = R.drawable.img_fall_armyworm,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Masses Under Scales",
                    scientificSub = "Felted Egg Cluster",
                    iconType = StageIconType.EGG,
                    duration = "2 – 3 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Females lay clusters of 100–200 dome-shaped eggs covered by greyish-pink hair-like body scales on leaf undersides.",
                    symptomsToSpot = "Felted grey patches on lower leaves of young maize plants.",
                    targetAction = "Apply egg-parasitoids (Trichogramma) or early microbial ovicides.",
                    recommendedActiveIngredients = listOf("Spinetoram", "Lufenuron", "Baculovirus SfMNPV")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Instars 1–3 'Window Pane' Feeding",
                    scientificSub = "Early Instar Folivory",
                    iconType = StageIconType.LARVA,
                    duration = "4 – 6 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Young caterpillars scrape leaf epidermis without chewing through, leaving thin translucent window patches before migrating into the whorl.",
                    symptomsToSpot = "Translucent papery 'window pane' patches on whorl leaves; tiny dark larvae.",
                    targetAction = "Optimal window for biocontrol or bio-pesticide spray before caterpillars hide in deep whorl.",
                    recommendedActiveIngredients = listOf("Bacillus thuringiensis (Bt)", "Ampligo 150 ZC", "Radiant 120 SC")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Instars 4–6 Deep Whorl Shredding",
                    scientificSub = "Voracious Internal Larval Feeding",
                    iconType = StageIconType.LARVA,
                    duration = "8 – 14 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Large (3–4cm) caterpillars shelter deep inside whorls protected by a plug of moist frass. Severely shreds leaves and eats growing tips.",
                    symptomsToSpot = "Piles of moist sawdust-like frass; ragged ragged shot-hole leaves.",
                    targetAction = "Direct knapsack spray jet into whorl cup using high water volume.",
                    recommendedActiveIngredients = listOf("Chlorantraniliprole 200 g/L", "Flubendiamide 480 g/L")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Pupation in Soil Cocoon",
                    scientificSub = "Subterranean Pupation",
                    iconType = StageIconType.PUPA,
                    duration = "7 – 10 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Mature larvae drop to the ground and construct earthen cocoons 2–8 cm below the soil surface to pupate.",
                    symptomsToSpot = "Disappearance of large larvae from whorls; empty frass chambers.",
                    targetAction = "Soil cultivation, intercropping, and weeding to expose cocoons to birds and sun.",
                    recommendedActiveIngredients = listOf("Soil Inter-row Tillage")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Adult Night-Flying Moth",
                    scientificSub = "Migratory Noctuid Moth",
                    iconType = StageIconType.ADULT,
                    duration = "10 – 14 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Nocturnal moths fly up to 100km per night on wind currents, mating and seeking fresh young cereals.",
                    symptomsToSpot = "Grey-brown moths with triangular white marks on forewings caught in pheromone traps.",
                    targetAction = "Pheromone funnel traps for monitoring flight peaks and mating disruption.",
                    recommendedActiveIngredients = listOf("Fall Armyworm Pheromone Lures")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Adopt Push-Pull technology: Intercrop maize with silverleaf or greenleaf Desmodium (pushes moth away) and plant Brachiaria / Napier grass as a trap crop border (pulls and traps caterpillars).",
                    "Early planting at the onset of rains so crops pass vulnerable vegetative stages before moth populations surge.",
                    "Hand-picking caterpillars and crushing egg masses in smallholder farms (<0.5 ha).",
                    "Drop a pinch of dry wood ash, clean sand, or fine soil into the central whorl of young maize plants to abrade caterpillar cuticle."
                ),
                physicalMechanicalControls = listOf(
                    "Install FAW sex pheromone funnel traps (2 traps per hectare) to monitor moth arrival.",
                    "Deep plowing before planting to expose pupae to predatory ants, birds, and sunlight."
                ),
                biologicalControls = listOf(
                    "Fawligen / Spodoptera frugiperda Multiple Nucleopolyhedrovirus (SfMNPV): Highly specific viral pathogen causing fatal liquification of larvae.",
                    "Bacillus thuringiensis kurstaki (Dipel / Thuricide): Bio-insecticide crystal proteins that destroy caterpillar gut lining.",
                    "Trichogramma egg parasitoid wasps that sting and destroy FAW egg masses.",
                    "Metarhizium rileyi entomopathogenic fungus infecting caterpillars in humid weather."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Ampligo 150 ZC",
                        pcpbRegNo = "PCPB(CR)1025",
                        formulation = "150 ZC (Capsule Suspension + Suspension Concentrate)",
                        activeIngredient = "Chlorantraniliprole (100 g/L) + Lambda-cyhalothrin (50 g/L)",
                        chemicalGroup = "IRAC Group 28 (Diamides) + IRAC Group 3A (Pyrethroids)",
                        classification = "Fast Knockdown & Long-Lasting Systemic",
                        targetStage = "Young Instars & Whorl Feeding Larvae (Stages 1-3)",
                        dosagePerKnapsack = "10ml in 20L knapsack (0.5ml/L water)",
                        dosagePerHectare = "0.2 - 0.25 L/ha in 400L water",
                        phiDays = 14,
                        reiHours = 24,
                        applicationTiming = "Apply at 10%–20% infested plants, directing spray into the whorl cup.",
                        resistanceManagement = "Maximum 2 applications per crop season. Alternate with non-diamide chemistry (e.g. Radiant or Belt).",
                        safetyPpe = listOf("Nitrile gloves", "Overalls", "Boots", "Eye goggles", "Respirator")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Radiant 120 SC",
                        pcpbRegNo = "PCPB(CR)1140",
                        formulation = "120 SC",
                        activeIngredient = "Spinetoram (120 g/L)",
                        chemicalGroup = "IRAC Group 5 (Spinosyns)",
                        classification = "Translaminar Muscle Paralytic",
                        targetStage = "Larvae Stages 1-4",
                        dosagePerKnapsack = "12ml in 20L knapsack",
                        dosagePerHectare = "0.25 L/ha",
                        phiDays = 7,
                        reiHours = 12,
                        applicationTiming = "Apply when window pane damage is first noticed on young maize.",
                        resistanceManagement = "Rotate with IRAC 28 or IRAC 15.",
                        safetyPpe = listOf("Nitrile gloves", "Mask", "Boots")
                    )
                ),
                economicThreshold = "At early whorl stage: 20% of plants showing fresh window pane or frass damage warrants treatment. At mid-whorl to tasseling: 40% infested plants."
            ),
            scoutChecklist = listOf(
                "Walk 'W' pattern through maize field; inspect 20 consecutive plants at 5 random stations.",
                "Unroll leaves of central whorl to search for small caterpillars and frass.",
                "Check leaf undersides for felted grey egg masses.",
                "Empty pheromone traps weekly and count captured adult male moths."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Push-Pull Cropping System (ICIPE / Infonet Flagship)",
                    category = "Ecological Agro-Ecosystem Control",
                    description = "Infonet breakthrough technology: Desmodium intercrop emits volatile semiochemicals repelling FAW moths, while Napier/Brachiaria borders attract and trap larvae with non-nutritive gum.",
                    recipeOrMethod = "Intercrop 1 row of Desmodium for every 1–2 rows of maize. Plant 3 perimeter rows of Napier or Brachiaria grass around the field border. Controls both Fall Armyworm and Striga weed."
                ),
                InfonetPractice(
                    title = "Fermented Plant & Chili Ash Whorl Treatment",
                    category = "Cultural Physical Barrier",
                    description = "Infonet smallholder field treatment using abrasive ash and botanical repellent.",
                    recipeOrMethod = "Mix 2 handfuls dry sieved wood ash with 1 tbsp powdered hot chili and 1 handful fine river sand. Drop 1/2 teaspoon into the central whorl of each infested maize plant."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Fawligen",
                    activeAgent = "Spodoptera frugiperda multiple nucleopolyhedrovirus (SfMNPV)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Spodoptera frugiperda (Fall Armyworm)",
                    targetCrops = listOf("Maize", "Sorghum", "Sweet corn"),
                    modeOfAction = "Biological baculovirus; caterpillars ingest viral occlusion bodies, dying of systemic viral infection within 3–5 days, releasing billions of new viral particles.",
                    applicationRate = "10ml - 15ml per 20L knapsack; spray late afternoon to minimize UV breakdown.",
                    safetyMrl = "Zero chemical residue; completely non-toxic to bees, predators, and mammals.",
                    supplierOrDistributor = "AgBiTech / CABI BioProtection Portal"
                ),
                CabiBioProduct(
                    tradeName = "Dipel DF",
                    activeAgent = "Bacillus thuringiensis subsp. kurstaki (32,000 IU/mg)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Lepidopteran caterpillars (FAW, DBM, Helicoverpa)",
                    targetCrops = listOf("Maize", "Vegetables", "Sorghum"),
                    modeOfAction = "Endotoxin crystals bind to midgut receptors, causing gut perforation and rapid feeding cessation.",
                    applicationRate = "25g in 20L knapsack.",
                    safetyMrl = "Zero PHI; Certified Organic.",
                    supplierOrDistributor = "Valent BioSciences / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Ampligo 150 ZC",
                    activeIngredient = "Chlorantraniliprole 100g/L + Lambda-cyhalothrin 50g/L",
                    pcpbRegNo = "PCPB(CR)1025",
                    formulation = "Capsule Suspension + Suspension Concentrate (ZC)",
                    targetCrops = listOf("Maize", "Sorghum", "Vegetables"),
                    fracIracGroup = "IRAC Group 28 + IRAC Group 3A",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "0.2 - 0.25 L/ha",
                    phiDays = 14,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Dual chemistry delivers quick pyrethroid knockdown + 21-day diamide residual; strictly limit to 2 sprays per season."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Belt 480 SC",
                    activeIngredient = "Flubendiamide 480g/L",
                    pcpbRegNo = "PCPB(CR)0933",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Maize", "Tomatoes", "Vegetables"),
                    fracIracGroup = "IRAC Group 28 (Ryanodine receptor modulator)",
                    knapsackDosage = "5ml - 7ml per 20L knapsack",
                    perHaDosage = "0.15 L/ha",
                    phiDays = 7,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: High safety margin to beneficial insects and predators; highly effective on resistant caterpillar instars."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Coragen 20 SC",
                    activeIngredient = "Chlorantraniliprole 200g/L",
                    pcpbRegNo = "PCPB(CR)0899",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Maize", "Vegetables", "Potatoes"),
                    fracIracGroup = "IRAC Group 28",
                    knapsackDosage = "5ml - 7.5ml per 20L knapsack",
                    perHaDosage = "0.15 L/ha",
                    phiDays = 7,
                    reiHours = 12,
                    registrant = "FMC Agricultural Solutions",
                    lastingIpmRule = "Lasting solution: Long residual ovi-larvicidal action; protects newly emerged whorl leaves."
                )
            )
        ),

        // =========================================================================
        // 7. TOMATO LEAFMINER (Tuta absoluta) - HORTICULTURE & VEGETABLES
        // Sources: Infonet-Biovision, PCPB Kenya, CABI BioProtection Portal
        // =========================================================================
        CropThreat(
            id = "tuta_absoluta",
            name = "Tomato Leafminer",
            scientificName = "Tuta absoluta",
            type = ThreatType.PEST,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Tomato", "Potato", "Eggplant", "Pepper"),
            affectedParts = listOf("Leaves", "Stem", "Fruit", "Growing Tips"),
            severityLevel = "Extreme",
            summary = "Gelechiid micro-moth pest capable of 80%–100% crop destruction. Larvae mine between upper and lower leaf epidermises creating broad translucent blotch mines, bore into stems, and tunnel under fruit calyxes.",
            diagnosticKeys = listOf(
                "Irregular blotch mines on leaves with dark granular frass piled inside.",
                "Mined leaves turn papery and necrotic, resembling severe late blight or drought scorch.",
                "Pinhead-sized bore holes beneath green or ripe tomato fruit calyx.",
                "Minute cream to green caterpillars with a distinctive dark band behind the head capsule."
            ),
            favorableConditions = "Warm dry conditions (25°C–30°C); continuous cultivation of Solanaceous crops without host break.",
            imageResId = R.drawable.img_tuta_absoluta,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Deposition on Leaf Underside",
                    scientificSub = "Cylindrical Creamy Eggs",
                    iconType = StageIconType.EGG,
                    duration = "3 – 5 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Females lay up to 260 microscopic cream-colored eggs on the underside of young tomato leaves and sepals.",
                    symptomsToSpot = "Minute yellowish-white eggs along leaf veins visible under magnifying lens.",
                    targetAction = "Apply egg parasitoids (Trichogramma) or ovi-larvicide insect growth regulators.",
                    recommendedActiveIngredients = listOf("Lufenuron", "Azadirachtin", "Methoxydifenozide")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Blotchy Leaf & Stem Mining",
                    scientificSub = "Larval Endophytic Mining",
                    iconType = StageIconType.LARVA,
                    duration = "10 – 15 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Larvae burrow between epidermal layers, consuming mesophyll. Mines expand into large transparent blisters filled with black fecal pellets.",
                    symptomsToSpot = "Broad translucent leaf blisters with black frass grains; wilted stem tips.",
                    targetAction = "Apply penetrant translaminar or systemic insecticide with kickback action.",
                    recommendedActiveIngredients = listOf("Voliam Targo 063 SC", "Coragen 20 SC", "Spinetoram")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Fruit Boring & Calyx Entry",
                    scientificSub = "Fruit Pulp Colonization",
                    iconType = StageIconType.LARVA,
                    duration = "4 – 6 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Larvae bore into green and ripening tomato fruit, introducing secondary rot pathogens (Botrytis, Rhizopus).",
                    symptomsToSpot = "Small circular entry holes near fruit stem attachment; fruit drop.",
                    targetAction = "Apply targeted spray with short PHI before harvest.",
                    recommendedActiveIngredients = listOf("Abamectin", "Emamectin Benzoate")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Pupation in Leaf Fold or Soil",
                    scientificSub = "Silk Cocoon Pupation",
                    iconType = StageIconType.PUPA,
                    duration = "7 – 10 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Larvae exit mines on silk threads to pupate in dried leaf folds or top 2cm of soil in silken cocoons.",
                    symptomsToSpot = "White silk cocoons inside curled dried leaflets or on mulch surface.",
                    targetAction = "Soil solarization between crop cycles; predatory mirid bugs (Nesidiocoris).",
                    recommendedActiveIngredients = listOf("Soil Solarization", "Nesidiocoris tenuis")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Adult Night-Flying Micro-Moth",
                    scientificSub = "Gelechiid Moth Flight",
                    iconType = StageIconType.ADULT,
                    duration = "10 – 15 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Small (6–7mm) silvery-grey moths with dark spots fly actively at dawn and dusk.",
                    symptomsToSpot = "Swarming micro-moths fluttering when tomato foliage is disturbed.",
                    targetAction = "Mass trapping with Tutasan pheromone water-oil traps.",
                    recommendedActiveIngredients = listOf("Tuta Absoluta Pheromone Lures (Tutasan)")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Install Tutasan sex pheromone water traps (20–40 traps/ha for mass trapping; 4–8 traps/ha for monitoring).",
                    "Fit insect-proof netting (30 mesh / 0.9mm) on seedling nurseries and greenhouse sides.",
                    "Remove and incinerate all crop debris, culled fruit, and Solanaceous weeds immediately after final harvest.",
                    "Solarize soil with clear polyethylene sheets for 6–8 weeks between seasons."
                ),
                physicalMechanicalControls = listOf(
                    "Hand-pick and crush mined leaflets during early crop establishment before populations multiply.",
                    "Use yellow and black light sticky traps inside greenhouse bays."
                ),
                biologicalControls = listOf(
                    "Nesidiocoris tenuis mirid bug (CABI BioProtection registered): Predatory bug consuming Tuta eggs and early instar larvae.",
                    "Trichogramma achaeae / Trichogrammatoidea egg parasitoid wasps.",
                    "Bacillus thuringiensis (Dipel DF) applied during early evening hours.",
                    "Neem seed oil (Azadirachtin) disrupting larval metamorphosis."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Voliam Targo 063 SC",
                        pcpbRegNo = "PCPB(CR)1034",
                        formulation = "063 SC",
                        activeIngredient = "Chlorantraniliprole (45 g/L) + Abamectin (18 g/L)",
                        chemicalGroup = "IRAC Group 28 (Diamide) + IRAC Group 6 (Avermectin)",
                        classification = "Translaminar Contact & Ingestion",
                        targetStage = "Mined Larvae & Calyx Borers (Stages 2 & 3)",
                        dosagePerKnapsack = "12ml in 20L knapsack (0.6ml/L water)",
                        dosagePerHectare = "0.6 L/ha in 500L water",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at first trace of transparent blotch mines on young leaves.",
                        resistanceManagement = "Maximum 2 applications per crop cycle. Rotate with IRAC 5 (Radiant) or IRAC 15 (Match).",
                        safetyPpe = listOf("Nitrile gloves", "Overalls", "Boots", "Goggles", "Respirator")
                    ),
                    ChemicalRecommendation(
                        tradeName = "ACTARA 25 WG",
                        pcpbRegNo = "PCPB(CR)0544",
                        formulation = "25 WG (Water Dispersible Granules)",
                        activeIngredient = "Thiamethoxam (250 g/kg)",
                        chemicalGroup = "IRAC Group 4A (Neonicotinoid)",
                        classification = "Systemic Soil Drench & Foliar",
                        targetStage = "Early Vegetative Protection & Sap Feeders",
                        dosagePerKnapsack = "10g in 20L knapsack",
                        dosagePerHectare = "200g/ha",
                        phiDays = 7,
                        reiHours = 24,
                        applicationTiming = "Nursery drench or early field spray.",
                        resistanceManagement = "Do not apply consecutively; rotate with diamides.",
                        safetyPpe = listOf("Gloves", "Safety glasses", "Mask")
                    )
                ),
                economicThreshold = "3–4 moths per trap per week or 1 mined leaf per 10 plants sampled triggers intervention."
            ),
            scoutChecklist = listOf(
                "Inspect uppermost fully expanded leaves for tiny translucent blister mines.",
                "Check undersides of tomato calyxes for entrance holes and frass pellets.",
                "Count male moths caught in pheromone water traps twice a week.",
                "Examine stem forks for tunnel collapse."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Tutasan Pheromone Water Trap (Infonet Standard)",
                    category = "Biotechnical Mass Trapping",
                    description = "Infonet organic mass trapping method using female sex pheromone lure and water with cooking oil.",
                    recipeOrMethod = "Place a red plastic basin filled with water + 2 tbsp cooking oil on a raised stand at 30cm above crop canopy. Suspend a Tuta pheromone septa 5cm above the water surface. Empty dead moths weekly."
                ),
                InfonetPractice(
                    title = "Wild Nightshade Weed Eradication",
                    category = "Sanitation & Host Break",
                    description = "Infonet sanitation protocol: Eradicate reservoir weed hosts around tomato production fields.",
                    recipeOrMethod = "Rogue out all Black nightshade (Solanum nigrum), Datura stramonium, and sodom apple within 50 meters of tomato greenhouses and field plots."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Tutasan Pheromone Trap",
                    activeAgent = "(3E,8Z,11Z)-tetradecatrien-1-yl acetate (Tuta lure)",
                    type = BioProtectionType.SEMIOCHEMICAL,
                    targetOrganism = "Tuta absoluta (Adult Male Moths)",
                    targetCrops = listOf("Tomato", "Potato", "Eggplant"),
                    modeOfAction = "Sex pheromone lure attracts male moths into water trap, disrupting mating and crashing population reproduction.",
                    applicationRate = "Monitoring: 4–6 traps/ha; Mass trapping: 20–40 traps/ha.",
                    safetyMrl = "Non-toxic semiochemical; zero MRL; completely organic compliant.",
                    supplierOrDistributor = "Koppert / CABI BioProtection Portal"
                ),
                CabiBioProduct(
                    tradeName = "Nesidiocoris tenuis (Nesiline)",
                    activeAgent = "Nesidiocoris tenuis (Mirid predatory bug)",
                    type = BioProtectionType.MACROBIAL_PREDATOR,
                    targetOrganism = "Tuta absoluta eggs, Whitefly, Spider mites",
                    targetCrops = listOf("Greenhouse Tomatoes"),
                    modeOfAction = "Predatory bug pierces and sucks dry Tuta eggs and young larvae.",
                    applicationRate = "1–2 bugs/m² released in greenhouse crop during vegetative stage.",
                    safetyMrl = "Natural biological control agent; zero chemical residues.",
                    supplierOrDistributor = "Bioline AgroSciences / CABI Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Voliam Targo 063 SC",
                    activeIngredient = "Chlorantraniliprole 45g/L + Abamectin 18g/L",
                    pcpbRegNo = "PCPB(CR)1034",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Tomatoes", "Vegetables"),
                    fracIracGroup = "IRAC Group 28 + IRAC Group 6",
                    knapsackDosage = "12ml per 20L knapsack",
                    perHaDosage = "0.6 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Rapid paralytic action stops larval mining within 2 hours; rotate with IRAC 5 (Radiant)."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Coragen 20 SC",
                    activeIngredient = "Chlorantraniliprole 200g/L",
                    pcpbRegNo = "PCPB(CR)0899",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Tomatoes", "Potatoes"),
                    fracIracGroup = "IRAC Group 28",
                    knapsackDosage = "5ml - 7.5ml per 20L knapsack",
                    perHaDosage = "0.15 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "FMC Agricultural Solutions",
                    lastingIpmRule = "Lasting solution: Excellent translaminar movement into leaf mesophyll where larvae feed."
                )
            )
        ),

        // =========================================================================
        // 8. EARLY BLIGHT (Alternaria solani) - VEGETABLES & TUBERS
        // Sources: Infonet-Biovision, PCPB Kenya, CABI BioProtection Portal
        // =========================================================================
        CropThreat(
            id = "early_blight",
            name = "Early Blight",
            scientificName = "Alternaria solani",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Tomato", "Potato", "Eggplant", "Pepper"),
            affectedParts = listOf("Leaves", "Stem", "Fruit", "Tuber"),
            severityLevel = "High",
            summary = "Widespread fungal disease causing concentric target-board leaf spots, premature lower canopy defoliation, collar rot on seedlings, and dark sunken leathery rot on tomato fruits.",
            diagnosticKeys = listOf(
                "Distinct circular dark brown spots with concentric ring ridges resembling a bullseye target.",
                "Spots surrounded by a bright yellow chlorotic halo.",
                "Disease begins on older bottom leaves and progressively moves up the canopy.",
                "Dark sunken leathery rot lesions at the stem-end of tomato fruit with concentric rings."
            ),
            favorableConditions = "Warm humid weather (24°C–29°C) with alternating wet periods (dew, rain) and sunny dry winds favoring spore release.",
            imageResId = R.drawable.sample_early_blight,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Overwintering Conidia & Inoculum",
                    scientificSub = "Beaked Conidiospore Inoculum",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 4 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Fungus overwinters in crop debris and infected Solanaceous weeds. Conidia are splashed by rain onto lower leaves.",
                    symptomsToSpot = "No visible damage yet; spores resting on foliage near ground level.",
                    targetAction = "Apply mulch barrier and copper/mancozeb protectant.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Copper Oxychloride 500 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Direct Penetration & Incubation",
                    scientificSub = "Germ Tube Cuticle Entry",
                    iconType = StageIconType.PENETRATION,
                    duration = "6 – 12 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Spore germinates in film of moisture and penetrates leaf cuticle directly or through wounds and stomata.",
                    symptomsToSpot = "Tiny brown pinhead flecks on lower leaves.",
                    targetAction = "Apply systemic penetrant triazole or strobilurin.",
                    recommendedActiveIngredients = listOf("Difenoconazole 250 g/L", "Azoxystrobin 250 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Concentric Bullseye Lesion Formation",
                    scientificSub = "Alternaric Acid Secretion",
                    iconType = StageIconType.LESION,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Fungus secretes alternaric acid toxin, killing host cells. Distinct concentric dark rings form as lesion expands.",
                    symptomsToSpot = "Classic concentric circular target spots surrounded by bright yellow halo.",
                    targetAction = "Prune lower diseased foliage; spray curative triazole + protectant.",
                    recommendedActiveIngredients = listOf("Score 250 EC", "Nativo 300 SC")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Defoliation & Sunscald",
                    scientificSub = "Canopy Collapse & Fruit Rot",
                    iconType = StageIconType.DISPERSAL,
                    duration = "Continuous under alternating weather",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Heavily spotted leaves wither and drop, exposing developing tomato fruit to sunscald and fruit infection.",
                    symptomsToSpot = "Extensive lower canopy defoliation; sunken dark leathery rot on fruit shoulders.",
                    targetAction = "Maintain spray schedule; salvage marketable fruit.",
                    recommendedActiveIngredients = listOf("Trifloxystrobin + Tebuconazole", "Chlorothalonil")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Apply thick organic straw mulch (5–10 cm) to prevent soil rain-splash onto lower leaves.",
                    "Prune off lower leaves up to 30 cm from the ground after first flower cluster forms.",
                    "Ensure adequate nitrogen and potassium nutrition; stressed and senescing plants are much more susceptible.",
                    "Rotate crops for at least 2–3 years away from Solanaceae."
                ),
                physicalMechanicalControls = listOf(
                    "Drip irrigation only; avoid wetting the canopy with sprinkler or hose watering.",
                    "Prune and destroy infected lower leaves at weekly scouting."
                ),
                biologicalControls = listOf(
                    "Trichoderma harzianum foliar colonization.",
                    "Bacillus subtilis bio-fungicide preventative sprays.",
                    "Compost tea foliar sprays providing antagonistic microflora."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Score 250 EC",
                        pcpbRegNo = "PCPB(CR)0322",
                        formulation = "250 EC (Emulsifiable Concentrate)",
                        activeIngredient = "Difenoconazole (250 g/L)",
                        chemicalGroup = "FRAC Group 3 (DMI Triazole)",
                        classification = "Systemic Curative & Protective",
                        targetStage = "Germ Tubes & Expanding Lesions (Stages 2 & 3)",
                        dosagePerKnapsack = "10ml in 20L knapsack (0.5ml/L water)",
                        dosagePerHectare = "0.5 L/ha in 500L water",
                        phiDays = 7,
                        reiHours = 12,
                        applicationTiming = "At first observation of concentric target spots on bottom leaves.",
                        resistanceManagement = "Maximum 3 applications per season. Alternate with FRAC 11 (Ortiva Top) or FRAC M03 (Mancozeb).",
                        safetyPpe = listOf("Nitrile gloves", "Overalls", "Boots", "Goggles")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Nativo 300 SC",
                        pcpbRegNo = "PCPB(CR)0968",
                        formulation = "300 SC",
                        activeIngredient = "Trifloxystrobin (100 g/L) + Tebuconazole (200 g/L)",
                        chemicalGroup = "FRAC Group 11 (Strobilurin) + FRAC Group 3 (Triazole)",
                        classification = "Mesostemic & Systemic Curative",
                        targetStage = "Target Spots & Fruit Rot Prevention (Stages 2-4)",
                        dosagePerKnapsack = "16ml in 20L knapsack (0.8ml/L water)",
                        dosagePerHectare = "0.8 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply during high risk warm humid weather.",
                        resistanceManagement = "Rotate after maximum 2 sprays.",
                        safetyPpe = listOf("Gloves", "Safety goggles", "Apron")
                    )
                ),
                economicThreshold = "5% lower canopy foliage showing concentric spots warrants curative treatment."
            ),
            scoutChecklist = listOf(
                "Inspect the lowest 3 leaf branches closest to the soil surface.",
                "Look for concentric rings and surrounding yellow chlorotic halos.",
                "Check tomato fruit stems and shoulders for dark sunken leathery patches.",
                "Verify whether soil mulch is intact to prevent splash."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Aerated Compost Tea (Infonet Organic Standard)",
                    category = "Biological Microflora Suppression",
                    description = "Infonet organic foliar spray containing diverse beneficial bacteria that outcompete Alternaria conidia on leaf surfaces.",
                    recipeOrMethod = "Steep 1 kg well-aged active compost in 10L aerated water with 1 tbsp molasses for 24 hours. Strain and spray un-diluted onto foliage weekly."
                ),
                InfonetPractice(
                    title = "Straw Mulch Splash Barrier",
                    category = "Physical Cultural Prevention",
                    description = "Infonet rain-splash prevention technique breaking spore transfer from soil to lower leaves.",
                    recipeOrMethod = "Apply a 10cm layer of dry cereal straw or dry grass mulch beneath tomato plants immediately after staking. Keeps leaves dry and stops soil splash."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Eco-T",
                    activeAgent = "Trichoderma asperellum (2x10^9 spores/g)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Alternaria solani (Early Blight), Fusarium wilt",
                    targetCrops = listOf("Tomato", "Potato", "Vegetables"),
                    modeOfAction = "Colonizes root and leaf rhizosphere, producing chitinases that dissolve Alternaria fungal cell walls.",
                    applicationRate = "10g in 20L knapsack.",
                    safetyMrl = "Zero MRL; Certified Organic.",
                    supplierOrDistributor = "Plant Health Products / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Score 250 EC",
                    activeIngredient = "Difenoconazole 250g/L",
                    pcpbRegNo = "PCPB(CR)0322",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Tomato", "Potato", "Pepper"),
                    fracIracGroup = "FRAC Group 3 (Triazole)",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 7,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Acropetal movement stops lesion expansion; rotate with multi-site contact protectants."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Nativo 300 SC",
                    activeIngredient = "Trifloxystrobin 100g/L + Tebuconazole 200g/L",
                    pcpbRegNo = "PCPB(CR)0968",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Tomatoes", "Vegetables", "Potatoes"),
                    fracIracGroup = "FRAC Group 11 + FRAC Group 3",
                    knapsackDosage = "16ml per 20L knapsack",
                    perHaDosage = "0.8 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Dual systemic and translaminar activity; maximum 2 applications per season."
                )
            )
        ),

        // =========================================================================
        // 9. BACTERIAL WILT (Ralstonia solanacearum) - VEGETABLES & TUBERS
        // Sources: Infonet-Biovision, PCPB Kenya
        // =========================================================================
        CropThreat(
            id = "bacterial_wilt",
            name = "Bacterial Wilt",
            scientificName = "Ralstonia solanacearum",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Potato", "Tomato", "Pepper", "Eggplant", "Banana"),
            affectedParts = listOf("Stem", "Roots", "Leaves", "Tuber"),
            severityLevel = "Extreme",
            summary = "Lethal soil-borne vascular pathogen. Plants wilt rapidly while leaves remain completely green. Stems show brown vascular ring discoloration, and freshly cut stems exude milky white bacterial streaming in water.",
            diagnosticKeys = listOf(
                "Rapid green wilting: entire plant wilts during the heat of the day without prior yellowing.",
                "Brown vascular ring discoloration visible when the lower stem is sliced horizontally.",
                "Positive glass-of-water test: cut stem held in clear water discharges milky white bacterial ooze threads within 2 minutes.",
                "Potato tubers exude bacterial slime from eye buds; soil clings to weeping eyes."
            ),
            favorableConditions = "High soil temperatures (28°C–35°C), high soil moisture, acidic soils, presence of root-knot nematodes causing root wounds.",
            imageResId = R.drawable.img_bacterial_wilt,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Soil Survival & Root Attachment",
                    scientificSub = "Chemotactic Root Surface Colonization",
                    iconType = StageIconType.BACTERIA_SURVIVAL,
                    duration = "1 – 3 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Ralstonia bacteria persist in soil debris; attracted to root exudates and adhere to outer epidermis.",
                    symptomsToSpot = "No visible foliar symptoms yet; root hairs colonized.",
                    targetAction = "Plant certified clean seed; drench with bio-antagonistic bacteria.",
                    recommendedActiveIngredients = listOf("Pseudomonas fluorescens", "Copper Hydroxide 538 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Wound & Hydathode Ingress",
                    scientificSub = "Bacterial Ingress via Root Fractures",
                    iconType = StageIconType.BACTERIA_ENTRY,
                    duration = "12 – 24 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Bacteria enter through transplanting root tears, nematode feeding wounds, or natural root emergence points.",
                    symptomsToSpot = "Early localized root cortex browning.",
                    targetAction = "Prevent nematode damage and root injury; drench transplant holes with copper.",
                    recommendedActiveIngredients = listOf("Copper Hydroxide", "Paecilomyces lilacinus")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Vascular Colonization & EPS Slime",
                    scientificSub = "Xylem Biofilm Clogging",
                    iconType = StageIconType.VASCULAR_COLONIZATION,
                    duration = "24 – 72 hours",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Bacteria multiply rapidly in xylem vessels and produce thick extracellular polysaccharide slime (EPS) that blocks water transport.",
                    symptomsToSpot = "Top youngest leaves show mild wilting during hot midday hours, recovering at night.",
                    targetAction = "Isolate infected plants; rogue out immediately with root ball.",
                    recommendedActiveIngredients = listOf("Copper Oxychloride", "Bio-fumigation")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Irreversible Green Wilt & Streaming",
                    scientificSub = "Total Vascular Occlusion & Ooze",
                    iconType = StageIconType.SYSTEMIC_COLLAPSE,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Water conduction is completely obstructed. The entire canopy collapses rapidly while remaining green.",
                    symptomsToSpot = "Entire plant permanently flaccid and collapsed while green; milky bacterial streaming ooze in clean water.",
                    targetAction = "Rogue plant and root soil; pour hydrated lime or copper drench into planting hole.",
                    recommendedActiveIngredients = listOf("Hydrated Lime Drench", "Copper Hydroxide")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Bio-fumigation: Incorporate Brassica green manure (mustard or radish) into soil 4 weeks before planting to release bio-fumigant isothiocyanates.",
                    "Practice 5-year crop rotation with non-hosts (maize, sorghum, millet, Rhodes grass); never rotate Solanaceae with Solanaceae.",
                    "Plant certified disease-free potato tubers and certified disease-free grafted tomato seedlings on resistant rootstocks.",
                    "Disinfect farm tools and boots in 10% household bleach or 2% copper sulfate before moving between fields."
                ),
                physicalMechanicalControls = listOf(
                    "Carefully dig out infected plants along with surrounding root soil; place in sealed bags and burn away from field.",
                    "Solarize nursery beds under clear plastic sheeting for 8 weeks during hottest dry months."
                ),
                biologicalControls = listOf(
                    "Pseudomonas fluorescens bio-antagonist soil drench.",
                    "Trichoderma asperellum beneficial root colonization.",
                    "Bacillus subtilis biological bactericide."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Kocide 3000",
                        pcpbRegNo = "PCPB(CR)0781",
                        formulation = "461 g/kg WG",
                        activeIngredient = "Copper Hydroxide (461 g/kg equivalent to 300g metallic copper)",
                        chemicalGroup = "FRAC Group M01 (Inorganic Multi-site Copper)",
                        classification = "Protective Bactericide & Fungicide",
                        targetStage = "Soil Surface & Root Collar Barrier (Stage 1)",
                        dosagePerKnapsack = "40g - 50g in 20L knapsack (2.0g - 2.5g/L water)",
                        dosagePerHectare = "2.0 kg/ha",
                        phiDays = 3,
                        reiHours = 24,
                        applicationTiming = "Soil collar drench at transplanting to suppress bacterial transmission.",
                        resistanceManagement = "Multi-site contact activity; no resistance development known.",
                        safetyPpe = listOf("Nitrile gloves", "Dust mask", "Goggles", "Coveralls")
                    )
                ),
                economicThreshold = "Zero tolerance: 1 wilted plant requires immediate roguing and spot soil quarantine."
            ),
            scoutChecklist = listOf(
                "Perform glass-of-water test: cut lower stem, submerge in clean water, and observe milky streaming.",
                "Slice lower stem crosswise to check for brown ring in vascular bundles.",
                "Check potato tubers around eyes for dark staining and oozing bacterial slime.",
                "Inspect field for localized circular wilt patches in poorly drained areas."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Bio-Fumigation with Indian Mustard (Infonet Standard)",
                    category = "Soil Health & Bio-Fumigation",
                    description = "Infonet organic soil sanitation method using high-glucosinolate Brassica plants to sterilize soil-borne Ralstonia bacteria.",
                    recipeOrMethod = "Sow Indian mustard (Brassica juncea) thickly. At flowering (6–8 weeks), chop finely with a flail mower and immediately incorporate into moist soil. Seal soil surface with plastic or heavy rolling for 3 weeks."
                ),
                InfonetPractice(
                    title = "Stem Ooze Diagnostic Glass Test",
                    category = "Field Diagnostic Verification",
                    description = "Infonet definitive field test distinguishing bacterial wilt from fungal Fusarium/Verticillium wilt.",
                    recipeOrMethod = "Cut a 3cm section of stem near the soil line. Suspend cut stem in a clear glass of clean water. If Ralstonia is present, fine milky-white bacterial streaming threads will emerge like smoke within 2–5 minutes."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Real Trichoderma",
                    activeAgent = "Trichoderma asperellum",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Soil pathogens (Ralstonia, Fusarium, Pythium)",
                    targetCrops = listOf("Potato", "Tomato", "Vegetables"),
                    modeOfAction = "Vigorous colonization of root surfaces, producing antibiotic enzymes and outcompeting bacterial entry sites.",
                    applicationRate = "20g in 20L knapsack; drench into root zone at planting.",
                    safetyMrl = "Zero residue; Certified Organic.",
                    supplierOrDistributor = "Real IPM Kenya / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Kocide 3000",
                    activeIngredient = "Copper Hydroxide 461g/kg",
                    pcpbRegNo = "PCPB(CR)0781",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("Potatoes", "Tomatoes", "Vegetables"),
                    fracIracGroup = "FRAC Group M01 (Inorganic Copper)",
                    knapsackDosage = "40g - 50g per 20L knapsack",
                    perHaDosage = "2.0 kg/ha",
                    phiDays = 3,
                    reiHours = 24,
                    registrant = "Corteva Agriscience East Africa",
                    lastingIpmRule = "Lasting solution: Multi-site bactericide; prevents wound colonization when drenched into transplant root zone."
                )
            )
        ),

        // =========================================================================
        // 10. ROSE & VEGETABLE APHIDS (Macrosiphum rosae / Aphis gossypii)
        // Sources: CABI BioProtection Portal, PCPB Kenya, Infonet-Biovision
        // =========================================================================
        CropThreat(
            id = "aphids",
            name = "Cotton / Melon & Rose Aphids",
            scientificName = "Aphis gossypii / Macrosiphum rosae",
            type = ThreatType.PEST,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Vegetables", "Tomato", "Cotton", "Citrus"),
            affectedParts = listOf("Leaves", "Shoot Tips", "Flower Buds"),
            severityLevel = "High",
            summary = "Prolific piercing-sucking insect colonies clustering on tender shoot tips, flower buds, and leaf undersides. Excrete sticky honeydew that colonizes black sooty mold and vectors destructive plant viruses.",
            diagnosticKeys = listOf(
                "Dense colonies of green, pink, or black pear-shaped soft-bodied insects on growing tips and bud stems.",
                "Curling, puckering, and downward cupping of tender young leaves.",
                "Shiny, sticky honeydew coating foliage and stems, followed by velvety black sooty mold fungus.",
                "Presence of white cast skins (moults) scattered across leaf undersides; ants tending the colonies."
            ),
            favorableConditions = "Moderate temperatures (18°C–25°C) with dry weather; high nitrogen fertilizer application causing lush shoot flushes.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Nymphs & Parthenogenetic Reproduction",
                    scientificSub = "Asexual Viviparous Birth",
                    iconType = StageIconType.LARVA,
                    duration = "4 – 7 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Females give birth directly to live young nymphs without mating, multiplying from 1 aphid to thousands in 2 weeks.",
                    symptomsToSpot = "Clusters of tiny green/pink nymphs crowded along rose bud pedicels.",
                    targetAction = "Apply contact insecticidal soap, neem extract, or systemic neonicotinoid.",
                    recommendedActiveIngredients = listOf("Confidor 200 SL", "Actara 25 WG", "Potassium Salts of Fatty Acids")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Wingless Adults & Honeydew Secretion",
                    scientificSub = "Apterous Phloem Feeding",
                    iconType = StageIconType.ADULT,
                    duration = "15 – 25 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Adult aphids pump phloem sap continuously, secreting excess sugar as honeydew that coats leaves.",
                    symptomsToSpot = "Black sooty mold covering lower leaves; distorted buds.",
                    targetAction = "Release ladybird beetles (Coccinellids) or spray systemic flonicamid/spirotetramat.",
                    recommendedActiveIngredients = listOf("Movento 240 SC", "Teppeki")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Winged Dispersal Form (Alates)",
                    scientificSub = "Alate Flight & Virus Vectoring",
                    iconType = StageIconType.DISPERSAL,
                    duration = "7 – 14 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "When colonies crowd, winged aphids develop and take flight on wind currents to colonize clean crops and transmit viruses.",
                    symptomsToSpot = "Winged aphids caught on yellow sticky cards; solitary aphids landing on young flushes.",
                    targetAction = "Yellow sticky cards for monitoring and mass trapping; barrier sprays.",
                    recommendedActiveIngredients = listOf("Yellow Sticky Cards", "Pyrethrins")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Avoid over-fertilization with soluble synthetic nitrogen, which triggers lush shoot growth that fuels explosive aphid reproduction.",
                    "Hang yellow sticky cards (1 trap per 100m²) at canopy height to capture winged alate aphids.",
                    "Conserve field border plants (fennel, dill, coriander) to provide nectar for adult hoverflies and parasitic wasps.",
                    "Wash shoot tips with high-pressure water stream to physically dislodge and drown wingless aphids."
                ),
                physicalMechanicalControls = listOf(
                    "Pinch out heavily infested shoot tips and dispose in closed bags.",
                    "Apply insect exclusion netting on greenhouse side vents."
                ),
                biologicalControls = listOf(
                    "Ladybird beetles (Cheilomenes lunata / Coccinella): Voracious predators consuming 50 aphids per day.",
                    "Aphidius colemani / Aphidius ervi parasitoid wasps: Lay eggs inside aphids, swelling them into golden-brown 'aphid mummies'.",
                    "Hoverfly larvae (Syrphidae) and Green lacewing larvae (Chrysoperla carnea).",
                    "Lecanicillium lecanii / Beauveria bassiana entomopathogenic fungi."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Confidor 200 SL",
                        pcpbRegNo = "PCPB(CR)0352",
                        formulation = "200 SL (Soluble Liquid)",
                        activeIngredient = "Imidacloprid (200 g/L)",
                        chemicalGroup = "IRAC Group 4A (Neonicotinoid)",
                        classification = "Systemic Protective & Curative",
                        targetStage = "All Feeding Nymphs & Adults (Stages 1-3)",
                        dosagePerKnapsack = "10ml in 20L knapsack (0.5ml/L water)",
                        dosagePerHectare = "0.25 - 0.3 L/ha in 500L water",
                        phiDays = 7,
                        reiHours = 12,
                        applicationTiming = "At first colony clustering on shoot tips.",
                        resistanceManagement = "Maximum 2 applications per season. Strictly rotate with non-neonicotinoids (e.g. Movento or Actara).",
                        safetyPpe = listOf("Nitrile gloves", "Overalls", "Boots", "Eye protection")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Movento 240 SC",
                        pcpbRegNo = "PCPB(CR)1002",
                        formulation = "240 SC (Suspension Concentrate)",
                        activeIngredient = "Spirotetramat (240 g/L)",
                        chemicalGroup = "IRAC Group 23 (Tetramic acid - Lipid biosynthesis inhibitor)",
                        classification = "2-Way Systemic (Xylem & Phloem Translocation)",
                        targetStage = "Immature Nymphs & Juvenile Stages (Stage 1)",
                        dosagePerKnapsack = "15ml in 20L knapsack (0.75ml/L water)",
                        dosagePerHectare = "0.5 L/ha in 1000L water",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply to active vegetative growth; unique 2-way systemicity protects hidden inner canopy.",
                        resistanceManagement = "Rotate after 2 sprays; completely different IRAC group from neonicotinoids.",
                        safetyPpe = listOf("Nitrile gloves", "Goggles", "Coveralls")
                    )
                ),
                economicThreshold = "In cut flowers: 1 colony per 25 stems sampled warrants spot treatment. In vegetables: 20% of shoots infested."
            ),
            scoutChecklist = listOf(
                "Inspect the underside of youngest leaves and flower bud pedicels.",
                "Look for shiny sticky honeydew droplets on lower leaves below shoot tips.",
                "Check for presence of parasitized bloated golden-brown 'aphid mummies'.",
                "Count winged aphids on yellow sticky traps weekly."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Soap & Wood Ash Foliar Dusting (Infonet Standard)",
                    category = "Botanical & Mineral Desiccant",
                    description = "Infonet smallholder organic control combining alkaline soap suffocation with abrasive wood ash.",
                    recipeOrMethod = "Dissolve 30g pure potassium soap in 10L clean water. Spray heavily over aphid clusters. Dust fine sieved hardwood ash over wet foliage 10 minutes later to desiccate survivors."
                ),
                InfonetPractice(
                    title = "Hot Pepper & Garlic Spray",
                    category = "Natural Botanical Repellent",
                    description = "Infonet botanical contact wash causing aphid dehydration and repelling winged alates.",
                    recipeOrMethod = "Blend 100g hot chili peppers with 50g garlic in 1L water. Strain and dilute into 10L water with 1 tbsp vegetable oil and soap. Spray directly onto aphid colonies."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Aphipar",
                    activeAgent = "Aphidius colemani (Parasitoid Wasp)",
                    type = BioProtectionType.MACROBIAL_PREDATOR,
                    targetOrganism = "Aphis gossypii, Myzus persicae (Aphids)",
                    targetCrops = listOf("Cut Roses", "Carnations", "Greenhouse Vegetables"),
                    modeOfAction = "Female wasp deposits egg inside aphid body; developing larva consumes aphid from within, creating a golden-brown mummy.",
                    applicationRate = "0.5 to 2 wasps/m² released in greenhouse bays.",
                    safetyMrl = "Beneficial insect; zero chemical residues.",
                    supplierOrDistributor = "Koppert Biological Systems / CABI Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Confidor 200 SL",
                    activeIngredient = "Imidacloprid 200g/L",
                    pcpbRegNo = "PCPB(CR)0352",
                    formulation = "Soluble Liquid (SL)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Vegetables", "Horticulture"),
                    fracIracGroup = "IRAC Group 4A (Neonicotinoid)",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "0.25 - 0.3 L/ha",
                    phiDays = 7,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Rapid systemic uptake; rotate with IRAC 23 (Movento) to preserve neonicotinoid sensitivity."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Movento 240 SC",
                    activeIngredient = "Spirotetramat 240g/L",
                    pcpbRegNo = "PCPB(CR)1002",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Vegetables"),
                    fracIracGroup = "IRAC Group 23",
                    knapsackDosage = "15ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Unique 2-way systemicity travels upward in xylem and downward in phloem to reach hidden shoots."
                )
            )
        ),

        // =========================================================================
        // 11. ROSE & FLOWER BOTRYTIS (Botrytis cinerea) - CUT FLOWERS & FRUITS
        // Sources: CABI BioProtection Portal, PCPB Kenya Floriculture Registrations
        // =========================================================================
        CropThreat(
            id = "botrytis_blight",
            name = "Botrytis Blight / Grey Mold",
            scientificName = "Botrytis cinerea",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Lilies", "Gypsophila", "Strawberries", "Tomato"),
            affectedParts = listOf("Flower Buds", "Petals", "Stem Cuttings", "Leaves"),
            severityLevel = "Extreme",
            summary = "Crucial post-harvest and pre-harvest fungal disease of cut flowers. Infects outer petals with small reddish-brown water-soaked specks ('ghost spots') that explode into dense velvety grey mold during cold chain export shipping.",
            diagnosticKeys = listOf(
                "Pinhead-sized translucent water-soaked spots with pinkish or red halos on flower petals.",
                "Dense velvety mouse-grey mold covering collapsing flower buds and dying petals.",
                "Brown, soft water-soaked stem cankers at cutting wounds.",
                "Flower heads turn soft, brown, and rot completely in packing boxes."
            ),
            favorableConditions = "Cool to moderate temperatures (15°C–20°C) with persistent free water on petals or relative humidity above 90% for >6 hours (dew, ceiling drip, postharvest condensation).",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Conidia Inoculum on Petal Moisture",
                    scientificSub = "Macroconidia Germination",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 4 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Ubiquitous grey mold spores land on petals and germinate in microscopic water droplets.",
                    symptomsToSpot = "Minute translucent flecks on outer petals under dew.",
                    targetAction = "Apply preventative bio-protectant or anti-botrytis spray before harvest.",
                    recommendedActiveIngredients = listOf("Fludioxonil + Cyprodinil", "Fenhexamid 500 g/kg", "Trichoderma harzianum")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Enzymatic Maceration & Petal Speckling",
                    scientificSub = "Pectinase Tissue Breakdown",
                    iconType = StageIconType.PENETRATION,
                    duration = "6 – 12 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Fungus secretes pectinases that dissolve petal cell walls, creating distinct 'ghost spots'.",
                    symptomsToSpot = "Small brown/red specks on rose petals; outer petal collapse.",
                    targetAction = "Strip outer guard petals; dip flower heads in registered postharvest solution.",
                    recommendedActiveIngredients = listOf("Switch 62.5 WG", "Teldor 500 SC")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Grey Velvet Sporulation in Cold Chain",
                    scientificSub = "Conidiophore Eruption & Transit Rot",
                    iconType = StageIconType.DISPERSAL,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "In transit cold rooms (2°C–4°C), fungus erupts into thick grey felt, destroying packed bouquets.",
                    symptomsToSpot = "Dense grey fluffy mold over blooms inside export shipping cartons.",
                    targetAction = "Strict cold chain temperature control and pre-cooling air purge.",
                    recommendedActiveIngredients = listOf("Pre-cooling Purge", "Postharvest Fungicide Dip")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Avoid overhead ceiling condensation drips onto rose and carnation flower beds.",
                    "Pre-cool harvested flower stems quickly to 2°C–4°C with forced air to remove all field heat and surface moisture.",
                    "Disinfect cold store holding buckets and grading tables daily with chlorine dioxide or quaternary ammonium sanitizers.",
                    "Harvest flowers at correct bud opening stage; avoid harvesting wet flowers."
                ),
                physicalMechanicalControls = listOf(
                    "Remove and destroy all dead leaves, fallen petals, and diseased flower heads from greenhouse beds daily.",
                    "Strip outer damaged guard petals during packing table inspection."
                ),
                biologicalControls = listOf(
                    "Trichoderma harzianum (Trianum-P) applied as protective flower wash.",
                    "Clonostachys rosea bio-antagonistic fungus.",
                    "Chitosan bio-polymer coating that forms an invisible anti-fungal barrier on petals."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Switch 62.5 WG",
                        pcpbRegNo = "PCPB(CR)0543",
                        formulation = "62.5 WG (Water Dispersible Granules)",
                        activeIngredient = "Fludioxonil (250 g/kg) + Cyprodinil (375 g/kg)",
                        chemicalGroup = "FRAC Group 12 (Phenylpyrroles) + FRAC Group 9 (Anilinopyrimidines)",
                        classification = "Translaminar Contact & Ingestion Botryticide",
                        targetStage = "Petal Spores & Latent Infection (Stages 1 & 2)",
                        dosagePerKnapsack = "16g - 20g in 20L knapsack (0.8g - 1.0g/L water)",
                        dosagePerHectare = "0.8 - 1.0 kg/ha in 1000L water",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply 2–3 days prior to peak harvest flushes during humid weather.",
                        resistanceManagement = "Maximum 2 applications per flush. Rotate with FRAC 17 (Teldor) to avoid anilinopyrimidine resistance.",
                        safetyPpe = listOf("Nitrile gloves", "Face mask", "Coveralls", "Boots")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Teldor 500 SC",
                        pcpbRegNo = "PCPB(CR)0719",
                        formulation = "500 SC",
                        activeIngredient = "Fenhexamid (500 g/L)",
                        chemicalGroup = "FRAC Group 17 (Hydroxyanilides)",
                        classification = "Protective Contact Botryticide",
                        targetStage = "Spore Germination & Germ Tube Elongation (Stage 1)",
                        dosagePerKnapsack = "20ml in 20L knapsack (1.0ml/L water)",
                        dosagePerHectare = "1.0 L/ha",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Preventative spray on open flower buds.",
                        resistanceManagement = "Strictly rotate with Switch or Scala; no cross-resistance with FRAC 9/12.",
                        safetyPpe = listOf("Gloves", "Goggles", "Coveralls")
                    )
                ),
                economicThreshold = "Export flower standard is strictly ZERO tolerance for Botrytis petal speckling."
            ),
            scoutChecklist = listOf(
                "Inspect outer guard petals of developing buds for pinhead reddish-brown flecks.",
                "Check flower stems for brown stem cankers near pruning cuts.",
                "Inspect cold room export boxes after 24 hours of pre-cooling.",
                "Monitor relative humidity inside greenhouse canopies before sunrise."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Greenhouse Postharvest Sanitize Protocol",
                    category = "Sanitation & Cold Chain Hygiene",
                    description = "Infonet sanitation method for preventing grey mold spread in cut flower packhouses.",
                    recipeOrMethod = "Wash all harvest buckets with 100 ppm sodium hypochlorite (bleach) solution. Dip cut stem bases in clean solution containing 0.1% citric acid to lower pH."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Trianum-P",
                    activeAgent = "Trichoderma harzianum strain T-22",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Botrytis cinerea (Grey Mold)",
                    targetCrops = listOf("Cut Roses", "Carnations", "Lilies"),
                    modeOfAction = "Outcompetes Botrytis conidia for nutrients on petal surfaces and secretes glucanases that lyse germ tubes.",
                    applicationRate = "15g per 20L knapsack.",
                    safetyMrl = "Zero residue; leaves no stains on flower petals.",
                    supplierOrDistributor = "Koppert / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Switch 62.5 WG",
                    activeIngredient = "Fludioxonil 250g/kg + Cyprodinil 375g/kg",
                    pcpbRegNo = "PCPB(CR)0543",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Strawberries"),
                    fracIracGroup = "FRAC Group 12 + FRAC Group 9",
                    knapsackDosage = "16g - 20g per 20L knapsack",
                    perHaDosage = "0.8 - 1.0 kg/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Gold standard botryticide with translaminar gas phase; rotate with Fenhexamid (Teldor)."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Teldor 500 SC",
                    activeIngredient = "Fenhexamid 500g/L",
                    pcpbRegNo = "PCPB(CR)0719",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Roses (Cut Flowers)", "Carnations", "Ornamentals"),
                    fracIracGroup = "FRAC Group 17",
                    knapsackDosage = "20ml per 20L knapsack",
                    perHaDosage = "1.0 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Zero residue marking on petals; excellent resistance rotation partner."
                )
            )
        )
    )

    val allThreats: List<CropThreat> = baseThreats + ExpandedThreats.threats + DiverseCropThreats.threats + InclusiveCropThreats.threats + PestWeedSoilThreats.threats

    fun getThreatById(id: String): CropThreat? = allThreats.find { it.id == id }

    fun filterThreats(
        query: String = "",
        crop: String? = null,
        type: ThreatType? = null,
        plantPart: String? = null,
        category: CropCategory? = null
    ): List<CropThreat> {
        val q = query.trim()
        return allThreats.filter { threat ->
            val matchesQuery = q.isBlank() ||
                threat.name.contains(q, ignoreCase = true) ||
                threat.scientificName.contains(q, ignoreCase = true) ||
                threat.summary.contains(q, ignoreCase = true) ||
                threat.targetCrops.any { it.contains(q, ignoreCase = true) } ||
                threat.diagnosticKeys.any { it.contains(q, ignoreCase = true) } ||
                threat.pcpbChemicals.any { it.tradeName.contains(q, true) || it.activeIngredient.contains(q, true) || it.pcpbRegNo.contains(q, true) } ||
                threat.cabiBioProtections.any { it.tradeName.contains(q, true) || it.activeAgent.contains(q, true) } ||
                threat.infonetPractices.any { it.title.contains(q, true) || it.description.contains(q, true) } ||
                threat.ipmPlan.chemicalControls.any { it.activeIngredient.contains(q, true) || it.tradeName.contains(q, true) }

            val matchesCrop = crop == null || crop.isBlank() || crop == "All Crops" ||
                threat.targetCrops.any { it.contains(crop, ignoreCase = true) || crop.contains(it, ignoreCase = true) }

            val matchesType = type == null || threat.type == type

            val matchesPart = plantPart == null || plantPart.isBlank() || plantPart == "All Parts" ||
                threat.affectedParts.any { it.equals(plantPart, ignoreCase = true) }

            val matchesCategory = category == null || category == CropCategory.ALL || threat.cropCategory == category

            matchesQuery && matchesCrop && matchesType && matchesPart && matchesCategory
        }
    }

    val availableCrops: List<String> = listOf(
        "All Crops",
        "Roses (Cut Flowers)",
        "Carnations",
        "Chrysanthemums",
        "Lilies",
        "Lisianthus",
        "French Beans",
        "Common Beans",
        "Cowpeas",
        "Tomato",
        "Potato",
        "Cabbage",
        "Kale",
        "Broccoli",
        "Maize",
        "Wheat",
        "Barley",
        "Sorghum",
        "Rice",
        "Pepper"
    )

    val plantPartsList: List<String> = listOf(
        "All Parts",
        "Leaves",
        "Stem",
        "Fruit",
        "Flowers/Buds",
        "Petals",
        "Whorl",
        "Roots",
        "Tuber"
    )
}
