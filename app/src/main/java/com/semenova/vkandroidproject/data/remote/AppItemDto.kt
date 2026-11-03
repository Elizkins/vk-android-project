package com.semenova.vkandroidproject.data.test

import com.semenova.vkandroidproject.data.remote.AppCategoryDto
import com.semenova.vkandroidproject.domain.AppCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppItemDto(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("developer")
    val developer: String = "",
    @SerialName("category")
    val category: AppCategoryDto = AppCategoryDto.UNKNOWN,
    @SerialName("ageRating")
    val ageRating: Int = 0,
    @SerialName("size")
    val size: Float = 0f,
    @SerialName("iconUrl")
    val iconUrl: String = "",
    @SerialName("screenshotUrlList")
    val screenshotUrlList: List<String> = emptyList(),
    @SerialName("description")
    val description: String = ""
)