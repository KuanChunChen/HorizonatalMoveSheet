package k.c.horizontal.move.sheet.horizontalmovesheet.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior


class CustomBottomSheetBehaviour <V : ViewGroup>(context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<V>(context, attrs) {


    override fun onDependentViewChanged(parent: CoordinatorLayout, child: V, dependency: View): Boolean {
        if (isBottomSheet(dependency)) {
        Log.e("dependency","yesssss")
        }else{
            Log.e("tttt","child")

        }


        return false
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: V, ev: MotionEvent): Boolean {
        return false
    }



    private fun isBottomSheet(view: View): Boolean {
        val layoutParams = view.layoutParams
        return if (layoutParams is CoordinatorLayout.LayoutParams) {
            layoutParams.behavior is BottomSheetBehavior<*>
        } else false
    }

}