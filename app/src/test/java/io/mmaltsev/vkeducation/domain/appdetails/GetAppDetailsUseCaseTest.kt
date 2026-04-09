package io.mmaltsev.vkeducation.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mmaltsev.vkeducation.domain.appdetails.Category
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAppDetailsUseCaseTest {

    private val repository = mockk<AppDetailsRepository>()
    private val useCase = GetAppDetailsUseCase(repository)

    @Test
    fun `repository returns app details EXPECT use case return same app details`() = runTest {
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
            isInWishlist = false,
        )
        coEvery { repository.getAppDetails("app-id") } returns expected

        val result = useCase("app-id")

        assertEquals(expected, result)
    }

    @Test
    fun `invoke with id EXPECT request details for same id from repository`() = runTest {
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
            isInWishlist = false,
        )
        coEvery { repository.getAppDetails("app-id") } returns expected

        useCase("app-id")

        coVerify(exactly = 1) { repository.getAppDetails("app-id") }
    }
}