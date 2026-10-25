package com.example.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AgronomyCatalog
import com.example.data.ThreatCatalog
import com.example.data.local.CropShieldDatabase
import com.example.data.local.FieldNoteRepository
import com.example.data.local.ScoutRecordEntity
import com.example.data.local.ScoutRepository
import com.example.data.local.ThreatRepository
import com.example.data.local.entities.FieldNoteEntity
import com.example.data.scanner.PhotoDiseaseScanner
import com.example.data.scanner.ScanDiagnosisResult
import com.example.data.weather.CompleteWeatherResult
import com.example.data.weather.WeatherRepository
import android.content.Context
import com.example.model.ChemicalRecommendation
import com.example.model.CropFertilizerPlan
import com.example.model.CropThreat
import com.example.model.FertilizerCalculationResult
import com.example.model.FertilizerPhase
import com.example.model.FertilizerProduct
import com.example.model.KenyaAgriculturalCounty
import com.example.model.SearchCategoryFilter
import com.example.model.ThreatType
import com.example.model.UnifiedSearchItem
import com.example.model.WeedModel
import com.example.model.WeedType
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    LIBRARY,
    SCANNER,
    DIAGNOSE,
    AGRONOMY,
    FIELD_MAP,
    LIFECYCLE_LAB,
    CALCULATOR,
    SOIL_CALCULATOR,
    FIELD_NOTES,
    SCOUT_LOG,
    SCOUTING_SHEETS,
    THREAT_DETAIL
}

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val result: CompleteWeatherResult) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

class CropShieldViewModel(application: Application) : AndroidViewModel(application) {

    private val database = CropShieldDatabase.getDatabase(application)
    private val scoutRepository = ScoutRepository(database.scoutDao())
    private val threatRepository = ThreatRepository(database.threatDao())
    private val fieldNoteRepository = FieldNoteRepository(database.fieldNoteDao())
    private val weatherRepository = WeatherRepository(application)

