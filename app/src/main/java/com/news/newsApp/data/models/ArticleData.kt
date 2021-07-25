package com.news.newsApp.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ArticleData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {
    @Entity(tableName = "articles")
    data class Article(
        @PrimaryKey
        val title: String,
        val author: String?,
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val url: String?,
        val urlToImage: String?,
        @Embedded
        val source: Source?
    ) {
        data class Source(
            val id: String?,
            val name: String?
        )
    }
}