package com.nyahonk.rijksmuseumgallery.repository

import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSource
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import javax.inject.Inject

class ArtCollectionsRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): List<ArtCollectionListItem> {
        return networkDataSource.getCollectionItems(pageNumber, resultsPerPage).artObjects.map {
            ArtCollectionListItem(
                id = it.objectNumber,
                title = it.title,
                author = it.principalOrFirstMaker,
                imageHeaderUrl = it.headerImage.url,
                imageFullUrl = it.webImage.url
            )
        }
    }

    suspend fun getCollectionDetails(collection: String): ArtCollectionDetailsResponse {
        return networkDataSource.getCollectionDetails(collection)
    }
}