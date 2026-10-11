package com.semenova.vkandroidproject.domain

data class AppItem(
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