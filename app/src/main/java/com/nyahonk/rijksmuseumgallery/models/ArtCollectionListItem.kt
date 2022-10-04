package com.nyahonk.rijksmuseumgallery.models

data class ArtCollectionListItem(
    var id: String,
    val title: String,
    val author: String,
    val imageHeaderUrl: String,
    val imageFullUrl: String
)
