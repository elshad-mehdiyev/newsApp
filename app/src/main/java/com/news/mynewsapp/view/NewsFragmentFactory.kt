package com.news.mynewsapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.news.mynewsapp.adapter.AddSourceAdapter
import com.news.mynewsapp.adapter.NewsAdapter
import com.news.mynewsapp.adapter.SearchNewsAdapter
import javax.inject.Inject

class NewsFragmentFactory @Inject constructor(
    private val searchNewsAdapter: SearchNewsAdapter,
    private val newsAdapter: NewsAdapter,
    private val addSourceAdapter: AddSourceAdapter,
    private val glide: RequestManager,
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ExploreNews::class.java.name -> ExploreNews(searchNewsAdapter)
            ViewPagerFragment::class.java.name -> ViewPagerFragment(newsAdapter)
            AddNewSource::class.java.name -> AddNewSource(addSourceAdapter)
            DetailsNewsFragment::class.java.name -> DetailsNewsFragment(glide)
            else ->  super.instantiate(classLoader, className)
        }
    }
}