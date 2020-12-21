package com.thaiduong.shapechallenge.repo.api

import com.thaiduong.shapechallenge.repo.api.response.ResponseColor
import com.thaiduong.shapechallenge.repo.api.response.ResponseImage
import retrofit2.http.GET
import retrofit2.http.Url

interface ShapeApi {
    companion object {
        const val HOST = "http://www.colourlovers.com/"
        const val HOST_COLOR = "http://www.colourlovers.com/api/colors/random?format=json"
        const val HOST_IMAGE = "http://www.colourlovers.com/api/patterns/random?format=json"
    }

    @GET
    suspend fun getColor(@Url url: String): List<ResponseColor>

    @GET
    suspend fun getImage(@Url url: String): List<ResponseImage>
}