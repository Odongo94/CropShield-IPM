package com.example.data.science

import com.example.model.science.*

object AgriculturalSciencesCatalog {

    // =========================================================================
    // 1. SOIL SCIENCE & EDAPHOLOGY PILLAR
    // =========================================================================
    val soilSciencePillar = SoilSciencePillar(
        kenyanSoilTaxonomy = listOf(
            KenyanSoilTypeInfo(
                name = "Nitisols (Deep Red Volcanic Clay Soils)",
                distribution = "Central Highlands (Kiambu, Murang'a, Nyeri), Kericho, Kisii, Meru, Mt. Kenya & Aberdare foothills",
                physicalCharacteristics = "Deep (>150 cm), dusky red to dark reddish-brown, friable clay with shiny ped faces ('nuciform' nut-like structure). Outstanding porosity, root permeability, and moisture-holding capacity.",
                fertilityAndChemistry = "Naturally high fertility, but strongly acidic (pH 4.8 – 5.8) due to continuous high rainfall leaching basic cations. High free iron (Fe) and aluminum (Al) oxides cause severe phosphorus fixation (fixation rates up to 80% of applied P).",
                managementAdvice = "1. Apply Agricultural Lime (CaCO3) @ 2.5 t/ha every 3 seasons to raise pH > 5.8. 2. Band placement of phosphorus fertilizers (prefer buffered rock phosphates like Minjingu or coated MAP/TSP). 3. Avoid straight DAP or Urea without calcium buffering."
            ),
            KenyanSoilTypeInfo(
                name = "Vertisols (Black Cotton Soils / Grumusols)",
                distribution = "Kano Plains (Kisumu), Athi River / Kapiti Plains, Mwea (Kirinyaga), Trans-Mara, Lower Machakos",
                physicalCharacteristics = "Heavy dark clay (>30% smectite / montmorillonite expanding clay). Swells massively and becomes sticky and impassable when wet; shrinks and forms wide, deep vertical fissures (cracks 5–10 cm wide, 1 m deep) when dry.",
                fertilityAndChemistry = "High chemical fertility: Neutral to alkaline pH (7.2 – 8.5), high Cation Exchange Capacity (CEC > 40 cmol/kg), high exchangeable Calcium and Magnesium. However, available Nitrogen and Phosphorus are low. Prone to sodicity and drainage asphyxiation.",
                managementAdvice = "1. Construct Cambered Beds (1.0–1.2 m wide raised beds) with deep furrow drains. 2. Deep subsoiling during the dry season. 3. Apply Gypsum (CaSO4) @ 2.0 t/ha to displace exchangeable sodium and improve soil aggregation. 4. Farm machinery must strictly stay off wet fields to prevent fatal soil compaction."
            ),
            KenyanSoilTypeInfo(
                name = "Ferralsols (Highly Weathered Red & Yellow Oxisols)",
                distribution = "Western Kenya (Kakamega, Bungoma, Busia, Siaya), Coastal hinterland (Kwale, Kilifi)",
                physicalCharacteristics = "Highly weathered, strongly leached tropical soils dominated by 1:1 kaolinite clay and iron/aluminum sesquioxides. Reddish-yellow coloration with crumbly topsoil but poor aggregate stability when de-vegetated.",
                fertilityAndChemistry = "Low effective Cation Exchange Capacity (ECEC < 10 cmol/kg). Low organic carbon, depleted base saturation, poor nutrient buffering reserves. Vulnerable to micronutrient deficiencies (Boron, Zinc).",
                managementAdvice = "1. Intensive organic matter addition (incorporate 8–10 t/ha farmyard manure or compost). 2. Split application of mineral fertilizers (never apply all Nitrogen at once, as sandy-clay matrix allows rapid leaching). 3. Implement Push-Pull with Desmodium to fix atmospheric nitrogen and suppress Striga."
            ),
            KenyanSoilTypeInfo(
                name = "Andosols (Young Volcanic Ash Soils)",
                distribution = "Rift Valley floor and escarpments (Naivasha, Nakuru, Nyandarua, Kinangop, Timau)",
                physicalCharacteristics = "Formed from recent volcanic ejecta and tephra. Very low bulk density (<0.9 g/cm³), highly porous, feels fluffy and greasy when moist. Exceptionally high water-holding capacity.",
                fertilityAndChemistry = "Extremely rich in organic matter (SOM > 6%), but dominated by allophane and imogolite amorphous minerals that bind phosphates with tremendous energy (P-fixation > 85%). pH ranges from 5.2 to 6.4.",
                managementAdvice = "1. Band phosphate fertilizer close to planting seed row; broadcast P is virtually wasted. 2. Use mycorrhizal fungi inoculants to assist root phosphate mobilization. 3. Conserve topsoil against wind erosion in dry, windy Rift Valley seasons."
            ),
            KenyanSoilTypeInfo(
                name = "Cambisols & Luvisols (Transition Medium Soils)",
                distribution = "Eastern Mid-Altitude (Machakos, Embu, Kitui, Makueni, Tharaka Nithi)",
                physicalCharacteristics = "Brownish loam to sandy clay loam with subsurface horizon showing moderate pedogenetic alteration. Susceptible to severe rain-beating surface crusting (capping) and high runoff velocity.",
                fertilityAndChemistry = "Moderate base saturation; moderate pH (6.0 – 7.2). Nitrogen and phosphorus are limiting factors. High vulnerability to moisture stress during erratic rain seasons.",
                managementAdvice = "1. Continuous organic mulching to break raindrop kinetic energy and prevent capping. 2. Implement tied ridges, zai pits, and contour stone lines. 3. Minimum tillage with subsoiler/ripper."
            )
        ),
        chemicalParameters = listOf(
            SoilChemistryParam(
                parameterName = "Soil pH (1:2.5 H2O scale)",
                optimalRange = "pH 6.0 – 6.8 (Cereals & Legumes), pH 5.5 – 6.5 (Potato), pH 4.5 – 5.5 (Tea)",
                agriculturalSignificance = "Controls nutrient solubility and soil microbial vitality. Below pH 5.5, aluminum and manganese become toxic, while phosphorus, calcium, magnesium, and molybdenum become locked. Above pH 7.8, phosphorus, iron, zinc, and manganese precipitate.",
                deficiencyCorrection = "For Acid soils: Apply Agricultural Lime (CaCO3) @ 2.0 – 3.5 t/ha or Dolomitic Lime. For Alkaline soils: Apply agricultural gypsum (CaSO4) and elemental sulfur."
            ),
            SoilChemistryParam(
                parameterName = "Cation Exchange Capacity (CEC)",
                optimalRange = "15 – 30 cmol(+)/kg soil",
                agriculturalSignificance = "Measures the soil's capacity to hold and exchange vital positively charged cations (Ca2+, Mg2+, K+, NH4+). Soils with low CEC (<10) cannot hold nutrients and suffer rapid leaching; soils with high CEC hold large nutrient reserves.",
                deficiencyCorrection = "Incorporate well-cured compost, animal manure, biochar, and green manure residues to build permanent humus complexes."
            ),
            SoilChemistryParam(
                parameterName = "Base Saturation & Ca:Mg:K Ratio",
                optimalRange = "Ca 65% – 75%, Mg 10% – 15%, K 3% – 5%, Na < 3%",
                agriculturalSignificance = "An optimal balance between basic cations prevents nutrient antagonism. Excessive potassium inhibits magnesium uptake; excessive sodium destroys soil flocculation and causes dispersion.",
                deficiencyCorrection = "Apply Dolomitic Lime if Mg is low; apply Gypsum if Na is above 5%; apply Potassium Sulfate (SOP) or Muriate of Potash (MOP) if K is deficient."
            ),
            SoilChemistryParam(
                parameterName = "Soil Organic Carbon (SOC)",
                optimalRange = "2.0% – 4.0% SOC (> 3.5% Soil Organic Matter)",
                agriculturalSignificance = "The foundation of biological soil health. Humus holds 4 to 6 times its weight in water, mineralizes 20–40 kg N/ha/year, buffers pH, and provides energy for beneficial soil mycorrhizae and bacteria.",
                deficiencyCorrection = "Implement cover cropping (Velvet bean, Desmodium, Lablab), retain crop stover (avoid burning), and apply 5–10 t/ha farmyard manure."
            )
        ),
        limeRequirementGuidelines = "Lime Requirement Equation: Lime (tonnes/ha) = (Target pH - Current pH) × Buffer Capacity Factor (1.8 for Sandy Loam, 2.8 for Clay Loam, 3.8 for Heavy Clay) × Purity Factor (100 / % CCE). Apply agricultural lime at least 4 weeks before sowing, broadcasting evenly and incorporating into the top 15 cm of soil."
    )

