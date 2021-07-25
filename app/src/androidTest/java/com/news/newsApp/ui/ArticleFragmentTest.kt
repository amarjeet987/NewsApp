package com.news.newsApp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.news.newsApp.R
import com.news.newsApp.getOrAwaitValueAndroidTest
import com.news.newsApp.repository.FakeNewsApiRepositoryAndroid
import com.news.newsApp.ui.utils.RecyclerViewAdapter
import com.news.newsApp.ui.utils.clickItemWithId
import com.news.newsApp.viewmodels.NewsApiViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleFragmentTest: KoinTest {
    private val viewModel by inject<NewsApiViewModel>()
    private val repository by inject<FakeNewsApiRepositoryAndroid>()

    @get:Rule
    var executionRule = InstantTaskExecutorRule()

    @Before
    fun initialize() {
        ActivityScenario.launch(MainActivity::class.java)
        repository.setNetworkErrorHappened(false)

        // click on the first item in recyclerview to go to articlesFragment
        onView(withId(R.id.newsView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.ViewHolder>(
                    0, clickItemWithId(R.id.card)
                )
            )
    }

    @Test
    fun viewIsShowingCorrectArticle() = runBlockingTest {

        // get first item from viewModel
        val itemTitle = viewModel.newsData.getOrAwaitValueAndroidTest()[0].title

        // check if article_view element from fragment_article is visible
        onView(withText(itemTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun backButtonTakesToHome() {

        // click the back button
        onView(withId(R.id.back)).perform(click())

        // check if article_view element from fragment_home is visible
        onView(withId(R.id.home_view))
            .check(matches(isDisplayed()))
    }
}




















