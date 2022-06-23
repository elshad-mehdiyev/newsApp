package com.news.mynewsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.mynewsapp.databinding.SavedItemsBinding
import com.news.mynewsapp.model.Articles

class ProfilePageAdapter(): RecyclerView.Adapter<ProfilePageAdapter.SavedNewsHolder>() {

    private var onItemClickListener: ((Articles) -> Unit)? = null

    class SavedNewsHolder(var binding: SavedItemsBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

    }
    private val list = AsyncListDiffer(this, diffUtil)

    var savedNewsAdapterList: List<Articles>
        get() = list.currentList
        set(value) = list.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsHolder {
        val binding = SavedItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedNewsHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedNewsHolder, position: Int) {
        val article = savedNewsAdapterList[position]
        holder.binding.saved = savedNewsAdapterList[position]

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return savedNewsAdapterList.size
    }

    fun setOnItemClickListener(listener: (Articles) -> Unit) {
        onItemClickListener = listener
    }
}