    // =========================================================================
    // 2. CROP PATHOLOGY PILLAR
    // =========================================================================
    val cropPathologyPillar = CropPathologyPillar(
        diseaseTriangleConcept = "The Plant Disease Triangle dictates that infectious disease can only develop when three fundamental conditions coincide simultaneously: 1. A Susceptible Host Plant (e.g. non-resistant Shangi potato), 2. A Virulent Pathogen (e.g. Phytophthora infestans sporangia), and 3. A Conducive Environment (e.g. >90% Relative Humidity, free leaf moisture for >6 hours, temperatures 12–22°C). Breaking any single corner of this triangle halts the epidemic.",
        pathogenClasses = listOf(
            PathogenClassInfo(
                classification = "Oomycetes (Water Molds / Stramenopiles)",
                keyExamples = "Phytophthora infestans (Late Blight of Potato & Tomato), Pythium spp. (Damping Off), Pseudoperonospora cubensis (Downy Mildew of Cucurbits)",
                transmissionVectors = "Asexual sporangia spread by wind and rain splash. Under wet/cold conditions, sporangia release 6–16 motile biflagellate zoospores that swim through water films to penetrate leaf stomata.",
                chemicalAndBiologicalControls = "Preventative: Mancozeb 800 g/kg (FRAC M03), Copper Hydroxide (FRAC M01). Curative / Systemic: Metalaxyl-M + Mancozeb (Ridomil Gold, FRAC 4), Cymoxanil (FRAC 27), Dimethomorph (FRAC 40). Biological: Trichoderma harzianum root drench."
            ),
            PathogenClassInfo(
                classification = "Ascomycetes & Deuteromycetes (Sac Fungi & Hyphomycetes)",
                keyExamples = "Alternaria solani (Early Blight / Target Spot), Septoria lycopersici, Colletotrichum spp. (Anthracnose of Beans, Avocado, Mango), Fusarium oxysporum (Vascular Wilt)",
                transmissionVectors = "Airborne and water-splashed conidia. Survived on infested crop debris, volunteer plants, and infected seeds.",
                chemicalAndBiologicalControls = "DMI Triazoles: Difenoconazole (Score 250 EC, FRAC 3), Tebuconazole. QoI Strobilurins: Azoxystrobin (Amistar, FRAC 11). SDHI: Boscalid (FRAC 7). Crop hygiene, 3-year solanaceous rotation."
            ),
            PathogenClassInfo(
                classification = "Bacteria (Prokaryotic Phytopathogens)",
                keyExamples = "Ralstonia solanacearum (Bacterial Wilt of Solanaceae - devastating in Kenya), Xanthomonas campestris pv. campestris (Black Rot of Cabbage), Erwinia carotovora (Soft Rot)",
                transmissionVectors = "Soil-borne, infected seed tubers, drainage and irrigation runoff, contaminated farm tools and shoes, root-knot nematode wounds.",
                chemicalAndBiologicalControls = "No chemical cure once vascular system is invaded. Preventative: Copper Oxychloride, Copper Hydroxide (Kocide 2000, FRAC M01) for foliar blights. Soil management: Bio-fumigation with Brassica green manures, 5-year rotation away from solanaceae, certified seed only."
            ),
            PathogenClassInfo(
                classification = "Viruses & Sub-Viral Agents",
                keyExamples = "Tomato Yellow Leaf Curl Virus (TYLCV), Maize Lethal Necrosis Disease (MLND - co-infection of SCMV + MCMV), Potato Leaf Roll Virus (PLRV), Bean Common Mosaic Virus (BCMV)",
                transmissionVectors = "Sap-sucking insect vectors: Whiteflies (Bemisia tabaci), Aphids (Rhopalosiphum, Myzus), Thrips. Mechanical pruning tools, vegetative cuttings.",
                chemicalAndBiologicalControls = "Total vector control (Neonicotinoids, Acetamiprid, Yellow sticky traps). Immediate roguing and burning of symptomatic infected plants. Certified disease-free seed and resistant hybrids."
            ),
            PathogenClassInfo(
                classification = "Plant Parasitic Nematodes",
                keyExamples = "Meloidogyne incognita / javanica (Root-Knot Nematodes in Tomato, Potato, Tobacco, Flowers), Pratylenchus (Lesion Nematodes in Maize)",
                transmissionVectors = "Soil, irrigation water runoff, infested nursery seedlings.",
                chemicalAndBiologicalControls = "Bio-nematicides: Paecilomyces lilacinus (Bio-Nem), Tagetes minuta (Mexican Marigold) green manure bio-fumigation. Nematicides: Oxamyl, Fluopyram (Velum Prime)."
            )
        ),
        fracFungicideGuide = listOf(
            FracGroupInfo(
                fracCode = "FRAC 4 (Phenylamides)",
                modeOfAction = "Inhibits RNA polymerase I in Oomycetes. Systemic acropetal transport.",
                activeIngredients = "Metalaxyl-M, Mefenoxam (e.g. Ridomil Gold, Agrolaxyl)",
                resistanceRisk = "High risk: Never apply more than 2-3 times per season; always co-formulated with multi-site partner (Mancozeb)."
            ),
            FracGroupInfo(
                fracCode = "FRAC 11 (QoI Strobilurins)",
                modeOfAction = "Inhibits complex III mitochondrial respiration. Broad-spectrum preventative with translaminar mobility.",
                activeIngredients = "Azoxystrobin, Pyraclostrobin, Trifloxystrobin (e.g. Amistar Top, Ortiva)",
                resistanceRisk = "High risk: Rotate with triazoles (FRAC 3) or multi-site protectants."
            ),
            FracGroupInfo(
                fracCode = "FRAC M03 (Dithiocarbamates)",
                modeOfAction = "Multi-site contact inhibitor of fungal enzyme thiol groups.",
                activeIngredients = "Mancozeb, Propineb (e.g. Dithane M-45, Antracol)",
                resistanceRisk = "Very Low risk: Ideal foundation partner for fungicide resistance management programs."
            ),
            FracGroupInfo(
                fracCode = "FRAC M01 (Inorganic Copper)",
                modeOfAction = "Multi-site contact activity causing protein denaturation and cell wall lysis in both fungi and bacteria.",
                activeIngredients = "Copper Hydroxide, Copper Oxychloride, Cuprous Oxide (e.g. Kocide, Nordox, Cobox)",
                resistanceRisk = "Very Low risk: Essential for bacterial disease suppression and broad-spectrum fungal prevention."
            )
        ),
        diseaseSeverityIndexFormula = "Disease Severity Index (DSI %) = [ Σ (Rating Scale (0-5) × Number of Plants in Category) / (Max Scale (5) × Total Assessed Plants) ] × 100. Action Threshold: Initiate systemic intervention when DSI exceeds 5% in high-risk weather windows."
    )

