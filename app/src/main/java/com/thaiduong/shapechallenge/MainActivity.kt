package com.thaiduong.shapechallenge

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thaiduong.shapechallenge.view.*
import com.thaiduong.shapechallenge.viewmodel.ShapeViewModel
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: ShapeViewModel
    private lateinit var rootView: ViewGroup
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerAdapter: ShapePagerAdapter
    private lateinit var mSensorManager: SensorManager
    private var mAccel = 0f
    private var mAccelCurrent = 0f
    private var mAccelLast = 0f

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Utils.initScreenDimension(windowManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.rootContainer)
        mViewModel = ViewModelProvider(this).get(ShapeViewModel::class.java)

        mTabLayout = findViewById(R.id.tabLayout)
        mViewPager = findViewById(R.id.viewpager)

        initData()
        initSensor()
    }

    private fun initSensor() {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensorManager.registerListener(
            mSensorListener,
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
        mAccel = 0.00f
        mAccelCurrent = SensorManager.GRAVITY_EARTH
        mAccelLast = SensorManager.GRAVITY_EARTH
    }

    private lateinit var mListView: List<View>
    private fun initData() {
        mListView = getListContentView()
        mPagerAdapter = ShapePagerAdapter(mListView)
        mTabLayout.setupWithViewPager(mViewPager)
        mViewPager.adapter = mPagerAdapter
    }

    private fun getListContentView(): List<View> {
        val listView = ArrayList<View>()

        val viewCircle = ViewCircle(this)
        viewCircle.setViewModel(mViewModel)
        listView.add(viewCircle)

        val viewSquare = ViewSquare(this)
        viewSquare.setViewModel(mViewModel)
        listView.add(viewSquare)

        val viewTriangle = ViewTriangle(this)
        viewTriangle.setViewModel(mViewModel)
        listView.add(viewTriangle)

        val viewAll = ViewAll(this)
        viewAll.setViewModel(mViewModel)
        listView.add(viewAll)

        return listView
    }

    private val mSensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(se: SensorEvent) {
            val x = se.values[0]
            val y = se.values[1]
            val z = se.values[2]
            mAccelLast = mAccelCurrent
            mAccelCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta = mAccelCurrent - mAccelLast
            mAccel = mAccel * 0.9f + delta // perform low-cut filter

            if (mAccel > 12) {
                removeAllShape()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    private fun removeAllShape() {
        for (view in mListView) {
            if (view is ViewBaseShape) {
                view.clearAllView()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(
            mSensorListener,
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        mSensorManager.unregisterListener(mSensorListener)
        super.onPause()
    }
}