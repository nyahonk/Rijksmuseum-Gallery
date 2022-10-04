package com.nyahonk.rijksmuseumgallery.models.network

import com.squareup.moshi.Json

data class ArtCollectionResponse(
    @Json(name = "artObjects")
    val artObjects: List<ArtObject>,
    @Json(name = "count")
    val count: Int
)

data class ArtObject(
    @Json(name = "headerImage")
    val headerImage: HeaderImage,
    @Json(name = "id")
    val id: String,
    @Json(name = "longTitle")
    val longTitle: String,
    @Json(name = "objectNumber")
    val objectNumber: String,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "webImage")
    val webImage: WebImage
)

data class HeaderImage(
    @Json(name = "url")
    val url: String
)
