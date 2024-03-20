package com.example.infiniterecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infiniterecycler.databinding.ItemContentBinding
import com.example.infiniterecycler.databinding.ItemLoadingBinding

class ContentAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<Content>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ITEM -> ItemViewHolder(ItemContentBinding.inflate(inflater, parent, false))
            TYPE_LOADING -> LoadingViewHolder(ItemLoadingBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int) = when (items[position].title) {
        " " -> TYPE_LOADING
        else -> TYPE_ITEM
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(items[position])
            is LoadingViewHolder -> Unit
        }
    }

    inner class ItemViewHolder(private val binding: ItemContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content) = with(binding) {
            title.text = content.title
            date.text = content.date.substring(0, 10)
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding): RecyclerView.ViewHolder(binding.root)

    fun setData(content: MutableList<Content>) {
        items.addAll(content)
        items.add(Content(" ", " "))
    }

    fun removeLoading() {
        items.removeAt(items.lastIndex)
    }

    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_LOADING = 1
    }
}