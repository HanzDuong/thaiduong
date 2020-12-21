package com.thaiduong.shapechallenge.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.thaiduong.shapechallenge.R
import com.thaiduong.shapechallenge.viewmodel.ShapeViewModel

open class ViewBaseShape(context: Context) : FrameLayout(context) {
    private lateinit var mRootView: View
    protected lateinit var mViewGroup: ViewGroup
    protected lateinit var mViewModel: ShapeViewModel

    init {
        inflateLayout(context)
    }

    open fun setViewModel(viewModel: ShapeViewModel) {
        mViewModel = viewModel
    }

    open fun inflateLayout(context: Context?) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_base, this, false)
        mViewGroup = mRootView.findViewById(R.id.rootContainer)
        addView(mRootView)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            drawViewAtPosition(event.x, event.y)
        }
        return true
    }

    open fun drawViewAtPosition(x: Float, y: Float) {
    }

    open fun clearAllView() {
        mViewGroup.removeAllViews()
    }
}