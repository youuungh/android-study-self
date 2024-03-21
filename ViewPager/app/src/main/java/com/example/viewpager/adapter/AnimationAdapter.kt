package com.example.viewpager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.databinding.ItemListBinding

class AnimationAdapter(
    private val items: MutableList<Int>
): RecyclerView.Adapter<AnimationAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimationAdapter.ItemViewHolder {
        return ItemViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AnimationAdapter.ItemViewHolder, position: Int) {
        holder.apply {
            binding.img.setImageResource(items[position])
        }
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)
}