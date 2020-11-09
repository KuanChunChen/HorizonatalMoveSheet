/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Typeface
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.c.horizontal.move.sheet.horizontalmovesheet.R

open class SwitchRecyclerScrollerListener(private var mPosition: Int, private val itemWith: Int) : RecyclerView.OnScrollListener() {


    private var scrolledWidth = 0


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        setScrollInfo(dx)

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        when (newState) {

            RecyclerView.SCROLL_STATE_IDLE -> {




                val offset = scrolledWidth.toFloat() / itemWith.toFloat()


                val moveTotalCount = offset.toInt()

                if (moveTotalCount != 0) {
                    mPosition += moveTotalCount
                    scrolledWidth -= itemWith * moveTotalCount
                    setItemAnim(recyclerView, mPosition)
                }


            }

        }
    }

    open fun changeView(position: Int) {}

    private fun setScrollInfo(dx: Int) {

        scrolledWidth += dx
    }


    private fun setTextViewStyles(textView: TextView, colors: IntArray, position :FloatArray, isBold :Boolean) {

        if (colors == null || position == null) {

            return
        }

        val mLinearGradient = LinearGradient(
            0f,
            0f,
            textView.paint.textSize * textView.text.length,
            0f,
            colors,
            position,
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = mLinearGradient
        textView.invalidate()


        if (isBold) {

            textView.setTypeface(textView.typeface, Typeface.BOLD)

        } else {
            textView.setTypeface(null, Typeface.NORMAL)

        }
    }



    fun setItemAnim(recyclerView: RecyclerView, position: Int) {

        val centerView = recyclerView.layoutManager!!.findViewByPosition(position)
        val rightView = recyclerView.layoutManager!!.findViewByPosition(position + 1)
        val leftView = recyclerView.layoutManager!!.findViewByPosition(position - 1)
        val doubleRightView = recyclerView.layoutManager!!.findViewByPosition(position + 2)
        val doubleLeftView = recyclerView.layoutManager!!.findViewByPosition(position - 2)



        if (doubleRightView != null) {

            setTextViewStyles(
                doubleRightView.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#00000000")),
                floatArrayOf(0.0f, 0.5f),
                false
            )
        }

        if (doubleLeftView != null) {

            setTextViewStyles(
                doubleLeftView.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#00000000"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )

        }


        if (leftView != null) {

            setTextViewStyles(
                leftView.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )
        }

        if (rightView != null) {

            setTextViewStyles(
                rightView.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )
        }

        if (centerView != null) {

            setTextViewStyles(
                centerView.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#000000"), Color.parseColor("#000000")),
                floatArrayOf(0.5f, 1.0f),
                true
            )
        }

        changeView(position)
    }



}
