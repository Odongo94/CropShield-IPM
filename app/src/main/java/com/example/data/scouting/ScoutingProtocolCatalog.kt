package com.example.data.scouting

import com.example.model.ScoutingInspectionRow
import com.example.model.ScoutingProtocolItem

object ScoutingProtocolCatalog {

    /**
     * Standard Kenyan IPM Field Scouting Protocols with Economic Thresholds.
     */
    val protocols: List<ScoutingProtocolItem> = listOf(
        ScoutingProtocolItem(
            id = "maize_faw",
            cropName = "Maize",
            threatName = "Fall Armyworm (FAW)",
            scientificName = "Spodoptera frugiperda",
            threatType = "PEST",
            samplingPattern = "5-point W-pattern across 20 consecutive plants per station (100 plants total).",
            samplingUnit = "Plant whorl, leaf axils, and ear shucks.",
            actionThreshold = "Early vegetative (V1-V6): >10% plants with fresh windowing / larvae. Mid-whorl to tasseling (V7-VT): >20% plants infested.",
            severityScaleGuide = "1: Pin-hole windowing; 2: Ragged holes; 3: Deep leaf shredding + frass; 4: Bored central whorl; 5: Dead heart.",
            ipmIntervention = "Apply Emamectin benzoate (Escort 1.9EC) 15-20ml/20L knapsack or Spinetoram (Radiant). Direct knapsack cone nozzle into whorl early morning or dusk.",
            urgencyLevel = "Critical"
        ),
        ScoutingProtocolItem(
            id = "maize_mlnd",
            cropName = "Maize",
            threatName = "Maize Lethal Necrosis Disease (MLND)",
            scientificName = "MCMV + Potyvirus co-infection",
            threatType = "DISEASE",
            samplingPattern = "Diagonal field transect checking 50 plants per quadrant.",
            samplingUnit = "Entire plant, youngest leaf whorl, leaf margins.",
            actionThreshold = ">1% plants exhibiting severe chlorotic mottling or dead heart necrosis.",
            severityScaleGuide = "1: Mild leaf mottling; 2: General mosaic yellowing; 3: Necrotic leaf margins; 4: 'Dead heart' central death; 5: Complete plant collapse.",
            ipmIntervention = "Immediately rogue and bury/burn infected plants. Control vector thrips and aphids with Imidacloprid (Confidor) or Acetamiprid. Maintain 2-season maize-free break.",
            urgencyLevel = "Critical"
        ),
        ScoutingProtocolItem(
            id = "tomato_tuta",
            cropName = "Tomato",
            threatName = "Tomato Leafminer (Tuta Absoluta)",
            scientificName = "Phthorimaea absoluta",
            threatType = "PEST",
            samplingPattern = "Examine 20 plants across 5 stations + check Delta pheromone sticky traps weekly.",
            samplingUnit = "Upper 3 canopy leaves, stems, and flower trusses.",
            actionThreshold = "Trap catches: >3-4 moths/trap/day. Field inspection: >2 active larval mines per plant or >5% damaged leaves.",
            severityScaleGuide = "1: Isolated translucent pin-mines; 2: Blotch leaf mines; 3: Necrotic patches on 3+ leaves; 4: Stem/truss boring; 5: Secondary rot.",
            ipmIntervention = "Deploy Tutasan / Q-lure delta pheromone traps (10-15 traps/ha). Spray Chlorantraniliprole (Coragen 200SC) at 5ml/20L knapsack or Bacillus thuringiensis.",
            urgencyLevel = "Critical"
        ),
        ScoutingProtocolItem(
            id = "tomato_late_blight",
            cropName = "Tomato",
            threatName = "Late Blight",
            scientificName = "Phytophthora infestans",
            threatType = "DISEASE",
            samplingPattern = "Inspect bottom canopy leaves and shaded rows after persistent morning mist or dew.",
            samplingUnit = "Leaf undersides, petioles, green fruit shoulders.",
            actionThreshold = "First lesion observed anywhere in block during cool wet weather (Temp 12-22°C, RH >90%).",
            severityScaleGuide = "1: Water-soaked spots on 1 leaf; 2: White downy mildew under leaf; 3: Petiole blighting; 4: Fruit shoulder rot; 5: Rapid vine blight.",
            ipmIntervention = "Preventative: Mancozeb (Dithane M-45) 50g/20L knapsack. Curative at first lesion: Metalaxyl + Mancozeb (Ridomil Gold MZ) 50g/20L knapsack.",
            urgencyLevel = "Critical"
        ),
        ScoutingProtocolItem(
            id = "potato_late_blight",
            cropName = "Irish Potatoes",
            threatName = "Potato Late Blight",
            scientificName = "Phytophthora infestans",
            threatType = "DISEASE",
            samplingPattern = "Random 5-point cluster scouting; 10 hills per cluster.",
            samplingUnit = "Lower leaves, stem joints, foliage crown.",
            actionThreshold = "Single active lesion detected when relative humidity >85% for 48 consecutive hours.",
            severityScaleGuide = "1: Pale green water-soaked spots; 2: Dark brown lesions with white mold ring; 3: Stem blackened; 4: 50% foliage dead; 5: Tubers infected.",
            ipmIntervention = "Spray Cymoxanil + Mancozeb (Curzate M8) or Dimethomorph + Mancozeb (Acrobat MZ). Ensure thorough canopy penetration.",
            urgencyLevel = "Critical"
        ),
        ScoutingProtocolItem(
            id = "potato_bacterial_wilt",
            cropName = "Irish Potatoes",
            threatName = "Bacterial Wilt (Brown Rot)",
            scientificName = "Ralstonia solanacearum",
            threatType = "DISEASE",
            samplingPattern = "Walk rows during warm afternoon hours (12pm - 3pm) when wilt symptoms are most pronounced.",
            samplingUnit = "Whole plant wilt + cut stem in water for bacterial streaming.",
            actionThreshold = "Zero tolerance (>0% threshold). Any wilting plant without root chewing.",
            severityScaleGuide = "1: One branch flagging at midday; 2: Entire plant wilting; 3: Plant permanently collapsed; 4: Stem vascular browning; 5: Tuber ring rot.",
            ipmIntervention = "Rogue and incinerate infected plants with adhering soil. Disinfect boots and jembes with 10% household bleach. Crop rotation with non-solanaceous crops.",
            urgencyLevel = "Critical"
        ),
        ScoutingProtocolItem(
            id = "french_beans_thrips",
            cropName = "French Beans",
            threatName = "Flower Thrips (Megalurothrips sjostedti)",
            scientificName = "Megalurothrips sjostedti",
            threatType = "PEST",
            samplingPattern = "Shake 10 flower buds or open flowers over a white tray at 5 points in field.",
            samplingUnit = "Flower buds, open flowers, and tiny pin-pods.",
            actionThreshold = "Pre-flowering: >2 thrips/flower bud. Flowering: >3 thrips/open flower or first silver scarring on pods.",
            severityScaleGuide = "1: Flower bud drop; 2: Petal browning; 3: Pod corking & curling; 4: Silver scarring; 5: Complete unmarketable pods.",
            ipmIntervention = "Install blue sticky traps. Spray Spinetoram (Radiant 120SC) at 6ml/20L knapsack or Acetamiprid (Profile 444EC) early morning when flowers open.",
            urgencyLevel = "High"
        ),
        ScoutingProtocolItem(
            id = "coffee_berry_borer",
            cropName = "Coffee",
            threatName = "Coffee Berry Borer (CBB)",
            scientificName = "Hypothenemus hampei",
            threatType = "PEST",
            samplingPattern = "Examine 30 trees per hectare, inspecting 1 branch in each canopy third (top, middle, bottom).",
            samplingUnit = "Green berries (80-120 days post-flowering).",
            actionThreshold = ">5% green berries with entry holes at the apex (calyx ring).",
            severityScaleGuide = "1: Pinhole at berry tip; 2: Female boring entry canal; 3: Endosperm devoured; 4: Premature berry drop; 5: Harvest beans degraded.",
            ipmIntervention = "Strip off all leftover dry berries (mummified berries). Spray Beauveria bassiana bio-fungicide or Chlorpyrifos when female beetles are boring.",
            urgencyLevel = "High"
        ),
        ScoutingProtocolItem(
            id = "cabbage_dbm",
            cropName = "Cabbage",
            threatName = "Diamondback Moth (DBM)",
            scientificName = "Plutella xylostella",
            threatType = "PEST",
            samplingPattern = "Examine 20 heads diagonally across block; shake outer leaves onto white paper.",
            samplingUnit = "Underside of wrapper leaves and developing heart.",
            actionThreshold = "Pre-heading: >1 larva per 2 plants. Heading stage: >1 larva per 5 plants (>20% heads with feeding).",
            severityScaleGuide = "1: Small windowpanes on leaves; 2: Holes between veins; 3: Heart leaves chewed; 4: Silken cocoons on leaves; 5: Stunted loose heads.",
            ipmIntervention = "Apply Bacillus thuringiensis (Dipel DF) at 20g/20L knapsack or Chlorantraniliprole (Coragen). Alternate chemical classes to avoid resistance.",
            urgencyLevel = "High"
        )
    )

