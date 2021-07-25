package com.news.newsApp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.newsApp.data.models.ArticleData
import com.news.newsApp.repository.NewsApiRepositoryInterface

class NewsApiViewModel(
    private val repository: NewsApiRepositoryInterface
) : ViewModel() {

    private val mErrorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = mErrorMessage

    private val mNewsData = repository.observeHeadlines()
    val newsData: LiveData<List<ArticleData.Article>> = mNewsData

    suspend fun getNewsHeadlines() {
        val res = repository.getHeadlines()
        res?.let { mErrorMessage.postValue(res) }
    }
}