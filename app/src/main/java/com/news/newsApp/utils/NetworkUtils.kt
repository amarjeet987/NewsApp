package com.news.newsApp.utils

import android.accounts.NetworkErrorException
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import retrofit2.Response
import java.lang.Exception

class NetworkUtils {
    companion object {

        @Throws(NetworkErrorException::class)
        inline fun <reified T> callApiAndReturnResponse(api: () -> Response<JsonElement>): Pair<String?, T?> {
            return try {
                val res = api()
                if(res.code() == 200) {
                    Pair(null, Gson().fromJson(res.body(), T::class.java))
                } else {
                    throw NetworkErrorException(res.errorBody()?.string() ?: GENERAL_ERROR)
                }
            } catch (e: Exception) {
                throw NetworkErrorException(e.message ?: GENERAL_ERROR)
            }
        }

        inline fun <reified T> ifJsonThenParse(str: String?): T? {
            if(str == null) return null
            return try {
                Gson().fromJson(str, T::class.java)
            } catch (e: JsonParseException) {
                null
            }
        }
    }
}