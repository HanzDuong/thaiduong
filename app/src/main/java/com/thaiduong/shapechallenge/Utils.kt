package com.thaiduong.shapechallenge

import android.animation.TimeInterpolator
import android.content.Context
import android.graphics.*
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.thaiduong.shapechallenge.repo.model.ShapeEntity
import kotlin.random.Random

class Utils {
    companion object {
        lateinit var mDisplayMetrics: DisplayMetrics
        var mScreenWidth: Int = 0
        var mScreenHeight: Int = 0

        fun initScreenDimension(windowManager: WindowManager) {
            mDisplayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(mDisplayMetrics)
            mScreenHeight = mDisplayMetrics.heightPixels;
            mScreenWidth = mDisplayMetrics.widthPixels;
        }

        fun getRadius(): Int {
            val ran: Int = Random.nextInt(10, 45)
            return mScreenWidth * ran / 100
        }

        fun getRandomColor(): Int {
            return Color.argb(
                255,
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
        }

        fun getBitmapRect(radius: Int): Bitmap {
            val width = radius * 2
            val bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = getPaint()
            paint.color = getRandomColor()
            val rect = Rect(0, 0, width, width)
            canvas.drawRect(rect, paint)
            return bitmap
        }

        fun getPaint(shapeEntity: ShapeEntity): Paint {
            val paint = Paint()
            paint.color = shapeEntity.color
            paint.style = Paint.Style.FILL
            paint.isAntiAlias = true
            paint.isDither = true
            return paint
        }

        fun getPaint(): Paint {
            val paint = Paint()
            paint.style = Paint.Style.FILL
            paint.isAntiAlias = true
            paint.isDither = true
            return paint
        }

        fun getImageView(context: Context, bitmap: Bitmap?, point: PointF): ImageView {
            val imageView = ImageView(context)
            imageView.setImageBitmap(bitmap)
            if (bitmap == null) return imageView
            val radius = bitmap.width / 2
            val layoutParam: RelativeLayout.LayoutParams =
                RelativeLayout.LayoutParams(radius * 2, radius * 2)
            layoutParam.setMargins((point.x - radius).toInt(), (point.y - radius).toInt(), 0, 0)
            imageView.layoutParams = layoutParam
            return imageView
        }

        fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
            return Bitmap.createScaledBitmap(
                bitmap,
                width,
                height,
                false
            )
        }

        fun addViewAnimation(imageView: ImageView) {
            imageView.alpha = 0.0f
            val imageAnimator = imageView.animate()
            imageAnimator.setDuration(300).alpha(1f).setInterpolator(LinearInterpolator())
                .start()
        }

    }
}