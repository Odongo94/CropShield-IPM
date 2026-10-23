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

/**
 * Comprehensive Catalog of Crop Pests, Invasive & Bio-Indicator Weeds,
 * and Soil Profiles & Health Conditions.
 *
 * Authored and calibrated for Kenyan and East African agro-ecological zones
 * by Lead Agronomist & Crop Scientist Odongo Vincent (0741886046 | odongovincento@gmail.com).
 * Includes full multi-stage lifecycles, crop target vulnerabilities, yield loss risks,
 * and integrated control measures (Cultural, Mechanical, Biological, Chemical, and Organic).
 */
object PestWeedSoilThreats {

    val threats: List<CropThreat> = listOf(
        // =========================================================================
        // 1. AFRICAN BOLLWORM / TOMATO FRUITWORM (Helicoverpa armigera) - PEST
        // =========================================================================
        CropThreat(
            id = "african_bollworm",
            name = "African Bollworm / Tomato Fruitworm",
            scientificName = "Helicoverpa armigera (Hübner)",
            type = ThreatType.PEST,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Tomato", "French Beans", "Maize", "Pepper", "Cotton", "Pigeon Peas", "Sorghum"),
            affectedParts = listOf("Fruit", "Flowers/Buds", "Leaves", "Stem"),
            severityLevel = "Extreme",
            summary = "Polyphagous pest capable of inflicting 60–90% yield destruction on tomatoes and legumes. Larvae bore directly into green and ripening fruit, leaving conspicuous circular holes surrounded by wet frass that trigger secondary fungal and bacterial fruit rots.",
            diagnosticKeys = listOf(
                "Circular entry holes (2–5mm) bored into fruits, often near the calyx.",
                "Caterpillar feeding with head buried deep inside the fruit while body remains outside.",
                "Aborted floral buds and perforated bean pods with consumed seeds inside.",
                "Caterpillar body with distinct longitudinal pale and dark stripes and sparse tubercles."
            ),
            favorableConditions = "Warm dry periods (24°C–32°C), flowering and fruit-setting stages with abundant nectar and tender bracts.",
            imageResId = null,
            vulnerableCropStages = "Floral bud initiation through early fruit development (V-Flowering to Green Fruit)",
            yieldLossPotential = "50% – 90% direct marketable fruit loss",
            economicThresholdInfo = "Tomato: 1 larva or egg per 10 plants; French Beans: 5% flower/pod infestation",
            mechanicalControls = listOf(
                "Hand-pick early instar caterpillars and punctured fruits during morning scouting.",
                "Install yellow funnel pheromone traps (Helilure) at 4–5 traps/ha to monitor moth flights.",
                "Deep post-harvest tillage to expose subterranean pupae to predatory birds and solar heat."
            ),
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Oviposition & Spherical Eggs",
                    scientificSub = "Dome-shaped Ribbed Eggs",
                    iconType = StageIconType.EGG,
                    duration = "2 – 4 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Female moth deposits 500–1,500 yellow-white dome-shaped eggs singly on young leaves, flower buds, and bracts.",
                    symptomsToSpot = "Minute yellowish pins (0.5mm) glued singly on upper foliage and sepals.",
                    targetAction = "Apply ovicidal biorational sprays or release egg parasitoids.",
                    recommendedActiveIngredients = listOf("Trichogramma chilonis", "Chlorantraniliprole 200 g/L", "Azadirachtin (Neem 1%)")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Young Instars (L1–L2) Foliar Feeding",
                    scientificSub = "Free-living Early Caterpillar",
                    iconType = StageIconType.LARVA,
                    duration = "4 – 6 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Newly hatched larvae graze externally on tender foliage and floral petals before boring into fruits.",
                    symptomsToSpot = "Fine pinhole feeding on leaves and floral bracts; fragile green larvae (2–8mm).",
                    targetAction = "Primary chemical and biological knockdown window before larvae enter fruit interior.",
                    recommendedActiveIngredients = listOf("Bacillus thuringiensis (Bt)", "Emamectin benzoate 50 g/kg", "Spinetoram 120 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Voracious Boring Instars (L3–L6)",
                    scientificSub = "Endophagous Destructive Stage",
                    iconType = StageIconType.LARVA,
                    duration = "10 – 14 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Caterpillars grow up to 40mm, boring deep cavities into fruits. Highly protected from non-translaminar contact sprays.",
                    symptomsToSpot = "Deep gouged fruit holes with dark frass; foul-smelling secondary soft rot.",
                    targetAction = "Translaminar diamide insecticides or systemic feeding blockers.",
                    recommendedActiveIngredients = listOf("Chlorantraniliprole + Thiamethoxam", "Indoxacarb 150 g/L", "Flubendiamide 480 g/L")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Subterranean Pupation",
                    scientificSub = "Soil Diapause Phase",
                    iconType = StageIconType.PUPA,
                    duration = "10 – 16 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Mature larva drops from the plant and burrows 2–8cm into soil to construct an earthen cell for pupation.",
                    symptomsToSpot = "Dropping larvae around plant base; mahogany brown smooth pupae in soil.",
                    targetAction = "Inter-row hoeing, flooding, or soil application of entomopathogenic nematodes.",
                    recommendedActiveIngredients = listOf("Heterorhabditis bacteriophora", "Deep Soil Cultivation")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Nocturnal Adult Moth",
                    scientificSub = "Dispersal & Mating Adult",
                    iconType = StageIconType.ADULT,
                    duration = "8 – 14 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Stout brown/ochre moth with dark kidney-shaped spot on forewing. Highly migratory and strong flyer.",
                    symptomsToSpot = "Moths flying at dusk around flowering crop canopy; attraction to light traps.",
                    targetAction = "Pheromone mass trapping and companion flowering borders.",
                    recommendedActiveIngredients = listOf("Helilure Pheromone Traps", "Light Traps", "African Marigold Border")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Intercrop tomatoes with maize or African marigold (Tagetes erecta) as oviposition trap crops.",
                    "Maintain 3-year crop rotation with non-host cereals or alliums.",
                    "Remove and destroy crop residues immediately after harvest to prevent pupal overwintering."
                ),
                physicalMechanicalControls = listOf(
                    "Install 4–6 pheromone lure traps per hectare for early detection and mating disruption.",
                    "Hand-collect punctured fruits during weekly harvest cycles and incinerate.",
                    "Shallow weeding to desiccate exposed pupae under direct solar radiation."
                ),
                biologicalControls = listOf(
                    "Release Trichogramma wasps (50,000 parasitoids/ha) upon first moth trap catch.",
                    "Spray Bacillus thuringiensis var. kurstaki (Bt) in late afternoons (avoid UV degradation).",
                    "Conserve predatory Anthocorid pirate bugs and lacewing larvae in field margins."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Chlorantraniliprole 200 g/L (Coragen 20 SC)",
                        chemicalGroup = "IRAC Group 28 (Ryanodine Receptor Modulators)",
                        classification = "Translaminar / Systemic Ovi-larvicide",
                        targetStage = "Eggs and L1–L2 Instars",
                        dosagePerKnapsack = "4ml in 20L knapsack",
                        dosagePerHectare = "100ml / ha",
                        phiDays = 1,
                        reiHours = 12,
                        applicationTiming = "At floral bud break or upon observing first eggs on sepals",
                        resistanceManagement = "Maximum 2 applications per crop cycle; alternate with Indoxacarb or Emamectin",
                        safetyPpe = listOf("Chemical resistant nitrile gloves", "Eye protection mask", "Overall boots")
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Emamectin Benzoate 50 g/kg (Prove 5 WDG)",
                        chemicalGroup = "IRAC Group 6 (Glutamate-gated Chloride Channel Allosteric Modulators)",
                        classification = "Translaminar Stomach & Contact Action",
                        targetStage = "L1–L3 Young Feeding Larvae",
                        dosagePerKnapsack = "8g in 20L knapsack",
                        dosagePerHectare = "200g / ha",
                        phiDays = 3,
                        reiHours = 12,
                        applicationTiming = "Apply in late evening when young caterpillars graze actively",
                        resistanceManagement = "Rotate strictly with IRAC 28 or IRAC 22A",
                        safetyPpe = listOf("Nitrile gloves", "Face shield", "Waterproof apron")
                    )
                ),
                economicThreshold = "Spray when 1 or more eggs/larvae are observed per 10 inspected plants or 5% fruit puncture incidence."
            ),
            scoutChecklist = listOf(
                "Inspect 30 plants randomly along a zig-zag route across the block.",
                "Scrutinize flower clusters, developing green calyxes, and underside of top leaves.",
                "Check for fresh entry holes with moist frass around fruit stems.",
                "Check pheromone delta traps weekly; if catches exceed 5 moths/trap/night, initiate intervention."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Neem Seed Kernel Extract (NSKE 5%) Anti-feedant Spray",
                    category = "Botanical Biopesticide",
                    description = "Crushed neem seed azadirachtin prevents larval ecdysis (moulting) and deters adult egg laying.",
                    recipeOrMethod = "Pound 500g dry neem seeds, soak in 10L clean water with 15ml soap for 12 hours. Spray foliar canopy weekly."
                ),
                InfonetPractice(
                    title = "African Marigold (Tagetes minuta) Companion Trap Crop",
                    category = "Cultural Trap Cropping",
                    description = "Plant dense marigold strips around tomato borders; adults preferentially lay eggs on marigold flowers, protecting main tomatoes."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Dipel DF",
                    activeAgent = "Bacillus thuringiensis subsp. kurstaki (Bt)",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Helicoverpa armigera / Fruitworm Larvae",
                    targetCrops = listOf("Tomato", "French Beans", "Vegetables"),
                    modeOfAction = "Endotoxin crystals dissolve gut wall of feeding larvae within 24–48 hours.",
                    applicationRate = "20g per 20L knapsack",
                    supplierOrDistributor = "Philagro / CABI BioProtection Portal"
                ),
                CabiBioProduct(
                    tradeName = "Trichogramma Wasps",
                    activeAgent = "Trichogramma evanescens",
                    type = BioProtectionType.MACROBIAL_PREDATOR,
                    targetOrganism = "Helicoverpa eggs",
                    targetCrops = listOf("Tomato", "French Beans", "Horticulture"),
                    modeOfAction = "Microscopic parasitoid wasp deposits its eggs inside bollworm eggs, killing embryo before hatch.",
                    applicationRate = "50,000 parasitoids/ha card release",
                    supplierOrDistributor = "Koppert / Real IPM Kenya"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Coragen 20 SC",
                    activeIngredient = "Chlorantraniliprole 200 g/L",
                    pcpbRegNo = "PCPB(CR)1034",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Tomato", "French Beans", "Maize"),
                    fracIracGroup = "IRAC Group 28 (Diamide)",
                    knapsackDosage = "4ml per 20L knapsack",
                    perHaDosage = "100ml / ha",
                    phiDays = 1,
                    reiHours = 12,
                    registrant = "FMC Agricultural Solutions Kenya"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Steward 150 SC",
                    activeIngredient = "Indoxacarb 150 g/L",
                    pcpbRegNo = "PCPB(CR)0645",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Tomato", "Vegetables", "French Beans"),
                    fracIracGroup = "IRAC Group 22A (Oxadiazine)",
                    knapsackDosage = "10ml per 20L knapsack",
                    perHaDosage = "250ml / ha",
                    phiDays = 3,
                    reiHours = 24,
                    registrant = "UPL East Africa"
                )
            )
        ),

        // =========================================================================
        // 2. MAIZE STEM BORER (Busseola fusca & Chilo partellus) - PEST
        // =========================================================================
        CropThreat(
            id = "maize_stem_borer",
            name = "Maize Stem Borer",
            scientificName = "Busseola fusca (African Stem Borer) / Chilo partellus (Spotted)",
            type = ThreatType.PEST,
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetCrops = listOf("Maize", "Sorghum", "Pearl Millet", "Sugarcane"),
            affectedParts = listOf("Stem", "Whorl", "Leaves"),
            severityLevel = "High",
            summary = "Endemic stalk boring caterpillars causing severe 'deadheart' in vegetative cereals and stem lodging during tasseling and cob fill. Early instars feed on young whorl leaves before boring into internal pith tissues where they disrupt vascular nutrient transport.",
            diagnosticKeys = listOf(
                "Deadheart symptom: Central whorl shoot withers, turns brown, and pulls out easily with foul rotting odor.",
                "Horizontal rows of circular 'window' holes across expanded maize leaves (pinhole lines).",
                "Stem entrance/exit holes surrounded by coarse brown sawdust-like frass.",
                "Stems lodging or breaking cleanly at lower nodes under moderate wind pressure."
            ),
            favorableConditions = "Continuous cereal monoculture, unburied maize stover from prior season, warm sub-humid altitudes (1,200m–2,200m).",
            imageResId = null,
            vulnerableCropStages = "Early vegetative whorl stage (2–6 weeks after emergence; V3–V8)",
            yieldLossPotential = "30% – 70% grain yield reduction",
            economicThresholdInfo = "10% of plants showing visible pinholes or leaf windowing in young whorl",
            mechanicalControls = listOf(
                "Slash maize stalks at ground level and incorporate or burn stubble before new season planting.",
                "Pinch out deadheart stalks during vegetative weeding to eliminate larvae before stem entry.",
                "Intercrop with Desmodium under the Push-Pull agronomic configuration."
            ),
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Egg Batches Behind Leaf Sheath",
                    scientificSub = "Oviposition Columns",
                    iconType = StageIconType.EGG,
                    duration = "5 – 8 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Female moth inserts strings of 30–100 flattened hemispherical eggs between the stem and leaf sheath.",
                    symptomsToSpot = "Yellowish egg rows concealed beneath leaf sheaths near lower nodes.",
                    targetAction = "Apply systemic or biological sprays before hatch or release Cotesia parasitoids.",
                    recommendedActiveIngredients = listOf("Cotesia sesamiae", "Chlorantraniliprole", "Neem Seed Powder")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Young Whorl-Feeding Larvae",
                    scientificSub = "Pre-Boring Windowing Stage",
                    iconType = StageIconType.LARVA,
                    duration = "5 – 10 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Newly emerged larvae migrate up into the vegetative funnel (whorl) to feed on tender unfurled leaves.",
                    symptomsToSpot = "Window-paning and linear pinholes on freshly unfolding leaf blades.",
                    targetAction = "Primary intervention window: Place granules or spray directly into the whorl cup.",
                    recommendedActiveIngredients = listOf("Spinetoram", "Bt var. kurstaki", "Emamectin Benzoate", "Fine Sand + Ash (Infonet)")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Stem Penetration & Internal Tunneling",
                    scientificSub = "Endophagous Vascular Boring",
                    iconType = StageIconType.LARVA,
                    duration = "20 – 35 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Larvae tunnel through the hard rind into internal stalk pith, excavating vertical tunnels.",
                    symptomsToSpot = "Stem boreholes with extruded frass pellets; stunted internode extension; deadheart.",
                    targetAction = "Vascular systemic insecticides; physical destruction of infested stems.",
                    recommendedActiveIngredients = listOf("Chlorantraniliprole 200 g/L", "Rogue out Deadheart plants")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Stalk Pupation",
                    scientificSub = "Internal Stem Cocoon",
                    iconType = StageIconType.PUPA,
                    duration = "7 – 14 days",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Mature larva cuts an exit window in the stem wall and pupates within the tunnel.",
                    symptomsToSpot = "Thin translucent circular window in outer stem bark; brown cylindrical pupa inside.",
                    targetAction = "Post-harvest stover chopping and feeding to livestock or deep plowing.",
                    recommendedActiveIngredients = listOf("Stover shredding", "Livestock silage incorporation")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Nocturnal Adult Stem Borer Moth",
                    scientificSub = "Dispersal & Oviposition",
                    iconType = StageIconType.ADULT,
                    duration = "5 – 10 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Pale yellowish-brown night-flying moth. Lacks mouthparts and does not feed on crops.",
                    symptomsToSpot = "Moths resting on lower stems during daylight; active flutter around light sources.",
                    targetAction = "Push-Pull Napier grass border trap cropping to attract and trap egg-laying moths.",
                    recommendedActiveIngredients = listOf("Push-Pull Napier / Brachiaria border", "Light Traps")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Adopt Push-Pull technology: Intercrop maize with Silverleaf Desmodium (push) and plant Napier/Brachiaria grass border (pull).",
                    "Early and synchronized community planting at onset of rainy season to escape high second-generation moth flights.",
                    "Proper balanced nitrogen and phosphate basal fertilization to accelerate early vegetative whorl escape."
                ),
                physicalMechanicalControls = listOf(
                    "Hand-apply a pinch of dry wood ash mixed with fine sand (1:1 ratio) directly into the whorl cup.",
                    "Post-harvest stalk destruction and stover clearing before next rains.",
                    "Destroy volunteer cereal hosts and wild sorghum grass along boundaries."
                ),
                biologicalControls = listOf(
                    "Conserve and augment Cotesia sesamiae, an indigenous larval parasitoid wasp that attacks B. fusca inside stems.",
                    "Apply granulosis virus or Bacillus thuringiensis kurstaki sprays into leaf whorls.",
                    "Encourage predator earwigs (Forficula spp.) and black ants that hunt eggs behind leaf sheaths."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Flubendiamide 480 g/L (Belt 480 SC)",
                        chemicalGroup = "IRAC Group 28 (Ryanodine Receptor Modulator)",
                        classification = "Systemic / Translaminar Larvicide",
                        targetStage = "L1–L2 Early Whorl Larvae",
                        dosagePerKnapsack = "5ml in 20L knapsack",
                        dosagePerHectare = "100ml / ha",
                        phiDays = 14,
                        reiHours = 12,
                        applicationTiming = "At 2–4 weeks after germination when first pinhole windows appear",
                        resistanceManagement = "Limit to 1 application per crop season; rotate with IRAC 6 or IRAC 4A",
                        safetyPpe = listOf("Nitrile gloves", "Dust mask", "Boots")
                    )
                ),
                economicThreshold = "Treat when 10% of inspected plants in the field exhibit visible windowing or pinholes."
            ),
            scoutChecklist = listOf(
                "Scout 20 consecutive maize plants at 5 random spots in the shamba starting at 3 weeks post-emergence.",
                "Examine the central leaf whorl for pinhole perforations and frass sawdust.",
                "Gently tug central unfurled leaf; if it slips out rot-scented, deadheart is confirmed.",
                "Log infestation incidence into Field Scouting Logs for threshold evaluation."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Push-Pull Technology with Desmodium & Napier Grass",
                    category = "Agronomic Push-Pull System",
                    description = "Infonet flagship method developed by ICIPE: Intercropped Desmodium produces volatile sesquiterpenes that repel stem borer moths ('push'), while surrounding Napier grass emits attractive volatiles ('pull') that trap moths, whose larvae are killed by gummy Napier sap."
                ),
                InfonetPractice(
                    title = "Wood Ash & Crushed Chili Whorl Powder",
                    category = "Organic Physical Barrier",
                    description = "Mix 2 parts fine sieved wood ash with 1 part powdered chili and 1 part sand. Drop 1 teaspoon into each whorl cup. Causes severe larval desiccation and irritation."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Cotesia Biocontrol",
                    activeAgent = "Cotesia sesamiae (Braconid Parasitoid Wasp)",
                    type = BioProtectionType.MACROBIAL_PREDATOR,
                    targetOrganism = "Busseola fusca larvae",
                    targetCrops = listOf("Maize", "Sorghum"),
                    modeOfAction = "Female wasp injects eggs into borer larvae; emerging parasitoid grubs consume host internally.",
                    applicationRate = "Field establishment / boundary conservation",
                    supplierOrDistributor = "ICIPE / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Belt 480 SC",
                    activeIngredient = "Flubendiamide 480 g/L",
                    pcpbRegNo = "PCPB(CR)0912",
                    formulation = "Suspension Concentrate (SC)",
                    targetCrops = listOf("Maize", "Sorghum", "Vegetables"),
                    fracIracGroup = "IRAC Group 28",
                    knapsackDosage = "5ml per 20L knapsack",
                    perHaDosage = "100ml / ha",
                    phiDays = 14,
                    reiHours = 12,
                    registrant = "Bayer East Africa Ltd"
                )
            )
        ),

        // =========================================================================
        // 3. WITCHWEED (Striga hermonthica) - INVASIVE PARASITIC WEED
        // =========================================================================
        CropThreat(
            id = "weed_striga",
            name = "Witchweed (Striga)",
            scientificName = "Striga hermonthica (Delile) Benth.",
            type = ThreatType.WEED,
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetCrops = listOf("Maize", "Sorghum", "Pearl Millet", "Sugarcane", "Upland Rice"),
            affectedParts = listOf("Root Zone", "Stem", "Nutrient Reserves"),
            severityLevel = "Extreme",
            summary = "Obligate hemi-parasitic weed attaching to cereal roots via subterranean haustoria. Injects phytotoxins and robs host crop of water, minerals, and carbohydrates long before it emerges above ground. Serves as a primary edaphic bio-indicator of severely degraded, nitrogen-deficient, low-organic matter soils in Western Kenya and Lake Victoria Basin.",
            diagnosticKeys = listOf(
                "Bright pink-to-purple tubular bilabiate flowers on slender hairy green stems (30–100cm).",
                "Severe stunting, chlorosis, and drought-like scorching of maize host despite adequate rainfall.",
                "Subterranean bell-shaped haustorial attachments connecting Striga roots to cereal roots.",
                "Prolific seed production: up to 50,000 microscopic dust-like seeds per single plant."
            ),
            favorableConditions = "Low soil nitrogen (<0.15%), depleted soil organic carbon (<1.2%), continuous cereal monoculture, warm soils (25°C–35°C).",
            imageResId = null,
            vulnerableCropStages = "Early crop establishment through vegetative elongation (Germination to V6)",
            yieldLossPotential = "40% – 100% complete cereal failure",
            economicThresholdInfo = "Zero tolerance: Any Striga presence (>0 spikes/ha) warrants immediate hand-pulling before seed set",
            mechanicalControls = listOf(
                "Hand-pulling and rogueing Striga flowering spikes before seed capsules mature (destroy by burning).",
                "Deep moldboard plowing to bury dormant seed bank deeper than 25cm (inhibits strigolactone-triggered germination).",
                "False seedbed preparation: Induce suicidal germination with synthetic strigolactone stimulants before planting."
            ),
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Seed Dormancy & After-Ripening",
                    scientificSub = "Soil Seed Bank Longevity",
                    iconType = StageIconType.SEED_BANK,
                    duration = "Up to 15 – 20 years",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Microscopic dust-like seeds remain viable in dry soil for up to two decades, conditioned by warm moist soil.",
                    symptomsToSpot = "Invisible in soil; massive seed reservoir (millions of seeds/m² in endemic shambas).",
                    targetAction = "Suicidal trap cropping with non-host legumes (Soybean, Cotton, Desmodium).",
                    recommendedActiveIngredients = listOf("Desmodium uncinatum", "Soybean / Cowpea rotational trap crop")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Strigolactone-Triggered Germination",
                    scientificSub = "Host Root Exudate Chemotropism",
                    iconType = StageIconType.GERMINATION_EMERGENCE,
                    duration = "24 – 48 hours",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Seed germinates only within 2–3mm of host root in response to strigolactones secreted by nitrogen-stressed roots.",
                    symptomsToSpot = "Radicle elongates toward root; plant completely subterranean and invisible.",
                    targetAction = "Apply high basal Nitrogen (CAN/Urea) or Imazapethyr-coated IR maize seed.",
                    recommendedActiveIngredients = listOf("Urea 46% N / CAN 26% N", "Imazapyr (IR-Maize StrigAway Seed Coat)")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Haustorial Penetration & Parasitism",
                    scientificSub = "Subterranean Vascular Robbery",
                    iconType = StageIconType.VASCULAR_COLONIZATION,
                    duration = "4 – 7 weeks",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Haustorium penetrates host xylem. Striga siphons 70–80% of host water and nutrients while injecting growth-stunting phytotoxins.",
                    symptomsToSpot = "Maize leaves curl, wilt, and yellow with scorch marks as if suffering severe drought.",
                    targetAction = "Post-emergence directed herbicide or Desmodium root exudate allelochemical inhibition.",
                    recommendedActiveIngredients = listOf("Desmodium uncinatum intercrop", "2,4-D Amine (directed root-zone)")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Aboveground Emergence & Flowering",
                    scientificSub = "Inflorescence & Floral Bloom",
                    iconType = StageIconType.FLOWERING_SEED_SET,
                    duration = "2 – 4 weeks",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Bright pink/purple spikes erupt around host root crowns and initiate flowering within 2 weeks of emergence.",
                    symptomsToSpot = "Pink/purple flowering spikes (30–60cm) clustered at maize stalk bases.",
                    targetAction = "Immediate manual rogueing before seed pod capsule formation.",
                    recommendedActiveIngredients = listOf("Hand-pulling & burning", "2,4-D Amine 720 SL foliar wipe")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Capsule Dehiscence & Seed Rain",
                    scientificSub = "Dissemination & Seed Rain",
                    iconType = StageIconType.PERENNATION_DISPERSAL,
                    duration = "2 – 3 weeks",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Bimodal capsules mature and explode, shedding tens of thousands of microscopic seeds that contaminate field tools and livestock feet.",
                    symptomsToSpot = "Black dried seed pods shedding microscopic black seed dust upon touching.",
                    targetAction = "Strict quarantine of contaminated tools, burning of harvested stover.",
                    recommendedActiveIngredients = listOf("Stover incineration", "Sanitary machinery wash")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Integrate Push-Pull technology: Intercrop Silverleaf Desmodium (Desmodium uncinatum) in every alternate row with maize.",
                    "Heavily incorporate 5–8 t/ha well-rotted farmyard cattle manure or compost to suppress strigolactone root emissions.",
                    "Apply adequate nitrogen fertilizer (topdress 75–100 kg/ha CAN) to suppress host root strigolactone signals.",
                    "Practice 2-year rotation with non-host trap legumes (Soybean, Desmodium, Cowpea, Groundnut)."
                ),
                physicalMechanicalControls = listOf(
                    "Rigorous hand-pulling of flowering spikes every 14 days before capsules set seed; pile and burn away from field.",
                    "Clean hoes, tractor discs, and walking boots between plots to prevent moving seed-contaminated soil."
                ),
                biologicalControls = listOf(
                    "Desmodium uncinatum root exudates (di-C-glycosylflavones) act as allelopathic suicidal germination triggers that arrest radicle development.",
                    "Conserve Smicronyx weevils (Smicronyx guineanus), whose grubs form galls inside Striga flower buds and destroy seed development.",
                    "Fusarium oxysporum f. sp. strigae (FOS) bioherbicide isolates for targeted root-zone inoculations."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "2,4-D Amine 720 g/L (Agro-D-Amine)",
                        chemicalGroup = "HRAC Group 4 (Synthetic Auxins)",
                        classification = "Selective Post-Emergence Systemic Herbicide",
                        targetStage = "Young Vegetative & Flowering Striga Spikes",
                        dosagePerKnapsack = "50ml in 20L knapsack",
                        dosagePerHectare = "1.5 L/ha",
                        phiDays = 30,
                        reiHours = 24,
                        applicationTiming = "Apply as directed spot spray onto emerged Striga spikes before capsules set seed",
                        resistanceManagement = "Shield maize foliage to prevent epinastic leaf curl; do not spray onto intercropped legumes",
                        safetyPpe = listOf("Nitrile chemical gloves", "Respirator mask", "Goggles", "Rubber boots")
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Imazapyr (IR-Maize StrigAway Seed Dressing)",
                        chemicalGroup = "HRAC Group 2 (ALS Inhibitors)",
                        classification = "Herbicide-Coated Resistance Seed System",
                        targetStage = "Subterranean Seed Germination & Haustoria",
                        dosagePerKnapsack = "Pre-coated seed technology",
                        dosagePerHectare = "Commercial seed pack",
                        phiDays = 60,
                        reiHours = 24,
                        applicationTiming = "At planting: Herbicide dissolves around seed and kills attaching Striga radicles",
                        resistanceManagement = "Plant certified IR-maize varieties only; rotate with legumes",
                        safetyPpe = listOf("Gloves when handling treated seed")
                    )
                ),
                economicThreshold = "Economic threshold is strictly zero: Even 1 single spike per hectare must be rogued before flowering."
            ),
            scoutChecklist = listOf(
                "Walk maize rows weekly beginning at 4 weeks post-germination.",
                "Look for unexplained stunting, pale leaf veins, and drought symptoms in wet soil.",
                "Inspect ground surface within 30cm radius of maize stalks for pink/purple flower spikes.",
                "Immediately yank out emerged stems below the ground surface and deposit into a collection sack."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Push-Pull with Desmodium uncinatum",
                    category = "Biochemical Allelopathy",
                    description = "Infonet flagship agro-ecological protocol: Desmodium roots exude specialized flavonoids that stimulate suicidal Striga germination without providing an attachment host, clearing the soil seed bank by 80–90% within 2 seasons."
                ),
                InfonetPractice(
                    title = "Heavy Manuring & High Nitrogen Topdressing",
                    category = "Soil Health Suppression",
                    description = "Striga is exclusively adapted to nitrogen-depleted, leached soils. Applying 5–10 t/ha farmyard manure and 100 kg/ha CAN shifts the biochemical balance, dramatically suppressing host root strigolactones."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Desmodium Root Allelochemical System",
                    activeAgent = "Desmodium uncinatum Seed Inoculant",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Striga hermonthica / Striga asiatica",
                    targetCrops = listOf("Maize", "Sorghum", "Cereals"),
                    modeOfAction = "Exudate isoschaftoside causes suicidal Striga seed germination and post-germination radicle abort.",
                    applicationRate = "5 kg/ha intercropped seed",
                    supplierOrDistributor = "ICIPE / Simlaw Seeds / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Agro-D-Amine 720 SL",
                    activeIngredient = "2,4-D Amine 720 g/L",
                    pcpbRegNo = "PCPB(CR)0188",
                    formulation = "Soluble Liquid (SL)",
                    targetCrops = listOf("Maize", "Sugarcane", "Wheat"),
                    fracIracGroup = "HRAC Group 4",
                    knapsackDosage = "50ml per 20L knapsack",
                    perHaDosage = "1.5 L/ha",
                    phiDays = 30,
                    reiHours = 24,
                    registrant = "Agrochemicals Association of Kenya"
                )
            )
        ),

        // =========================================================================
        // 4. PURPLE NUTSEDGE (Cyperus rotundus) - PERENNIAL SEDGE & HARDPAN INDICATOR
        // =========================================================================
        CropThreat(
            id = "weed_purple_nutsedge",
            name = "Purple Nutsedge (Ndago)",
            scientificName = "Cyperus rotundus L.",
            type = ThreatType.WEED,
            cropCategory = CropCategory.VEGETABLES,
            targetCrops = listOf("Tomato", "Potato", "French Beans", "Cabbage", "Maize", "Cut Flowers", "Onion"),
            affectedParts = listOf("Root Zone", "Vegetative Canopy", "Nutrient Reserves"),
            severityLevel = "High",
            summary = "Ranked among the world's most pernicious agricultural weeds. Possesses triangular stems and an intricate subterranean network of tubers and basal bulbs capable of penetrating tomato and potato tubers. Serves as a primary edaphic bio-indicator of heavy, waterlogged subsoil with an impenetrable plowpan compaction layer.",
            diagnosticKeys = listOf(
                "Solid triangular (3-angled) stems without nodes.",
                "Reddish-purple to dark brown umbrella-like spikelet inflorescence.",
                "Extensive chain of bitter, aromatic underground tubers (nuts) connected by thin wiry rhizomes.",
                "Sharp leaf shoots capable of piercing plastic mulch and potato seed tubers."
            ),
            favorableConditions = "Compacted heavy soils, seasonal waterlogging or perched water tables, open sunlight, frequent shallow cultivation.",
            imageResId = null,
            vulnerableCropStages = "Crop emergence and early canopy establishment (Days 1–35; Critical Period of Weed Control)",
            yieldLossPotential = "30% – 60% yield loss; severe tuber piercing in root crops",
            economicThresholdInfo = "Dense patches (>15 shoots/m²) severely depress seedling emergence",
            mechanicalControls = listOf(
                "Deep subsoil ripping and chisel plowing in dry season to unearth tubers and expose them to solar baking.",
                "Do NOT use rotary tillers: Rototilling cuts tubers into hundreds of pieces, multiplying shoot emergence.",
                "Opaque UV-stabilized heavy black silage plastic solarization for 6–8 weeks in warm sun."
            ),
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Dormant Subterranean Tubers",
                    scientificSub = "Underground Nut Tuber Chain",
                    iconType = StageIconType.SEED_BANK,
                    duration = "Perennial (Months to Years)",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Chain of apical-dominant tubers stores carbohydrates at 5–25cm depth. Highly tolerant to drought and flooding.",
                    symptomsToSpot = "Hard dark brown to black fragrant tubers unearthed during hoeing.",
                    targetAction = "Deep dry-season subsoiling or translocated systemic tuber-killing herbicides.",
                    recommendedActiveIngredients = listOf("Glyphosate 480 SL (pre-plant)", "Halosulfuron-methyl 75 WG")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Rhizome Sprouting & Shoot Emergence",
                    scientificSub = "Basal Bulb & Foliar Spurt",
                    iconType = StageIconType.GERMINATION_EMERGENCE,
                    duration = "4 – 7 days",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Tuber breaks dormancy and produces an ascending wiry rhizome that forms a basal bulb at the soil surface.",
                    symptomsToSpot = "Needle-sharp green spears puncturing the soil surface and plastic mulch.",
                    targetAction = "Apply selective post-emergence ALS-inhibitor herbicide before 5-leaf stage.",
                    recommendedActiveIngredients = listOf("Halosulfuron-methyl 75 WG", "Bentazone 480 g/L")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Rapid Tillering & Foliar Rosette",
                    scientificSub = "Vegetative Canopy & Pith",
                    iconType = StageIconType.VEGETATIVE_ROSETTE,
                    duration = "14 – 21 days",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Shoot expands into glossy dark-green leaves arranged in 3 ranks around a triangular stem.",
                    symptomsToSpot = "Dense green carpet of triangular grassy leaves outcompeting crop seedlings.",
                    targetAction = "Post-emergence translocated herbicide or smother cover crop canopy.",
                    recommendedActiveIngredients = listOf("Halosulfuron-methyl", "Shading with Mucuna / Sweet potato")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Inflorescence & Secondary Tuber Chain",
                    scientificSub = "Purple Spikelet Umbel",
                    iconType = StageIconType.FLOWERING_SEED_SET,
                    duration = "20 – 35 days",
                    vulnerability = VulnerabilityLevel.MODERATE,
                    description = "Terminal umbel produces reddish-purple spikelets. Concurrently, basal bulb sends horizontal rhizomes forming new daughter tubers.",
                    symptomsToSpot = "Purple flower umbrellas on stiff triangular stalks; new tubers forming under soil.",
                    targetAction = "Mow or spray before daughter tubers become independent.",
                    recommendedActiveIngredients = listOf("Halosulfuron-methyl + Surfactant", "Slashing before flowering")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Break subsoil plowpan with deep ripper to improve internal soil drainage and eliminate waterlogged conditions favored by sedges.",
                    "Plant aggressive smother cover crops like Velvet bean (Mucuna pruriens) or Lablab to block light; nutsedge is highly shade intolerant.",
                    "Rotate with sweet potatoes, which form a dense opaque canopy that starves subterranean nutsedge tubers."
                ),
                physicalMechanicalControls = listOf(
                    "Deep chisel plowing during dry season (January–February): Exposes tubers to direct scorching sunlight; 10 days of dry heat kills >80% of tubers.",
                    "Avoid rototilling, which slices rhizomes into thousands of viable propagating units.",
                    "Install heavy woven weed-control fabric in perennial nursery beds and greenhouse alleys."
                ),
                biologicalControls = listOf(
                    "Bactra verutana (Tortricid moth) larvae bore into nutsedge shoots and feed on basal bulb tissues.",
                    "Puccinia canaliculata rust fungus induces foliar desiccation and reduces tuber viability."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Halosulfuron-methyl 750 g/kg (Sempra 75 WG)",
                        chemicalGroup = "HRAC Group 2 (Sulfonylureas / ALS Inhibitor)",
                        classification = "Selective Systemic Post-Emergence Herbicide",
                        targetStage = "3–5 Leaf Emerged Nutsedge Shoots",
                        dosagePerKnapsack = "2.5g in 20L knapsack + Non-ionic surfactant",
                        dosagePerHectare = "50g / ha",
                        phiDays = 30,
                        reiHours = 12,
                        applicationTiming = "Apply when nutsedge is actively growing with 3–5 leaves before flowering",
                        resistanceManagement = "Translocates into rhizome chains to kill daughter tubers; do not cultivate for 7 days post-spray",
                        safetyPpe = listOf("Nitrile gloves", "Goggles", "Coveralls")
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Bentazone 480 g/L (Basagran 480 SL)",
                        chemicalGroup = "HRAC Group 6 (PS II Inhibitors)",
                        classification = "Selective Contact Herbicide for Beans & Cereals",
                        targetStage = "Young Vegetative Nutsedge (under 10cm height)",
                        dosagePerKnapsack = "50ml in 20L knapsack",
                        dosagePerHectare = "2.0 L/ha",
                        phiDays = 21,
                        reiHours = 12,
                        applicationTiming = "Apply in warm bright sunlight for rapid photosynthetic contact burn",
                        resistanceManagement = "Safe in French beans, dry beans, peas, and maize; does not leave soil residue",
                        safetyPpe = listOf("Chemical gloves", "Protective eyewear")
                    )
                ),
                economicThreshold = "Initiate control as soon as green shoots emerge; tubers proliferate exponentially once canopy closes."
            ),
            scoutChecklist = listOf(
                "Inspect lower field contours, water-collecting depressions, and furrow bottoms.",
                "Dig 15cm down in suspected patches to examine tuber chains and soil plowpan hardness.",
                "Differentiate from true grasses by feeling stem: 'Sedges have edges, grasses have nodes'.",
                "Map infested zones for targeted dry-season ripping and post-emergence spot treatment."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Dry Season Deep Solarization & Plowing",
                    category = "Physical Tuber Desiccation",
                    description = "Infonet cultural recommendation: Plow soil deep (25cm) at the peak of the dry season. Break clods and leave exposed to scorching tropical sun for 14 days without irrigation. Nutsedge tubers lose viability when moisture drops below 15%."
                ),
                InfonetPractice(
                    title = "Aggressive Smother Cropping with Mucuna pruriens",
                    category = "Biological Shading",
                    description = "Nutsedge is a C4 plant that requires intense direct sunlight. Planting a dense stand of Mucuna creates 100% shade within 6 weeks, causing root tubers to starve and exhaust their starch reserves."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Cover Crop Seed Biological Suppressors",
                    activeAgent = "Mucuna pruriens (Velvet Bean)",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Cyperus rotundus / Cyperus esculentus",
                    targetCrops = listOf("All Crops", "Fallow Fields", "Vegetable Rotations"),
                    modeOfAction = "Dense foliar canopy cuts photosynthetically active radiation by 95%, exhausting subterranean tuber chains.",
                    applicationRate = "50 kg/ha drilled seed",
                    supplierOrDistributor = "Kenya Seed Company / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Sempra 75 WG",
                    activeIngredient = "Halosulfuron-methyl 750 g/kg",
                    pcpbRegNo = "PCPB(CR)1182",
                    formulation = "Water Dispersible Granule (WG)",
                    targetCrops = listOf("Maize", "Sugarcane", "Tomato", "French Beans"),
                    fracIracGroup = "HRAC Group 2",
                    knapsackDosage = "2.5g per 20L knapsack",
                    perHaDosage = "50g / ha",
                    phiDays = 30,
                    reiHours = 12,
                    registrant = "Gowan East Africa / Arysta"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Basagran 480 SL",
                    activeIngredient = "Bentazone 480 g/L",
                    pcpbRegNo = "PCPB(CR)0244",
                    formulation = "Soluble Concentrate (SL)",
                    targetCrops = listOf("Beans", "French Beans", "Peas", "Maize"),
                    fracIracGroup = "HRAC Group 6",
                    knapsackDosage = "50ml per 20L knapsack",
                    perHaDosage = "2.0 L/ha",
                    phiDays = 21,
                    reiHours = 12,
                    registrant = "BASF East Africa Ltd"
                )
            )
        ),

        // =========================================================================
        // 5. HUMIC NITISOL (Red Volcanic Soil) - SOIL PROFILE & EDAPHIC DIAGNOSIS
        // =========================================================================
        CropThreat(
            id = "soil_humic_nitisol",
            name = "Humic Nitisol (Red Volcanic Soil)",
            scientificName = "Humic Nitisol / Dystric Nitosol (WRB Soil Taxonomy)",
            type = ThreatType.SOIL,
            cropCategory = CropCategory.ALL,
            targetCrops = listOf("Tea", "Coffee", "Maize", "Potato", "Tomato", "Pyrethrum", "Cabbage", "Cut Flowers (Roses)"),
            affectedParts = listOf("Root Infiltration Zone", "Subsoil (15-40cm)", "Topsoil (0-15cm)"),
            severityLevel = "Moderate",
            summary = "World-renowned deep, dusky red tropical soils derived from volcanic rocks across Mt. Kenya foothills, Aberdares, Kisii, and Central Rift. Highly aggregated with shiny nut-shaped peds (nitic horizon). Highly fertile physically, but chemically vulnerable to severe phosphorus fixation and continuous acidification from ammoniacal fertilizers (DAP/Urea), releasing toxic exchangeable Aluminum (Al3+) when pH drops below 5.2.",
            diagnosticKeys = listOf(
                "Uniform deep dusky-red to dark reddish-brown color extending beyond 150cm without abrupt textural changes.",
                "Strongly aggregated subsoil with shiny, polyhedral nut-shaped peds ('nitic' structure).",
                "High clay content (often >60%), yet feels friable and crumbly like loam due to iron-oxide cementing.",
                "Indicator weeds under low pH: Bracken Fern (Pteridium aquilinum), Oxalis latifolia, and Sheep Sorrel."
            ),
            favorableConditions = "High rainfall highlands (1,200mm–2,200mm), humid cool-to-warm temperatures, undulating volcanic topography.",
            imageResId = null,
            vulnerableCropStages = "Basal planting & early root elongation: Seedling phosphorus uptake is severely locked by iron/aluminum oxides",
            yieldLossPotential = "30% – 60% crop yield penalty if phosphorus fixation and acidity are uncorrected",
            economicThresholdInfo = "Soil pH (water) < 5.4 or Exchangeable Acidity (Al3+ + H+) > 1.5 cmol/kg requires agricultural liming",
            mechanicalControls = listOf(
                "Maintain contour vegetative grass strips (Vetiver / Napier) to protect topsoil from sheet wash on slopes.",
                "Shallow minimum tillage or ripping to protect delicate, crumbly nitic aggregates from pulverization.",
                "Mulch with crop residues to maintain high organic matter and buffer soil surface temperatures."
            ),
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Pristine Volcanic Parent Material",
                    scientificSub = "Weathering of Tertiary Volcanics",
                    iconType = StageIconType.SOIL_BASELINE,
                    duration = "Pedogenic timescale (Centuries)",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Volcanic basalts and phonolites weather under high rainfall into kaolinite clay and iron/aluminum oxyhydroxides.",
                    symptomsToSpot = "Deep porous red profile, high natural infiltration, excellent water-holding capacity.",
                    targetAction = "Baseline soil testing: Measure pH, CEC, available P (Mehlich-3), and Exchangeable Acidity.",
                    recommendedActiveIngredients = listOf("Soil Testing Mehlich-3", "Baseline Soil Organic Matter conservation")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Phosphorus Fixation & Acid Leaching",
                    scientificSub = "Al3+ Toxicity & P-Latching",
                    iconType = StageIconType.SOIL_DEGRADATION,
                    duration = "3 – 5 cropping seasons",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Free iron and aluminum oxides adsorb soluble phosphate anions into insoluble Al-PO4 precipitates. Soluble Ca and Mg leach away.",
                    symptomsToSpot = "Maize seedlings purple-tinged (severe P-starvation); roots stubby and clubbed from Al toxicity.",
                    targetAction = "Apply Agricultural Lime (CaCO3) and banded Minjingu Rock Phosphate.",
                    recommendedActiveIngredients = listOf("Agricultural Lime (CaCO3) @ 2.5 t/ha", "Minjingu Hyper Phosphate (P2O5 28% + 38% CaO)")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Severe Acidification & Base Depletion",
                    scientificSub = "Acid Crash (pH < 4.8)",
                    iconType = StageIconType.SOIL_DEGRADATION,
                    duration = "5 – 10 continuous seasons with DAP",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Prolonged DAP/Urea use consumes buffering carbonates. Exchangeable Al3+ occupies >30% of CEC sites, poisoning crop root tips.",
                    symptomsToSpot = "Bracken fern and Oxalis dominating; complete failure of legumes to nodulate; stunted yellowing crops.",
                    targetAction = "Emergency broadcast liming and heavy compost/manure incorporation.",
                    recommendedActiveIngredients = listOf("Calcitic / Dolomitic Lime (CaCO3/MgCO3)", "Farmyard Manure 8-10 t/ha")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Remediation & Lime Neutralization",
                    scientificSub = "Cation Saturation Balancing",
                    iconType = StageIconType.REMEDIATION_AMENDMENT,
                    duration = "6 – 12 weeks post-application",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Calcium ions from lime replace toxic Al3+ on clay micelle surfaces; aluminum precipitates as harmless Al(OH)3 hydroxide.",
                    symptomsToSpot = "Soil pH rises to 5.8–6.5; vigorous root branching; nodulation restored in beans.",
                    targetAction = "Band balanced compound NPK fertilizers with sulfur, zinc, and boron.",
                    recommendedActiveIngredients = listOf("Mavuno Planting NPK 10:26:10", "YaraMila Power NPK 14:14:24", "CAN Topdress")
                ),
                LifecycleStage(
                    stageNumber = 5,
                    title = "Restored Highly Productive Soil Ecosystem",
                    scientificSub = "Optimal Edaphic Equilibrium",
                    iconType = StageIconType.REHABILITATED_FERTILE,
                    duration = "Sustainable with annual maintenance",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Crumb structure stabilized by organic glues; active earthworm biopores; optimal microbial nutrient mineralization.",
                    symptomsToSpot = "Dark chocolate-red crumbly tilth; crops achieve full genetic yield potential (60+ bags maize/ha).",
                    targetAction = "Annual maintenance lime (200 kg/ha) and crop residue recycling.",
                    recommendedActiveIngredients = listOf("Crop residue mulching", "Balanced NPK + micro-nutrients")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Broadcast Agricultural Lime (CaCO3) at 2.0–2.5 t/ha every 3 years if soil pH is below 5.4.",
                    "Switch from acidifying Diammonium Phosphate (DAP 18:46:0) to non-acidifying or buffering fertilizers: Mavuno Planting, Minjingu Hyper Phosphate, or NPK 17:17:17.",
                    "Incorporate 5–8 t/ha well-rotted farmyard cattle manure to chelate free aluminum ions and buffer soil pH.",
                    "Rotate maize with climbing beans or Desmodium to biological fix atmospheric nitrogen."
                ),
                physicalMechanicalControls = listOf(
                    "Construct contour Fanya Juu terraces on slopes exceeding 8% to prevent torrential sheet erosion of fertile topsoil.",
                    "Mulch rows with maize stover or grass clippings to maintain soil organic carbon above 2.5%."
                ),
                biologicalControls = listOf(
                    "Inoculate legume seeds with Rhizobium leguminosarum (Biofix) to restore biological nitrogen fixation.",
                    "Apply Arbuscular Mycorrhizal Fungi (AMF) to enhance plant root reach for fixed phosphorus."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Agricultural Limestone (CaCO3 90%+ Neutralizing Value)",
                        chemicalGroup = "Mineral Soil Amendment (Liming Agent)",
                        classification = "Soil Acidity Neutralizer & Calcium Supplier",
                        targetStage = "Pre-Planting Soil Preparation (4–6 weeks before rains)",
                        dosagePerKnapsack = "Soil broadcast: 100g – 150g per m²",
                        dosagePerHectare = "2,000 kg – 2,500 kg / ha",
                        phiDays = 0,
                        reiHours = 0,
                        applicationTiming = "Broadcast evenly across ploughed shamba and harrow into top 15cm at least 1 month before planting",
                        resistanceManagement = "Retest soil every 3 years; maintain pH between 5.8 and 6.5",
                        safetyPpe = listOf("Dust mask", "Safety goggles", "Work gloves")
                    ),
                    ChemicalRecommendation(
                        activeIngredient = "Minjingu Rock Phosphate (P2O5 28% + 38% CaO + Micro-nutrients)",
                        chemicalGroup = "Natural Reactive Phosphate Rock",
                        classification = "Basal Fertilizer & Slow-Release Liming Agent",
                        targetStage = "Basal Placement at Planting Hole",
                        dosagePerKnapsack = "Basal placement: 1 bottle top (50g) per planting hole",
                        dosagePerHectare = "150 kg – 200 kg / ha",
                        phiDays = 0,
                        reiHours = 0,
                        applicationTiming = "Place in planting hole and mix with soil and manure before dropping seed",
                        resistanceManagement = "Ideal for acidic Nitisols: Acidity gently dissolves rock phosphate, releasing P while neutralizing acid with 38% CaO",
                        safetyPpe = listOf("Dust mask", "Gloves")
                    )
                ),
                economicThreshold = "Liming is economically mandatory whenever soil pH falls below 5.2 or exchangeable aluminum exceeds 20% of CEC."
            ),
            scoutChecklist = listOf(
                "Sample topsoil (0–20cm) across 15 zig-zag points in the field; composite and test pH.",
                "Inspect seedling leaves for purple pigmentation indicating severe phosphorus fixation.",
                "Look for presence of Bracken Fern or Oxalis indicating extreme acidity (pH < 5.0).",
                "Dig a 50cm inspection pit to verify nut-shaped aggregate structure and absence of plowpan."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Agricultural Liming with Local Calcitic Lime",
                    category = "Edaphic Acidity Correction",
                    description = "Infonet recommended soil rehabilitation: Broadcast 20–25 bags (50kg each) of fine agricultural lime per acre. Incorporate thoroughly 4 weeks before the onset of the long rains. Neutralizes aluminum toxicity and restores microbial nitrogen mineralization."
                ),
                InfonetPractice(
                    title = "Composting & Cattle Manure Humus Building",
                    category = "Soil Organic Carbon",
                    description = "Apply 2 large wheelbarrows of mature thermophilic compost per 10m² every season. Humic and fulvic acids bond with iron and aluminum, physically shielding phosphate molecules from fixation."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Biofix Rhizobium Inoculant",
                    activeAgent = "Rhizobium leguminosarum / phaseoli",
                    type = BioProtectionType.MICROBIAL,
                    targetOrganism = "Atmospheric Nitrogen Fixation",
                    targetCrops = listOf("Common Beans", "French Beans", "Soybeans", "Cowpeas"),
                    modeOfAction = "Symbiotic root nodules fix 50–100 kg N/ha in lime-corrected Nitisols.",
                    applicationRate = "100g packet per 15kg seed",
                    supplierOrDistributor = "MEA Fertilizers / University of Nairobi"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Athiriver Agricultural Lime",
                    activeIngredient = "Calcium Carbonate (CaCO3 > 92%)",
                    pcpbRegNo = "KE-FERT-LIME-001",
                    formulation = "Fine Milled Powder (<250 micron)",
                    targetCrops = listOf("All Crops", "Maize", "Tea", "Coffee", "Vegetables"),
                    fracIracGroup = "Soil Amendment",
                    knapsackDosage = "N/A (Soil applied: 2 t/ha)",
                    perHaDosage = "2,000 kg / ha",
                    phiDays = 0,
                    reiHours = 0,
                    registrant = "Athi River Mining Kenya Ltd"
                ),
                PcpbRegisteredChemical(
                    tradeName = "Mavuno Planting NPK 10:26:10 + Micro",
                    activeIngredient = "NPK 10:26:10 + 4S + 8CaO + 4MgO + 0.1B + 0.5Zn",
                    pcpbRegNo = "KE-FERT-NPK-0192",
                    formulation = "Granular Basal Blend",
                    targetCrops = listOf("Maize", "Potato", "Tomato", "Wheat"),
                    fracIracGroup = "Compound Soil Fertilizer",
                    knapsackDosage = "1 bottle top per planting hole (185 kg/ha)",
                    perHaDosage = "185 kg / ha",
                    phiDays = 0,
                    reiHours = 0,
                    registrant = "ARM Energy / Mavuno Fertilizers"
                )
            )
        ),

        // =========================================================================
        // 6. PELLIC VERTISOL (Black Cotton Soil) - SOIL PROFILE & WATERLOGGING
        // =========================================================================
        CropThreat(
            id = "soil_pellic_vertisol",
            name = "Pellic Vertisol (Black Cotton Soil)",
            scientificName = "Pellic Vertisol (Swell-Shrink Smectite Clay)",
            type = ThreatType.SOIL,
            cropCategory = CropCategory.ALL,
            targetCrops = listOf("Cotton", "Rice", "Sugarcane", "Sorghum", "Chickpeas", "Wheat", "Sunflower"),
            affectedParts = listOf("Root Infiltration Zone", "Subsoil (15-40cm)", "Topsoil (0-15cm)"),
            severityLevel = "High",
            summary = "Heavy, dark grey to jet black cracking clay soils dominated by 2:1 expanding montmorillonite/smectite clays. Found in Athi Plains, Mwea, Kano Plains, and semi-arid lowlands. Characterized by spectacular swelling when wet (plastic, sticky, impermeable) and deep wide cracking when dry (gilgai micro-relief). High CEC and natural base fertility, but extremely narrow workable moisture window and severe hazard of root asphyxiation from waterlogging.",
            diagnosticKeys = listOf(
                "Deep, wide vertical fissures and cracks (up to 5cm wide and 1m deep) during dry season.",
                "Extreme stickiness and plasticity when wet; adheres heavily to hoes and boots.",
                "Shiny polished grooved surfaces on subsoil peds known as 'slickensides'.",
                "Ponded surface water that refuses to infiltrate for days after heavy downpours.",
                "Indicator weed: Purple Nutsedge (Cyperus rotundus) and dense sedges."
            ),
            favorableConditions = "Flat to gently undulating plains, alternating wet and dry seasons, poorly drained depressions.",
            imageResId = null,
            vulnerableCropStages = "Germination and seedling emergence: Seeds easily rot or seedlings asphyxiate under stagnant water",
            yieldLossPotential = "40% – 80% loss in non-tolerant crops due to waterlogging, damping-off, and zinc lockup",
            economicThresholdInfo = "Water ponding exceeding 24 hours warrants broadbed and furrow (BBF) or cambered bed drainage",
            mechanicalControls = listOf(
                "Construct Broadbed and Furrow (BBF) systems (1.5m wide raised beds with 30cm drainage furrows).",
                "Deep ripping or chisel plowing when soil is friable (neither soaking wet nor bone dry).",
                "Laser land leveling or surface drainage cutoff drains to evacuate excess runoff."
            ),
            lifecycleStages = listOf(
                LifecycleStage(
                    stageNumber = 1,
                    title = "Dry Season Shrinkage & Deep Cracking",
                    scientificSub = "Self-Mulching Desiccation Phase",
                    iconType = StageIconType.SOIL_BASELINE,
                    duration = "2 – 4 months (Dry spell)",
                    vulnerability = VulnerabilityLevel.LOW,
                    description = "Loss of water molecules between smectite clay sheets causes massive volume shrinkage, forming 1m deep fissures.",
                    symptomsToSpot = "Cracked honeycomb ground surface; dry topsoil crumb falling into deep cracks (self-swallowing).",
                    targetAction = "Primary land preparation window: Chisel plow or form raised Broadbeds and Furrows (BBF).",
                    recommendedActiveIngredients = listOf("Dry Chisel Plowing", "Broadbed & Furrow Maker (BBF)")
                ),
                LifecycleStage(
                    stageNumber = 2,
                    title = "Monsoon Inundation & Smectite Swelling",
                    scientificSub = "Plastic Impermeable Stage",
                    iconType = StageIconType.CRUSTING_EROSION,
                    duration = "4 – 8 weeks during heavy rains",
                    vulnerability = VulnerabilityLevel.CRITICAL,
                    description = "Rain slakes surface aggregates. Clays swell up to 30% by volume, shutting all cracks and making the soil entirely impermeable.",
                    symptomsToSpot = "Water ponds on surface; sticky mud balls on tractor tires; anaerobic grey-black odor.",
                    targetAction = "Evacuate standing water via furrow network; avoid heavy machinery to prevent compaction.",
                    recommendedActiveIngredients = listOf("Surface Drainage Furrows", "Avoid heavy tractor traffic")
                ),
                LifecycleStage(
                    stageNumber = 3,
                    title = "Root Hypoxia & Seedling Asphyxiation",
                    scientificSub = "Anaerobic Denitrification & Fe/Zn Lock",
                    iconType = StageIconType.SOIL_DEGRADATION,
                    duration = "1 – 3 weeks post-flooding",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Pore spaces remain 100% waterlogged. Oxygen depletion kills root hairs; nitrates undergo rapid denitrification into gas.",
                    symptomsToSpot = "Maize/bean seedlings turn pale yellow and cease growth; roots turn black and rot.",
                    targetAction = "Foliar zinc and nitrogen feeding to bypass waterlogged roots; dig out field drains.",
                    recommendedActiveIngredients = listOf("Foliar Zinc Chelate (Zn-EDTA)", "Foliar Urea 1% spray")
                ),
                LifecycleStage(
                    stageNumber = 4,
                    title = "Drainage Remediation & Gypsum Amelioration",
                    scientificSub = "Flocculation & Porosity Restructuring",
                    iconType = StageIconType.REMEDIATION_AMENDMENT,
                    duration = "Ongoing management",
                    vulnerability = VulnerabilityLevel.HIGH,
                    description = "Calcium from Agricultural Gypsum (CaSO4.2H2O) replaces sodium and promotes clay flocculation, opening internal pore networks.",
                    symptomsToSpot = "Water drains twice as fast; soil develops loose workable crumb texture; no surface crusting.",
                    targetAction = "Broadcast Gypsum @ 1.5–2.0 t/ha and incorporate coarse crop stover.",
                    recommendedActiveIngredients = listOf("Agricultural Gypsum (CaSO4.2H2O) @ 1.5 t/ha", "Coarse Cereal Stover Mulch")
                )
            ),
            ipmPlan = IpmPlan(
                culturalControls = listOf(
                    "Adopt Broadbed and Furrow (BBF) technology: Plant crops on raised 1.5m wide beds while excess water drains down side furrows.",
                    "Plant waterlogging-tolerant crops: Rice, Cotton, Chickpeas, Sorghum, or Sugarcane rather than sensitive Solanaceae.",
                    "Apply Agricultural Gypsum (CaSO4.2H2O) at 1.5–2.0 t/ha to flocculate heavy clays and improve internal drainage.",
                    "Supply zinc via basal fertilizer or foliar spray; Vertisols are notoriously prone to severe zinc deficiency."
                ),
                physicalMechanicalControls = listOf(
                    "Work the soil only during the brief 'friable window'—working while wet causes irreversible puddling and compaction.",
                    "Construct cutoff drains and grassed waterways to divert upland runoff away from Vertisol flats."
                ),
                biologicalControls = listOf(
                    "Incorporate high-biomass leguminous green manures (Crotalaria / Sesbania) to add stable coarse organic fibers."
                ),
                chemicalControls = listOf(
                    ChemicalRecommendation(
                        activeIngredient = "Agricultural Gypsum (Calcium Sulfate Dihydrate CaSO4·2H2O)",
                        chemicalGroup = "Mineral Soil Flocculant & Calcium/Sulfur Supplier",
                        classification = "Soil Structure Restorer & Sodic Soil Reclaimer",
                        targetStage = "Dry Season Land Preparation",
                        dosagePerKnapsack = "Soil broadcast: 150g per m²",
                        dosagePerHectare = "1,500 kg – 2,000 kg / ha",
                        phiDays = 0,
                        reiHours = 0,
                        applicationTiming = "Broadcast evenly across dry cracked beds and lightly disc before the rains",
                        resistanceManagement = "Calcium flocculates swollen clay platelets into stable crumb aggregates without increasing pH",
                        safetyPpe = listOf("Dust mask", "Gloves", "Safety boots")
                    )
                ),
                economicThreshold = "Raised beds are mandatory on all Vertisols receiving >700mm annual rainfall to avoid complete crop drowning."
            ),
            scoutChecklist = listOf(
                "Observe crack width during dry periods (cracks >2cm wide and >50cm deep confirm Vertisol classification).",
                "Conduct ribbon test: Moistened soil rolls into a long flexible shiny ribbon exceeding 5cm (confirms >50% smectite clay).",
                "Measure ponding duration after 25mm rain; standing water >12 hours indicates urgent need for Broadbed & Furrow profiling."
            ),
            infonetPractices = listOf(
                InfonetPractice(
                    title = "Broadbed and Furrow (BBF) System for Vertisols",
                    category = "Soil & Water Engineering",
                    description = "Infonet proven soil engineering technique: Form raised beds 150cm wide with 30cm wide, 15cm deep furrows between them. Seeds are sown on the elevated bed where roots remain aerated, while excess rain drains safely down the furrows into farm ponds."
                )
            ),
            cabiBioProtections = listOf(
                CabiBioProduct(
                    tradeName = "Vertisol Cover Crop Restructurer",
                    activeAgent = "Sesbania sesban (Indigenous Agroforestry Shrub)",
                    type = BioProtectionType.NATURAL_SUBSTANCE,
                    targetOrganism = "Soil Drainage & Root Penetration",
                    targetCrops = listOf("Vertisols", "Black Cotton Soils", "Cotton / Sorghum Rotations"),
                    modeOfAction = "Deep woody taproots penetrate cracking Vertisol subsoil, leaving large permanent vertical biopores that accelerate water infiltration.",
                    applicationRate = "Boundary / Fallow hedgerow",
                    supplierOrDistributor = "ICRAF / KEFRI / CABI BioProtection Portal"
                )
            ),
            pcpbChemicals = listOf(
                PcpbRegisteredChemical(
                    tradeName = "Agricultural Gypsum Fertilizer Grade",
                    activeIngredient = "Calcium Sulfate (CaSO4·2H2O > 85%, Ca 22%, S 18%)",
                    pcpbRegNo = "KE-FERT-GYP-004",
                    formulation = "Granular Soil Conditioner",
                    targetCrops = listOf("All Crops", "Cotton", "Sugarcane", "Rice", "Cereals"),
                    fracIracGroup = "Soil Conditioner",
                    knapsackDosage = "N/A (Soil applied: 1.5 t/ha)",
                    perHaDosage = "1,500 kg / ha",
                    phiDays = 0,
                    reiHours = 0,
                    registrant = "East Africa Mining & Chemical Supplies"
                )
            )
        )
    )
}
