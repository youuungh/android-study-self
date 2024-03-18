package com.example.stickyheaderview.sticky2

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.stickyheaderview.BannerAdapter
import com.example.stickyheaderview.FragmentStateAdapter
import com.example.stickyheaderview.R
import com.example.stickyheaderview.databinding.FragmentBottomBinding
import com.example.stickyheaderview.databinding.FragmentHeaderBinding
import com.example.stickyheaderview.databinding.FragmentTopBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

data class Item(val type: Int, val isHeader: Boolean)

class Sticky2Adapter(private val activity: Activity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null
    private var pageIndex: Int = 0

    private val dataSet = arrayListOf(
        Item(TOP, false),
        Item(HEADER, true),
        Item(BOTTOM, false)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TOP -> TopViewHolder(
                FragmentTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            HEADER -> HeaderViewHolder(
                FragmentHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> BottomViewHolder(
                FragmentBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int = dataSet[position].type

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopViewHolder -> holder.bind()
            is HeaderViewHolder -> holder.bind()
            is BottomViewHolder -> holder.bind()
        }
    }

    inner class TopViewHolder(val binding: FragmentTopBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding.banner) {
                adapter = BannerAdapter(activity)
            }
        }
    }

    inner class HeaderViewHolder(val binding: FragmentHeaderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            tabLayout = binding.tab

            viewPager?.let {
                TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                    tab.text = "tab$position"
                }.attach()
            }
        }
    }

    inner class BottomViewHolder(val binding: FragmentBottomBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            viewPager = binding.viewpager

            with(binding.viewpager) {
                adapter = FragmentStateAdapter(activity as FragmentActivity)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        pageIndex = position
                    }
                })
            }

            tabLayout?.let {
                TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                    tab.text = "tab$position"
                }.attach()
            }
        }
    }

    fun isHeader(position: Int) = dataSet[position].type == HEADER
    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val lastIndex =
            if (position < dataSet.size)
                position else dataSet.size - 1
        for (index in lastIndex downTo 0) {
            val model = dataSet[index]
            if (model.type == HEADER) {
                val headerView = LayoutInflater.from(list.context)
                    .inflate(R.layout.fragment_header, list, false)
                val tabLayout = headerView.findViewById<TabLayout>(R.id.tab)
                TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                    tab.text = "tab$position"
                }.attach()
                return headerView
            }
        }
        return null
    }

    companion object {
        const val TOP = 1
        const val HEADER = 2
        const val BOTTOM = 3
    }
}