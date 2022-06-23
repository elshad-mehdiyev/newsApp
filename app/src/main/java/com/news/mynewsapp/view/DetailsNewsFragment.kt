package com.news.mynewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.news.mynewsapp.databinding.FragmentDetailsNewsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsNewsFragment(
    private val glide: RequestManager
): Fragment() {

    private var _binding: FragmentDetailsNewsBinding? = null
    private val binding
    get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsNewsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let { bundle ->
            if (bundle != null) {
                binding.detailNewsTitle.text = bundle.getString("title")
                binding.detailNewsContent.text = bundle.getString("content")
                binding.detailNewsTime.text = bundle.getString("pubDate")
                val url = bundle.getString("newsImage")
                glide.load(url).into(binding.detailNewsImage)
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}