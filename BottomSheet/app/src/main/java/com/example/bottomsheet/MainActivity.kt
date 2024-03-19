package com.example.bottomsheet

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.bottomsheet.databinding.ActivityMainBinding

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

        binding.apply {
            modal1.setOnClickListener {
                ModalBottomSheet1().show(supportFragmentManager, "modal1")
            }
            modal2.setOnClickListener {
                ModalBottomSheet2().apply {
                    setStyle(DialogFragment.STYLE_NORMAL, R.style.Transparent_BottomSheet)
                }.show(supportFragmentManager, "modal2")
            }
            modal3.setOnClickListener {
                ModalBottomSheet3.show(supportFragmentManager, "modal3")
            }
        }

        binding.persistent.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}