package com.example.stickyheaderview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stickyheaderview.databinding.ActivityMainBinding
import com.example.stickyheaderview.sticky1.Sticky1
import com.example.stickyheaderview.sticky2.Sticky2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.sticky1.setOnClickListener {
            startActivity(Intent(this, Sticky1::class.java))
        }

        binding.sticky2.setOnClickListener {
            startActivity(Intent(this, Sticky2::class.java))
        }
    }
}