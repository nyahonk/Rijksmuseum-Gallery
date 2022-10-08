package com.nyahonk.rijksmuseumgallery.repository

import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionResponse

interface ArtCollectionsRepository {

    suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): ArtCollectionResponse

    suspend fun getCollectionDetails(collection: String): ArtCollectionDetailsResponse
}