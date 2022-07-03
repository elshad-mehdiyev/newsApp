package com.news.mynewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.repository.NewsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ViewPagerFragmentViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
): ViewModel() {

    private val _newsList = MutableStateFlow<List<Articles>>(listOf())
    val newsList: StateFlow<List<Articles>> = _newsList

    fun getData(idView: Int) {
        when(idView) {
            1 -> getFromSelectedCountry()
            2 -> resultForCategory("sports")
            3 -> resultForCategory("science")
            4 -> resultForCategory("business")
            5 -> resultForCategory("entertainment")
            6 -> resultForCategory("health")
            7 -> resultForCategory("technology")
        }
    }
    private fun getFromSelectedCountry() {
        viewModelScope.launch {
               val response = repository.breakingNewsFromSelectedCountry("tr")
                withContext(Dispatchers.Main) {
                    response.body()?.let {
                        showNews(it.articles)
                    }
                }
        }
    }
    private fun getFromSource() {
        viewModelScope.launch {
            val response = repository.breakingNewsFromSelectedSource("cnn")
            withContext(Dispatchers.Main) {
                response.body()?.let {
                    showNews(it.articles)
                }
            }
        }
    }
    private fun resultForCategory(category: String) {
        viewModelScope.launch {
            val response = repository.resultForCategory(category)
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