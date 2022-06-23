package com.news.mynewsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.mynewsapp.MainCoroutineRule
import com.news.mynewsapp.repository.FakeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(FakeRepository())
    }
}