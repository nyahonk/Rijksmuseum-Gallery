package com.nyahonk.rijksmuseumgallery.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nyahonk.rijksmuseumgallery.Constants
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepository

class CollectionsPagingSource(
    private val repository: ArtCollectionsRepository
) : PagingSource<Int, ArtCollectionListItem>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ArtCollectionListItem> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = repository.getCollectionItems(nextPageNumber, Constants.PAGE_SIZE)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
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