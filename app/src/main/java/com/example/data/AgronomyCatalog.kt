package com.example.data

import com.example.model.CropCategory
import com.example.model.CropFertilizerPlan
import com.example.model.FertilizerCalculationResult
import com.example.model.FertilizerPhase
import com.example.model.FertilizerProduct
import com.example.model.HerbicideTiming
import com.example.model.KenyaAgriculturalCounty
import com.example.model.KnapsackFertilizerResult
import com.example.model.RegisteredHerbicide
import com.example.model.WeedModel
import com.example.model.WeedType

object AgronomyCatalog {

    // =========================================================================
    // KENYAN FERTILIZER BRANDS & NPK RATIOS FOR PROPER DECISION MAKING
    // Sources: Yara East Africa, MEA Limited, Mavuno Fertilizers, Minjingu Mines, Haifa
    // =========================================================================
    val fertilizerProducts: List<FertilizerProduct> = listOf(
        // Yara East Africa Products
        FertilizerProduct(
            id = "yara_mila_power",
            name = "YaraMila Power",
            brand = "Yara East Africa",
            npkRatio = "14:14:24 + 1.2S + 0.1B + 0.1Zn",
            nPercent = 14.0,
            pPercent = 14.0,
            kPercent = 24.0,
            secondaryNutrients = "1.2% S, 0.1% B, 0.1% Zn",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Mildly Acidifying (contains balanced ammoniacal and nitrate nitrogen)",
            standardRatePerHa = "200 kg - 250 kg/ha (4 - 5 bags/ha)",
            decisionRule = "Best for horticulture, potatoes, tomatoes, and cut flowers requiring high potassium from day one for stem rigidity, tuber bulking, and disease resilience.",
            priceRangeKes = "KES 5,200 - 5,600"
        ),
        FertilizerProduct(
            id = "yara_mila_winner",
            name = "YaraMila Winner",
            brand = "Yara East Africa",
            npkRatio = "15:9:20 + 1.5S + 1.8Mg + B + Zn",
            nPercent = 15.0,
            pPercent = 9.0,
            kPercent = 20.0,
            secondaryNutrients = "1.5% S, 1.8% Mg, 0.02% B, 0.02% Zn",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Neutral to Mildly Acidifying",
            standardRatePerHa = "150 kg - 200 kg/ha (3 - 4 bags/ha)",
            decisionRule = "Optimal for brassicas (cabbage, sukuma wiki) and leafy vegetables where balanced nitrogen and high magnesium boost chlorophyll synthesis and rapid vegetative growth.",
            priceRangeKes = "KES 5,100 - 5,400"
        ),
        FertilizerProduct(
            id = "yara_mila_cereal",
            name = "YaraMila Cereal",
            brand = "Yara East Africa",
            npkRatio = "23:10:5 + 3S + 0.3Zn",
            nPercent = 23.0,
            pPercent = 10.0,
            kPercent = 5.0,
            secondaryNutrients = "3% S, 0.3% Zinc",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Mildly Acidifying",
            standardRatePerHa = "150 kg - 200 kg/ha (3 - 4 bags/ha)",
            decisionRule = "Tailored for maize, wheat, and barley in soils with moderate native phosphorus. Delivers early nitrogen push with zinc for strong seedling vigor.",
            priceRangeKes = "KES 4,800 - 5,200"
        ),
        FertilizerProduct(
            id = "yara_liva_nitrabor",
            name = "YaraLiva Nitrabor / Tropicote",
            brand = "Yara East Africa",
            npkRatio = "15.5:0:0 + 26% CaO + 0.3% B",
            nPercent = 15.5,
            pPercent = 0.0,
            kPercent = 0.0,
            secondaryNutrients = "26% Calcium (CaO), 0.3% Boron (B)",
            phase = FertilizerPhase.TOP_DRESSING_1,
            soilAcidImpact = "Alkalinizing / Buffering (Reduces soil acidity)",
            standardRatePerHa = "100 kg - 150 kg/ha (2 - 3 bags/ha)",
            decisionRule = "Essential top-dressing for tomatoes, peppers, and roses. Prevents Blossom End Rot (BER) in solanaceae, strengthens cell walls against pest puncture, and buffers acidic soils.",
            priceRangeKes = "KES 4,500 - 4,900"
        ),
        FertilizerProduct(
            id = "yara_bela_sulfan",
            name = "YaraBela Sulfan",
            brand = "Yara East Africa",
            npkRatio = "24:0:0 + 6% S + 10% CaO",
            nPercent = 24.0,
            pPercent = 0.0,
            kPercent = 0.0,
            secondaryNutrients = "6% Sulfur (S), 10% Calcium Oxide (CaO)",
            phase = FertilizerPhase.TOP_DRESSING_1,
            soilAcidImpact = "Non-Acidifying (Neutralizes acidity via calcium)",
            standardRatePerHa = "150 kg - 200 kg/ha (3 - 4 bags/ha)",
            decisionRule = "Superior maize and wheat top-dresser. Sulfur synchronizes with nitrogen for amino acid synthesis, yielding darker green foliage and fuller grain cobs.",
            priceRangeKes = "KES 4,600 - 4,950"
        ),

        // MEA Fertilizers Products
        FertilizerProduct(
            id = "mea_dap",
            name = "MEA DAP (Diammonium Phosphate)",
            brand = "MEA Fertilizers",
            npkRatio = "18:46:0",
            nPercent = 18.0,
            pPercent = 46.0,
            kPercent = 0.0,
            secondaryNutrients = "Traces",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Strongly Acidifying (Releases H+ ions during nitrification)",
            standardRatePerHa = "150 kg - 200 kg/ha (3 - 4 bags/ha)",
            decisionRule = "High-phosphate traditional planting fertilizer for cereals. CAUTION: In acidic soils (pH < 5.5 in Western Kenya/North Rift), continuous DAP acidifies soils. Combine with agricultural lime or switch to Mavuno/Minjingu.",
            priceRangeKes = "KES 4,200 - 4,800"
        ),
        FertilizerProduct(
            id = "mea_can",
            name = "MEA CAN (Calcium Ammonium Nitrate)",
            brand = "MEA Fertilizers",
            npkRatio = "26:0:0 + 10% CaO",
            nPercent = 26.0,
            pPercent = 0.0,
            kPercent = 0.0,
            secondaryNutrients = "10% Calcium (CaO)",
            phase = FertilizerPhase.TOP_DRESSING_1,
            soilAcidImpact = "Neutral to Non-Acidifying",
            standardRatePerHa = "150 kg - 200 kg/ha (3 - 4 bags/ha)",
            decisionRule = "Standard top-dressing fertilizer across Kenya. Provides fast-acting nitrate and sustained ammonium nitrogen without acidifying soil.",
            priceRangeKes = "KES 3,800 - 4,300"
        ),
        FertilizerProduct(
            id = "mea_npk_17",
            name = "MEA NPK 17:17:17",
            brand = "MEA Fertilizers",
            npkRatio = "17:17:17",
            nPercent = 17.0,
            pPercent = 17.0,
            kPercent = 17.0,
            secondaryNutrients = "Traces",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Mildly Acidifying",
            standardRatePerHa = "200 kg/ha (4 bags/ha)",
            decisionRule = "Standard balanced starter for vegetable transplanting, coffee, and fruit trees in soils with unknown fertility status.",
            priceRangeKes = "KES 4,400 - 4,900"
        ),
        FertilizerProduct(
            id = "mea_urea",
            name = "MEA Urea",
            brand = "MEA Fertilizers",
            npkRatio = "46:0:0",
            nPercent = 46.0,
            pPercent = 0.0,
            kPercent = 0.0,
            secondaryNutrients = "Nil",
            phase = FertilizerPhase.TOP_DRESSING_1,
            soilAcidImpact = "Moderately Acidifying",
            standardRatePerHa = "100 kg/ha (2 bags/ha)",
            decisionRule = "Most concentrated economical nitrogen source. Apply only into moist soil and incorporate immediately to avoid ammonia volatilization loss.",
            priceRangeKes = "KES 4,000 - 4,500"
        ),
        FertilizerProduct(
            id = "mea_npk_23",
            name = "MEA NPK 23:23:0",
            brand = "MEA Fertilizers",
            npkRatio = "23:23:0",
            nPercent = 23.0,
            pPercent = 23.0,
            kPercent = 0.0,
            secondaryNutrients = "Nil",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Moderately Acidifying",
            standardRatePerHa = "150 kg/ha (3 bags/ha)",
            decisionRule = "Excellent for maize and sorghum in potassium-rich volcanic soils (such as parts of Central and Rift Valley) where only N and P are deficient.",
            priceRangeKes = "KES 4,300 - 4,700"
        ),

        // Mavuno Fertilizers Products
        FertilizerProduct(
            id = "mavuno_planting",
            name = "Mavuno Planting NPK",
            brand = "Mavuno Fertilizers",
            npkRatio = "10:26:10 + 4S + 8Ca + 4Mg + B + Zn",
            nPercent = 10.0,
            pPercent = 26.0,
            kPercent = 10.0,
            secondaryNutrients = "4% S, 8% Ca, 4% Mg, 0.2% B, 0.5% Zn",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Buffering / Non-Acidifying (contains calcium and magnesium)",
            standardRatePerHa = "150 kg - 200 kg/ha (3 - 4 bags/ha)",
            decisionRule = "Specially blended for acidic, degraded African soils. Supplies 11 essential plant nutrients to eliminate hidden micro-deficiencies in maize and cereals.",
            priceRangeKes = "KES 4,100 - 4,600"
        ),
        FertilizerProduct(
            id = "mavuno_topdress",
            name = "Mavuno Topdressing",
            brand = "Mavuno Fertilizers",
            npkRatio = "26:0:0 + 4S + 10Ca",
            nPercent = 26.0,
            pPercent = 0.0,
            kPercent = 0.0,
            secondaryNutrients = "4% S, 10% Calcium",
            phase = FertilizerPhase.TOP_DRESSING_1,
            soilAcidImpact = "Non-Acidifying",
            standardRatePerHa = "150 kg/ha (3 bags/ha)",
            decisionRule = "Fortified top-dresser that boosts cereal nitrogen uptake with sulfur and calcium for stronger stems and higher test weight.",
            priceRangeKes = "KES 3,900 - 4,400"
        ),
        FertilizerProduct(
            id = "mavuno_horticulture",
            name = "Mavuno Horticulture",
            brand = "Mavuno Fertilizers",
            npkRatio = "14:18:15 + 3S + 6Ca + 2Mg + TE",
            nPercent = 14.0,
            pPercent = 18.0,
            kPercent = 15.0,
            secondaryNutrients = "3% S, 6% Ca, 2% Mg, B, Zn, Cu",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Neutral",
            standardRatePerHa = "200 kg - 250 kg/ha (4 - 5 bags/ha)",
            decisionRule = "Formulated for French beans, onions, cabbages, and tomatoes. Prevents micro-nutrient yellowing and boosts marketable head size.",
            priceRangeKes = "KES 4,400 - 4,900"
        ),

        // Minjingu Mines Products
        FertilizerProduct(
            id = "minjingu_mazao",
            name = "Minjingu Mazao Plus",
            brand = "Minjingu Mines",
            npkRatio = "0:31:0 + 38% CaO + 2% S",
            nPercent = 0.0,
            pPercent = 31.0,
            kPercent = 0.0,
            secondaryNutrients = "38% Calcium Oxide (CaO), 2% S, 2% MgO",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Strongly Buffering / Liming Effect",
            standardRatePerHa = "200 kg/ha (4 bags/ha)",
            decisionRule = "Natural organic reactive phosphate rock with high liming capacity (38% CaO). Best for acidic soils in Western Kenya and North Rift to raise soil pH naturally.",
            priceRangeKes = "KES 3,600 - 4,000"
        ),
        FertilizerProduct(
            id = "minjingu_nafaka",
            name = "Minjingu Nafaka Plus",
            brand = "Minjingu Mines",
            npkRatio = "10:20:10 + 5S + 8CaO + 1.2MgO + B + Zn",
            nPercent = 10.0,
            pPercent = 20.0,
            kPercent = 10.0,
            secondaryNutrients = "5% S, 8% CaO, 1.2% MgO, Boron, Zinc",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Non-Acidifying / Soil Restorative",
            standardRatePerHa = "200 kg/ha (4 bags/ha)",
            decisionRule = "Cost-effective fortified cereal starter designed for smallholder farmers contending with soil acidity and nutrient depletion.",
            priceRangeKes = "KES 3,700 - 4,200"
        ),

        // Sympal / Kelphos Products
        FertilizerProduct(
            id = "sympal_legumes",
            name = "Sympal Legumes Blend",
            brand = "Kelphos / MEA",
            npkRatio = "0:23:16 + 10Ca + 4S + 1Mg + 0.1B",
            nPercent = 0.0,
            pPercent = 23.0,
            kPercent = 16.0,
            secondaryNutrients = "10% Ca, 4% S, 1% Mg, 0.1% B, 0.1% Zn",
            phase = FertilizerPhase.BASAL_PLANTING,
            soilAcidImpact = "Non-Acidifying",
            standardRatePerHa = "125 kg/ha (2.5 bags/ha)",
            decisionRule = "Specifically designed for common beans, French beans, and cowpeas. Nitrogen-free to encourage symbiotic biological Rhizobium nitrogen fixation.",
            priceRangeKes = "KES 3,900 - 4,300"
        ),

        // Haifa Floriculture / Soluble Feeds
        FertilizerProduct(
            id = "haifa_multi_k",
            name = "Haifa Multi-K (Potassium Nitrate)",
            brand = "Haifa Group / Amiran",
            npkRatio = "13:0:46",
            nPercent = 13.0,
            pPercent = 0.0,
            kPercent = 46.0,
            secondaryNutrients = "100% Water Soluble",
            phase = FertilizerPhase.FOLIAR_FERTIGATION,
            soilAcidImpact = "Alkalinizing in Root Zone",
            standardRatePerHa = "50 kg - 100 kg/ha through drip lines",
            decisionRule = "Gold standard for cut-flower roses, greenhouse tomatoes, and bulb onions during flower initiation and bulbing. Maximizes stem thickness and bloom diameter.",
            priceRangeKes = "KES 7,500 - 8,200"
        ),
        FertilizerProduct(
            id = "haifa_poly_feed",
            name = "Haifa Poly-Feed 19:19:19 + ME",
            brand = "Haifa Group / Amiran",
            npkRatio = "19:19:19 + Fe + Mn + Zn + Cu + Mo",
            nPercent = 19.0,
            pPercent = 19.0,
            kPercent = 19.0,
            secondaryNutrients = "Chelated Micronutrients (EDTA)",
            phase = FertilizerPhase.FOLIAR_FERTIGATION,
            soilAcidImpact = "Neutral",
            standardRatePerHa = "2.5 kg - 5.0 kg/ha foliar spray",
            decisionRule = "Fully water-soluble foliar feed or fertigation feed for roses, carnations, and high-value vegetables under shade net/greenhouses.",
            priceRangeKes = "KES 6,800 - 7,400"
        ),
        FertilizerProduct(
            id = "yaravita_croplift",
            name = "YaraVita Croplift Bio",
            brand = "Yara East Africa",
            npkRatio = "15:8:26 + 1.2Mg + TE",
            nPercent = 15.0,
            pPercent = 8.0,
            kPercent = 26.0,
            secondaryNutrients = "1.2% Mg + Cu + Fe + Mn + Mo + Zn",
            phase = FertilizerPhase.FOLIAR_FERTIGATION,
            soilAcidImpact = "Foliar Buffered (Safe on Cuticle)",
            standardRatePerHa = "1.5 kg - 2.5 kg/ha in 250L-400L water",
            decisionRule = "Potassium-rich foliar booster for crop vigor, flowering surge, and potato/onion bulking.",
            priceRangeKes = "KES 1,400 - 1,800 per kg"
        ),
        FertilizerProduct(
            id = "easygro_vegetative",
            name = "Easygro Vegetative Foliar",
            brand = "Murphy / Twiga",
            npkRatio = "27:10:10 + Trace Elements",
            nPercent = 27.0,
            pPercent = 10.0,
            kPercent = 10.0,
            secondaryNutrients = "Full Chelated Micronutrients",
            phase = FertilizerPhase.FOLIAR_FERTIGATION,
            soilAcidImpact = "Neutral",
            standardRatePerHa = "1.0 - 1.5 kg/ha (40g in 20L knapsack)",
            decisionRule = "High-nitrogen surge for rapid leaf development in brassicas, french beans, and vegetative maize.",
            priceRangeKes = "KES 950 - 1,200 per kg"
        ),
        FertilizerProduct(
            id = "easygro_flower_fruit",
            name = "Easygro Flower & Fruit",
            brand = "Murphy / Twiga",
            npkRatio = "14:11:33 + Trace Elements",
            nPercent = 14.0,
            pPercent = 11.0,
            kPercent = 33.0,
            secondaryNutrients = "High Potassium + Micronutrients",
            phase = FertilizerPhase.FOLIAR_FERTIGATION,
            soilAcidImpact = "Neutral",
            standardRatePerHa = "1.5 - 2.0 kg/ha (50g in 20L knapsack)",
            decisionRule = "Essential potassium booster during flowering and fruit setting for tomatoes, peppers, and beans.",
            priceRangeKes = "KES 1,100 - 1,350 per kg"
        )
    )

