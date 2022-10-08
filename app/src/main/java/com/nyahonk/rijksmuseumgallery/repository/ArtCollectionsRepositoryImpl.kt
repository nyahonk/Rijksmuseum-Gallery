package com.nyahonk.rijksmuseumgallery.repository

import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSource
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionResponse
import javax.inject.Inject

class ArtCollectionsRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : ArtCollectionsRepository {

    override suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): ArtCollectionResponse {
        return networkDataSource.getCollectionItems(pageNumber, resultsPerPage)
    }

    override suspend fun getCollectionDetails(collection: String): ArtCollectionDetailsResponse {
        return networkDataSource.getCollectionDetails(collection)
    }
}