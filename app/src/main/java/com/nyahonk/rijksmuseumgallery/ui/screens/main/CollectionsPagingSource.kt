package com.nyahonk.rijksmuseumgallery.ui.screens.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import com.nyahonk.rijksmuseumgallery.utils.Constants

class CollectionsPagingSource(
    private val source: ArtCollectionsUseCase
) : PagingSource<Int, ArtCollectionListItem>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ArtCollectionListItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = source.getCollectionItems(nextPageNumber, Constants.PAGE_SIZE)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtCollectionListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}