package com.semenova.vkandroidproject.data

import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf

class AppRepositoryImpl : AppRepository {
    override fun getAllApps(): Flow<List<AppItem>> {
        return flowOf(AppData.apps)
    }
}