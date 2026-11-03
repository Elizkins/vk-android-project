package com.semenova.vkandroidproject.data.remote

import com.semenova.vkandroidproject.data.test.AppItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppsApiService {
    
    @GET("catalog")
    suspend fun getApps(): List<AppItemDto>
    
    @GET("catalog/{id}")
    suspend fun getAppById(@Path("id") id: String): AppItemDto
}