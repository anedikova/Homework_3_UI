package io.mmaltsev.vkeducation.data.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsDao
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntity
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.Category
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppDetailsRepositoryImplTest {

    private val appApi = mockk<AppApi>()
    private val dao = mockk<AppDetailsDao>()
    private val mapper = mockk<AppDetailsMapper>()
    private val entityMapper = mockk<AppDetailsEntityMapper>()

    private lateinit var repository: AppDetailsRepositoryImpl

    @Before
    fun setUp() {
        repository = AppDetailsRepositoryImpl(
            appApi = appApi,
            dao = dao,
            mapper = mapper,
            entityMapper = entityMapper,
        )
    }

    @Test
    fun `cached entity EXPECT return domain from database`() = runTest {
        val entity = AppDetailsEntity(
            id = "app-id",
            name = "Cached App",
            developer = "Cached Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshots = "[\"s1\"]",
            description = "Cached description",
            isInWishlist = true,
        )
        val domain = AppDetails(
            id = "app-id",
            name = "Cached App",
            developer = "Cached Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshotUrlList = listOf("s1"),
            description = "Cached description",
            isInWishlist = true,
        )

        every { dao.observeAppDetails("app-id") } returns flowOf(entity)
        every { entityMapper.toDomain(entity) } returns domain

        val result = repository.getAppDetails("app-id")

        assertEquals(domain, result)
        coVerify(exactly = 0) { appApi.getAppDetails(any()) }
    }

    @Test
    fun `missing cache EXPECT request details from api`() = runTest {
        val dto = mockk<AppDetailsDto>()
        val domain = mockk<AppDetails>()
        val entity = mockk<AppDetailsEntity>()

        every { dao.observeAppDetails("app-id") } returns flowOf(null)
        coEvery { appApi.getAppDetails("app-id") } returns dto
        every { mapper.toDomain(dto, false) } returns domain
        every { entityMapper.toEntity(domain) } returns entity
        coEvery { dao.insertAppDetails(entity) } returns Unit

        repository.getAppDetails("app-id")

        coVerify(exactly = 1) { appApi.getAppDetails("app-id") }
    }

    @Test
    fun `missing cache EXPECT save mapped entity to database`() = runTest {
        val dto = mockk<AppDetailsDto>()
        val domain = mockk<AppDetails>()
        val entity = mockk<AppDetailsEntity>()

        every { dao.observeAppDetails("app-id") } returns flowOf(null)
        coEvery { appApi.getAppDetails("app-id") } returns dto
        every { mapper.toDomain(dto, false) } returns domain
        every { entityMapper.toEntity(domain) } returns entity
        coEvery { dao.insertAppDetails(entity) } returns Unit

        repository.getAppDetails("app-id")

        coVerify(exactly = 1) { dao.insertAppDetails(entity) }
    }

    @Test
    fun `missing cache EXPECT return mapped domain from api response`() = runTest {
        val dto = mockk<AppDetailsDto>()
        val domain = mockk<AppDetails>()
        val entity = mockk<AppDetailsEntity>()

        every { dao.observeAppDetails("app-id") } returns flowOf(null)
        coEvery { appApi.getAppDetails("app-id") } returns dto
        every { mapper.toDomain(dto, false) } returns domain
        every { entityMapper.toEntity(domain) } returns entity
        coEvery { dao.insertAppDetails(entity) } returns Unit

        val result = repository.getAppDetails("app-id")

        assertEquals(domain, result)
    }

    @Test
    fun `observe app details EXPECT map entity flow to domain flow`() = runTest {
        val entity = mockk<AppDetailsEntity>()
        val domain = mockk<AppDetails>()

        every { dao.observeAppDetails("app-id") } returns flowOf(entity)
        every { entityMapper.toDomain(entity) } returns domain

        val result = repository.observeAppDetails("app-id").first()

        assertEquals(domain, result)
    }

    @Test
    fun `toggle wishlist with false state EXPECT update status to true`() = runTest {
        val entity = AppDetailsEntity(
            id = "app-id",
            name = "App",
            developer = "Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshots = null,
            description = "Description",
            isInWishlist = false,
        )

        every { dao.observeAppDetails("app-id") } returns flowOf(entity)
        coEvery { dao.updateWishlistStatus("app-id", true) } returns Unit

        repository.toggleWishlist("app-id")

        coVerify(exactly = 1) { dao.updateWishlistStatus("app-id", true) }
    }

    @Test
    fun `toggle wishlist with true state EXPECT update status to false`() = runTest {
        val entity = AppDetailsEntity(
            id = "app-id",
            name = "App",
            developer = "Dev",
            category = Category.GAME,
            ageRating = 12,
            size = 10.5f,
            iconUrl = "https://icon",
            screenshots = null,
            description = "Description",
            isInWishlist = true,
        )

        every { dao.observeAppDetails("app-id") } returns flowOf(entity)
        coEvery { dao.updateWishlistStatus("app-id", false) } returns Unit

        repository.toggleWishlist("app-id")

        coVerify(exactly = 1) { dao.updateWishlistStatus("app-id", false) }
    }
}