
package com.example.appinashi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostEndPoint {
    @GET("news") // Endpoint
    fun getPosts(
        @Query("access_key") accessKey: String,
        @Query("keywords") keywords: String,
        @Query("countries") countries: String
    ): Call<ResponseData>
}