package io.mmaltsev.vkeducation.data.appslist

import kotlinx.serialization.Serializable

@Serializable
data class CatalogAppDto(
    val id: String,
    val name: String = "",
    val developer: String = "",
    val category: String = "",
    val iconUrl: String = "",
)