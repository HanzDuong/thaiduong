package com.thaiduong.shapechallenge.repo.model

class ShapeFactory {
    companion object {
        const val TYPE_CIRCLE: Int = 1
        const val TYPE_SQUARE: Int = 2
        const val TYPE_TRIANGLE: Int = 3
        fun createShape(shapeEntity: ShapeEntity): BaseShape {
            return when (shapeEntity.type) {
                TYPE_CIRCLE -> return SCircle()
                TYPE_SQUARE -> return SSquare()
                else -> STriangle()
            }
        }
    }
}