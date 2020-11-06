/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet.widget

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import k.c.horizontal.move.sheet.horizontalmovesheet.R

class CenterLayoutManager : LinearLayoutManager {

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )


    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {

        Log.e("test","current position :$position")
        val smoothScroller = CenterSmoothScroller(recyclerView.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }


    private class CenterSmoothScroller internal constructor(context: Context) :
        LinearSmoothScroller(context) {

        companion object { private const val SMOOTH_SPEED_PER_PIXEL = 1f }

//        override fun calculateDtToFit(viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int): Int {
//            return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
//        }

        /**
         * 控制滑動速度
         */
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return SMOOTH_SPEED_PER_PIXEL / displayMetrics.densityDpi
        }


    }

}