    // =========================================================================
    // CROP FERTILIZER PLANS (KENYAN AGROECOLOGICAL ZONES)
    // =========================================================================
    val cropFertilizerPlans: List<CropFertilizerPlan> = listOf(
        CropFertilizerPlan(
            cropName = "Maize (Corn)",
            botanicalName = "Zea mays",
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetNPerHa = 100.0,
            targetPPerHa = 60.0,
            targetKPerHa = 30.0,
            recommendedBasalBrand = "Mavuno Planting NPK or YaraMila Cereal",
            basalRateKgPerHa = 175.0, // 3.5 bags/ha
            recommendedTopdressBrand = "YaraBela Sulfan or MEA CAN",
            topdressRateKgPerHa = 175.0, // 3.5 bags/ha
            splitSchedule = listOf(
                "Basal at planting: Apply in planting furrow/hole, mix with soil to prevent seed scorching.",
                "1st Top-Dress: 100 kg/ha when maize is knee-high (V6 stage, 4-6 weeks after emergence).",
                "2nd Top-Dress: 75 kg/ha just before tasseling (V10-V12 stage) if seasonal rainfall is adequate."
            ),
            acidityAlert = "If soil pH is below 5.5 (common in Western Kenya & North Rift), apply 2 to 4 tons/ha of agricultural lime 4 weeks before planting or substitute DAP with Minjingu Mazao Plus.",
            agronomicGuidance = listOf(
                "Never place raw seed directly on fertilizer granules to prevent chemical burn.",
                "Ensure soil is moist before top-dressing with CAN or Urea.",
                "In drought-prone zones, apply total nitrogen in split applications to reduce financial loss."
            )
        ),

        CropFertilizerPlan(
            cropName = "French Beans & Legumes",
            botanicalName = "Phaseolus vulgaris",
            cropCategory = CropCategory.LEGUMES,
            targetNPerHa = 30.0,
            targetPPerHa = 60.0,
            targetKPerHa = 40.0,
            recommendedBasalBrand = "Sympal Legumes Blend or Mavuno Planting",
            basalRateKgPerHa = 150.0,
            recommendedTopdressBrand = "YaraLiva Nitrabor (Calcium + Boron)",
            topdressRateKgPerHa = 75.0,
            splitSchedule = listOf(
                "Basal at planting: 150 kg/ha in seed furrow.",
                "Top-Dress: 75 kg/ha at first flower bud initiation (21-25 days after germination)."
            ),
            acidityAlert = "Inoculate seeds with Bio-Fix (Rhizobium phaseoli) at planting. Avoid excess nitrogen which inhibits root nodule formation and causes vegetative overgrowth.",
            agronomicGuidance = listOf(
                "Boron and calcium in YaraLiva Nitrabor prevent flower abortion and pod curvature.",
                "Phosphorus drives extensive root nodules for natural nitrogen fixation from air."
            )
        ),

        CropFertilizerPlan(
            cropName = "Irish Potatoes",
            botanicalName = "Solanum tuberosum",
            cropCategory = CropCategory.VEGETABLES,
            targetNPerHa = 120.0,
            targetPPerHa = 100.0,
            targetKPerHa = 150.0,
            recommendedBasalBrand = "YaraMila Power or MEA NPK 17:17:17",
            basalRateKgPerHa = 250.0,
            recommendedTopdressBrand = "YaraLiva Nitrabor or MEA CAN",
            topdressRateKgPerHa = 150.0,
            splitSchedule = listOf(
                "Basal at planting: 250 kg/ha incorporated in ridges before seed tuber placement.",
                "Top-Dress at hilling: 150 kg/ha at first earthing-up (when plants are 15-20 cm tall)."
            ),
            acidityAlert = "Potatoes prefer slightly acidic soils (pH 5.2 - 6.0) which naturally suppresses Common Scab (Streptomyces scabies). Do not over-lime.",
            agronomicGuidance = listOf(
                "High potassium is non-negotiable for tuber dry matter, starch density, and chip frying quality.",
                "Avoid high nitrogen after flowering to prevent hollow heart disease."
            )
        ),

        CropFertilizerPlan(
            cropName = "Tomatoes & Capsicums",
            botanicalName = "Solanum lycopersicum",
            cropCategory = CropCategory.VEGETABLES,
            targetNPerHa = 140.0,
            targetPPerHa = 100.0,
            targetKPerHa = 180.0,
            recommendedBasalBrand = "YaraMila Power or Mavuno Horticulture",
            basalRateKgPerHa = 200.0,
            recommendedTopdressBrand = "YaraLiva Nitrabor (Calcium Boron)",
            topdressRateKgPerHa = 150.0,
            splitSchedule = listOf(
                "Basal at transplant: 200 kg/ha in transplanting holes mixed with compost.",
                "Top-Dress 1: 75 kg/ha at 3 weeks after transplant (early vegetative).",
                "Top-Dress 2: 75 kg/ha at first fruit set (5-6 weeks).",
                "Foliar/Fertigation: Haifa Multi-K during peak fruit bulbing."
            ),
            acidityAlert = "Blossom End Rot (black sunken fruit base) is caused by Calcium deficiency under moisture stress. Nitrabor calcium prevents 100% of BER.",
            agronomicGuidance = listOf(
                "Do not use high-ammonium fertilizers during fruiting as ammonium ions compete with calcium uptake.",
                "Mulch beds to maintain uniform root zone moisture for smooth calcium absorption."
            )
        ),

        CropFertilizerPlan(
            cropName = "Wheat & Barley",
            botanicalName = "Triticum aestivum",
            cropCategory = CropCategory.CEREALS_GRAINS,
            targetNPerHa = 90.0,
            targetPPerHa = 50.0,
            targetKPerHa = 20.0,
            recommendedBasalBrand = "YaraMila Cereal or MEA DAP",
            basalRateKgPerHa = 150.0,
            recommendedTopdressBrand = "YaraBela Sulfan or MEA CAN",
            topdressRateKgPerHa = 150.0,
            splitSchedule = listOf(
                "Basal at drilling: 150 kg/ha drilled with seed using combine seed drill.",
                "Top-Dress: 150 kg/ha broadcasted at tillering stage (GS21-GS29) before stem elongation."
            ),
            acidityAlert = "Wheat is highly sensitive to aluminum toxicity in acidic soils (pH < 5.2). If planting in Timau, Narok, or Eldoret acid soils, lime or use Minjingu.",
            agronomicGuidance = listOf(
                "Sulfur in YaraBela Sulfan increases gluten protein content and grain hectoliter test weight.",
                "Apply post-emergence broadleaf herbicides before stem elongation stage."
            )
        ),

        CropFertilizerPlan(
            cropName = "Cut Flowers & Roses",
            botanicalName = "Rosa hybrida",
            cropCategory = CropCategory.ORNAMENTAL_CUT_FLOWER,
            targetNPerHa = 200.0,
            targetPPerHa = 80.0,
            targetKPerHa = 250.0,
            recommendedBasalBrand = "YaraMila Power + Haifa Multi-K",
            basalRateKgPerHa = 250.0,
            recommendedTopdressBrand = "YaraLiva Nitrabor + Poly-Feed Fertigation",
            topdressRateKgPerHa = 200.0,
            splitSchedule = listOf(
                "Bed Preparation: 250 kg/ha YaraMila Power worked into top 25cm soil.",
                "Weekly Fertigation: 15-20 kg/ha split via drip irrigation using Haifa Multi-K and Calcium Nitrate."
            ),
            acidityAlert = "Maintain substrate/soil pH strictly between 5.8 and 6.5. Monitor electrical conductivity (EC) daily (target EC 1.4 - 1.8 mS/cm).",
            agronomicGuidance = listOf(
                "Balanced calcium and potassium ratio yields long, thick export stems with large bloom heads.",
                "Foliar zinc and iron prevent interveinal chlorosis under alkaline water conditions."
            )
        ),

        CropFertilizerPlan(
            cropName = "Bulb Onions & Garlic",
            botanicalName = "Allium cepa",
            cropCategory = CropCategory.VEGETABLES,
            targetNPerHa = 110.0,
            targetPPerHa = 90.0,
            targetKPerHa = 120.0,
            recommendedBasalBrand = "YaraMila Winner or Mavuno Horticulture",
            basalRateKgPerHa = 200.0,
            recommendedTopdressBrand = "YaraLiva Nitrabor or MEA CAN",
            topdressRateKgPerHa = 150.0,
            splitSchedule = listOf(
                "Basal at transplant: 200 kg/ha in furrows.",
                "Top-Dress 1: 75 kg/ha at 4 weeks after transplant.",
                "Top-Dress 2: 75 kg/ha at bulb initiation (8 weeks)."
            ),
            acidityAlert = "Stop all nitrogen top-dressing 4 weeks before harvest to ensure thick papery storage skins and prevent neck rot.",
            agronomicGuidance = listOf(
                "Sulfur is vital for onion pungency, allicin concentration, and long shelf life.",
                "Potassium ensures tight bulb scales and prevents post-harvest storage rot."
            )
        ),

        CropFertilizerPlan(
            cropName = "Cabbages & Sukuma Wiki (Brassicas)",
            botanicalName = "Brassica oleracea",
            cropCategory = CropCategory.VEGETABLES,
            targetNPerHa = 130.0,
            targetPPerHa = 80.0,
            targetKPerHa = 90.0,
            recommendedBasalBrand = "YaraMila Winner or MEA NPK 17:17:17",
            basalRateKgPerHa = 200.0,
            recommendedTopdressBrand = "MEA CAN or YaraLiva Nitrabor",
            topdressRateKgPerHa = 150.0,
            splitSchedule = listOf(
                "Basal at transplant: 200 kg/ha in transplanting holes.",
                "Top-Dress 1: 75 kg/ha at 3 weeks after transplanting.",
                "Top-Dress 2: 75 kg/ha at head formation initiation (6 weeks)."
            ),
            acidityAlert = "In acidic soils, brassicas are highly susceptible to Clubroot disease (Plasmodiophora brassicae). Keep soil pH above 6.5 using lime.",
            agronomicGuidance = listOf(
                "Calcium prevents tipburn (internal leaf necrosis) in dense cabbage heads.",
                "Sufficient boron prevents hollow stem disorder in brassica crops."
            )
        )
    )