    val scoutRecords: StateFlow<List<ScoutRecordEntity>> = scoutRepository.allRecords
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val fieldNotes: StateFlow<List<FieldNoteEntity>> = fieldNoteRepository.allNotes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val savedFields: StateFlow<List<com.example.data.local.entities.MappedFieldEntity>> = database.mappedFieldDao()
        .getAllMappedFields()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allThreats: StateFlow<List<CropThreat>> = threatRepository.allThreats
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThreatCatalog.allThreats
        )

    // App Theme State (System Default, Light Mode, Dark Mode)
    private val prefs = application.getSharedPreferences("cropshield_settings", Context.MODE_PRIVATE)
    private val _themeMode = MutableStateFlow(
        try {
            AppThemeMode.valueOf(prefs.getString("app_theme_mode", AppThemeMode.SYSTEM.name) ?: AppThemeMode.SYSTEM.name)
        } catch (_: Exception) {
            AppThemeMode.SYSTEM
        }
    )
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    fun setThemeMode(mode: AppThemeMode) {
        _themeMode.value = mode
        prefs.edit().putString("app_theme_mode", mode.name).apply()
    }

    // Universal Multi-Category Search
    private val _isGlobalSearchOpen = MutableStateFlow(false)
    val isGlobalSearchOpen: StateFlow<Boolean> = _isGlobalSearchOpen.asStateFlow()

    private val _globalSearchQuery = MutableStateFlow("")
    val globalSearchQuery: StateFlow<String> = _globalSearchQuery.asStateFlow()

    private val _globalSearchCategory = MutableStateFlow(SearchCategoryFilter.ALL)
    val globalSearchCategory: StateFlow<SearchCategoryFilter> = _globalSearchCategory.asStateFlow()

    fun performSearch(
        query: String = _globalSearchQuery.value,
        cat: SearchCategoryFilter = _globalSearchCategory.value,
        threats: List<CropThreat> = allThreats.value.ifEmpty { ThreatCatalog.allThreats }
    ): List<UnifiedSearchItem> {
        val q = query.trim()
        val allItems = mutableListOf<UnifiedSearchItem>()

        // 1. Diseases and Pests
        threats.forEach { t ->
            val isDisease = t.type == com.example.model.ThreatType.DISEASE
            val category = if (isDisease) SearchCategoryFilter.DISEASES else SearchCategoryFilter.PESTS
            val matchesCat = cat == SearchCategoryFilter.ALL || cat == category
            val matchesQuery = q.isBlank() ||
                t.name.contains(q, ignoreCase = true) ||
                t.scientificName.contains(q, ignoreCase = true) ||
                t.targetCrops.any { it.contains(q, ignoreCase = true) } ||
                t.diagnosticKeys.any { it.contains(q, ignoreCase = true) } ||
                t.summary.contains(q, ignoreCase = true) ||
                t.ipmPlan.chemicalControls.any { it.tradeName.contains(q, ignoreCase = true) || it.activeIngredient.contains(q, ignoreCase = true) }

            if (matchesCat && matchesQuery) {
                allItems.add(
                    UnifiedSearchItem(
                        id = "threat_${t.id}",
                        title = t.name,
                        scientificOrSecondary = t.scientificName,
                        category = category,
                        badgeText = if (isDisease) "Disease" else "Insect Pest",
                        snippet = t.diagnosticKeys.firstOrNull() ?: t.summary,
                        tags = t.targetCrops,
                        imageResId = t.imageResId,
                        threatRef = t
                    )
                )
            }
        }

        // 2. Weeds
        if (cat == SearchCategoryFilter.ALL || cat == SearchCategoryFilter.WEEDS) {
            AgronomyCatalog.kenyanWeeds.forEach { weed ->
                val matchesQuery = q.isBlank() ||
                    weed.commonName.contains(q, ignoreCase = true) ||
                    weed.botanicalName.contains(q, ignoreCase = true) ||
                    weed.localKenyanNames.any { it.contains(q, ignoreCase = true) } ||
                    weed.cropsAttacked.any { it.contains(q, ignoreCase = true) } ||
                    weed.competitiveImpact.contains(q, ignoreCase = true) ||
                    weed.registeredHerbicides.any { it.tradeName.contains(q, ignoreCase = true) || it.activeIngredient.contains(q, ignoreCase = true) }

                if (matchesQuery) {
                    allItems.add(
                        UnifiedSearchItem(
                            id = "weed_${weed.id}",
                            title = weed.commonName,
                            scientificOrSecondary = weed.botanicalName,
                            category = SearchCategoryFilter.WEEDS,
                            badgeText = weed.type.displayName,
                            snippet = if (weed.localKenyanNames.isNotEmpty()) "Kenyan: " + weed.localKenyanNames.joinToString(", ") else weed.competitiveImpact,
                            tags = weed.cropsAttacked,
                            imageResId = weed.imageResId,
                            weedRef = weed
                        )
                    )
                }
            }
        }

        // 3. Crops & Fertilizer Plans
        if (cat == SearchCategoryFilter.ALL || cat == SearchCategoryFilter.CROPS) {
            AgronomyCatalog.cropFertilizerPlans.forEach { crop ->
                val matchesQuery = q.isBlank() ||
                    crop.cropName.contains(q, ignoreCase = true) ||
                    crop.botanicalName.contains(q, ignoreCase = true) ||
                    crop.cropCategory.displayName.contains(q, ignoreCase = true) ||
                    crop.recommendedBasalBrand.contains(q, ignoreCase = true) ||
                    crop.recommendedTopdressBrand.contains(q, ignoreCase = true)

                if (matchesQuery) {
                    allItems.add(
                        UnifiedSearchItem(
                            id = "crop_${crop.cropName.hashCode()}",
                            title = crop.cropName,
                            scientificOrSecondary = crop.botanicalName,
                            category = SearchCategoryFilter.CROPS,
                            badgeText = "Crop Profile",
                            snippet = "Basal: ${crop.recommendedBasalBrand} • Topdress: ${crop.recommendedTopdressBrand}",
                            tags = listOf(crop.cropCategory.displayName, "Fertilizer Plan"),
                            cropPlanRef = crop
                        )
                    )
                }
            }
        }

        // 4. Fertilizers & Brands
        if (cat == SearchCategoryFilter.ALL || cat == SearchCategoryFilter.FERTILIZERS) {
            AgronomyCatalog.fertilizerProducts.forEach { fert ->
                val matchesQuery = q.isBlank() ||
                    fert.name.contains(q, ignoreCase = true) ||
                    fert.brand.contains(q, ignoreCase = true) ||
                    fert.npkRatio.contains(q, ignoreCase = true) ||
                    fert.secondaryNutrients.contains(q, ignoreCase = true) ||
                    fert.decisionRule.contains(q, ignoreCase = true)

                if (matchesQuery) {
                    allItems.add(
                        UnifiedSearchItem(
                            id = "fert_${fert.id}",
                            title = "${fert.brand} ${fert.name}",
                            scientificOrSecondary = "NPK: ${fert.npkRatio}",
                            category = SearchCategoryFilter.FERTILIZERS,
                            badgeText = fert.phase.displayName.take(16),
                            snippet = "${fert.decisionRule} • ${fert.soilAcidImpact}",
                            tags = listOf(fert.priceRangeKes, fert.standardRatePerHa),
                            fertilizerRef = fert
                        )
                    )
                }
            }
        }

        return allItems
    }

    val unifiedSearchResults: StateFlow<List<UnifiedSearchItem>> = combine(
        allThreats,
        _globalSearchQuery,
        _globalSearchCategory
    ) { threats, query, cat ->
        performSearch(query, cat, threats.ifEmpty { ThreatCatalog.allThreats })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = performSearch("", SearchCategoryFilter.ALL, ThreatCatalog.allThreats)
    )

    fun openGlobalSearch() {
        _isGlobalSearchOpen.value = true
    }

    fun closeGlobalSearch() {
        _isGlobalSearchOpen.value = false
    }

    fun setGlobalSearchQuery(query: String) {
        _globalSearchQuery.value = query
    }

    fun setGlobalSearchCategory(category: SearchCategoryFilter) {
        _globalSearchCategory.value = category
    }

    fun openCropInCalculator(plan: CropFertilizerPlan) {
        setCropPlan(plan)
        navigateTo(AppScreen.CALCULATOR)
    }

    fun openFertilizerInCalculator(fertilizer: FertilizerProduct) {
        when (fertilizer.phase) {
            FertilizerPhase.BASAL_PLANTING -> setBasalProduct(fertilizer)
            FertilizerPhase.TOP_DRESSING_1, FertilizerPhase.TOP_DRESSING_2 -> setTopdressProduct(fertilizer)
            FertilizerPhase.FOLIAR_FERTIGATION -> setFoliarProduct(fertilizer)
        }
        navigateTo(AppScreen.CALCULATOR)
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults: StateFlow<List<CropThreat>> = _searchQuery
        .flatMapLatest { query ->
            threatRepository.searchThreats(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThreatCatalog.allThreats
        )

    private val _currentScreen = MutableStateFlow(AppScreen.LIBRARY)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val screenStack = mutableListOf<AppScreen>()

    private val _selectedThreat = MutableStateFlow<CropThreat?>(null)
    val selectedThreat: StateFlow<CropThreat?> = _selectedThreat.asStateFlow()

    private val _selectedChemical = MutableStateFlow<ChemicalRecommendation?>(null)
    val selectedChemical: StateFlow<ChemicalRecommendation?> = _selectedChemical.asStateFlow()

    private val _prefilledScoutThreat = MutableStateFlow<CropThreat?>(null)
    val prefilledScoutThreat: StateFlow<CropThreat?> = _prefilledScoutThreat.asStateFlow()

    private val _prefilledNoteCrop = MutableStateFlow<String?>(null)
    val prefilledNoteCrop: StateFlow<String?> = _prefilledNoteCrop.asStateFlow()

    private val _prefilledNotePest = MutableStateFlow<String?>(null)
    val prefilledNotePest: StateFlow<String?> = _prefilledNotePest.asStateFlow()

    private val _scanBitmap = MutableStateFlow<Bitmap?>(null)
    val scanBitmap: StateFlow<Bitmap?> = _scanBitmap.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    private val _scanResult = MutableStateFlow<ScanDiagnosisResult?>(null)
    val scanResult: StateFlow<ScanDiagnosisResult?> = _scanResult.asStateFlow()

    private val _selectedCropHint = MutableStateFlow("Auto-Detect")
    val selectedCropHint: StateFlow<String> = _selectedCropHint.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<ThreatType?>(null)
    val selectedCategoryFilter: StateFlow<ThreatType?> = _selectedCategoryFilter.asStateFlow()

    private val _scanError = MutableStateFlow<String?>(null)
    val scanError: StateFlow<String?> = _scanError.asStateFlow()

    // =========================================================================
    // AGRONOMY STATE: FERTILIZER CALCULATOR, WEEDS & WEATHER FORECASTING
    // =========================================================================
    private val _farmSizeHectares = MutableStateFlow(1.0)
    val farmSizeHectares: StateFlow<Double> = _farmSizeHectares.asStateFlow()

    private val _selectedCropPlan = MutableStateFlow(AgronomyCatalog.cropFertilizerPlans.first())
    val selectedCropPlan: StateFlow<com.example.model.CropFertilizerPlan> = _selectedCropPlan.asStateFlow()

    private val _selectedBasalProduct = MutableStateFlow<FertilizerProduct?>(null)
    val selectedBasalProduct: StateFlow<FertilizerProduct?> = _selectedBasalProduct.asStateFlow()

    private val _selectedTopdressProduct = MutableStateFlow<FertilizerProduct?>(null)
    val selectedTopdressProduct: StateFlow<FertilizerProduct?> = _selectedTopdressProduct.asStateFlow()

    val fertilizerResult: StateFlow<FertilizerCalculationResult> = combine(
        _farmSizeHectares,
        _selectedCropPlan,
        _selectedBasalProduct,
        _selectedTopdressProduct
    ) { ha, plan, basal, topdress ->
        AgronomyCatalog.calculateFertilizer(
            cropName = plan.cropName,
            farmSizeHectares = ha,
            customBasalProduct = basal,
            customTopdressProduct = topdress
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AgronomyCatalog.calculateFertilizer(
            cropName = AgronomyCatalog.cropFertilizerPlans.first().cropName,
            farmSizeHectares = 1.0
        )
    )

    // Knapsack Fertilizer state
    private val _selectedKnapsackLiters = MutableStateFlow(16.0)
    val selectedKnapsackLiters: StateFlow<Double> = _selectedKnapsackLiters.asStateFlow()

    private val _selectedFoliarProduct = MutableStateFlow<FertilizerProduct?>(null)
    val selectedFoliarProduct: StateFlow<FertilizerProduct?> = _selectedFoliarProduct.asStateFlow()

    val knapsackFertilizerResult: StateFlow<com.example.model.KnapsackFertilizerResult> = combine(
        listOf(
            _farmSizeHectares,
            _selectedCropPlan,
            _selectedKnapsackLiters,
            _selectedBasalProduct,
            _selectedTopdressProduct,
            _selectedFoliarProduct
        )
    ) { array ->
        val ha = array[0] as Double
        val plan = array[1] as com.example.model.CropFertilizerPlan
        val tankLiters = array[2] as Double
        val basal = array[3] as? FertilizerProduct
        val topdress = array[4] as? FertilizerProduct
        val foliar = array[5] as? FertilizerProduct

        AgronomyCatalog.calculateKnapsackFertilizer(
            cropName = plan.cropName,
            farmSizeHectares = ha,
            knapsackTankCapacityLiters = tankLiters,
            customBasalProduct = basal,
            customTopdressProduct = topdress,
            customFoliarProduct = foliar
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AgronomyCatalog.calculateKnapsackFertilizer(
            cropName = AgronomyCatalog.cropFertilizerPlans.first().cropName,
            farmSizeHectares = 1.0,
            knapsackTankCapacityLiters = 16.0
        )
    )

    // Weeds state
    private val _weedQuery = MutableStateFlow("")
    val weedQuery: StateFlow<String> = _weedQuery.asStateFlow()

    private val _selectedWeedType = MutableStateFlow<WeedType?>(null)
    val selectedWeedType: StateFlow<WeedType?> = _selectedWeedType.asStateFlow()

    val filteredWeeds: StateFlow<List<WeedModel>> = combine(
        _weedQuery,
        _selectedWeedType
    ) { q, type ->
        AgronomyCatalog.kenyanWeeds.filter { weed ->
            val matchesType = type == null || weed.type == type
            val matchesQuery = q.isBlank() ||
                weed.commonName.contains(q, ignoreCase = true) ||
                weed.botanicalName.contains(q, ignoreCase = true) ||
                weed.localKenyanNames.any { it.contains(q, ignoreCase = true) } ||
                weed.cropsAttacked.any { it.contains(q, ignoreCase = true) } ||
                weed.registeredHerbicides.any { it.tradeName.contains(q, ignoreCase = true) || it.activeIngredient.contains(q, ignoreCase = true) }
            matchesType && matchesQuery
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AgronomyCatalog.kenyanWeeds
    )

    // Weather state
    private val _selectedCounty = MutableStateFlow(AgronomyCatalog.kenyanCounties.first())
    val selectedCounty: StateFlow<KenyaAgriculturalCounty> = _selectedCounty.asStateFlow()

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()

    init {
        // Ensure Room database is seeded with complete threat metadata, lifecycles, and IPM solutions
        viewModelScope.launch {
            threatRepository.ensureSeeded()
            scoutRepository.insert(
                ScoutRecordEntity(
                    dateString = "2026-10-14 09:30",
                    fieldOrPlot = "Greenhouse Block A",
                    cropName = "Tomato",
                    threatName = "Late Blight",
                    threatType = "DISEASE",
                    severityLevel = "Moderate",
                    affectedPlantCount = "8 plants in row 3",
                    actionTaken = "Pruned infected lower foliage; sprayed Metalaxyl-M + Mancozeb (50g/16L knapsack)",
                    notes = "Early morning fog persisted for 4 hours; monitored spore sporulation under leaves."
                )
            )
        }

        // Initialize weather with default hub
        fetchWeatherForCounty(_selectedCounty.value)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun clearSearchQuery() {
        _searchQuery.value = ""
    }

    fun navigateTo(screen: AppScreen) {
        if (_currentScreen.value != screen) {
            screenStack.add(_currentScreen.value)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        if (screenStack.isNotEmpty()) {
            _currentScreen.value = screenStack.removeAt(screenStack.lastIndex)
            return true
        } else if (_currentScreen.value != AppScreen.LIBRARY) {
            _currentScreen.value = AppScreen.LIBRARY
            return true
        }
        return false
    }

    fun openThreatDetail(threat: CropThreat) {
        _selectedThreat.value = threat
        navigateTo(AppScreen.THREAT_DETAIL)
    }

    fun openCalculator(threat: CropThreat? = null, chemical: ChemicalRecommendation? = null) {
        _selectedThreat.value = threat
        _selectedChemical.value = chemical
        navigateTo(AppScreen.CALCULATOR)
    }

    fun openAddScout(threat: CropThreat) {
        _prefilledScoutThreat.value = threat
        navigateTo(AppScreen.SCOUT_LOG)
    }

    fun openFieldNotes(crop: String? = null, pest: String? = null) {
        _prefilledNoteCrop.value = crop
        _prefilledNotePest.value = pest
        navigateTo(AppScreen.FIELD_NOTES)
    }

    fun clearPrefilledScoutThreat() {
        _prefilledScoutThreat.value = null
    }

    fun clearPrefilledNote() {
        _prefilledNoteCrop.value = null
        _prefilledNotePest.value = null
    }

    fun saveScoutRecord(record: ScoutRecordEntity) {
        viewModelScope.launch {
            scoutRepository.insert(record)
        }
    }

    fun deleteScoutRecord(record: ScoutRecordEntity) {
        viewModelScope.launch {
            scoutRepository.delete(record)
        }
    }

    fun saveFieldNote(note: FieldNoteEntity) {
        viewModelScope.launch {
            fieldNoteRepository.insertNote(note)
        }
    }

    fun deleteFieldNote(note: FieldNoteEntity) {
        viewModelScope.launch {
            fieldNoteRepository.deleteNote(note)
        }
    }

    fun setSelectedCropHint(hint: String) {
        _selectedCropHint.value = hint
    }

    fun setSelectedCategoryFilter(category: ThreatType?) {
        _selectedCategoryFilter.value = category
    }

    fun onPhotoCaptured(bitmap: Bitmap) {
        _scanBitmap.value = bitmap
        _scanResult.value = null
        _scanError.value = null
        analyzeCurrentPhoto()
    }

    fun analyzeCurrentPhoto() {
        val bitmap = _scanBitmap.value ?: return
        viewModelScope.launch {
            _isScanning.value = true
            _scanError.value = null
            try {
                val result = PhotoDiseaseScanner.analyzeCropPhoto(
                    bitmap = bitmap,
                    cropHint = _selectedCropHint.value,
                    categoryFilter = _selectedCategoryFilter.value
                )
                _scanResult.value = result
            } catch (e: Exception) {
                _scanError.value = e.message ?: "Analysis failed"
            } finally {
                _isScanning.value = false
            }
        }
    }

    fun resetScanner() {
        _scanBitmap.value = null
        _scanResult.value = null
        _scanError.value = null
    }

    fun resetScan() {
        resetScanner()
    }

    fun openScanner() {
        navigateTo(AppScreen.SCANNER)
    }

    // =========================================================================
    // AGRONOMY ACTIONS
    // =========================================================================
    fun setFarmHectares(ha: Double) {
        _farmSizeHectares.value = ha.coerceAtLeast(0.1)
    }

    fun setCropPlan(plan: com.example.model.CropFertilizerPlan) {
        _selectedCropPlan.value = plan
        _selectedBasalProduct.value = null
        _selectedTopdressProduct.value = null
    }

    fun setBasalProduct(product: FertilizerProduct?) {
        _selectedBasalProduct.value = product
    }

    fun setTopdressProduct(product: FertilizerProduct?) {
        _selectedTopdressProduct.value = product
    }

    fun setKnapsackTankLiters(liters: Double) {
        _selectedKnapsackLiters.value = liters
    }

    fun setFoliarProduct(product: FertilizerProduct?) {
        _selectedFoliarProduct.value = product
    }

    fun setWeedSearchQuery(query: String) {
        _weedQuery.value = query
    }

    fun setWeedTypeFilter(type: WeedType?) {
        _selectedWeedType.value = type
    }

    fun fetchWeatherForCounty(county: KenyaAgriculturalCounty) {
        _selectedCounty.value = county
        viewModelScope.launch(Dispatchers.IO) {
            _weatherState.value = WeatherUiState.Loading
            val res = weatherRepository.fetchWeatherForecast(county = county)
            res.fold(
                onSuccess = { _weatherState.value = WeatherUiState.Success(it) },
                onFailure = { _weatherState.value = WeatherUiState.Error(it.message ?: "Failed to connect to weather service") }
            )
        }
    }

    fun autoDetectFarmLocationAndFetchWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherState.value = WeatherUiState.Loading
            val loc = weatherRepository.detectDeviceLocation()
            if (loc != null) {
                val res = weatherRepository.fetchWeatherForecast(customLat = loc.first, customLng = loc.second)
                res.fold(
                    onSuccess = { _weatherState.value = WeatherUiState.Success(it) },
                    onFailure = { _weatherState.value = WeatherUiState.Error(it.message ?: "Failed to fetch weather for detected location") }
                )
            } else {
                // If location permission or GPS not available, fetch for primary agricultural county
                val res = weatherRepository.fetchWeatherForecast(county = _selectedCounty.value)
                res.fold(
                    onSuccess = { _weatherState.value = WeatherUiState.Success(it) },
                    onFailure = { _weatherState.value = WeatherUiState.Error("GPS signal unavailable. Showing ${_selectedCounty.value.name}") }
                )
            }
        }
    }

    fun saveMappedField(field: com.example.data.local.entities.MappedFieldEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.mappedFieldDao().insertMappedField(field)
        }
    }

    fun deleteMappedField(field: com.example.data.local.entities.MappedFieldEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            database.mappedFieldDao().deleteMappedField(field)
        }
    }

    fun openInKnapsackCalculator(hectares: Double, cropName: String) {
        setFarmHectares(hectares)
        val matchedPlan = AgronomyCatalog.cropFertilizerPlans.firstOrNull {
            it.cropName.equals(cropName, ignoreCase = true)
        }
        if (matchedPlan != null) {
            setCropPlan(matchedPlan)
        }
        navigateTo(AppScreen.CALCULATOR)
    }
}
