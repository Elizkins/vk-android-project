package com.semenova.vkandroidproject.data.mapper

import com.semenova.vkandroidproject.data.test.AppItemDto
import com.semenova.vkandroidproject.domain.AppItem

fun List<AppItemDto>.toAppItemsEntities(): List<AppItem> {
    return map { appItemDto ->
        AppItem(
            id = appItemDto.id,
            name = appItemDto.name,
            developer = appItemDto.developer,
            category = appItemDto.category,
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
        category = this.category,
        ageRating = this.ageRating,
        size = this.size,
        iconUrl = this.iconUrl,
        screenshotUrlList = this.screenshotUrlList,
        description = this.description,
    )
}