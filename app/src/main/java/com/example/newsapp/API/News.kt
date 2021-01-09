package com.example.newsapp.API

import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("articles")
    var news: ArrayList<News>,
) {
    operator fun get(i: Int): ArrayList<String?> =
        arrayListOf(news[i].title, news[i].author, news[i].url, news[i].urlToImage)
}

data class News(
    val title: String?,
    val author: String?,
    val url: String?,
    val urlToImage: String?,
)



