package com.thaiduong.shapechallenge.repo.model

import android.graphics.Bitmap
import android.graphics.Canvas
import com.thaiduong.shapechallenge.Utils

class SSquare : BaseShape {
    override fun getBitmap(shapeEntity: ShapeEntity): Bitmap {
        val radius = Utils.getRadius()
        val bitmap = if (shapeEntity.imageBitmap != null) {
            shapeEntity.imageBitmap
        } else {
            Utils.getBitmapRect(radius)
        }
        return Utils.resizeBitmap(bitmap!!, radius, radius)
    }
}