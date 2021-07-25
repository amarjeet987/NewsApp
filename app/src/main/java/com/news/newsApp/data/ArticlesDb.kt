package com.news.newsApp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.news.newsApp.data.daos.ArticleDao
import com.news.newsApp.data.models.ArticleData

@Database(
    entities = [ArticleData.Article::class],
    version = 4,
    exportSchema = false
)
abstract class ArticlesDb: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}