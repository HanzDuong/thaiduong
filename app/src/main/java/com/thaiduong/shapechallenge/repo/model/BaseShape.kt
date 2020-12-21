package com.thaiduong.shapechallenge.repo.model

import android.graphics.Bitmap

interface BaseShape {
    fun getBitmap(shapeEntity: ShapeEntity): Bitmap
}