    // =========================================================================
    // 3. AGRICULTURAL ENTOMOLOGY PILLAR
    // =========================================================================
    val entomologyPillar = EntomologyPillar(
        insectOrders = listOf(
            InsectOrderInfo(
                orderName = "Lepidoptera (Caterpillars, Borers & Moths)",
                mouthpartsAndDamage = "Larvae have powerful chewing mandibles; adults have siphoning proboscis. Larvae cause extensive defoliation, windowing, boring into cobs, stems, pods, and fruit.",
                majorKenyanPests = "Fall Armyworm (Spodoptera frugiperda), African Bollworm (Helicoverpa armigera), Tomato Pinworm (Tuta absoluta), Maize Stalk Borer (Busseola fusca), Diamondback Moth (Plutella xylostella)",
                scoutingAndIpmStrategy = "Pheromone traps for male flight monitoring. Scout whorls and leaf undersides for egg masses and early instars (L1-L2). Spray biologicals (Bt - Bacillus thuringiensis, Metarhizium anisopliae) or Diamides (IRAC 28) early."
            ),
            InsectOrderInfo(
                orderName = "Hemiptera / Homoptera (True Bugs, Aphids & Whiteflies)",
                mouthpartsAndDamage = "Piercing-sucking stylet bundled inside a rostrum. Ingests phloem sap, exudes sugary honeydew inducing sooty mold, causes leaf curling and chlorosis, and vectors destructive plant viruses.",
                majorKenyanPests = "Cotton Aphid (Aphis gossypii), Cabbage Aphid (Brevicoryne brassicae), Whitefly (Bemisia tabaci), Tomato Mealybug, Green Stink Bug (Nezara viridula)",
                scoutingAndIpmStrategy = "Yellow sticky cards @ 20-30 traps/ha. Check terminal buds and leaf undersides. Treat with systemic Neonicotinoids (IRAC 4A), Flonicamid (IRAC 29), or insecticidal potassium soaps."
            ),
            InsectOrderInfo(
                orderName = "Thysanoptera (Thrips)",
                mouthpartsAndDamage = "Asymmetrical rasping-sucking mouthparts. Pierces epidermis and rasps tissue cells, feeding on contents. Causes silvery scar patches, necrotic speckling, floral bud abortion, and fruit curling.",
                majorKenyanPests = "Western Flower Thrips (Frankliniella occidentalis), Onion Thrips (Thrips tabaci), Bean Flower Thrips (Megalurothrips sjostedti)",
                scoutingAndIpmStrategy = "Blue sticky traps (more attractive to thrips than yellow). Shake 10 flower buds over a white tray. Treat with Spinetoram (IRAC 5), Chlorfenapyr, or Abamectin (IRAC 6)."
            ),
            InsectOrderInfo(
                orderName = "Coleoptera (Beetles & Weevils)",
                mouthpartsAndDamage = "Chewing mouthparts in both larvae (grubs) and adults. Adults feed on leaves and flowers; grubs attack root crowns, stems, and stored grains.",
                majorKenyanPests = "Bean Foliage Beetle (Ootheca bennigseni), Maize Weevil (Sitophilus zeamais), Sweet Potato Weevil (Cylas formicarius), White Grubs (Phyllophaga)",
                scoutingAndIpmStrategy = "Inspect emerging seedlings for notched leaves. Use hermetic storage bags (PICS bags) for grain beetles. Soil drench with entomopathogenic nematodes or Imidacloprid for grubs."
            ),
            InsectOrderInfo(
                orderName = "Diptera (True Flies & Leafminers)",
                mouthpartsAndDamage = "Larvae (maggots) are legless with scraping mouth hooks; adults have sponging/lapping mouthparts. Maggots mine between leaf epidermal layers or oviposit into developing fruit causing rot.",
                majorKenyanPests = "Serpentine Leafminer (Liriomyza trifolii / sativae), African Fruit Fly (Bactrocera dorsalis), Melon Fly (Zeugodacus cucurbitae), Bean Fly (Ophiomyia phaseoli)",
                scoutingAndIpmStrategy = "Protein bait traps (Torula yeast / GF-120) with male attractants (Methyl Eugenol) for fruit flies. Translaminar sprays (Cyromazine IRAC 17, Abamectin) for leafminers."
            )
        ),
        beneficialPredators = listOf(
            BeneficialOrganismInfo(
                name = "Ladybird Beetles (Coccinellidae)",
                targetPests = "Aphids, Whitefly nymphs, Mealybugs, Soft Scales, Spider Mites",
                habitatConservation = "Adults and larvae can each consume 50–100 aphids per day. Preserve field borders with flowering coriander, fennel, and marigolds; avoid broad-spectrum pyrethroid sprays."
            ),
            BeneficialOrganismInfo(
                name = "Parasitic Wasps (Trichogramma, Cotesia, Encarsia)",
                targetPests = "Caterpillar eggs (Trichogramma), Fall Armyworm / Diamondback moth larvae (Cotesia), Whitefly nymphs (Encarsia formosa)",
                habitatConservation = "Microscopic non-stinging wasps that lay eggs inside pest eggs/larvae, causing complete biological mortality. Highly susceptible to chemical insecticides."
            ),
            BeneficialOrganismInfo(
                name = "Green Lacewings (Chrysoperla carnea)",
                targetPests = "Aphids ('Aphid lions'), Thrips, Mite eggs, Whitefly crawlers",
                habitatConservation = "Larvae have hollow sickle-shaped jaws that inject digestive enzymes and suck prey dry. Plant nectar strips and provide night light refuges."
            ),
            BeneficialOrganismInfo(
                name = "Predatory Mites (Phytoseiulus persimilis)",
                targetPests = "Two-spotted Red Spider Mites (Tetranychus urticae)",
                habitatConservation = "Fast-moving orange-red predatory mites used widely in commercial rose and vegetable greenhouse farming in Naivasha and Mount Kenya."
            )
        ),
        iracInsecticideGuide = listOf(
            IracGroupInfo(
                iracCode = "IRAC Group 28 (Diamides)",
                targetSite = "Ryanodine receptor modulators causing uncontrolled calcium release in muscle cells, causing rapid paralysis and cessation of feeding within 15 minutes.",
                activeIngredients = "Chlorantraniliprole (Coragen 200 SC), Flubendiamide (Belt), Cyantraniliprole (Benevia)",
                resistanceManagement = "Premier solution for Fall Armyworm and Tuta absoluta. Maximum 2 sprays per crop cycle to preserve sensitivity."
            ),
            IracGroupInfo(
                iracCode = "IRAC Group 6 (Avermectins & Milbemycins)",
                targetSite = "Glutamate-gated chloride channel (GluCl) allosteric modulators disrupting nerve transmission.",
                activeIngredients = "Emamectin Benzoate (Prove, Escort), Abamectin (Dynamec)",
                resistanceManagement = "Rapid translaminar action into leaf tissue. Rotate with diamides and Bt."
            ),
            IracGroupInfo(
                iracCode = "IRAC Group 4A (Neonicotinoids)",
                targetSite = "Nicotinic acetylcholine receptor (nAChR) competitive modulators causing continuous nervous excitation.",
                activeIngredients = "Imidacloprid (Confidor), Acetamiprid, Thiamethoxam (Actara)",
                resistanceManagement = "Highly systemic root and foliar uptake against sucking pests. Toxic to bees; do not spray open flowering blooms."
            ),
            IracGroupInfo(
                iracCode = "IRAC Group 3A (Pyrethroids)",
                targetSite = "Sodium channel modulators causing repetitive nerve firing and rapid insect 'knockdown'.",
                activeIngredients = "Lambda-cyhalothrin (Karate), Deltamethrin (Decis), Cypermethrin",
                resistanceManagement = "Broad-spectrum contact insecticide. High resistance in diamondback moth and armyworm; use sparingly to protect beneficial ladybirds."
            )
        ),
        economicThresholdFormula = "Economic Injury Level (EIL) = C / (V × I × D × K), where C = Cost of Management per Hectare (KES), V = Market Value of Crop per Kg (KES), I = Pest Density, D = Yield Loss per Pest, K = Proportionate Reduction in Pest by Treatment. Action Rule: Spray only when pest density reaches Economic Threshold Level (ETL = 75% of EIL)."
    )

