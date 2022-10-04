package com.nyahonk.rijksmuseumgallery.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
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

    fun getCollectionsPager() = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        CollectionsPagingSource(artCollectionsRepository)
    }
}