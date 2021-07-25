package com.news.newsApp.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.newsApp.data.models.ArticleData

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleData.Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(article: List<ArticleData.Article>)

    @Query("DELETE FROM articles")
    fun clearTable()

    @Query("SELECT * FROM articles")
    fun observeArticles() : LiveData<List<ArticleData.Article>>
}