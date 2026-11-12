package com.semenova.vkandroidproject.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getAllApps(): Flow<List<AppItem>>
    suspend fun getAppItemById(appId: String): AppItem
    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}