package com.news.mynewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.mynewsapp.adapter.NewsAdapter
import com.news.mynewsapp.constants.Constant.Companion.ARG_OBJECT
import com.news.mynewsapp.databinding.FragmentViewPagerBinding
import com.news.mynewsapp.viewmodel.ViewPagerFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ViewPagerFragment(
    private val newsAdapter: NewsAdapter
) : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding
    get() = _binding!!
    private var idViewPager = 0
    private val viewModel: ViewPagerFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            idViewPager = getInt(ARG_OBJECT)
        }
        viewModel.getData(idViewPager)


        when(idViewPager) {
            1 -> observeData()
            2 -> observeData()
            3 -> observeData()
            4 -> observeData()
            5 -> observeData()
            6 -> observeData()
            7 -> observeData()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        newsAdapter.setOnItemClickListener {
            val action = HomeNewsDirections.actionHomeNewsToWebViewOfNews2(it)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = newsAdapter
    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.newsList.collectLatest {
                newsAdapter.newsAdapterList = it
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}