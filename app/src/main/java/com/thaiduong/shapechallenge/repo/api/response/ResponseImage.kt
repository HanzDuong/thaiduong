package com.thaiduong.shapechallenge.repo.api.response

import com.google.gson.annotations.SerializedName

data class ResponseImage(
    @SerializedName("imageUrl") val imageUrl: String
)