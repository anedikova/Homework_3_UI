package io.mmaltsev.vkeducation.presentation.appslist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mmaltsev.vkeducation.domain.appslist.AppsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsListViewModel @Inject constructor(
    private val appsRepository: AppsRepository,
    private val uiMapper: AppsListUiMapper,
) : ViewModel() {

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
        viewModelScope.launch {
            _state.value = AppsListUiState.Loading

            runCatching {
                appsRepository.getApps().map { app ->
                    uiMapper.toUi(app)
                }
            }.onSuccess { apps ->
                _state.value = AppsListUiState.Content(apps)
            }.onFailure {
                _state.value = AppsListUiState.Content(emptyList())
                _events.send(
                    AppsListEvent.ShowSnackbar(
                        "Не удалось загрузить каталог"
                    )
                )
            }
        }
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