package com.example.infiniterecycler

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("notices")
    suspend fun getContentData(@Query("page") page: String): Response<Data>

}
