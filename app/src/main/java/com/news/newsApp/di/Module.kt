package com.news.newsApp.di

import androidx.room.Room
import com.news.newsApp.data.ArticlesDb
import com.news.newsApp.network.ApiClient
import com.news.newsApp.network.NewsApi
import com.news.newsApp.repository.NewsApiRepository
import com.news.newsApp.utils.HEADLINES_DB_NAME
import com.news.newsApp.viewmodels.NewsApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsApiViewModel(get<NewsApiRepository>()) }
}
val repositoryModule = module {
    single { NewsApiRepository(get(), get()) }
}
val apiModule = module {
    single { ApiClient.defaultBankClient.create(NewsApi::class.java) }
}
val newsArticleDaoModule = module {
    single { get<ArticlesDb>().getArticleDao() }
}
val roomDbModule = module {
    single { Room.databaseBuilder(get(), ArticlesDb::class.java, HEADLINES_DB_NAME).fallbackToDestructiveMigration().build() }
}