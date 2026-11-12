package com.semenova.vkandroidproject.di

import com.semenova.vkandroidproject.data.remote.AppsApiService
import com.semenova.vkandroidproject.data.repository.AppRepositoryImpl
import com.semenova.vkandroidproject.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    
    @Singleton
    @Binds
    fun bindAppRepository(
        impl: AppRepositoryImpl
    ): AppRepository
    
    companion object {
        
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
        }
        
        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }
        
        @Provides
        @Singleton
        fun provideConverterFactory(
            json: Json
        ): Converter.Factory {
            return json.asConverterFactory("application/json".toMediaType())
        }
        
        @Provides
        @Singleton
        fun provideRetrofit(
            converterFactory: Converter.Factory,
            client: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://185.103.109.134/")
                .client(client)
                .addConverterFactory(converterFactory)
                .build()
        }
        
        @Provides
        @Singleton
        fun provideAppsApiService(
            retrofit: Retrofit
        ): AppsApiService {
            return retrofit.create()
        }
    }
}