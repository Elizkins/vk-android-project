package com.semenova.vkandroidproject.data.repository

import android.util.Log
import com.semenova.vkandroidproject.data.mapper.toAppItemEntity
import com.semenova.vkandroidproject.data.mapper.toAppItemsEntities
import com.semenova.vkandroidproject.data.remote.AppsApiService
import com.semenova.vkandroidproject.data.test.AppItemDto
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.AppRepository
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.coroutines.cancellation.CancellationException

class AppRepositoryImpl @Inject constructor(
    private val apiService: AppsApiService
) : AppRepository {
    
    override suspend fun getAllApps(): Flow<List<AppItem>> {
        return flowOf(loadApps().toAppItemsEntities())
    }
    
    private suspend fun loadApps(): List<AppItemDto> {
        return try {
            apiService.getApps()
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            Log.e("AppRepository", e.stackTraceToString())
            listOf<AppItemDto>()
        }
    }
    
    override suspend fun getAppItemById(appId: String): AppItem {
        return loadApp(appId).toAppItemEntity()
    }
    
    private suspend fun loadApp(id: String): AppItemDto {
        return try {
            apiService.getAppById(id)
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            Log.e("AppRepository", e.stackTraceToString())
            AppItemDto()
        }
    }
    
    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        return ids.associateWith { id ->
            apiService.getRating(id)
        }
    }
    
    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> =
        coroutineScope {
            ids.map {id ->
                async {
                    id to apiService.getRating(id)
                }
            }.awaitAll().toMap()
        }
}