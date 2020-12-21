package com.thaiduong.shapechallenge.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofitBuilder {
    companion object {
        fun defaultBuilder(baseUrl: String, httpClient: OkHttpClient): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
        }
    }

    fun customBuilder() = Retrofit.Builder()
}