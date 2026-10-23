package com.example.data.map

import com.example.model.AgroEcologicalZone
import com.example.model.GeoPoint

object AgroZoneCatalog {

    val zones: List<AgroEcologicalZone> = listOf(
        AgroEcologicalZone(
            id = "central_highlands_tea_coffee",
            code = "AEZ II (LH1/UM1)",
            name = "Central Highlands High Potential Zone",
            localZoneName = "Mt. Kenya & Aberdare Highland Belt (Kiambu, Nyeri, Murang'a, Kirinyaga, Embu, Meru)",
            elevationRangeMeters = "1,600m - 2,500m ASL",
            annualRainfallMm = "1,400mm - 2,200mm (Bimodal: Long rains Mar-May, Short rains Oct-Dec)",
            soilType = "Humic Nitisols & Andosols",
            soilCharacteristics = "Deep, highly weathered dark red/brown volcanic clay loam. Rich in organic matter, slightly acidic (pH 5.0 - 6.2). Prone to phosphorus fixation.",
            primaryCrops = listOf("Tea", "Arabica Coffee", "Irish Potatoes", "Horticultural Brassicas", "French Beans", "Avocados", "Dairy Napier"),
            fertilizerAdvice = "High response to phosphate basal fertilizers (DAP 18:46:0 or Mavuno Basal 10:26:10). Acidifying CAN or ASN recommended for top-dressing. Regular agricultural lime (1-2 tons/ha every 3 years) essential for potato and cabbage yields.",
            drainageSlopeAdvice = "Steep hillside topography (>12% slope). Mandatory bench terracing (Fanya Juu/Fanya Chini), grass strips (Vetiver/Napier), and cutoff drains to prevent topsoil loss during heavy rains.",
            tileColorHex = 0xFF2E7D32 // Deep emerald green
        ),
        AgroEcologicalZone(
            id = "rift_valley_grain_belt",
            code = "AEZ III (LH2/UM2)",
            name = "Rift Valley Cereal & Dairy Belt",
            localZoneName = "Rift Valley Grain Basket (Nakuru, Uasin Gishu, Trans Nzoia, Narok)",
            elevationRangeMeters = "1,800m - 2,450m ASL",
            annualRainfallMm = "950mm - 1,450mm (Single prolonged to bimodal season)",
            soilType = "Mollic Andosols & Phaeozems",
            soilCharacteristics = "Porous, dark friable volcanic ash loam. High moisture retention, neutral to slightly acidic pH (5.8 - 6.8). Excellent nutrient holding capacity.",
            primaryCrops = listOf("Hybrid Maize (H6213, H629)", "Wheat", "Barley", "Canola", "Irish Potatoes", "Dry Field Beans", "Dairy Pastures"),
            fertilizerAdvice = "Balanced basal application: 50kg/ha DAP or YaraMila Power at planting. Topdress with CAN (26% N) at knee-high (V6) and early tasseling. Secondary zinc and sulfur supplementation boosts kernel filling.",
            drainageSlopeAdvice = "Gently undulating to rolling plains (2% - 8% slope). Broad-based terraces and contour plowing prevent sheet erosion. Good internal drainage minimizes waterlogging.",
            tileColorHex = 0xFF558B2F // Golden-olive lush green
        ),
        AgroEcologicalZone(
            id = "western_lake_victoria_basin",
            code = "AEZ II/III (LM1/LM2)",
            name = "Western Lake Victoria Agro-Zone",
            localZoneName = "Lake Victoria Moist Belt (Kakamega, Bungoma, Kisumu, Kisii, Busia)",
            elevationRangeMeters = "1,150m - 1,650m ASL",
            annualRainfallMm = "1,350mm - 1,950mm (Reliable, well-distributed throughout year)",
            soilType = "Ferralsols & Dystric Acrisols",
            soilCharacteristics = "Deep, strongly leached reddish-yellow clay loam. Low base saturation, acidic pH (4.8 - 5.8). High risk of Striga weed dormancy and phosphorus deficiency.",
            primaryCrops = listOf("Sugarcane", "Maize", "Common Beans", "Sweet Potatoes", "Cassava", "Bananas", "Paddy Rice (Ahero/Bunyala)"),
            fertilizerAdvice = "Use non-acidifying basal fertilizers (NPK 23:23:0 or Mavuno Planting with secondary calcium and magnesium). Incorporate agricultural lime or biochar. Push-Pull technology with Desmodium controls Striga and stem borers.",
            drainageSlopeAdvice = "Undulating hills with flat river floodplains. River floodplains require cambered beds and drainage ditches; uplands need contour bunds to manage torrential convective storms.",
            tileColorHex = 0xFF388E3C // Moist vibrant green
        ),
        AgroEcologicalZone(
            id = "eastern_semi_arid_belt",
            code = "AEZ IV (UM4/LM4)",
            name = "Eastern Semi-Arid Transitional Zone",
            localZoneName = "Lower Eastern Agro-Belt (Machakos, Makueni, Kitui, Lower Embu, Tharaka Nithi)",
            elevationRangeMeters = "800m - 1,500m ASL",
            annualRainfallMm = "500mm - 850mm (Unpredictable bimodal, short heavy convective bursts)",
            soilType = "Chromic Luvisols & Cambisols",
            soilCharacteristics = "Sandy clay loam with gravelly subsoils. Prone to surface crusting and compaction. Low organic matter (<1.5%), alkaline to neutral pH (6.2 - 7.5).",
            primaryCrops = listOf("Pigeon Peas (Mbaazi)", "Green Grams (Ndengu)", "Sorghum (Gadam)", "Finger Millet", "Cowpeas", "Mangoes", "Dryland Maize (Duma 43)"),
            fertilizerAdvice = "Apply composted farmyard manure (5-10 tons/ha) alongside moderate basal micro-dosing (NPK 17:17:17 at 25kg/ha). Foliar feeding with zinc and boron helps withstand mid-season dry spells.",
            drainageSlopeAdvice = "Rolling dissected hills with high runoff velocity. Construct Zai pits, tied contour ridges, and retention ditches (Ngolo) to trap every drop of rainwater within root zones.",
            tileColorHex = 0xFF8D6E63 // Warm sienna earth tone
        ),
        AgroEcologicalZone(
            id = "coastal_lowland_belt",
            code = "AEZ III/IV (CL3/CL4)",
            name = "Coastal Lowland Humid Belt",
            localZoneName = "Coast Agricultural Strip (Kilifi, Kwale, Taita Taveta Lowlands, Lamu)",
            elevationRangeMeters = "0m - 400m ASL",
            annualRainfallMm = "900mm - 1,300mm (High relative humidity, maritime influence)",
            soilType = "Arenosols & Coastal Ferralsols",
            soilCharacteristics = "Sandy to sandy loam, low nutrient holding capacity, high permeability. Rapid leaching of nitrogen and potassium. pH 6.0 - 7.2.",
            primaryCrops = listOf("Coconuts", "Cashew Nuts", "Cassava", "Citrus (Pixie Oranges)", "Bixa", "Watermelons", "Amaranth"),
            fertilizerAdvice = "Split topdressing into 3 to 4 smaller doses to prevent leaching losses. High potassium requirement for tree crops. Organic mulching critical to preserve moisture.",
            drainageSlopeAdvice = "Coastal plains and low sandstone ridges (0% - 4% slope). Highly permeable sands; minimal runoff risk but windbreak hedgerows needed to reduce maritime salt spray.",
            tileColorHex = 0xFF00897B // Coastal teal-green
        ),
        AgroEcologicalZone(
            id = "arid_agropastoral_belt",
            code = "AEZ V/VI (LM5/LM6)",
            name = "Arid & Semi-Arid Pastoral Belt (ASAL)",
            localZoneName = "Northern & Southern ASALs (Kajiado, Baringo, Isiolo, Garissa Riverine)",
            elevationRangeMeters = "300m - 1,200m ASL",
            annualRainfallMm = "250mm - 500mm (Low, highly erratic)",
            soilType = "Calcisols, Solonchaks & Fluvisols",
            soilCharacteristics = "Coarse sands to heavy cracking alluvial clays in river basins. Saline/sodic tendencies in depressions.",
            primaryCrops = listOf("Pasture Grasses (Cenchrus ciliaris)", "Fodder Sorghum", "Irrigated Onions & Melons (Perkerra/Garissa)", "Watermelons"),
            fertilizerAdvice = "Drip fertigation with soluble fertilizers (Urea, Potassium nitrate). Monitor soil electrical conductivity (EC) to avoid salt accumulation.",
            drainageSlopeAdvice = "Flat plains prone to flash flooding. Water harvesting earth pans and contour swales capture intermittent river runoffs.",
            tileColorHex = 0xFFA1887F // Earthy arid ochre
        )
    )

