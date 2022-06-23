package com.news.mynewsapp.repository


import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.model.NewsResponse
import retrofit2.Response

class FakeRepository: NewsRepositoryInterface {
    private val list = mutableListOf<Articles>()

    override suspend fun saveNews(articles: Articles){
        list.add(articles)
    }

    override suspend fun deleteNews(articles: Articles) {
        list.remove(articles)
    }

    override suspend fun showSavedNews(): List<Articles> {
        return list
    }

    override suspend fun exploreForNews(searchQuery: String): Response<NewsResponse> {
        return Response.success(NewsResponse("",0, listOf()))
    }

    override suspend fun breakingNewsFromSelectedSource(sources: String): Response<NewsResponse> {
        return Response.success(NewsResponse("",0, listOf()))
    }

    override suspend fun breakingNewsFromSelectedCountry(country: String): Response<NewsResponse> {
        return Response.success(NewsResponse("",0, listOf()))
    }

    override suspend fun resultForCategory(category: String): Response<NewsResponse> {
        return Response.success(NewsResponse("",0, listOf()))
    }

}