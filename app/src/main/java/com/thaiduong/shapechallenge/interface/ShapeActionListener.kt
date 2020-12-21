package com.thaiduong.shapechallenge.`interface`

import com.thaiduong.shapechallenge.repo.api.response.ResponseColor

interface ShapeActionListener {
    fun onLoadColorSuccess(color: ResponseColor?)
    fun onLoadColorFail()
}