package com.example.stickyheaderview.sticky1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaderview.databinding.ActivitySticky1Binding

class Sticky1 : AppCompatActivity() {

    private lateinit var binding: ActivitySticky1Binding
    private lateinit var sticky1Adapter: Sticky1Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySticky1Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        sticky1Adapter = Sticky1Adapter()

        initContentList()

        with(binding.rv1) {
            layoutManager = LinearLayoutManager(context)
            adapter = sticky1Adapter
            addItemDecoration(StickyHeaderItemDecoration(getSectionCallBack()))
        }
    }

    private fun getSectionCallBack(): StickyHeaderItemDecoration.SectionCallback {
        return object : StickyHeaderItemDecoration.SectionCallback {
            override fun isHeader(position: Int): Boolean {
                return sticky1Adapter.isHeader(position)
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return sticky1Adapter.getHeaderView(list, position)
            }

        }
    }

    private fun initContentList() {
        val contentList = arrayListOf<Content>()
        for (i in 0..50) {
            contentList.add(Content("contents[$i]"))
        }
        sticky1Adapter.initData(contentList)
    }
}