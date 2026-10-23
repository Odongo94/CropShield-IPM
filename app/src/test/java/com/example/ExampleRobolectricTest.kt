package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.ThreatCatalog
import com.example.data.local.ThreatDatabaseMapper
import com.example.model.ThreatType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("CropShield IPM", appName)
    }

    @Test
    fun `threat catalog contains both pests and diseases with full lifecycles`() {
        val threats = ThreatCatalog.allThreats
        assertTrue("Threat catalog must not be empty", threats.isNotEmpty())

        val hasDisease = threats.any { it.type == ThreatType.DISEASE }
        val hasPest = threats.any { it.type == ThreatType.PEST }
        assertTrue("Must include diseases", hasDisease)
        assertTrue("Must include pests", hasPest)

        val lateBlight = ThreatCatalog.getThreatById("late_blight")
        assertNotNull("Late Blight must exist", lateBlight)
        assertEquals(5, lateBlight!!.lifecycleStages.size)
        assertTrue(lateBlight.ipmPlan.chemicalControls.isNotEmpty())

        val fallArmyworm = ThreatCatalog.getThreatById("fall_armyworm")
        assertNotNull("Fall Armyworm must exist", fallArmyworm)
        assertEquals(5, fallArmyworm!!.lifecycleStages.size)
    }

    @Test
    fun `room database mapper maps threat with lifecycle and chemicals accurately`() {
        val originalThreat = ThreatCatalog.getThreatById("late_blight")!!
        val dbBundle = ThreatDatabaseMapper.toEntities(originalThreat)

        assertEquals("late_blight", dbBundle.threat.id)
        assertEquals(5, dbBundle.stages.size)
        assertEquals(2, dbBundle.chemicals.size)
        assertTrue(dbBundle.nonChemicals.isNotEmpty())
    }

    @Test
    fun `offline filter works for specific crops and plant parts`() {
        val tomatoThreats = ThreatCatalog.filterThreats(crop = "Tomato")
        assertTrue(tomatoThreats.isNotEmpty())
        assertTrue(tomatoThreats.all { it.targetCrops.contains("Tomato") })

        val diseaseThreats = ThreatCatalog.filterThreats(type = ThreatType.DISEASE)
        assertTrue(diseaseThreats.all { it.type == ThreatType.DISEASE })
    }

    @Test
    fun `search by pest or disease name filters results accurately`() {
        val blightResults = ThreatCatalog.filterThreats(query = "Blight")
        assertTrue("Searching 'Blight' should match Late Blight and Early Blight", blightResults.size >= 2)
        assertTrue(blightResults.any { it.name.contains("Late Blight") })
        assertTrue(blightResults.any { it.name.contains("Early Blight") })

        val armywormResults = ThreatCatalog.filterThreats(query = "Armyworm")
        assertEquals(1, armywormResults.size)
        assertEquals("Fall Armyworm", armywormResults.first().name)
    }

    @Test
    fun `knapsack calculation formulas compute exact pesticide dosage and refills`() {
        val tankCapacityLiters = 16.0
        val concentrationMlPerLiter = 2.5
        val amountPerTank = tankCapacityLiters * concentrationMlPerLiter
        assertEquals(40.0, amountPerTank, 0.001)

        val powderTankLiters = 20.0
        val powderConcentrationGramsPerLiter = 3.0
        val powderAmountPerTank = powderTankLiters * powderConcentrationGramsPerLiter
        assertEquals(60.0, powderAmountPerTank, 0.001)

        val fieldAcres = 2.0
        val sprayVolumeLitersPerAcre = 110.0 // Standard canopy
        val totalWaterVolume = fieldAcres * sprayVolumeLitersPerAcre
        assertEquals(220.0, totalWaterVolume, 0.001)

        val totalTanksNeeded = totalWaterVolume / tankCapacityLiters
        assertEquals(13.75, totalTanksNeeded, 0.001)

        val totalPesticideRequired = totalWaterVolume * concentrationMlPerLiter
        assertEquals(550.0, totalPesticideRequired, 0.001)
    }

    @Test
    fun `fertilizer calculation accurately computes requirement in hectares with Kenyan brands`() {
        val maizeResult = com.example.data.AgronomyCatalog.calculateFertilizer(
            cropName = "Maize (Corn)",
            farmSizeHectares = 2.0
        )

        assertEquals(2.0, maizeResult.farmSizeHectares, 0.001)
        assertEquals(2.0 * 2.47105, maizeResult.farmSizeAcres, 0.01)
        assertTrue(maizeResult.basalKgTotal > 0)
        assertTrue(maizeResult.basal50kgBags > 0)
        assertTrue(maizeResult.topdressKgTotal > 0)
        assertTrue(maizeResult.totalNitrogenKg > 0)
        assertTrue(maizeResult.totalPhosphateKg > 0)
        assertTrue(maizeResult.applicationSchedule.isNotEmpty())
        assertTrue(maizeResult.soilAcidityManagement.contains("pH", ignoreCase = true))

        // Check fertilizer products list contains top Kenyan brands
        val brands = com.example.data.AgronomyCatalog.fertilizerProducts.map { it.brand }.toSet()
        assertTrue(brands.any { it.contains("Yara", ignoreCase = true) })
        assertTrue(brands.any { it.contains("MEA", ignoreCase = true) })
        assertTrue(brands.any { it.contains("Mavuno", ignoreCase = true) })
        assertTrue(brands.any { it.contains("Minjingu", ignoreCase = true) })
    }

    @Test
    fun `kenyan weeds catalog covers all weed types with management and herbicides`() {
        val weeds = com.example.data.AgronomyCatalog.kenyanWeeds
        assertTrue("Must contain weeds", weeds.size >= 8)

        val types = weeds.map { it.type }.toSet()
        assertTrue("Must contain parasitic weeds (e.g. Striga)", types.contains(com.example.model.WeedType.PARASITIC))
        assertTrue("Must contain broadleaf weeds", types.contains(com.example.model.WeedType.BROADLEAF))
        assertTrue("Must contain grassy weeds", types.contains(com.example.model.WeedType.GRASS))
        assertTrue("Must contain sedges", types.contains(com.example.model.WeedType.SEDGE))

        val striga = weeds.find { it.id == "striga" }
        assertNotNull("Striga must exist", striga)
        assertTrue(striga!!.localKenyanNames.any { it.contains("Kayongo") })
        assertTrue(striga.registeredHerbicides.isNotEmpty())
        assertTrue(striga.registeredHerbicides.any { it.activeIngredient.contains("2,4-D") || it.activeIngredient.contains("Imazapyr") })

        val couchGrass = weeds.find { it.id == "couch_grass" }
        assertNotNull("African Couch grass must exist", couchGrass)
        assertTrue(couchGrass!!.isPerennial)
        assertTrue(couchGrass.registeredHerbicides.any { it.tradeName.contains("Fusilade") })
    }

    @Test
    fun `knapsack fertilizer calculation computes tank refills foliar grams and bulk bags for hectares`() {
        val result16 = com.example.data.AgronomyCatalog.calculateKnapsackFertilizer(
            cropName = "Tomatoes & Capsicums",
            farmSizeHectares = 1.0,
            knapsackTankCapacityLiters = 16.0
        )

        assertEquals(1.0, result16.farmSizeHectares, 0.001)
        assertEquals(16.0, result16.knapsackTankCapacityLiters, 0.001)
        assertTrue("Water volume should be calculated", result16.totalWaterLitersNeeded > 0)
        assertTrue("Knapsack refills must be computed", result16.totalKnapsackTanks > 0)
        assertEquals(64.0, result16.foliarDosePerTankGrams, 0.001)
        assertTrue(result16.basal50kgBags > 0)
        assertTrue(result16.topdress50kgBags > 0)
        assertTrue(result16.knapsackCalibrationAdvice.contains("knapsack tank"))

        // Test with 20L tank
        val result20 = com.example.data.AgronomyCatalog.calculateKnapsackFertilizer(
            cropName = "Tomatoes & Capsicums",
            farmSizeHectares = 1.0,
            knapsackTankCapacityLiters = 20.0
        )
        assertEquals(80.0, result20.foliarDosePerTankGrams, 0.001)
        assertTrue(result20.totalKnapsackTanks < result16.totalKnapsackTanks)
    }

    @Test
    fun `theme mode supports SYSTEM, LIGHT, and DARK modes live`() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.viewmodel.CropShieldViewModel(app)

        assertEquals(com.example.ui.theme.AppThemeMode.SYSTEM, viewModel.themeMode.value)

        viewModel.setThemeMode(com.example.ui.theme.AppThemeMode.LIGHT)
        assertEquals(com.example.ui.theme.AppThemeMode.LIGHT, viewModel.themeMode.value)

        viewModel.setThemeMode(com.example.ui.theme.AppThemeMode.DARK)
        assertEquals(com.example.ui.theme.AppThemeMode.DARK, viewModel.themeMode.value)
    }

    @Test
    fun `weed catalog has images linked and expanded species available`() {
        val weeds = com.example.data.AgronomyCatalog.kenyanWeeds
        assertTrue(weeds.size >= 12)

        // Verify images exist for key weeds
        val striga = weeds.find { it.id == "striga" }
        assertNotNull(striga?.imageResId)

        val blackjack = weeds.find { it.id == "blackjack" }
        assertNotNull(blackjack?.imageResId)

        val wanderingJew = weeds.find { it.id == "wandering_jew" }
        assertNotNull(wanderingJew?.imageResId)

        val marigold = weeds.find { it.id == "mexican_marigold" }
        assertNotNull(marigold?.imageResId)

        val oxalis = weeds.find { it.id == "creeping_woodsorrel" }
        assertNotNull(oxalis?.imageResId)

        // Verify expanded species
        val parthenium = weeds.find { it.id == "parthenium" }
        assertNotNull("Parthenium must be in catalog", parthenium)
        assertTrue(parthenium!!.registeredHerbicides.isNotEmpty())

        val lantana = weeds.find { it.id == "lantana" }
        assertNotNull("Lantana must be in catalog", lantana)

        val itchGrass = weeds.find { it.id == "itch_grass" }
        assertNotNull("Itch grass must be in catalog", itchGrass)
    }

    @Test
    fun `universal search returns matches for disease, pest, weed, crop, and fertilizer`() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.viewmodel.CropShieldViewModel(app)

        // Search for a crop
        val cropResults = viewModel.performSearch("Maize", com.example.model.SearchCategoryFilter.ALL)
        println("DEBUG SEARCH CROP RESULTS: " + cropResults.map { "${it.category}: ${it.title}" })
        assertTrue("Must find Maize results", cropResults.isNotEmpty())
        assertTrue(cropResults.any { it.title.contains("Maize", ignoreCase = true) })

        // Search for a weed
        val weedResults = viewModel.performSearch("Blackjack", com.example.model.SearchCategoryFilter.ALL)
        println("DEBUG SEARCH WEED RESULTS: " + weedResults.map { "${it.category}: ${it.title}" })
        assertTrue("Must find Blackjack weed", weedResults.any { it.category == com.example.model.SearchCategoryFilter.WEEDS })

        // Search for a disease
        val diseaseResults = viewModel.performSearch("Blight", com.example.model.SearchCategoryFilter.ALL)
        println("DEBUG SEARCH DISEASE RESULTS: " + diseaseResults.map { "${it.category}: ${it.title}" })
        assertTrue("Must find Blight disease", diseaseResults.any { it.title.contains("Blight", ignoreCase = true) })

        // Search for a fertilizer brand
        val fertResults = viewModel.performSearch("Yara", com.example.model.SearchCategoryFilter.ALL)
        println("DEBUG SEARCH FERTILIZER RESULTS: " + fertResults.map { "${it.category}: ${it.title}" })
        assertTrue("Must find Yara fertilizers", fertResults.any { it.category == com.example.model.SearchCategoryFilter.FERTILIZERS })
    }
}


