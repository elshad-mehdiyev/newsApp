package com.news.mynewsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.news.mynewsapp.adapter.SearchNewsAdapter
import com.news.mynewsapp.databinding.FragmentExploreNewsBinding
import com.news.mynewsapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreNews(
    private val searchNewsAdapter: SearchNewsAdapter
) : Fragment() {
    private var _binding: FragmentExploreNewsBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    var job:Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreNewsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getData(editable.toString())
                    }
                }
            }
        }
        observeData()
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = searchNewsAdapter
        searchNewsAdapter.setOnItemClickListener {
            val action = ExploreNewsDirections.actionExploreNewsToWebViewOfNews2(it)
            findNavController().navigate(action)
        }
    }

    private fun observeData() {
        viewModel.newsList.observe(viewLifecycleOwner) {
            it.let {
                searchNewsAdapter.searchAdapterList = it ?: listOf()
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        _binding = null
        super.onDestroy()
    }
}