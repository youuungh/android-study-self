package com.example.viewpager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.databinding.ItemAutoInfiniteBinding

class AutoInfiniteAdapter(
    private val items: MutableList<Int>
): RecyclerView.Adapter<AutoInfiniteAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AutoInfiniteAdapter.ItemViewHolder {
        return ItemViewHolder(ItemAutoInfiniteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AutoInfiniteAdapter.ItemViewHolder, position: Int) {
        holder.apply {
            binding.img.setImageResource(items[(position+1) % 4])
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    inner class ItemViewHolder(val binding: ItemAutoInfiniteBinding): RecyclerView.ViewHolder(binding.root)

}