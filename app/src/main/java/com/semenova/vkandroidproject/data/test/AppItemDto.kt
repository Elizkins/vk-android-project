package com.semenova.vkandroidproject.data.test

import com.semenova.vkandroidproject.domain.AppCategory

data class AppItemDto(
    val id: Int,
    val name: String,
    val developer: String,
    val category: AppCategory,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>,
    val description: String
)