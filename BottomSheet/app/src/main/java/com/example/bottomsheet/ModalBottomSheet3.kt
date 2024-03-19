package com.example.bottomsheet

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomsheet.databinding.ModalBottomSheet3Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

object ModalBottomSheet3 : BottomSheetDialogFragment() {

    @SuppressLint("StaticFieldLeak")
    lateinit var binding: ModalBottomSheet3Binding

    private val itemList = arrayListOf(
        Item(R.drawable.ic_default, "1"),
        Item(R.drawable.ic_default, "2"),
        Item(R.drawable.ic_default, "3")
    )

    private val itemAdapter = ItemAdapter(itemList) { position ->
        handleItemClick(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ModalBottomSheet3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.sort) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    private fun handleItemClick(position: Int) {
        dismiss()
    }
}