package com.semenova.vkandroidproject.presentation.screens.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.AppRepository
import com.semenova.vkandroidproject.domain.usecase.GetAllApps
import com.semenova.vkandroidproject.domain.usecase.GetAppById
import com.semenova.vkandroidproject.presentation.screens.applist.AppListScreenEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel(assistedFactory = AppScreenViewModel.Factory::class)
class AppScreenViewModel @AssistedInject constructor(
    private val getAllApps: GetAllApps,
    private val getAppById: GetAppById,
    @Assisted("appId") private val appId: String
) : ViewModel() {
    
    private val _events = Channel<AppDetailsScreenEvent>(Channel.BUFFERED)
    
    private var installJob: Job? = null
    val events = _events.receiveAsFlow()
    
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
                        apps = allApps,
                        installState = InstallState.NotInstalled
                    )
                }
                .catch { _state.value = AppScreenState.Error }
                .collect()
        }
    }
    
    fun sendMessage() {
        viewModelScope.launch {
            _events.send(
                AppDetailsScreenEvent.ShowToastInDevelop
            )
        }
    }
    
    fun onInstallClick() {
        val current = _state.value
        if (current !is AppScreenState.Content) return
        
        when(current.installState) {
            InstallState.Installed -> {
            
            }
            
            is InstallState.Installing -> {
                val job = installJob
                installJob = null
                viewModelScope.launch {
                    job?.cancelAndJoin()
                    updateInstallState(InstallState.NotInstalled)
                }
            }
            
            InstallState.NotInstalled -> {
                installJob?.cancel()
                installJob = viewModelScope.launch {
                    emulateInstall()
                }
            }
        }
    }
    
    private suspend fun emulateInstall() {
        for (progress in (0..100) step 5) {
            delay(150.milliseconds)
            updateInstallState(InstallState.Installing(progress))
        }
        updateInstallState(InstallState.Installed)
    }
    
    private fun updateInstallState(installState: InstallState) {
        _state.update { previousState ->
            if (previousState is AppScreenState.Content) {
                previousState.copy(installState = installState)
            } else {
                previousState
            }
        }
    }
}

sealed interface AppScreenState {
    data class Content(
        val apps: List<AppItem> = emptyList(),
        val app: AppItem,
        val installState: InstallState
    ) : AppScreenState
    
    data object Loading : AppScreenState
    data object Error : AppScreenState
}

sealed interface AppDetailsScreenEvent {
    data object ShowToastInDevelop : AppDetailsScreenEvent
}

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState
    data object Installed : InstallState
}