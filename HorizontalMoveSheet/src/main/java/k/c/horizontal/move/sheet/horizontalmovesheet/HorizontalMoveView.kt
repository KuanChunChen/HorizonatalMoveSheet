/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.CenterLayoutManager
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.SpaceItemDecoration
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.SwitchRecyclerScrollerListener
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.SwitchRecyclerViewAdapter
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.model.SwitchViewModel
import k.c.horizontal.move.sheet.horizontalmovesheet.wrapper.WebViewClientWrapper
import kotlinx.android.synthetic.main.partial_bottom_card.view.*


class HorizontalMoveView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    var bottomBehavior: BottomSheetBehavior<View>

    private var builder: Builder



    init{

        LayoutInflater.from(context).inflate(R.layout.partial_bottom_card, this, true)
        (context as Activity).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        bottomBehavior = BottomSheetBehavior.from(frame_bottom_sheet)

        bottomBehavior.apply {
            addBottomSheetCallback(object :BottomSheetBehavior.BottomSheetCallback(){
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    setImageAnimate(alpha = slideOffset)
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {

                }
            })
        }

        builder = Builder(this).create()

    }

    fun setImageAnimate(alpha: Float) {
        builder.imageIconView.alpha = alpha
    }
    fun setModel(switchViewModelList: MutableList<SwitchViewModel>){
        builder.listSwitchViewModel = switchViewModelList
    }

    fun show() {
        builder.show()
    }

    open class Builder(private var horizontalMoveView: HorizontalMoveView){

        private val itemSpace: Int = 9
        private var currentPosition :Int = 0

        private var centerLayoutManager : CenterLayoutManager
        private val spaceItemDecoration = SpaceItemDecoration(horizontalMoveView.context, itemSpace)
        private val switchRecyclerView = horizontalMoveView.findViewById<RecyclerView>(R.id.switchView)
        open val imageIconView = horizontalMoveView.findViewById<ImageView>(R.id.image_icon)
        private val webView = horizontalMoveView.findViewById<WebView>(R.id.webView_container)


        private var switchRecyclerViewAdapter :SwitchRecyclerViewAdapter


        private val snapHelper = PagerSnapHelper()

        lateinit var listSwitchViewModel: MutableList<SwitchViewModel>

        init {
            snapHelper.attachToRecyclerView(switchRecyclerView)
            centerLayoutManager = CenterLayoutManager(horizontalMoveView.context, LinearLayoutManager.HORIZONTAL, false)
            switchRecyclerViewAdapter =
                object : SwitchRecyclerViewAdapter() {
                    override fun onItemViewClick(position: Int, itemView: View) {

                        /**
                         * Do something after click.
                         * */
                        val movePosition = position - currentPosition
                        switchRecyclerView.smoothScrollBy(movePosition * spaceItemDecoration.mItemConsumeX, 0)



                    }
                }

            switchRecyclerView.adapter = switchRecyclerViewAdapter
            switchRecyclerView.layoutManager = centerLayoutManager
            switchRecyclerView.addItemDecoration(spaceItemDecoration)
            WebViewClientWrapper(webView)

        }

        @NonNull
        fun create(): Builder = this

        @NonNull
        fun show():HorizontalMoveView{

            switchRecyclerViewAdapter.reset(listSwitchViewModel)

            currentPosition = switchRecyclerView.adapter!!.itemCount / 2

            val offset = spaceItemDecoration.sideVisibleWidth - itemSpace

            switchRecyclerView.post{

                val galleryScrollerListener = object : SwitchRecyclerScrollerListener(currentPosition, spaceItemDecoration.mItemConsumeX) {
                    override fun changeView(position: Int) {
                        currentPosition = position

                        /***
                         * Do something here.
                         */
                        Glide.with(horizontalMoveView.context)
                            .load(listSwitchViewModel[position].imageIcon).into(imageIconView)
                        webView.goBack()
                        webView.loadUrl(listSwitchViewModel[position].url!!)
                    }

                }

                switchRecyclerView.addOnScrollListener(galleryScrollerListener)
                galleryScrollerListener.setItemAnim(switchRecyclerView, currentPosition)

            }

            centerLayoutManager.scrollToPositionWithOffset(currentPosition, offset)

            return horizontalMoveView
        }

    }

}