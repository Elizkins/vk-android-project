package com.semenova.vkandroidproject.presentation.screens.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenova.vkandroidproject.data.repostory.AppRepositoryImpl
import com.semenova.vkandroidproject.domain.AppItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(assistedFactory = AppScreenViewModel.Factory::class)
class AppScreenViewModel @AssistedInject constructor(
    @Assisted("appId") private val appId: Int
) : ViewModel() {
    
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("appId") appId: Int
        ): AppScreenViewModel
    }
    
    private val repository = AppRepositoryImpl()
    private val _state = MutableStateFlow<AppScreenState>(AppScreenState.Loading)
    val state = _state.asStateFlow()
    
    init {
        viewModelScope.launch {
            runCatching {
                _state.value = AppScreenState.Loading
                repository.getAllApps()
                    .onEach { allApps ->
                        _state.value = AppScreenState.Content(
                            app = repository.getAppItemById(appId),
                            apps = allApps
                        )
                    }.launchIn(this)
            }.onFailure {
                _state.value = AppScreenState.Error
            }
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