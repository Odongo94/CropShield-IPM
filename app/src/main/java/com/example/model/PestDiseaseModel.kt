package com.example.model

enum class ThreatType(val label: String) {
    DISEASE("Crop Disease"),
    PEST("Crop Pest"),
    WEED("Invasive & Indicator Weed"),
    SOIL("Soil Type & Health Issue")
}

enum class CropCategory(val displayName: String) {
    ALL("All Crops"),
    ORNAMENTAL_CUT_FLOWER("Cut Flowers & Ornamentals"), // Roses, Carnations, Chrysanthemums, Lilies, Lisianthus, Summer flowers
    VEGETABLES("Vegetables & Solanaceae"),             // Tomato, Potato, Pepper, Cabbage, French Beans
    CEREALS_GRAINS("Cereals & Grains"),                // Maize, Rice, Sorghum, Wheat, Barley
    TUBERS_ROOTS("Tubers & Roots"),                    // Potato, Sweet Potato, Cassava
    LEGUMES("Legumes & Pulses"),                       // French Beans, Common Beans, Cowpeas, Soybeans, Pigeon Peas
    FRUITS("Fruits")
}

enum class PathogenClass(val label: String) {
    FUNGAL("Fungal & Oomycete Pathogen"),
    BACTERIAL("Bacterial Pathogen"),
    VIRAL("Viral & Vector Complex"),
    NEMATODE("Plant Parasitic Nematode"),
    INSECT_PEST("Insect Pest & Caterpillars"),
    MITE_PEST("Acarina Mite Pest"),
    WEED_SPECIES("Weed Flora & Parasitic Plant"),
    SOIL_PROFILE("Soil Taxonomy & Edaphic Condition")
}

fun determinePathogenClass(type: ThreatType, scientificName: String, name: String): PathogenClass {
    return when (type) {
        ThreatType.WEED -> PathogenClass.WEED_SPECIES
        ThreatType.SOIL -> PathogenClass.SOIL_PROFILE
        ThreatType.PEST -> {
            if (scientificName.contains("Tetranychus", true) ||
                scientificName.contains("Aculops", true) ||
                name.contains("Mite", true)
            ) {
                PathogenClass.MITE_PEST
            } else {
                PathogenClass.INSECT_PEST
            }
        }
        ThreatType.DISEASE -> {
            if (scientificName.contains("Ralstonia", true) ||
                scientificName.contains("Xanthomonas", true) ||
                scientificName.contains("Pseudomonas", true) ||
                scientificName.contains("Clavibacter", true) ||
                name.contains("Bacterial", true) ||
                name.contains("Black Rot", true)
            ) {
                PathogenClass.BACTERIAL
            } else if (scientificName.contains("Virus", true) ||
                scientificName.contains("MCMV", true) ||
                scientificName.contains("Potyvirus", true) ||
                name.contains("Lethal Necrosis", true) ||
                name.contains("MLND", true) ||
                name.contains("Mosaic", true) ||
                name.contains("Leaf Curl", true)
            ) {
                PathogenClass.VIRAL
            } else if (scientificName.contains("Meloidogyne", true) ||
                name.contains("Nematode", true)
            ) {
                PathogenClass.NEMATODE
            } else {
                PathogenClass.FUNGAL
            }
        }
    }
}

enum class StageIconType {
    SPORE,                 // Spore landing / inoculum (Fungi)
    PENETRATION,           // Germination & stomata/cuticle entry (Fungi)
    HYPHAE,                // Mycelium / incubation in tissue (Fungi)
    LESION,                // Visual lesion & symptom emergence (Fungi)
    DISPERSAL,             // Sporulation & wind/splash spread (Fungi)
    EGG,                   // Pest egg cluster / Oviposition (Insects & Mites)
    LARVA,                 // Feeding caterpillar / nymph / borer / maggot (Insects)
    PUPA,                  // Pupation in soil or leaf cocoon (Insects)
    ADULT,                 // Mating moth / fly / beetle / mite (Insects & Mites)
    BACTERIA_SURVIVAL,     // Epiphytic survival / Ingress (Bacteria)
    BACTERIA_ENTRY,        // Hydathode / Wound entry (Bacteria)
    VASCULAR_COLONIZATION, // Xylem biofilm / Slime occlusion (Bacteria)
    SYSTEMIC_COLLAPSE,     // Wilt & Bacterial ooze (Bacteria)
    VECTOR_TRANSMISSION,   // Vector feeding & transmission (Viruses)
    VIRAL_REPLICATION,     // Systemic multiplication & phloem spread (Viruses)
    NEMATODE_JUVENILE,     // Infective J2 stage (Nematodes)
    ROOT_GALL,             // Giant feeding cell & root galling (Nematodes)
    SEED_BANK,             // Weed seed dormancy & soil seed bank (Weeds)
    GERMINATION_EMERGENCE, // Seed germination & cotyledon emergence (Weeds)
    VEGETATIVE_ROSETTE,    // Tillering / rosette / vine spread (Weeds)
    FLOWERING_SEED_SET,    // Inflorescence & seed maturation (Weeds)
    PERENNATION_DISPERSAL, // Rhizomes / tubers / wind dispersal (Weeds)
    SOIL_BASELINE,         // Native pedogenesis / initial parent material (Soil)
    SOIL_DEGRADATION,      // Nutrient depletion / acidification / hardpan formation (Soil)
    CRUSTING_EROSION,      // Surface crusting / rill erosion / compaction (Soil)
    REMEDIATION_AMENDMENT, // Active liming / gypsum / deep ripping / bio-cycling (Soil)
    REHABILITATED_FERTILE  // Restored humus crumb structure & optimal CEC (Soil)
}

