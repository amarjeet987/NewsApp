package com.news.newsApp.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines/")
    suspend fun getTopHeadlines(@Query("country") country: String): Response<JsonElement>
}