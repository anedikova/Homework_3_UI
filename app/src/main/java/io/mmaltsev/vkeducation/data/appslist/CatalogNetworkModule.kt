package io.mmaltsev.vkeducation.data.appslist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatalogNetworkModule {

    @Provides
    @Singleton
    fun provideCatalogApi(): CatalogApi {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("http://185.103.109.134/")
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(CatalogApi::class.java)
    }
}