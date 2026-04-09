package io.mmaltsev.vkeducation.domain.appdetails

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mmaltsev.vkeducation.domain.appdetails.Category
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ObserveAppDetailsUseCaseTest {

    private val repository = mockk<AppDetailsRepository>()
    private val useCase = ObserveAppDetailsUseCase(repository)

    @Test
    fun `repository returns flow EXPECT use case return same first app details`() = runTest {
        val expected = AppDetails(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshotUrlList = listOf("s1"),
            description = "Test description",
            isInWishlist = true,
        )
        every { repository.observeAppDetails("app-id") } returns flowOf(expected)

        val result = useCase("app-id").first()

        assertEquals(expected, result)
    }

    @Test
    fun `invoke with id EXPECT request observation for same id from repository`() {
        val expected = AppDetails(
            id = "app-id",
            name = "Test App",
            developer = "Test Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshotUrlList = listOf("s1"),
            description = "Test description",
            isInWishlist = true,
        )
        every { repository.observeAppDetails("app-id") } returns flowOf(expected)

        useCase("app-id")

        verify(exactly = 1) { repository.observeAppDetails("app-id") }
    }
}