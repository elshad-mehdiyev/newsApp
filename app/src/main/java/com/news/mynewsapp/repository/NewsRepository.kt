package com.news.mynewsapp.repository


import androidx.lifecycle.LiveData
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.model.NewsResponse
import com.news.mynewsapp.room.NewsDao
import com.news.mynewsapp.service.NewsApi
import retrofit2.Response
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val retrofitApi: NewsApi): NewsRepositoryInterface {


    override suspend fun saveNews(articles: Articles) {
        return newsDao.saveNews(articles)
    }

    override suspend fun deleteNews(articles: Articles) {
        newsDao.deleteNews(articles)
    }

    override fun showSavedNews(): LiveData<List<Articles>> {
       return newsDao.showSavedNews()
    }

    override fun getArticleList(): List<Articles> {
        return newsDao.getArticleList()
    }

    override suspend fun deleteAll() {
        newsDao.deleteAll()
    }

    override suspend fun exploreForNews(searchQuery: String): Response<NewsResponse> {
        return retrofitApi.exploreForNews(searchQuery)
    }

    override suspend fun breakingNewsFromSelectedSource(sources: String): Response<NewsResponse> {
        return retrofitApi.breakingNewsFromSelectedSource(sources)
    }

    override suspend fun breakingNewsFromSelectedCountry(country: String): Response<NewsResponse> {
        return retrofitApi.breakingNewsFromSelectedCountry(country)
    }

    override suspend fun resultForCategory(category:  String): Response<NewsResponse> {
        return retrofitApi.resultForCategory(category)
    }

}