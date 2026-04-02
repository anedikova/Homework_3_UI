package io.mmaltsev.vkeducation.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mmaltsev.vkeducation.domain.appdetails.GetAppDetailsUseCase
import io.mmaltsev.vkeducation.domain.appdetails.ObserveAppDetailsUseCase
import io.mmaltsev.vkeducation.domain.appdetails.ToggleWishlistUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
) : ViewModel() {

    private val appId = "fa2e31b8-1234-4cf7-9914-108a170a1b01"

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        observeAppDetails()
        loadAppDetails()
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            toggleWishlistUseCase(appId)
        }
    }
    fun refresh() {
        loadAppDetails()
    }
    private fun loadAppDetails() {
        viewModelScope.launch {
            runCatching {
                getAppDetailsUseCase(appId)
            }.onFailure { e ->
                if (_state.value !is AppDetailsState.Content) {
                    _state.value = AppDetailsState.Error
                }
                Log.d("HOHOHO", "ERROR $e")
            }
        }
    }

    private fun observeAppDetails() {
        viewModelScope.launch {
            observeAppDetailsUseCase(appId)
                .catch { e ->
                    _state.value = AppDetailsState.Error
                    Log.d("HOHOHO", "ERROR $e")
                }
                .collect { appDetails ->
                    val currentCollapsed =
                        (_state.value as? AppDetailsState.Content)?.descriptionCollapsed ?: false

                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        descriptionCollapsed = currentCollapsed,
                        isInWishlist = appDetails.isInWishlist,
                    )
                }
        }
    }
}