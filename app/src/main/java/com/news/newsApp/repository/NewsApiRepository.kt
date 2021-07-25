package com.news.newsApp.repository

import android.accounts.NetworkErrorException
import com.news.newsApp.data.daos.ArticleDao
import com.news.newsApp.data.models.ArticleData
import com.news.newsApp.data.models.ArticleDataErr
import com.news.newsApp.network.NewsApi
import com.news.newsApp.utils.GENERAL_ERROR
import com.news.newsApp.utils.NetworkUtils

class NewsApiRepository(
    private val newsApi: NewsApi,
    private val dbDao: ArticleDao
): NewsApiRepositoryInterface {

    @Throws(NetworkErrorException::class)
    override suspend fun getHeadlines(country: String): String? {
        try {
            val data = NetworkUtils.callApiAndReturnResponse<ArticleData> { newsApi.getTopHeadlines(country) }
            data.second?.let {
                // clear old data
                dbDao.clearTable()
                // add new data
                dbDao.insertArticles(it.articles)
            }
        } catch (e: NetworkErrorException) {
            val err = NetworkUtils.ifJsonThenParse<ArticleDataErr>(e.message)
            if(e.message != GENERAL_ERROR && err!=null) {
                return err.message
            }
            return e.message
        }
        return null
    }

    override fun observeHeadlines() = dbDao.observeArticles()
}