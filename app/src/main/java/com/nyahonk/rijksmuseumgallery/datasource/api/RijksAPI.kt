package com.nyahonk.rijksmuseumgallery.datasource.api

import com.nyahonk.rijksmuseumgallery.ApiKey
import com.nyahonk.rijksmuseumgallery.Constants
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionDetailsResponse
import com.nyahonk.rijksmuseumgallery.models.network.ArtCollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksAPI {

    @GET("collection")
    suspend fun getCollection(
        @Query("key") key: String = ApiKey.API_KEY,
        @Query("sort") sorting: String = "artist",
        @Query("ps") resultsPerPage: String = Constants.PAGE_SIZE.toString(),
        @Query("p") pageNumber: String = "0"
        ): ArtCollectionResponse

    @GET("collection/{collection}")
    suspend fun getCollectionDetails(
        @Path("collection") collection: String,
        @Query("key") key: String = ApiKey.API_KEY
    ): ArtCollectionDetailsResponse

}