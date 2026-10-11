package com.semenova.vkandroidproject.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MusicNote
import androidx.lifecycle.ViewModel
import com.semenova.vkandroidproject.data.AppData
import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    val apps = AppData.apps
}