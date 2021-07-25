package com.news.newsApp.network

import com.news.newsApp.BuildConfig
import com.news.newsApp.utils.API_KEY_PARAM
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private lateinit var retrofitForBank: Retrofit
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor {
                val newUrl = it.request().url().newBuilder()
                    .addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
                    .build()
                it.proceed(it.request().newBuilder().url(newUrl).build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build() }

    private fun initializeClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()
    }

    val defaultBankClient: Retrofit
        get() {
        if (!this::retrofitForBank.isInitialized) {
            retrofitForBank = initializeClient()
        }
        return retrofitForBank
    }
}
