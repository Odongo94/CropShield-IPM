package com.example

import com.example.data.map.FieldGeometryUtil
import com.example.data.scouting.ScoutingProtocolCatalog
import com.example.model.DeveloperInfo
import com.example.model.GeoPoint
import com.example.model.ScoutingInspectionRow
import com.example.model.ScoutingSheetReport
import com.example.data.science.AgriculturalSciencesCatalog
import com.example.data.science.WeedSoilScienceCatalog
import com.example.model.science.SoilPhCategory
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun developerInfo_matchesRequestedCredentials() {
        assertEquals("Odongo Vincent", DeveloperInfo.NAME)
        assertEquals("Crop Scientist", DeveloperInfo.TITLE)
        assertEquals("0741886046", DeveloperInfo.PHONE)
        assertEquals("odongovincento@gmail.com", DeveloperInfo.EMAIL)
    }

    @Test
    fun autoDetectFarmBoundary_generatesFourCornersAroundGpsPoint() {
        val userLocation = GeoPoint(-0.3031, 36.0800) // Nakuru
        val boundary = FieldGeometryUtil.autoDetectFarmBoundary(userLocation, 1.0)

        assertEquals(4, boundary.size)

        // Verify centroid is approximately the center coordinate
        val centroid = FieldGeometryUtil.calculateCentroid(boundary)
        assertNotNull(centroid)
        assertEquals(userLocation.latitude, centroid!!.latitude, 0.0001)
        assertEquals(userLocation.longitude, centroid.longitude, 0.0001)

        // Verify area calculated is close to 1.0 hectare (10,000 m²)
        val metrics = FieldGeometryUtil.computeMetrics(boundary)
        assertEquals(1.0, metrics.hectares, 0.05)
        assertEquals(2.47, metrics.acres, 0.15)
        assertTrue(metrics.perimeterMeters > 350.0 && metrics.perimeterMeters < 450.0)
    }

    @Test
    fun scoutingSheetReport_exportsValidCsv() {
        val sampleRow = ScoutingInspectionRow(
            stationNumber = 1,
            plotLocation = "Block 1A",
            cropName = "Maize",
            threatName = "Fall Armyworm (Spodoptera frugiperda)",
            plantsSampled = 20,
            plantsInfested = 4,
            incidencePercent = 20.0,
            severityRating = 4,
            stageObserved = "L2-L3 Larvae",
            actionStatus = "ACTION REQUIRED",
            notes = "Window feeding observed on whorls; spray Emamectin benzoate"
        )

        val report = ScoutingSheetReport(
            farmName = "Rongai Shamba",
            blockName = "Block 1",
            cropName = "Maize",
            scoutName = "John K.",
            dateInspected = "Oct 24, 2026",
            gpsCoordinates = "-0.3031, 36.0800",
            rows = listOf(sampleRow)
        )

        val csv = report.toCsv()
        assertTrue(csv.contains("Station #,Plot Location,Crop,Target Threat,Plants Sampled,Plants Infested,Incidence (%),Severity (1-5),Stage Observed,Action Status,Scout Notes"))
        assertTrue(csv.contains("Fall Armyworm"))
        assertTrue(csv.contains("Emamectin benzoate"))
        assertTrue(csv.contains("Rongai Shamba"))
        assertTrue(csv.contains(DeveloperInfo.NAME))
        assertTrue(csv.contains(DeveloperInfo.PHONE))
    }

    @Test
    fun scoutingProtocols_containKenyanIPMStandards() {
        val protocols = ScoutingProtocolCatalog.protocols
        assertTrue(protocols.isNotEmpty())

        val crops = protocols.map { it.cropName }.distinct()
        assertTrue(crops.any { it.contains("Maize", ignoreCase = true) })
        assertTrue(crops.any { it.contains("Tomato", ignoreCase = true) })
        assertTrue(crops.any { it.contains("Potato", ignoreCase = true) })

        val sampleRows = ScoutingProtocolCatalog.getSampleInspectionRows()
        assertEquals(4, sampleRows.size)
    }

    @Test
    fun weedSoilDiagnosis_strigaDetectsDegradedFertilityAndPushPull() {
        val report = WeedSoilScienceCatalog.diagnoseSoilFromWeeds(listOf("striga"))
        assertEquals(1, report.selectedWeeds.size)
        assertTrue(report.diagnosedNutrientDeficiencies.any { it.contains("Nitrogen", ignoreCase = true) })
        assertTrue(report.diagnosedNutrientDeficiencies.any { it.contains("Phosphorus", ignoreCase = true) })
        assertTrue(report.recommendedCommercialFertilizers.any { it.contains("DAP", ignoreCase = true) || it.contains("CAN", ignoreCase = true) || it.contains("Mavuno", ignoreCase = true) })
        assertTrue(report.urgentSoilAmendments.any { it.contains("Desmodium", ignoreCase = true) || it.contains("Organic", ignoreCase = true) || it.contains("Manure", ignoreCase = true) })
        assertTrue(report.agronomicRehabilitationPlan.isNotEmpty())
        assertTrue(report.technicalAdvisorySignoff.contains(DeveloperInfo.NAME))
    }

    @Test
    fun weedSoilDiagnosis_brackenFernDetectsAcidicSoilAndLiming() {
        val report = WeedSoilScienceCatalog.diagnoseSoilFromWeeds(listOf("bracken_fern"))
        assertEquals(1, report.selectedWeeds.size)
        assertEquals(SoilPhCategory.VERY_STRONGLY_ACIDIC, report.dominantPhCategory)
        assertTrue(report.diagnosedNutrientExcesses.any { it.contains("Aluminum", ignoreCase = true) })
        assertTrue(report.urgentSoilAmendments.any { it.contains("Lime", ignoreCase = true) })
    }

    @Test
    fun agriculturalSciencesCatalog_containsAllFiveCorePillars() {
        val soilPillar = AgriculturalSciencesCatalog.soilSciencePillar
        assertNotNull(soilPillar)
        assertTrue(soilPillar.kenyanSoilTaxonomy.isNotEmpty())
        assertTrue(soilPillar.kenyanSoilTaxonomy.any { it.name.contains("Nitisol", ignoreCase = true) })
        assertTrue(soilPillar.chemicalParameters.isNotEmpty())
        assertTrue(soilPillar.limeRequirementGuidelines.isNotEmpty())

        val pathologyPillar = AgriculturalSciencesCatalog.cropPathologyPillar
        assertNotNull(pathologyPillar)
        assertTrue(pathologyPillar.pathogenClasses.isNotEmpty())
        assertTrue(pathologyPillar.fracFungicideGuide.isNotEmpty())
        assertTrue(pathologyPillar.diseaseSeverityIndexFormula.contains("DSI", ignoreCase = true))

        val entomologyPillar = AgriculturalSciencesCatalog.entomologyPillar
        assertNotNull(entomologyPillar)
        assertTrue(entomologyPillar.insectOrders.isNotEmpty())
        assertTrue(entomologyPillar.beneficialPredators.isNotEmpty())
        assertTrue(entomologyPillar.iracInsecticideGuide.isNotEmpty())

        val agronomyPillar = AgriculturalSciencesCatalog.agronomyPillar
        assertNotNull(agronomyPillar)
        assertTrue(agronomyPillar.physiologyPrinciples.isNotEmpty())
        assertTrue(agronomyPillar.plantingGeometryStandards.isNotEmpty())

        val conservationPillar = AgriculturalSciencesCatalog.conservationPillar
        assertNotNull(conservationPillar)
        assertTrue(conservationPillar.engineeringStructures.isNotEmpty())
        assertTrue(conservationPillar.biologicalMeasures.isNotEmpty())
    }
}

