package com.news.mynewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.mynewsapp.adapter.ProfilePageAdapter
import com.news.mynewsapp.databinding.FragmentProfilePageBinding
import com.news.mynewsapp.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilePage: Fragment() {
    private lateinit var binding: FragmentProfilePageBinding
    private val viewModel: ArticlesViewModel by viewModels()
    private val profilePageAdapter = ProfilePageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = profilePageAdapter
        profilePageAdapter.setOnItemClickListener {
            val action = ProfilePageDirections.actionProfilePageToWebViewOfNews2(it)
            findNavController().navigate(action)
        }
    }

    private fun observe() {
        viewModel.getSavedArticles().observe(viewLifecycleOwner) {
            profilePageAdapter.savedNewsAdapterList = it.reversed()
        }
    }
}