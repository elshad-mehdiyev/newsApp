package com.news.mynewsapp.view

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.news.mynewsapp.R
import com.news.mynewsapp.databinding.FragmentWebViewOfNewsBinding
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewOfNews : Fragment() {
    private lateinit var binding: FragmentWebViewOfNewsBinding
    private val args: WebViewOfNewsArgs by navArgs()
    private val viewModel: ArticlesViewModel by viewModels()
    private var articlesList = mutableSetOf<Articles>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewOfNewsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articles = args.article

        val webSetting = binding.webView.settings
        webSetting.javaScriptEnabled
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
            articles.url?.let { binding.webView.loadUrl(it) }
        //binding.topAppBar.title = articles.source.name
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.webProgressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                binding.webProgressBar.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }
        }
       binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.save_webview -> {
                        if (articles !in articlesList) {
                            articlesList.add(articles)
                            println(articlesList)
                            viewModel.saveArticles(articles)
                        }
                    true}
                R.id.share_webview -> {
                    val intent = Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, articles.url)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }
        binding.bottomAppBar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}