package com.news.mynewsapp.repository

import androidx.lifecycle.LiveData
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.model.NewsResponse
import retrofit2.Response

interface NewsRepositoryInterface {

    suspend fun saveNews(articles: Articles)

    suspend fun deleteNews(articles: Articles)

    fun showSavedNews(): LiveData<List<Articles>>

    fun getArticleList(): List<Articles>

    suspend fun deleteAll()

    suspend fun exploreForNews(searchQuery: String) : Response<NewsResponse>

    suspend fun breakingNewsFromSelectedSource(sources: String): Response<NewsResponse>

    suspend fun breakingNewsFromSelectedCountry(country: String): Response<NewsResponse>

    suspend fun resultForCategory(category: String): Response<NewsResponse>
}