package com.example.model

/**
 * Commercial Greenhouse Bay Scouting Row based on the Shalimar Fresh Limited flower scouting protocol.
 */
data class BayScoutingRow(
    val id: String = java.util.UUID.randomUUID().toString(),
    val bayNo: Int,
    // Other Lepidopteras - Caterpillars / Larvae
    var helLarvae: Int = 0,
    var depLarvae: Int = 0,
    var fcmLarvae: Int = 0,
    // Other Lepidopteras - Eggs
    var helEggs: Int = 0,
    var depEggs: Int = 0,
    var otherEggs: Int = 0,
    // Moth or Pupa (Tick if noted, false if not)
    var helMoth: Boolean = false,
    var depMoth: Boolean = false,
    var othersMoth: Boolean = false,
    // Other Pests
    var mites: Int = 0,
    var thrips: Int = 0,
    var aphids: Int = 0,
    var whiteflies: Boolean = false,
    // Diseases
    var downyMildew: Int = 0,
    var powderyMildew: Int = 0,
    var botrytis: Int = 0,
    var leafspot: Int = 0,
    // Weeds
    var weeds: Int = 0,
    // Comments / Action Required
    var comments: String = ""
)

/**
 * Complete Commercial Scouting Sheet ready for display and Excel (.csv) export.
 */
data class CommercialScoutingSheet(
    val farmName: String = "Commercial Shamba / Greenhouse",
    val phase: String = "Phase 1",
    val ghNo: String = "GH 01",
    val wkNo: String = "WK 43",
    val date: String = "25/10/2026",
    val rows: List<BayScoutingRow> = emptyList()
) {
    /**
     * Generates a clean tabular CSV for direct import into Microsoft Excel or Google Sheets.
     * Complies with user requirement: "avoid the footnotes and the headings just write Scouting Sheet in Table format which can be exported into excel sheet for analysis."
     */
    fun toExcelCsv(): String {
        val sb = StringBuilder()
        sb.append("Scouting Sheet\n")
        sb.append("Farm Name,Phase,GH No,WK No,Date\n")
        sb.append("\"$farmName\",\"$phase\",\"$ghNo\",\"$wkNo\",\"$date\"\n\n")
        sb.append("BAY No.,Caterpillars Hel,Caterpillars Dep,Caterpillars Fcm,Eggs Hel,Eggs Dep,Eggs Other,Moth/Pupa Hel,Moth/Pupa Dep,Moth/Pupa Others,Mites,Thrips,Aphids,White flies,Downy mildew,Powdery mildew,Botrytis,Leafspot,Weeds,COMMENTS / ACTION REQUIRED\n")
        
        for (r in rows) {
            val helM = if (r.helMoth) "Tick" else "X"
            val depM = if (r.depMoth) "Tick" else "X"
            val othM = if (r.othersMoth) "Tick" else "X"
            val wf = if (r.whiteflies) "Tick" else "X"
            sb.append("${r.bayNo},${r.helLarvae},${r.depLarvae},${r.fcmLarvae},${r.helEggs},${r.depEggs},${r.otherEggs},$helM,$depM,$othM,${r.mites},${r.thrips},${r.aphids},$wf,${r.downyMildew},${r.powderyMildew},${r.botrytis},${r.leafspot},${r.weeds},\"${r.comments.replace("\"", "\"\"")}\"\n")
        }
        
        // Total Counts row
        val totHelL = rows.sumOf { it.helLarvae }
        val totDepL = rows.sumOf { it.depLarvae }
        val totFcmL = rows.sumOf { it.fcmLarvae }
        val totHelE = rows.sumOf { it.helEggs }
        val totDepE = rows.sumOf { it.depEggs }
        val totOthE = rows.sumOf { it.otherEggs }
        val totHelM = rows.count { it.helMoth }
        val totDepM = rows.count { it.depMoth }
        val totOthM = rows.count { it.othersMoth }
        val totMites = rows.sumOf { it.mites }
        val totThrips = rows.sumOf { it.thrips }
        val totAphids = rows.sumOf { it.aphids }
        val totWf = rows.count { it.whiteflies }
        val totDowny = rows.sumOf { it.downyMildew }
        val totPowdery = rows.sumOf { it.powderyMildew }
        val totBotrytis = rows.sumOf { it.botrytis }
        val totLeafspot = rows.sumOf { it.leafspot }
        val totWeeds = rows.sumOf { it.weeds }
        
        sb.append("Total Counts,$totHelL,$totDepL,$totFcmL,$totHelE,$totDepE,$totOthE,$totHelM,$totDepM,$totOthM,$totMites,$totThrips,$totAphids,$totWf,$totDowny,$totPowdery,$totBotrytis,$totLeafspot,$totWeeds,\n")
        return sb.toString()
    }
}
