package com.news.newsApp.repository

import androidx.lifecycle.LiveData
import com.news.newsApp.data.models.ArticleData


interface NewsApiRepositoryInterface {
    suspend fun getHeadlines(country: String = "us"): String?
    fun observeHeadlines() : LiveData<List<ArticleData.Article>>
}