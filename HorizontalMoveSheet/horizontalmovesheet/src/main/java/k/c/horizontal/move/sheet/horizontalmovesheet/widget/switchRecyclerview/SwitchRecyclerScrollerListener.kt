package k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.c.horizontal.move.sheet.horizontalmovesheet.R

abstract class SwitchRecyclerScrollerListener(private var mPosition: Int, private val itemWith: Int) : RecyclerView.OnScrollListener() {


    private var scrolledWidth = 0


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        Log.d("test","dx : $dx , dy : $dy")
        setScrollInfo(recyclerView, dx)

    }

    abstract fun changeView(position: Int)


    init {
    }


    fun updatePosition(currentPosition: Int) {
        mPosition = currentPosition
        scrolledWidth = 0

    }


    private fun setScrollInfo(recyclerView: RecyclerView, dx: Int) {
        scrolledWidth += dx

        // 位置移動數值(-1 - 0 - 1) =（單一物件距離(-(單一物件長度)-(單一物件長度) / 單一物件長度
        val offset = scrolledWidth.toFloat() / itemWith.toFloat()

        val percent = if (offset > 0) {
            offset - offset.toInt()
        } else {
            1f + offset
        }

        var movementPosition = 0
        if (offset > 0) {
            if ((offset.toInt() > 0)) {
                mPosition += offset.toInt()
                scrolledWidth -= itemWith
                movementPosition = mPosition
            } else {
                movementPosition += mPosition
            }

        } else if (offset < 0) {
            movementPosition--
            if (offset.toInt() < 0) {
                mPosition += offset.toInt()
                scrolledWidth += itemWith
                movementPosition = mPosition
            } else {
                movementPosition += mPosition
            }
        }

        Log.d("test22", movementPosition.toString())
        setItemAnim(recyclerView, movementPosition, percent)


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



    fun setItemAnim(recyclerView: RecyclerView, position: Int, percent: Float) {

        val centerView = recyclerView.layoutManager!!.findViewByPosition(position)
        val rightView = recyclerView.layoutManager!!.findViewByPosition(position + 1)
        val leftView = recyclerView.layoutManager!!.findViewByPosition(position - 1)
        val doubleRightView = recyclerView.layoutManager!!.findViewByPosition(position + 2)
        val doubleLeftView = recyclerView.layoutManager!!.findViewByPosition(position - 2)



        if (doubleRightView != null) {

            setTextViewStyles(
                doubleRightView!!.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#00000000")),
                floatArrayOf(0.0f, 0.5f),
                false
            )
        }

        if (doubleLeftView != null) {

            setTextViewStyles(
                doubleLeftView!!.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#00000000"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )

        }


        if (leftView != null) {

            setTextViewStyles(
                leftView!!.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )
        }

        if (rightView != null) {

            setTextViewStyles(
                rightView!!.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )
        }

        if (centerView != null) {

            setTextViewStyles(
                centerView!!.findViewById(R.id.text_title),
                intArrayOf(Color.parseColor("#000000"), Color.parseColor("#000000")),
                floatArrayOf(0.5f, 1.0f),
                true
            )
        }

        changeView(position)
    }



}
