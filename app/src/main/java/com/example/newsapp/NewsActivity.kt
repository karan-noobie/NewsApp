package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.API.News
import com.example.newsapp.API.NewsData
import com.example.newsapp.API.RetrofitClient
import com.example.newsapp.RecyclerView.NewsItemClicked
import com.example.newsapp.RecyclerView.NewsListAdapter
import kotlinx.android.synthetic.main.activity_recycler.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recycler.layoutManager = LinearLayoutManager(this)
        mAdapter = NewsListAdapter(this)
        recycler.adapter = mAdapter


        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RetrofitClient::class.java)

        val category = intent.getStringExtra("category")
        api.getNews("in",category).enqueue(object : Callback<NewsData> {
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val News: NewsData = response.body()!!
                val newsArray = ArrayList<News>()
                for(i in 0 until News.news.size)
                {
                    val news = News(News.get(i)[0],
                        News.get(i)[1],
                        News.get(i)[2],
                        News.get(i)[3],)
                    newsArray.add(news)
                }

                mAdapter.updateNews(News.news)
            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onItemClicked(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

}