    // =========================================================================
    // 4. AGRONOMY & CROP PHYSIOLOGY PILLAR
    // =========================================================================
    val agronomyPillar = AgronomyPillar(
        physiologyPrinciples = listOf(
            CropPhysiologyPrinciple(
                principle = "C3 vs C4 Photosynthetic Efficiency",
                description = "C4 plants (Maize, Sorghum, Sugarcane) possess specialized bundle sheath anatomy (Kranz anatomy) that concentrates CO2 around RuBisCO, completely eliminating photorespiration. This gives C4 crops 2x the water-use efficiency and explosive biomass accumulation at high tropical light and temperatures compared to C3 crops (Potato, Tomato, Beans, Wheat).",
                fieldApplication = "Maize thrives in hot, sunny tropical mid-altitudes, while C3 crops (potatoes, brassicas) require cooler highland temperatures to prevent excessive photorespiratory yield drag."
            ),
            CropPhysiologyPrinciple(
                principle = "Leaf Area Index (LAI) Optimization",
                description = "LAI is the ratio of one-sided green leaf area to ground area. For maximum solar energy interception (>95% light capture), maize requires an LAI of 3.8 – 4.5, while potatoes require 3.5 – 4.0. Beyond the critical LAI, lower leaves are shaded, turning from photosynthetic 'sources' into respiratory 'sinks'.",
                fieldApplication = "Determine exact plant population density per hectare. Overcrowding causes mutual leaf shading, weak spindly stems, and lodging."
            ),
            CropPhysiologyPrinciple(
                principle = "Calcium (Ca) Transpiration-Driven Transport",
                description = "Unlike nitrogen and potassium which are mobile in both xylem and phloem, Calcium moves exclusively through the xylem via the transpiration stream. If humidity is 100% or root water uptake is restricted, transpiration stops and no calcium reaches rapidly expanding cell walls in fruits and growing tips.",
                fieldApplication = "Blossom End Rot (BER) in tomatoes and tip-burn in cabbage are caused by transpiration failures or moisture fluctuations, even when soil calcium is adequate. Ensure regular irrigation and spray foliar calcium (YaraLiva Nitrabor)."
            )
        ),
        plantingGeometryStandards = listOf(
            CropGeometryStandard(
                cropName = "Maize (Hybrid Grain / Silage)",
                spacingCm = "75 cm × 25 cm (1 plant/hill) OR 75 cm × 50 cm (2 plants/hill)",
                populationPerHa = "53,333 plants/ha",
                populationPerAcre = "21,585 plants/acre",
                seedRateKgPerHa = "20 – 25 kg/ha (certified seed)"
            ),
            CropGeometryStandard(
                cropName = "Irish Potato (Solanum tuberosum)",
                spacingCm = "75 cm between ridges × 30 cm along row",
                populationPerHa = "44,444 plants/ha",
                populationPerAcre = "17,986 plants/acre",
                seedRateKgPerHa = "1,800 – 2,200 kg/ha (Size I seed tubers, 35–45 mm)"
            ),
            CropGeometryStandard(
                cropName = "Tomato (Determinate Field Bush)",
                spacingCm = "60 cm × 45 cm (Field bush) OR 75 cm × 40 cm (Staked)",
                populationPerHa = "37,037 plants/ha",
                populationPerAcre = "14,988 plants/acre",
                seedRateKgPerHa = "150 – 250 g/ha (transplanted seedlings)"
            ),
            CropGeometryStandard(
                cropName = "Common Dry Beans (Phaseolus vulgaris)",
                spacingCm = "45 cm × 15 cm (pure stand) OR 50 cm × 10 cm",
                populationPerHa = "148,148 – 200,000 plants/ha",
                populationPerAcre = "59,950 – 80,940 plants/acre",
                seedRateKgPerHa = "50 – 60 kg/ha"
            ),
            CropGeometryStandard(
                cropName = "Cabbage / Sukuma Wiki (Brassica oleracea)",
                spacingCm = "60 cm × 60 cm (Large heads) OR 60 cm × 45 cm (Medium market)",
                populationPerHa = "27,777 – 37,037 plants/ha",
                populationPerAcre = "11,240 – 14,988 plants/acre",
                seedRateKgPerHa = "300 g/ha (nursery seed)"
            )
        ),
        criticalPeriodWeedControl = "Critical Period for Weed Control (CPWC): The specific window in the crop lifecycle where weed competition causes permanent, irreversible yield reduction. In maize, CPWC occurs between emergence (VE) and the 6-leaf stage (V6) (first 14 to 35 days post-planting). Weeds emerging after V8 cause negligible yield loss because the closed maize canopy intercepts >90% of solar radiation, naturally starving understory weeds."
    )

