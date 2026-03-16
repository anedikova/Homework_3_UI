package io.mmaltsev.vkeducation.presentation.appslist

sealed interface AppsListEvent {
    data class ShowSnackbar(
        val message: String,
    ) : AppsListEvent
}