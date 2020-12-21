package com.thaiduong.shapechallenge.repo

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.thaiduong.shapechallenge.repo.api.response.ResponseColor
import com.thaiduong.shapechallenge.repo.model.ShapeEntity
import kotlinx.coroutines.flow.Flow

interface ShapeRepo {
    fun getShapeEntity(): LiveData<ShapeEntity>

    fun getShapeColor(): Flow<List<ResponseColor>>

    fun getShapeImage(): Flow<List<Bitmap>>
}