package com.example.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomsheet.databinding.ItemModalBinding

data class Item(
    val drawable: Int,
    val option: String
)

class ItemAdapter(
    private val itemList: ArrayList<Item>,
    private val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var checkedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemModalBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class ItemViewHolder(private val binding: ItemModalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                title.text = item.option
                button.isChecked = checkedItem == adapterPosition
                icon.setImageResource(item.drawable)
                root.setOnClickListener {
                    checkedItem = adapterPosition
                    onItemClick(adapterPosition)
                }
            }
        }
    }
}