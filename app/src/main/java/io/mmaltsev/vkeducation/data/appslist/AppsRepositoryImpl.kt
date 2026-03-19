package io.mmaltsev.vkeducation.data.appslist

import io.mmaltsev.vkeducation.domain.appslist.App
import io.mmaltsev.vkeducation.domain.appslist.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val localDataSource: AppsLocalDataSource,
    private val mapper: AppsListMapper,
) : AppsRepository {

    override suspend fun getApps(): List<App> {
        return localDataSource.getApps().map { dto ->
            mapper.toDomain(dto)
        }
    }
}