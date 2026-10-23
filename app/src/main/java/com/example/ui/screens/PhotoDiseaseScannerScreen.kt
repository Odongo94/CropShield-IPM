package com.example.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Warning
import com.example.model.ThreatType
import com.example.ui.components.DiseaseLifecycleStepper
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.R
import com.example.data.scanner.ScanDiagnosisResult
import com.example.model.ChemicalRecommendation
import com.example.model.CropThreat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PhotoDiseaseScannerScreen(
    scannedBitmap: Bitmap?,
    isScanning: Boolean,
    scanResult: ScanDiagnosisResult?,
    scanError: String?,
    selectedCropHint: String,
    onCropHintSelected: (String) -> Unit,
    onPhotoCaptured: (Bitmap) -> Unit,
    onResetScan: () -> Unit,
    onOpenThreatDetail: (CropThreat) -> Unit,
    onOpenCalculator: (CropThreat?, ChemicalRecommendation?) -> Unit,
    onSaveToFieldNotes: (crop: String, threat: String) -> Unit,
    modifier: Modifier = Modifier,
    selectedCategoryFilter: ThreatType? = null,
    onCategoryFilterSelected: (ThreatType?) -> Unit = {}
) {
    val context = LocalContext.current
    var showPermissionRationale by remember { mutableStateOf(false) }

    // Camera capture launcher (direct thumbnail bitmap from native intent)
    val takePicturePreviewLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            onPhotoCaptured(bitmap)
        }
    }

    // Camera permission request launcher
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            takePicturePreviewLauncher.launch(null)
        } else {
            showPermissionRationale = true
        }
    }

    // Photo picker launcher for picking from gallery
    val pickVisualMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    if (bitmap != null) {
                        onPhotoCaptured(bitmap)
                    }
                }
            } catch (e: Exception) {
                // handle decode error gracefully
            }
        }
    }

    val crops = listOf(
        "Auto-Detect",
        "Roses (Cut Flowers)",
        "Carnations",
        "Chrysanthemums",
        "French Beans / Legumes",
        "Tomato",
        "Potato",
        "Cabbage / Brassicas",
        "Onion & Garlic",
        "Maize / Cereals",
        "Wheat & Barley",
        "Pepper"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            ),
            modifier = Modifier.fillMaxWidth().testTag("scanner_header_card")
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Real-Time Threat & Soil AI Scanner",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Photograph leaves, insect pests, indicator weeds, or soil profiles to identify threats & calculate exact IPM controls.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Diagnostic Domain & Category Filter
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "Diagnostic Domain & Focus:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Auto-Detect Chip
                val isAllSelected = selectedCategoryFilter == null
                FilterChip(
                    selected = isAllSelected,
                    onClick = { onCategoryFilterSelected(null) },
                    label = { Text("Auto-Detect (All)") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Science,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.testTag("filter_domain_auto")
                )

                // Crop Disease Chip
                val isDiseaseSelected = selectedCategoryFilter == ThreatType.DISEASE
                FilterChip(
                    selected = isDiseaseSelected,
                    onClick = { onCategoryFilterSelected(if (isDiseaseSelected) null else ThreatType.DISEASE) },
                    label = { Text("Crop Diseases") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Coronavirus,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.testTag("filter_domain_disease")
                )

                // Crop Pest Chip
                val isPestSelected = selectedCategoryFilter == ThreatType.PEST
                FilterChip(
                    selected = isPestSelected,
                    onClick = { onCategoryFilterSelected(if (isPestSelected) null else ThreatType.PEST) },
                    label = { Text("Crop Pests") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.BugReport,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.testTag("filter_domain_pest")
                )

                // Invasive & Indicator Weeds Chip
                val isWeedSelected = selectedCategoryFilter == ThreatType.WEED
                FilterChip(
                    selected = isWeedSelected,
                    onClick = { onCategoryFilterSelected(if (isWeedSelected) null else ThreatType.WEED) },
                    label = { Text("Weeds & Indicators") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Grass,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.testTag("filter_domain_weed")
                )

                // Soil Type & Health Chip
                val isSoilSelected = selectedCategoryFilter == ThreatType.SOIL
                FilterChip(
                    selected = isSoilSelected,
                    onClick = { onCategoryFilterSelected(if (isSoilSelected) null else ThreatType.SOIL) },
                    label = { Text("Soil Profiles & Health") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Terrain,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.testTag("filter_domain_soil")
                )
            }
        }

        // Target Crop Selector
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "Target Crop (Improves Accuracy):",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                crops.forEach { crop ->
                    val isSelected = crop == selectedCropHint
                    FilterChip(
                        selected = isSelected,
                        onClick = { onCropHintSelected(crop) },
                        label = { Text(crop) },
                        leadingIcon = if (isSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.testTag("crop_chip_$crop")
                    )
                }
            }
        }

        // Viewfinder / Image Preview Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF1E2620))
                .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (scannedBitmap != null) {
                Image(
                    bitmap = scannedBitmap.asImageBitmap(),
                    contentDescription = "Photographed Crop Sample",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Laser scan line animation during analysis
                if (isScanning) {
                    val transition = rememberInfiniteTransition(label = "scan_transition")
                    val scanProgress by transition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1400, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "laser_y"
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    0f to Color.Transparent,
                                    scanProgress - 0.05f to Color.Transparent,
                                    scanProgress to MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                                    scanProgress + 0.05f to Color.Transparent,
                                    1f to Color.Transparent
                                )
                            )
                    )

                    // Target scanning reticle
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                    )
                }

                // Close / Reset photo button overlay
                IconButton(
                    onClick = onResetScan,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                        .testTag("button_reset_scan")
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Photo",
                        tint = Color.White
                    )
                }
            } else {
                // Empty Viewfinder placeholder
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.White.copy(alpha = 0.1f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = "Align leaf or diseased crop tissue inside frame",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.85f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Good lighting & clear close-up focus increases diagnostic accuracy",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Scanning Status Overlay Badge
            if (isScanning) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.Black.copy(alpha = 0.8f),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Analyzing visual symptoms...",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Photo Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    val hasPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED

                    if (hasPermission) {
                        takePicturePreviewLauncher.launch(null)
                    } else {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .testTag("button_take_photo"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Take Photo", fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = {
                    pickVisualMediaLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .testTag("button_pick_gallery"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoLibrary,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("From Gallery", fontWeight = FontWeight.Medium)
            }
        }

        // Quick Test Presets (Ideal for testing on emulator without a physical camera lens)
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            modifier = Modifier.fillMaxWidth().testTag("sample_photos_card")
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Or Test with Field Crop Samples:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Sample 1: Late Blight (Vegetables)
                    SamplePhotoChip(
                        title = "Late Blight",
                        subtitle = "Tomato/Potato rot",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.sample_late_blight)
                            onCropHintSelected("Tomato")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_late_blight"
                    )

                    // Sample 2: Bean Rust (Legumes)
                    SamplePhotoChip(
                        title = "Bean Rust",
                        subtitle = "Legume rust pustules",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.sample_bean_rust)
                            onCropHintSelected("French Beans / Legumes")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_bean_rust"
                    )

                    // Sample 3: Cereal Rust (Cereals)
                    SamplePhotoChip(
                        title = "Cereal Rust",
                        subtitle = "Wheat/Maize stripe",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.sample_cereal_rust)
                            onCropHintSelected("Wheat & Barley")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_cereal_rust"
                    )

                    // Sample 4: Flower Thrips (Flowers)
                    SamplePhotoChip(
                        title = "Western Flower Thrips",
                        subtitle = "Rose petal silvering",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.img_flower_thrips)
                            onCropHintSelected("Roses (Cut Flowers)")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_flower_thrips"
                    )

                    // Sample 5: Fall Armyworm (Cereals)
                    SamplePhotoChip(
                        title = "Fall Armyworm",
                        subtitle = "Maize whorl frass",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.img_fall_armyworm)
                            onCropHintSelected("Maize / Cereals")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_armyworm"
                    )

                    // Sample 6: Bean Anthracnose (Legumes)
                    SamplePhotoChip(
                        title = "Bean Anthracnose",
                        subtitle = "French bean pod rot",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.img_bean_anthracnose)
                            onCropHintSelected("French Beans / Legumes")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_bean_anthracnose"
                    )

                    // Sample 7: Tuta Absoluta (Vegetables)
                    SamplePhotoChip(
                        title = "Tomato Leafminer (Tuta)",
                        subtitle = "Blotch leaf mines",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.img_tuta_absoluta)
                            onCropHintSelected("Tomato")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_tuta_absoluta"
                    )

                    // Sample 8: Bacterial Wilt (Vegetables)
                    SamplePhotoChip(
                        title = "Bacterial Wilt",
                        subtitle = "Sudden green wilt",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.img_bacterial_wilt)
                            onCropHintSelected("Tomato")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_bacterial_wilt"
                    )

                    // Sample 9: Whiteflies (Vegetables & Flowers)
                    SamplePhotoChip(
                        title = "Whiteflies",
                        subtitle = "Under-leaf colonies",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.img_whiteflies)
                            onCropHintSelected("Tomato")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_whiteflies"
                    )

                    // Sample 10: Rose Powdery Mildew (Cut Flowers)
                    SamplePhotoChip(
                        title = "Rose Powdery Mildew",
                        subtitle = "Cut flower conidia",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.sample_rose_powdery_mildew)
                            onCropHintSelected("Roses (Cut Flowers)")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_rose_mildew"
                    )

                    // Sample 11: Spider Mites (Cut Flowers)
                    SamplePhotoChip(
                        title = "Two-Spotted Spider Mite",
                        subtitle = "Rose stippling & silk",
                        onClick = {
                            val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.sample_rose_spider_mite)
                            onCropHintSelected("Roses (Cut Flowers)")
                            onPhotoCaptured(bmp)
                        },
                        tag = "sample_rose_spider_mite"
                    )
                }
            }
        }

        // Permission rationale message if denied
        AnimatedVisibility(visible = showPermissionRationale) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Text(
                        text = "Camera access is needed to capture leaf photos. You can still use 'From Gallery' or tap sample field photos above.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { showPermissionRationale = false }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }
        }

        // Scan Error Display
        if (scanError != null) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "Diagnostic notice: $scanError",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }

        // RESULTS CARD (When a scan is completed)
        if (scanResult != null && !isScanning) {
            ScanResultCard(
                result = scanResult,
                onOpenThreatDetail = {
                    scanResult.threat?.let { onOpenThreatDetail(it) }
                },
                onOpenCalculator = {
                    onOpenCalculator(scanResult.threat, scanResult.recommendedChemicalObject)
                },
                onSaveToFieldNotes = {
                    onSaveToFieldNotes(scanResult.crop, scanResult.diseaseName)
                },
                onScanAnother = onResetScan
            )
        }
    }
}

