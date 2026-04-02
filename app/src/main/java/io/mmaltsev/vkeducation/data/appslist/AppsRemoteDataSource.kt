package io.mmaltsev.vkeducation.data.appslist

import javax.inject.Inject

class AppsRemoteDataSource @Inject constructor(
    private val catalogApi: CatalogApi,
) {
    suspend fun getCatalog(): List<CatalogAppDto> {
        return catalogApi.getCatalog()
    }

    suspend fun getAppDetails(id: String): CatalogAppDetailsDto {
        return catalogApi.getCatalogAppById(id)
    }
}