    val defaultZone: AgroEcologicalZone = zones[1] // Rift Valley

    /**
     * Determines the closest matching Kenyan Agro-Ecological Zone based on geographic coordinates.
     */
    fun findZoneForLocation(lat: Double, lng: Double): AgroEcologicalZone {
        return when {
            // Coastal region (east of 38.8°E and south of 1.0°S)
            lng > 38.8 && lat < -1.0 -> zones[4] // Coastal
            // Western Kenya (west of 35.0°E, 1.2°N to 1.2°S)
            lng < 35.0 && lat in -1.3..1.3 -> zones[2] // Western Lake Basin
            // Central Highlands (36.5°E to 37.8°E, 0.8°S to 1.4°S)
            lng in 36.5..37.8 && lat in -1.5..0.2 -> zones[0] // Central Highlands
            // Rift Valley Grain Belt (35.0°E to 36.5°E, 1.5°N to 1.8°S)
            lng in 35.0..36.5 && lat in -1.8..1.5 -> zones[1] // Rift Valley Grain Belt
            // Lower Eastern (37.2°E to 38.8°E, 0.5°S to 2.8°S)
            lng in 37.2..38.8 && lat in -2.8..0.5 -> zones[3] // Eastern Semi-Arid
            // ASAL / other arid zones
            lat > 1.5 || (lat < -1.5 && lng < 36.5) -> zones[5] // ASAL
            else -> zones[1] // Default to Rift Valley
        }
    }
}
