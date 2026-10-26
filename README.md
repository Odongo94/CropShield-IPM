# CropShield IPM 🌾🛡️

[![Kotlin Version](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg)](https://kotlinlang.org)
[![Android Gradle Plugin](https://img.shields.io/badge/AGP-8.5.0-green.svg)](https://developer.android.com/studio/releases/gradle-plugin)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-M3-brightgreen.svg)](https://developer.android.com/jetpack/compose)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg)](https://developer.android.com/about/dashboards)
[![Target SDK](https://img.shields.io/badge/Target%20SDK-36-blueviolet.svg)](https://developer.android.com/about/versions/16)
[![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)](LICENSE)

> **Offline crop agronomy, weed bio-indicator soil diagnostician, crop pathology, agricultural entomology, precision scouting sheets, GPS field mapping, soil classification calculator, and crop rotation suite signed off by Crop Scientist Odongo Vincent.**

---

## 📖 Overview

**CropShield IPM** is an advanced, offline-first precision agriculture and Integrated Pest Management (IPM) Android application developed for agronomists, field scouts, extension officers, and commercial growers. Designed specifically to operate reliably in rural and off-grid farming environments, CropShield delivers evidence-based agronomic intelligence, mathematical dosing calculations, satellite GIS mapping, and soil classification tools without requiring constant cellular connectivity.

---

## ✨ Key Features

### 1. 🧪 Soil Classification Calculator & USDA Texture Triangle
* **Interactive USDA Texture Triangle**: Custom-engineered Jetpack Compose `Canvas` rendering all 12 USDA textural classes with distinct harmonic color fills and high-contrast boundaries.
* **Animated Pulsing Pointer**: Dynamic coordinate pin and ripple animation pointing directly to the classified coordinate based on user input.
* **Barycentric Touch & Drag**: Interactive touch input on the triangle canvas to explore soil textural classes and immediately read particle fractions.
* **Dual Input Modes**:
  * **Particle Percentage Sliders (%)**: Sliders for Sand, Silt, and Clay with real-time normalization and a 1-tap **"Auto-Balance to 100%"** utility.
  * **Sedimentation Jar Test (Field Method)**: Input measured layer heights (mm) of settled Sand (1-min), Silt (2-hr), and Clay (24-hr) to compute exact fractions.
* **Regional Soil Taxonomies**: Pre-calibrated presets for East African soils:
  * *Humic Nitisols* (Kikuyu Red Clays)
  * *Eutric Vertisols* (Black Cotton Soils)
  * *Volcanic Andosols* (Rift Valley Silt Loams)
  * *Arenosols & Ferralsols* (Coastal Sands & Sandy Loams)

### 2. 🌾 Tailored Agronomic Advisory Engine
* **Crop Suitability Matrix**: Classifies crops into *Highly Suitable*, *Moderate (requires raised beds or amendments)*, and *Unsuitable / High Risk* with clear agronomic reasoning.
* **Fertility & Dosing Management**:
  * Cation Exchange Capacity (CEC) rating and nutrient retention dynamics.
  * Basal placement guidelines to mitigate Phosphorus fixation in volcanic clays.
  * Split-N topdressing schedule to prevent nitrate leaching in sandy profiles.
  * Well-decomposed farmyard manure/compost rates (metric tonnes/ha).
  * Liming and soil conditioning protocols (Calcitic lime vs. Agricultural Gypsum for sodic Vertisols).
* **Water Application & Irrigation Scheduling**:
  * Infiltration rates ($\text{mm/hr}$) and Available Water Capacity (AWC in $\text{mm/m}$).
  * Irrigation system recommendations (low-rate drip, micro-sprinklers, furrow basins).
  * Pulse irrigation vs. deep watering intervals to avert waterlogging and root asphyxiation.
* **Routine Field Management Practices**:
  * Cultivation moisture workability window to eliminate plastic clods and hardpans.
  * Organic mulching standards and subsoiling/ripping intervals.
  * Routine laboratory soil testing schedules.

### 3. 🔄 Interactive Crop Rotation Pattern Designer
* **4-Season Multi-Year Matrix**: Interactive rotation builder across 4 agronomic plant families:
  1. **Heavy Feeders** (*Maize, Cabbage, Potatoes, Tomatoes, Sorghum*)
  2. **Legume Nitrogen Fixers** (*Common Beans, Peas, Cowpeas, Desmodium*)
  3. **Light Feeders & Root Crops** (*Carrots, Onions, Garlic, Sweet Potatoes*)
  4. **Soil Conditioners & Cover Crops** (*Sunn Hemp, Forage Oats, Brachiaria, Vetch*)
* **Diagnostic Sustainability Scorecard**:
  * **Pest & Pathogen Break Index (0–100)**: Evaluates non-host succession.
  * **Biological Nitrogen Balance (0–100)**: Computes legume Rhizobia replenishment vs. heavy feeder extraction.
  * **Weed & Soil Structure Conditioning (0–100)**: Analyzes root bio-drilling and canopy weed smothering.
* **Critical Pathogen Alerts**: Flags consecutive Solanaceae plantings (e.g. Potatoes $\rightarrow$ Tomatoes) to break Late Blight (*Phytophthora infestans*) and Bacterial Wilt (*Ralstonia solanacearum*).
* **Pre-Validated Templates**:
  * *Commercial Vegetable & Pulse Cycle*
  * *Highland Cereal & Tuber Sequence*
  * *Grain, Pulse & Fodder System*
  * *Nematode Bio-Fumigation Brassica Cycle*

### 4. 🧮 Knapsack & Commercial Tank Dilution Calculator
* **Multi-Volume Support**: Supports standard 16L–20L backpack knapsacks up to commercial 500L, 1000L, 1500L, and 2000L tractor spray rigs and boom sprayers.
* **Dual Calculation Modes**:
  * **Fertilizer Dosing**: Basal, topdressing, and foliar spray tank allocations in grams, kilograms, milliliters, and liters.
  * **Pesticide / Fungicide Dilution**: Dilution rate calculator with water carrier volume, application rates, pre-harvest intervals (PHI), and re-entry intervals (REI).

### 5. 🗺️ Offline GPS Field Mapping & Google Satellite GIS
* **Satellite Base Layer**: High-resolution Google Satellite imagery stream with intelligent disk and LRU memory caching for offline field viewing.
* **Perimeter Walk & Pin Drop**: Walk boundaries using real-time GPS tracking or drop pins to measure farm area in Hectares and Acres.
* **Direct Knapsack Linking**: Instantly transfer mapped acreage into the Knapsack Calculator.

### 6. 🔍 IPM Disease & Pest Identification
* **Factual Diagnostic Key**: Two-tab botanical symptom diagnostic tree (leaf spots, wilts, stem borers, sucking pests, fungal blights) with zero heuristic guessing.
* **Crop Production Library**: Detailed production guides covering cereals, pulses, vegetables, cut flowers (roses, carnations, summer flowers), and perennial fruit orchards.
* **Disease Lifecycle Lab**: Interactive 4-stage pathogenesis visualizer (Inoculation, Incubation, Symptom Expression, Overwintering) detailing optimal chemical/biological intervention windows.

### 7. 📋 Precision Scouting Sheets & Field Notes
* **Customizable Scouting Sheets**: Edit scout names, farm blocks, variety batches, and inspection dates.
* **Standardized Monitoring Protocols**: Economic Threshold Levels (ETL) and Economic Injury Levels (EIL) with CSV report export.
* **Offline Field Notes**: Store timestamped observations, pest severity scores, and photos using local Room Database persistence.

### 8. 📚 Agricultural Science & Research Journals
* **Peer-Reviewed Publications**: Curated technical reference catalog from KALRO, ICIPE, CABI, CIMMYT, and FAO.
* **Interactive Agronomy Quiz**: Built-in 20-question agricultural science examination covering soil chemistry, entomology, plant pathology, and weed biology.

---

## 🛠️ Architecture & Tech Stack

CropShield IPM is engineered adhering to modern Android development best practices:

* **Language**: [Kotlin](https://kotlinlang.org) (100%)
* **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material Design 3 (M3)
* **Architecture**: Model-View-ViewModel (MVVM) + Clean Architecture
* **State Management**: Kotlin Coroutines & `MutableStateFlow` with `collectAsStateWithLifecycle`
* **Local Persistence**: [Room Database](https://developer.android.com/training/data-storage/room) with KSP (Kotlin Symbol Processing)
* **Custom Graphics**: Jetpack Compose `Canvas` with Barycentric ternary coordinate transformation
* **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
* **Testing**: [Robolectric](https://robolectric.org/) and JUnit 4 for local JVM unit and integration testing

---

## 📁 Project Structure

```
app/src/main/java/com/example/
├── MainActivity.kt                       # Single-activity container & navigation routing
├── data/
│   ├── AgronomyCatalog.kt               # Fertilizer plans, spray rates, knapsack math
│   ├── CropProductionCatalog.kt         # Comprehensive crop production database
│   ├── ThreatCatalog.kt                 # Factual pests, diseases, and IPM solutions
│   ├── local/                           # Room Database, DAOs, and Entity models
│   ├── scanner/                         # Photo diagnosis and botanical symptom matcher
│   ├── science/                         # Research publications, journals, and learning quiz
│   ├── soil/
│   │   └── SoilClassificationCatalog.kt # USDA classification algorithm, profiles & rotation templates
│   └── weather/                         # 47 Kenya counties weather repository & GPS detection
├── model/
│   ├── CropThreat.kt                    # Pest, disease, and IPM data models
│   ├── KnapsackModels.kt                # Spray tank and chemical formulation models
│   ├── WeedModel.kt                     # Weed species and herbicide timing models
│   └── soil/
│       └── SoilClassificationModel.kt   # USDA classes, coordinates, fertility, water & rotation models
├── ui/
│   ├── components/
│   │   ├── CropRotationDesignerComponent.kt # Interactive 4-season rotation pattern builder
│   │   ├── DiseaseLifecycleStepper.kt       # Pathogenesis stage visualizer
│   │   ├── KnapsackCalculatorComponent.kt   # 16L - 2000L tank calculation component
│   │   ├── OfflineTileProvider.kt           # Google Satellite tile cache provider
│   │   ├── SoilTextureTriangleCanvas.kt     # USDA equilateral ternary triangle canvas
│   │   └── UniversalSearchDialog.kt         # Global cross-catalog search dialog
│   ├── screens/
│   │   ├── AgronomyScreen.kt                # Crop guides, weed diagnostic & weather suite
│   │   ├── CalculatorScreen.kt              # Knapsack, pesticide dilution & soil classifier tabs
│   │   ├── DiagnoseScreen.kt                # Symptom key diagnostician
│   │   ├── FieldMapScreen.kt                # Satellite GIS & farm boundary polygon mapper
│   │   ├── FieldNotesScreen.kt              # Room-backed scouting notes & observations
│   │   ├── LibraryScreen.kt                 # Searchable A-Z pest & disease library
│   │   ├── LifecycleLabScreen.kt            # Pathology lifecycle laboratory
│   │   ├── PhotoDiseaseScannerScreen.kt     # Visual camera scanner interface
│   │   ├── ScoutLogScreen.kt                # Field scouting log entries
│   │   ├── ScoutingSheetScreen.kt           # Standardized scouting tables & CSV export
│   │   ├── ThreatDetailScreen.kt            # Deep-dive IPM threat view
│   │   ├── science/                         # Agricultural research journals & science quiz
│   │   └── soil/
│   │       └── SoilClassificationScreen.kt  # 3-tab Soil Triangle, Advisory & Rotation Designer
│   └── theme/                               # Material 3 ColorScheme, Typography & AppThemeMode
└── viewmodel/
    └── CropShieldViewModel.kt               # Centralized state management & UI state flows
```

---

## 📐 Soil Texture Triangle Mathematics

The USDA Soil Texture Triangle represents a 3-component ternary simplex where:
$$\text{Sand}\% + \text{Silt}\% + \text{Clay}\% = 100\%$$

### Coordinate Transformation
Given triangle vertices $V_{\text{sand}} = (x_{bl}, y_b)$, $V_{\text{silt}} = (x_{br}, y_b)$, and $V_{\text{clay}} = (x_{top}, y_t)$, any soil coordinate is projected onto the 2D Canvas via Barycentric linear interpolation:
$$X = \frac{\text{Sand}}{100} x_{bl} + \frac{\text{Silt}}{100} x_{br} + \frac{\text{Clay}}{100} x_{top}$$
$$Y = \frac{\text{Sand}}{100} y_b + \frac{\text{Silt}}{100} y_b + \frac{\text{Clay}}{100} y_t$$

### Barycentric Inversion on Touch
When a user taps or drags on the Canvas at $(x, y)$, normalized weights are inverted:
$$w_{\text{clay}} = \frac{y_b - y}{y_b - y_t}$$
$$w_{\text{silt}} = \frac{(x - x_{bl})(y_b - y_t) - (y_b - y)(x_{top} - x_{bl})}{(x_{br} - x_{bl})(y_b - y_t)}$$
$$w_{\text{sand}} = 1.0 - w_{\text{clay}} - w_{\text{silt}}$$

---

## 🚀 Getting Started

### Prerequisites
* **Android Studio**: Ladybug (2024.2.1) or newer
* **JDK**: Java 11 or Java 17
* **Android SDK**: `minSdk 24`, `compileSdk 36`
* **Gradle**: 8.5+

### Installation & Build

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/cropshield-ipm.git
   cd cropshield-ipm
   ```

2. **Open in Android Studio:**
   - Launch Android Studio and select **Open**.
   - Navigate to the cloned folder and let Gradle sync dependencies.

3. **Build the Debug APK via CLI:**
   ```bash
   gradle :app:assembleDebug
   ```

4. **Run Unit Tests:**
   ```bash
   gradle :app:testDebugUnitTest
   ```

5. **Install on Device:**
   Connect your Android device via USB debugging and run:
   ```bash
   gradle :app:installDebug
   ```

---

## 🧪 Testing

CropShield utilizes **Robolectric** for fast, deterministic local JVM testing of Critical User Journeys (CUJs) and agronomic calculation logic:
* Unit tests verify mathematical dosing formulas across 16L–2000L volumes.
* USDA soil classification algorithm boundary tests confirm 100% classification coverage without gaps.
* Room Database DAOs and entities are verified using in-memory SQLite instances.

To run all unit tests:
```bash
gradle test
```

---

## 👨‍🌾 Technical Lead & Agronomic Sign-Off

* **Agronomy & Plant Science Lead**: **Odongo Vincent** (*Crop Scientist & Agronomist*)
* **Focus Areas**: Integrated Pest Management (IPM), Sustainable Soil Fertility, Tropical Pathology, and Field Scout Capacity Building.

---

## 📄 License

```
Copyright 2026 CropShield IPM Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
