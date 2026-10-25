package com.example.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.compose.runtime.DisposableEffect
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import com.example.ui.components.FieldMapCanvas
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Undo
import android.widget.Toast
import com.example.model.DeveloperInfo
import com.example.model.GpsPrecisionTelemetry
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.AgronomyCatalog
import com.example.data.local.entities.MappedFieldEntity
import com.example.data.map.AgroZoneCatalog
import com.example.data.map.FieldGeometryUtil
import com.example.data.map.OfflineTileProvider
import com.example.model.AgroEcologicalZone
import com.example.model.GeoPoint
import com.example.model.KenyaAgriculturalCounty
import com.example.model.MapLayerType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldMapScreen(
    savedFields: List<MappedFieldEntity>,
    onSaveField: (MappedFieldEntity) -> Unit,
    onDeleteField: (MappedFieldEntity) -> Unit,
    onNavigateBack: () -> Unit,
    onCalculateFertilizerForField: (Double, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var tileRevision by remember { mutableIntStateOf(0) }
    val tileProvider = remember {
        OfflineTileProvider(context.cacheDir).apply {
            onTileLoaded = { tileRevision++ }
        }
    }

    // Map Viewport state (Default: Nakuru Agricultural Basin / Kenya Rift Valley)
    var centerLat by remember { mutableDoubleStateOf(-0.3031) }
    var centerLng by remember { mutableDoubleStateOf(36.0800) }
    var zoomLevel by remember { mutableIntStateOf(16) }
    var layerType by remember { mutableStateOf(MapLayerType.GOOGLE_SATELLITE) }

    // Boundary vertices
    val boundaryPoints = remember { mutableStateListOf<GeoPoint>() }
    var selectedPointIndex by remember { mutableStateOf<Int?>(null) }

    // Current identified Agro-Zone
    val currentZone = remember(centerLat, centerLng) {
        AgroZoneCatalog.findZoneForLocation(centerLat, centerLng)
    }

    // Boundary metrics
    val metrics = remember(boundaryPoints.toList(), currentZone) {
        FieldGeometryUtil.computeMetrics(boundaryPoints.toList(), currentZone)
    }

    // GPS & Precision Telemetry State
    var currentGpsLocation by remember { mutableStateOf<GeoPoint?>(null) }
    var gpsTelemetry by remember { mutableStateOf<GpsPrecisionTelemetry?>(null) }
    var isWalkingModeEnabled by remember { mutableStateOf(false) }
    var isFollowFarmerEnabled by remember { mutableStateOf(false) }
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Real-Time LocationListener
    val realTimeLocationListener = remember {
        object : LocationListener {
            override fun onLocationChanged(loc: Location) {
                val isFine = loc.hasAccuracy() && loc.accuracy <= 5.0f
                val providerLabel = if (loc.provider == LocationManager.GPS_PROVIDER) "Satellite GPS (Real-Time)" else "Network Triangulation"
                val telemetry = GpsPrecisionTelemetry(
                    latitude = loc.latitude,
                    longitude = loc.longitude,
                    altitudeMeters = loc.altitude,
                    accuracyMeters = if (loc.hasAccuracy()) loc.accuracy else 2.5f,
                    speedKmh = if (loc.hasSpeed()) loc.speed * 3.6f else 0f,
                    bearingDegrees = if (loc.hasBearing()) loc.bearing else 0f,
                    isHighPrecision = isFine,
                    provider = providerLabel,
                    timestamp = loc.time
                )
                gpsTelemetry = telemetry
                val pt = GeoPoint(loc.latitude, loc.longitude)
                currentGpsLocation = pt

                if (isFollowFarmerEnabled) {
                    centerLat = pt.latitude
                    centerLng = pt.longitude
                }

                if (isWalkingModeEnabled) {
                    val lastPt = boundaryPoints.lastOrNull()
                    if (lastPt == null || FieldGeometryUtil.haversineDistanceMeters(lastPt, pt) >= 3.0) {
                        boundaryPoints.add(pt)
                    }
                }
            }
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
    }

    // Register Continuous Real-Time GPS updates
    DisposableEffect(hasLocationPermission) {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        val fineGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarseGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (lm != null && (fineGranted || coarseGranted)) {
            try {
                if (fineGranted && lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, realTimeLocationListener)
                }
                if (coarseGranted && lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000L, 2.0f, realTimeLocationListener)
                }
            } catch (_: SecurityException) {}
        }

        onDispose {
            try {
                lm?.removeUpdates(realTimeLocationListener)
            } catch (_: Exception) {}
        }
    }

    // Dialog & Sheet States
    var showLayerMenu by remember { mutableStateOf(false) }
    var showCountyPicker by remember { mutableStateOf(false) }
    var showSaveFieldDialog by remember { mutableStateOf(false) }
    var showSavedFieldsSheet by remember { mutableStateOf(false) }
    var showZoneDetailsDialog by remember { mutableStateOf(false) }
    var showAgronomistDialog by remember { mutableStateOf(false) }
    var isMetricsExpanded by remember { mutableStateOf(true) }

    // Permission launcher for Location
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (fineGranted || coarseGranted) {
            hasLocationPermission = true
            fetchDeviceLocation(context) { loc, telemetry ->
                gpsTelemetry = telemetry
                if (loc != null) {
                    val pt = GeoPoint(loc.latitude, loc.longitude)
                    currentGpsLocation = pt
                    centerLat = pt.latitude
                    centerLng = pt.longitude
                    if (isWalkingModeEnabled) {
                        boundaryPoints.add(pt)
                    }
                }
            }
        }
    }

    // Standard location fetch
    fun checkAndRequestLocation() {
        val fine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (fine || coarse) {
            hasLocationPermission = true
            fetchDeviceLocation(context) { loc, telemetry ->
                gpsTelemetry = telemetry
                if (loc != null) {
                    val pt = GeoPoint(loc.latitude, loc.longitude)
                    currentGpsLocation = pt
                    centerLat = pt.latitude
                    centerLng = pt.longitude
                    if (isWalkingModeEnabled) {
                        boundaryPoints.add(pt)
                    }
                } else {
                    Toast.makeText(context, "Acquiring real-time satellite GPS fix...", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            locationPermissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }

    // Precision GPS & Satellite: Locate where the user is and map out the farm boundary
    fun autoLocateAndMapFarm() {
        val fine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (fine || coarse) {
            fetchDeviceLocation(context) { loc, telemetry ->
                gpsTelemetry = telemetry
                val targetCenter = if (loc != null) {
                    val pt = GeoPoint(loc.latitude, loc.longitude)
                    currentGpsLocation = pt
                    centerLat = pt.latitude
                    centerLng = pt.longitude
                    pt
                } else {
                    GeoPoint(centerLat, centerLng)
                }

                // Switch to high-resolution satellite hybrid view zoomed into farm
                zoomLevel = 17
                layerType = MapLayerType.SATELLITE_HYBRID

                // Generate 1.0 hectare (2.47 acre) cadastral parcel boundary around user coordinates
                val detectedCorners = FieldGeometryUtil.autoDetectFarmBoundary(targetCenter, 1.0)
                boundaryPoints.clear()
                boundaryPoints.addAll(detectedCorners)
                selectedPointIndex = null

                val msg = if (loc != null) {
                    "Farm located & mapped via Satellite GPS! (1.0 Ha parcel boundary plotted)"
                } else {
                    "Farm boundary mapped around map center (1.0 Ha cadastral parcel plotted)"
                }
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            }
        } else {
            locationPermissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Offline Field & Zone Mapper",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Lightweight offline tiles • Zero data needed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("action_map_back")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Layer Switcher Menu
                    Box {
                        IconButton(
                            onClick = { showLayerMenu = true },
                            modifier = Modifier.testTag("action_map_layers")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Layers,
                                contentDescription = "Map Layers",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        DropdownMenu(
                            expanded = showLayerMenu,
                            onDismissRequest = { showLayerMenu = false }
                        ) {
                            MapLayerType.values().forEach { layer ->
                                DropdownMenuItem(
                                    text = {
                                        Column {
                                            Text(
                                                text = layer.title,
                                                fontWeight = if (layer == layerType) FontWeight.Bold else FontWeight.Normal,
                                                color = if (layer == layerType) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = layer.description,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    },
                                    onClick = {
                                        layerType = layer
                                        showLayerMenu = false
                                    }
                                )
                            }
                        }
                    }

                    // County / Hub selector
                    IconButton(
                        onClick = { showCountyPicker = true },
                        modifier = Modifier.testTag("action_map_counties")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Pick Kenyan Region",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Saved Fields Drawer button
                    IconButton(
                        onClick = { showSavedFieldsSheet = true },
                        modifier = Modifier.testTag("action_saved_fields")
                    ) {
                        Box {
                            Icon(
                                imageVector = Icons.Default.Folder,
                                contentDescription = "Saved Fields",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (savedFields.isNotEmpty()) {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Text(
                                        text = savedFields.size.toString(),
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.padding(1.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    // Agronomist & Developer Profile (Odongo Vincent)
                    IconButton(
                        onClick = { showAgronomistDialog = true },
                        modifier = Modifier.testTag("action_map_agronomist")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Agronomist Odongo Vincent",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier.fillMaxSize().testTag("field_map_screen")
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 1. OFFLINE TILE & VECTOR MAP CANVAS
            FieldMapCanvas(
                centerLat = centerLat,
                centerLng = centerLng,
                zoomLevel = zoomLevel,
                layerType = layerType,
                tileProvider = tileProvider,
                boundaryPoints = boundaryPoints.toList(),
                selectedPointIndex = selectedPointIndex,
                currentGpsLocation = currentGpsLocation,
                tileRevision = tileRevision,
                onPanDelta = { dx: Float, dy: Float ->
                    val (wX, wY) = tileProvider.latLngToWorldPixel(centerLat, centerLng, zoomLevel)
                    val newWorldX = wX - dx
                    val newWorldY = wY - dy
                    val newCenter = tileProvider.worldPixelToLatLng(newWorldX, newWorldY, zoomLevel)
                    centerLat = newCenter.latitude
                    centerLng = newCenter.longitude
                },
                onZoomChange = { delta: Float ->
                    val newZoom = (zoomLevel + if (delta > 0f) 1 else -1).coerceIn(
                        OfflineTileProvider.MIN_ZOOM,
                        OfflineTileProvider.MAX_ZOOM
                    )
                    zoomLevel = newZoom
                },
                onMapTapped = { tappedGeoPoint: GeoPoint ->
                    selectedPointIndex = null
                    boundaryPoints.add(tappedGeoPoint)
                },
                onPointSelected = { idx: Int ->
                    selectedPointIndex = idx
                }
            )

            // 2. TOP METRICS & SATELLITE CONTROLS HUD CARD
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // Quick Map Layer Mode Selector
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FilterChip(
                        selected = layerType == MapLayerType.GOOGLE_SATELLITE,
                        onClick = { layerType = MapLayerType.GOOGLE_SATELLITE },
                        label = { Text("🛰️ Google Satellite", style = MaterialTheme.typography.labelSmall) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.testTag("chip_layer_google_satellite")
                    )
                    FilterChip(
                        selected = layerType == MapLayerType.AGRO_ECOLOGICAL,
                        onClick = { layerType = MapLayerType.AGRO_ECOLOGICAL },
                        label = { Text("🌱 Agro-Zones", style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.testTag("chip_layer_agro")
                    )
                    FilterChip(
                        selected = layerType == MapLayerType.TOPOGRAPHIC,
                        onClick = { layerType = MapLayerType.TOPOGRAPHIC },
                        label = { Text("⛰️ Topo Shading", style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.testTag("chip_layer_topo")
                    )
                    FilterChip(
                        selected = layerType == MapLayerType.CADASTRAL,
                        onClick = { layerType = MapLayerType.CADASTRAL },
                        label = { Text("📐 Cadastre Grid", style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.testTag("chip_layer_cadastral")
                    )
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth().testTag("map_metrics_card")
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Surface(
                                        shape = CircleShape,
                                        color = Color(currentZone.tileColorHex.toInt()),
                                        modifier = Modifier.size(10.dp)
                                    ) {}
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = currentZone.name,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.widthIn(max = 240.dp)
                                    )
                                }
                                Text(
                                    text = currentZone.code + " • " + currentZone.elevationRangeMeters,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Row {
                                IconButton(
                                    onClick = { showZoneDetailsDialog = true },
                                    modifier = Modifier.size(32.dp).testTag("action_zone_info")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Zone Details",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { isMetricsExpanded = !isMetricsExpanded },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isMetricsExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                        contentDescription = "Toggle Metrics",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }

                        AnimatedVisibility(
                            visible = isMetricsExpanded,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Column {
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    MetricColumn(
                                        label = "AREA",
                                        value = String.format("%.2f ha", metrics.hectares),
                                        subValue = String.format("%.2f ac", metrics.acres),
                                        highlight = metrics.hectares > 0
                                    )
                                    MetricColumn(
                                        label = "PERIMETER",
                                        value = if (metrics.perimeterMeters >= 1000) String.format("%.2f km", metrics.perimeterMeters / 1000.0) else String.format("%.0f m", metrics.perimeterMeters),
                                        subValue = "${boundaryPoints.size} corners",
                                        highlight = false
                                    )
                                    MetricColumn(
                                        label = "SLOPE EST.",
                                        value = String.format("%.1f%%", metrics.estimatedSlopePercent),
                                        subValue = if (metrics.estimatedSlopePercent > 12) "Steep" else "Gentle",
                                        highlight = false
                                    )
                                }

                                if (gpsTelemetry != null) {
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.GpsFixed,
                                                contentDescription = null,
                                                tint = if (gpsTelemetry!!.isHighPrecision) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "${gpsTelemetry!!.provider} (±${String.format("%.1f", gpsTelemetry!!.accuracyMeters)}m)",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.SemiBold,
                                                color = if (gpsTelemetry!!.isHighPrecision) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Text(
                                            text = "${String.format("%.6f", gpsTelemetry!!.latitude)}°, ${String.format("%.6f", gpsTelemetry!!.longitude)}°",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Alt: ${gpsTelemetry!!.altitudeMeters.toInt()}m ASL • Speed: ${String.format("%.1f", gpsTelemetry!!.speedKmh)} km/h",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = if (isFollowFarmerEnabled) "● LIVE TRACKING LOCKED" else "Tap GPS to Lock",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isFollowFarmerEnabled) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 3. FLOATING ON-SCREEN MAP CONTROLS (Right Side)
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.End
            ) {
                // Zoom In
                SmallFloatingActionButton(
                    onClick = {
                        zoomLevel = (zoomLevel + 1).coerceAtMost(OfflineTileProvider.MAX_ZOOM)
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.testTag("action_zoom_in")
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Zoom In")
                }

                // Zoom Out
                SmallFloatingActionButton(
                    onClick = {
                        zoomLevel = (zoomLevel - 1).coerceAtLeast(OfflineTileProvider.MIN_ZOOM)
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.testTag("action_zoom_out")
                ) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Zoom Out")
                }

                // GPS My Location & Real-Time Tracking Lock
                FloatingActionButton(
                    onClick = {
                        checkAndRequestLocation()
                        if (currentGpsLocation != null) {
                            centerLat = currentGpsLocation!!.latitude
                            centerLng = currentGpsLocation!!.longitude
                            isFollowFarmerEnabled = !isFollowFarmerEnabled
                            val msg = if (isFollowFarmerEnabled) "Live GPS Locked: Map following your real-time position" else "Free Pan Mode: You can freely scroll the map"
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    },
                    containerColor = if (isFollowFarmerEnabled) MaterialTheme.colorScheme.primary else if (currentGpsLocation != null) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                    contentColor = if (isFollowFarmerEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.testTag("action_my_location")
                ) {
                    Icon(imageVector = Icons.Default.MyLocation, contentDescription = "My GPS Location")
                }

                // Walking Boundary Trace Mode
                FloatingActionButton(
                    onClick = {
                        isWalkingModeEnabled = !isWalkingModeEnabled
                        if (isWalkingModeEnabled) {
                            checkAndRequestLocation()
                        }
                    },
                    containerColor = if (isWalkingModeEnabled) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface,
                    contentColor = if (isWalkingModeEnabled) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.testTag("action_walk_mode")
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsWalk,
                        contentDescription = "Walk Boundary"
                    )
                }

                // Undo Last Point
                if (boundaryPoints.isNotEmpty()) {
                    SmallFloatingActionButton(
                        onClick = {
                            if (boundaryPoints.isNotEmpty()) {
                                boundaryPoints.removeAt(boundaryPoints.size - 1)
                                selectedPointIndex = null
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.testTag("action_undo_point")
                    ) {
                        Icon(imageVector = Icons.Default.Undo, contentDescription = "Undo Point")
                    }

                    // Clear All Points
                    SmallFloatingActionButton(
                        onClick = {
                            boundaryPoints.clear()
                            selectedPointIndex = null
                        },
                        containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f),
                        contentColor = MaterialTheme.colorScheme.error,
                        modifier = Modifier.testTag("action_clear_points")
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear All")
                    }
                }
            }

            // 4. BOTTOM ACTION CONTROL DECK
            Card(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .testTag("map_bottom_action_deck")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    if (isWalkingModeEnabled) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.DirectionsWalk,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Walk Field Active • Walk along field perimeter and tap '+ Add GPS Corner' at each boundary turn.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    // 1-Tap Precision Satellite & GPS Farm Mapping
                    Button(
                        onClick = { autoLocateAndMapFarm() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .testTag("action_auto_map_farm")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Satellite,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Locate & Map My Farm (Satellite GPS)",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Add Pin at Map Center
                        OutlinedButton(
                            onClick = {
                                boundaryPoints.add(GeoPoint(centerLat, centerLng))
                            },
                            modifier = Modifier.weight(1f).testTag("action_add_center_pin")
                        ) {
                            Icon(imageVector = Icons.Default.AddLocation, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Add Pin", maxLines = 1)
                        }

                        // Save Boundary Button
                        Button(
                            onClick = { showSaveFieldDialog = true },
                            enabled = boundaryPoints.size >= 3,
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                            modifier = Modifier.weight(1f).testTag("action_save_field")
                        ) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Save Field", maxLines = 1)
                        }

                        // Calculate Fertilizer Button
                        Button(
                            onClick = {
                                val area = if (metrics.hectares > 0) metrics.hectares else 1.0
                                onCalculateFertilizerForField(area, "Maize")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.weight(1.3f).testTag("action_calculate_field_fertilizer")
                        ) {
                            Icon(imageVector = Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Fertilizer", maxLines = 1, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }

    // 5. REGION / COUNTY SELECTOR DIALOG
    if (showCountyPicker) {
        AlertDialog(
            onDismissRequest = { showCountyPicker = false },
            title = {
                Text("Jump to Kenyan Agricultural Hub", fontWeight = FontWeight.Bold)
            },
            text = {
                LazyColumn(modifier = Modifier.height(300.dp)) {
                    items(AgronomyCatalog.kenyanCounties) { county ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    centerLat = county.latitude
                                    centerLng = county.longitude
                                    zoomLevel = 14
                                    showCountyPicker = false
                                }
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = county.name,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = county.region + " • " + county.primaryCrops,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCountyPicker = false }) {
                    Text("Close")
                }
            }
        )
    }

    // 6. SAVE FIELD BOUNDARY DIALOG
    if (showSaveFieldDialog) {
        var fieldName by remember { mutableStateOf("Plot ${savedFields.size + 1} - ${currentZone.name.take(15)}") }
        var selectedCrop by remember { mutableStateOf("Maize") }
        var notesText by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showSaveFieldDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Agriculture, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Mapped Field", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        text = "Area: ${String.format("%.2f ha", metrics.hectares)} (${String.format("%.2f acres", metrics.acres)}) • Perimeter: ${String.format("%.0f m", metrics.perimeterMeters)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = fieldName,
                        onValueChange = { fieldName = it },
                        label = { Text("Field / Shamba Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("input_field_name")
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Crop to Plant / Cultivate:", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(4.dp))

                    val crops = listOf("Maize", "Irish Potatoes", "Tomatoes", "French Beans", "Coffee", "Tea", "Cabbage", "Wheat", "Cassava", "Pasture")
                    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                        crops.forEach { crop ->
                            FilterChip(
                                selected = selectedCrop == crop,
                                onClick = { selectedCrop = crop },
                                label = { Text(crop) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                modifier = Modifier.padding(end = 6.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = notesText,
                        onValueChange = { notesText = it },
                        label = { Text("Soil & Irrigation Notes (Optional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val pointsStr = boundaryPoints.joinToString(";") { "${it.latitude},${it.longitude}" }
                        val dateFormat = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault())
                        val entity = MappedFieldEntity(
                            name = fieldName.ifBlank { "Shamba Plot" },
                            cropName = selectedCrop,
                            hectares = metrics.hectares,
                            acres = metrics.acres,
                            perimeterMeters = metrics.perimeterMeters,
                            pointsJson = pointsStr,
                            zoneName = currentZone.name,
                            soilType = currentZone.soilType,
                            slopeCategory = metrics.slopeCategory,
                            notes = notesText,
                            dateCreated = dateFormat.format(Date())
                        )
                        onSaveField(entity)
                        showSaveFieldDialog = false
                    },
                    modifier = Modifier.testTag("action_confirm_save_field")
                ) {
                    Text("Save to Device")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveFieldDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // 7. SAVED FIELDS DRAWER / BOTTOM SHEET
    if (showSavedFieldsSheet) {
        AlertDialog(
            onDismissRequest = { showSavedFieldsSheet = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Folder, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Saved Shamba Fields (${savedFields.size})", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                if (savedFields.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                        Text("No mapped fields saved yet. Tap points on the map and click 'Save Field'.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    LazyColumn(modifier = Modifier.height(340.dp)) {
                        items(savedFields) { field ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = field.name,
                                                fontWeight = FontWeight.Bold,
                                                style = MaterialTheme.typography.titleSmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Text(
                                                text = "Crop: ${field.cropName} • ${field.dateCreated}",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }

                                        IconButton(
                                            onClick = { onDeleteField(field) },
                                            modifier = Modifier.size(28.dp)
                                        ) {
                                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "${String.format("%.2f", field.hectares)} ha (${String.format("%.2f", field.acres)} ac) • Perimeter: ${String.format("%.0f", field.perimeterMeters)}m",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = field.zoneName + " • " + field.soilType,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedButton(
                                            onClick = {
                                                // Load boundary points onto map
                                                boundaryPoints.clear()
                                                if (field.pointsJson.isNotBlank()) {
                                                    val ptPairs = field.pointsJson.split(";")
                                                    ptPairs.forEach { pair ->
                                                        val coords = pair.split(",")
                                                        if (coords.size == 2) {
                                                            val lat = coords[0].toDoubleOrNull()
                                                            val lng = coords[1].toDoubleOrNull()
                                                            if (lat != null && lng != null) {
                                                                boundaryPoints.add(GeoPoint(lat, lng))
                                                            }
                                                        }
                                                    }
                                                    if (boundaryPoints.isNotEmpty()) {
                                                        centerLat = boundaryPoints.first().latitude
                                                        centerLng = boundaryPoints.first().longitude
                                                        zoomLevel = 16
                                                    }
                                                }
                                                showSavedFieldsSheet = false
                                            },
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text("Load on Map", style = MaterialTheme.typography.labelSmall)
                                        }

                                        Button(
                                            onClick = {
                                                onCalculateFertilizerForField(field.hectares, field.cropName)
                                                showSavedFieldsSheet = false
                                            },
                                            modifier = Modifier.weight(1.3f)
                                        ) {
                                            Icon(imageVector = Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("Fertilizer", style = MaterialTheme.typography.labelSmall)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showSavedFieldsSheet = false }) {
                    Text("Close")
                }
            }
        )
    }

    // 8. AGRO-ECOLOGICAL ZONE DETAILS DIALOG
    if (showZoneDetailsDialog) {
        AlertDialog(
            onDismissRequest = { showZoneDetailsDialog = false },
            title = {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = Color(currentZone.tileColorHex.toInt()),
                            modifier = Modifier.size(14.dp)
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(currentZone.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    }
                    Text(
                        text = currentZone.code + " • " + currentZone.localZoneName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    DetailRow(title = "Elevation Range", content = currentZone.elevationRangeMeters)
                    DetailRow(title = "Annual Rainfall", content = currentZone.annualRainfallMm)
                    DetailRow(title = "Dominant Soil Type", content = "${currentZone.soilType} — ${currentZone.soilCharacteristics}")
                    DetailRow(title = "Optimal Commercial Crops", content = currentZone.primaryCrops.joinToString(", "))
                    DetailRow(title = "Regional Fertilizer Advisory", content = currentZone.fertilizerAdvice)
                    DetailRow(title = "Erosion, Slope & Terracing", content = currentZone.drainageSlopeAdvice)
                    DetailRow(
                        title = "Lead Agronomist & Crop Scientist",
                        content = "${DeveloperInfo.NAME} (${DeveloperInfo.TITLE}) • Tel: ${DeveloperInfo.PHONE} • Email: ${DeveloperInfo.EMAIL}"
                    )
                }
            },
            confirmButton = {
                Button(onClick = { showZoneDetailsDialog = false }) {
                    Text("Done")
                }
            }
        )
    }

    // 9. CROP SCIENTIST & DEVELOPER PROFILE DIALOG
    if (showAgronomistDialog) {
        AgronomistProfileDialog(onDismiss = { showAgronomistDialog = false })
    }
}

@Composable
private fun MetricColumn(label: String, value: String, subValue: String, highlight: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = if (highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = subValue,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DetailRow(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun fetchDeviceLocation(
    context: Context,
    onLocation: (Location?, GpsPrecisionTelemetry?) -> Unit
) {
    try {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        if (lm != null) {
            val hasGps = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val hasNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            var loc: Location? = null
            var providerName = "GPS (Satellite)"
            if (hasGps && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                providerName = "Satellite GPS (Fine Fix)"
            }
            if (loc == null && hasNetwork && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                providerName = "Cell/Network Triangulation"
            }
            if (loc != null) {
                val telemetry = GpsPrecisionTelemetry(
                    latitude = loc.latitude,
                    longitude = loc.longitude,
                    altitudeMeters = loc.altitude,
                    accuracyMeters = if (loc.hasAccuracy()) loc.accuracy else 2.5f,
                    speedKmh = if (loc.hasSpeed()) loc.speed * 3.6f else 0f,
                    bearingDegrees = if (loc.hasBearing()) loc.bearing else 0f,
                    isHighPrecision = loc.hasAccuracy() && loc.accuracy <= 5.0f,
                    provider = providerName,
                    timestamp = loc.time
                )
                onLocation(loc, telemetry)
            } else {
                onLocation(null, null)
            }
        } else {
            onLocation(null, null)
        }
    } catch (e: Exception) {
        onLocation(null, null)
    }
}
