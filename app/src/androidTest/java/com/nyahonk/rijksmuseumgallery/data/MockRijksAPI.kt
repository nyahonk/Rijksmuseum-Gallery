package com.nyahonk.rijksmuseumgallery.data

import com.nyahonk.rijksmuseumgallery.datasource.api.RijksAPI
import com.nyahonk.rijksmuseumgallery.models.network.*

class MockRijksAPI : RijksAPI {

    private var idCounter: Int = 0

    override suspend fun getCollection(
        key: String,
        sorting: String,
        resultsPerPage: String,
        pageNumber: String
    ): ArtCollectionResponse {

        val count = resultsPerPage.toInt()
        val artObjects = ArrayList<ArtObject>()

        for (i in 1..count) {
            artObjects.add(
                ArtObject(
                    id = (++idCounter).toString(),
                    headerImage = HeaderImage("TestUrl$idCounter"),
                    longTitle = "longTitle$idCounter",
                    objectNumber = "objectNumber$idCounter",
                    principalOrFirstMaker = "principalOrFirstMaker$idCounter",
                    title = "title$idCounter",
                    webImage = WebImage("TestUrl$idCounter")
                )
            )
        }

        return ArtCollectionResponse(
            artObjects = artObjects,
            count = count
        )
    }

    override suspend fun getCollectionDetails(
        collection: String,
        key: String
    ): ArtCollectionDetailsResponse {
        val artObject = ArtObjectDetails(
            description = "description",
            id = "id",
            principalOrFirstMaker = "principalOrFirstMaker",
            title = "title",
            webImage = WebImage("TestUrl")
        )
        return ArtCollectionDetailsResponse(artObject)
    }
}