package com.news.mynewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.repository.NewsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddSourceViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
): ViewModel() {

    private val _sourceList = MutableStateFlow<List<Articles>>(listOf())
    val sourceList: StateFlow<List<Articles>> = _sourceList.asStateFlow()


    fun getData() {
        getFromSource()
    }
    private fun getFromSource() {
        viewModelScope.launch {
            val response = repository.breakingNewsFromSelectedCountry("tr")
            withContext(Dispatchers.Main) {
                response.body()?.let {
                    showNews(it.articles)
                }
            }
        }
    }

    private fun showNews(list: List<Articles>) {
        _sourceList.value = list
    }
}