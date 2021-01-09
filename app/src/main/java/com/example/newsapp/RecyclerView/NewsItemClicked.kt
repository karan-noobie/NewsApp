package com.example.newsapp.RecyclerView

import com.example.newsapp.API.News

interface NewsItemClicked {
    fun onItemClicked(item: News)
}