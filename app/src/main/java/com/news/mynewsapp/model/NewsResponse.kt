package com.news.mynewsapp.model


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)