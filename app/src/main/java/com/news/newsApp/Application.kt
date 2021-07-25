package com.news.newsApp

import android.app.Application
import com.news.newsApp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@App)
            modules(listOf(
                apiModule,
                roomDbModule,
                newsArticleDaoModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}