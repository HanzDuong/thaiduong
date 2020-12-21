package com.thaiduong.shapechallenge.view

import android.content.Context
import android.graphics.PointF
import com.thaiduong.shapechallenge.Utils
import com.thaiduong.shapechallenge.repo.model.ShapeFactory

class ViewTriangle(context: Context) : ViewBaseShape(context) {

    override fun drawViewAtPosition(x: Float, y: Float) {
        val shapeEntity = mViewModel.getShapeEntity()
        shapeEntity.type = ShapeFactory.TYPE_TRIANGLE
        val shape = ShapeFactory.createShape(shapeEntity)
        val bitmap = shape.getBitmap(shapeEntity)
        val imageView = Utils.getImageView(context, bitmap, PointF(x, y))
        mViewGroup.addView(imageView)
        Utils.addViewAnimation(imageView)
    }
}