package com.nyahonk.rijksmuseumgallery.models.network

import com.squareup.moshi.Json

data class WebImage(
    @Json(name = "url")
    val url: String
)