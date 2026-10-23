package com.example.model

enum class SearchCategoryFilter(val displayName: String) {
    ALL("All Results"),
    DISEASES("Diseases"),
    PESTS("Pests"),
    WEEDS("Weeds"),
    CROPS("Crops"),
    FERTILIZERS("Fertilizers")
}

data class UnifiedSearchItem(
    val id: String,
    val title: String,
    val scientificOrSecondary: String,
    val category: SearchCategoryFilter,
    val badgeText: String,
    val snippet: String,
    val tags: List<String>,
    val imageResId: Int? = null,
    val threatRef: CropThreat? = null,
    val weedRef: WeedModel? = null,
    val cropPlanRef: CropFertilizerPlan? = null,
    val fertilizerRef: FertilizerProduct? = null
)
