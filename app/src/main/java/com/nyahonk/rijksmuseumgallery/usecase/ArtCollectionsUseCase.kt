package com.nyahonk.rijksmuseumgallery.usecase

import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.models.ArtObjectDetailsItem

interface ArtCollectionsUseCase {

    suspend fun getCollectionDetails(collection: String): ArtObjectDetailsItem

    suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): List<ArtCollectionListItem>
}