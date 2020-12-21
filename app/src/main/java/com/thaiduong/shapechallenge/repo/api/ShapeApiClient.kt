package com.thaiduong.shapechallenge.repo.api

import android.util.Log
import com.thaiduong.shapechallenge.base.BaseHttpClientBuilder
import com.thaiduong.shapechallenge.base.BaseRetrofitBuilder
import com.thaiduong.shapechallenge.repo.api.response.ResponseColor
import com.thaiduong.shapechallenge.repo.api.response.ResponseImage
import okhttp3.OkHttpClient

class ShapeApiClient {
    private var mApi: ShapeApi

    init {
        val client: OkHttpClient = BaseHttpClientBuilder.defaultBuilder().build()
        mApi = BaseRetrofitBuilder.defaultBuilder(ShapeApi.HOST, client)
            .build().create(ShapeApi::class.java)
    }

    companion object {
        private var sInstance: ShapeApiClient? = null

        fun getInstance(): ShapeApiClient {
            if (sInstance == null) {
                sInstance = ShapeApiClient()
            }
            return sInstance as ShapeApiClient
        }
    }

    suspend fun loadColor(): List<ResponseColor> {
        return mApi.getColor(ShapeApi.HOST_COLOR)
    }

    suspend fun loadImage(): List<ResponseImage> {
        return mApi.getImage(ShapeApi.HOST_IMAGE)
    }
}