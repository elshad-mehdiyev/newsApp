package com.news.mynewsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.mynewsapp.databinding.SearchItemBinding
import com.news.mynewsapp.model.Articles
import javax.inject.Inject

class SearchNewsAdapter @Inject constructor(): RecyclerView.Adapter<SearchNewsAdapter.SearchHolder>() {

    private var onItemClickListener: ((Articles) -> Unit)? = null

    class SearchHolder(var binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

    }
    private val list = AsyncListDiffer(this, diffUtilCallBack)

    var searchAdapterList: List<Articles>
    get() = list.currentList
    set(value) = list.submitList(value)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article = searchAdapterList[position]
        holder.binding.search = searchAdapterList[position]

        holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
    }

    override fun getItemCount(): Int {
        return searchAdapterList.size
    }
    fun setOnItemClickListener(listener: (Articles) -> Unit) {
        onItemClickListener = listener
    }
}