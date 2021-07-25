package com.news.newsApp.di

import com.news.newsApp.repository.FakeNewsApiRepository
import com.news.newsApp.viewmodels.NewsApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fakeViewModelModule = module {
    viewModel { NewsApiViewModel(get<FakeNewsApiRepository>()) }
}

val fakeRepositoryModule = module {
    single { FakeNewsApiRepository() }
}