package com.example.appinashi.apiRequest

data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int
)
