package com.news.newsApp

import android.app.Application
import com.news.newsApp.di.fakeTestModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppTest: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppTest)
            modules(fakeTestModule)
        }
    }
}