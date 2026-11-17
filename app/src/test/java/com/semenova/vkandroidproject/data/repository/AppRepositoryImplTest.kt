package com.semenova.vkandroidproject.data.repository

import com.semenova.vkandroidproject.data.mapper.toAppItemEntity
import com.semenova.vkandroidproject.data.mapper.toAppItemsEntities
import com.semenova.vkandroidproject.data.remote.AppsApiService
import com.semenova.vkandroidproject.data.test.AppItemDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.io.IOException

@ExtendWith(MockitoExtension::class)
class AppRepositoryImplTest {
    
    @Mock
    lateinit var apiService: AppsApiService
    
    private lateinit var repository: AppRepositoryImpl
    
    @BeforeEach
    fun setUp() {
        repository = AppRepositoryImpl(apiService)
    }
    
    @Test
    fun `getAllApps maps dto to domain`() = runTest {
        val dtos = listOf(AppItemDto(), AppItemDto())
        whenever(apiService.getApps()).thenReturn(dtos)
        
        val result = repository.getAllApps().first()
        
        assertEquals(dtos.toAppItemsEntities(), result)
    }
    
    @Test
    fun `getAllApps returns empty list when api fails`() = runTest {
        whenever(apiService.getApps()).thenAnswer { throw IOException("NO NETWORK") }
        
        val result = repository.getAllApps().first()
        
        assertTrue(result.isEmpty())
    }
    
    @Test
    fun `getAppItemById maps dto to domain`() = runTest {
        val dto = AppItemDto()
        whenever(apiService.getAppById("1")).thenReturn(dto)
        
        val result = repository.getAppItemById("1")
        
        assertEquals(dto.toAppItemEntity(), result)
    }
    
    @Test
    fun `getAppItemById returns default item when api fails`() = runTest {
        whenever(apiService.getAppById("1")).thenAnswer { throw IOException("NO NETWORK") }
        
        val result = repository.getAppItemById("1")
        
        assertEquals(AppItemDto().toAppItemEntity(), result)
    }
    
    @Test
    fun `loadRatingsSequentially returns rating per id`() = runTest {
        whenever(apiService.getRating("1")).thenReturn(4.5f)
        whenever(apiService.getRating("2")).thenReturn(3.0f)
        
        val result = repository.loadRatingsSequentially(listOf("1", "2"))
        
        assertEquals(mapOf("1" to 4.5f, "2" to 3.0f), result)
    }
    
    @Test
    fun `loadRatingsInParallel returns rating per id`() = runTest {
        whenever(apiService.getRating("1")).thenReturn(4.5f)
        whenever(apiService.getRating("2")).thenReturn(3.0f)
        
        val result = repository.loadRatingsInParallel(listOf("1", "2"))
        
        assertEquals(mapOf("1" to 4.5f, "2" to 3.0f), result)
    }
}