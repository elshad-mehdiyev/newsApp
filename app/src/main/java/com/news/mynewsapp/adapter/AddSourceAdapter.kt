package com.news.mynewsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.mynewsapp.databinding.AddSourceItemBinding
import com.news.mynewsapp.model.Articles
import javax.inject.Inject

class AddSourceAdapter @Inject constructor(): RecyclerView.Adapter<AddSourceAdapter.SourceHolder>() {

    class SourceHolder(var binding: AddSourceItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

    }

    private val list = AsyncListDiffer(this, diffUtil)

    var addSourceAdapterList: List<Articles>
    get() = list.currentList
    set(value) = list.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceHolder {
        val binding = AddSourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SourceHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceHolder, position: Int) {
        holder.binding.source = addSourceAdapterList[position]
    }

    override fun getItemCount(): Int {
       return  addSourceAdapterList.size
    }
}