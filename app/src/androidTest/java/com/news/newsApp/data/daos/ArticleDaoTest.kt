package com.news.newsApp.data.daos

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.news.newsApp.getOrAwaitValueAndroidTest
import com.news.newsApp.sample.dummyArticleAndroidTest
import com.news.newsApp.sample.dummyArticlesAndroidTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest: KoinTest {

    private val dao by inject<ArticleDao>()

    @get:Rule
    var executionRule = InstantTaskExecutorRule()

    @Before
    fun initialization() {
        dao.clearTable()
    }

    @Test
    fun insertItemToDb() = runBlockingTest {
        dao.insertArticle(dummyArticleAndroidTest)
        val articles = dao.observeArticles().getOrAwaitValueAndroidTest()
        assertThat(articles).contains(dummyArticleAndroidTest)
    }

    @Test
    fun deleteAllItemsFromDb() = runBlockingTest {
        dao.clearTable()
        val articles = dao.observeArticles().getOrAwaitValueAndroidTest()
        assertThat(articles.size).isEqualTo(0)
    }

    @Test
    fun insertItemsToDb() = runBlockingTest {
        dao.insertArticles(dummyArticlesAndroidTest)
        val articles = dao.observeArticles().getOrAwaitValueAndroidTest()
        Log.d("DATA_CHECK", "${articles.map { it.title }}")
        Log.d("DATA_CHECK", "${dummyArticlesAndroidTest.map { it.title }}")
        assertThat(articles.size).isEqualTo(dummyArticlesAndroidTest.size)
    }
}