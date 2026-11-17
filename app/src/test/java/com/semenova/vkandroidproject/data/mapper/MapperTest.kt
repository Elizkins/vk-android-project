package com.semenova.vkandroidproject.data.mapper

import com.semenova.vkandroidproject.data.remote.AppCategoryDto
import com.semenova.vkandroidproject.data.test.AppItemDto
import com.semenova.vkandroidproject.domain.AppCategory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MapperTest {
    
    private fun dto() = AppItemDto(
        id = "42",
        name = "Test app",
        developer = "Test dev",
        category = AppCategoryDto.GAMES,
        ageRating = 12,
        size = 55.5f,
        iconUrl = "https://example.com/icon.png",
        screenshotUrlList = listOf("https://example.com/1.png", "https://example.com/2.png"),
        description = "Description"
    )
    
    @Test
    fun `toAppItemEntity maps all fields`() {
        val result = dto().toAppItemEntity()
        
        assertEquals("42", result.id)
        assertEquals("Test app", result.name)
        assertEquals("Test dev", result.developer)
        assertEquals(AppCategory.FUN, result.category)
        assertEquals(12, result.ageRating)
        assertEquals(55.5f, result.size)
        assertEquals("https://example.com/icon.png", result.iconUrl)
        assertEquals(
            listOf("https://example.com/1.png", "https://example.com/2.png"),
            result.screenshotUrlList
        )
        assertEquals("Description", result.description)
    }
    
    @Test
    fun `toAppItemsEntities maps every item and keeps order`() {
        val first = dto().copy(id = "1")
        val second = dto().copy(id = "2", category = AppCategoryDto.NEWS)
        
        val result = listOf(first, second).toAppItemsEntities()
        
        assertEquals(listOf("1", "2"), result.map { it.id })
        assertEquals(listOf(AppCategory.FUN, AppCategory.COMM), result.map { it.category })
    }
    
    @Test
    fun `toAppItemsEntities returns empty list for empty input`() {
        assertTrue(emptyList<AppItemDto>().toAppItemsEntities().isEmpty())
    }
    
    @Test
    fun `list mapper and single mapper give the same result`() {
        val item = dto()
        
        assertEquals(listOf(item.toAppItemEntity()), listOf(item).toAppItemsEntities())
    }
    
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
        "COMMUNICATION, COMM",
        "NEWS, COMM",
        "GAMES, FUN",
        "ENTERTAINMENT, FUN",
        "MUSIC, FUN",
        "BOOKS, FUN",
        "TRAVEL, TRAVEL",
        "NAVIGATION, TRAVEL",
        "WEATHER, TRAVEL",
        "PRODUCTIVITY, WORK",
        "BUSINESS, WORK",
        "FINANCE, WORK",
        "UTILITIES, WORK",
        "EDUCATION, WORK",
        "PHOTO_VIDEO, PHOTO",
        "HEALTH_FITNESS, LIFESTYLE",
        "FOOD_DRINK, LIFESTYLE",
        "LIFESTYLE, LIFESTYLE",
        "SHOPPING, LIFESTYLE",
        "UNKNOWN, UNKNOWN"
    )
    fun `maps category dto to domain`(dto: AppCategoryDto, expected: AppCategory) {
        assertEquals(expected, dto.toDomain())
    }
}