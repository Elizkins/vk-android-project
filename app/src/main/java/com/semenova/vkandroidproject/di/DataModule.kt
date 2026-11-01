package com.semenova.vkandroidproject.di

import com.semenova.vkandroidproject.data.repostory.AppRepositoryImpl
import com.semenova.vkandroidproject.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    
    @Singleton
    @Binds
    fun bindAppRepository(
        impl: AppRepositoryImpl
    ): AppRepository
}