    // =========================================================================
    // CALCULATION LOGIC FOR FARMERS IN HECTARES
    // =========================================================================
    fun calculateFertilizer(
        cropName: String,
        farmSizeHectares: Double,
        customBasalProduct: FertilizerProduct? = null,
        customTopdressProduct: FertilizerProduct? = null
    ): FertilizerCalculationResult {
        val plan = cropFertilizerPlans.find { it.cropName == cropName } ?: cropFertilizerPlans.first()
        val basal = customBasalProduct ?: (fertilizerProducts.find { it.name.contains(plan.recommendedBasalBrand.take(8)) } ?: fertilizerProducts[0])
        val topdress = customTopdressProduct ?: (fertilizerProducts.find { it.name.contains(plan.recommendedTopdressBrand.take(8)) } ?: fertilizerProducts[4])

        val totalBasalKg = plan.basalRateKgPerHa * farmSizeHectares
        val totalTopdressKg = plan.topdressRateKgPerHa * farmSizeHectares

        val basalBags = totalBasalKg / basal.bagWeightKg
        val topdressBags = totalTopdressKg / topdress.bagWeightKg

        val totalN = (totalBasalKg * (basal.nPercent / 100.0)) + (totalTopdressKg * (topdress.nPercent / 100.0))
        val totalP = (totalBasalKg * (basal.pPercent / 100.0))
        val totalK = (totalBasalKg * (basal.kPercent / 100.0)) + (totalTopdressKg * (topdress.kPercent / 100.0))

        val decisionRationale = "For $cropName on ${String.format("%.2f", farmSizeHectares)} ha: ${basal.name} supplies ${basal.npkRatio} (${basal.decisionRule}). Top-dressing with ${topdress.name} (${topdress.npkRatio}) supplies nitrogen and secondary nutrients (${topdress.secondaryNutrients}) without adverse soil acidification."

        return FertilizerCalculationResult(
            farmSizeHectares = farmSizeHectares,
            farmSizeAcres = farmSizeHectares * 2.47105,
            cropName = cropName,
            selectedBasalProduct = basal,
            selectedTopdressProduct = topdress,
            basalKgTotal = totalBasalKg,
            basal50kgBags = basalBags,
            topdressKgTotal = totalTopdressKg,
            topdress50kgBags = topdressBags,
            totalNitrogenKg = totalN,
            totalPhosphateKg = totalP,
            totalPotassiumKg = totalK,
            applicationSchedule = plan.splitSchedule,
            soilAcidityManagement = plan.acidityAlert,
            brandDecisionRationale = decisionRationale
        )
    }

