package io.mmaltsev.vkeducation.presentation.appslist

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class StoreAppUi(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val iconText: String,
    val iconColor: Color,
)

sealed interface AppsListUiState {
    data object Loading : AppsListUiState

    data class Content(
        val apps: List<StoreAppUi>,
    ) : AppsListUiState
}