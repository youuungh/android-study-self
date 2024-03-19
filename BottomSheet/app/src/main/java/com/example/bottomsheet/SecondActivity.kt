package com.example.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomsheet.databinding.ActivitySecondBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var behavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> Log.d("tag", "COLLAPSED")
                    BottomSheetBehavior.STATE_DRAGGING -> Log.d("tag", "DRAGGING")
                    BottomSheetBehavior.STATE_EXPANDED -> Log.d("tag", "EXPANDED")
                    BottomSheetBehavior.STATE_HIDDEN -> Log.d("tag", "HIDDEN")
                    BottomSheetBehavior.STATE_SETTLING-> Log.d("tag", "SETTLING")
                    else -> {}
                }
            }

            override fun onSlide(p0: View, p1: Float) {}
        })

        binding.collapse.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        binding.expand.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}