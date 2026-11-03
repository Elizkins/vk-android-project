package com.semenova.vkandroidproject.data.mapper

import com.semenova.vkandroidproject.data.remote.AppCategoryDto
import com.semenova.vkandroidproject.data.test.AppItemDto
import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem

fun AppCategoryDto.toDomain(): AppCategory = when (this) {
    AppCategoryDto.COMMUNICATION,
    AppCategoryDto.NEWS -> AppCategory.COMM
    
    AppCategoryDto.GAMES,
    AppCategoryDto.ENTERTAINMENT,
    AppCategoryDto.MUSIC,
    AppCategoryDto.BOOKS -> AppCategory.FUN
    
    AppCategoryDto.TRAVEL,
    AppCategoryDto.NAVIGATION,
    AppCategoryDto.WEATHER -> AppCategory.TRAVEL
    
    AppCategoryDto.PRODUCTIVITY,
    AppCategoryDto.BUSINESS,
    AppCategoryDto.FINANCE,
    AppCategoryDto.UTILITIES,
    AppCategoryDto.EDUCATION -> AppCategory.WORK
    
    AppCategoryDto.PHOTO_VIDEO -> AppCategory.PHOTO
    
    AppCategoryDto.HEALTH_FITNESS,
    AppCategoryDto.FOOD_DRINK,
    AppCategoryDto.LIFESTYLE,
    AppCategoryDto.SHOPPING -> AppCategory.LIFESTYLE
    
    AppCategoryDto.UNKNOWN -> AppCategory.UNKNOWN
}

fun List<AppItemDto>.toAppItemsEntities(): List<AppItem> {
    return map { appItemDto ->
        AppItem(
            id = appItemDto.id,
            name = appItemDto.name,
            developer = appItemDto.developer,
            category = appItemDto.category.toDomain(),
            ageRating = appItemDto.ageRating,
            size = appItemDto.size,
            iconUrl = appItemDto.iconUrl,
            screenshotUrlList = appItemDto.screenshotUrlList,
            description = appItemDto.description,
        )
    }
}

fun AppItemDto.toAppItemEntity(): AppItem {
    return AppItem(
        id = this.id,
        name = this.name,
        developer = this.developer,
        category = this.category.toDomain(),
        ageRating = this.ageRating,
        size = this.size,
        iconUrl = this.iconUrl,
        screenshotUrlList = this.screenshotUrlList,
        description = this.description,
    )
}