package com.nyahonk.rijksmuseumgallery.repository

import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSource
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionResponse
import javax.inject.Inject

class ArtCollectionsRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): ArtCollectionResponse {
        return networkDataSource.getCollectionItems(pageNumber, resultsPerPage)
    }

    suspend fun getCollectionDetails(collection: String): ArtCollectionDetailsResponse {
        return networkDataSource.getCollectionDetails(collection)
    }
}