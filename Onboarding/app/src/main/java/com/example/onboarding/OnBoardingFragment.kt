package com.example.onboarding

import android.annotation.SuppressLint
import android.graphics.drawable.Animatable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.viewpager2.widget.ViewPager2
import com.example.onboarding.databinding.FragmentOnBoardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    private val fragments = arrayOfNulls<OnBoardingPageFragment>(3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setArrows(0, false)

        with(binding) {
            previous.setOnClickListener {
                if (binding.viewpager.currentItem > 0) {
                    (binding.previous.icon as? Animatable)?.start()
                    binding.viewpager.currentItem--
                }
            }

            next.setOnClickListener {
                if (binding.viewpager.currentItem < 3) {
                    (binding.next.icon as? Animatable)?.start()
                    binding.viewpager.currentItem++
                }
            }
        }

        with(binding.viewpager) {
            adapter = OnBoardingPagerAdapter(this@OnBoardingFragment)
            currentItem = 0
            getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setArrows(position, true)
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                    for (i in 0..2) {
                        if (fragments[i] == null) {
                            fragments[i] = childFragmentManager.findFragmentByTag("f$i") as? OnBoardingPageFragment
                            fragments[i]?.updateLayout(false)
                        }
                    }

                    fragments[position]?.setOffset(position, positionOffset)
                    if (position > 0)
                        fragments[position - 1]?.setOffset(position, positionOffset)
                    if (position < (binding.viewpager.adapter?.itemCount?.minus(1) ?: 0))
                        fragments[position + 1]?.setOffset(position, positionOffset)

                    val targetPosition = if (positionOffset < 0.5f) position else position + 1

                    with(binding) {
                        container.alpha = if (positionOffset < 0.5f) 1 - 2 * positionOffset else 2 * positionOffset - 1
                        title.setText(OnBoardingPageFragment.getTitle(targetPosition))
                        description.setText(OnBoardingPageFragment.getDescription(targetPosition))
                    }
                }
            })
        }

        TabLayoutMediator(binding.tab, binding.viewpager) { _, _ -> }.attach()

        binding.tab.getChildAt(0).run {
            (this as? LinearLayout)?.children?.forEach {
                it.setOnTouchListener { _, _ -> true }
            }
        }
    }

    private fun setArrows(position: Int, animated: Boolean) {
        val prevAlpha = if (position > 0) 1f else 0f
        val nextAlpha = if (position < 2) 1f else 0f
        val buttonAlpha = if (position == 2) 1f else 0f
        if (animated) {
            binding.previous.animate().alpha(prevAlpha).setDuration(200).start()
            binding.next.animate().alpha(nextAlpha).setDuration(200).start()
            binding.start.animate().alpha(buttonAlpha).setDuration(100).start()
        } else {
            binding.previous.alpha = prevAlpha
            binding.next.alpha = nextAlpha
            binding.start.alpha = buttonAlpha
        }
        binding.previous.isEnabled = position > 0
        binding.next.isEnabled = position < 2
        binding.start.isEnabled = position == 2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}