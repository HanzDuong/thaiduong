package com.thaiduong.shapechallenge.repo.model

import android.graphics.*
import android.util.Log
import com.thaiduong.shapechallenge.Utils
import kotlin.random.Random

class STriangle : BaseShape {
    override fun getBitmap(shapeEntity: ShapeEntity): Bitmap {
        val randomStyle: Boolean = Random.nextBoolean()
        return if (randomStyle) {
            //draw by color
            getBitmapColor(shapeEntity)
        } else {
            //draw by image
            getBitmapImage(shapeEntity)
        }
    }

    private fun getBitmapImage(shapeEntity: ShapeEntity): Bitmap {
        val radius = Utils.getRadius()
        val bitmap: Bitmap? = getBitmapServer(shapeEntity, radius)
        val bitmapTriangle = getBitmapColor(shapeEntity)
        val scaleBitmap = Utils.resizeBitmap(bitmap!!, radius * 2, radius * 2)

        val canvas = Canvas(bitmapTriangle)
        val paint = Utils.getPaint()
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(scaleBitmap, 0.0f, 0.0f, paint)
        return bitmapTriangle
    }

    private fun getBitmapServer(shapeEntity: ShapeEntity, radius: Int): Bitmap {
        return if (shapeEntity.imageBitmap != null) shapeEntity.imageBitmap!!
        else Utils.getBitmapRect(radius)
    }

    private fun getBitmapColor(shapeEntity: ShapeEntity): Bitmap {
        val radius = Utils.getRadius()
        val diameter = radius * 2
        val paint = Utils.getPaint(shapeEntity)

        val bitmap = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        // draw triangle

        val path = Path()
        val x = radius.toFloat()
        val y = x
        path.moveTo(x, y - radius) // Top
        path.lineTo(x - radius, y + radius) // Bottom left
        path.lineTo(x + radius, y + radius) // Bottom right
        path.lineTo(x, y - radius) // Back to Top
        path.close()
        canvas.drawPath(path, paint)
        Log.e("hanz", "hanz shapeEntity.color $shapeEntity.color + $bitmap")
        return bitmap
    }
}