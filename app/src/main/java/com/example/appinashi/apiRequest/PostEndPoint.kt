package com.example.appinashi.apiRequest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostEndPoint {
    @GET("news") // Endpoint
    fun getPosts(
        @Query("access_key") accessKey: String,
        @Query("categories") category: String,
        @Query("countries") countries: String,
        @Query("languages") language: String
    ): Call<ResponseData>
}