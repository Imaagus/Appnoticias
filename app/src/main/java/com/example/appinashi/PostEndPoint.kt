package com.example.appinashi

import retrofit2.Call
import retrofit2.http.GET

interface PostEndPoint {
    @GET("/posts")
    fun getPosts() : Call<List<Post>>
}