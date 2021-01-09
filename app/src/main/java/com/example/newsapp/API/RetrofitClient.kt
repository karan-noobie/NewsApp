package com.example.newsapp.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitClient {

    @GET("/v2/top-headlines?apiKey=f4be304a75bb49ab8713022d2da68e87")
    fun getNews(
        @Query("country") country: String?,
        @Query("category") category: String?
    ): Call<NewsData>

}