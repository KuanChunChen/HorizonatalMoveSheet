/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet.widget

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import k.c.horizontal.move.sheet.horizontalmovesheet.base.util.ScreenUtil
import kotlin.math.roundToInt

class SpaceItemDecoration(private val context: Context, private var mSpace: Int) : RecyclerView.ItemDecoration() {




    var mItemConsumeX = ScreenUtil().convertDpToPixel(70F, context).roundToInt() + mSpace * 2

    var sideVisibleWidth = (context.resources.displayMetrics.widthPixels - ScreenUtil().convertDpToPixel(70F,context).roundToInt()) / 2 - ScreenUtil().convertDpToPixel(28F,context).roundToInt()


    /**
     *
     * Adjust the recycler distance between each item.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)
        val metric = context.resources.displayMetrics

        val layoutParams = view.layoutParams as RecyclerView.LayoutParams

        val width = metric.widthPixels
        val height = metric.heightPixels
        val density = metric.density
        val densityDpi = metric.densityDpi



        val itemWidth : Int = ScreenUtil().convertDpToPixel(70F,context).roundToInt()
        if (layoutParams.width != itemWidth) {
            layoutParams.width = itemWidth
        }


        if (parent.getChildAdapterPosition(view) == 0 ) {

            outRect.left = sideVisibleWidth
            outRect.right = mSpace



        }


        if (parent.getChildAdapterPosition(view) == itemCount - 1) {
            outRect.left = mSpace

            outRect.right = sideVisibleWidth

        }

        if (parent.getChildAdapterPosition(view) != itemCount - 1 && parent.getChildAdapterPosition(view) != 0) {
            outRect.left = mSpace
            outRect.right = mSpace

        }



    }




}