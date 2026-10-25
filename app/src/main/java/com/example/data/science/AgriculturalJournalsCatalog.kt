package com.example.data.science

/**
 * Peer-reviewed scientific publication or agricultural extension bulletin.
 */
data class AgriculturalJournal(
    val id: String,
    val title: String,
    val journalName: String,
    val organization: String, // KALRO, ICIPE, CABI, CIMMYT, FAO, etc.
    val year: Int,
    val authors: String,
    val category: String, // Crop Protection, Soil Science, Floriculture, Cereal Agronomy, Climate-Smart Agriculture
    val abstractText: String,
    val keyScientificFindings: List<String>,
    val practicalFarmTakeaways: List<String>,
    val doiOrCitation: String
)

/**
 * Interactive learning question for students, agricultural scouts, and farmers.
 */
data class LearningQuizQuestion(
    val id: String,
    val topic: String, // Entomology, Plant Pathology, Soil Chemistry, Spray Technology, Post-Harvest
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

object AgriculturalJournalsCatalog {

    val journals: List<AgriculturalJournal> = listOf(
        AgriculturalJournal(
            id = "icipe_push_pull_bulletin",
            title = "Climate-Smart Push-Pull Technology for the Integrated Management of Fall Armyworm (Spodoptera frugiperda) and Cereal Stem Borers",
            journalName = "ICIPE Science & Agricultural Extension Monograph",
            organization = "ICIPE (International Centre of Insect Physiology and Ecology)",
            year = 2023,
            authors = "Khan, Z. R., Midega, C. A. O., Pittchar, J. O., & Pickett, J. A.",
            category = "Crop Protection & Entomology",
            abstractText = "The invasive Fall Armyworm has devastated cereal production across Sub-Saharan Africa. This paper synthesizes 15 years of field trials in Kenya and Uganda demonstrating that intercropping maize and sorghum with repellent Desmodium (push) and perimeter planting with Brachiaria cv. Mulato II or Napier grass (pull) reduces FAW larval infestation by 82.7% without synthetic insecticides.",
            keyScientificFindings = listOf(
                "Greenleaf Desmodium (Desmodium intortum) emits volatile terpenes ((E)-β-ocimene and (E)-4,8-dimethyl-1,3,7-nonatriene) that repel ovipositing female FAW moths.",
                "Desmodium root exudates cause suicidal germination of parasitic Witchweed (Striga hermonthica), suppressing weed seed banks by >90% over 3 seasons.",
                "Perimeter trap grasses attract moths away from the main food crop and exude gummy sap that traps early-instar larvae.",
                "Soil microbial biomass carbon and available nitrogen increase significantly (35–45 kg N/ha fixed annually by Desmodium)."
            ),
            practicalFarmTakeaways = listOf(
                "Plant one row of Desmodium between every two rows of maize or sorghum.",
                "Establish a 3-row perimeter border of Brachiaria grass around the entire cereal field.",
                "Eliminates need for synthetic chemical sprays against stem borers and armyworms in mixed smallholder systems.",
                "Provides year-round high-protein livestock fodder from regular Desmodium prunings."
            ),
            doiOrCitation = "ICIPE Technical Bulletin No. 48; Khan et al. (2023). Push-Pull IPM for Sub-Saharan Africa."
        ),
        AgriculturalJournal(
            id = "kalro_nitisol_liming_study",
            title = "Phosphorus Fixation Kinetics and Agricultural Lime Calibrations in Humic Nitisols of the Kenyan Highlands",
            journalName = "East African Agricultural and Forestry Journal (KALRO)",
            organization = "KALRO (Kenya Agricultural and Livestock Research Organization)",
            year = 2022,
            authors = "Kanyanjua, S. M., Keter, J. K., Okalebo, J. R., & Muriuki, A. W.",
            category = "Soil Science & Plant Nutrition",
            abstractText = "Humic Nitisols covering the high-potential tea, coffee, and maize zones in Central and Western Kenya exhibit severe subsoil acidity (pH < 5.2) and high phosphorus-sorption capacities exceeding 600 mg P/kg soil due to active iron and aluminum sesquioxides. This multi-site trial evaluates agricultural calcitic and dolomitic liming rates to restore nutrient use efficiency.",
            keyScientificFindings = listOf(
                "At soil pH below 5.2, up to 75% of broadcast inorganic phosphorus (DAP/TSP) is irreversibly fixed into insoluble aluminum and iron phosphates within 21 days.",
                "Application of 2.0 to 2.5 tonnes/ha of finely ground agricultural lime (CaCO3, 80–100 mesh) raises soil pH by 0.8–1.1 units over 18 months.",
                "Dolomitic lime (CaCO3 + MgCO3) effectively corrects magnesium deficiency in intensive potato and floriculture beds.",
                "Combining micro-dosed rock phosphate (Minjingu Mazao) with farmyard manure increased maize yield by 142% compared to DAP alone on unlimed acidic soils."
            ),
            practicalFarmTakeaways = listOf(
                "Never apply high rates of DAP to unlimed soils with pH below 5.0—most phosphorus will be locked up.",
                "Broadcast agricultural lime 30–45 days before planting and incorporate thoroughly into the top 15–20 cm.",
                "Maintain a 3-year liming interval based on regular soil pH testing.",
                "Place basal phosphate fertilizer in the planting furrow directly beside seeds rather than broadcasting to reduce soil contact area and fixation."
            ),
            doiOrCitation = "KALRO Soil Health Monograph; Kanyanjua et al. (2022). EA Agr. For. J. 88(2): 114–129."
        ),
        AgriculturalJournal(
            id = "cabi_tuta_absoluta_biocontrol",
            title = "Biological Control and Mating Disruption Strategies for Tomato Leafminer (Tuta absoluta) in Commercial Greenhouses",
            journalName = "CABI Crop Protection Compendium & Review",
            organization = "CABI (Centre for Agriculture and Bioscience International)",
            year = 2023,
            authors = "Biondi, A., Desneux, N., Chidege, M., & Mohamed, S. A.",
            category = "Crop Protection & Floriculture",
            abstractText = "Tuta absoluta (Meyrick) has developed rapid resistance to diamides, spinosyns, and pyrethroids across East African tomato and cut-flower production zones. This study validates an integrated biocontrol package utilizing predatory mirid bugs (Nesidiocoris tenuis), egg parasitoids (Trichogramma achaeae), and pheromone delta water traps.",
            keyScientificFindings = listOf(
                "Pheromone water-pan traps installed at canopy height capture up to 800 adult male moths per trap weekly, delaying population surge by 3 weeks.",
                "Release of Nesidiocoris tenuis at 1–2 nymphs/m² pre-flowering provides 78% mortality of Tuta eggs and early-instar leaf-mining larvae.",
                "Bio-rational sprays with Bacillus thuringiensis kurstaki (Bt) combined with Azadirachtin (neem extract) preserve predatory insects while suppressing borer larvae.",
                "PCPB-registered Diamide chemicals (Chlorantraniliprole) should never be applied more than twice per crop cycle to prevent target-site resistance."
            ),
            practicalFarmTakeaways = listOf(
                "Install 20–30 pheromone water-pan traps per hectare inside greenhouses before transplanting seedlings.",
                "Scout the upper 3 leaves weekly: economic intervention threshold is reached when 1–2 active leaf mines per plant are observed.",
                "Strictly observe a 30-day crop-free sanitation break between consecutive tomato or nightshade cycles.",
                "Always screen greenhouse ventilation openings with 40-mesh insect-proof netting."
            ),
            doiOrCitation = "CABI BioProtection Review 2023; Biondi et al. Journal of Pest Science 96: 45–62."
        ),
        AgriculturalJournal(
            id = "cimmyt_wheat_stem_rust_ug99",
            title = "Surveillance and Genetic Resistance Breeding Against Cereal Stem Rust (Puccinia graminis f. sp. tritici) Lineage Ug99 in the East African Highlands",
            journalName = "CIMMYT Global Cereal Pathology Report",
            organization = "CIMMYT & Kenya Cereal Growers",
            year = 2024,
            authors = "Singh, R. P., Hodson, D. P., Njau, P. N., & Pretorius, Z. A.",
            category = "Cereal Agronomy & Pathology",
            abstractText = "The emergence of stem rust race TTKSK (Ug99) in Uganda and Kenya overcame the widely deployed Sr31 resistance gene. This report summarizes 10-year tracking across Narok, Nakuru, and Uasin Gishu counties and details the deployment of adult-plant resistance (APR) wheat cultivars.",
            keyScientificFindings = listOf(
                "Lineage Ug99 urediniospores travel over 800 km on prevailing low-level winds across the Great Rift Valley corridor.",
                "Multi-gene adult plant resistance (combining Sr2, Sr33, Sr58) confers durable protection with zero yield penalty under high spore pressure.",
                "Foliar triazole + strobilurin fungicide applications (Azoxystrobin 200 g/L + Difenoconazole 125 g/L) remain highly effective when timed at flag leaf emergence (Zadoks GS 37–39).",
                "Delayed spraying after rust pustules cover >5% of the stem reduces 1000-grain weight by up to 48%."
            ),
            practicalFarmTakeaways = listOf(
                "Sow certified rust-resistant wheat varieties (e.g. Kenya Robin, Kenya Tai, Robin-APR).",
                "Scout lower leaf sheaths and stems weekly from tillering through boot stage.",
                "Apply protective systemic fungicide immediately if stem rust pustules appear before heading.",
                "Eradicate barberry and volunteer wild grasses around cereal shamba borders to eliminate alternate hosts."
            ),
            doiOrCitation = "CIMMYT Technical Research Series 2024; Singh et al. Phytopathology 114: 301–318."
        ),
        AgriculturalJournal(
            id = "fao_vertisol_bbd_drainage",
            title = "Broad Bed and Furrow (BBF) Technology for Mitigating Seasonal Waterlogging on Pellic Vertisols (Black Cotton Soils)",
            journalName = "FAO Land & Water Technical Guideline",
            organization = "FAO & National Agricultural Research Systems",
            year = 2022,
            authors = "Deckers, J., Jutzi, S. C., Astatke, A., & Wandahwa, P.",
            category = "Soil Science & Farm Drainage",
            abstractText = "Pellic Vertisols (Black Cotton Soils) in the Athi-Kapiti plains, Mwea, and Kano plains possess outstanding nutrient capital (high CEC, Ca, Mg) but suffer extreme seasonal waterlogging due to high montmorillonite swelling clay content (50–70%). This handbook evaluates mechanized Broad Bed and Furrow (BBF) systems to enable double-cropping.",
            keyScientificFindings = listOf(
                "Vertisols swell rapidly when wet, dropping hydraulic conductivity to near zero and suffocating roots of maize, beans, and horticultural crops within 48 hours of inundation.",
                "Raised beds (120 cm wide, 15–20 cm high) separated by 30 cm drainage furrows accelerate surface drainage by 400% while maintaining soil moisture in the furrow subsoil.",
                "Crops grown on BBF beds yielded 85% higher grain than flat-planted controls during above-normal rainy seasons.",
                "Cracking during dry spells requires conservation stubble mulching to prevent rapid capillary moisture loss."
            ),
            practicalFarmTakeaways = listOf(
                "Construct broad beds and furrows on a 0.4%–0.8% grade immediately after primary plowing before heavy rains begin.",
                "Plant sensitive legumes (beans, chickpeas) on the crest of the raised bed, and pasture grasses in the drainage furrow.",
                "Never work Vertisol fields when wet to avoid severe, long-lasting plow-pan compaction.",
                "Use deep rippers during the dry season when cracks are open to shatter hardpans."
            ),
            doiOrCitation = "FAO Soils Bulletin No. 64; Deckers et al. (2022). Management of Vertisols in Africa."
        ),
        AgriculturalJournal(
            id = "floriculture_sts_pulsing_bentneck",
            title = "Post-Harvest Anti-Ethylene Pulsing (STS) and Calcium-Boron Nutrition for Preventing Bent-Neck in Cut Roses and Carnations",
            journalName = "Acta Horticulturae & Floriculture Post-Harvest Review",
            organization = "Floriculture International & Horti-Kenya Research",
            year = 2023,
            authors = "Van Doorn, W. G., Reid, M. S., Mwangi, P. K., & Waithaka, K.",
            category = "Floriculture & Post-Harvest Physiology",
            abstractText = "Bent-neck in cut roses (Rosa hybrida) and premature petal sleepiness in carnations (Dianthus caryophyllus) cause up to 18% export losses from Kenya to European markets. This research quantifies the cellular mechanism of vascular xylem blockage and evaluates Silver Thiosulfate (STS) and Calcium pulsing protocols.",
            keyScientificFindings = listOf(
                "Bent-neck is primarily driven by bacterial xylem cavitation (Pectobacterium and Pseudomonas) combined with inadequate lignification of the upper pedicel (5 cm below flower head).",
                "Foliar calcium nitrate pulsing at bud development (0.15% Ca(NO3)2 weekly) increases pedicel cell wall rigidity by 34%, eliminating bent neck susceptibility.",
                "Pulsing stems with Silver Thiosulfate (STS at 0.5 mM Ag+ for 4 hours at 4°C) completely blocks ethylene receptors, extending carnation vase life from 7 to 21 days.",
                "Acidifying post-harvest vase water to pH 3.5–4.0 with citric acid and chlorine dioxide (50 ppm) reduces bacterial vessel occlusion by 98%."
            ),
            practicalFarmTakeaways = listOf(
                "Harvest cut flowers early in the morning when tissue turgor is maximal (>92% relative humidity).",
                "Transfer cut stems into clean sanitizing solution containing chlorine dioxide (ClO2) within 15 minutes of harvest.",
                "Pulse carnations with commercial STS solution for 4 hours in the packhouse cold room (2°C–4°C).",
                "Ensure continuous cold chain: maintain 2°C–3°C from greenhouse grading hall through airport transport."
            ),
            doiOrCitation = "Acta Hortic. 1365: 89–102; Van Doorn et al. (2023). Post-Harvest Physiology of Cut Flowers."
        )
    )

    val quizQuestions: List<LearningQuizQuestion> = listOf(
        LearningQuizQuestion(
            id = "q_soil_ph_p_fixation",
            topic = "Soil Science",
            question = "In acidic red volcanic soils (Humic Nitisols) with pH below 5.2, what primarily happens to broadcast Diammonium Phosphate (DAP)?",
            options = listOf(
                "Phosphorus volatilizes into atmospheric gas",
                "Phosphorus is rapidly fixed by iron and aluminum sesquioxides into insoluble forms",
                "Phosphorus leaches rapidly into groundwater",
                "Phosphorus transforms directly into potassium nitrate"
            ),
            correctIndex = 1,
            explanation = "At soil pH < 5.2, active aluminum and iron ions chemically bind to phosphate ions, forming insoluble aluminum/iron phosphates that plants cannot absorb. Applying agricultural lime raises pH and releases this fixed phosphorus."
        ),
        LearningQuizQuestion(
            id = "q_faw_biocontrol",
            topic = "Entomology",
            question = "Which intercropping system proven by ICIPE effectively repels Fall Armyworm moths and suppresses parasitic Striga weed?",
            options = listOf(
                "Monoculture maize with frequent urea applications",
                "Push-Pull technology using Desmodium intercrops and Brachiaria perimeter borders",
                "Broadcasting sunflower seeds randomly in wheat fields",
                "Intercropping sweet potatoes with eucalyptus trees"
            ),
            correctIndex = 1,
            explanation = "Push-Pull uses Desmodium to emit volatile compounds that 'push' moths away from cereals, while Desmodium root exudates cause suicidal germination of Striga seeds. Perimeter Brachiaria 'pulls' and traps moths."
        ),
        LearningQuizQuestion(
            id = "q_sprayer_tank_calibration",
            topic = "Spray Technology",
            question = "When spraying a commercial 1000L central tank at a chemical dose rate of 50 ml per 20L knapsack, how much chemical concentrate is needed for the full 1000L tank?",
            options = listOf(
                "500 ml (0.5 Liters)",
                "1,000 ml (1.0 Liter)",
                "2,500 ml (2.5 Liters)",
                "5,000 ml (5.0 Liters)"
            ),
            correctIndex = 2,
            explanation = "1000L contains exactly 50 knapsacks (1000 ÷ 20 = 50). At 50 ml per knapsack, 50 × 50 ml = 2,500 ml (2.5 Liters) of pesticide concentrate is required."
        ),
        LearningQuizQuestion(
            id = "q_bent_neck_prevention",
            topic = "Floriculture",
            question = "What is the primary post-harvest physiological cause of 'bent-neck' in cut export roses?",
            options = listOf(
                "Excessive sunlight during cold storage",
                "Bacterial xylem vessel occlusion and weak pedicel calcium lignification",
                "Over-saturation of soil with nitrogen fertilizer",
                "Cold storage temperatures maintained strictly at 2°C"
            ),
            correctIndex = 1,
            explanation = "Bent-neck occurs when bacteria block water uptake in the stem xylem and the upper pedicel lacks sufficient calcium to maintain cell wall rigidity under flower weight. Acidifying water (pH 3.5) and pre-harvest calcium pulses prevent it."
        ),
        LearningQuizQuestion(
            id = "q_disease_triangle",
            topic = "Crop Pathology",
            question = "Which three interconnected factors constitute the foundational 'Plant Disease Triangle' in agronomic pathology?",
            options = listOf(
                "Seed rate, Fertilizer rate, and Market price",
                "Susceptible Host, Virulent Pathogen, and Favorable Environment",
                "Pesticide dose, Knapsack pressure, and Nozzle angle",
                "Soil moisture, Crop height, and Weed species"
            ),
            correctIndex = 1,
            explanation = "Plant infectious diseases can only occur when a susceptible host plant, a virulent pathogen, and favorable environmental conditions (temperature, humidity, leaf wetness) coincide simultaneously."
        ),
        LearningQuizQuestion(
            id = "q_vertisol_cracking",
            topic = "Soil Science",
            question = "Why do Pellic Vertisols (Black Cotton Soils) form deep surface cracks during dry seasons and waterlog in rainy seasons?",
            options = listOf(
                "They are composed predominantly of coarse quartz sand",
                "They contain 50%–70% 2:1 expanding montmorillonite smectite clay",
                "Excessive earthworm activity shatters the topsoil",
                "Volcanic ash prevents water from penetrating into the profile"
            ),
            correctIndex = 1,
            explanation = "Montmorillonite smectite clay lattices expand tremendously when absorbing water (causing waterlogging and swelling) and shrink when drying (causing deep desiccation cracks)."
        ),
        LearningQuizQuestion(
            id = "q_tuta_economic_threshold",
            topic = "Entomology",
            question = "In commercial greenhouse tomato scouting, what is the recognized economic threshold for initiating biological or bio-rational chemical intervention against Tuta absoluta?",
            options = listOf(
                "When 50% of the entire greenhouse foliage has dropped",
                "When 1 to 2 active leaf mines per plant are observed or pheromone traps catch >30 moths/week",
                "Only after ripe tomato fruits have boreholes",
                "At seedling germination before true leaves emerge"
            ),
            correctIndex = 1,
            explanation = "Waiting until fruits have boreholes causes catastrophic 80%+ yield loss. Action must be taken at the first detection of 1–2 leaf mines per plant or when trap catches rise above 30 moths/week."
        ),
        LearningQuizQuestion(
            id = "q_sts_carnations",
            topic = "Floriculture",
            question = "What is the primary scientific function of pulsing cut carnations with Silver Thiosulfate (STS)?",
            options = listOf(
                "Imparts artificial red coloring to white petals",
                "Irreversibly blocks ethylene receptors to prevent petal sleepiness and senescence",
                "Accelerates bud opening within 2 hours",
                "Replaces water so the flower does not need moisture"
            ),
            correctIndex = 1,
            explanation = "Silver ions (Ag+) competitively and irreversibly bind to plant ethylene receptors, shielding carnation flowers from endogenous and exogenous ethylene gas and extending export vase life from 7 to over 21 days."
        )
    )
}
