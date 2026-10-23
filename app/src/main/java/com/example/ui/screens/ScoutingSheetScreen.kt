package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.data.scouting.ScoutingProtocolCatalog
import com.example.model.DeveloperInfo
import com.example.model.ScoutingInspectionRow
import com.example.model.ScoutingProtocolItem
import com.example.model.ScoutingSheetReport
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class ScoutingSheetTab(val title: String) {
    INSPECTION_TABLE("Scouting Table Sheet"),
    PROTOCOLS_GUIDE("IPM Protocol Matrix")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoutingSheetScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var activeTab by remember { mutableStateOf(ScoutingSheetTab.INSPECTION_TABLE) }

    // Scouting Sheet Metadata
    var farmName by remember { mutableStateOf("Nakuru Agri-Farm Block A") }
    var blockName by remember { mutableStateOf("Plot 3 - Upper Ridge") }
    var targetCrop by remember { mutableStateOf("Maize") }
    var scoutName by remember { mutableStateOf("Lead Scout") }
    val todayDate = remember {
        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())
    }

    // Dynamic Inspection Rows
    val inspectionRows = remember {
        mutableStateListOf<ScoutingInspectionRow>().apply {
            addAll(ScoutingProtocolCatalog.getSampleInspectionRows())
        }
    }

    // Dialog state for adding inspection row
    var showAddRowDialog by remember { mutableStateOf(false) }
    var showAgronomistInfoDialog by remember { mutableStateOf(false) }

    // Protocol filter state
    var selectedProtocolCrop by remember { mutableStateOf("All") }
    var protocolSearchQuery by remember { mutableStateOf("") }

    val filteredProtocols = remember(selectedProtocolCrop, protocolSearchQuery) {
        ScoutingProtocolCatalog.protocols.filter { proto ->
            (selectedProtocolCrop == "All" || proto.cropName.contains(selectedProtocolCrop, ignoreCase = true)) &&
                    (protocolSearchQuery.isBlank() ||
                            proto.threatName.contains(protocolSearchQuery, ignoreCase = true) ||
                            proto.cropName.contains(protocolSearchQuery, ignoreCase = true) ||
                            proto.actionThreshold.contains(protocolSearchQuery, ignoreCase = true))
        }
    }

    val report = remember(farmName, blockName, targetCrop, scoutName, inspectionRows.toList()) {
        ScoutingSheetReport(
            farmName = farmName,
            blockName = blockName,
            cropName = targetCrop,
            dateInspected = todayDate,
            scoutName = scoutName,
            rows = inspectionRows.toList()
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Scouting Sheets & Protocols",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Standard IPM Tables • Certified by ${DeveloperInfo.NAME}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
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
                    // Agronomist details button
                    IconButton(
                        onClick = { showAgronomistInfoDialog = true },
                        modifier = Modifier.testTag("action_view_agronomist_info")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Agronomist Details",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Share button
                    IconButton(
                        onClick = { shareScoutingTable(context, report) },
                        modifier = Modifier.testTag("action_share_scout_table")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Table",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Download CSV button
                    IconButton(
                        onClick = { exportCsvFile(context, report) },
                        modifier = Modifier.testTag("action_download_scout_csv")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download CSV Sheet",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Print / HTML View
                    IconButton(
                        onClick = { printHtmlReport(context, report) },
                        modifier = Modifier.testTag("action_print_scout_html")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Print,
                            contentDescription = "Printable Table",
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
            // LEAD AGRONOMIST CONTACT HEADER CARD
            LeadAgronomistCard(onContactClick = { showAgronomistInfoDialog = true })

            // TAB SELECTOR
            PrimaryTabRow(
                selectedTabIndex = activeTab.ordinal,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().testTag("scout_sheet_tabs")
            ) {
                ScoutingSheetTab.values().forEach { tab ->
                    Tab(
                        selected = activeTab == tab,
                        onClick = { activeTab = tab },
                        text = {
                            Text(
                                text = tab.title,
                                fontWeight = if (activeTab == tab) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    )
                }
            }

            when (activeTab) {
                ScoutingSheetTab.INSPECTION_TABLE -> {
                    InspectionTableContent(
                        report = report,
                        rows = inspectionRows,
                        onAddRowClick = { showAddRowDialog = true },
                        onDeleteRow = { row -> inspectionRows.remove(row) },
                        onShareClick = { shareScoutingTable(context, report) },
                        onDownloadCsv = { exportCsvFile(context, report) },
                        onPrintHtml = { printHtmlReport(context, report) }
                    )
                }

                ScoutingSheetTab.PROTOCOLS_GUIDE -> {
                    ProtocolsGuideContent(
                        protocols = filteredProtocols,
                        selectedCrop = selectedProtocolCrop,
                        searchQuery = protocolSearchQuery,
                        onCropSelect = { selectedProtocolCrop = it },
                        onQueryChange = { protocolSearchQuery = it }
                    )
                }
            }
        }
    }

    // ADD INSPECTION ROW DIALOG
    if (showAddRowDialog) {
        AddInspectionRowDialog(
            nextStation = inspectionRows.size + 1,
            defaultCrop = targetCrop,
            onDismiss = { showAddRowDialog = false },
            onAddRow = { newRow ->
                inspectionRows.add(newRow)
                showAddRowDialog = false
            }
        )
    }

    // DEVELOPER & CROP SCIENTIST PROFILE DIALOG
    if (showAgronomistInfoDialog) {
        AgronomistProfileDialog(onDismiss = { showAgronomistInfoDialog = false })
    }
}

// =============================================================================
// LEAD AGRONOMIST PROFILE CARD
// =============================================================================
@Composable
private fun LeadAgronomistCard(onContactClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.fillMaxWidth().testTag("lead_agronomist_card")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = DeveloperInfo.NAME,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(1.dp)
                        ) {
                            Text(
                                text = DeveloperInfo.TITLE,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    }
                    Text(
                        text = "Tel: ${DeveloperInfo.PHONE} • ${DeveloperInfo.EMAIL}",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )
                }
            }

            OutlinedButton(
                onClick = onContactClick,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.height(34.dp)
            ) {
                Text("Contact", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

// =============================================================================
// TAB 1: FIELD INSPECTION TABLE CONTENT
// =============================================================================
@Composable
private fun InspectionTableContent(
    report: ScoutingSheetReport,
    rows: List<ScoutingInspectionRow>,
    onAddRowClick: () -> Unit,
    onDeleteRow: (ScoutingInspectionRow) -> Unit,
    onShareClick: () -> Unit,
    onDownloadCsv: () -> Unit,
    onPrintHtml: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Quick Action Bar for Table
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${report.farmName} • ${report.dateInspected}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${rows.size} Stations Sampled | Target Crop: ${report.cropName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Button(
                    onClick = onAddRowClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.height(36.dp).testTag("btn_add_station_row")
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("+ Station", style = MaterialTheme.typography.labelSmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // HORIZONTALLY SCROLLABLE STRUCTURED DATA TABLE
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .testTag("scouting_tabular_grid")
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Table Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TableHeaderCell("Station", width = 65.dp)
                    TableHeaderCell("Plot Location", width = 130.dp)
                    TableHeaderCell("Pest / Disease", width = 160.dp)
                    TableHeaderCell("Sampled", width = 75.dp)
                    TableHeaderCell("Incidence", width = 85.dp)
                    TableHeaderCell("Severity", width = 75.dp)
                    TableHeaderCell("Stage", width = 120.dp)
                    TableHeaderCell("Action Status", width = 130.dp)
                    TableHeaderCell("Notes / Guidance", width = 160.dp)
                    TableHeaderCell("Action", width = 55.dp)
                }

                // Table Data Rows
                if (rows.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No scouting stations recorded. Tap '+ Station' to log an inspection.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(rows) { row ->
                            val statusColor = when (row.actionStatus) {
                                "ACTION REQUIRED" -> MaterialTheme.colorScheme.error
                                "MONITOR (3 DAYS)" -> Color(0xFFF57C00)
                                else -> Color(0xFF388E3C)
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp, horizontal = 8.dp)
                                    .horizontalScroll(rememberScrollState()),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TableDataCell("#${row.stationNumber}", width = 65.dp, bold = true)
                                TableDataCell(row.plotLocation, width = 130.dp)
                                TableDataCell(row.threatName, width = 160.dp, bold = true, color = MaterialTheme.colorScheme.primary)
                                TableDataCell("${row.plantsInfested}/${row.plantsSampled}", width = 75.dp)
                                TableDataCell("${String.format("%.1f", row.incidencePercent)}%", width = 85.dp, bold = true)
                                TableDataCell("${row.severityRating}/5", width = 75.dp)
                                TableDataCell(row.stageObserved.ifBlank { "-" }, width = 120.dp)

                                // Status Badge Cell
                                Box(modifier = Modifier.width(130.dp)) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = statusColor.copy(alpha = 0.15f),
                                        border = BorderStroke(1.dp, statusColor.copy(alpha = 0.5f))
                                    ) {
                                        Text(
                                            text = row.actionStatus,
                                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                            color = statusColor,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                TableDataCell(row.notes.ifBlank { "-" }, width = 160.dp)

                                Box(modifier = Modifier.width(55.dp)) {
                                    IconButton(
                                        onClick = { onDeleteRow(row) },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Export Actions Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onShareClick,
                modifier = Modifier.weight(1f).testTag("action_share_table_text")
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Share (WhatsApp)", style = MaterialTheme.typography.labelSmall)
            }

            Button(
                onClick = onDownloadCsv,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.weight(1f).testTag("action_download_csv_file")
            ) {
                Icon(imageVector = Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Download CSV", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

// =============================================================================
// TAB 2: IPM PROTOCOLS & ACTION THRESHOLDS GUIDE (TABLE)
// =============================================================================
@Composable
private fun ProtocolsGuideContent(
    protocols: List<ScoutingProtocolItem>,
    selectedCrop: String,
    searchQuery: String,
    onCropSelect: (String) -> Unit,
    onQueryChange: (String) -> Unit
) {
    val crops = listOf("All", "Maize", "Tomato", "Potato", "French Beans", "Coffee", "Cabbage")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Crop Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            crops.forEach { crop ->
                FilterChip(
                    selected = selectedCrop == crop,
                    onClick = { onCropSelect(crop) },
                    label = { Text(crop) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            placeholder = { Text("Search crop, pest, threshold...") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().testTag("input_search_protocols")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Protocols List / Table Cards
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(protocols) { proto ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth().testTag("protocol_item_${proto.id}")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = proto.threatName,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "${proto.cropName} • ${proto.scientificName}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = if (proto.urgencyLevel == "Critical") MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.secondaryContainer
                            ) {
                                Text(
                                    text = proto.urgencyLevel,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (proto.urgencyLevel == "Critical") MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        ProtocolRowItem(label = "Sampling Pattern:", value = proto.samplingPattern)
                        ProtocolRowItem(label = "Sampling Unit:", value = proto.samplingUnit)
                        ProtocolRowItem(label = "Action Threshold (EIL):", value = proto.actionThreshold, highlight = true)
                        ProtocolRowItem(label = "Severity Scale (1-5):", value = proto.severityScaleGuide)
                        ProtocolRowItem(label = "Recommended IPM Action:", value = proto.ipmIntervention)
                    }
                }
            }
        }
    }
}

@Composable
private fun ProtocolRowItem(label: String, value: String, highlight: Boolean = false) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = if (highlight) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (highlight) FontWeight.SemiBold else FontWeight.Normal,
            color = if (highlight) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun TableHeaderCell(text: String, width: androidx.compose.ui.unit.Dp) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.width(width).padding(horizontal = 4.dp)
    )
}

@Composable
private fun TableDataCell(
    text: String,
    width: androidx.compose.ui.unit.Dp,
    bold: Boolean = false,
    color: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
        color = color,
        modifier = Modifier.width(width).padding(horizontal = 4.dp)
    )
}

// =============================================================================
// DIALOG: ADD INSPECTION ROW
// =============================================================================
@Composable
private fun AddInspectionRowDialog(
    nextStation: Int,
    defaultCrop: String,
    onDismiss: () -> Unit,
    onAddRow: (ScoutingInspectionRow) -> Unit
) {
    var stationNum by remember { mutableIntStateOf(nextStation) }
    var plotLocation by remember { mutableStateOf("Block A - Station $nextStation") }
    var cropName by remember { mutableStateOf(defaultCrop) }
    var threatName by remember { mutableStateOf("Fall Armyworm (FAW)") }
    var sampledCount by remember { mutableStateOf("20") }
    var infestedCount by remember { mutableStateOf("2") }
    var severityRating by remember { mutableIntStateOf(2) }
    var stageObserved by remember { mutableStateOf("Larvae in whorl") }
    var actionStatus by remember { mutableStateOf("ACTION REQUIRED") }
    var notesText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Log Scouting Station #$stationNum", fontWeight = FontWeight.Bold) },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                OutlinedTextField(
                    value = plotLocation,
                    onValueChange = { plotLocation = it },
                    label = { Text("Plot / Field Location") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = threatName,
                    onValueChange = { threatName = it },
                    label = { Text("Target Pest or Disease") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = sampledCount,
                        onValueChange = { sampledCount = it },
                        label = { Text("Sampled") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = infestedCount,
                        onValueChange = { infestedCount = it },
                        label = { Text("Infested") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Action Status:", style = MaterialTheme.typography.labelSmall)
                val statusOptions = listOf("ACTION REQUIRED", "MONITOR (3 DAYS)", "CLEAR")
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    statusOptions.forEach { status ->
                        FilterChip(
                            selected = actionStatus == status,
                            onClick = { actionStatus = status },
                            label = { Text(status) },
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = stageObserved,
                    onValueChange = { stageObserved = it },
                    label = { Text("Pest/Disease Lifecycle Stage") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    label = { Text("Field Notes / Chemical Action") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val sCount = sampledCount.toIntOrNull() ?: 20
                    val iCount = infestedCount.toIntOrNull() ?: 0
                    val incidence = if (sCount > 0) (iCount.toDouble() / sCount) * 100.0 else 0.0

                    val newRow = ScoutingInspectionRow(
                        stationNumber = stationNum,
                        plotLocation = plotLocation,
                        cropName = cropName,
                        threatName = threatName,
                        plantsSampled = sCount,
                        plantsInfested = iCount,
                        incidencePercent = incidence,
                        severityRating = severityRating,
                        stageObserved = stageObserved,
                        actionStatus = actionStatus,
                        notes = notesText
                    )
                    onAddRow(newRow)
                }
            ) {
                Text("Add to Table")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// =============================================================================
// DEVELOPER & CROP SCIENTIST PROFILE DIALOG
// =============================================================================
@Composable
fun AgronomistProfileDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.White)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = DeveloperInfo.NAME, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(text = DeveloperInfo.TITLE, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                }
            }
        },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = DeveloperInfo.QUALIFICATION,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = DeveloperInfo.BIO,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Contact Actions
                Button(
                    onClick = {
                        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${DeveloperInfo.PHONE}"))
                        context.startActivity(callIntent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call ${DeveloperInfo.PHONE}")
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = {
                        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${DeveloperInfo.EMAIL}")).apply {
                            putExtra(Intent.EXTRA_SUBJECT, "Agronomy & Scouting Consultation - CropShield")
                        }
                        context.startActivity(emailIntent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Email ${DeveloperInfo.EMAIL}")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

// =============================================================================
// EXPORT & SHARING HELPERS
// =============================================================================
private fun shareScoutingTable(context: Context, report: ScoutingSheetReport) {
    try {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Field Scouting Table - ${report.farmName} (${report.cropName})")
            putExtra(Intent.EXTRA_TEXT, report.toFormattedShareText())
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Scouting Sheet Table via"))
    } catch (e: Exception) {
        Toast.makeText(context, "Failed to share: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

private fun exportCsvFile(context: Context, report: ScoutingSheetReport) {
    try {
        val fileName = "scouting_sheet_${report.cropName.lowercase().replace(" ", "_")}_${System.currentTimeMillis()}.csv"
        val file = File(context.cacheDir, fileName)
        FileOutputStream(file).use { out ->
            out.write(report.toCsv().toByteArray())
        }

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, "Scouting Sheet CSV - ${report.farmName}")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(sendIntent, "Download / Share Scouting CSV Table"))
    } catch (e: Exception) {
        // Fallback: share raw CSV as text
        val textIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, report.toCsv())
        }
        context.startActivity(Intent.createChooser(textIntent, "Export Scouting Table"))
    }
}

private fun printHtmlReport(context: Context, report: ScoutingSheetReport) {
    try {
        val htmlFile = File(context.cacheDir, "scouting_report_${System.currentTimeMillis()}.html")
        FileOutputStream(htmlFile).use { out ->
            out.write(report.toPrintableHtml().toByteArray())
        }

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", htmlFile)
        val viewIntent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "text/html")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(viewIntent, "Open Printable Scouting Sheet Table"))
    } catch (e: Exception) {
        shareScoutingTable(context, report)
    }
}
