package com.example.catcanvas.di

import com.example.catcanvas.domain.repository.CatListingRepository
import com.example.catcanvas.domain.repository.CatListingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCatListingRepository(
        catListingRepositoryImpl: CatListingRepositoryImpl
    ): CatListingRepository
}
