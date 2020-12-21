package com.thaiduong.shapechallenge.view

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ShapePagerAdapter(private val mViewList: List<View>) :
    PagerAdapter() {
    override fun getCount(): Int {
        return mViewList.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = mViewList[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Circles"
            1 -> "Squares"
            2 -> "Triangles"
            else -> "All"
        }
    }

}