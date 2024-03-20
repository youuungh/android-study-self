package com.example.infiniterecycler

import com.google.gson.annotations.SerializedName

data class Data(
    val data: DataList
)

data class DataList(
    val list: ArrayList<Content>
)

data class Content(
    @SerializedName("title")
    val title: String,
    @SerializedName("created_at")
    val date: String
)