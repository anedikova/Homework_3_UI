package io.mmaltsev.vkeducation.data.appslist

import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {

    @GET("catalog")
    suspend fun getCatalog(): List<CatalogAppDto>

    @GET("catalog/{id}")
    suspend fun getCatalogAppById(
        @Path("id") id: String,
    ): CatalogAppDetailsDto
}