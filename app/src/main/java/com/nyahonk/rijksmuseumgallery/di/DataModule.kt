package com.nyahonk.rijksmuseumgallery.di

import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSource
import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSourceImpl
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepository
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepositoryImpl
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindArtCollectionsUseCase(
        artCollectionsUseCaseImpl: ArtCollectionsUseCaseImpl
    ): ArtCollectionsUseCase

    @Binds
    abstract fun bindArtCollectionsRepository(
        artCollectionsRepositoryImpl: ArtCollectionsRepositoryImpl
    ): ArtCollectionsRepository

    @Binds
    abstract fun bindNetworkDataSource(
        networkDataSourceImpl: NetworkDataSourceImpl
    ): NetworkDataSource
}