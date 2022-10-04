package com.nyahonk.rijksmuseumgallery.models.network

import com.squareup.moshi.Json

data class ArtCollectionDetailsResponse(
    @Json(name = "artObject")
    val artObject: ArtObjectDetails
)

data class ArtObjectDetails(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "webImage")
    val webImage: WebImage
)

