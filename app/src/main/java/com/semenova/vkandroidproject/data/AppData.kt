package com.semenova.vkandroidproject.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MusicNote
import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem

object AppData {
    val apps = listOf(
        AppItem(
            name = "Мессенджер",
            description = "Быстрые сообщения и звонки",
            category = AppCategory.COMM,
            icon = Icons.AutoMirrored.Filled.Message
        ),
        AppItem(
            name = "Музыка",
            description = "Плейлисты и подкасты офлайн",
            category = AppCategory.FUN,
            icon = Icons.Default.MusicNote
        ),
        AppItem(
            name = "Карты",
            description = "Навигация и маршруты",
            category = AppCategory.TRAVEL,
            icon = Icons.Default.Map
        ),
        AppItem(
            name = "Заметки",
            description = "Списки дел и напоминания",
            category = AppCategory.WORK,
            icon = Icons.AutoMirrored.Filled.Note
        ),
        AppItem(
            name = "Камера",
            description = "Фото, видео и фильтры",
            category = AppCategory.PHOTO,
            icon = Icons.Default.Camera
        )
    )
}