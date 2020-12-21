package com.thaiduong.shapechallenge.view

import android.content.Context
import android.graphics.PointF
import com.thaiduong.shapechallenge.Utils
import com.thaiduong.shapechallenge.repo.model.ShapeFactory
import kotlin.random.Random

class ViewAll(context: Context) : ViewBaseShape(context) {

    override fun drawViewAtPosition(x: Float, y: Float) {
        val shapeEntity = mViewModel.getShapeEntity()
        val randomStyle: Int = Random.nextInt()
        val shapeType = when (randomStyle % 3) {
            0 -> ShapeFactory.TYPE_CIRCLE
            1 -> ShapeFactory.TYPE_SQUARE
            else -> ShapeFactory.TYPE_TRIANGLE
        }
        shapeEntity.type = shapeType
        val shape = ShapeFactory.createShape(shapeEntity)
        val bitmap = shape.getBitmap(shapeEntity)
        val imageView = Utils.getImageView(context, bitmap, PointF(x, y))
        mViewGroup.addView(imageView)
        Utils.addViewAnimation(imageView)
    }
}