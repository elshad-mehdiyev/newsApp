package com.news.mynewsapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.news.mynewsapp.constants.Constant.Companion.ARG_OBJECT
import com.news.mynewsapp.view.ViewPagerFragment


class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment(newsAdapter = NewsAdapter())
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}