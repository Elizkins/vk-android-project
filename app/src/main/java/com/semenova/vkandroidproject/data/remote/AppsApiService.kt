package com.semenova.vkandroidproject.data.remote

import com.semenova.vkandroidproject.data.test.AppItemDto
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.time.Duration.Companion.milliseconds

interface AppsApiService {
    
    @GET("catalog")
    suspend fun getApps(): List<AppItemDto>
    
    @GET("catalog/{id}")
    suspend fun getAppById(@Path("id") id: String): AppItemDto
    
    suspend fun getRating(id: String): Float {
        delay(1500.milliseconds)
        return (30..50).random() / 10f
    }
}