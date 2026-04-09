package io.mmaltsev.vkeducation.data.appdetails

import io.mmaltsev.vkeducation.domain.appdetails.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppDetailsMapperTest {

    private val mapper = AppDetailsMapper()

    @Test
    fun `dto with wishlist false EXPECT map all fields to domain`() {
        val dto = AppDetailsDto(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5,
            icon = "https://icon",
            screenshots = listOf("s1", "s2"),
            description = "Test description",
        )

        val result = mapper.toDomain(
            dto = dto,
            isInWishlist = false,
        )

        assertEquals("app-id", result.id)
        assertEquals("Test App", result.name)
        assertEquals("Test Dev", result.developer)
        assertEquals(Category.GAME, result.category)
        assertEquals(12, result.ageRating)
        assertEquals(10.5f, result.size)
        assertEquals("https://icon", result.iconUrl)
        assertEquals(listOf("s1", "s2"), result.screenshotUrlList)
        assertEquals("Test description", result.description)
        assertEquals(false, result.isInWishlist)
    }

    @Test
    fun `dto with wishlist true EXPECT map wishlist flag to domain`() {
        val dto = AppDetailsDto(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5,
            icon = "https://icon",
            screenshots = listOf("s1"),
            description = "Test description",
        )

        val result = mapper.toDomain(
            dto = dto,
            isInWishlist = true,
        )

        assertTrue(result.isInWishlist)
    }
}