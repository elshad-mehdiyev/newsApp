package com.news.mynewsapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.mynewsapp.databinding.NewsItemsBinding
import com.news.mynewsapp.model.Articles
import javax.inject.Inject

class NewsAdapter @Inject constructor(): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private var mPosition = -1
    private var onItemClickListener: ((Articles) -> Unit)? = null

    class NewsHolder(var binding: NewsItemsBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

    }
    private val list = AsyncListDiffer(this, diffUtil)

    var newsAdapterList: List<Articles>
        get() = list.currentList
    set(value) = list.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = newsAdapterList[position]
        holder.binding.news = newsAdapterList[position]
        holder.binding.saveNews.setOnClickListener {
            mPosition = holder.adapterPosition
            if (mPosition == position) {
                holder.binding.saveNews.setColorFilter(Color.CYAN)
            } else {
                holder.binding.saveNews.setColorFilter(Color.WHITE)
            }
        }
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }

    }

    override fun getItemCount(): Int {
        return newsAdapterList.size
    }
    fun setOnItemClickListener(listener: (Articles) -> Unit) {
        onItemClickListener = listener
    }
}