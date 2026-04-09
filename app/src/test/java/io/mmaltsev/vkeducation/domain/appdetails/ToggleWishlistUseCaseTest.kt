package io.mmaltsev.vkeducation.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ToggleWishlistUseCaseTest {

    private val repository = mockk<AppDetailsRepository>()
    private val useCase = ToggleWishlistUseCase(repository)

    @Test
    fun `invoke with id EXPECT delegate toggle wishlist to repository`() = runTest {
        coEvery { repository.toggleWishlist("app-id") } returns Unit

        useCase("app-id")

        coVerify(exactly = 1) { repository.toggleWishlist("app-id") }
    }
}