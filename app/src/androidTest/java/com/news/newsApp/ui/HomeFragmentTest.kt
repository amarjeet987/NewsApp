package com.news.newsApp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.news.newsApp.R
import com.news.newsApp.getOrAwaitValueAndroidTest
import com.news.newsApp.repository.FakeNewsApiRepositoryAndroid
import com.news.newsApp.ui.utils.RecyclerViewAdapter
import com.news.newsApp.ui.utils.RecyclerViewItemCountAssertion
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
class HomeFragmentTest: KoinTest {
    private val viewModel by inject<NewsApiViewModel>()
    private val repository by inject<FakeNewsApiRepositoryAndroid>()

    @get:Rule
    var executionRule = InstantTaskExecutorRule()

    @Before
    fun initialize() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun liveDataElementsShowInRecyclerview() = runBlockingTest {
        repository.setNetworkErrorHappened(false)

        // get number of items in viewModel
        val nData = viewModel.newsData.getOrAwaitValueAndroidTest().size

        // compare to items showed in views
        onView(ViewMatchers.withId(R.id.newsView))
            .check(RecyclerViewItemCountAssertion(nData))
    }

    @Test
    fun onClickItemTakesToArticlePage() = runBlockingTest {
        repository.setNetworkErrorHappened(false)

        // click on an item
        onView(ViewMatchers.withId(R.id.newsView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.ViewHolder>(
                    0, clickItemWithId(R.id.card)
                )
            )

        // check if article_view element from fragment_article is visible
        onView(ViewMatchers.withId(R.id.article_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}