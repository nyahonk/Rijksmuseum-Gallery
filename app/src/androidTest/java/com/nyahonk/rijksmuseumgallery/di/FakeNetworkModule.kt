package com.nyahonk.rijksmuseumgallery.di

import com.nyahonk.rijksmuseumgallery.data.MockRijksAPI
import com.nyahonk.rijksmuseumgallery.datasource.api.RijksAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class FakeNetworkModule {

    @Provides
    @Singleton
    fun provideMockRijksAPI(): RijksAPI = MockRijksAPI()

}