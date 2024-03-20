package com.example.infiniterecycler

import android.util.Log
import retrofit2.Response

class Repository {
    suspend fun getContent(page: Int): Response<Data> {
        val response = RetrofitObject.provideApi().getContentData(page.toString())
        if (response.isSuccessful) {
            Log.d("Repository", "Content 연결 성공")
        } else {
            Log.d("Repository", "Content 연결 실패")
        }
        return response
    }
}