@Composable
private fun SamplePhotoChip(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    tag: String
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.testTag("button_$tag")
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ScanResultCard(
    result: ScanDiagnosisResult,
    onOpenThreatDetail: () -> Unit,
    onOpenCalculator: () -> Unit,
    onSaveToFieldNotes: () -> Unit,
    onScanAnother: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("scan_result_card")
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header: Title & Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Diagnosis Result",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = result.diseaseName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = result.scientificName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Confidence pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.testTag("badge_confidence")
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "${result.confidencePercent}%",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Match",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // Severity, Domain & Engine Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Domain Badge
                val domainColor = when (result.diagnosticCategory) {
                    ThreatType.DISEASE -> Color(0xFFC62828)
                    ThreatType.PEST -> Color(0xFFD97706)
                    ThreatType.WEED -> Color(0xFF059669)
                    ThreatType.SOIL -> Color(0xFF7C3AED)
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = domainColor
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = when (result.diagnosticCategory) {
                                ThreatType.DISEASE -> Icons.Default.Coronavirus
                                ThreatType.PEST -> Icons.Default.BugReport
                                ThreatType.WEED -> Icons.Default.Grass
                                ThreatType.SOIL -> Icons.Default.Terrain
                            },
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = result.diagnosticCategory.label,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                val severityBg = when (result.severity.lowercase()) {
                    "critical", "extreme" -> Color(0xFFB3261E)
                    "severe", "high" -> Color(0xFFE65100)
                    "moderate" -> Color(0xFFF57F17)
                    else -> Color(0xFF2E7D32)
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = severityBg
                ) {
                    Text(
                        text = "Severity: ${result.severity}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (result.isOnlineAiAnalysis) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (result.isOnlineAiAnalysis) Icons.Default.Science else Icons.Default.Shield,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = if (result.isOnlineAiAnalysis) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = if (result.isOnlineAiAnalysis) "Gemini Vision AI" else "Offline IPM Optical Engine",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium,
                            color = if (result.isOnlineAiAnalysis) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

            // Agronomic Crop Targets & Vulnerability Profile
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                ),
                modifier = Modifier.fillMaxWidth().testTag("card_agronomic_targets")
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Eco,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Crop Target & Agronomic Vulnerability:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Hosts / Context: ${result.targetCropsList.joinToString(", ")}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Vulnerable Growth Stages: ${result.vulnerableCropStages}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Yield Loss Risk: ${result.yieldLossPotential}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "Economic Threshold (ETL): ${result.economicThresholdInfo}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Interactive Multi-Stage Lifecycle Stepper
            if (result.threat != null && result.threat.lifecycleStages.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth().testTag("card_interactive_lifecycle")
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Interactive Lifecycle & Critical Intervention Window:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        DiseaseLifecycleStepper(threat = result.threat)
                    }
                }
            }

            // Identified Visual Symptoms
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Identified Diagnostic Symptoms & Signals:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                result.visualSymptomsDetected.forEach { symptom ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp).padding(top = 2.dp)
                        )
                        Text(
                            text = symptom,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // 1. Infonet-Biovision Organic & Cultural Practices
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ),
                modifier = Modifier.fillMaxWidth().testTag("card_infonet_action")
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Eco,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Infonet-Biovision Organic & Cultural Control:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = if (result.infonetOrganicAction.isNotBlank()) result.infonetOrganicAction else result.recommendedCulturalAction,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // 2. CABI BioProtection Biological Control
            val cabiBio = if (result.cabiBiocontrolSolution.isNotBlank()) result.cabiBiocontrolSolution
                          else result.threat?.cabiBioProtections?.firstOrNull()?.let { "${it.tradeName} (${it.activeAgent}) - ${it.modeOfAction}. Rate: ${it.applicationRate}" } ?: ""
            if (cabiBio.isNotBlank()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.35f)
                    ),
                    modifier = Modifier.fillMaxWidth().testTag("card_cabi_biocontrol")
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.BugReport,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "CABI BioProtection Biological Solution:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        Text(
                            text = cabiBio,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // 3. PCPB Kenya Verified Chemical Solutions & Resistance Management
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                ),
                modifier = Modifier.fillMaxWidth().testTag("card_pcpb_chemicals")
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "PCPB Kenya Registered Chemical Controls (IPM):",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    val pcpbList = result.threat?.pcpbChemicals
                    if (!pcpbList.isNullOrEmpty()) {
                        for (chem in pcpbList) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = chem.tradeName,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = chem.pcpbRegNo,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Active: ${chem.activeIngredient} (${chem.fracIracGroup})",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Knapsack Dosage: ${chem.knapsackDosage} | PHI: ${chem.phiDays} days",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = chem.lastingIpmRule,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }
                    } else if (result.recommendedChemicals.isNotEmpty()) {
                        for (chem in result.recommendedChemicals) {
                            Text(
                                text = "• $chem",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // 4. Physical & Mechanical Control Measures
            if (result.mechanicalControlAction.isNotBlank()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth().testTag("card_mechanical_controls")
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Layers,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Physical & Mechanical Control Measures:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = result.mechanicalControlAction,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // 5. Soil Science, Nutrient Deficiency & Conservation Management
            val soilProfile = result.threat?.soilIndicatorProfile
            if (soilProfile != null) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                    ),
                    modifier = Modifier.fillMaxWidth().testTag("card_soil_indicator_profile")
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Terrain,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Soil Science & Conservation Profile:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = "Soil Profile: ${soilProfile.inferredSoilType} (${soilProfile.soilPhRangeText})",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Condition: ${soilProfile.physicalStructure}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Nutrient Deficiency: ${soilProfile.deficienciesIndicated.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = "Recommended Amendments: ${soilProfile.recommendedAmendments.joinToString("; ")}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Technical Agronomic Advisory: ${soilProfile.cropScientistExplanation}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

            // Action Buttons
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Button 1: Open Full Threat Detail & Lifecycle
                Button(
                    onClick = onOpenThreatDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("button_view_full_lifecycle"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View Full Guide & Lifecycle Stages", fontWeight = FontWeight.Bold)
                }

                // Button 2: Knapsack mix calculation
                OutlinedButton(
                    onClick = onOpenCalculator,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("button_open_knapsack_calculator"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Calculate Knapsack Spray Mix")
                }

                // Button 3: Save to Field Notes & Scout Log
                OutlinedButton(
                    onClick = onSaveToFieldNotes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("button_save_to_field_notes"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Log Scan into Field Notes")
                }

                // Button 4: Scan Another Photo
                Button(
                    onClick = onScanAnother,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("button_scan_another"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Scan Another Photo")
                }
            }
        }
    }
}
