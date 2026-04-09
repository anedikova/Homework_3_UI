package io.mmaltsev.vkeducation.data.appdetails.local

import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppDetailsEntityMapperTest {

    private val mapper = AppDetailsEntityMapper()

    @Test
    fun `entity with json screenshots EXPECT map screenshots to list`() {
        val entity = AppDetailsEntity(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshots = "[\"s1\",\"s2\"]",
            description = "Test description",
            isInWishlist = true,
        )

        val result = mapper.toDomain(entity)

        assertEquals(listOf("s1", "s2"), result.screenshotUrlList)
        assertTrue(result.isInWishlist)
    }

    @Test
    fun `entity with null screenshots EXPECT return empty list`() {
        val entity = AppDetailsEntity(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshots = null,
            description = "Test description",
            isInWishlist = false,
        )

        val result = mapper.toDomain(entity)

        assertEquals(emptyList<String>(), result.screenshotUrlList)
    }

    @Test
    fun `entity with string null screenshots EXPECT return empty list`() {
        val entity = AppDetailsEntity(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshots = "null",
            description = "Test description",
            isInWishlist = false,
        )

        val result = mapper.toDomain(entity)

        assertEquals(emptyList<String>(), result.screenshotUrlList)
    }

    @Test
    fun `domain EXPECT map to entity with serialized screenshots`() {
        val domain = AppDetails(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshotUrlList = listOf("s1", "s2"),
            description = "Test description",
            isInWishlist = true,
        )

        val result = mapper.toEntity(domain)

        assertEquals("app-id", result.id)
        assertEquals("Test App", result.name)
        assertEquals("Test Dev", result.developer)
        assertEquals(Category.GAME, result.category)
        assertEquals(12, result.ageRating)
        assertEquals(10.5f, result.size)
        assertEquals("https://icon", result.iconUrl)
        assertEquals("[\"s1\",\"s2\"]", result.screenshots)
        assertEquals("Test description", result.description)
        assertEquals(true, result.isInWishlist)
    }
}