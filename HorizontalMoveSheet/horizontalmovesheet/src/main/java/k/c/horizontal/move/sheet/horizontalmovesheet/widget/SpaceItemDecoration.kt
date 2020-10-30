package k.c.horizontal.move.sheet.horizontalmovesheet.widget

import android.content.Context
import android.graphics.Rect
import android.util.Log
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

        val lp = view.layoutParams as RecyclerView.LayoutParams

        val width = metric.widthPixels     // 螢幕寬度（畫素）
        val height = metric.heightPixels   // 螢幕高度（畫素）
        val density = metric.density      // 螢幕密度（0.75 / 1.0 / 1.5）
        val densityDpi = metric.densityDpi  // 螢幕密度DPI（120 / 160 / 240）

//        Log.d("my screen : "," width :$width, height :$height, density :$density, densityDpi:$densityDpi,")
//
//        Log.d("my screen : "," lp : ${lp.width}")


        val itemWidth : Int = ScreenUtil().convertDpToPixel(70F,context).roundToInt()
        if (lp.width != itemWidth) {
            lp.width = itemWidth
        }


        if (parent.getChildAdapterPosition(view) == 0 ) {

            outRect.left = (width / 2) - (itemWidth / 2) - ScreenUtil().convertDpToPixel(28F,context).roundToInt()
            outRect.right = mSpace

            Log.d("screen", outRect.left.toString())
            Log.d("screen", outRect.right.toString())


        }


        if (parent.getChildAdapterPosition(view) == itemCount - 1) {
            outRect.left = mSpace

            outRect.right = (width / 2) - (itemWidth/ 2) - ScreenUtil().convertDpToPixel(28F,context).roundToInt()

        }

        if (parent.getChildAdapterPosition(view) != itemCount - 1 && parent.getChildAdapterPosition(view) != 0) {
            outRect.left = mSpace
            outRect.right = mSpace

        }



    }




}