package com.example.viewpager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.viewpager.R
import com.example.viewpager.databinding.ItemListBinding

class MultiItemAdapter(
    private val context: Context
): PagerAdapter() {

    private val items = mutableListOf(
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4
    )

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemListBinding.inflate(LayoutInflater.from(context), container, false)
        binding.img.setImageResource(items[position])
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageWidth(position: Int): Float = 0.3f
}