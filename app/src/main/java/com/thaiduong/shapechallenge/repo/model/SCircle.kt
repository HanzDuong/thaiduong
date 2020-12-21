package com.thaiduong.shapechallenge.repo.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import com.thaiduong.shapechallenge.Utils

class SCircle : BaseShape {

    override fun getBitmap(shapeEntity: ShapeEntity): Bitmap {
        val radius = Utils.getRadius()
        val diameter = radius * 2
        val paint = Utils.getPaint(shapeEntity)

        val bitmap = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        // draw circle
        canvas.drawCircle(
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            paint
        )
        Log.e("hanz", "hanz shapeEntity.color $shapeEntity.color + $bitmap")

        return bitmap
    }

}