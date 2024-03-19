package com.example.bottomsheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomsheet.databinding.ModalBottomSheet1Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet1 : BottomSheetDialogFragment() {

    private var _binding: ModalBottomSheet1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModalBottomSheet1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}