package com.semenova.vkandroidproject.presentation.screens.applist

import androidx.lifecycle.ViewModel
import com.semenova.vkandroidproject.data.AppData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    val apps = AppData.apps
}