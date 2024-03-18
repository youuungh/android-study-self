package com.example.stickyheaderview.sticky2

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaderview.R
import com.example.stickyheaderview.databinding.ActivitySticky2Binding

class Sticky2 : AppCompatActivity() {

    private lateinit var binding: ActivitySticky2Binding
    private lateinit var sticky2Adapter: Sticky2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySticky2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sticky2Adapter = Sticky2Adapter(this)

        with(binding.rv2) {
            layoutManager = LinearLayoutManager(context)
            adapter = sticky2Adapter
            addItemDecoration(StickyHeaderItemDecoration2(getSectionCallback()))
        }
    }

    private fun getSectionCallback(): StickyHeaderItemDecoration2.SectionCallback {
        return object : StickyHeaderItemDecoration2.SectionCallback {
            override fun isHeader(position: Int): Boolean {
                return sticky2Adapter.isHeader(position)
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return sticky2Adapter.getHeaderView(list, position)
            }
        }
    }
}