    // =========================================================================
    // KNAPSACK FERTILIZER CALCULATION (HECTARES, TANK REFILLS & DILUTION)
    // =========================================================================
    fun calculateKnapsackFertilizer(
        cropName: String,
        farmSizeHectares: Double,
        knapsackTankCapacityLiters: Double = 16.0,
        customBasalProduct: FertilizerProduct? = null,
        customTopdressProduct: FertilizerProduct? = null,
        customFoliarProduct: FertilizerProduct? = null
    ): KnapsackFertilizerResult {
        val plan = cropFertilizerPlans.find { it.cropName == cropName } ?: cropFertilizerPlans.first()
        val basal = customBasalProduct ?: (fertilizerProducts.find { it.name.contains(plan.recommendedBasalBrand.take(8)) } ?: fertilizerProducts[0])
        val topdress = customTopdressProduct ?: (fertilizerProducts.find { it.name.contains(plan.recommendedTopdressBrand.take(8)) } ?: fertilizerProducts[4])
        val foliar = customFoliarProduct ?: (fertilizerProducts.find { it.phase == FertilizerPhase.FOLIAR_FERTIGATION } ?: fertilizerProducts.last())

        // Standard water spray volume per hectare in Kenya
        val sprayVolumeLitersPerHa = when (plan.cropCategory) {
            CropCategory.ORNAMENTAL_CUT_FLOWER -> 500.0
            CropCategory.VEGETABLES -> 350.0
            CropCategory.LEGUMES -> 250.0
            CropCategory.CEREALS_GRAINS -> 200.0
            else -> 300.0
        }

        val totalWaterLitersNeeded = sprayVolumeLitersPerHa * farmSizeHectares
        val totalKnapsackTanks = totalWaterLitersNeeded / knapsackTankCapacityLiters

        // Standard foliar concentration: 4.0 g/L (64g per 16L tank; 80g per 20L tank)
        val foliarDosePerLiterGrams = 4.0
        val foliarDosePerTankGrams = foliarDosePerLiterGrams * knapsackTankCapacityLiters
        val totalFoliarKg = (totalWaterLitersNeeded * foliarDosePerLiterGrams) / 1000.0

        // Basal & topdress requirements
        val totalBasalKg = plan.basalRateKgPerHa * farmSizeHectares
        val totalTopdressKg = plan.topdressRateKgPerHa * farmSizeHectares
        val basalBags = totalBasalKg / basal.bagWeightKg
        val topdressBags = totalTopdressKg / topdress.bagWeightKg

        val totalN = (totalBasalKg * (basal.nPercent / 100.0)) + (totalTopdressKg * (topdress.nPercent / 100.0)) + (totalFoliarKg * (foliar.nPercent / 100.0))
        val totalP = (totalBasalKg * (basal.pPercent / 100.0)) + (totalFoliarKg * (foliar.pPercent / 100.0))
        val totalK = (totalBasalKg * (basal.kPercent / 100.0)) + (totalTopdressKg * (topdress.kPercent / 100.0)) + (totalFoliarKg * (foliar.kPercent / 100.0))

        val doseText = if (foliarDosePerTankGrams >= 1000.0) String.format("%.2f kg", foliarDosePerTankGrams / 1000.0) else String.format("%.0f g", foliarDosePerTankGrams)
        val tankTypeText = if (knapsackTankCapacityLiters >= 500.0) "tractor boom / central tank" else if (knapsackTankCapacityLiters >= 100.0) "motorized power sprayer" else "knapsack tank"
        val knapsackCalibration = "For ${String.format("%.2f", farmSizeHectares)} ha of $cropName: Mix $doseText of ${foliar.name} into each ${knapsackTankCapacityLiters.toInt()}L $tankTypeText. Spray evenly across foliage using calibrated nozzles at 2.0-2.5 bar pressure. Total sprayer tank refills required: ${String.format("%.1f", totalKnapsackTanks)} tanks (${String.format("%.0f", totalWaterLitersNeeded)} Liters total spray volume)."

        return KnapsackFertilizerResult(
            farmSizeHectares = farmSizeHectares,
            farmSizeAcres = farmSizeHectares * 2.47105,
            cropName = cropName,
            knapsackTankCapacityLiters = knapsackTankCapacityLiters,
            sprayVolumeLitersPerHa = sprayVolumeLitersPerHa,
            totalWaterLitersNeeded = totalWaterLitersNeeded,
            totalKnapsackTanks = totalKnapsackTanks,
            foliarDosePerLiterGrams = foliarDosePerLiterGrams,
            foliarDosePerTankGrams = foliarDosePerTankGrams,
            totalFoliarFertilizerKg = totalFoliarKg,
            selectedBasalProduct = basal,
            selectedTopdressProduct = topdress,
            selectedFoliarProduct = foliar,
            basalKgTotal = totalBasalKg,
            basal50kgBags = basalBags,
            topdressKgTotal = totalTopdressKg,
            topdress50kgBags = topdressBags,
            totalNitrogenKg = totalN,
            totalPhosphateKg = totalP,
            totalPotassiumKg = totalK,
            applicationTimeline = plan.splitSchedule,
            soilAcidityAdvice = plan.acidityAlert,
            knapsackCalibrationAdvice = knapsackCalibration
        )
    }

