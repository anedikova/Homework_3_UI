package io.mmaltsev.vkeducation.presentation.appslist

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppsListViewModel : ViewModel() {

    private val _state = MutableStateFlow<AppsListUiState>(
        AppsListUiState.Loading,
    )
    val state = _state.asStateFlow()

    private val _events = Channel<AppsListEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        _state.value = AppsListUiState.Content(
            apps = listOf(
                StoreAppUi(
                    id = "sber",
                    title = "СберБанк Онлайн — с Салютом",
                    subtitle = "Больше чем банк",
                    category = "Финансы",
                    iconText = "С",
                    iconColor = Color(0xFF42C05E),
                ),
                StoreAppUi(
                    id = "browser",
                    title = "Яндекс.Браузер — с Алисой",
                    subtitle = "Быстрый и безопасный браузер",
                    category = "Инструменты",
                    iconText = "Я",
                    iconColor = Color(0xFFFF4B3A),
                ),
                StoreAppUi(
                    id = "mail",
                    title = "Почта Mail.ru",
                    subtitle = "Почтовый клиент для любых ящиков",
                    category = "Инструменты",
                    iconText = "@",
                    iconColor = Color(0xFF3366FF),
                ),
                StoreAppUi(
                    id = "navigator",
                    title = "Яндекс Навигатор",
                    subtitle = "Парковки и заправки — по пути",
                    category = "Транспорт",
                    iconText = "N",
                    iconColor = Color(0xFFFFD54F),
                ),
                StoreAppUi(
                    id = "mts",
                    title = "Мой МТС",
                    subtitle = "Мой МТС — центр экосистемы МТС",
                    category = "Инструменты",
                    iconText = "М",
                    iconColor = Color(0xFFFF4A4A),
                ),
                StoreAppUi(
                    id = "yandex",
                    title = "Яндекс — с Алисой",
                    subtitle = "Яндекс — поиск всегда под рукой",
                    category = "Инструменты",
                    iconText = "Я",
                    iconColor = Color(0xFFFF7A45),
                ),
            ),
        )
    }

    fun onLogoClick(appTitle: String) {
        viewModelScope.launch {
            _events.send(
                AppsListEvent.ShowSnackbar(
                    message = "Нажали на логотип: $appTitle",
                )
            )
        }
    }
}