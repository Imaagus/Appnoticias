package com.example.appinashi
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    val author: String?,
    val title: String,
    val description: String,
    val source: String,
    val category: String,
    val url: String,
    val image: String?,
    val language: String,
    val country: String,
    val published_at: String
)

