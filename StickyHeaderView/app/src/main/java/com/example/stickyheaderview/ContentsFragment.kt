package com.example.stickyheaderview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stickyheaderview.databinding.FragmentContentsBinding

class ContentsFragment : Fragment() {

	private lateinit var binding: FragmentContentsBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentContentsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binding.rvContent) {
			adapter = ContentsAdapter()
			setHasFixedSize(true)
		}

	}

	companion object {
		@JvmStatic
		fun newInstance() = ContentsFragment()
	}
}