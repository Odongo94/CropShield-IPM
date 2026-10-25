package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.model.BayScoutingRow
import com.example.model.CommercialScoutingSheet
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoutingSheetScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Metadata matching the paper scouting form (general name, fully customizable by scout)
    var farmName by remember { mutableStateOf("Commercial Farm & Greenhouse") }
    var phase by remember { mutableStateOf("Phase 1") }
    var ghNo by remember { mutableStateOf("GH 01") }
    var wkNo by remember { mutableStateOf("WK 43") }
    var sheetDate by remember {
        mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()))
    }

    // Interactive Bays list
    val bayRows = remember {
        mutableStateListOf<BayScoutingRow>().apply {
            // Default 30 standard bays as shown in the paper form
            for (i in 1..30) {
                add(
                    when (i) {
                        1 -> BayScoutingRow(bayNo = 1, helLarvae = 1, mites = 2, downyMildew = 1, comments = "Spot spray bay 1-3")
                        2 -> BayScoutingRow(bayNo = 2, depLarvae = 1, thrips = 3, comments = "Thrips hot spot")
                        3 -> BayScoutingRow(bayNo = 3, helEggs = 2, helMoth = true, whiteflies = true, comments = "Moth observed at vent")
                        4 -> BayScoutingRow(bayNo = 4, powderyMildew = 2, weeds = 1, comments = "De-leaf bottom canopy")
                        5 -> BayScoutingRow(bayNo = 5, fcmLarvae = 0, aphids = 5, comments = "Aphid colony on tender shoots")
                        8 -> BayScoutingRow(bayNo = 8, botrytis = 1, leafspot = 1, comments = "Ventilation check")
                        12 -> BayScoutingRow(bayNo = 12, depMoth = true, othersMoth = true, comments = "Delta trap replacement")
                        15 -> BayScoutingRow(bayNo = 15, helLarvae = 2, mites = 4, comments = "Phytoseiid release")
                        20 -> BayScoutingRow(bayNo = 20, downyMildew = 2, comments = "Fungicide rotation")
                        else -> BayScoutingRow(bayNo = i)
                    }
                )
            }
        }
    }

    // Dialog state for editing a row
    var editingRowIndex by remember { mutableStateOf<Int?>(null) }
    var showEditMetaDialog by remember { mutableStateOf(false) }

    val currentSheet = remember(farmName, phase, ghNo, wkNo, sheetDate, bayRows.toList()) {
        CommercialScoutingSheet(
            farmName = farmName,
            phase = phase,
            ghNo = ghNo,
            wkNo = wkNo,
            date = sheetDate,
            rows = bayRows.toList()
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Scouting Sheet",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("action_scout_sheet_back")
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Quick edit metadata
                    IconButton(
                        onClick = { showEditMetaDialog = true },
                        modifier = Modifier.testTag("action_edit_metadata")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Farm Info",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Copy Table CSV
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                            val clip = ClipData.newPlainText("Scouting Sheet CSV", currentSheet.toExcelCsv())
                            clipboard?.setPrimaryClip(clip)
                            Toast.makeText(context, "Scouting Sheet copied to clipboard in Excel CSV format!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.testTag("action_copy_csv")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy Table as CSV",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Export to Excel .csv file
                    IconButton(
                        onClick = { exportScoutingSheetToExcel(context, currentSheet) },
                        modifier = Modifier.testTag("action_export_excel_csv")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Export to Excel (.csv)",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        modifier = modifier.fillMaxSize().testTag("scouting_sheet_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Farm Metadata Strip in Table Format
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        onClick = { showEditMetaDialog = true },
                        color = Color.Transparent,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(4.dp)
                        ) {
                            MetaTag(label = "FARM (Tap to Change)", value = farmName)
                            MetaTag(label = "PHASE", value = phase)
                            MetaTag(label = "GH No.", value = ghNo)
                            MetaTag(label = "WK No.", value = wkNo)
                            MetaTag(label = "DATE", value = sheetDate)
                            MetaTag(label = "TOTAL BAYS", value = "${bayRows.size}")
                        }
                    }

                    // Export to Excel button right on the bar
                    Button(
                        onClick = { exportScoutingSheetToExcel(context, currentSheet) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("btn_export_excel_top")
                    ) {
                        Icon(imageVector = Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Export to Excel (.csv)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            HorizontalDivider()

            // Instructions text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tap any cell or row to edit counts, toggle moths/whiteflies, or enter scouting comments. Total Counts update automatically.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )

                TextButton(
                    onClick = {
                        val nextBay = (bayRows.maxOfOrNull { it.bayNo } ?: 0) + 1
                        bayRows.add(BayScoutingRow(bayNo = nextBay))
                        Toast.makeText(context, "Added Bay $nextBay", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.testTag("btn_add_bay")
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add Bay")
                }
            }

            // Main Table View
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                val horizontalScrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .horizontalScroll(horizontalScrollState)
                ) {
                    // Two-tiered Header Row matching the paper form:
                    // Top Header: CATEGORIES
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HeaderCell(text = "BAY", width = 64.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "CATERPILLARS (LARVAE)", width = 168.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "EGGS", width = 168.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "MOTH / PUPA", width = 168.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "OTHER PESTS", width = 232.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "DISEASES", width = 240.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "WEEDS", width = 64.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "COMMENTS / ACTION REQUIRED", width = 260.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                        HeaderCell(text = "ACTION", width = 60.dp, textColor = MaterialTheme.colorScheme.onPrimary)
                    }

                    // Sub-Header: Specific Columns
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SubHeaderCell(text = "No.", width = 64.dp)
                        // Caterpillars
                        SubHeaderCell(text = "Hel", width = 56.dp)
                        SubHeaderCell(text = "Dep", width = 56.dp)
                        SubHeaderCell(text = "Fcm", width = 56.dp)
                        // Eggs
                        SubHeaderCell(text = "Hel", width = 56.dp)
                        SubHeaderCell(text = "Dep", width = 56.dp)
                        SubHeaderCell(text = "Other", width = 56.dp)
                        // Moth / Pupa
                        SubHeaderCell(text = "Hel", width = 56.dp)
                        SubHeaderCell(text = "Dep", width = 56.dp)
                        SubHeaderCell(text = "Others", width = 56.dp)
                        // Other pests
                        SubHeaderCell(text = "Mites", width = 58.dp)
                        SubHeaderCell(text = "Thrips", width = 58.dp)
                        SubHeaderCell(text = "Aphids", width = 58.dp)
                        SubHeaderCell(text = "W-Flies", width = 58.dp)
                        // Diseases
                        SubHeaderCell(text = "Downy", width = 60.dp)
                        SubHeaderCell(text = "Powd", width = 60.dp)
                        SubHeaderCell(text = "Botry", width = 60.dp)
                        SubHeaderCell(text = "Leafsp", width = 60.dp)
                        // Weeds
                        SubHeaderCell(text = "Count", width = 64.dp)
                        // Comments
                        SubHeaderCell(text = "Action / Observation", width = 260.dp)
                        SubHeaderCell(text = "Edit", width = 60.dp)
                    }

                    // Rows
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        items(bayRows.indices.toList()) { index ->
                            val row = bayRows[index]
                            val isEven = index % 2 == 0
                            val rowBg = if (isEven) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)

                            Row(
                                modifier = Modifier
                                    .background(rowBg)
                                    .clickable { editingRowIndex = index }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // BAY No.
                                TableCell(text = "${row.bayNo}", width = 64.dp, isBold = true, textColor = MaterialTheme.colorScheme.primary)

                                // Caterpillars
                                TableCell(text = if (row.helLarvae > 0) "${row.helLarvae}" else "-", width = 56.dp, isHighlighted = row.helLarvae > 0)
                                TableCell(text = if (row.depLarvae > 0) "${row.depLarvae}" else "-", width = 56.dp, isHighlighted = row.depLarvae > 0)
                                TableCell(text = if (row.fcmLarvae > 0) "${row.fcmLarvae}" else "-", width = 56.dp, isHighlighted = row.fcmLarvae > 0)

                                // Eggs
                                TableCell(text = if (row.helEggs > 0) "${row.helEggs}" else "-", width = 56.dp, isHighlighted = row.helEggs > 0)
                                TableCell(text = if (row.depEggs > 0) "${row.depEggs}" else "-", width = 56.dp, isHighlighted = row.depEggs > 0)
                                TableCell(text = if (row.otherEggs > 0) "${row.otherEggs}" else "-", width = 56.dp, isHighlighted = row.otherEggs > 0)

                                // Moth / Pupa (Ticks)
                                TickCell(isTicked = row.helMoth, width = 56.dp, onToggle = {
                                    bayRows[index] = row.copy(helMoth = !row.helMoth)
                                })
                                TickCell(isTicked = row.depMoth, width = 56.dp, onToggle = {
                                    bayRows[index] = row.copy(depMoth = !row.depMoth)
                                })
                                TickCell(isTicked = row.othersMoth, width = 56.dp, onToggle = {
                                    bayRows[index] = row.copy(othersMoth = !row.othersMoth)
                                })

                                // Other Pests
                                TableCell(text = if (row.mites > 0) "${row.mites}" else "-", width = 58.dp, isHighlighted = row.mites > 0)
                                TableCell(text = if (row.thrips > 0) "${row.thrips}" else "-", width = 58.dp, isHighlighted = row.thrips > 0)
                                TableCell(text = if (row.aphids > 0) "${row.aphids}" else "-", width = 58.dp, isHighlighted = row.aphids > 0)
                                TickCell(isTicked = row.whiteflies, width = 58.dp, onToggle = {
                                    bayRows[index] = row.copy(whiteflies = !row.whiteflies)
                                })

                                // Diseases
                                TableCell(text = if (row.downyMildew > 0) "${row.downyMildew}" else "-", width = 60.dp, isHighlighted = row.downyMildew > 0)
                                TableCell(text = if (row.powderyMildew > 0) "${row.powderyMildew}" else "-", width = 60.dp, isHighlighted = row.powderyMildew > 0)
                                TableCell(text = if (row.botrytis > 0) "${row.botrytis}" else "-", width = 60.dp, isHighlighted = row.botrytis > 0)
                                TableCell(text = if (row.leafspot > 0) "${row.leafspot}" else "-", width = 60.dp, isHighlighted = row.leafspot > 0)

                                // Weeds
                                TableCell(text = if (row.weeds > 0) "${row.weeds}" else "-", width = 64.dp, isHighlighted = row.weeds > 0)

                                // Comments / Action Required
                                Text(
                                    text = row.comments.ifEmpty { "-" },
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .width(260.dp)
                                        .padding(horizontal = 8.dp)
                                )

                                // Action icon
                                IconButton(
                                    onClick = { editingRowIndex = index },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Bay", modifier = Modifier.size(16.dp))
                                }
                            }
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                        }
                    }

                    // TOTAL COUNTS ROW (Bottom Row)
                    val totHelL = bayRows.sumOf { it.helLarvae }
                    val totDepL = bayRows.sumOf { it.depLarvae }
                    val totFcmL = bayRows.sumOf { it.fcmLarvae }
                    val totHelE = bayRows.sumOf { it.helEggs }
                    val totDepE = bayRows.sumOf { it.depEggs }
                    val totOthE = bayRows.sumOf { it.otherEggs }
                    val totHelM = bayRows.count { it.helMoth }
                    val totDepM = bayRows.count { it.depMoth }
                    val totOthM = bayRows.count { it.othersMoth }
                    val totMites = bayRows.sumOf { it.mites }
                    val totThrips = bayRows.sumOf { it.thrips }
                    val totAphids = bayRows.sumOf { it.aphids }
                    val totWf = bayRows.count { it.whiteflies }
                    val totDowny = bayRows.sumOf { it.downyMildew }
                    val totPowdery = bayRows.sumOf { it.powderyMildew }
                    val totBotrytis = bayRows.sumOf { it.botrytis }
                    val totLeafspot = bayRows.sumOf { it.leafspot }
                    val totWeeds = bayRows.sumOf { it.weeds }

                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TableCell(text = "Total Counts", width = 64.dp, isBold = true, textColor = MaterialTheme.colorScheme.onPrimaryContainer)

                        // Caterpillars
                        TableCell(text = "$totHelL", width = 56.dp, isBold = true)
                        TableCell(text = "$totDepL", width = 56.dp, isBold = true)
                        TableCell(text = "$totFcmL", width = 56.dp, isBold = true)

                        // Eggs
                        TableCell(text = "$totHelE", width = 56.dp, isBold = true)
                        TableCell(text = "$totDepE", width = 56.dp, isBold = true)
                        TableCell(text = "$totOthE", width = 56.dp, isBold = true)

                        // Moth / Pupa
                        TableCell(text = "$totHelM", width = 56.dp, isBold = true)
                        TableCell(text = "$totDepM", width = 56.dp, isBold = true)
                        TableCell(text = "$totOthM", width = 56.dp, isBold = true)

                        // Other Pests
                        TableCell(text = "$totMites", width = 58.dp, isBold = true)
                        TableCell(text = "$totThrips", width = 58.dp, isBold = true)
                        TableCell(text = "$totAphids", width = 58.dp, isBold = true)
                        TableCell(text = "$totWf", width = 58.dp, isBold = true)

                        // Diseases
                        TableCell(text = "$totDowny", width = 60.dp, isBold = true)
                        TableCell(text = "$totPowdery", width = 60.dp, isBold = true)
                        TableCell(text = "$totBotrytis", width = 60.dp, isBold = true)
                        TableCell(text = "$totLeafspot", width = 60.dp, isBold = true)

                        // Weeds
                        TableCell(text = "$totWeeds", width = 64.dp, isBold = true)

                        // End spacer
                        Box(modifier = Modifier.width(320.dp).padding(horizontal = 8.dp)) {
                            Text(
                                text = "Ready for Excel Export",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            // Bottom Action Bar: Clear / Reset / Export
            Surface(
                tonalElevation = 3.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                            val clip = ClipData.newPlainText("Scouting Sheet CSV", currentSheet.toExcelCsv())
                            clipboard?.setPrimaryClip(clip)
                            Toast.makeText(context, "Scouting Sheet CSV copied to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("btn_copy_csv_bottom")
                    ) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy CSV", fontSize = 13.sp)
                    }

                    Button(
                        onClick = { exportScoutingSheetToExcel(context, currentSheet) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("btn_export_excel_bottom")
                    ) {
                        Icon(imageVector = Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Export to Excel (.csv)", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // DIALOG: Edit Specific Bay Row
    editingRowIndex?.let { index ->
        val row = bayRows.getOrNull(index)
        if (row != null) {
            EditBayDialog(
                bayRow = row,
                onDismiss = { editingRowIndex = null },
                onSave = { updatedRow ->
                    bayRows[index] = updatedRow
                    editingRowIndex = null
                },
                onDelete = {
                    bayRows.removeAt(index)
                    editingRowIndex = null
                }
            )
        }
    }

    // DIALOG: Edit Metadata (Farm, Phase, GH No, WK No, Date)
    if (showEditMetaDialog) {
        EditMetaDialog(
            currentFarm = farmName,
            currentPhase = phase,
            currentGh = ghNo,
            currentWk = wkNo,
            currentDate = sheetDate,
            onDismiss = { showEditMetaDialog = false },
            onSave = { f, p, gh, wk, d ->
                farmName = f
                phase = p
                ghNo = gh
                wkNo = wk
                sheetDate = d
                showEditMetaDialog = false
            }
        )
    }
}

@Composable
private fun MetaTag(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun HeaderCell(text: String, width: androidx.compose.ui.unit.Dp, textColor: Color) {
    Box(
        modifier = Modifier
            .width(width)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SubHeaderCell(text: String, width: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier
            .width(width)
            .padding(horizontal = 2.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TableCell(
    text: String,
    width: androidx.compose.ui.unit.Dp,
    isBold: Boolean = false,
    isHighlighted: Boolean = false,
    textColor: Color? = null
) {
    Box(
        modifier = Modifier
            .width(width)
            .padding(horizontal = 2.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        val color = when {
            textColor != null -> textColor
            isHighlighted -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.onSurface
        }
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = if (isBold || isHighlighted) FontWeight.Bold else FontWeight.Normal,
            color = color,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TickCell(
    isTicked: Boolean,
    width: androidx.compose.ui.unit.Dp,
    onToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(width)
            .clickable { onToggle() }
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isTicked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Ticked",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
        } else {
            Text(
                text = "-",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
private fun EditBayDialog(
    bayRow: BayScoutingRow,
    onDismiss: () -> Unit,
    onSave: (BayScoutingRow) -> Unit,
    onDelete: () -> Unit
) {
    var helLarvae by remember { mutableStateOf("${bayRow.helLarvae}") }
    var depLarvae by remember { mutableStateOf("${bayRow.depLarvae}") }
    var fcmLarvae by remember { mutableStateOf("${bayRow.fcmLarvae}") }
    var helEggs by remember { mutableStateOf("${bayRow.helEggs}") }
    var depEggs by remember { mutableStateOf("${bayRow.depEggs}") }
    var otherEggs by remember { mutableStateOf("${bayRow.otherEggs}") }
    var helMoth by remember { mutableStateOf(bayRow.helMoth) }
    var depMoth by remember { mutableStateOf(bayRow.depMoth) }
    var othersMoth by remember { mutableStateOf(bayRow.othersMoth) }
    var mites by remember { mutableStateOf("${bayRow.mites}") }
    var thrips by remember { mutableStateOf("${bayRow.thrips}") }
    var aphids by remember { mutableStateOf("${bayRow.aphids}") }
    var whiteflies by remember { mutableStateOf(bayRow.whiteflies) }
    var downy by remember { mutableStateOf("${bayRow.downyMildew}") }
    var powdery by remember { mutableStateOf("${bayRow.powderyMildew}") }
    var botrytis by remember { mutableStateOf("${bayRow.botrytis}") }
    var leafspot by remember { mutableStateOf("${bayRow.leafspot}") }
    var weeds by remember { mutableStateOf("${bayRow.weeds}") }
    var comments by remember { mutableStateOf(bayRow.comments) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Edit Bay ${bayRow.bayNo} Data", fontWeight = FontWeight.Bold)
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Text("Caterpillars / Larvae", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = helLarvae,
                            onValueChange = { helLarvae = it },
                            label = { Text("Hel") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = depLarvae,
                            onValueChange = { depLarvae = it },
                            label = { Text("Dep") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = fcmLarvae,
                            onValueChange = { fcmLarvae = it },
                            label = { Text("Fcm") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Text("Eggs", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = helEggs,
                            onValueChange = { helEggs = it },
                            label = { Text("Hel") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = depEggs,
                            onValueChange = { depEggs = it },
                            label = { Text("Dep") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = otherEggs,
                            onValueChange = { otherEggs = it },
                            label = { Text("Other") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Text("Moth / Pupa (Tick if noted)", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = helMoth, onCheckedChange = { helMoth = it })
                            Text("Hel", fontSize = 12.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = depMoth, onCheckedChange = { depMoth = it })
                            Text("Dep", fontSize = 12.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = othersMoth, onCheckedChange = { othersMoth = it })
                            Text("Others", fontSize = 12.sp)
                        }
                    }
                }

                item {
                    Text("Other Pests", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = mites,
                            onValueChange = { mites = it },
                            label = { Text("Mites") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = thrips,
                            onValueChange = { thrips = it },
                            label = { Text("Thrips") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = aphids,
                            onValueChange = { aphids = it },
                            label = { Text("Aphids") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                        Checkbox(checked = whiteflies, onCheckedChange = { whiteflies = it })
                        Text("Whiteflies Present", fontSize = 13.sp)
                    }
                }

                item {
                    Text("Diseases", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = downy,
                            onValueChange = { downy = it },
                            label = { Text("Downy") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = powdery,
                            onValueChange = { powdery = it },
                            label = { Text("Powdery") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = botrytis,
                            onValueChange = { botrytis = it },
                            label = { Text("Botrytis") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = leafspot,
                            onValueChange = { leafspot = it },
                            label = { Text("Leafspot") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Text("Weeds", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    OutlinedTextField(
                        value = weeds,
                        onValueChange = { weeds = it },
                        label = { Text("Weed Count") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Text("Comments / Action Required", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    OutlinedTextField(
                        value = comments,
                        onValueChange = { comments = it },
                        label = { Text("Action or scouting note") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val updated = bayRow.copy(
                        helLarvae = helLarvae.toIntOrNull() ?: 0,
                        depLarvae = depLarvae.toIntOrNull() ?: 0,
                        fcmLarvae = fcmLarvae.toIntOrNull() ?: 0,
                        helEggs = helEggs.toIntOrNull() ?: 0,
                        depEggs = depEggs.toIntOrNull() ?: 0,
                        otherEggs = otherEggs.toIntOrNull() ?: 0,
                        helMoth = helMoth,
                        depMoth = depMoth,
                        othersMoth = othersMoth,
                        mites = mites.toIntOrNull() ?: 0,
                        thrips = thrips.toIntOrNull() ?: 0,
                        aphids = aphids.toIntOrNull() ?: 0,
                        whiteflies = whiteflies,
                        downyMildew = downy.toIntOrNull() ?: 0,
                        powderyMildew = powdery.toIntOrNull() ?: 0,
                        botrytis = botrytis.toIntOrNull() ?: 0,
                        leafspot = leafspot.toIntOrNull() ?: 0,
                        weeds = weeds.toIntOrNull() ?: 0,
                        comments = comments
                    )
                    onSave(updated)
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onDelete) {
                    Text("Delete Bay", color = MaterialTheme.colorScheme.error)
                }
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        }
    )
}

@Composable
private fun EditMetaDialog(
    currentFarm: String,
    currentPhase: String,
    currentGh: String,
    currentWk: String,
    currentDate: String,
    onDismiss: () -> Unit,
    onSave: (String, String, String, String, String) -> Unit
) {
    var farm by remember { mutableStateOf(currentFarm) }
    var phase by remember { mutableStateOf(currentPhase) }
    var gh by remember { mutableStateOf(currentGh) }
    var wk by remember { mutableStateOf(currentWk) }
    var date by remember { mutableStateOf(currentDate) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Scouting Sheet Info", fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = farm,
                    onValueChange = { farm = it },
                    label = { Text("Farm Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = phase,
                        onValueChange = { phase = it },
                        label = { Text("Phase") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = gh,
                        onValueChange = { gh = it },
                        label = { Text("GH No.") },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = wk,
                        onValueChange = { wk = it },
                        label = { Text("WK No.") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Date (DD/MM/YYYY)") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = { onSave(farm, phase, gh, wk, date) }) {
                Text("Save Info")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

/**
 * Exports the clean tabular Scouting Sheet as an Excel-compatible CSV file.
 * Avoids footnotes and extraneous headings, complying directly with user specification:
 * "avoid the footnotes and the headings just write Scouting Sheet in Table format which can be exported into excel sheet for analysis."
 */
private fun exportScoutingSheetToExcel(context: Context, sheet: CommercialScoutingSheet) {
    try {
        val fileName = "Scouting_Sheet_${sheet.farmName.replace(" ", "_")}_${sheet.ghNo.replace(" ", "_")}_${System.currentTimeMillis()}.csv"
        val file = File(context.cacheDir, fileName)
        FileOutputStream(file).use { out ->
            out.write(sheet.toExcelCsv().toByteArray())
        }

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, "Scouting Sheet - ${sheet.farmName} (${sheet.ghNo})")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(sendIntent, "Open / Export Scouting Sheet for Excel"))
    } catch (e: Exception) {
        // Fallback: share raw CSV as plain text
        try {
            val textIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, sheet.toExcelCsv())
                putExtra(Intent.EXTRA_SUBJECT, "Scouting Sheet - ${sheet.farmName}")
            }
            context.startActivity(Intent.createChooser(textIntent, "Share Scouting Sheet Table"))
        } catch (ex: Exception) {
            Toast.makeText(context, "Export error: ${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
