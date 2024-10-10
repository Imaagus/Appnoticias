package com.example.appinashi

data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int
)
