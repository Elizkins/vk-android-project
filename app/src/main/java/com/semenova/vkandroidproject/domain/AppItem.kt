package com.semenova.vkandroidproject.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class AppItem(
    val name: String,
    val description: String,
    val category: AppCategory,
    val icon: String
)