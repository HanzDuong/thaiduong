package com.thaiduong.shapechallenge.repo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thaiduong.shapechallenge.repo.api.ShapeApiClient
import com.thaiduong.shapechallenge.repo.api.response.ResponseColor
import com.thaiduong.shapechallenge.repo.model.ShapeEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.URL


class ShapeRepoImpl : ShapeRepo {

    private val mLiveDataShape = MutableLiveData<ShapeEntity>()

    var mApi: ShapeApiClient = ShapeApiClient.getInstance()

    companion object {
        private var sInstance: ShapeRepo? = null

        fun getInstance(): ShapeRepo {
            if (sInstance == null) {
                sInstance = ShapeRepoImpl()
            }
            return sInstance as ShapeRepo
        }
    }

    override fun getShapeEntity(): LiveData<ShapeEntity> {
        return mLiveDataShape
    }

    override fun getShapeColor(): Flow<List<ResponseColor>> {
        return mLatestColor
    }

    override fun getShapeImage(): Flow<List<Bitmap>> {
        return mLatestImage
    }

    private var count: Int = 0
    private val mLatestColor: Flow<List<ResponseColor>> = flow {
        val refreshIntervalMs: Long = 100
        while (true) {
            try {
                val latestColor = mApi.loadColor()
                emit(latestColor) // Emits the result of the request to the flow
                delay(refreshIntervalMs) // Suspends the coroutine for some time
            } catch (e: Exception) {
            }
        }
    }

    private val mLatestImage: Flow<List<Bitmap>> = flow {
        val refreshIntervalMs: Long = 100
        while (true) {
            try {
                val latestImage = mApi.loadImage()
                val urlImage = URL(latestImage[0].imageUrl)
                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage.toBitmap()
                }
                val bitmaps = ArrayList<Bitmap>();
                val bitmap: Bitmap? = result.await()
                if (bitmap != null) {
                    bitmaps.add(bitmap)
                    emit(bitmaps)
                }
                delay(refreshIntervalMs)
            } catch (e: Exception) {
            }

        }
    }

    private fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }

}