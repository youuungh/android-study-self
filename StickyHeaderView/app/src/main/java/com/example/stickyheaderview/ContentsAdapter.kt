package com.example.stickyheaderview

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaderview.databinding.ItemContentsBinding
import kotlin.random.Random

class ContentsAdapter : RecyclerView.Adapter<ContentsAdapter.ViewHolder>(){

	private val dataSet = arrayListOf<String>()

	init {
		for (i in 0 until 200) {
			dataSet.add("contents:$i")
		}
	}

	inner class ViewHolder(private val binding: ItemContentsBinding): RecyclerView.ViewHolder(binding.root){
		fun bind(contents: String) {
			binding.contents.text = contents
		}
	}

	override fun onViewRecycled(holder: ViewHolder) {
		super.onViewRecycled(holder)
		Log.d("tag", "$holder")
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(ItemContentsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun getItemCount(): Int = dataSet.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(dataSet[position])
	}
}