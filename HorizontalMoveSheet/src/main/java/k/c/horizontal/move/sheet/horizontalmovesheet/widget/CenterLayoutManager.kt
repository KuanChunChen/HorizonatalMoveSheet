/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet.widget

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CenterLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean)
    : LinearLayoutManager(context, orientation, reverseLayout) {


    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {

        val smoothScroller = CenterSmoothScroller(recyclerView.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }


    private class CenterSmoothScroller internal constructor(context: Context) :
        LinearSmoothScroller(context) {

        companion object { private const val SMOOTH_SPEED_PER_PIXEL = 1f }

        /**
         * 控制滑動速度
         */
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return SMOOTH_SPEED_PER_PIXEL / displayMetrics.densityDpi
        }


    }

}