package com.semenova.vkandroidproject.domain.usecase

import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.domain.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class UseCasesTests {
    
    @Mock
    lateinit var repository: AppRepository
    
    private lateinit var getAllApps: GetAllApps
    private lateinit var getAppById: GetAppById
    private lateinit var loadRatingSequentially: LoadRatingSequentially
    private lateinit var loadRatingInParallel: LoadRatingInParallel
    
    @BeforeEach
    fun setUp() {
        getAllApps = GetAllApps(repository)
        getAppById = GetAppById(repository)
        loadRatingSequentially = LoadRatingSequentially(repository)
        loadRatingInParallel = LoadRatingInParallel(repository)
    }
    
    @Test
    fun `returns all apps from repository`() = runTest {
        val apps = listOf(
            appItem(id = "1"),
            appItem(id = "2")
        )
        whenever(repository.getAllApps()).thenReturn(flowOf(apps))
        
        val result = getAllApps().first()
        
        assertEquals(apps, result)
        verify(repository, times(1)).getAllApps()
        verifyNoMoreInteractions(repository)
    }
    
    @Test
    fun `returns empty list when repository has no apps`() = runTest {
        whenever(repository.getAllApps()).thenReturn(flowOf(emptyList()))
        
        val result = getAllApps().first()
        
        assertTrue(result.isEmpty())
    }
    
    
    @Test
    fun `returns app by id from repository`() = runTest {
        val app = appItem(id = "1", category = AppCategory.UNKNOWN)
        whenever(repository.getAppItemById("1")).thenReturn(app)
        
        val result = getAppById("1")
        
        assertEquals(app, result)
        verify(repository).getAppItemById("1")
        verifyNoMoreInteractions(repository)
    }
    
    @Test
    fun `throws IllegalStateException when category is FUN`() = runTest {
        val app = appItem(id = "2", category = AppCategory.FUN)
        whenever(repository.getAppItemById("2")).thenReturn(app)
        
        assertThrows<IllegalStateException> { getAppById("2") }
    }
    
    @Test
    fun `returns ratings in parallel from repository`() = runTest {
        val ids = listOf("1", "2")
        val ratings = mapOf("1" to 4.5f, "2" to 3.0f)
        whenever(repository.loadRatingsInParallel(ids)).thenReturn(ratings)
        
        val result = loadRatingInParallel(ids)
        
        assertEquals(ratings, result)
        verify(repository).loadRatingsInParallel(ids)
        verifyNoMoreInteractions(repository)
    }
    
    @Test
    fun `passes empty ids to repository for parallel load`() = runTest {
        whenever(repository.loadRatingsInParallel(emptyList())).thenReturn(emptyMap())
        
        val result = loadRatingInParallel(emptyList())
        
        assertTrue(result.isEmpty())
    }
    
    @Test
    fun `returns ratings sequentially from repository`() = runTest {
        val ids = listOf("1", "2")
        val ratings = mapOf("1" to 4.5f, "2" to 3.0f)
        whenever(repository.loadRatingsSequentially(ids)).thenReturn(ratings)
        
        val result = loadRatingSequentially(ids)
        
        assertEquals(ratings, result)
        verify(repository).loadRatingsSequentially(ids)
        verifyNoMoreInteractions(repository)
    }
    
    @Test
    fun `passes empty ids to repository for sequentially load`() = runTest {
        whenever(repository.loadRatingsSequentially(emptyList())).thenReturn(emptyMap())
        
        val result = loadRatingSequentially(emptyList())
        
        assertTrue(result.isEmpty())
    }
    
    
    private fun appItem(
        id: String = "1",
        name: String = "Test app",
        developer: String = "Test developer",
        category: AppCategory = AppCategory.entries.first(),
        ageRating: Int = 0,
        size: Float = 10f,
        iconUrl: String = "https://example.com/icon.png",
        screenshotUrlList: List<String> = emptyList(),
        description: String = "Test description"
    ) = AppItem(
        id = id,
        name = name,
        developer = developer,
        category = category,
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshotUrlList = screenshotUrlList,
        description = description
    )
}