package com.example.viewpager

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager.adapter.AnimationAdapter
import com.example.viewpager.adapter.AutoInfiniteAdapter
import com.example.viewpager.adapter.MultiItemAdapter
import com.example.viewpager.databinding.ActivityAnimationBinding
import kotlin.math.abs

class Animation : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding
    private lateinit var animationAdapter: AnimationAdapter
    private lateinit var multiItemAdapter: MultiItemAdapter
    private lateinit var autoInfiniteAdapter: AutoInfiniteAdapter
    private var currentPosition = Int.MAX_VALUE / 4
    private val autoScrollHandler = Handler(Looper.getMainLooper())
    private var autoScrollInterval: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val items = mutableListOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
        )

        animationAdapter = AnimationAdapter(items)
        multiItemAdapter = MultiItemAdapter(this)
        autoInfiniteAdapter = AutoInfiniteAdapter(items)

        with(binding.viewpager) {
            adapter = animationAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            setPageTransformer(ZoomOutPageTransformer())
        }

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
        }

        with(binding.viewpager2) {
            adapter = animationAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(compositePageTransformer)
        }
        binding.indicator.attachTo(binding.viewpager2)

        with(binding.viewpager3) {
            adapter = multiItemAdapter
        }

        binding.max.text = items.size.toString()

        with(binding.viewpager4) {
            adapter = autoInfiniteAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            setCurrentItem(currentPosition, false)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.current.text = "${((position+1) % 4) + 1}"
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        ViewPager2.SCROLL_STATE_IDLE -> startAutoScroll()
                        ViewPager2.SCROLL_STATE_DRAGGING -> stopAutoScroll()
                        ViewPager2.SCROLL_STATE_SETTLING -> {}
                    }
                }
            })
        }
    }

    inner class ZoomOutPageTransformer: ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { alpha = 0f }
                    position <= 1 -> {
                        val scaleFactor = 0.85f.coerceAtLeast(1 - abs(position))
                        val verticalMargin = pageHeight * (1 - scaleFactor) / 2
                        val horizontalMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horizontalMargin - verticalMargin / 2
                        } else {
                            horizontalMargin + verticalMargin / 2
                        }
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                        alpha = (0.5f +
                                (((scaleFactor - 0.85f) / (1 - 0.85f)) * (1 - 0.5f)))
                    }
                    else -> {
                        alpha = 0f
                    }
                }
            }
        }
    }

    private val autoScrollRunnable = Runnable {
        binding.viewpager4.setCurrentItem(++currentPosition, true)
        startAutoScroll()
    }

    private fun startAutoScroll() {
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
        autoScrollHandler.postDelayed(autoScrollRunnable, autoScrollInterval)
    }

    private fun stopAutoScroll() {
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
    }

    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScroll()
    }
}