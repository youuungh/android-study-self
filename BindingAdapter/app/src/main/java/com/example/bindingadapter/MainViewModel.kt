package com.example.bindingadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

data class Item(
    var title: String,
    var msg: String
)

class MainViewModel: ViewModel() {

    private val _img = MutableLiveData<String>()
    val img: LiveData<String> = _img

    init {
        _img.value = "https://cdn.pixabay.com/photo/2024/02/28/07/42/european-shorthair-8601492_1280.jpg"
    }

    fun onChangeImage() {
        _img.value = "https://cdn.pixabay.com/photo/2024/03/04/20/22/dogs-8613175_1280.jpg"
    }

    private val _items = MutableLiveData<MutableList<Item>>()
    val items: LiveData<MutableList<Item>> = _items

    init {
        _items.value = mutableListOf(Item("1", "아이템 1"), Item("2", "아이템 2"))
    }

    fun addItem() {
        val itemList: MutableList<Item> = (_items.value ?: mutableListOf()).toMutableList()
        itemList.add(Item("New", Date().toString()))
        _items.value = itemList
    }
}