enum class VulnerabilityLevel {
    CRITICAL, // Most susceptible to intervention (e.g. before penetration or early instar larva)
    HIGH,     // High success rate with preventative or curative measures
    MODERATE, // Harder to reach (e.g. deep internal tissues)
    LOW       // Protected phase (e.g. subterranean pupa or overwintering thick-walled oospores)
}

data class LifecycleStage(
    val stageNumber: Int,
    val title: String,
    val scientificSub: String,
    val iconType: StageIconType,
    val duration: String,
    val vulnerability: VulnerabilityLevel,
    val description: String,
    val symptomsToSpot: String,
    val targetAction: String,
    val recommendedActiveIngredients: List<String>
)

data class ChemicalRecommendation(
    val activeIngredient: String,
    val chemicalGroup: String,         // e.g. FRAC Group 4, IRAC Group 28
    val classification: String,        // Protective / Systemic Curative / Translaminar / Contact
    val targetStage: String,           // Stage 1-2 Spores, Instar 1-2 Larvae, etc.
    val dosagePerKnapsack: String,     // e.g. "40g - 50g in 16L/20L knapsack"
    val dosagePerHectare: String,      // e.g. "1.5 kg/ha"
    val phiDays: Int,                  // Pre-Harvest Interval (days)
    val reiHours: Int,                 // Re-Entry Interval (hours)
    val applicationTiming: String,
    val resistanceManagement: String,
    val safetyPpe: List<String>,
    val tradeName: String = "",
    val pcpbRegNo: String = "",
    val formulation: String = "",
    val lastingIpmRule: String = ""
)

enum class BioProtectionType(val label: String) {
    MICROBIAL("Microbial Biopesticide (Bacteria/Fungi/Virus)"),
    MACROBIAL_PREDATOR("Invertebrate Biocontrol / Predatory Mite / Beneficial"),
    INVERTEBRATE_MACROBIAL("Invertebrate Biocontrol / Predatory Mite / Beneficial"),
    SEMIOCHEMICAL("Semiochemical / Pheromone / Attractant"),
    SEMIOCIOCHEMICAL("Semiochemical / Pheromone / Attractant"),
    NATURAL_SUBSTANCE("Botanical & Natural Substance")
}

data class CabiBioProduct(
    val tradeName: String,
    val activeAgent: String,
    val type: BioProtectionType,
    val targetOrganism: String = "",
    val targetCrops: List<String> = emptyList(),
    val modeOfAction: String = "",
    val applicationRate: String = "",
    val safetyMrl: String = "Zero MRL; Organic compatible",
    val supplierOrDistributor: String = "",
    val registrationCountry: String = "Kenya & East Africa (CABI BioProtection Portal)",
    val compatibilityNotes: String = "",
    val targetPestOrDisease: String = targetOrganism,
    val targetCropCategory: String = if (targetCrops.isNotEmpty()) targetCrops.joinToString(", ") else "Crops",
    val supplier: String = supplierOrDistributor
)

data class PcpbRegisteredChemical(
    val tradeName: String,
    val activeIngredient: String,
    val pcpbRegNo: String,
    val formulation: String, // e.g. SC, EC, WP, WG
    val targetCrops: List<String> = emptyList(),
    val fracIracGroup: String, // e.g. FRAC 11 (QoI), IRAC 4A (Neonicotinoid)
    val knapsackDosage: String, // e.g. "20ml per 20L knapsack"
    val perHaDosage: String = "",
    val phiDays: Int,
    val reiHours: Int,
    val registrant: String = "",
    val lastingIpmRule: String = "Rotate with different MoA groups to prevent resistance buildup.",
    val targetCrop: String = if (targetCrops.isNotEmpty()) targetCrops.first() else "",
    val targetThreat: String = "",
    val dosagePerHectare: String = perHaDosage,
    val registrantOrAgent: String = registrant,
    val whoClass: String = "Class III (Slightly Hazardous)"
)

data class InfonetPractice(
    val title: String,
    val category: String, // Cultural, Organic Botanical, Companion Planting, Push-Pull, Soil Health
    val description: String,
    val recipeOrMethod: String = "",
    val preventionOrDirect: String = "Preventative & Direct Cultural Practice",
    val infonetSourceUrl: String = "https://infonet-biovision.org"
)

data class IpmPlan(
    val culturalControls: List<String>,
    val physicalMechanicalControls: List<String>,
    val biologicalControls: List<String>,
    val chemicalControls: List<ChemicalRecommendation>,
    val economicThreshold: String
)

data class CropThreat(
    val id: String,
    val name: String,
    val scientificName: String,
    val type: ThreatType,
    val cropCategory: CropCategory = CropCategory.VEGETABLES,
    val targetCrops: List<String>,
    val affectedParts: List<String>,   // Leaves, Stem, Fruit, Tuber, Roots, Flowers/Buds, Petals
    val severityLevel: String,         // Extreme, High, Moderate
    val summary: String,
    val diagnosticKeys: List<String>,
    val favorableConditions: String,
    val imageResId: Int?,
    val lifecycleStages: List<LifecycleStage>,
    val ipmPlan: IpmPlan,
    val scoutChecklist: List<String>,
    val infonetPractices: List<InfonetPractice> = emptyList(),
    val cabiBioProtections: List<CabiBioProduct> = emptyList(),
    val pcpbChemicals: List<PcpbRegisteredChemical> = emptyList(),
    val vulnerableCropStages: String = "",
    val yieldLossPotential: String = "",
    val economicThresholdInfo: String = "",
    val mechanicalControls: List<String> = emptyList(),
    val soilIndicatorProfile: com.example.model.science.SoilIndicatorProfile? = null,
    val pathogenClass: PathogenClass = determinePathogenClass(type, scientificName, name)
)
