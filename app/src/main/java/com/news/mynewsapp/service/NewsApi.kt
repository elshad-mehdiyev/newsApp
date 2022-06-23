package com.news.mynewsapp.service

import com.news.mynewsapp.constants.Constant.Companion.API_KEY
import com.news.mynewsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun exploreForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageInt: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
    @GET("top-headlines")
    suspend fun breakingNewsFromSelectedSource(
        @Query("sources")
        sources: String,
        @Query("page")
        pageInt: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
    @GET("top-headlines")
    suspend fun breakingNewsFromSelectedCountry(
        @Query("country")
        country: String,
        @Query("page")
        pageInt: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
    @GET("top-headlines")
    suspend fun resultForCategory(
        @Query("category")
        country: String,
        @Query("page")
        pageInt: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}