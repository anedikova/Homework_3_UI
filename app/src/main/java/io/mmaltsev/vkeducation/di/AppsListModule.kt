package io.mmaltsev.vkeducation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mmaltsev.vkeducation.data.appslist.AppsRepositoryImpl
import io.mmaltsev.vkeducation.domain.appslist.AppsRepository
import io.mmaltsev.vkeducation.presentation.appslist.AppsListUiMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppsListModule {

    @Binds
    @Singleton
    abstract fun bindAppsRepository(
        impl: AppsRepositoryImpl,
    ): AppsRepository

    companion object {
        @Provides
        @Singleton
        fun provideAppsListUiMapper(): AppsListUiMapper {
            return AppsListUiMapper()
        }
    }
}