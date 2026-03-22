package io.mmaltsev.vkeducation.presentation.appslist

import androidx.compose.ui.graphics.Color
import io.mmaltsev.vkeducation.domain.appslist.App
import javax.inject.Inject

class AppsListUiMapper {

    fun toUi(app: App): StoreAppUi {
        return StoreAppUi(
            id = app.id,
            title = app.title,
            subtitle = app.subtitle,
            category = app.category,
            iconText = iconTextByKey(app.iconKey),
            iconColor = iconColorByKey(app.iconKey),
        )
    }

    private fun iconTextByKey(iconKey: String): String {
        return when (iconKey) {
            "sber" -> "С"
            "browser" -> "Я"
            "mail" -> "@"
            "navigator" -> "N"
            "mts" -> "М"
            "yandex" -> "Я"
            else -> "?"
        }
    }

    private fun iconColorByKey(iconKey: String): Color {
        return when (iconKey) {
            "sber" -> Color(0xFF42C05E)
            "browser" -> Color(0xFFFF4B3A)
            "mail" -> Color(0xFF3366FF)
            "navigator" -> Color(0xFFFFD54F)
            "mts" -> Color(0xFFFF4A4A)
            "yandex" -> Color(0xFFFF7A45)
            else -> Color.Gray
        }
    }
}