package com.nyahonk.rijksmuseumgallery.datasource

import com.nyahonk.rijksmuseumgallery.datasource.api.RijksAPI
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val rijksAPI: RijksAPI
) : NetworkDataSource {

    override suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): ArtCollectionResponse {
        return rijksAPI.getCollection(
            pageNumber = pageNumber.toString(),
            resultsPerPage = resultsPerPage.toString()
        )
    }

    override suspend fun getCollectionDetails(collection: String): ArtCollectionDetailsResponse {
        return rijksAPI.getCollectionDetails(collection)
    }
}