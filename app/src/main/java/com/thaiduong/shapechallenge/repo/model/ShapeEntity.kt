package com.thaiduong.shapechallenge.repo.model

import android.graphics.Bitmap

data class ShapeEntity(
    var type: Int,
    var color: Int,
    var imageBitmap: Bitmap?
)