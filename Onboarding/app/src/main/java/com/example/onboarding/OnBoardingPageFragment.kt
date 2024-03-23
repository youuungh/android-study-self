package com.example.onboarding

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import com.example.onboarding.databinding.FragmentOnBoardingPageBinding

class OnBoardingPageFragment: Fragment() {

    private var _binding: FragmentOnBoardingPageBinding? = null
    private val binding get() = _binding!!
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        position = arguments?.getInt("POSITION") ?: 0

        with(binding) {
            bgImage.setImageResource(getBgImage(position))
            frontImage.setImageResource(getFrontImage(position))
            title.setText(getTitle(position))
            description.setText(getDescription(position))
        }

        binding.container.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                updateLayout(true)
                binding.container.viewTreeObserver?.removeOnGlobalLayoutListener(this)
            }
        })
    }

    fun updateLayout(force: Boolean) {
        if (context == null)
            return

        val orientation = resources.configuration.orientation
        val params = binding.container.layoutParams

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (!force && (params?.height ?: 0) > 0)
                return
            params?.height = binding.container.width
        } else {
            if (!force && (params?.width ?: 0) > 0)
                return
            params?.width = binding.container.height
        }

        binding.container.requestLayout()
        if (force)
            binding.container.forceLayout()
    }

    fun setOffset(position: Int, offset: Float) {
        with(binding) {
            val bgOffset = -200
            val fOffset = 200
            val titleOffset = 150
            bgImage.translationX =
                if (position == this@OnBoardingPageFragment.position) offset * -bgOffset.toFloat() else (1 - offset) * bgOffset
            frontImage.translationX =
                if (position == this@OnBoardingPageFragment.position) offset * fOffset.toFloat() else (1 - offset) * fOffset
            title.translationX =
                if (position == this@OnBoardingPageFragment.position) offset * -titleOffset.toFloat() else (1 - offset) * titleOffset
        }
    }

    override fun onResume() {
        super.onResume()
        updateLayout(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private fun getBgImage(position: Int) = when (position) {
            1 -> R.drawable.bg_onboarding_2
            2 -> R.drawable.bg_onboarding_3
            else -> R.drawable.bg_onboarding_1
        }

        private fun getFrontImage(position: Int) = when (position) {
            1 -> R.drawable.f_onboarding_2
            2 -> R.drawable.f_onboarding_3
            else -> R.drawable.f_onboarding_1
        }

        fun getTitle(position: Int) = when (position) {
            1 -> R.string.title_2
            2 -> R.string.title_3
            else -> R.string.title_1
        }

        fun getDescription(position: Int) = when (position) {
            1 -> R.string.description_2
            2 -> R.string.description_3
            else -> R.string.description_1
        }
    }
}