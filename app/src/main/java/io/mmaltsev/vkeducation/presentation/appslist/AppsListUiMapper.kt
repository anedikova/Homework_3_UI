package io.mmaltsev.vkeducation.presentation.appslist

import androidx.compose.ui.graphics.Color
import io.mmaltsev.vkeducation.domain.appslist.App
import javax.inject.Inject

class AppsListUiMapper @Inject constructor() {

    fun toUi(app: App): StoreAppUi {
        return StoreAppUi(
            id = app.id,
            title = app.title,
            subtitle = app.subtitle,
            category = app.category,
            iconText = app.title.firstOrNull()?.uppercase() ?: "?",
            iconColor = colorByCategory(app.category),
            iconUrl = app.iconUrl,
        )
    }

    private fun colorByCategory(category: String): Color {
        return when (category.lowercase()) {
            "финансы" -> Color(0xFF42C05E)
            "инструменты" -> Color(0xFFFF7A45)
            "транспорт" -> Color(0xFF4A90E2)
            "игры" -> Color(0xFF9C27B0)
            else -> Color(0xFF607D8B)
        }
    }
}