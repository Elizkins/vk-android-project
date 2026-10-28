package com.semenova.vkandroidproject.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getAllApps(): Flow<List<AppItem>>
    fun getAppItemById(appId: Int): AppItem
}