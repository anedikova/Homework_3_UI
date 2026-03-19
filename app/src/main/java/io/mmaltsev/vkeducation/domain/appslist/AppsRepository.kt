package io.mmaltsev.vkeducation.domain.appslist

interface AppsRepository {
    suspend fun getApps(): List<App>
}