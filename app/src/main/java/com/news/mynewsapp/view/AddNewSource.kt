package com.news.mynewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.news.mynewsapp.adapter.AddSourceAdapter
import com.news.mynewsapp.databinding.FragmentAddNewSourceBinding
import com.news.mynewsapp.viewmodel.AddSourceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddNewSource(
    private val addSourceAdapter: AddSourceAdapter
) : Fragment() {
    private var _binding: FragmentAddNewSourceBinding? = null
    private val binding
    get() = _binding!!
    private val viewModel: AddSourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewSourceBinding.inflate(layoutInflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()

        observeData()

        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = addSourceAdapter
    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.sourceList.collectLatest {
                addSourceAdapter.addSourceAdapterList = it
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}