package io.mmaltsev.vkeducation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mmaltsev.vkeducation.data.appslist.AppsRepositoryImpl
import io.mmaltsev.vkeducation.domain.appslist.AppsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppsListModule {

    @Binds
    @Singleton
    abstract fun bindAppsRepository(
        impl: AppsRepositoryImpl,
    ): AppsRepository
}