    // =========================================================================
    // KENYAN WEED MANAGEMENT & REGISTERED HERBICIDES DATABASE
    // All Types: Broadleaf, Grasses, Sedges, Parasitic
    // =========================================================================
    val kenyanWeeds: List<WeedModel> = listOf(
        // 1. Parasitic Weeds
        WeedModel(
            id = "striga",
            commonName = "Witchweed (Striga)",
            botanicalName = "Striga hermonthica / Striga asiatica",
            localKenyanNames = listOf("Kayongo (Luo)", "Oluyongo (Luhya)", "Emoto (Teso)"),
            type = WeedType.PARASITIC,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Sorghum", "Finger Millet", "Sugarcane"),
            competitiveImpact = "Devastating root parasitic weed in Lake Victoria basin and Western Kenya. Connects haustoria into cereal roots, draining nutrients and releasing phytotoxins causing stunting, chlorosis, and 40%–100% crop loss.",
            culturalManagement = listOf(
                "Push-Pull Technology: Intercrop maize with Desmodium uncinatum. Desmodium roots exude chemicals that stimulate suicidal Striga germination without providing a host.",
                "Crop Rotation: Rotate with trap crops (soybeans, cowpeas, groundnuts, cotton) for 3 seasons.",
                "Hand-pulling Striga flowers before seed set (each plant produces up to 50,000 viable seeds that persist for 15 years in soil)."
            ),
            imageResId = com.example.R.drawable.img_weed_striga,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "StrigAway (IR-Maize Seed Coating)",
                    registrant = "BASF / Seed Co Kenya",
                    activeIngredient = "Imazapyr 800 g/kg",
                    formulation = "Seed Dressing (WS)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective on Imazapyr-Resistant (IR) Maize varieties only",
                    dosagePerKnapsack = "Seed coated by certified seed producer",
                    dosagePerHectare = "Seed treatment rate",
                    applicationGuidelines = "Plant certified StrigAway IR-maize seed. As Striga attaches to roots, it absorbs the lethal herbicide dose and dies."
                ),
                RegisteredHerbicide(
                    tradeName = "2,4-D Amine 720 SL",
                    registrant = "Twiga Chemical Industries / Murphy",
                    activeIngredient = "2,4-D Dimethylamine 720 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Maize, Sorghum, Sugarcane",
                    dosagePerKnapsack = "50ml - 60ml in 20L knapsack",
                    dosagePerHectare = "1.5 L - 2.0 L/ha",
                    applicationGuidelines = "Spot-spray emerged purple Striga weed plants before flowering to kill the parasite without harming maize."
                )
            )
        ),

        WeedModel(
            id = "alectra",
            commonName = "Purple Witchweed (Alectra)",
            botanicalName = "Alectra vogelii",
            localKenyanNames = listOf("Alectra", "Kayongo mar Oganda (Luo)"),
            type = WeedType.PARASITIC,
            isPerennial = false,
            cropsAttacked = listOf("Cowpeas", "Pigeon Peas", "Common Beans", "Groundnuts", "Soybeans"),
            competitiveImpact = "Root parasite attacking legumes in semi-arid and mid-altitude zones of Eastern and Western Kenya. Causes severe yellowing, floral abortion, and poor pod filling.",
            culturalManagement = listOf(
                "Plant certified resistant cowpea and grain legume varieties.",
                "Intercrop legumes with trap crops (cereals like sorghum and maize) to exhaust seed bank.",
                "Uproot and burn flowering stems before capsules mature and shatter."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Basagran 480 SL",
                    registrant = "BASF East Africa",
                    activeIngredient = "Bentazon 480 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Beans, Cowpeas, Peas",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "2.0 L - 2.5 L/ha",
                    applicationGuidelines = "Post-emergence spray when parasite stems first penetrate soil surface."
                )
            )
        ),

        // 2. Broadleaf Weeds
        WeedModel(
            id = "blackjack",
            commonName = "Blackjack",
            botanicalName = "Bidens pilosa",
            localKenyanNames = listOf("Nyabaras (Luo)", "Kipkoleit (Kalenjin)", "Muchege (Kikuyu)", "Mbangikwa (Kamba)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Vegetables", "Tomatoes", "French Beans", "Cut Flowers", "Maize", "Potatoes"),
            competitiveImpact = "Ubiquitous fast-growing annual broadleaf weed. Competes aggressively for light, water, and nitrogen. Acts as an alternative host for tomato yellow leaf curl virus (TYLCV), thrips, and aphids.",
            culturalManagement = listOf(
                "Shallow cultivation / hoeing when seedlings are at 2-4 leaf stage.",
                "Stale seedbed technique: irrigate field 2 weeks before planting, let weed seeds germinate, then harrow or apply burndown herbicide.",
                "Organic mulching with dry straw (5cm-10cm depth) to suppress light germination."
            ),
            imageResId = com.example.R.drawable.img_weed_blackjack,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Dual Gold 960 EC",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "S-metolachlor 960 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Maize, French Beans, Potatoes, Vegetables",
                    dosagePerKnapsack = "40ml - 50ml in 20L knapsack",
                    dosagePerHectare = "1.0 L - 1.5 L/ha",
                    applicationGuidelines = "Spray on moist weed-free soil immediately after planting but before crop and weed emergence."
                ),
                RegisteredHerbicide(
                    tradeName = "Basagran 480 SL",
                    registrant = "BASF East Africa",
                    activeIngredient = "Bentazon 480 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in French Beans, Common Beans, Peas, Maize",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "2.0 L - 3.0 L/ha",
                    applicationGuidelines = "Foliar spray when blackjack is at 2-4 leaf stage and beans have at least 2 trifoliate leaves."
                )
            )
        ),

        WeedModel(
            id = "wandering_jew",
            commonName = "Wandering Jew / Benghal Dayflower",
            botanicalName = "Commelina benghalensis",
            localKenyanNames = listOf("Mukengeria (Kikuyu)", "Ndekwe (Luo)", "Kimeng'wet (Kalenjin)", "Gikengeria (Meru)"),
            type = WeedType.BROADLEAF,
            isPerennial = true,
            cropsAttacked = listOf("Legumes", "French Beans", "Maize", "Vegetables", "Potatoes", "Roses"),
            competitiveImpact = "Fleshy succulent weed that roots at every node. Produces underground cleistogamous flowers that set viable seed underground. Ordinary weeding merely breaks stems that re-root in damp soil.",
            culturalManagement = listOf(
                "Hand-rouging: must be gathered completely off the field and composted in pits; never leave chopped stems in furrows.",
                "Shade out with dense row spacing and vigorous cover crops.",
                "Deep moldboard plowing to bury subterranean seeds."
            ),
            imageResId = com.example.R.drawable.img_weed_wandering_jew,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Sencor 480 SC",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Metribuzin 480 g/L",
                    formulation = "Suspension Concentrate (SC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Potatoes, Tomatoes, Sugarcane",
                    dosagePerKnapsack = "30ml in 20L knapsack",
                    dosagePerHectare = "0.75 L - 1.0 L/ha",
                    applicationGuidelines = "Apply before weed emergence onto moist ridges."
                ),
                RegisteredHerbicide(
                    tradeName = "2,4-D Amine 720 SL",
                    registrant = "Twiga Chemical Industries",
                    activeIngredient = "2,4-D Dimethylamine 720 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Maize & Cereals",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "1.5 L/ha",
                    applicationGuidelines = "Apply when Commelina is actively growing; translocates to fleshy nodes."
                )
            )
        ),

        WeedModel(
            id = "mexican_marigold",
            commonName = "Mexican Marigold / Stinking Roger",
            botanicalName = "Tagetes minuta",
            localKenyanNames = listOf("Muvangi (Kikuyu)", "Ang'we (Luo)", "Kiplokwet (Kalenjin)", "Manuka (Kisii)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Wheat", "Legumes", "Horticulture", "Coffee"),
            competitiveImpact = "Tall strong-scented annual broadleaf weed (up to 2 meters). Depletes massive moisture and nutrients. Produces millions of sticky achenes that adhere to livestock and farm machinery.",
            culturalManagement = listOf(
                "Shallow inter-row hoeing before flowering.",
                "Crop rotation with dense ground-covering legumes (sweet potato, cowpeas).",
                "Note: In small quantities, roots exude alpha-terthienyl which suppresses root-knot nematodes."
            ),
            imageResId = com.example.R.drawable.img_weed_marigold,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Lumax 537.5 SE",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Mesotrione 37.5 g/L + S-metolachlor 375 g/L + Terbuthylazine 125 g/L",
                    formulation = "Suspo-Emulsion (SE)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Maize & Sugarcane",
                    dosagePerKnapsack = "100ml in 20L knapsack",
                    dosagePerHectare = "3.5 L - 4.0 L/ha",
                    applicationGuidelines = "Premium one-shot selective pre-emergence herbicide providing season-long broadleaf and grass control."
                ),
                RegisteredHerbicide(
                    tradeName = "Buctril MC",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Bromoxynil 225 g/L + MCPA 225 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Wheat & Barley",
                    dosagePerKnapsack = "40ml in 20L knapsack",
                    dosagePerHectare = "1.25 L/ha",
                    applicationGuidelines = "Post-emergence broadleaf control in wheat from 3-leaf stage to flag leaf emergence."
                )
            )
        ),

        WeedModel(
            id = "pigweed",
            commonName = "Pigweed / Redroot Amaranth",
            botanicalName = "Amaranthus hybridus / Amaranthus spinosus",
            localKenyanNames = listOf("Omboga (Luo)", "Muchicha (Swahili)", "Terere (Kikuyu)", "Kelichot (Kalenjin)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Vegetables", "Tomatoes", "Legumes", "Maize", "Cabbage"),
            competitiveImpact = "Vigorous C4 plant with tremendous growth rate. Competes fiercely for nitrogen, shading out low-growing legumes and vegetable crops within 3 weeks.",
            culturalManagement = listOf(
                "Early shallow cultivation at seedling stage.",
                "Edible utilization: young tender shoots are harvested and eaten as traditional leafy greens in Kenya.",
                "Mulching in vegetable beds to prevent light-induced seed germination."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Stomp 455 CS",
                    registrant = "BASF East Africa",
                    activeIngredient = "Pendimethalin 455 g/L",
                    formulation = "Capsule Suspension (CS)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Onions, Garlic, French Beans, Brassicas, Maize",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "2.0 L - 2.5 L/ha",
                    applicationGuidelines = "Apply onto smooth moist soil within 2 days after transplanting or sowing before weed emergence."
                )
            )
        ),

        WeedModel(
            id = "gallant_soldier",
            commonName = "Gallant Soldier / McDonald's Eye",
            botanicalName = "Galinsoga parviflora",
            localKenyanNames = listOf("Mugoka (Kikuyu)", "Kanyanya (Kikuyu/Meru)", "Mukhanyanya (Luhya)", "Chepkomon (Kalenjin)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Potatoes", "Vegetables", "Tomatoes", "Carrots", "Flowers"),
            competitiveImpact = "Rapid lifecycle annual that flowers in only 4 weeks from seed. Infests fertile highland vegetable soils across Nyandarua, Limuru, and Kisii. Produces up to 3 generations per season.",
            culturalManagement = listOf(
                "Frequent hoeing before tiny white flower heads open.",
                "High density planting of leafy crops to shade out seedlings.",
                "Clean equipment before moving between plots."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Sencor 480 SC",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Metribuzin 480 g/L",
                    formulation = "Suspension Concentrate (SC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Potatoes & Tomatoes",
                    dosagePerKnapsack = "25ml - 30ml in 20L knapsack",
                    dosagePerHectare = "0.75 L/ha",
                    applicationGuidelines = "Spray ridges after planting potatoes prior to emergence."
                ),
                RegisteredHerbicide(
                    tradeName = "Basagran 480 SL",
                    registrant = "BASF East Africa",
                    activeIngredient = "Bentazon 480 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Beans and Peas",
                    dosagePerKnapsack = "50ml in 20L knapsack",
                    dosagePerHectare = "2.0 L/ha",
                    applicationGuidelines = "Spray early while Galinsoga plants are under 5 cm tall."
                )
            )
        ),

        WeedModel(
            id = "thorn_apple",
            commonName = "Thorn Apple / Jimsonweed",
            botanicalName = "Datura stramonium",
            localKenyanNames = listOf("Muvangi wa Ng'ombe (Kikuyu)", "Kwamalo (Luo)", "Kimoriet (Kalenjin)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Wheat", "Legumes", "Pasture", "Vegetables"),
            competitiveImpact = "Extremely poisonous solanaceous weed containing lethal tropane alkaloids (hyoscyamine, scopolamine). Severe hazard to livestock and contaminated cereal grain harvests.",
            culturalManagement = listOf(
                "Hand-pull using protective gloves before prickly seed capsules dry and open.",
                "Burn uprooted mature plants; never add to livestock feed or compost pits.",
                "Deep tillage to bury seeds beyond germination zone."
            ),
            imageResId = com.example.R.drawable.img_weed_jimsonweed,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Lumax 537.5 SE",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Mesotrione 37.5 g/L + S-metolachlor 375 g/L + Terbuthylazine 125 g/L",
                    formulation = "Suspo-Emulsion (SE)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Maize",
                    dosagePerKnapsack = "100ml in 20L knapsack",
                    dosagePerHectare = "4.0 L/ha",
                    applicationGuidelines = "Pre-emergence soil application; completely suppresses Datura seedling emergence."
                ),
                RegisteredHerbicide(
                    tradeName = "2,4-D Amine 720 SL",
                    registrant = "Twiga Chemical Industries",
                    activeIngredient = "2,4-D Dimethylamine 720 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Maize & Wheat",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "1.5 L/ha",
                    applicationGuidelines = "Post-emergence spray when Datura plants are at 2-6 leaf stage."
                )
            )
        ),

        WeedModel(
            id = "sodom_apple",
            commonName = "Sodom Apple / Apple of Sodom",
            botanicalName = "Solanum incanum",
            localKenyanNames = listOf("Ndongu (Kikuyu)", "Ochok (Luo)", "Labotiet (Kalenjin)", "Mutongu (Kamba)"),
            type = WeedType.BROADLEAF,
            isPerennial = true,
            cropsAttacked = listOf("Pastures", "Coffee", "Fallow Fields", "Maize Borders"),
            competitiveImpact = "Prickly perennial shrub with yellow poisonous fruits. Invades degraded pasturelands and field borders, harboring potato/tomato bacterial wilt and fruit borers.",
            culturalManagement = listOf(
                "Deep digging and removal of woody taproots (ordinary cutting causes vigorous stump re-sprouting).",
                "Pasture resting and reseeding with Rhodes grass (Chloris gayana).",
                "Mechanical clearing followed by targeted stump herbicide painting."
            ),
            imageResId = com.example.R.drawable.img_weed_sodom_apple,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Touchdown Forte 500 SL",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Glyphosate Potassium Salt 500 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.NON_SELECTIVE_BURNDOWN,
                    selectivity = "Non-Selective Systemic",
                    dosagePerKnapsack = "150ml in 20L knapsack",
                    dosagePerHectare = "3.0 L - 4.0 L/ha",
                    applicationGuidelines = "Cut shrub stems at 10cm above ground and paint cut surfaces immediately with 50% solution, or spray regrowth foliage."
                )
            )
        ),

        WeedModel(
            id = "double_thorn",
            commonName = "Double Thorn / Spiny Emex",
            botanicalName = "Oxygonum sinuatum",
            localKenyanNames = listOf("Mchongoma (Swahili)", "Kang'ei (Kikuyu)", "Kafupfu (Luhya)", "Chepkomol (Kalenjin)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Beans", "Vegetables", "Lawns"),
            competitiveImpact = "Low prostrate herb producing hard three-spined achenes that pierce bare feet and tractor tires. Competes with early emerging bean and vegetable seedlings.",
            culturalManagement = listOf(
                "Early shallow cultivation with a hand hoe before thorns harden.",
                "Use clean seed lots free of spiny burs.",
                "Dense crop canopy to suppress prostrate spreading."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "2,4-D Amine 720 SL",
                    registrant = "Twiga Chemical Industries",
                    activeIngredient = "2,4-D Dimethylamine 720 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Cereals",
                    dosagePerKnapsack = "50ml in 20L knapsack",
                    dosagePerHectare = "1.5 L/ha",
                    applicationGuidelines = "Post-emergence foliar spray on young prostrate rosettes."
                )
            )
        ),

        WeedModel(
            id = "creeping_woodsorrel",
            commonName = "Broadleaf Woodsorrel / Oxalis",
            botanicalName = "Oxalis latifolia / Oxalis corniculata",
            localKenyanNames = listOf("Nyonye (Kikuyu)", "Nsugwa (Luhya)", "Atego (Luo)"),
            type = WeedType.BROADLEAF,
            isPerennial = true,
            cropsAttacked = listOf("Vegetables", "French Beans", "Cut Flowers", "Nursery Beds"),
            competitiveImpact = "Clover-like weed producing dense underground clusters of tiny bulbils. Hoeing disperses the bulbils across the bed, aggravating the infestation.",
            culturalManagement = listOf(
                "Fork weeding: carefully lift the entire root crown with all bulbils attached onto a tray.",
                "Heavy black plastic mulch (solarization) to suffocate emerging shoots.",
                "Avoid rotary tilling which spreads thousands of bulbils."
            ),
            imageResId = com.example.R.drawable.img_weed_oxalis,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Roundup Turbo 450 SL",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Glyphosate Potassium Salt 450 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.NON_SELECTIVE_BURNDOWN,
                    selectivity = "Non-Selective",
                    dosagePerKnapsack = "120ml in 20L knapsack",
                    dosagePerHectare = "2.5 L/ha",
                    applicationGuidelines = "Apply during land prep when Oxalis is in full leaf; translocates to underground bulbils."
                )
            )
        ),

        // 3. Grasses
        WeedModel(
            id = "couch_grass",
            commonName = "African Couch Grass",
            botanicalName = "Digitaria scalarum",
            localKenyanNames = listOf("Ombogo (Luhya)", "Muthuthi (Kikuyu)", "Kwigweta (Kalenjin)", "Sangari (Kisii)"),
            type = WeedType.GRASS,
            isPerennial = true,
            cropsAttacked = listOf("Cut Flowers", "Roses", "Vegetables", "Maize", "Tea", "Coffee"),
            competitiveImpact = "Notorious perennial grass producing extensive rhizome networks beneath the soil surface. Depletes soil moisture and releases allelopathic toxins that inhibit crop root growth.",
            culturalManagement = listOf(
                "Fork weeding: dig out underground white rhizomes intact using garden forks; never use disc harrows which slice rhizomes into thousands of viable fragments.",
                "Solarization of greenhouse flower beds under transparent 150-micron polyethylene film for 6 weeks.",
                "Exhaustion tillage: repeatedly discing every 2 weeks during hot dry weather to dehydrate rhizomes on soil surface."
            ),
            imageResId = com.example.R.drawable.img_weed_couch_grass,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Fusilade Forte 150 EC",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Fluazifop-P-butyl 150 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Highly Selective in Broadleaf Crops (Roses, Cut Flowers, French Beans, Tomatoes, Potatoes)",
                    dosagePerKnapsack = "40ml - 50ml in 20L knapsack",
                    dosagePerHectare = "1.5 L - 2.0 L/ha",
                    applicationGuidelines = "Completely safe over-the-top of broadleaf crops and flowers. Systemically translocates into couch grass rhizomes and stops grass growth in 48 hours."
                ),
                RegisteredHerbicide(
                    tradeName = "Roundup Turbo 450 SL",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Glyphosate Potassium Salt 450 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.NON_SELECTIVE_BURNDOWN,
                    selectivity = "Non-Selective (Total Systemic Herbicide)",
                    dosagePerKnapsack = "100ml - 150ml in 20L knapsack",
                    dosagePerHectare = "2.5 L - 3.0 L/ha",
                    applicationGuidelines = "Pre-planting land preparation only. Spray onto actively growing lush green couch grass before sowing."
                )
            )
        ),

        WeedModel(
            id = "wild_oats",
            commonName = "Wild Oats & Darnel Ryegrass",
            botanicalName = "Avena fatua / Lolium temulentum",
            localKenyanNames = listOf("Ngano Mwitu (Swahili)", "Ngano ya Nyika"),
            type = WeedType.GRASS,
            isPerennial = false,
            cropsAttacked = listOf("Wheat", "Barley", "Oats"),
            competitiveImpact = "Major problem weed in wheat belts of Narok, Mau Narok, Timau, and Uasin Gishu. Mimics wheat growth, causing massive grain yield reductions and docking fees at flour millers due to seed contamination.",
            culturalManagement = listOf(
                "Plant certified clean wheat seed guaranteed free from wild oat grains.",
                "Rotate wheat with broadleaf crops (canola, potatoes, peas) for 2 seasons to break the grass seed cycle.",
                "Clean combine harvesters thoroughly between fields."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Topik 080 EC",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Clodinafop-propargyl 80 g/L + Cloquintocet-mexyl safener",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Wheat (Not for use in Barley)",
                    dosagePerKnapsack = "15ml - 20ml in 20L knapsack",
                    dosagePerHectare = "0.25 L - 0.3 L/ha",
                    applicationGuidelines = "Post-emergence grass killer in wheat when wild oats are at 2-leaf to tillering stage."
                ),
                RegisteredHerbicide(
                    tradeName = "Puma Super 069 EW",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Fenoxaprop-P-ethyl 69 g/L + Mefenpyr-diethyl safener",
                    formulation = "Emulsion in Water (EW)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Wheat & Barley",
                    dosagePerKnapsack = "30ml in 20L knapsack",
                    dosagePerHectare = "0.75 L - 1.0 L/ha",
                    applicationGuidelines = "Safe on both wheat and barley for controlling wild oats and darnel."
                )
            )
        ),

        WeedModel(
            id = "goosegrass",
            commonName = "Goosegrass / Wiregrass",
            botanicalName = "Eleusine indica",
            localKenyanNames = listOf("Kiriita (Kikuyu)", "Nyasore (Luo)", "Kipkandolit (Kalenjin)"),
            type = WeedType.GRASS,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Legumes", "Vegetables", "Cut Flowers"),
            competitiveImpact = "Tough fibrous grass with exceptionally strong root anchorage that resists hand weeding. Competes with crop seedlings for water and nutrients.",
            culturalManagement = listOf(
                "Early weeding while root system is small; once established, hoe blades often bend against the wire-like crown.",
                "Mulch vegetable beds to restrict seedling illumination.",
                "Pre-emergence herbicide protection."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Dual Gold 960 EC",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "S-metolachlor 960 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Maize, Beans, Vegetables",
                    dosagePerKnapsack = "40ml in 20L knapsack",
                    dosagePerHectare = "1.0 L - 1.5 L/ha",
                    applicationGuidelines = "Apply pre-emergence immediately after planting onto moist seedbeds."
                ),
                RegisteredHerbicide(
                    tradeName = "Agil 100 EC",
                    registrant = "ADAMA East Africa",
                    activeIngredient = "Propaquizafop 100 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Broadleaf Crops (Beans, Vegetables, Potatoes, Flowers)",
                    dosagePerKnapsack = "30ml in 20L knapsack",
                    dosagePerHectare = "0.75 L - 1.0 L/ha",
                    applicationGuidelines = "Post-emergence grass killer; safe over broadleaf crops."
                )
            )
        ),

        // 4. Sedges
        WeedModel(
            id = "nutgrass",
            commonName = "Nutgrass / Yellow Nutsedge",
            botanicalName = "Cyperus esculentus / Cyperus rotundus",
            localKenyanNames = listOf("Ndago (Kikuyu)", "Ndago / Sirira (Luo)", "Ndororit (Kalenjin)"),
            type = WeedType.SEDGE,
            isPerennial = true,
            cropsAttacked = listOf("Vegetables", "Tomatoes", "Onions", "Potatoes", "Roses & Cut Flowers", "Maize"),
            competitiveImpact = "Extremely aggressive perennial sedge with triangular stems and underground chains of nut-like tubers. Penetrates mulch, plastic sheeting, and potato tubers, reducing market quality.",
            culturalManagement = listOf(
                "Repeated deep cultivation during dry hot months to expose underground tubers to desiccation by the sun.",
                "Avoid chopping tubers with rotavators which multiplies and spreads the weed chains.",
                "Dense cover crops (Mucuna / Velvet bean) to shade out sedges."
            ),
            imageResId = com.example.R.drawable.img_weed_nutgrass,
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Sempra 75 WG",
                    registrant = "UPL East Africa",
                    activeIngredient = "Halosulfuron-methyl 750 g/kg",
                    formulation = "Water Dispersible Granules (WG)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Maize, Sugarcane (Directed spray in Vegetables)",
                    dosagePerKnapsack = "2.5g in 20L knapsack",
                    dosagePerHectare = "50g/ha",
                    applicationGuidelines = "Translocates through leaves down to the underground tubers, killing the entire vegetative root chain."
                ),
                RegisteredHerbicide(
                    tradeName = "Basagran 480 SL",
                    registrant = "BASF East Africa",
                    activeIngredient = "Bentazon 480 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Beans, Maize, Rice",
                    dosagePerKnapsack = "60ml - 80ml in 20L knapsack",
                    dosagePerHectare = "2.5 L/ha",
                    applicationGuidelines = "Apply when sedge is actively growing at 10cm-15cm height in full sunlight."
                )
            )
        ),

        WeedModel(
            id = "parthenium",
            commonName = "Parthenium / Famine Weed / Santa Maria",
            botanicalName = "Parthenium hysterophorus",
            localKenyanNames = listOf("Muguka Mwitu", "Nyasore Marachar (Luo)", "Kavukuvuku"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Sorghum", "Pastures", "Vegetables", "Sugarcane"),
            competitiveImpact = "Extremely aggressive invasive alien weed rapidly colonizing Kenya's arable lands and roadsides. Produces allelopathic parthenin that inhibits crop germination, causes severe contact dermatitis/allergies in humans, and renders cattle milk bitter and toxic.",
            culturalManagement = listOf(
                "Mechanical removal with full PPE (gloves, face mask) before flowering; do NOT touch with bare hands.",
                "Salt and vinegar contact spray on roadsides and field borders.",
                "Establish competitive fodder grasses like Rhodes grass (Chloris gayana) to choke out seedlings."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Lumax 537.5 SE",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Mesotrione 37.5 g/L + S-metolachlor 375 g/L + Terbuthylazine 125 g/L",
                    formulation = "Suspo-Emulsion (SE)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Maize & Sugarcane",
                    dosagePerKnapsack = "100ml in 20L knapsack",
                    dosagePerHectare = "3.5 L - 4.0 L/ha",
                    applicationGuidelines = "Pre-emergence herbicide providing residual suppression of emerging Parthenium."
                ),
                RegisteredHerbicide(
                    tradeName = "2,4-D Amine 720 SL",
                    registrant = "Twiga Chemical Industries",
                    activeIngredient = "2,4-D Dimethylamine 720 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Cereals & Grass pastures",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "1.5 L - 2.0 L/ha",
                    applicationGuidelines = "Spray early at rosette or young vegetative stage before flowering heads mature."
                )
            )
        ),

        WeedModel(
            id = "lantana",
            commonName = "Lantana / Tickberry",
            botanicalName = "Lantana camara",
            localKenyanNames = listOf("Magarada (Kikuyu)", "Kanyanja (Meru)", "Atek (Luo)", "Mwekundu (Swahili)"),
            type = WeedType.BROADLEAF,
            isPerennial = true,
            cropsAttacked = listOf("Coffee", "Tea", "Pastures", "Forest Boundaries", "Fruit Orchards"),
            competitiveImpact = "Woody sprawling shrub forming impenetrable thickets in coffee and tea zones. Displaces native flora, harbors tsetse flies, and contains lantadene triterpenoids causing photosensitization, acute jaundice, and death in grazing livestock.",
            culturalManagement = listOf(
                "Stump eradication: uproot the central root collar using a mattock; simple slashing prompts rapid multi-stem regrowth.",
                "Cut-stump application with herbicide immediately after chopping.",
                "Revegetation with aggressive pasture grasses."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Touchdown Forte 500 SL",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "Glyphosate Potassium Salt 500 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.NON_SELECTIVE_BURNDOWN,
                    selectivity = "Non-Selective Systemic Brushkiller",
                    dosagePerKnapsack = "150ml - 200ml in 20L knapsack",
                    dosagePerHectare = "4.0 L - 5.0 L/ha",
                    applicationGuidelines = "Slash stems 15cm from soil and paint cut surface with 30% solution within 10 minutes, or spray lush regrowth at 50cm height."
                )
            )
        ),

        WeedModel(
            id = "itch_grass",
            commonName = "Itch Grass / Shamva Grass",
            botanicalName = "Rottboellia cochinchinensis",
            localKenyanNames = listOf("Nyasore Magweno (Luo)", "Mwiko (Luhya)", "Kipkandolit (Kalenjin)"),
            type = WeedType.GRASS,
            isPerennial = false,
            cropsAttacked = listOf("Maize", "Sugarcane", "Sorghum", "Upland Rice"),
            competitiveImpact = "Fierce annual grass growing up to 3 meters tall. Leaf sheaths are covered in brittle siliceous glass-fiber hairs that penetrate farmworkers' skin, causing severe itching and infection. Seeds shatter early and reduce maize yields by up to 80%.",
            culturalManagement = listOf(
                "Clean equipment before moving between fields to prevent moving seed heads.",
                "False seedbed: cultivate to stimulate germination, then harrow before planting crop.",
                "Crop rotation with dense velvet bean (Mucuna pruriens) to smother grass seedlings."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Stellar Star",
                    registrant = "BASF East Africa",
                    activeIngredient = "Topramezone 50 g/L + Dicamba 160 g/L",
                    formulation = "Soluble Concentrate (SC)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Maize",
                    dosagePerKnapsack = "50ml in 20L knapsack + Dash Adjuvant",
                    dosagePerHectare = "1.0 L/ha",
                    applicationGuidelines = "Foliar post-emergence spray when Rottboellia is at 2-4 leaf stage and actively growing."
                ),
                RegisteredHerbicide(
                    tradeName = "Dual Gold 960 EC",
                    registrant = "Syngenta East Africa Ltd",
                    activeIngredient = "S-metolachlor 960 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Maize & Sugarcane",
                    dosagePerKnapsack = "50ml in 20L knapsack",
                    dosagePerHectare = "1.5 L/ha",
                    applicationGuidelines = "Apply immediately following sowing before itch grass shoots break soil surface."
                )
            )
        ),

        WeedModel(
            id = "black_nightshade",
            commonName = "Black Nightshade",
            botanicalName = "Solanum nigrum",
            localKenyanNames = listOf("Managu (Kikuyu)", "Osuga (Luo)", "Sucha (Luhya)", "Kiseveve (Kamba)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Irish Potatoes", "Tomatoes", "French Beans", "Cabbage", "Peas"),
            competitiveImpact = "Common agricultural weed in highland vegetable belts. Acts as a key alternate host and reservoir for late blight (Phytophthora infestans), potato leafroll virus, and tomato fruit borers.",
            culturalManagement = listOf(
                "Shallow hoeing during early vegetative stage.",
                "Sanitation: remove all solanaceous weeds around potato/tomato plots to eliminate pest bridges.",
                "Note: Certain cultivated morphotypes are grown and harvested as traditional indigenous vegetables in Kenya."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Sencor 480 SC",
                    registrant = "Bayer East Africa Ltd",
                    activeIngredient = "Metribuzin 480 g/L",
                    formulation = "Suspension Concentrate (SC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Irish Potatoes & Tomatoes",
                    dosagePerKnapsack = "30ml in 20L knapsack",
                    dosagePerHectare = "0.75 L - 1.0 L/ha",
                    applicationGuidelines = "Spray ridges after planting potatoes prior to crop sprout emergence."
                ),
                RegisteredHerbicide(
                    tradeName = "Basagran 480 SL",
                    registrant = "BASF East Africa",
                    activeIngredient = "Bentazon 480 g/L",
                    formulation = "Soluble Liquid (SL)",
                    timing = HerbicideTiming.POST_EMERGENCE,
                    selectivity = "Selective in Legumes & French Beans",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "2.0 L - 2.5 L/ha",
                    applicationGuidelines = "Post-emergence spray when black nightshade is under 8cm in height."
                )
            )
        ),

        WeedModel(
            id = "purslane",
            commonName = "Common Purslane / Pigweed Moss",
            botanicalName = "Portulaca oleracea",
            localKenyanNames = listOf("Matoro (Luo)", "Mwoga (Swahili)", "Ndururu (Kikuyu)"),
            type = WeedType.BROADLEAF,
            isPerennial = false,
            cropsAttacked = listOf("Tomatoes", "Onions", "Carrots", "Brassicas", "French Beans"),
            competitiveImpact = "Succulent prostrate broadleaf herb forming dense red-stemmed mats. Exceptional drought tolerance; severed stems easily regenerate roots in moist soil, surviving routine mechanical hoeing.",
            culturalManagement = listOf(
                "Hand-collection: gather uprooted mats off the vegetable field; do not leave on soil surface.",
                "Organic or black plastic mulching in vegetable beds to suppress prostrate spreading.",
                "Cultivate during hot sunny midday hours so exposed uprooted stems dry out and desiccate."
            ),
            registeredHerbicides = listOf(
                RegisteredHerbicide(
                    tradeName = "Stomp 455 CS",
                    registrant = "BASF East Africa",
                    activeIngredient = "Pendimethalin 455 g/L",
                    formulation = "Capsule Suspension (CS)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Onions, Garlic, French Beans, Brassicas",
                    dosagePerKnapsack = "60ml in 20L knapsack",
                    dosagePerHectare = "2.0 L - 2.5 L/ha",
                    applicationGuidelines = "Apply onto smooth moist soil surface immediately after transplanting or sowing."
                ),
                RegisteredHerbicide(
                    tradeName = "Goal 240 EC",
                    registrant = "Corteva Agriscience Kenya",
                    activeIngredient = "Oxyfluorfen 240 g/L",
                    formulation = "Emulsifiable Concentrate (EC)",
                    timing = HerbicideTiming.PRE_EMERGENCE,
                    selectivity = "Selective in Onions and Garlic",
                    dosagePerKnapsack = "40ml in 20L knapsack",
                    dosagePerHectare = "1.0 L/ha",
                    applicationGuidelines = "Excellent pre-emergence control of purslane in bulb onion fields."
                )
            )
        )
    )

    // =========================================================================
    // KENYA AGRICULTURAL HUBS & COORDINATES (FOR WEATHER)
    // =========================================================================
    val kenyanCounties: List<KenyaAgriculturalCounty> = listOf(
        KenyaAgriculturalCounty("Nakuru (Njoro / Molo)", "Rift Valley Highland", -0.3031, 36.0800, "Potatoes, Maize, Wheat, Vegetables, Flowers"),
        KenyaAgriculturalCounty("Uasin Gishu (Eldoret)", "North Rift Cereal Belt", 0.5143, 35.2698, "Maize, Wheat, Dairy, Legumes"),
        KenyaAgriculturalCounty("Trans Nzoia (Kitale)", "North Rift Grain Basket", 1.0167, 35.0000, "Maize seed, Beans, Sunflower"),
        KenyaAgriculturalCounty("Kiambu (Thika / Limuru)", "Central Highlands", -1.0333, 37.0694, "Roses, Cut Flowers, Coffee, French Beans, Cabbages"),
        KenyaAgriculturalCounty("Nyandarua (Ol Kalou)", "Aberdares / Highland", -0.2667, 36.3833, "Potatoes, Cabbage, Peas, Carrots"),
        KenyaAgriculturalCounty("Meru (Imenti / Buuri)", "Mt. Kenya Slopes", 0.0500, 37.6500, "Potatoes, French Beans, Wheat, Bananas, Tea"),
        KenyaAgriculturalCounty("Narok (Narok Town)", "South Rift Wheat Belt", -1.0833, 35.8667, "Wheat, Barley, Maize, Pasture"),
        KenyaAgriculturalCounty("Machakos (Machakos / Mwala)", "Eastern Semi-Arid", -1.5167, 37.2667, "Cowpeas, Pigeon Peas, Green Grams, Drought-tolerant Maize"),
        KenyaAgriculturalCounty("Kisumu (Lake Basin)", "Western / Lake Region", -0.1022, 34.7617, "Sugarcane, Sorghum, Rice, Maize, Cassava"),
        KenyaAgriculturalCounty("Kakamega / Bungoma", "Western Maize Belt", 0.2833, 34.7500, "Maize, Beans, Sugarcane, Sweet Potatoes")
    )
}
