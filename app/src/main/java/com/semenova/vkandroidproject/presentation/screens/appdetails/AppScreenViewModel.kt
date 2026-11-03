package com.semenova.vkandroidproject.presentation.screens.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.AppRepository
import com.semenova.vkandroidproject.domain.usecase.GetAllApps
import com.semenova.vkandroidproject.domain.usecase.GetAppById
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AppScreenViewModel.Factory::class)
class AppScreenViewModel @AssistedInject constructor(
    private val getAllApps: GetAllApps,
    private val getAppById: GetAppById,
    @Assisted("appId") private val appId: String
) : ViewModel() {
    
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("appId") appId: String
        ): AppScreenViewModel
    }
    
    private val _state = MutableStateFlow<AppScreenState>(AppScreenState.Loading)
    val state = _state.asStateFlow()
    
    init {
        viewModelScope.launch {
            getAllApps()
                .onStart { _state.value = AppScreenState.Loading }
                .onEach { allApps ->
                    _state.value = AppScreenState.Content(
                        app = getAppById(appId),
                        apps = allApps
                    )
                }
                .catch { _state.value = AppScreenState.Error }
                .collect()
        }
    }
}

sealed interface AppScreenState {
    data class Content(
        val apps: List<AppItem> = emptyList(),
        val app: AppItem
    ) : AppScreenState
    
    data object Loading : AppScreenState
    data object Error : AppScreenState
}