package com.example.model

/**
 * Standard agronomic scouting protocol guideline for a crop pest or disease.
 */
data class ScoutingProtocolItem(
    val id: String,
    val cropName: String,
    val threatName: String,
    val scientificName: String,
    val threatType: String, // "PEST" or "DISEASE"
    val samplingPattern: String,
    val samplingUnit: String,
    val actionThreshold: String,
    val severityScaleGuide: String,
    val ipmIntervention: String,
    val urgencyLevel: String = "High"
)

/**
 * An individual field inspection log row recorded by a scout.
 */
data class ScoutingInspectionRow(
    val id: String = java.util.UUID.randomUUID().toString(),
    val stationNumber: Int,
    val plotLocation: String,
    val cropName: String,
    val threatName: String,
    val plantsSampled: Int = 20,
    val plantsInfested: Int = 0,
    val incidencePercent: Double = 0.0,
    val severityRating: Int = 1, // 1 to 5
    val stageObserved: String = "",
    val actionStatus: String = "CLEAR", // "ACTION REQUIRED", "MONITOR (3 DAYS)", "CLEAR"
    val notes: String = ""
)

/**
 * Full inspection sheet with metadata and certified agronomist sign-off.
 */
data class ScoutingSheetReport(
    val sheetId: String = "SCOUT-${System.currentTimeMillis() % 100000}",
    val farmName: String,
    val blockName: String,
    val cropName: String,
    val dateInspected: String,
    val scoutName: String,
    val gpsCoordinates: String = "Auto-Acquired GPS",
    val leadAgronomist: String = "${DeveloperInfo.NAME} (${DeveloperInfo.TITLE} • ${DeveloperInfo.PHONE})",
    val agronomistEmail: String = DeveloperInfo.EMAIL,
    val rows: List<ScoutingInspectionRow>
) {
    /**
     * Generates a standard comma-separated values (CSV) table for spreadsheets.
     */
    fun toCsv(): String {
        val sb = StringBuilder()
        sb.append("CropShield IPM - Standard Field Scouting Inspection Sheet\n")
        sb.append("Lead Crop Scientist,${DeveloperInfo.NAME} (${DeveloperInfo.TITLE}),Phone,${DeveloperInfo.PHONE},Email,${DeveloperInfo.EMAIL}\n")
        sb.append("Farm Name,$farmName,Block/Shamba,$blockName,Crop,$cropName,Date,$dateInspected,GPS Coordinates,$gpsCoordinates\n\n")
        sb.append("Station #,Plot Location,Crop,Target Threat,Plants Sampled,Plants Infested,Incidence (%),Severity (1-5),Stage Observed,Action Status,Scout Notes\n")
        for (row in rows) {
            sb.append("${row.stationNumber},\"${row.plotLocation}\",\"${row.cropName}\",\"${row.threatName}\",${row.plantsSampled},${row.plantsInfested},${String.format("%.1f", row.incidencePercent)},${row.severityRating},\"${row.stageObserved}\",\"${row.actionStatus}\",\"${row.notes}\"\n")
        }
        sb.append("\nAgronomic Certification: Inspected according to Kenyan IPM Scouting Protocols. Technical Advisory: ${DeveloperInfo.NAME} (${DeveloperInfo.PHONE} / ${DeveloperInfo.EMAIL})\n")
        return sb.toString()
    }

    /**
     * Generates a clean monospace ASCII formatted table for sharing on WhatsApp or SMS.
     */
    fun toFormattedShareText(): String {
        val sb = StringBuilder()
        sb.append("📋 *CROPSHIELD IPM FIELD SCOUTING SHEET*\n")
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n")
        sb.append("🌱 *Farm:* $farmName | *Block:* $blockName\n")
        sb.append("🌾 *Crop:* $cropName | *Date:* $dateInspected\n")
        sb.append("📍 *GPS:* $gpsCoordinates\n")
        sb.append("👨‍🌾 *Lead Agronomist:* ${DeveloperInfo.NAME} (${DeveloperInfo.TITLE})\n")
        sb.append("📞 *Contact:* ${DeveloperInfo.PHONE} | ${DeveloperInfo.EMAIL}\n")
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n")
        sb.append("*INSPECTION DATA TABLE:*\n")

        for (row in rows) {
            val statusEmoji = when (row.actionStatus) {
                "ACTION REQUIRED" -> "🚨"
                "MONITOR (3 DAYS)" -> "⚠️"
                else -> "✅"
            }
            sb.append("• Station ${row.stationNumber} [${row.plotLocation}]:\n")
            sb.append("  Target: ${row.threatName}\n")
            sb.append("  Count: ${row.plantsInfested}/${row.plantsSampled} plants (${String.format("%.1f", row.incidencePercent)}% incidence)\n")
            sb.append("  Severity: ${row.severityRating}/5 (${row.stageObserved})\n")
            sb.append("  Status: $statusEmoji ${row.actionStatus}\n")
            if (row.notes.isNotBlank()) {
                sb.append("  Notes: ${row.notes}\n")
            }
            sb.append("\n")
        }

        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n")
        sb.append("💡 *Agronomic Guidance:* For chemical or bio-rational recommendations, contact Crop Scientist ${DeveloperInfo.NAME} at ${DeveloperInfo.PHONE}.")
        return sb.toString()
    }

    /**
     * Generates a clean HTML table with CSS formatting ready for browser viewing or PDF printing.
     */
    fun toPrintableHtml(): String {
        val rowsHtml = StringBuilder()
        for (r in rows) {
            val statusColor = when (r.actionStatus) {
                "ACTION REQUIRED" -> "#d32f2f"
                "MONITOR (3 DAYS)" -> "#f57c00"
                else -> "#388e3c"
            }
            rowsHtml.append(
                """
                <tr>
                    <td style="text-align:center;font-weight:bold;">${r.stationNumber}</td>
                    <td>${r.plotLocation}</td>
                    <td><strong>${r.threatName}</strong></td>
                    <td style="text-align:center;">${r.plantsInfested}/${r.plantsSampled}</td>
                    <td style="text-align:center;font-weight:bold;">${String.format("%.1f", r.incidencePercent)}%</td>
                    <td style="text-align:center;">${r.severityRating} / 5</td>
                    <td>${r.stageObserved}</td>
                    <td style="color:$statusColor;font-weight:bold;">${r.actionStatus}</td>
                    <td style="font-size:12px;">${r.notes}</td>
                </tr>
                """.trimIndent()
            )
        }

        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8"/>
            <title>Scouting Sheet - $farmName ($cropName)</title>
            <style>
                body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Arial, sans-serif; margin: 24px; color: #212121; }
                .header-card { background: #e8f5e9; border: 2px solid #2e7d32; border-radius: 8px; padding: 16px; margin-bottom: 20px; }
                .header-title { color: #1b5e20; font-size: 22px; font-weight: bold; margin-bottom: 6px; }
                .meta-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 8px; font-size: 14px; }
                table { width: 100%; border-collapse: collapse; margin-top: 16px; font-size: 13px; }
                th { background-color: #2e7d32; color: white; padding: 10px 8px; text-align: left; border: 1px solid #1b5e20; }
                td { padding: 8px; border: 1px solid #e0e0e0; }
                tr:nth-child(even) { background-color: #f9f9f9; }
                .cert-footer { margin-top: 24px; border-top: 2px dashed #9e9e9e; padding-top: 12px; font-size: 13px; color: #424242; }
                .badge { display: inline-block; padding: 4px 8px; border-radius: 4px; color: white; font-weight: bold; font-size: 11px; }
            </style>
        </head>
        <body>
            <div class="header-card">
                <div class="header-title">CropShield IPM • Field Scouting Inspection Table</div>
                <div class="meta-grid">
                    <div><strong>Farm Name:</strong> $farmName</div>
                    <div><strong>Block / Plot:</strong> $blockName</div>
                    <div><strong>Target Crop:</strong> $cropName</div>
                    <div><strong>Date Inspected:</strong> $dateInspected</div>
                    <div><strong>Field Scout:</strong> $scoutName</div>
                    <div><strong>GPS Position:</strong> $gpsCoordinates</div>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Station</th>
                        <th>Location</th>
                        <th>Target Pest / Disease</th>
                        <th>Infested / Sampled</th>
                        <th>Incidence</th>
                        <th>Severity</th>
                        <th>Stage Observed</th>
                        <th>Action Status</th>
                        <th>Scout Notes</th>
                    </tr>
                </thead>
                <tbody>
                    $rowsHtml
                </tbody>
            </table>

            <div class="cert-footer">
                <strong>Certified IPM Inspection Protocol</strong><br/>
                Technical Agronomic Lead: <strong>${DeveloperInfo.NAME}</strong> (${DeveloperInfo.TITLE})<br/>
                Direct Contacts: Phone: <strong>${DeveloperInfo.PHONE}</strong> | Email: <strong>${DeveloperInfo.EMAIL}</strong><br/>
                <em>Generated via CropShield IPM Precision Farming Suite • Offline Certified Protocol</em>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
