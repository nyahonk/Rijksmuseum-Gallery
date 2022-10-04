package com.nyahonk.rijksmuseumgallery.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nyahonk.rijksmuseumgallery.Constants
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepository
import javax.inject.Inject

class ArtCollectionsUseCase @Inject constructor(
    private val artCollectionsRepository: ArtCollectionsRepository
) {

    fun getCollectionsPager() = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        CollectionsPagingSource(artCollectionsRepository)
    }
}