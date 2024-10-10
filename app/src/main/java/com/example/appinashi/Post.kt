package com.example.appinashi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    //var author:String,
    var title: String,
    //var description: String,
    //var source: String,
    //var category: String
)
