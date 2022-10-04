package com.nyahonk.rijksmuseumgallery.usecase

import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.models.ArtObjectDetailsItem
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepository
import javax.inject.Inject

class ArtCollectionsUseCase @Inject constructor(
    private val artCollectionsRepository: ArtCollectionsRepository
) {

    suspend fun getCollectionDetails(collection: String): ArtObjectDetailsItem {
        with(artCollectionsRepository.getCollectionDetails(collection).artObject) {
            return ArtObjectDetailsItem(
                title = title,
                author = principalOrFirstMaker,
                description = description,
                url = webImage.url
            )
        }

    }

    suspend fun getCollectionItems(pageNumber: Int, resultsPerPage: Int): List<ArtCollectionListItem> {
        return artCollectionsRepository.getCollectionItems(pageNumber, resultsPerPage).artObjects.map {
            ArtCollectionListItem(
                id = it.objectNumber,
                title = it.title,
                author = it.principalOrFirstMaker,
                imageHeaderUrl = it.headerImage.url,
                imageFullUrl = it.webImage.url
            )
        }
    }
}