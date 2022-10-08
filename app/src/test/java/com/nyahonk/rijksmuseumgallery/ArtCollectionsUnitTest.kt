package com.nyahonk.rijksmuseumgallery

import androidx.paging.PagingSource
import com.nyahonk.rijksmuseumgallery.data.MockRijksAPI
import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSource
import com.nyahonk.rijksmuseumgallery.datasource.NetworkDataSourceImpl
import com.nyahonk.rijksmuseumgallery.datasource.api.RijksAPI
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.models.ArtObjectDetailsItem
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepository
import com.nyahonk.rijksmuseumgallery.repository.ArtCollectionsRepositoryImpl
import com.nyahonk.rijksmuseumgallery.ui.screens.main.CollectionsPagingSource
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCaseImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtCollectionsUnitTest {

    private lateinit var rijksApi: RijksAPI
    private lateinit var dataSource: NetworkDataSource
    private lateinit var repository: ArtCollectionsRepository
    private lateinit var useCase: ArtCollectionsUseCase

    private lateinit var expectedListItemsCollection: MutableList<ArtCollectionListItem>
    private lateinit var expectedArtObjectDetailsItem: ArtObjectDetailsItem

    private val itemsPerPage = 10

    @Before
    fun setupDependencies() {
        rijksApi = MockRijksAPI()
        dataSource = NetworkDataSourceImpl(rijksApi)
        repository = ArtCollectionsRepositoryImpl(dataSource)
        useCase = ArtCollectionsUseCaseImpl(repository)
        populateExpectedListItemsCollection()
        expectedArtObjectDetailsItem = createArtObjectDetailsItem()
    }

    @Test
    fun testCollectionItemsSizeIsCorrect() = runTest {
        assertEquals(itemsPerPage, useCase.getCollectionItems(1, itemsPerPage).size)
    }

    @Test
    fun testMappingCollectionItemsIsCorrect() = runTest {

        val mappedItem = useCase.getCollectionItems(1, 1).first()

        val expectedItem = ArtCollectionListItem(
            id = "objectNumber1",
            title = "title1",
            author = "principalOrFirstMaker1",
            imageHeaderUrl = "TestUrl1",
            imageFullUrl = "TestUrl1"
        )
        assertEquals(expectedItem, mappedItem)
    }

    @Test
    fun testPaginationLoad() = runTest {

        val pager = CollectionsPagingSource(useCase)

        var nextPageNumber = 2

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expectedListItemsCollection, prevKey = null, nextKey = nextPageNumber
            ),
            pager.load(
                PagingSource.LoadParams.Refresh(
                    key = null, loadSize = 10, placeholdersEnabled = false
                )
            ),
        )

    }

    @Test
    fun testArtObjectDetailsItemMapping() = runTest {
        assertEquals(expectedArtObjectDetailsItem, useCase.getCollectionDetails(""))
    }

    private fun populateExpectedListItemsCollection() {
        expectedListItemsCollection = ArrayList()

        expectedListItemsCollection.apply {
            for (i in 1..itemsPerPage) {
                add(
                    ArtCollectionListItem(
                        id = "objectNumber$i",
                        title = "title$i",
                        author = "principalOrFirstMaker$i",
                        imageHeaderUrl = "TestUrl$i",
                        imageFullUrl = "TestUrl$i"
                    )
                )
            }
        }
    }

    private fun createArtObjectDetailsItem() = ArtObjectDetailsItem(
        title = "title",
        author = "principalOrFirstMaker",
        description = "description",
        url = "TestUrl"
    )

}