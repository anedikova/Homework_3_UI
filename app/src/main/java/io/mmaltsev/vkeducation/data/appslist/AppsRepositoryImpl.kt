package io.mmaltsev.vkeducation.data.appslist

import io.mmaltsev.vkeducation.domain.appslist.App
import io.mmaltsev.vkeducation.domain.appslist.AppsRepository
import io.mmaltsev.vkeducation.domain.appslist.CatalogAppDetails
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AppsRemoteDataSource,
    private val mapper: AppsListMapper,
) : AppsRepository {

    override suspend fun getApps(): List<App> {
        return remoteDataSource.getCatalog().map { dto ->
            mapper.toDomain(dto)
        }
    }
    override suspend fun getAppDetails(id: String): CatalogAppDetails {
        return mapper.toDetailsDomain(
            remoteDataSource.getAppDetails(id)
        )
    }
}