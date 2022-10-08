package com.nyahonk.rijksmuseumgallery.datasource

import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionResponse

interface NetworkDataSource {

    suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): ArtCollectionResponse

    suspend fun getCollectionDetails(collection: String): ArtCollectionDetailsResponse
}