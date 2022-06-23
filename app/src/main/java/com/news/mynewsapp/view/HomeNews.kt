package com.news.mynewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.news.mynewsapp.adapter.ViewPagerAdapter
import com.news.mynewsapp.databinding.FragmentHomeNewsBinding


class HomeNews : Fragment() {
    private var _binding: FragmentHomeNewsBinding? = null
    private val binding
    get() = _binding!!
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = viewPagerAdapter
        tabLayout = binding.tabLayout
        forTabLayoutMediator()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun forTabLayoutMediator() {
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            this.arguments = Bundle().apply {
                // Our object is just an integer :-P
                when (position) {
                    0 -> tab.text = "all News"
                    1 -> tab.text = "sports"
                    2 -> tab.text = "science"
                    3 -> tab.text = "business"
                    4 -> tab.text = "entertainment"
                    5 -> tab.text = "health"
                    6 -> tab.text = "technology"
                }
            }
        }.attach()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}