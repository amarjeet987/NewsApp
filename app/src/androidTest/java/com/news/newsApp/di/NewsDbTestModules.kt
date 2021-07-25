package com.news.newsApp.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.news.newsApp.data.ArticlesDb
import com.news.newsApp.repository.FakeNewsApiRepositoryAndroid
import com.news.newsApp.viewmodels.NewsApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fakeTestModule = module {
    viewModel { NewsApiViewModel(get<FakeNewsApiRepositoryAndroid>()) }
    single { FakeNewsApiRepositoryAndroid() }
    single { get<ArticlesDb>().getArticleDao() }
    single { Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        ArticlesDb::class.java).allowMainThreadQueries().build() }
}
