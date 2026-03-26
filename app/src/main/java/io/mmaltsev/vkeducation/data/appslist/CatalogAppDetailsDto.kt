package io.mmaltsev.vkeducation.data.appslist

import kotlinx.serialization.Serializable

@Serializable
data class CatalogAppDetailsDto(
    val id: String,
    val name: String = "",
    val developer: String = "",
    val category: String = "",
    val ageRating: Int = 0,
    val size: Float = 0f,
    val iconUrl: String = "",
    val screenshots: List<String> = emptyList(),
    val description: String = "",
    val lastUpdated: Long = 0L,
)