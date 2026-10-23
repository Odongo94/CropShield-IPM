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

object ExpandedThreats {

    val threats: List<CropThreat> = listOf(
        // =========================================================================
        // 1. COMMON BEAN RUST (Uromyces appendiculatus) - LEGUMES & PULSES
        // Sources: Infonet-Biovision, PCPB Kenya, CABI BioProtection Portal
        // =========================================================================
        CropThreat(
            id = "bean_rust",
            name = "Common Bean Rust",
            scientificName = "Uromyces appendiculatus",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.LEGUMES,
            targetCrops = listOf("French Beans", "Common Beans", "Cowpeas", "Runner Beans", "Pigeon Peas"),
            affectedParts = listOf("Leaves", "Pods", "Stems"),
            severityLevel = "Extreme",
            summary = "One of the most destructive fungal diseases of legumes in East Africa. Causes widespread premature defoliation, chlorosis, and pod blemish, reducing French bean export yields by 30% to 100% under warm humid conditions (17°C–27°C, >95% RH).",
            diagnosticKeys = listOf(
                "Small, circular pale-yellow spots on upper leaf surfaces within 5-6 days of infection.",
                "Eruption of raised reddish-brown to dark cinnamon-brown powdery pustules (uredinia) on leaf undersides and pods.",
                "Pustules surrounded by a distinct bright chlorotic yellow halo.",
                "Pods developing brown blistered rust pustules causing distortion and total export rejection.",
                "In late stages, pustules turn dark brownish-black as overwintering two-celled teliospores form."
            ),
            favorableConditions = "Prolonged leaf wetness (>8 hours of dew or rain), moderate temperatures (17°C–25°C), high relative humidity (>95%), and dense unpruned canopies.",
            imageResId = R.drawable.sample_bean_rust,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Urediniospore Inoculum Landing",
                    scientificSub = "Asexual Repeating Spore Phase",
                    iconType = StageIconType.SPORE,
                    duration = "4 – 8 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Airborne dikaryotic urediniospores land on bean leaves from volunteer beans, wild legumes, or adjacent fields.",
                    symptomsToSpot = "Microscopic airborne spores present on leaf surfaces; no visible chlorosis yet.",
                    targetAction = "Apply preventative multi-site protective barrier before rain showers or heavy morning fog.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Chlorothalonil 720 g/L", "Copper Oxychloride 500 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Appressorium Formation & Stomatal Entry",
                    scientificSub = "Germ Tube Penetration",
                    iconType = StageIconType.PENETRATION,
                    duration = "8 – 14 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Germ tube recognizes guard cells of open stomata, forming an appressorium and driving infection hyphae into the substomatal cavity.",
                    symptomsToSpot = "Microscopic penetration; open stomata facilitate entry in morning dew.",
                    targetAction = "Apply translaminar systemic triazole or strobilurin fungicide with kick-back curative activity.",
                    recommendedActiveIngredients = listOf("Difenoconazole 250 g/L", "Azoxystrobin 250 g/L", "Tebuconazole 250 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Intercellular Mycelium & Haustoria Feeding",
                    scientificSub = "Endotrophic Incubation",
                    iconType = StageIconType.HYPHAE,
                    duration = "4 – 7 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Mycelium branches through intercellular leaf mesophyll, invaginating host cells with feeding haustoria without killing cells immediately.",
                    symptomsToSpot = "Small chlorotic pale green-yellow pinprick spots emerging on upper leaf surface.",
                    targetAction = "Apply systemic curative triazole spray to stop haustoria development and prevent sporulation.",
                    recommendedActiveIngredients = listOf("Propiconazole 250 g/L", "Difenoconazole + Azoxystrobin", "Trifloxystrobin + Tebuconazole")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Pustule Eruption & Reddish-Brown Sporulation",
                    scientificSub = "Uredinium Eruption & Dispersal",
                    iconType = StageIconType.LESION,
                    duration = "7 – 14 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Sub-epidermal uredinia rupture leaf cuticle, releasing millions of powdery cinnamon-brown urediniospores into wind currents.",
                    symptomsToSpot = "Reddish-brown powdery pustules with yellow halos; reddish dust rubs off on fingers.",
                    targetAction = "Spray eradicant and protective tank-mix; strip heavily rusted lower leaves and destroy.",
                    recommendedActiveIngredients = listOf("Azoxystrobin 200g/L + Difenoconazole 125g/L", "Trifloxystrobin 100g/L + Tebuconazole 200g/L")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Plant rust-tolerant or resistant bean varieties (e.g. GLP 24 / Mwitemania, KK8, Chelalang, MAC 34, Miezi Mbili).",
                    "Rotate legumes with non-host graminaceous crops (maize, sorghum, wheat) for at least 2 consecutive seasons.",
                    "Destroy and incorporate bean crop residues and volunteer bean seedlings immediately after harvest.",
                    "Space rows adequately (45cm x 15cm) to promote air movement and accelerate morning canopy drying.",
                    "Avoid entering or weeding bean fields when foliage is wet to prevent spore dissemination on clothing."
                ),
                physicalMechanicalControls = listOf(
                    "Hand-strip and deeply bury early infected lower canopy leaves showing initial chlorotic rust spots.",
                    "Use drip irrigation in place of overhead sprinklers to eliminate leaf surface moisture."
                ),
                biologicalControls = listOf(
                    "Apply Bacillus subtilis (Serenade ASO) bio-fungicide preventatively at 10-day intervals.",
                    "Spray Trichoderma harzianum (Trianum-P) to suppress germinating rust spores on leaf surfaces.",
                    "Foliar spray with fermented stinging nettle or 5% neem seed kernel extract to boost plant systemic resistance."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Difenoconazole 250 g/L",
                        chemicalGroup = "FRAC Group 3 (DMI Triazole)",
                        classification = "Systemic Curative & Translaminar",
                        targetStage = "Stage 2-3 (Penetration & Haustoria)",
                        dosagePerKnapsack = "10ml - 15ml per 20L knapsack",
                        dosagePerHectare = "0.3 - 0.5 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at first sign of pinprick chlorotic flecks before pustule rupture.",
                        resistanceManagement = "Rotate with FRAC 11 (Strobilurin) or FRAC M03 (Mancozeb). Maximum 2 triazole applications per season.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Score 250 EC",
                        pcpbRegNo = "PCPB(CR)0298",
                        formulation = "Emulsifiable Concentrate (EC)",
                        lastingIpmRule = "Lasting solution: Alternate Score with multi-site protective Mancozeb to avoid single-site triazole resistance."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Azoxystrobin 200g/L + Difenoconazole 125g/L",
                        chemicalGroup = "FRAC 11 (QoI) + FRAC 3 (DMI)",
                        classification = "Translaminar & Systemic Preventive/Curative",
                        targetStage = "Stages 1, 2, and 3",
                        dosagePerKnapsack = "15ml - 20ml per 20L knapsack",
                        dosagePerHectare = "0.75 - 1.0 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at flowering or pod formation if rust weather conditions prevail.",
                        resistanceManagement = "Dual mode-of-action formulation provides built-in resistance stewardship. Do not exceed 2 sprays per crop cycle.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Amistar Top 325 SC",
                        pcpbRegNo = "PCPB(CR)0781",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: Two complimentary MoAs prevent resistance development in Uromyces."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Trifloxystrobin 100g/L + Tebuconazole 200g/L",
                        chemicalGroup = "FRAC 11 (Strobilurin) + FRAC 3 (Triazole)",
                        classification = "Mesostemic & Systemic Curative",
                        targetStage = "Stages 1 to 4",
                        dosagePerKnapsack = "15ml - 20ml per 20L knapsack",
                        dosagePerHectare = "0.75 L/ha",
                        phiDays = 7,
                        reiHours = 24,
                        applicationTiming = "Apply at onset of disease; locks onto plant wax layer providing extended rain-fast protection.",
                        resistanceManagement = "Rotate with copper or mancozeb; maximum 2 sprays per cropping year.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Shield", "Boots"),
                        tradeName = "Nativo 300 SC",
                        pcpbRegNo = "PCPB(CR)0936",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: High curative kick-back action; stops sporulation of established rust pustules."
                    )
                ),
                economicThreshold = "Presence of 1-2 rust pustules per leaf on 10% of scouted plants before pod set."
            ),
            scoutChecklist = listOf(
                "Inspect lower and mid-canopy leaves on 20 randomly selected plants across an X-pattern in the field.",
                "Turn leaves over and check leaf undersides for powdery cinnamon-brown pustules and chlorotic halos.",
                "Inspect French bean pods for blister-like raised pustules during pod elongation stage.",
                "Monitor relative humidity and morning dew duration; trigger scouting after consecutive misty mornings."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Varietal Resistance & Certified Seed",
                    category = "Cultural & Preventative",
                    description = "Plant resistant cultivars like GLP 24 (Mwitemania) or KK8 developed by KALRO/CIAT. Resistant varieties prevent uredinium formation and halt fungal sporulation.",
                    recipeOrMethod = "Source certified certified disease-free seed from registered seed merchants (e.g. Kenya Seed, Simlaw)."
                ),
                InfonetPractice(
                    title = "Post-Harvest Sanitation & Residue Burial",
                    category = "Cultural & Hygiene",
                    description = "Uromyces survives between crops on volunteer beans and unburied haulms. Deep plowing buries teliospores below germination depth.",
                    recipeOrMethod = "Uproot infected bean stalks immediately after harvesting and bury at least 20cm deep or compost thoroughly."
                ),
                InfonetPractice(
                    title = "Botanical Stinging Nettle & Neem Extract Wash",
                    category = "Organic Botanical",
                    description = "Fermented stinging nettle extract provides silica and potassium to strengthen leaf cuticles against fungal penetration, while azadirachtin suppresses fungal spore germination.",
                    recipeOrMethod = "Chop 1kg fresh stinging nettle into 10L water. Ferment for 7 days in shaded bucket, stirring daily. Dilute 1:5 with water, add 10ml mild soap, and spray foliage."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Serenade ASO",
                    activeAgent = "Bacillus subtilis strain QST 713",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Uromyces appendiculatus & foliar bean blights",
                    targetCrops = listOf("French Beans", "Common Beans", "Legumes"),
                    modeOfAction = "Produces antifungal lipopeptides (surfactins, iturins) that rupture fungal cell membranes and elicit systemic acquired resistance (SAR).",
                    applicationRate = "50ml - 80ml in 20L knapsack; apply every 7-10 days preventatively.",
                    safetyMrl = "Zero MRL; 0 days PHI; NOP/OMRI organic certified.",
                    supplierOrDistributor = "Bayer CropScience Biologics / CABI BioProtection Portal",
                    compatibilityNotes = "Compatible with most copper fungicides; do not tank-mix with strong bactericides."
                ),
                CabiBioProduct(
                    tradeName = "ECO-T",
                    activeAgent = "Trichoderma asperellum strain kd",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Rust spores, root rots, and foliar pathogens",
                    targetCrops = listOf("French Beans", "Legumes", "Vegetables"),
                    modeOfAction = "Hyperparasitism, production of chitinase enzymes, and competitive colonization of leaf surfaces.",
                    applicationRate = "10g per 20L knapsack foliar application or drench.",
                    safetyMrl = "Exempt from residue tolerances; safe for export French beans.",
                    supplierOrDistributor = "Plant Health Products / CABI BioProtection Portal",
                    compatibilityNotes = "Do not mix with synthetic triazole fungicides in the same spray tank."
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Score 250 EC",
                    activeIngredient = "Difenoconazole 250 g/L",
                    pcpbRegNo = "PCPB(CR)0298",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("French Beans", "Common Beans", "Peas"),
                    fracIracGroup = "FRAC Group 3 (DMI Triazole)",
                    knapsackDosage = "10ml - 15ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Preventative and early curative action. Rotate with FRAC M03 (Mancozeb) to prevent resistant DMI strains."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Amistar Top 325 SC",
                    activeIngredient = "Azoxystrobin 200g/L + Difenoconazole 125g/L",
                    pcpbRegNo = "PCPB(CR)0781",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("French Beans", "Common Beans", "Snow Peas"),
                    fracIracGroup = "FRAC 11 + FRAC 3",
                    knapsackDosage = "15ml - 20ml per 20L knapsack",
                    perHaDosage = "0.8 - 1.0 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Two complementary modes of action protect plant canopy and suppress spore sporulation."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Nativo 300 SC",
                    activeIngredient = "Trifloxystrobin 100g/L + Tebuconazole 200g/L",
                    pcpbRegNo = "PCPB(CR)0936",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("French Beans", "Legumes", "Vegetables"),
                    fracIracGroup = "FRAC 11 + FRAC 3",
                    knapsackDosage = "15ml - 20ml per 20L knapsack",
                    perHaDosage = "0.75 L/ha",
                    phiDays = 7,
                    reiHours = 24,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Locks onto epicuticular plant waxes, providing superior rain-fastness against rust."
                )
            )
        ),

        // =========================================================================
        // 2. CEREAL RUSTS (Maize Common Rust & Wheat Stem/Stripe Rust) - CEREALS
        // Puccinia sorghi / Puccinia graminis / Puccinia striiformis
        // Sources: Infonet-Biovision, PCPB Kenya, CABI BioProtection Portal
        // =========================================================================
        CropThreat(
            id = "cereal_rust",
            name = "Cereal Rusts (Maize & Wheat)",
            scientificName = "Puccinia sorghi / Puccinia graminis",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetCrops = listOf("Maize", "Wheat", "Barley", "Sorghum", "Millet"),
            affectedParts = listOf("Leaves", "Stem", "Sheath", "Tassels"),
            severityLevel = "Extreme",
            summary = "Airborne macrocyclic rust fungi capable of continental wind dispersion. Causes severe leaf chlorosis, lodging, and premature desiccation, resulting in up to 70% grain yield loss in cereal production throughout the Rift Valley and Western Kenya.",
            diagnosticKeys = listOf(
                "Golden-brown to reddish-orange powdery pustules (uredinia) erupting on both leaf surfaces.",
                "Pustules on maize scattered randomly; on wheat forming distinct linear stripes or raised stem blisters.",
                "Leaves becoming chlorotic and desiccated; severely affected plants appear prematurely burned or ripe.",
                "Epidermis tearing irregularly with powdery spores rubbing off easily on skin or clothing.",
                "Pustules turning dark chocolate-brown to black as wheat or maize reaches maturity (teliospore stage)."
            ),
            favorableConditions = "High humidity (>95%), moderate day temperatures (16°C–25°C), cool nights with prolonged dew, and persistent wind currents transporting airborne spores.",
            imageResId = R.drawable.sample_cereal_rust,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Airborne Urediniospore Influx",
                    scientificSub = "Asexual Spore Cloud Arrival",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 6 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Wind currents carry microscopic urediniospores hundreds of kilometers from green cereal bridges and early-planted fields.",
                    symptomsToSpot = "Healthy green cereal leaves carrying microscopic spores; no visual lesions yet.",
                    targetAction = "Apply preventative multi-site protective fungicide prior to heavy cloud cover and humidity.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Chlorothalonil 720 g/L", "Copper Hydroxide 538 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Appressorial Stomatal Penetration",
                    scientificSub = "Infection Peg Entry",
                    iconType = StageIconType.PENETRATION,
                    duration = "6 – 12 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Spores germinate in surface dew droplets, directing germ tubes toward stomatal openings where an appressorium forms.",
                    symptomsToSpot = "Infection peg penetrates into substomatal cavity under moist night conditions.",
                    targetAction = "Apply systemic triazole or strobilurin fungicide with kick-back curative activity.",
                    recommendedActiveIngredients = listOf("Propiconazole 250 g/L", "Tebuconazole 250 g/L", "Azoxystrobin + Cyproconazole")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Sub-Epidermal Mycelial Expansion",
                    scientificSub = "Incubation & Chlorosis",
                    iconType = StageIconType.HYPHAE,
                    duration = "5 – 8 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Fungal mycelium establishes feeding haustoria in mesophyll cells, siphoning carbohydrates from photosynthetic machinery.",
                    symptomsToSpot = "Tiny chlorotic yellowish flecks along leaf veins and leaf sheaths.",
                    targetAction = "Apply translaminar systemic curative spray to arrest hyphal elongation.",
                    recommendedActiveIngredients = listOf("Prothioconazole 250 g/L", "Tebuconazole + Trifloxystrobin")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Epidermal Rupture & Powdery Pustule Eruption",
                    scientificSub = "Uredinium Eruption & Re-Infection",
                    iconType = StageIconType.LESION,
                    duration = "7 – 14 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Massive sporulation causes host epidermis to tear open, releasing billions of airborne powdery orange-brown spores.",
                    symptomsToSpot = "Prominent raised powdery orange to reddish-brown pustules on both upper and lower leaf surfaces.",
                    targetAction = "Eradicant triazole spray to prevent secondary re-infection cycles across the field.",
                    recommendedActiveIngredients = listOf("Prosaro 250 EC (Prothioconazole + Tebuconazole)", "Tilt 250 EC (Propiconazole)")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Plant resistant cereal hybrids and wheat varieties (e.g. Robin, Eagle10, H628, H629 maize; certified rust-resistant wheat).",
                    "Synchronize planting dates within farming communities to avoid staggered green bridges that sustain rust cycles.",
                    "Ensure balanced fertilization: avoid excessive nitrogen (which promotes lush succulent foliage highly susceptible to rust); apply adequate potassium and phosphorus.",
                    "Eradicate wild volunteer grasses and volunteer wheat/maize along field edges that harbor overwintering rust spores.",
                    "Practice crop rotation with non-cereal crops such as legumes (beans, peas, soybeans) or canola."
                ),
                physicalMechanicalControls = listOf(
                    "Maintain optimum plant population (75cm x 25cm for maize) to prevent overly humid microclimates.",
                    "Deep autumn/post-harvest plowing to bury infested cereal stubble."
                ),
                biologicalControls = listOf(
                    "Foliar spray with Bacillus amyloliquefaciens bio-antagonist at stem elongation.",
                    "Trichoderma asperellum seed coating and early canopy spray to elicit systemic induced resistance."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Propiconazole 250 g/L",
                        chemicalGroup = "FRAC Group 3 (DMI Triazole)",
                        classification = "Systemic Curative & Protective",
                        targetStage = "Stages 2 and 3 (Penetration & Incubation)",
                        dosagePerKnapsack = "15ml - 20ml per 20L knapsack",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 28,
                        reiHours = 24,
                        applicationTiming = "Apply at first sign of chlorotic rust flecks on lower leaves before tasseling/heading.",
                        resistanceManagement = "Rotate with FRAC 11 (Strobilurin) or FRAC 7 (SDHI). Maximum 2 applications per cereal season.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Tilt 250 EC",
                        pcpbRegNo = "PCPB(CR)0247",
                        formulation = "Emulsifiable Concentrate (EC)",
                        lastingIpmRule = "Lasting solution: Potent xylem-systemic translocation protecting emerging upper leaves and flag leaves."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Prothioconazole 125g/L + Tebuconazole 125g/L",
                        chemicalGroup = "FRAC Group 3 (Triazolinthione + Triazole)",
                        classification = "Broad-Spectrum Systemic Curative",
                        targetStage = "Stages 2, 3, and 4",
                        dosagePerKnapsack = "20ml per 20L knapsack",
                        dosagePerHectare = "0.75 - 1.0 L/ha",
                        phiDays = 35,
                        reiHours = 24,
                        applicationTiming = "Apply at flag leaf emergence (wheat) or pre-tasseling (maize) when rust pressure is detected.",
                        resistanceManagement = "Dual triazole chemistry provides robust curative action against multiple rust physiological races.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Prosaro 250 EC",
                        pcpbRegNo = "PCPB(CR)0982",
                        formulation = "Emulsifiable Concentrate (EC)",
                        lastingIpmRule = "Lasting solution: Protects flag leaf (wheat) or ear-leaf (maize) which contributes >70% of final grain fill."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Azoxystrobin 200g/L + Cyproconazole 80g/L",
                        chemicalGroup = "FRAC 11 (QoI) + FRAC 3 (DMI)",
                        classification = "Preventive, Curative & Greening Effect",
                        targetStage = "Stages 1 to 3",
                        dosagePerKnapsack = "15ml - 20ml per 20L knapsack",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 30,
                        reiHours = 24,
                        applicationTiming = "Apply preventatively when weather forecasts indicate persistent fog or rain showers.",
                        resistanceManagement = "Rotate with SDHI (FRAC 7) fungicides; maximum 2 sprays per cropping year.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Shield", "Boots"),
                        tradeName = "Amistar Xtra 280 SC",
                        pcpbRegNo = "PCPB(CR)0819",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: Promotes prolonged greening effect and delayed leaf senescence for maximum grain weight."
                    )
                ),
                economicThreshold = "Presence of 1-3 active pustules per leaf on 5% of scouted cereal plants prior to flowering/tasseling."
            ),
            scoutChecklist = listOf(
                "Walk field in W-pattern, inspecting 30 plants at 10 pace intervals.",
                "Check lower and middle leaves, paying particular attention to leaf sheaths on wheat and mid-rib margins on maize.",
                "Rub suspected spots between thumb and index finger to verify presence of orange-red powdery urediniospores.",
                "Scout intensively following regional rust alerts issued by KALRO or Ministry of Agriculture."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Community Synchronized Planting & Closed Periods",
                    category = "Cultural & Preventative",
                    description = "Avoid staggered planting dates within agricultural zones. When fields are planted months apart, younger crops receive immense spore showers from maturing fields.",
                    recipeOrMethod = "Coordinate with neighboring cereal growers to sow within a 2-week seasonal window."
                ),
                InfonetPractice(
                    title = "Eradication of Alternate & Volunteer Grass Hosts",
                    category = "Field Hygiene",
                    description = "Rust species utilize volunteer cereals and wild grasses along water channels to bridge dry seasons.",
                    recipeOrMethod = "Slash, plow, or graze volunteer wheat/maize stubble at least 4 weeks prior to planting the main cereal crop."
                ),
                InfonetPractice(
                    title = "Balanced Soil Nutrition with Adequate Potassium",
                    category = "Soil Health & IPM",
                    description = "Excessive nitrogen fertilizer leads to thin epidermal cell walls easily punctured by rust germ tubes. Adequate potassium thickens cell walls and promotes natural phytoalexin production.",
                    recipeOrMethod = "Apply muriate of potash (MOP) or NPK fertilizers based on soil test recommendations rather than top-dressing excessive urea."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Armicarb 85 SP",
                    activeAgent = "Potassium Bicarbonate 850 g/kg",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Cereal rusts, powdery mildews, and leaf blights",
                    targetCrops = listOf("Cereals", "Maize", "Wheat", "Horticulture"),
                    modeOfAction = "Direct osmotic disruption of fungal hyphae and spores through rapid pH elevation on the leaf surface.",
                    applicationRate = "30g - 50g per 20L knapsack.",
                    safetyMrl = "Zero residue; 0 days PHI; organic approved.",
                    supplierOrDistributor = "De Sangosse / CABI BioProtection Portal",
                    compatibilityNotes = "Excellent resistance breaker; acts on physical contact."
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Tilt 250 EC",
                    activeIngredient = "Propiconazole 250 g/L",
                    pcpbRegNo = "PCPB(CR)0247",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Wheat", "Maize", "Barley"),
                    fracIracGroup = "FRAC Group 3 (DMI Triazole)",
                    knapsackDosage = "15ml - 20ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 28,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Rapid systemic movement protects flag leaves and ear structures from rust invasion."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Prosaro 250 EC",
                    activeIngredient = "Prothioconazole 125g/L + Tebuconazole 125g/L",
                    pcpbRegNo = "PCPB(CR)0982",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Wheat", "Barley", "Maize"),
                    fracIracGroup = "FRAC Group 3 (Dual Triazoles)",
                    knapsackDosage = "20ml per 20L knapsack",
                    perHaDosage = "0.75 - 1.0 L/ha",
                    phiDays = 35,
                    reiHours = 24,
                    registrant = "Bayer East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Gold-standard curative fungicide controlling multiple rust races and Fusarium ear rot."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Amistar Xtra 280 SC",
                    activeIngredient = "Azoxystrobin 200g/L + Cyproconazole 80g/L",
                    pcpbRegNo = "PCPB(CR)0819",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Wheat", "Maize", "Sorghum"),
                    fracIracGroup = "FRAC 11 + FRAC 3",
                    knapsackDosage = "15ml - 20ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 30,
                    reiHours = 24,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Combines curative stop-action with extended protective greening for heavy grain yields."
                )
            )
        ),

        // =========================================================================
        // 3. CARNATION & ROSE RUST (Uromyces dianthi / Phragmidium mucronatum)
        // CUT FLOWERS & ORNAMENTALS
        // =========================================================================
        CropThreat(
            id = "flower_rust",
            name = "Carnation & Rose Rust",
            scientificName = "Uromyces dianthi / Phragmidium mucronatum",
            type = ThreatType.DISEASE,
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetCrops = listOf("Carnations", "Roses (Cut Flowers)", "Chrysanthemums", "Summer flowers", "Ornamentals"),
            affectedParts = listOf("Leaves", "Stems", "Flower Buds", "Sepals"),
            severityLevel = "Extreme",
            summary = "Quarantine-significant fungal disease in commercial floriculture exports. Produces prominent raised cinnamon to chocolate-brown powdery pustules on foliage and stems, causing leaf curling, premature defoliation, and 100% rejection in international cut-flower auctions (e.g. Royal FloraHolland).",
            diagnosticKeys = listOf(
                "Raised, oval, dark cinnamon-brown pustules erupting through the epidermis of carnation leaves and stems.",
                "On roses, orange-yellow powdery pustules on lower leaf surfaces accompanied by purple-red spots on upper surfaces.",
                "Severely infected cut-flower leaves turn yellow, curl upward, and desiccate rapidly.",
                "Stems develop elongated rust cankers that weaken flower stalks and cause snapping during harvesting.",
                "Powdery brown spore masses rub off easily on hands or grading tables."
            ),
            favorableConditions = "Relative humidity above 80%, free water film on leaves for 4-6 hours, moderate greenhouse temperatures (16°C–22°C), and stagnant air in commercial tunnels.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Urediniospore Inoculum on Petals/Foliage",
                    scientificSub = "Greenhouse Influx",
                    iconType = StageIconType.SPORE,
                    duration = "2 – 6 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Microscopic spores enter greenhouse via air vents, clothing of pickers, or infected mother plant cuttings.",
                    symptomsToSpot = "Invisible spore landing; plants appear completely symptomless.",
                    targetAction = "Automated ventilation to eliminate leaf wetness; apply preventative contact barrier.",
                    recommendedActiveIngredients = listOf("Mancozeb 800 g/kg", "Chlorothalonil 720 g/L", "Copper Hydroxide 538 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Stomatal Entry & Sub-Cuticular Hyphae",
                    scientificSub = "Germination in Condensation",
                    iconType = StageIconType.PENETRATION,
                    duration = "6 – 10 hours",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Spore germinates in micro-droplets of evening condensation, pushing germ tubes into open stomata on carnation and rose foliage.",
                    symptomsToSpot = "Microscopic penetration inside leaves; no external blemish visible yet.",
                    targetAction = "Apply systemic triazole or strobilurin curative spray with translaminar penetration.",
                    recommendedActiveIngredients = listOf("Penconazole 100 g/L", "Boscalid + Pyraclostrobin", "Myclobutanil 200 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Mesophyll Haustoria Incubation",
                    scientificSub = "Tissue Colonization",
                    iconType = StageIconType.HYPHAE,
                    duration = "5 – 9 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Fungal mycelium colonizes the internal parenchymal cells, forming dense clusters beneath the epidermis.",
                    symptomsToSpot = "Small yellow chlorotic blisters and pale yellow pinhead specks on carnation leaves.",
                    targetAction = "Apply systemic curative fungicide to halt haustorial nutrient extraction.",
                    recommendedActiveIngredients = listOf("Azoxystrobin + Difenoconazole", "Trifloxystrobin 500 g/kg")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Pustule Eruption & Spore Release",
                    scientificSub = "Cuticle Rupture (Export Hazard)",
                    iconType = StageIconType.LESION,
                    duration = "8 – 16 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Sub-epidermal pustules swell and burst through the thick waxy cuticle, scattering millions of dry powdery spores across the greenhouse.",
                    symptomsToSpot = "Chocolate-brown to orange-brown powdery pustules on leaf undersides and flower stems.",
                    targetAction = "Rogue and incinerate infected stems; apply eradicant tank-mix to sanitize crop canopy.",
                    recommendedActiveIngredients = listOf("Boscalid 252g/kg + Pyraclostrobin 128g/kg", "Penconazole 100 g/L")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Source certified pathogen-tested, disease-free cuttings from tissue-culture nurseries.",
                    "Strict climate computer management: heat and vent greenhouse before sunset to purge humid air and prevent condensation.",
                    "Strictly utilize drip irrigation; zero overhead watering in cut flower tunnels.",
                    "Immediately rogue out infected stems into plastic bags before walking down greenhouse paths to prevent spore drift.",
                    "Sanitize cutting shears and harvesting knives in 70% alcohol or quaternary ammonium solutions."
                ),
                physicalMechanicalControls = listOf(
                    "Install horizontal air flow (HAF) circulating fans to maintain constant gentle air motion over flower canopies.",
                    "Use UV-blocking greenhouse polyethylene cladding that reduces fungal sporulation."
                ),
                biologicalControls = listOf(
                    "Foliar sprays with Potassium Bicarbonate (Armicarb / Kaligreen) to physically collapse fungal spores.",
                    "Apply Bacillus subtilis (Serenade ASO) preventatively every 7 days in alternation with synthetic fungicides.",
                    "Introduce hyperparasitic fungus Lecanicillium lecanii to parasitize rust urediniospores."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Boscalid 252g/kg + Pyraclostrobin 128g/kg",
                        chemicalGroup = "FRAC Group 7 (SDHI) + FRAC Group 11 (QoI)",
                        classification = "Translaminar & Locally Systemic Preventive/Curative",
                        targetStage = "Stages 1, 2, and 3",
                        dosagePerKnapsack = "15g - 20g per 20L knapsack",
                        dosagePerHectare = "0.8 - 1.0 kg/ha",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply at onset of rust risk or first blister detection in cut flower greenhouses.",
                        resistanceManagement = "Rotate with FRAC 3 (Triazoles) and FRAC M03. Maximum 2 sprays per flush.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Bellis 38 WG",
                        pcpbRegNo = "PCPB(CR)0891",
                        formulation = "Water Dispersible Granules (WG)",
                        lastingIpmRule = "Lasting solution: Zero residue spotting on delicate flower petals; superior two-way anti-resistance mechanism."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Penconazole 100 g/L",
                        chemicalGroup = "FRAC Group 3 (DMI Triazole)",
                        classification = "Systemic Protective & Curative",
                        targetStage = "Stages 2 and 3",
                        dosagePerKnapsack = "10ml - 15ml per 20L knapsack",
                        dosagePerHectare = "0.5 L/ha",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "Apply upon early blister detection; fast acropetal systemic translocation.",
                        resistanceManagement = "Maximum 2 applications per flush; always partner with contact multi-site fungicide.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Topas 100 EC",
                        pcpbRegNo = "PCPB(CR)0234",
                        formulation = "Emulsifiable Concentrate (EC)",
                        lastingIpmRule = "Lasting solution: Rapid curative kick-back stops sub-cuticular mycelial growth within 24 hours."
                    )
                ),
                economicThreshold = "Zero tolerance (0%) for export cut flowers. Any single pustule causes crate rejection at European borders."
            ),
            scoutChecklist = listOf(
                "Inspect 50 stems per greenhouse bay twice weekly, examining leaf undersides and stem nodes.",
                "Check lower foliage in cold corners and areas nearest ventilation inlets.",
                "Look for tiny chlorotic blisters before cuticle rupture.",
                "Record any findings in CropShield Field Notes and flag infested beds for immediate sanitation."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Greenhouse Climate & Dew-Point Management",
                    category = "Cultural & Environmental",
                    description = "Rust spores cannot germinate without a film of free water lasting at least 4 hours. Automated venting before dusk prevents the greenhouse air from dropping below the dew point.",
                    recipeOrMethod = "Program computerized climate controls to purge high humidity between 5:00 PM and 7:00 PM."
                ),
                InfonetPractice(
                    title = "Sealed Plastic Bag Rogueing Protocol",
                    category = "Sanitation",
                    description = "Rogueing infected stems without containment disperses millions of spores onto neighboring healthy flower stems.",
                    recipeOrMethod = "Carefully slip a clear plastic bag over infected stems before cutting; tie securely and incinerate outside greenhouse."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Armicarb 85 SP",
                    activeAgent = "Potassium Bicarbonate 850 g/kg",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Uromyces dianthi, powdery mildew, and rusts",
                    targetCrops = listOf("Carnations", "Roses (Cut Flowers)", "Floriculture"),
                    modeOfAction = "Osmotic pressure elevation collapses fungal cell walls on contact; neutralizes acidic microclimate needed by germ tubes.",
                    applicationRate = "30g - 40g per 20L knapsack.",
                    safetyMrl = "Exempt from MRL; zero days harvest interval; leaves zero visible petal residue.",
                    supplierOrDistributor = "De Sangosse / CABI BioProtection Portal",
                    compatibilityNotes = "Spray in late afternoon to avoid direct hot midday sun on open blooms."
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Bellis 38 WG",
                    activeIngredient = "Boscalid 252g/kg + Pyraclostrobin 128g/kg",
                    pcpbRegNo = "PCPB(CR)0891",
                    formulation = "Water Dispersible Granules (WG)",
                    targetCrops = listOf("Carnations", "Roses (Cut Flowers)", "Ornamentals"),
                    fracIracGroup = "FRAC Group 7 + FRAC Group 11",
                    knapsackDosage = "15g - 20g per 20L knapsack",
                    perHaDosage = "0.8 kg/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "BASF East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Dual MOA prevents fungal resistance while delivering spotless petals for export."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Topas 100 EC",
                    activeIngredient = "Penconazole 100 g/L",
                    pcpbRegNo = "PCPB(CR)0234",
                    formulation = "Emulsifiable Concentrate (EC)",
                    targetCrops = listOf("Carnations", "Roses", "Cut Flowers"),
                    fracIracGroup = "FRAC Group 3 (DMI Triazole)",
                    knapsackDosage = "10ml - 15ml per 20L knapsack",
                    perHaDosage = "0.5 L/ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "Syngenta East Africa Ltd",
                    lastingIpmRule = "Lasting solution: Fast curative absorption halts internal fungal haustoria formation."
                )
            )
        ),

        // =========================================================================
        // 4. DIAMONDBACK MOTH (Plutella xylostella) - VEGETABLES & BRASSICAS
        // Cabbage, Kale (Sukuma Wiki), Broccoli, Cauliflower
        // =========================================================================
        CropThreat(
            id = "diamondback_moth",
            name = "Diamondback Moth (DBM)",
            scientificName = "Plutella xylostella",
            type = ThreatType.PEST,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Cabbage", "Kale", "Broccoli", "Cauliflower", "Asian Greens"),
            affectedParts = listOf("Leaves", "Heart / Head", "Growing Point"),
            severityLevel = "Extreme",
            summary = "Worldwide #1 insect pest of crucifers. Green larvae feed voraciously on brassica leaves, producing characteristic 'window-paning' and boring into forming cabbage hearts. Has developed resistance to nearly every synthetic insecticide class when used without IPM rotation.",
            diagnosticKeys = listOf(
                "Young larvae chewing small round holes in leaf undersides, leaving the upper waxy epidermis intact ('window-paning').",
                "Older larvae skeletonizing leaves and boring deep into the central cabbage heart or broccoli curds.",
                "Pale green, spindle-shaped caterpillars wriggling backwards rapidly and dropping on a silken thread when disturbed.",
                "Delicate, loose gauze-like open-mesh white cocoons spun on leaf undersides.",
                "Small grey-brown adult moths with three distinct cream-colored diamond patterns along the back when wings are folded."
            ),
            favorableConditions = "Dry warm weather (22°C–32°C), absence of heavy downpours, continuous mono-cropping of brassicas, and overuse of broad-spectrum pyrethroids that kill natural parasitoids.",
            imageResId = null,
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Laying on Leaf Undersides",
                    scientificSub = "Oviposition",
                    iconType = StageIconType.EGG,
                    duration = "3 – 5 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Moths lay tiny oval yellowish eggs singly or in small groups of 2-8 along leaf veins.",
                    symptomsToSpot = "Tiny yellow eggs glued to leaf veins under leaves; moths fluttering at dusk.",
                    targetAction = "Apply biological ovicide or spray neem extract to deter egg laying and hatch.",
                    recommendedActiveIngredients = listOf("Neem extract (Azadirachtin)", "Petroleum spray oil")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Leaf-Mining 1st Instar Larvae",
                    scientificSub = "Internal Mesophyll Mining",
                    iconType = StageIconType.LARVA,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Newly hatched larvae bore directly inside spongy mesophyll tissue, feeding between upper and lower cuticle.",
                    symptomsToSpot = "Tiny white serpentine mines on leaf lamina.",
                    targetAction = "Apply translaminar bio-insecticide before larvae emerge onto leaf surface.",
                    recommendedActiveIngredients = listOf("Emamectin Benzoate 50 g/kg", "Chlorantraniliprole 200 g/L", "Spinetoram 120 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "External Feeding 2nd – 4th Instars",
                    scientificSub = "Window-Paning & Heart Boring",
                    iconType = StageIconType.LARVA,
                    duration = "6 – 10 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Larvae feed externally on leaf undersides, creating translucent windowpanes and boring into cabbage head.",
                    symptomsToSpot = "Window-pane holes, ragged leaf edges, green caterpillars wriggling on silken threads.",
                    targetAction = "Apply Bacillus thuringiensis or diamide insecticide; conserve Diadegma parasitoids.",
                    recommendedActiveIngredients = listOf("Bacillus thuringiensis kurstaki", "Spinetoram 120 g/L", "Flubendiamide 480 g/L")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Open Gauze Cocoon Pupation",
                    scientificSub = "Pupal Metamorphosis",
                    iconType = StageIconType.PUPA,
                    duration = "4 – 8 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Mature larva spins a loose silken netting cocoon on leaf undersides, pupating inside.",
                    symptomsToSpot = "White mesh cocoons attached to lower leaves or cabbage outer wrappers.",
                    targetAction = "Hand pick and crush cocoons during weeding; avoid spraying broad-spectrum pyrethroids.",
                    recommendedActiveIngredients = listOf("Conserve natural parasitoid wasps")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Intercrop cabbage with Indian mustard (Brassica juncea) as a trap crop (1 row mustard every 15 rows of cabbage). DBM preferentially colonizes mustard.",
                    "Plant companion crops like coriander, mint, or dill that provide floral nectar to beneficial parasitoid wasps.",
                    "Destroy and plow under all brassica crop stalks and stumps immediately following harvest.",
                    "Practice 2-year crop rotation with non-brassica crops (solanaceae, legumes, cereals).",
                    "Overhead sprinkler irrigation in early evening washes larvae off foliage and disrupts moth mating flights."
                ),
                physicalMechanicalControls = listOf(
                    "Install yellow water-pan traps or pheromone delta traps to monitor moth flight peaks.",
                    "Cover seedling nurseries with 30-mesh insect netting to prevent early egg laying."
                ),
                biologicalControls = listOf(
                    "Conserve and introduce Diadegma semiclausum parasitoid wasps (introduced by ICIPE with >80% parasitism rates).",
                    "Apply Bacillus thuringiensis subsp. kurstaki (Dipel 2X / Thuricide) at 30g/20L knapsack in late afternoon.",
                    "Spray 5% Neem Kernel Water Extract (NKWE) to disrupt larval molting through natural azadirachtin."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Spinetoram 120 g/L",
                        chemicalGroup = "IRAC Group 5 (Spinosyns)",
                        classification = "Translaminar Ingestion & Contact",
                        targetStage = "Instar 1-3 Larvae",
                        dosagePerKnapsack = "8ml - 10ml per 20L knapsack",
                        dosagePerHectare = "0.2 - 0.25 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at egg hatch or early window-paning stage; avoid spraying during bee foraging.",
                        resistanceManagement = "Strictly maximum 2 applications per crop cycle. Rotate with IRAC 28 (Diamides) or IRAC 6 (Avermectins).",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Mask", "Boots"),
                        tradeName = "Radiant 120 SC",
                        pcpbRegNo = "PCPB(CR)1045",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: Bacterial fermentation derived active ingredient; safe for Diadegma parasitoids when dry."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Chlorantraniliprole 200 g/L",
                        chemicalGroup = "IRAC Group 28 (Ryanodine Receptor Modulator)",
                        classification = "Systemic & Translaminar Ovi-Larvicide",
                        targetStage = "Eggs & Young Larvae",
                        dosagePerKnapsack = "5ml - 7.5ml per 20L knapsack",
                        dosagePerHectare = "0.15 L/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply at early vegetative stage or cupping stage.",
                        resistanceManagement = "Rotate with IRAC 5 (Spinosyns) or IRAC 15 (IGR). Do not apply consecutively.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Goggles", "Boots"),
                        tradeName = "Coragen 20 SC",
                        pcpbRegNo = "PCPB(CR)0968",
                        formulation = "Suspension Concentrate (SC)",
                        lastingIpmRule = "Lasting solution: Paralyzes larval feeding within hours; highly selective with minimal impact on beneficials."
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Emamectin Benzoate 50 g/kg",
                        chemicalGroup = "IRAC Group 6 (Glutamate-gated Chloride Channel Allosteric Modulator)",
                        classification = "Translaminar Stomach Poison",
                        targetStage = "Larval Feeding Stages",
                        dosagePerKnapsack = "10g per 20L knapsack",
                        dosagePerHectare = "0.25 kg/ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply in late afternoon when larvae are actively feeding.",
                        resistanceManagement = "Rotate MoA groups every 30 days to break DBM resistance cycles.",
                        safetyPpe = listOf("Coveralls", "Chemical Gloves", "Face Shield", "Boots"),
                        tradeName = "Proclaim 05 SG",
                        pcpbRegNo = "PCPB(CR)0724",
                        formulation = "Soluble Granule (SG)",
                        lastingIpmRule = "Lasting solution: Rapid translaminar penetration into leaf mesophyll creates reservoirs protected from rain wash."
                    )
                ),
                economicThreshold = "1-2 larvae per cabbage plant at vegetative stage; 1 larva per 5 plants at head formation stage."
            ),
            scoutChecklist = listOf(
                "Scout 20 cabbage plants diagonally across the field twice a week.",
                "Inspect both upper and lower leaf surfaces, particularly inner wrapper leaves near the growing point.",
                "Check for small translucent window-pane feeding damage.",
                "Look for green caterpillars dropping on silken threads when plant is tapped."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Indian Mustard Trap Cropping",
                    category = "Cultural & Trap Cropping",
                    description = "Indian mustard (Brassica juncea) produces higher glucosinolate concentrations that attract DBM moths preferentially away from cabbage.",
                    recipeOrMethod = "Plant 2 rows of Indian mustard around cabbage field borders 15 days before transplanting cabbage. Plant 1 row every 15 cabbage rows."
                ),
                InfonetPractice(
                    title = "Neem Kernel Water Extract (NKWE 5%)",
                    category = "Organic Botanical",
                    description = "Neem contains azadirachtin which acts as an antifeedant, insect growth regulator (IGR), and oviposition deterrent for DBM without harming Diadegma parasitoids.",
                    recipeOrMethod = "Pound 500g clean dry neem seeds into powder. Soak in 10L clean water overnight. Strain through fine mesh, add 10ml liquid soap, and spray leaf undersides thoroughly."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Dipel 2X",
                    activeAgent = "Bacillus thuringiensis subsp. kurstaki (32,000 IU/mg)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Plutella xylostella larvae, loopers, and armyworms",
                    targetCrops = listOf("Cabbage", "Kale", "Broccoli", "Brassicas"),
                    modeOfAction = "Delta-endotoxin crystals dissolve in alkaline insect midgut, paralyzing digestive tract within hours.",
                    applicationRate = "20g - 30g per 20L knapsack in late afternoon.",
                    safetyMrl = "Zero MRL; 0 days PHI; organic compliant.",
                    supplierOrDistributor = "Sumitomo Chemical / CABI BioProtection Portal",
                    compatibilityNotes = "Do not tank-mix with highly alkaline sprays (e.g. Bordeaux mixture)."
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Radiant 120 SC",
                    activeIngredient = "Spinetoram 120 g/L",
                    pcpbRegNo = "PCPB(CR)1045",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Cabbage", "Kale", "Broccoli"),
                    fracIracGroup = "IRAC Group 5 (Spinosyns)",
                    knapsackDosage = "8ml - 10ml per 20L knapsack",
                    perHaDosage = "0.2 - 0.25 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "Corteva Agriscience East Africa",
                    lastingIpmRule = "Lasting solution: Fermentation-derived active with high environmental safety and zero cross-resistance to pyrethroids."
                ),
                PcpbRegisteredChemical(
                    tradeName = "Coragen 20 SC",
                    activeIngredient = "Chlorantraniliprole 200 g/L",
                    pcpbRegNo = "PCPB(CR)0968",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Cabbage", "Brassicas", "Vegetables"),
                    fracIracGroup = "IRAC Group 28 (Diamides)",
                    knapsackDosage = "5ml - 7.5ml per 20L knapsack",
                    perHaDosage = "0.15 L/ha",
                    phiDays = 3,
                    reiHours = 12,
                    registrant = "FMC Agricultural Solutions",
                    lastingIpmRule = "Lasting solution: Binds to insect ryanodine receptors, stopping larval feeding within 2-4 hours."
                )
            )
        )
    )
}
