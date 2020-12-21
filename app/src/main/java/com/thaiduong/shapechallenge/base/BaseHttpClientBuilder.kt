package com.thaiduong.shapechallenge.base

import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class BaseHttpClientBuilder {

    companion object {
        fun defaultBuilder(): OkHttpClient.Builder {
            val builder = customBuilder()
            val apiDispatcher = Dispatcher(ThreadManager.getInstance().networkPool)
            builder.dispatcher(apiDispatcher)
            return builder
        }

        private fun customBuilder() = OkHttpClient.Builder()
    }
}