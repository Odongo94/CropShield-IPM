package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role
import com.example.ui.components.UniversalSearchDialog
import com.example.ui.theme.AppThemeMode
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.ThreatCatalog
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.Terrain
import com.example.ui.screens.AgronomistProfileDialog
import com.example.ui.screens.AgronomyScreen
import com.example.ui.screens.CalculatorScreen
import com.example.ui.screens.DiagnoseScreen
import com.example.ui.screens.FieldMapScreen
import com.example.ui.screens.FieldNotesScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.LifecycleLabScreen
import com.example.ui.screens.PhotoDiseaseScannerScreen
import com.example.ui.screens.ScoutLogScreen
import com.example.ui.screens.ScoutingSheetScreen
import com.example.ui.screens.ThreatDetailScreen
import com.example.ui.theme.CropShieldTheme
import com.example.viewmodel.AppScreen
import com.example.viewmodel.CropShieldViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CropShieldViewModel = viewModel()
            val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
            CropShieldTheme(themeMode = themeMode) {
                CropShieldApp(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropShieldApp(viewModel: CropShieldViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val allThreats by viewModel.allThreats.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedThreat by viewModel.selectedThreat.collectAsStateWithLifecycle()
    val selectedChemical by viewModel.selectedChemical.collectAsStateWithLifecycle()
    val prefilledScoutThreat by viewModel.prefilledScoutThreat.collectAsStateWithLifecycle()
    val scoutRecords by viewModel.scoutRecords.collectAsStateWithLifecycle()
    val fieldNotes by viewModel.fieldNotes.collectAsStateWithLifecycle()
    val prefilledNoteCrop by viewModel.prefilledNoteCrop.collectAsStateWithLifecycle()
    val prefilledNotePest by viewModel.prefilledNotePest.collectAsStateWithLifecycle()

    // Theme and Global Search States
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
    val isGlobalSearchOpen by viewModel.isGlobalSearchOpen.collectAsStateWithLifecycle()
    val globalSearchQuery by viewModel.globalSearchQuery.collectAsStateWithLifecycle()
    val globalSearchCategory by viewModel.globalSearchCategory.collectAsStateWithLifecycle()
    val unifiedSearchResults by viewModel.unifiedSearchResults.collectAsStateWithLifecycle()
    var showThemeDialog by remember { mutableStateOf(false) }
    var showAgronomistDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = currentScreen != AppScreen.LIBRARY) {
        viewModel.navigateBack()
    }

    Scaffold(
        topBar = {
            if (currentScreen != AppScreen.THREAT_DETAIL && currentScreen != AppScreen.FIELD_MAP && currentScreen != AppScreen.SCOUTING_SHEETS) {
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentScreen) {
                                AppScreen.LIBRARY -> "CropShield IPM"
                                AppScreen.SCANNER -> "Photo AI Disease Scanner"
                                AppScreen.AGRONOMY -> "Agronomy & Farm Suite"
                                AppScreen.FIELD_MAP -> "Offline Field & Zone Mapper"
                                AppScreen.DIAGNOSE -> "Symptom Diagnosis"
                                AppScreen.LIFECYCLE_LAB -> "Lifecycle Lab"
                                AppScreen.CALCULATOR -> "Knapsack Fertilizer Calculator"
                                AppScreen.SOIL_CALCULATOR -> "Soil Texture & Rotation Calculator"
                                AppScreen.FIELD_NOTES -> "Field Notes & Observations"
                                AppScreen.SCOUT_LOG -> "Field Scouting Logs"
                                AppScreen.SCOUTING_SHEETS -> "Scouting Sheets & Protocols"
                                AppScreen.THREAT_DETAIL -> ""
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        // 1. Universal Search Action (Search disease, pest, weed, crop)
                        IconButton(
                            onClick = { viewModel.openGlobalSearch() },
                            modifier = Modifier.testTag("action_global_search")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Universal Search",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        // 2. Offline Field Boundary & Agro-Zone Map Action
                        IconButton(
                            onClick = { viewModel.navigateTo(AppScreen.FIELD_MAP) },
                            modifier = Modifier.testTag("action_open_field_map")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Terrain,
                                contentDescription = "Field & Zone Map",
                                tint = if (currentScreen == AppScreen.FIELD_MAP) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // 3. Theme Mode Switcher (System Default, Light, Dark)
                        IconButton(
                            onClick = { showThemeDialog = true },
                            modifier = Modifier.testTag("action_theme_switcher")
                        ) {
                            Icon(
                                imageVector = when (themeMode) {
                                    AppThemeMode.SYSTEM -> Icons.Default.BrightnessAuto
                                    AppThemeMode.LIGHT -> Icons.Default.LightMode
                                    AppThemeMode.DARK -> Icons.Default.DarkMode
                                },
                                contentDescription = "Theme: ${themeMode.title}",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // 4. Scouting Sheets Table (Pests & Diseases Tables & CSV)
                        IconButton(
                            onClick = { viewModel.navigateTo(AppScreen.SCOUTING_SHEETS) },
                            modifier = Modifier.testTag("action_open_scouting_sheets")
                        ) {
                            Icon(
                                imageVector = Icons.Default.TableChart,
                                contentDescription = "Scouting Sheets & Protocols",
                                tint = if (currentScreen == AppScreen.SCOUTING_SHEETS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // 5. Lead Agronomist & Developer Profile (Odongo Vincent)
                        IconButton(
                            onClick = { showAgronomistDialog = true },
                            modifier = Modifier.testTag("action_developer_profile")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Lead Agronomist & Developer Odongo Vincent",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        },
        bottomBar = {
            if (currentScreen != AppScreen.THREAT_DETAIL && currentScreen != AppScreen.FIELD_MAP && currentScreen != AppScreen.SCOUTING_SHEETS) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .testTag("bottom_nav_bar")
                ) {
                    NavigationBarItem(
                        selected = currentScreen == AppScreen.LIBRARY,
                        onClick = { viewModel.navigateTo(AppScreen.LIBRARY) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Eco,
                                contentDescription = "Library",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text("Catalog", style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_library")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.SCANNER,
                        onClick = { viewModel.navigateTo(AppScreen.SCANNER) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "AI Scanner",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text("AI Scan", style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_scanner")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.AGRONOMY,
                        onClick = { viewModel.navigateTo(AppScreen.AGRONOMY) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Agriculture,
                                contentDescription = "Agronomy",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text("Agronomy", style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_agronomy")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.DIAGNOSE,
                        onClick = { viewModel.navigateTo(AppScreen.DIAGNOSE) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Diagnose",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text("Diagnose", style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_diagnose")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.CALCULATOR,
                        onClick = { viewModel.navigateTo(AppScreen.CALCULATOR) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Calculate,
                                contentDescription = "Knapsack Calculator",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text("Knapsack", style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_calculator")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.FIELD_NOTES,
                        onClick = { viewModel.navigateTo(AppScreen.FIELD_NOTES) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.EditNote,
                                contentDescription = "Field Notes",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text("Notes", style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_item_field_notes")
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "screen_transition"
            ) { screen ->
                when (screen) {
                    AppScreen.LIBRARY -> {
                        LibraryScreen(
                            threats = searchResults,
                            searchQuery = searchQuery,
                            onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
                            onThreatClick = { threat -> viewModel.openThreatDetail(threat) },
                            onNavigateToDiagnose = { viewModel.navigateTo(AppScreen.DIAGNOSE) }
                        )
                    }

                    AppScreen.SCANNER -> {
                        val scanBitmap by viewModel.scanBitmap.collectAsStateWithLifecycle()
                        val isScanning by viewModel.isScanning.collectAsStateWithLifecycle()
                        val scanResult by viewModel.scanResult.collectAsStateWithLifecycle()
                        val scanError by viewModel.scanError.collectAsStateWithLifecycle()
                        val selectedCropHint by viewModel.selectedCropHint.collectAsStateWithLifecycle()
                        val selectedCategoryFilter by viewModel.selectedCategoryFilter.collectAsStateWithLifecycle()

                        PhotoDiseaseScannerScreen(
                            scannedBitmap = scanBitmap,
                            isScanning = isScanning,
                            scanResult = scanResult,
                            scanError = scanError,
                            selectedCropHint = selectedCropHint,
                            onCropHintSelected = { hint -> viewModel.setSelectedCropHint(hint) },
                            onPhotoCaptured = { bitmap -> viewModel.onPhotoCaptured(bitmap) },
                            onResetScan = { viewModel.resetScan() },
                            onOpenThreatDetail = { threat -> viewModel.openThreatDetail(threat) },
                            onOpenCalculator = { threat, chem -> viewModel.openCalculator(threat, chem) },
                            onSaveToFieldNotes = { crop, pest -> viewModel.openFieldNotes(crop, pest) },
                            selectedCategoryFilter = selectedCategoryFilter,
                            onCategoryFilterSelected = { cat -> viewModel.setSelectedCategoryFilter(cat) }
                        )
                    }

                    AppScreen.AGRONOMY -> {
                        val farmSizeHectares by viewModel.farmSizeHectares.collectAsStateWithLifecycle()
                        val selectedCropPlan by viewModel.selectedCropPlan.collectAsStateWithLifecycle()
                        val selectedBasalProduct by viewModel.selectedBasalProduct.collectAsStateWithLifecycle()
                        val selectedTopdressProduct by viewModel.selectedTopdressProduct.collectAsStateWithLifecycle()
                        val fertilizerResult by viewModel.fertilizerResult.collectAsStateWithLifecycle()
                        val filteredWeeds by viewModel.filteredWeeds.collectAsStateWithLifecycle()
                        val weedQuery by viewModel.weedQuery.collectAsStateWithLifecycle()
                        val selectedWeedType by viewModel.selectedWeedType.collectAsStateWithLifecycle()
                        val selectedCounty by viewModel.selectedCounty.collectAsStateWithLifecycle()
                        val weatherState by viewModel.weatherState.collectAsStateWithLifecycle()

                        AgronomyScreen(
                            farmSizeHectares = farmSizeHectares,
                            selectedCropPlan = selectedCropPlan,
                            selectedBasalProduct = selectedBasalProduct,
                            selectedTopdressProduct = selectedTopdressProduct,
                            fertilizerResult = fertilizerResult,
                            weedList = filteredWeeds,
                            weedQuery = weedQuery,
                            selectedWeedType = selectedWeedType,
                            selectedCounty = selectedCounty,
                            weatherState = weatherState,
                            onHectaresChanged = { viewModel.setFarmHectares(it) },
                            onCropPlanSelected = { viewModel.setCropPlan(it) },
                            onBasalProductSelected = { viewModel.setBasalProduct(it) },
                            onTopdressProductSelected = { viewModel.setTopdressProduct(it) },
                            onWeedQueryChanged = { viewModel.setWeedSearchQuery(it) },
                            onWeedTypeSelected = { viewModel.setWeedTypeFilter(it) },
                            onCountySelected = { viewModel.fetchWeatherForCounty(it) },
                            onAutoDetectLocation = { viewModel.autoDetectFarmLocationAndFetchWeather() },
                            onNavigateToFieldMap = { viewModel.navigateTo(AppScreen.FIELD_MAP) }
                        )
                    }

                    AppScreen.FIELD_MAP -> {
                        val savedFields by viewModel.savedFields.collectAsStateWithLifecycle()
                        FieldMapScreen(
                            savedFields = savedFields,
                            onSaveField = { field -> viewModel.saveMappedField(field) },
                            onDeleteField = { field -> viewModel.deleteMappedField(field) },
                            onNavigateBack = { viewModel.navigateBack() },
                            onCalculateFertilizerForField = { hectares, cropName ->
                                viewModel.openInKnapsackCalculator(hectares, cropName)
                            }
                        )
                    }

                    AppScreen.DIAGNOSE -> {
                        DiagnoseScreen(
                            onThreatClick = { threat -> viewModel.openThreatDetail(threat) }
                        )
                    }

                    AppScreen.LIFECYCLE_LAB -> {
                        LifecycleLabScreen(
                            onNavigateToThreatDetail = { threat -> viewModel.openThreatDetail(threat) },
                            onNavigateToCalculator = { threat -> viewModel.openCalculator(threat) }
                        )
                    }

                    AppScreen.CALCULATOR -> {
                        val farmSizeHectares by viewModel.farmSizeHectares.collectAsStateWithLifecycle()
                        val selectedCropPlan by viewModel.selectedCropPlan.collectAsStateWithLifecycle()
                        val selectedKnapsackLiters by viewModel.selectedKnapsackLiters.collectAsStateWithLifecycle()
                        val selectedBasalProduct by viewModel.selectedBasalProduct.collectAsStateWithLifecycle()
                        val selectedTopdressProduct by viewModel.selectedTopdressProduct.collectAsStateWithLifecycle()
                        val selectedFoliarProduct by viewModel.selectedFoliarProduct.collectAsStateWithLifecycle()
                        val knapsackResult by viewModel.knapsackFertilizerResult.collectAsStateWithLifecycle()

                        CalculatorScreen(
                            farmSizeHectares = farmSizeHectares,
                            selectedCropPlan = selectedCropPlan,
                            selectedKnapsackLiters = selectedKnapsackLiters,
                            selectedBasalProduct = selectedBasalProduct,
                            selectedTopdressProduct = selectedTopdressProduct,
                            selectedFoliarProduct = selectedFoliarProduct,
                            knapsackResult = knapsackResult,
                            onHectaresChanged = { viewModel.setFarmHectares(it) },
                            onCropPlanSelected = { viewModel.setCropPlan(it) },
                            onKnapsackLitersSelected = { viewModel.setKnapsackTankLiters(it) },
                            onBasalProductSelected = { viewModel.setBasalProduct(it) },
                            onTopdressProductSelected = { viewModel.setTopdressProduct(it) },
                            onFoliarProductSelected = { viewModel.setFoliarProduct(it) },
                            initialThreat = selectedThreat,
                            initialChemical = selectedChemical,
                            onNavigateToFieldMap = { viewModel.navigateTo(AppScreen.FIELD_MAP) }
                        )
                    }

                    AppScreen.SOIL_CALCULATOR -> {
                        com.example.ui.screens.soil.SoilClassificationScreen(
                            onNavigateBack = { viewModel.navigateBack() }
                        )
                    }

                    AppScreen.FIELD_NOTES -> {
                        FieldNotesScreen(
                            notes = fieldNotes,
                            onSaveNote = { note -> viewModel.saveFieldNote(note) },
                            onDeleteNote = { note -> viewModel.deleteFieldNote(note) },
                            prefilledCrop = prefilledNoteCrop,
                            prefilledPest = prefilledNotePest
                        )
                    }

                    AppScreen.SCOUT_LOG -> {
                        ScoutLogScreen(
                            records = scoutRecords,
                            onAddRecord = { record -> viewModel.saveScoutRecord(record) },
                            onDeleteRecord = { record -> viewModel.deleteScoutRecord(record) },
                            prefilledThreat = prefilledScoutThreat,
                            onPrefillConsumed = { viewModel.clearPrefilledScoutThreat() },
                            onNavigateToScoutingSheets = { viewModel.navigateTo(AppScreen.SCOUTING_SHEETS) }
                        )
                    }

                    AppScreen.SCOUTING_SHEETS -> {
                        ScoutingSheetScreen(
                            onNavigateBack = { viewModel.navigateBack() }
                        )
                    }

                    AppScreen.THREAT_DETAIL -> {
                        val threat = selectedThreat ?: ThreatCatalog.allThreats.first()
                        ThreatDetailScreen(
                            threat = threat,
                            onBackClick = { viewModel.navigateBack() },
                            onNavigateToCalculator = { t, c -> viewModel.openCalculator(t, c) },
                            onAddScoutLog = { t -> viewModel.openAddScout(t) },
                            onAddFieldNote = { crop, pest -> viewModel.openFieldNotes(crop, pest) }
                        )
                    }
                }
            }
        }
    }

    // Universal Multi-Category Search Dialog (Disease, Pest, Weed, Crop, Fertilizer)
    if (isGlobalSearchOpen) {
        UniversalSearchDialog(
            searchQuery = globalSearchQuery,
            selectedCategory = globalSearchCategory,
            results = unifiedSearchResults,
            onQueryChange = { viewModel.setGlobalSearchQuery(it) },
            onCategoryChange = { viewModel.setGlobalSearchCategory(it) },
            onDismiss = { viewModel.closeGlobalSearch() },
            onSelectThreat = { threat -> viewModel.openThreatDetail(threat) },
            onSelectWeed = { weed ->
                viewModel.setWeedSearchQuery(weed.commonName)
                viewModel.navigateTo(AppScreen.AGRONOMY)
            },
            onSelectCrop = { crop -> viewModel.openCropInCalculator(crop) },
            onSelectFertilizer = { fert -> viewModel.openFertilizerInCalculator(fert) }
        )
    }

    // Theme Mode Selection Dialog (Default / Light / Dark)
    if (showThemeDialog) {
        AlertDialog(
            onDismissRequest = { showThemeDialog = false },
            title = {
                Text(
                    text = "Display Appearance Mode",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "Choose light, dark, or system default mode. The app updates immediately.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    AppThemeMode.values().forEach { mode ->
                        val selected = themeMode == mode
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selected,
                                    onClick = {
                                        viewModel.setThemeMode(mode)
                                        showThemeDialog = false
                                    },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selected,
                                onClick = null
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = mode.title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = mode.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showThemeDialog = false }) {
                    Text("Done")
                }
            }
        )
    }

    // Lead Agronomist & Developer Profile Dialog (Odongo Vincent)
    if (showAgronomistDialog) {
        AgronomistProfileDialog(onDismiss = { showAgronomistDialog = false })
    }
}

