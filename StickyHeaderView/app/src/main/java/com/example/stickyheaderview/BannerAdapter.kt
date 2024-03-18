package com.example.stickyheaderview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaderview.databinding.ItemBannerBinding

class BannerAdapter(
	private val context: Context
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

	private val colorDataSet = arrayListOf(
		getColor(R.color.purple_500),
		getColor(R.color.teal_200),
		getColor(R.color.purple_200),
		getColor(R.color.teal_700)
	)

	inner class ViewHolder(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(colorRes: Int) {
			binding.banner.text = "$adapterPosition"
			binding.root.setBackgroundColor(colorRes)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun getItemCount(): Int = colorDataSet.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(colorDataSet[position])
	}

	private fun getColor(color: Int) = ResourcesCompat.getColor(context.resources, color, null)
}