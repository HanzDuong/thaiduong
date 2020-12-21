package com.thaiduong.shapechallenge.viewmodel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thaiduong.shapechallenge.Utils
import com.thaiduong.shapechallenge.repo.ShapeRepoImpl
import com.thaiduong.shapechallenge.repo.api.response.ResponseColor
import com.thaiduong.shapechallenge.repo.model.ShapeEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShapeViewModel : ViewModel() {
    private var responseColor: ResponseColor? = null
    private var responseImage: Bitmap? = null
    fun getShapeEntity(): ShapeEntity {
        return ShapeEntity(1, getColor(), getImage())
    }

    init {
        viewModelScope.launch {
            ShapeRepoImpl.getInstance().getShapeColor().collect { shapeColors ->
                responseColor = shapeColors[0]
            }
        }

        viewModelScope.launch {
            ShapeRepoImpl.getInstance().getShapeImage().collect { shapeImages ->
                responseImage = shapeImages[0]
            }
        }
    }

    private fun getColor(): Int {
        return if (responseColor == null) {
            Utils.getRandomColor()
        } else {
            Color.parseColor("#" + responseColor!!.color)
        }
    }

    private fun getImage(): Bitmap? {
        return responseImage
    }
}