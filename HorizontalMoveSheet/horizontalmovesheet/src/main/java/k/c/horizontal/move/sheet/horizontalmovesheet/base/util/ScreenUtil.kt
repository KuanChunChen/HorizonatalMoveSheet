/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet.base.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log

class ScreenUtil{



    /**
     * Covert dp to px
     * @param dp
     * @param context
     * @return pixel
     */
    fun convertDpToPixel(dp: Float, context: Context?): Float {
        return dp * getDensity(context)
    }

    /**
     * Covert px to dp
     * @param px
     * @param context
     * @return dp
     */
    fun convertPixelToDp(px: Float, context: Context): Float {
        return px / getDensity(context)
    }

    /**
     * 取得螢幕密度
     * 120dpi = 0.75
     * 160dpi = 1 (default)
     * 240dpi = 1.5
     * @param context
     * @return
     */
    private fun getDensity(context: Context?): Float {
        val metrics: DisplayMetrics = if (context != null) context.resources.displayMetrics else Resources.getSystem().displayMetrics
        Log.d("ttest","$metrics.densityDpi.toFloat()")

        return  (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}