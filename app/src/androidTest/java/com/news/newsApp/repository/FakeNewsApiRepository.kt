package com.news.newsApp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.news.newsApp.data.models.ArticleData
import com.news.newsApp.sample.dummyArticlesAndroidTest
import com.news.newsApp.utils.GENERAL_ERROR

class FakeNewsApiRepositoryAndroid: NewsApiRepositoryInterface {
    private val articles = mutableListOf<ArticleData.Article>()
    private val observableArticles = MutableLiveData<List<ArticleData.Article>>(articles)
    private var networkErrorHappened = false

    fun setNetworkErrorHappened(hasError: Boolean) {
        networkErrorHappened = hasError
    }

    override suspend fun getHeadlines(country: String): String? {
        return if(networkErrorHappened) {
            GENERAL_ERROR
        } else {
            articles.clear()
            articles.addAll(dummyArticlesAndroidTest)
            observableArticles.postValue(articles)
            null
        }
    }

    override fun observeHeadlines(): LiveData<List<ArticleData.Article>> {
        return observableArticles
    }
}