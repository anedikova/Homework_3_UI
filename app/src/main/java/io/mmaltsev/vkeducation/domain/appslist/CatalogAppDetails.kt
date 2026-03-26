package io.mmaltsev.vkeducation.domain.appslist

data class CatalogAppDetails(
    val id: String,
    val name: String,
    val developer: String,
    val category: String,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshots: List<String>,
    val description: String,
    val lastUpdated: Long,
)