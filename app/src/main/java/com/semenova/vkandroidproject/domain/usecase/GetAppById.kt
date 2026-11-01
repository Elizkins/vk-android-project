package com.semenova.vkandroidproject.domain.usecase

import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.AppRepository
import javax.inject.Inject

class GetAppById @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): AppItem {
        val app = repository.getAppItemById(id)
        if(app.category == AppCategory.FUN){
            throw IllegalStateException()
        }
        return app
    }
}