package com.semenova.vkandroidproject.presentation.screens.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenova.vkandroidproject.data.repostory.AppRepositoryImpl
import com.semenova.vkandroidproject.domain.AppItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListScreenViewModel @Inject constructor() : ViewModel() {
    private val repository = AppRepositoryImpl()
    private val _events = Channel<AppListScreenEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()
    
    private val _state = MutableStateFlow<AppListScreenState>(AppListScreenState.Loading)
    val state = _state.asStateFlow()
    
    init {
        loadItems()
    }
    
    fun loadItems() {
        viewModelScope.launch {
            runCatching {
                _state.value = AppListScreenState.Loading
                repository.getAllApps()
                    .onEach { allApps ->
                        _state.value = AppListScreenState.Content(apps = allApps)
                    }.launchIn(this)
            }.onFailure {
                _state.value = AppListScreenState.Error
            }
        }
    }
    
    fun sendMessage() {
        viewModelScope.launch {
            _events.send(
                AppListScreenEvent.ShowSnackbar
            )
        }
    }
}

sealed interface AppListScreenState {
    data class Content(
        val apps: List<AppItem> = emptyList()
    ) : AppListScreenState
    
    data object Loading : AppListScreenState
    data object Error : AppListScreenState
}

sealed interface AppListScreenEvent {
    data object ShowSnackbar : AppListScreenEvent
}
