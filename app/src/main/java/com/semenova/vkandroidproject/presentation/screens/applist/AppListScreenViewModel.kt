package com.semenova.vkandroidproject.presentation.screens.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.usecase.GetAllApps
import com.semenova.vkandroidproject.domain.usecase.LoadRatingInParallel
import com.semenova.vkandroidproject.domain.usecase.LoadRatingSequentially
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppListScreenViewModel @Inject constructor(
    private val getAllApps: GetAllApps,
    private val loadRatingSequentially: LoadRatingSequentially,
    private val loadRatingInParallel: LoadRatingInParallel
) : ViewModel() {
    private val _events = Channel<AppListScreenEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()
    
    private val _state = MutableStateFlow<AppListScreenState>(AppListScreenState.Loading)
    val state = _state.asStateFlow()
    
    init {
        loadItems()
        loadRatings()
    }
    
    fun loadItems() {
        viewModelScope.launch {
            getAllApps()
                .onStart {
                    _state.value = AppListScreenState.Loading
                }.onEach { allApps ->
                    _state.value = AppListScreenState.Content(apps = allApps)
                }.catch {
                    _state.value = AppListScreenState.Error
                }.collect()
        }
        
    }
    
    fun sendMessage() {
        viewModelScope.launch {
            _events.send(
                AppListScreenEvent.ShowSnackbar
            )
        }
    }
    
    private fun loadRatings(){
        viewModelScope.launch {
            val content = _state.first { it is AppListScreenState.Content }
                    as AppListScreenState.Content
            val ids = content.apps.map { it.id }
            
            var parallelRatings: Map<String, Float> = emptyMap()
            val loadInParallelTimeMeasure = measureTimeMillis {
                parallelRatings = loadRatingInParallel(ids.take(15))
            }
            updateRatings(parallelRatings)
            Log.d("Ratings", "Параллельно: $loadInParallelTimeMeasure мс")
            
            var sequentialRatings: Map<String, Float> = emptyMap()
            val loadSequentiallyTimeMeasure = measureTimeMillis {
                sequentialRatings = loadRatingSequentially(ids.take(15))
            }
            updateRatings(sequentialRatings)
            Log.d("Ratings", "Последовательно: $loadSequentiallyTimeMeasure мс")
        }
    }
    
    private fun updateRatings(ratings: Map<String, Float>) {
        _state.update { previousState ->
            if (previousState is AppListScreenState.Content) {
                previousState.copy(ratings = ratings)
            } else {
                previousState
            }
        }
    }
}

sealed interface AppListScreenState {
    data class Content(
        val apps: List<AppItem> = emptyList(),
        val ratings: Map<String, Float> = emptyMap()
    ) : AppListScreenState
    
    data object Loading : AppListScreenState
    data object Error : AppListScreenState
}

sealed interface AppListScreenEvent {
    data object ShowSnackbar : AppListScreenEvent
}