    // =========================================================================
    // 5. SOIL & WATER CONSERVATION AND MANAGEMENT PILLAR
    // =========================================================================
    val conservationPillar = SoilWaterConservationPillar(
        engineeringStructures = listOf(
            ConservationStructureInfo(
                structureName = "Fanya Juu Terraces ('Throw Soil Uphill')",
                suitableSlopes = "Slopes between 5% and 20% in medium to high rainfall zones (Machakos, Embu, Kisii, Kiambu)",
                constructionSpecifications = "A trench 60 cm wide and 60 cm deep is excavated along the contour using an A-frame or line level. The excavated soil is thrown uphill to create an embankment (bund) 40–50 cm high, 50 cm wide, with a 30 cm berm. Over 2–4 seasons, soil wash naturally levels the land into permanent bench terraces.",
                maintenanceAndVegetation = "Plant Vetiver grass, Napier grass (Pennisetum purpureum), or Calliandra calothyrsus along the top of the bund to bind the soil and produce high-quality livestock fodder."
            ),
            ConservationStructureInfo(
                structureName = "Fanya Chini Terraces ('Throw Soil Downhill')",
                suitableSlopes = "Gentle slopes (< 5%) in high-rainfall or waterlogged areas",
                constructionSpecifications = "The trench is dug along the contour and the soil is thrown downhill. The channel serves primarily as a graded cutoff drain to divert excess destructive surface runoff safely into natural waterways.",
                maintenanceAndVegetation = "Ensure a gentle channel gradient (0.2% - 0.4%) to prevent ditch erosion. Keep channel free of silt and weed blockages."
            ),
            ConservationStructureInfo(
                structureName = "Contour Stone Bunds & Semi-Circular Hoops",
                suitableSlopes = "Arid and Semi-Arid Lands (ASALs) with stony terrain (Turkana, Baringo, Makueni, Marsabit)",
                constructionSpecifications = "Lines of local stones laid along true contour lines, 15–30 cm high and 30–50 cm base width. Slows down flash-flood surface runoff, retains silt, and promotes deep water infiltration into root zones.",
                maintenanceAndVegetation = "Check lines after flash floods and replace dislodged stones. Plant drought-tolerant legumes (Pigeon peas, Green grams) behind bunds."
            ),
            ConservationStructureInfo(
                structureName = "Cutoff Drains (Diversion Ditches)",
                suitableSlopes = "Upper boundary of cultivated hillside land",
                constructionSpecifications = "Trapezoidal channel designed to capture incoming overland flow from uncultivated upper slopes and safely divert it away from farmed fields into retention basins or vegetated waterways.",
                maintenanceAndVegetation = "Plant grass along channel banks to prevent scour; never empty directly into gullies."
            )
        ),
        biologicalMeasures = listOf(
            BiologicalConservationInfo(
                measureName = "Push-Pull Technology",
                scientificSpecies = "Desmodium uncinatum (Silverleaf / Greenleaf) + Napier Grass / Brachiaria (Mulato II)",
                mechanism = "1. 'Push': Desmodium intercropped between maize rows produces volatile allelochemicals that repel stemborer moths and root exudates (di-C-glycosylflavones) that stimulate suicidal Striga germination. 2. 'Pull': Napier or Brachiaria border grass attracts moths to lay eggs on its sticky hairs where caterpillars perish.",
                coBenefits = "Fixes 80–100 kg N/ha into the soil, provides 100% soil ground cover suppressing weed seed banks, stops soil erosion, and yields year-round protein-rich livestock fodder."
            ),
            BiologicalConservationInfo(
                measureName = "Vetiver Grass Hedgerows (The Living Dam)",
                scientificSpecies = "Chrysopogon zizanioides (Vetiver Grass)",
                mechanism = "Vetiver forms an impenetrable, stiff-stemmed living hedge along contours that slows runoff, spreading water across the slope and trapping 98% of suspended topsoil sediment. Its massive sponge-like root system penetrates vertically 3 to 4 meters into subsoil.",
                coBenefits = "Sterile seeds (never becomes an invasive weed), extreme drought and fire tolerance, stabilizes gully edges and riverbanks."
            ),
            BiologicalConservationInfo(
                measureName = "Green Manure Cover Cropping",
                scientificSpecies = "Mucuna pruriens (Velvet Bean), Canavalia ensiformis (Jack Bean), Dolichos lablab",
                mechanism = "Dense ground canopy produces 5 to 8 tonnes of dry organic biomass per hectare in 90 days. Completely intercepts raindrops, buffers soil temperature, and suppresses aggressive weeds like Couch Grass and Nut Grass.",
                coBenefits = "Incorporated into the soil, it adds 120–150 kg N/ha and dramatically improves soil aggregate stability and CEC."
            )
        ),
        moistureRetentionTechniques = listOf(
            MoistureTechniqueInfo(
                technique = "Zai Pits (Tumbukiza Micro-Catchment Basins)",
                specifications = "Holes 30–40 cm wide and 20–30 cm deep excavated in grid patterns across barren or crusted land. Filled with 2–3 handfuls of well-rotted manure, crop residues, and topsoil. Seeds planted inside the depression.",
                waterSavingEfficiency = "Concentrates rain runoff directly around the root zone; increases cereal and sorghum yields by 300%–500% in drought-prone semi-arid areas."
            ),
            MoistureTechniqueInfo(
                technique = "Organic Stover Mulching",
                specifications = "Maintain a 5–10 cm layer of dry crop residues (maize stalks, straw, dry grass) across the entire soil surface. Keep mulch 5 cm away from seedling stems to prevent pest nesting.",
                waterSavingEfficiency = "Reduces evaporation losses from topsoil by 60%–70%, lowers soil surface temperatures by 5–8°C, and prevents surface capping."
            ),
            MoistureTechniqueInfo(
                technique = "Conservation Agriculture (CA) Ripping",
                specifications = "Zero-inversion tillage. A tractor- or oxen-drawn subsoil ripper cuts a narrow furrow 20–25 cm deep through the hardpan, leaving 70%–80% of the soil surface completely undisturbed under permanent organic cover.",
                waterSavingEfficiency = "Allows rapid vertical water infiltration during intense storm events; eliminates plowpan formation; reduces fuel/labor tillage costs by 65%."
            )
        )
    )
}
