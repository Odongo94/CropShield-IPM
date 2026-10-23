package com.example.data

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

object InclusiveCropThreats {

    val threats: List<CropThreat> = listOf(
        // =========================================================================
        // 1. CHRYSANTHEMUM WHITE RUST (Puccinia horiana) - FLOWERS & ORNAMENTALS
        // =========================================================================
        CropThreat(
            id = "chrysanthemum_white_rust",
            name = "Chrysanthemum White Rust",
            scientificName = "Puccinia horiana",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Chrysanthemums", "Cut Flowers", "Ornamentals"),
            affectedParts = listOf("Leaves", "Petals", "Stem"),
            severityLevel = "Extreme",
            summary = "Strict quarantine fungal rust in international floriculture. Causes circular pale green to yellow dimpled spots on upper leaf surfaces and prominent pinkish-buff to white waxy pustules on leaf undersides. Entire cut flower export consignments are incinerated if a single infected leaf is detected.",
            diagnosticKeys = listOf(
                "Circular, concave pale green to yellow spots (2mm–5mm) on the upper surface of chrysanthemum leaves.",
                "Prominent, raised, hemispherical waxy pustules on the lower leaf surface turning chalky white when sporulating.",
                "Leaves turn severely distorted, necrotic, and curl downward during heavy infections.",
                "Quarantine status: 100% rejection threshold at European Union (EU) export inspections."
            ),
            favorableConditions = "High humidity (>95% RH) for at least 5 hours, cool to moderate greenhouse temperatures (15°C–20°C), and water condensation on leaves.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Basidiospore Landing & Stomatal Entry",
                    scientificSub = "Primary Basidiospore Inoculation",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 5 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Windblown microscopic basidiospores land on moist chrysanthemum leaves and produce germ tubes that penetrate leaf epidermis directly.",
                    symptomsToSpot = "Leaves appear healthy but carry microscopic surface inoculum on wet foliage.",
                    targetAction = "Apply preventative multi-site protective barrier before misting or night cooling.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Chlorothalonil 720 g/L", "Azoxystrobin 250 g/L")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Intercellular Mycelial Colonization",
                    scientificSub = "Incubation & Haustoria Feeding",
                    iconType = StageIconType.HYPHAE,
                    duration = "7 – 10 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Mycelium invades intercellular spaces and taps nutrients with haustoria. Pale green dimpled chlorotic spots appear on the upper leaf surface.",
                    symptomsToSpot = "Depressed chlorotic pale yellow spots visible under top lighting.",
                    targetAction = "Apply systemic triazole or strobilurin curative fungicide with translaminar movement.",
                    recommendedActiveIngredients = listOf("Difenoconazole 250 g/L", "Tebuconazole 250 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Teliospore Pustule Eruption",
                    scientificSub = "Telia Formation",
                    iconType = StageIconType.LESION,
                    duration = "3 – 5 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Telia rupture through lower leaf epidermis, forming raised waxy pinkish-buff pustules that turn chalky white as they mature.",
                    symptomsToSpot = "Raised white waxy blisters on the underside of leaves; necrotic leaf patches.",
                    targetAction = "Immediately cut and burn infected mother stock; strip bottom leaves and quarantine bay.",
                    recommendedActiveIngredients = listOf("Azoxystrobin + Difenoconazole", "Pyraclostrobin")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Basidiospore Germination & Wind Dispersal",
                    scientificSub = "Secondary Basidiospore Discharge",
                    iconType = StageIconType.DISPERSAL,
                    duration = "12 – 24 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Teliospores germinate in situ without dormancy, producing millions of airborne basidiospores that float across greenhouse bays.",
                    symptomsToSpot = "Chalky white powder dust across adjacent leaves and stems.",
                    targetAction = "Reduce greenhouse humidity below 80% with ventilation; heat pipes at dawn to dry leaves.",
                    recommendedActiveIngredients = listOf("Bordeaux Mixture", "Copper Hydroxide")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Source 100% certified rust-free mother plant cuttings from tested nuclear stock.",
                    "Maintain continuous greenhouse heating and air circulation to prevent leaf wetness and condensation.",
                    "Use drip irrigation exclusively; strictly prohibit overhead irrigation or spraying after 2:00 PM.",
                    "Establish strict quarantine protocols for personnel moving between greenhouse blocks."
                ),
                physicalMechanicalControls = listOf(
                    "Immediately strip and incinerate all leaves showing any chlorotic spots or pustules.",
                    "Place infected cuttings in plastic bags inside the greenhouse before removal to prevent air currents from carrying spores."
                ),
                biologicalControls = listOf(
                    "Bacillus subtilis bio-fungicide preventative foliar spray.",
                    "Trichoderma harzianum root drench to enhance systemic acquired resistance (SAR)."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Amistar Top",
                        pcpbRegNo = "PCPB(CR)0834",
                        formulation = "325 g/L SC",
                        activeIngredient = "Azoxystrobin 200 g/L + Difenoconazole 125 g/L",
                        chemicalGroup = "FRAC Group 11 (QoI) + FRAC Group 3 (DMI Triazole)",
                        classification = "Translaminar & Systemic Preventative / Curative Fungicide",
                        targetStage = "Stage 1 (Spore Inoculum) & Stage 2 (Incubation)",
                        dosagePerKnapsack = "10ml - 12ml in 20L knapsack (0.5ml - 0.6ml/L water)",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply immediately when first chlorotic yellow flecks appear on leaves.",
                        resistanceManagement = "Dual mode-of-action; maximum 2 consecutive applications before rotating to FRAC M03.",
                        safetyPpe = listOf("Nitrile chemical gloves", "Respirator mask", "Safety glasses", "Full protective overall")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Score 250 EC",
                        pcpbRegNo = "PCPB(CR)0356",
                        formulation = "250 g/L EC",
                        activeIngredient = "Difenoconazole 250 g/L",
                        chemicalGroup = "FRAC Group 3 (Demethylation Inhibitor - Triazole)",
                        classification = "Systemic Curative Fungicide with Kickback Action",
                        targetStage = "Stage 2 (Mycelial Incubation)",
                        dosagePerKnapsack = "10ml in 20L knapsack",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 7,
                        reiHours = 24,
                        applicationTiming = "Curative application within 48 hours of infection event.",
                        resistanceManagement = "Rotate with FRAC M01 copper or FRAC M03 dithiocarbamates.",
                        safetyPpe = listOf("Gloves", "Eye goggles", "Coveralls")
                    )
                ),
                economicThreshold = "Zero tolerance: Single pustule detection halts flower export block."
            ),
            scoutChecklist = listOf(
                "Inspect underside of bottom leaves on 50 plants per 500m² twice weekly with 10x hand lens.",
                "Examine mother plant beds under strong backlighting for pale yellow dimpled spots.",
                "Check perimeter plants near greenhouse inlet vents and humid corners."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Greenhouse Microclimate Humidity Management",
                    category = "Cultural Management",
                    description = "Puccinia horiana basidiospores require a continuous film of water for at least 5 hours to germinate. Heating the greenhouse at dawn while opening ridge vents dries leaf surfaces within 20 minutes, breaking the spore germination window completely."
                ),
                InfonetPractice(
                    title = "Certified Nuclear Cutting Selection",
                    category = "Preventative Sanitation",
                    description = "Always source propagation vegetative cuttings from mother blocks tested and certified free from systemic rust mycelium."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Serenade ASO",
                    activeAgent = "Bacillus amyloliquefaciens strain QST 713",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Puccinia horiana",
                    targetCrops = listOf("Chrysanthemums", "Cut Flowers"),
                    modeOfAction = "Colonizes leaf surface and produces lipopeptides that disrupt fungal cell membranes",
                    applicationRate = "2.0 L - 3.0 L/ha",
                    supplierOrDistributor = "Bayer CropScience / Local Distributors"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Amistar Top",
                    activeIngredient = "Azoxystrobin 200 g/L + Difenoconazole 125 g/L",
                    pcpbRegNo = "PCPB(CR)0834",
                    formulation = "325 g/L SC",
                    targetCrops = listOf("Cut Flowers", "Carnations", "Chrysanthemums", "Roses"),
                    fracIracGroup = "FRAC 11 + FRAC 3",
                    knapsackDosage = "10ml - 12ml in 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Daconil 720 SC",
                    activeIngredient = "Chlorothalonil 720 g/L",
                    pcpbRegNo = "PCPB(CR)0298",
                    formulation = "720 g/L SC",
                    targetCrops = listOf("Chrysanthemums", "Roses", "Cut Flowers"),
                    fracIracGroup = "FRAC M05 (Multi-Site Chloronitrile)",
                    knapsackDosage = "40ml in 20L knapsack",
                    perHaDosage = "2.0 L/ha",
                    phiDays = 7,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd"
                )
            )
        ),

        // =========================================================================
        // 2. MAIZE STALK BORER (Busseola fusca / Chilo partellus) - CEREALS & GRAINS
        // =========================================================================
        CropThreat(
            id = "maize_stalk_borer",
            name = "African Maize Stalk Borer",
            scientificName = "Busseola fusca / Chilo partellus",
            type = ThreatType.PEST,
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetCrops = listOf("Maize", "Sorghum", "Millet", "Sugarcane"),
            affectedParts = listOf("Whorl", "Stem", "Leaves", "Cobs / Grains"),
            severityLevel = "High",
            summary = "Major lepidopteran stem-boring pests of cereals in Africa. Young larvae feed inside the leaf whorl creating 'window-paning' pinhole rows, then tunnel directly into the pith of the stalk, causing 'dead-heart' shoot collapse, lodging, and broken cobs.",
            diagnosticKeys = listOf(
                "Horizontal rows of tiny pinholes and transparent window-paning across newly unfurled whorl leaves.",
                "Accumulation of moist sawdust-like frass inside the central funnel (whorl) of young maize plants.",
                "'Dead-heart': central youngest leaf dries up and dies while outer leaves remain green.",
                "Round boreholes on the lower stem plugged with coarse brown frass; stalk hollowed out and snapping easily.",
                "Larvae inside stems: creamy white with dark spots (Chilo partellus) or purplish-grey with brown head (Busseola fusca)."
            ),
            favorableConditions = "Staggered planting of maize, continuous cereal cultivation, presence of dry maize stalks left in field as overwintering diapause shelters.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Clusters Under Leaf Sheath",
                    scientificSub = "Oviposition in Column Batches",
                    iconType = StageIconType.EGG,
                    duration = "5 – 9 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Night-flying female moths deposit 30–100 flattened creamy-white eggs tucked tightly behind lower leaf sheaths.",
                    symptomsToSpot = "Eggs hidden between stem and leaf sheath; moth activity at twilight.",
                    targetAction = "Conserve egg parasitoid wasps (Trichogramma); apply push-pull intercropping with Desmodium.",
                    recommendedActiveIngredients = listOf("Trichogramma chilonis", "Neem Seed Kernel Extract")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Early Instar Feeding in Whorl",
                    scientificSub = "Whorl Pin-Hole Feeding Phase",
                    iconType = StageIconType.LARVA,
                    duration = "7 – 12 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Newly hatched larvae migrate to the moist central leaf whorl. They feed on tender leaves, creating rows of pinholes and frass.",
                    symptomsToSpot = "Fine pinholes across unfurled leaves; moist brown frass in whorl cup.",
                    targetAction = "CRITICAL WINDOW: Apply bio-pesticide granules, fine sand/ash, or targeted contact/systemic spray into whorl funnel.",
                    recommendedActiveIngredients = listOf("Bacillus thuringiensis kurstaki", "Spinetoram 120 g/L", "Emamectin Benzoate 50 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Stem Boring & Tunneling",
                    scientificSub = "Endophytic Stem Excavation",
                    iconType = StageIconType.LARVA,
                    duration = "20 – 35 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Older larvae leave the whorl, bore into the stalk, and eat the vascular pith. Chemical insecticides cannot penetrate the stalk.",
                    symptomsToSpot = "Stem boreholes exuding coarse frass; dead-heart shoot death; stem lodging.",
                    targetAction = "Once larvae enter stem, foliar sprays are ineffective. Rogue plants with dead-heart.",
                    recommendedActiveIngredients = listOf("Rogue dead-heart plants; release Cotesia flavipes wasps")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Pupation in Stalk Chamber",
                    scientificSub = "Prepupal Diapause & Cocoon",
                    iconType = StageIconType.PUPA,
                    duration = "7 – 14 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Caterpillars chew a thin exit window in the stem wall before pupating inside the hollowed tunnel.",
                    symptomsToSpot = "Round papery translucent windows on the outer rind of the lower stem.",
                    targetAction = "Deep post-harvest tillage; chop and bury or feed crop residues to livestock.",
                    recommendedActiveIngredients = listOf("Post-harvest stalk shredding and burial")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Adult Moth Emergence & Flight",
                    scientificSub = "Nocturnal Dispersal & Mating",
                    iconType = StageIconType.ADULT,
                    duration = "3 – 6 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Brownish nocturnal moths emerge through the exit window to mate and deposit eggs on young cereal crops.",
                    symptomsToSpot = "Moths resting on lower stems during day; flight around light traps at night.",
                    targetAction = "Erect solar light traps and pheromone delta traps to monitor and catch flying moths.",
                    recommendedActiveIngredients = listOf("Light traps", "Push-Pull with Napier and Desmodium")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Push-Pull Technology: Intercrop maize with Silverleaf Desmodium (repels stem borer moths) and plant Napier grass or Brachiaria around field borders (attracts and traps moths).",
                    "Destroy dry maize and sorghum stubbles after harvest by plowing under, composting, or feeding to cattle to kill overwintering diapausing larvae.",
                    "Plant early and synchronously at the onset of rains so crops pass the susceptible whorl stage before borer moth populations peak.",
                    "Practice crop rotation with non-host legumes (common beans, cowpeas, groundnuts)."
                ),
                physicalMechanicalControls = listOf(
                    "Place a pinch of clean dry sand, wood ash, or tobacco dust into the whorl of each maize plant at 3–4 weeks after emergence.",
                    "Handpick and crush egg masses and early caterpillars during weekly field scouting."
                ),
                biologicalControls = listOf(
                    "Conserve and release larval parasitoid wasps: Cotesia sesamiae (for Busseola) and Cotesia flavipes (for Chilo partellus).",
                    "Apply Bacillus thuringiensis (Bt) or Beauveria bassiana bio-insecticides into the whorl cup."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Belt 480 SC",
                        pcpbRegNo = "PCPB(CR)1045",
                        formulation = "480 g/L SC",
                        activeIngredient = "Flubendiamide 480 g/L",
                        chemicalGroup = "IRAC Group 28 (Diamide - Ryanodine Receptor Modulator)",
                        classification = "Ingestion Larvicide with Ovicidal Action",
                        targetStage = "Stage 2 (Early Instar Larvae in Whorl)",
                        dosagePerKnapsack = "5ml - 7.5ml in 20L knapsack",
                        dosagePerHectare = "0.15 L/ha",
                        phiDays = 14,
                        reiHours = 12,
                        applicationTiming = "Direct spray coarse droplet jet into the maize whorl at 5% whorl damage threshold.",
                        resistanceManagement = "Maximum 2 applications per season; rotate with IRAC Group 5 (Spinetoram).",
                        safetyPpe = listOf("Nitrile gloves", "Face shield", "Rubber boots", "Overall")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Match 050 EC",
                        pcpbRegNo = "PCPB(CR)0412",
                        formulation = "50 g/L EC",
                        activeIngredient = "Lufenuron 50 g/L",
                        chemicalGroup = "IRAC Group 15 (Chitin Biosynthesis Inhibitor - IGR)",
                        classification = "Insect Growth Regulator (Ovicidal & Larvicidal)",
                        targetStage = "Stage 1 (Eggs) & Stage 2 (Instar 1)",
                        dosagePerKnapsack = "20ml in 20L knapsack",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 14,
                        reiHours = 24,
                        applicationTiming = "Apply at first sign of window-paning pinholes before larvae bore into stalk.",
                        resistanceManagement = "Rotate with diamides or Bt formulations.",
                        safetyPpe = listOf("Gloves", "Safety goggles", "Overalls")
                    )
                ),
                economicThreshold = "5% of young maize plants showing pinholes or window-paning in the whorl."
            ),
            scoutChecklist = listOf(
                "Walk field in W-pattern examining 20 consecutive plants in 5 locations starting 2 weeks after crop emergence.",
                "Inspect inside leaf whorls for sawdust frass and horizontal pinhole perforations.",
                "Check lower stem bases for small circular boreholes and entry tunnels."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Push-Pull Intercropping Companion System",
                    category = "Push-Pull Technology",
                    description = "Planting Desmodium uncinatum between rows of maize produces volatile repellent odors that push stem borer moths away from maize, while Napier grass (Pennisetum purpureum) planted around the perimeter acts as a trap plant that produces gummy sap, killing 80% of borer larvae."
                ),
                InfonetPractice(
                    title = "Neem Seed Kernel Extract (NSKE) Whorl Application",
                    category = "Organic Botanical",
                    description = "Crush 500g of dried neem seeds, soak in 10L water for 12 hours, strain through cheesecloth, add 10ml liquid soap, and apply 5ml-10ml of the extract directly into the central whorl of infested maize plants."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Dipel DF",
                    activeAgent = "Bacillus thuringiensis subsp. kurstaki (strain ABTS-351)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Busseola fusca / Chilo partellus",
                    targetCrops = listOf("Maize", "Sorghum"),
                    modeOfAction = "Produces crystal delta-endotoxins that dissolve borer gut lining upon ingestion",
                    applicationRate = "0.5 kg - 1.0 kg/ha",
                    supplierOrDistributor = "Philagro / Local Distributors Kenya"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Belt 480 SC",
                    activeIngredient = "Flubendiamide 480 g/L",
                    pcpbRegNo = "PCPB(CR)1045",
                    formulation = "480 g/L SC",
                    targetCrops = listOf("Maize", "Sorghum", "Vegetables"),
                    fracIracGroup = "IRAC 28",
                    knapsackDosage = "5ml - 7.5ml in 20L knapsack",
                    perHaDosage = "0.15 L/ha",
                    phiDays = 14,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Bulldock 0.05 GR",
                    activeIngredient = "Beta-cyfluthrin 0.5 g/kg",
                    pcpbRegNo = "PCPB(CR)0214",
                    formulation = "Granules",
                    targetCrops = listOf("Maize", "Sorghum"),
                    fracIracGroup = "IRAC 3A",
                    knapsackDosage = "Pinch (3g-5g) per whorl funnel",
                    perHaDosage = "6.0 kg/ha",
                    phiDays = 21,
                    reiHours = 24,
                    registrant = "Bayer East Africa Ltd"
                )
            )
        ),

        // =========================================================================
        // 3. BEAN FLY / STEM MAGGOT (Ophiomyia phaseoli) - LEGUMES & PULSES
        // =========================================================================
        CropThreat(
            id = "bean_fly",
            name = "Bean Fly (Bean Stem Maggot)",
            scientificName = "Ophiomyia phaseoli / Ophiomyia spencerella",
            type = ThreatType.PEST,
            cropCategory = CropCategory.LEGUMES,
            targetCrops = listOf("Common Beans", "French Beans", "Cowpeas", "Soybeans"),
            affectedParts = listOf("Stem", "Roots", "Leaves"),
            severityLevel = "Extreme",
            summary = "Most destructive early-season insect pest of common beans and French beans in Africa. Tiny shiny black flies lay eggs into unifoliate leaves; maggots mine down the petiole to the root-stem junction (collar), causing stem swelling, longitudinal cracking, adventitious root death, and complete seedling lodging and collapse within 2–3 weeks of emergence.",
            diagnosticKeys = listOf(
                "Minute, pale yellow oviposition and feeding puncture dots on the upper surface of seedling bean leaves.",
                "Swollen, spongy, cracked collar (base of the stem at soil level) with yellow or brown longitudinal splits.",
                "Brownish or translucent puparia (1.5mm–2.5mm) lodged beneath the cracked bark of the stem collar.",
                "Seedlings turn yellow, wilt during hot sunny hours, lodge (fall over), and snap off cleanly at the soil line.",
                "Pulling seedling reveals a rotten taproot with few adventitious roots attempting to survive above the damaged collar."
            ),
            favorableConditions = "Dry warm weather during the first 3 weeks after sowing, delayed crop emergence, low soil moisture, and late planting.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Inserted into Seedling Leaves",
                    scientificSub = "Endophytic Oviposition",
                    iconType = StageIconType.EGG,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Small shiny black flies puncture seedling unifoliate leaves with their ovipositor, inserting oval white eggs into the leaf mesophyll near the petiole.",
                    symptomsToSpot = "Pinhole puncture dots surrounded by faint chlorotic rings on cotyledons and first leaves.",
                    targetAction = "Apply systemic insecticidal seed treatment before sowing; insect-proof row covers.",
                    recommendedActiveIngredients = listOf("Thiamethoxam 350 g/L (Seed Dressing)", "Imidacloprid 600 g/L")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Maggot Mining Down the Petiole",
                    scientificSub = "Subcortical Tunneling Phase",
                    iconType = StageIconType.LARVA,
                    duration = "7 – 12 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Transparent cylindrical maggot mines through the leaf tissue, travels down the petiole into the stem cortex, and concentrates feeding at the soil collar.",
                    symptomsToSpot = "Brownish linear mine along the leaf petiole leading directly into the lower stem.",
                    targetAction = "CRITICAL WINDOW: Apply systemic drench/spray within 7–10 days of seedling emergence before collar cracking occurs.",
                    recommendedActiveIngredients = listOf("Thiamethoxam 250 g/kg", "Acetamiprid 200 g/kg", "Azadirachtin")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Collar Pupation & Stem Splitting",
                    scientificSub = "Puparium at Root-Stem Collar",
                    iconType = StageIconType.PUPA,
                    duration = "7 – 10 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Maggot turns into a shiny dark brown or reddish puparium located just below the cracked epidermal layer of the swollen collar.",
                    symptomsToSpot = "Swollen, cracked collar with dark cylindrical puparia embedded in the bark.",
                    targetAction = "Earth up soil around seedling stems to promote new adventitious roots above the injury.",
                    recommendedActiveIngredients = listOf("Hilling up soil around stem collar")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Adult Black Agromyzid Fly",
                    scientificSub = "Nocturnal & Morning Flight",
                    iconType = StageIconType.ADULT,
                    duration = "8 – 14 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Tiny (2mm) metallic black fly with clear iridescent wings emerges to feed on leaf punctures and lay eggs on next crop.",
                    symptomsToSpot = "Small black flies active on seedling foliage in early morning sunshine.",
                    targetAction = "Deploy yellow sticky traps at ground level to catch flying adult flies.",
                    recommendedActiveIngredients = listOf("Yellow sticky card traps", "Neem foliar spray")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Seed Dressing: Treat all bean seeds with systemic neonicotinoid seed dressing (e.g. Cruiser 350 FS) prior to sowing; provides complete 3-week protection during the vulnerable seedling stage.",
                    "Hilling Up (Earthing up): Mound loose moist soil around the base of the bean plants during the first weeding (2–3 weeks after emergence) to stimulate adventitious root formation above the damaged stem collar.",
                    "Plant early at the very onset of the rainy season when bean fly populations are low.",
                    "Mulch fields with dry straw or crop residue to conserve soil moisture and encourage vigorous seedling growth.",
                    "Avoid planting beans near older, harvested bean fields where adult flies are multiplying."
                ),
                physicalMechanicalControls = listOf(
                    "Erect yellow sticky cards at canopy height (1 card per 100m²) to monitor and mass-trap adult flies.",
                    "Cover nursery beds or export French bean blocks with 30-mesh insect-proof netting for the first 21 days."
                ),
                biologicalControls = listOf(
                    "Conserve native parasitic wasps: Opius phaseoli and Sphegigaster spp. which parasitize up to 60% of bean fly maggots and puparia.",
                    "Apply soil and foliar drench of Metarhizium anisopliae or Beauveria bassiana."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Cruiser 350 FS",
                        pcpbRegNo = "PCPB(CR)0689",
                        formulation = "350 g/L FS (Flowable Concentrate for Seed Treatment)",
                        activeIngredient = "Thiamethoxam 350 g/L",
                        chemicalGroup = "IRAC Group 4A (Neonicotinoid)",
                        classification = "Systemic Seed Treatment Insecticide",
                        targetStage = "Stage 1 (Egg & Early Maggot Penetration)",
                        dosagePerKnapsack = "2.5ml per 1kg of bean seed (slurry with 10ml water)",
                        dosagePerHectare = "100ml per 40kg seed/ha",
                        phiDays = 60,
                        reiHours = 0,
                        applicationTiming = "Coat certified seed thoroughly before sowing; allow to dry in shade.",
                        resistanceManagement = "Single seed application protects throughout critical 21-day seedling window.",
                        safetyPpe = listOf("Chemical resistant rubber gloves", "Dust/mist respirator", "Overalls")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Actara 25 WG",
                        pcpbRegNo = "PCPB(CR)0534",
                        formulation = "250 g/kg WG",
                        activeIngredient = "Thiamethoxam 250 g/kg",
                        chemicalGroup = "IRAC Group 4A (Neonicotinoid)",
                        classification = "Broad-Spectrum Systemic Insecticide",
                        targetStage = "Stage 2 (Maggots in Petiole)",
                        dosagePerKnapsack = "8g in 20L knapsack",
                        dosagePerHectare = "0.2 kg/ha",
                        phiDays = 7,
                        reiHours = 12,
                        applicationTiming = "Foliar drench 7–10 days after emergence if untreated seed was sown.",
                        resistanceManagement = "Rotate with diamides or pyrethroids; maximum 1 spray per cycle.",
                        safetyPpe = listOf("Gloves", "Goggles", "Coveralls")
                    )
                ),
                economicThreshold = "5% of bean seedlings showing leaf puncture dots or petiole mines at 10 days after emergence."
            ),
            scoutChecklist = listOf(
                "Inspect 50 emerging bean seedlings per hectare at 7 and 14 days after emergence.",
                "Check cotyledons and first true unifoliate leaves for tiny puncture dots.",
                "Examine lower stem collars at soil line for swelling, browning, or longitudinal splits."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Earthing-Up / Hilling Agronomic Compensation",
                    category = "Cultural Management",
                    description = "When bean fly maggots damage the stem collar, the plant's taproot is cut off. Mounding moist soil around the stem base at first weeding covers the cracked collar and stimulates vigorous adventitious roots to grow directly from the stem above the injury, preventing seedling death."
                ),
                InfonetPractice(
                    title = "Neem Seed Water Extract Foliar Spray",
                    category = "Organic Botanical",
                    description = "Soak 500g of crushed neem seed powder in 10L water for 24 hours. Spray onto seedlings at first unifoliate leaf stage. Azadirachtin deters adult fly oviposition and disrupts maggot moulting inside leaf tissue."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Campaign (Metarhizium)",
                    activeAgent = "Metarhizium anisopliae strain ICIPE 69",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Ophiomyia phaseoli",
                    targetCrops = listOf("French Beans", "Common Beans"),
                    modeOfAction = "Entomopathogenic fungal spores penetrate insect cuticle and colonize body cavity",
                    applicationRate = "200ml - 250ml/ha",
                    supplierOrDistributor = "Real IPM / Koppert Kenya"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Cruiser 350 FS",
                    activeIngredient = "Thiamethoxam 350 g/L",
                    pcpbRegNo = "PCPB(CR)0689",
                    formulation = "350 g/L FS",
                    targetCrops = listOf("French Beans", "Common Beans", "Maize"),
                    fracIracGroup = "IRAC 4A",
                    knapsackDosage = "2.5ml per 1kg seed",
                    perHaDosage = "100ml / ha seed",
                    phiDays = 60,
                    reiHours = 0,
                    registrant = "Syngenta East Africa Ltd"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Confidor 200 SL",
                    activeIngredient = "Imidacloprid 200 g/L",
                    pcpbRegNo = "PCPB(CR)0312",
                    formulation = "200 g/L SL",
                    targetCrops = listOf("French Beans", "Vegetables", "Tomatoes"),
                    fracIracGroup = "IRAC 4A",
                    knapsackDosage = "10ml - 15ml in 20L knapsack",
                    perHaDosage = "0.25 L/ha",
                    phiDays = 7,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd"
                )
            )
        ),

        // =========================================================================
        // 4. PURPLE BLOTCH & DOWNY MILDEW (Alternaria porri) - VEGETABLES & ALLIUMS
        // =========================================================================
        CropThreat(
            id = "purple_blotch_onion",
            name = "Purple Blotch of Onion & Garlic",
            scientificName = "Alternaria porri",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Onion", "Garlic", "Leek", "Shallot"),
            affectedParts = listOf("Leaves", "Stem", "Seed"),
            severityLevel = "High",
            summary = "Major fungal foliar blight of allium crops worldwide. Enters through stomata and thrips feeding wounds, creating sunken elliptical water-soaked lesions that develop distinctive deep purple to brown centers surrounded by yellow halos, girdling the leaf and causing premature lodging and collapse.",
            diagnosticKeys = listOf(
                "Small, water-soaked circular to elliptical spots on leaves that rapidly enlarge.",
                "Distinctive deep purple or brown necrotic centers inside elliptical lesions.",
                "Concentric dark rings of fungal conidiospores visible inside the purple center during moist mornings.",
                "Lesions girdle the tubular leaf, causing the entire upper leaf or seed stalk to bend over and snap.",
                "Severely infected fields appear scorched as if struck by frost or drought."
            ),
            favorableConditions = "Warm humid weather (21°C–30°C), prolonged leaf wetness (>10 hours) from overhead irrigation, dew, or rain, and high thrips feeding damage.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Conidia Inoculum & Thrips Entry",
                    scientificSub = "Airborne Spore Deposition",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 6 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Airborne conidia land on onion foliage and germinate in dew film, penetrating stomata or thrips feeding puncture wounds.",
                    symptomsToSpot = "Tiny white pinhead flecks on tubular onion leaves.",
                    targetAction = "Apply protective multi-site contact fungicide; control onion thrips to prevent entry points.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Chlorothalonil 720 g/L", "Copper Oxychloride")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Intercellular Mycelial Colonization",
                    scientificSub = "Elliptical Lesion Expansion",
                    iconType = StageIconType.HYPHAE,
                    duration = "3 – 5 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Fungal hyphae invade leaf tissue, producing alternaric acid toxin that kills cells and creates sunken elliptical purple centers.",
                    symptomsToSpot = "Elliptical lesions with dark purple centers and broad yellow margins.",
                    targetAction = "Apply systemic triazole or strobilurin curative fungicide with sticker.",
                    recommendedActiveIngredients = listOf("Difenoconazole 250 g/L", "Tebuconazole 250 g/L", "Azoxystrobin")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Stem Girdling & Foliar Lodging",
                    scientificSub = "Necrotic Girdling",
                    iconType = StageIconType.LESION,
                    duration = "5 – 8 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Lesion encircles the circumference of the tubular leaf or seed scape, cutting vascular flow and causing top collapse.",
                    symptomsToSpot = "Leaves bend sharply at the lesion site and collapse to the ground.",
                    targetAction = "Protect newly emerging central leaves; rogue severely blighted leaves.",
                    recommendedActiveIngredients = listOf("Azoxystrobin + Difenoconazole", "Fluopyram + Trifloxystrobin")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Sporulation & Wind Dispersal",
                    scientificSub = "Conidiophore Dispersal",
                    iconType = StageIconType.DISPERSAL,
                    duration = "12 – 24 hours",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Dark brown club-shaped conidia are discharged in dry afternoon air currents, spreading across entire onion basins.",
                    symptomsToSpot = "Velvety brown-black concentric rings of spores on dead leaf tips.",
                    targetAction = "Use non-ionic adjuvant/sticker with all sprays due to waxy onion leaf cuticle.",
                    recommendedActiveIngredients = listOf("Add organosilicone wetter/sticker to all sprays")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Practice 3-year crop rotation with non-allium crops (brassicas, legumes, cereals).",
                    "Always use a non-ionic wetting agent (sticker/spreader) when spraying onions due to their thick, waxy, upright cuticle.",
                    "Ensure adequate plant spacing (10cm x 15cm) and orient beds in the direction of prevailing winds for fast leaf drying.",
                    "Avoid excessive nitrogen fertilization which creates soft, succulent leaves highly vulnerable to spore penetration.",
                    "Use drip irrigation instead of overhead sprinklers to eliminate leaf wetness."
                ),
                physicalMechanicalControls = listOf(
                    "Destroy infected onion cull piles and volunteer allium plants around field borders.",
                    "Properly cure harvested onion bulbs in dry, well-ventilated sheds before storage."
                ),
                biologicalControls = listOf(
                    "Bacillus subtilis foliar bio-fungicide sprays.",
                    "Trichoderma harzianum bulb dressing and soil incorporation."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        tradeName = "Ortiva Top 325 SC",
                        pcpbRegNo = "PCPB(CR)0834",
                        formulation = "325 g/L SC",
                        activeIngredient = "Azoxystrobin 200 g/L + Difenoconazole 125 g/L",
                        chemicalGroup = "FRAC Group 11 (QoI) + FRAC Group 3 (DMI Triazole)",
                        classification = "Systemic Preventative and Curative Fungicide",
                        targetStage = "Stage 1 (Spore Inoculum) & Stage 2 (Purple Centers)",
                        dosagePerKnapsack = "15ml in 20L knapsack + 5ml Agral 90 sticker",
                        dosagePerHectare = "0.75 L/ha",
                        phiDays = 7,
                        reiHours = 12,
                        applicationTiming = "Apply at first appearance of purple flecks; reapply after 10-14 days.",
                        resistanceManagement = "Rotate with FRAC M05 chlorothalonil or FRAC M01 copper.",
                        safetyPpe = listOf("Nitrile gloves", "Face mask", "Goggles", "Coveralls")
                    ),
                    ChemicalRecommendation(
                        tradeName = "Ridomil Gold MZ 68 WG",
                        pcpbRegNo = "PCPB(CR)0567",
                        formulation = "680 g/kg WG",
                        activeIngredient = "Mefenoxam 40 g/kg + Mancozeb 640 g/kg",
                        chemicalGroup = "FRAC Group 4 (PA Phenylamide) + FRAC Group M03",
                        classification = "Systemic & Multi-Site Contact Fungicide",
                        targetStage = "Stage 1 (Spore Ingress)",
                        dosagePerKnapsack = "50g in 20L knapsack",
                        dosagePerHectare = "2.5 kg/ha",
                        phiDays = 14,
                        reiHours = 24,
                        applicationTiming = "Apply early during cool wet spells to prevent purple blotch & downy mildew complex.",
                        resistanceManagement = "Maximum 2 applications per season.",
                        safetyPpe = listOf("Gloves", "Dust mask", "Boots")
                    )
                ),
                economicThreshold = "1% leaf area infected or 5 purple spots per 20 inspected onion plants."
            ),
            scoutChecklist = listOf(
                "Inspect 10 plants per bed in 5 spots across the field weekly.",
                "Check older outer leaves first for water-soaked elliptical spots with purple centers.",
                "Scout simultaneously for onion thrips feeding scars on leaf axils."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Non-Ionic Sticker & Wetting Agent Requirement",
                    category = "Spraying Technology",
                    description = "Onion leaves have a thick, vertical, waxy cuticle. Spray droplets bounce off completely without an agricultural wetting agent. Always add an organosilicone sticker (e.g. Integra, Agral 90) at 3ml–5ml per 20L knapsack to ensure uniform chemical spread."
                ),
                InfonetPractice(
                    title = "Field Sanitation & Bulb Curing",
                    category = "Post-Harvest Sanitation",
                    description = "Alternaria porri overwinters on dry onion leaf residue and bulb necks. Cut foliage 2cm above the bulb, cure bulbs for 10–14 days in shade until necks are papery and sealed, and remove all field residue."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Trianum-P",
                    activeAgent = "Trichoderma harzianum strain T-22",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Alternaria porri",
                    targetCrops = listOf("Onion", "Garlic"),
                    modeOfAction = "Antagonistic colonization and enzymatic digestion of fungal hyphae",
                    applicationRate = "1.5 kg/ha",
                    supplierOrDistributor = "Koppert Biological Systems Kenya"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Ortiva Top 325 SC",
                    activeIngredient = "Azoxystrobin 200 g/L + Difenoconazole 125 g/L",
                    pcpbRegNo = "PCPB(CR)0834",
                    formulation = "325 g/L SC",
                    targetCrops = listOf("Onion", "Garlic", "Tomato"),
                    fracIracGroup = "FRAC 11 + FRAC 3",
                    knapsackDosage = "15ml in 20L knapsack",
                    perHaDosage = "0.75 L/ha",
                    phiDays = 7,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Score 250 EC",
                    activeIngredient = "Difenoconazole 250 g/L",
                    pcpbRegNo = "PCPB(CR)0356",
                    formulation = "250 g/L EC",
                    targetCrops = listOf("Onion", "Garlic", "Vegetables"),
                    fracIracGroup = "FRAC 3",
                    knapsackDosage = "10ml - 12ml in 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 7,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd"
                )
            )
        )
    )
}
