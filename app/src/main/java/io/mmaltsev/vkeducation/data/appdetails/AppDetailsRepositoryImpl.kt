package io.mmaltsev.vkeducation.data.appdetails

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsDao
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import io.mmaltsev.vkeducation.domain.appdetails.AppDetails
import io.mmaltsev.vkeducation.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): AppDetails {
        val cachedEntity = dao.observeAppDetails(id).first()

        if (cachedEntity != null) {
            return entityMapper.toDomain(cachedEntity)
        }

        val dto = appApi.getAppDetails(id)

        val domain = mapper.toDomain(
            dto = dto,
            isInWishlist = cachedEntity?.isInWishlist ?: false,
        )

        val entity = entityMapper.toEntity(domain)

        withContext(Dispatchers.IO) {
            dao.insertAppDetails(entity)
        }

        return domain
    }

    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.observeAppDetails(id)
            .filterNotNull()
            .map { entity ->
                entityMapper.toDomain(entity)
            }
    }

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = dao.observeAppDetails(id).first()

        currentEntity?.let {
            dao.updateWishlistStatus(
                id = id,
                isInWishlist = !it.isInWishlist,
            )
        }
    }
}