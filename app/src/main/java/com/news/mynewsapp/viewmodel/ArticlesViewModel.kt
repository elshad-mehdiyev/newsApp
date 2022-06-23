package com.news.mynewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.repository.NewsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    val repository: NewsRepositoryInterface
): ViewModel() {

    fun saveArticles(articles: Articles) = viewModelScope.launch {
            repository.saveNews(articles)
        }
    fun getSavedArticles() = repository.showSavedNews()

    fun deleteArticles(articles: Articles) = viewModelScope.launch {
            repository.deleteNews(articles)
        }
    fun deleteAll() = viewModelScope.launch {
            repository.deleteAll()
        }
    fun getArticleList() = repository.getArticleList()
}