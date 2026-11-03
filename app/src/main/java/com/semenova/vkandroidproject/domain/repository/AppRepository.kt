package com.semenova.vkandroidproject.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getAllApps(): Flow<List<AppItem>>
    suspend fun getAppItemById(appId: String): AppItem
}