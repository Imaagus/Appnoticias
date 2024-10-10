package com.example.appinashi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetroFitClient {
    private val BASE_URl = "https://jsonplaceholder.typicode.com"

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}