    /**
     * Sample initial inspection rows to populate the scouting sheet on first launch.
     */
    fun getSampleInspectionRows(): List<ScoutingInspectionRow> = listOf(
        ScoutingInspectionRow(
            stationNumber = 1,
            plotLocation = "Block A - North Boundary",
            cropName = "Maize",
            threatName = "Fall Armyworm (Spodoptera frugiperda)",
            plantsSampled = 20,
            plantsInfested = 3,
            incidencePercent = 15.0,
            severityRating = 3,
            stageObserved = "Instar 2 larvae + frass in whorl",
            actionStatus = "ACTION REQUIRED",
            notes = "Windowing damage active. Knapsack spray with Escort 1.9EC scheduled for 5:30 PM."
        ),
        ScoutingInspectionRow(
            stationNumber = 2,
            plotLocation = "Block A - Center Quadrat",
            cropName = "Maize",
            threatName = "Fall Armyworm (Spodoptera frugiperda)",
            plantsSampled = 20,
            plantsInfested = 1,
            incidencePercent = 5.0,
            severityRating = 1,
            stageObserved = "Egg mass under leaf",
            actionStatus = "MONITOR (3 DAYS)",
            notes = "Predatory earwigs spotted nearby. Hand-crushed egg mass."
        ),
        ScoutingInspectionRow(
            stationNumber = 3,
            plotLocation = "Block B - Terraced Ridge",
            cropName = "Tomato",
            threatName = "Tomato Leafminer (Tuta Absoluta)",
            plantsSampled = 20,
            plantsInfested = 4,
            incidencePercent = 20.0,
            severityRating = 3,
            stageObserved = "Blotch leaf mines + adults in delta trap",
            actionStatus = "ACTION REQUIRED",
            notes = "Delta trap count: 9 moths/night. Spray Coragen 200SC 5ml/20L knapsack."
        ),
        ScoutingInspectionRow(
            stationNumber = 4,
            plotLocation = "Block C - Valley Flat",
            cropName = "Irish Potatoes",
            threatName = "Potato Late Blight",
            plantsSampled = 20,
            plantsInfested = 0,
            incidencePercent = 0.0,
            severityRating = 0,
            stageObserved = "No lesions visible",
            actionStatus = "CLEAR",
            notes = "Foliage healthy. Preventative Mancozeb applied 4 days ago."
        )
    )
}
