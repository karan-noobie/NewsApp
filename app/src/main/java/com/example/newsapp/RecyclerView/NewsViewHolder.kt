package com.example.newsapp.RecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.newsTextView)
    val authorView: TextView = itemView.findViewById(R.id.authorTextView)
    val image: ImageView = itemView.findViewById(R.id.newsImageView)
}