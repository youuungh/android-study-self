package com.example.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("android:src")
    @JvmStatic
    fun loadImg(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .circleCrop()
            .into(view)
    }

    @BindingAdapter("itemList")
    @JvmStatic
    fun setItemList(view: RecyclerView, items: Any) {
        view.adapter = ItemAdapter(items as List<Item>)
    }
}