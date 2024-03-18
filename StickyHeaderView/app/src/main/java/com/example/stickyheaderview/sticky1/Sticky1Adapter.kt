package com.example.stickyheaderview.sticky1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaderview.R
import com.example.stickyheaderview.databinding.ViewholderBottomBinding
import com.example.stickyheaderview.databinding.ViewholderContentsBinding
import com.example.stickyheaderview.databinding.ViewholderHeaderBinding
import com.example.stickyheaderview.databinding.ViewholderTopHolderBinding

class Sticky1Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val contentList = arrayListOf<ContentList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                ViewholderHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_TOP_HOLDER -> TopHolderViewHolder(
                ViewholderTopHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_BOTTOM -> BottomViewHolder(
                ViewholderBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_CONTENTS -> ContentsViewHolder(
                ViewholderContentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> TODO("Unknown viewtype : $viewType")
        }
    }

    override fun getItemCount(): Int = contentList.size

    override fun getItemViewType(position: Int): Int = contentList[position].type

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.binding.root
            is TopHolderViewHolder -> holder.binding.root
            is BottomViewHolder -> holder.binding.root
            is ContentsViewHolder -> holder.bind(contentList[position].item as Content)
        }
    }

    fun initData(contents: ArrayList<Content>) {
        contentList.add(ContentList(TYPE_HEADER, ""))
        contents.forEachIndexed { index, contentsData ->
            if (index % 10 == 0) {
                contentList.add(ContentList(TYPE_TOP_HOLDER, ""))
            }
            contentList.add(ContentList(TYPE_CONTENTS, contentsData))
        }
        contentList.add(ContentList(TYPE_BOTTOM, ""))
        notifyDataSetChanged()
    }

    fun isHeader(position: Int) = contentList[position].type == TYPE_TOP_HOLDER
    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val lastIndex =
            if (position < contentList.size)
                position else contentList.size - 1
        for (index in lastIndex downTo 0) {
            val model = contentList[index]
            if (model.type == TYPE_TOP_HOLDER) {
                return LayoutInflater.from(list.context)
                    .inflate(R.layout.viewholder_top_holder, list, false)
            }
        }
        return null
    }

    inner class HeaderViewHolder(val binding: ViewholderHeaderBinding): RecyclerView.ViewHolder(binding.root)
    inner class TopHolderViewHolder(val binding: ViewholderTopHolderBinding) : RecyclerView.ViewHolder(binding.root)
    inner class BottomViewHolder(val binding: ViewholderBottomBinding) : RecyclerView.ViewHolder(binding.root)
    inner class ContentsViewHolder(val binding: ViewholderContentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content) {
            binding.content.text = content.stringData
        }
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_TOP_HOLDER = 1
        const val TYPE_BOTTOM = 2
        const val TYPE_CONTENTS = 3
    }
}