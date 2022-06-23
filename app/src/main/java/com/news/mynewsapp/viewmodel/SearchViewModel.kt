package com.news.mynewsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.repository.NewsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
): ViewModel() {
    private val _newsList = MutableLiveData<List<Articles>>()
    val newsList: LiveData<List<Articles>>
        get() = _newsList

    fun getData(query: String) {
        getForSearching(query)
    }

    private fun getForSearching(query: String) {
        viewModelScope.launch {
            val response = repository.exploreForNews(query)
            withContext(Dispatchers.Main) {
                response.body()?.let {
                    showNews(it.articles)
                }
            }
        }
    }
    private fun showNews(list: List<Articles>) {
        _newsList.value = list
    }
}