package k.c.horizontal.move.sheet.horizontalmovesheet

import android.content.Context
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

        bottomBehavior = BottomSheetBehavior.from(frame_bottom_sheet)
        builder = Builder(this).create()

    }

    fun setModel(switchViewModelList: MutableList<SwitchViewModel>){
        builder.listSwitchViewModel = switchViewModelList
    }

    fun show() {
        builder.show()
    }

    class Builder(private var horizontalMoveView: HorizontalMoveView){

        private var centerLayoutManager : CenterLayoutManager

        private val spaceItemDecoration = SpaceItemDecoration(horizontalMoveView.context, 9)
        private val switchRecyclerView = horizontalMoveView.findViewById<RecyclerView>(R.id.switchView)
        private val imageIconView = horizontalMoveView.findViewById<ImageView>(R.id.image_icon)
        private val webView = horizontalMoveView.findViewById<WebView>(R.id.webView_container)

        private val switchRecyclerViewAdapter =
            object : SwitchRecyclerViewAdapter() {
                override fun onItemViewClick(position: Int, itemView: View) {

                    /**
                     * Do something after click.
                     * */
                }
            }



        private val snapHelper = PagerSnapHelper()

        lateinit var listSwitchViewModel: MutableList<SwitchViewModel>

        init {
            snapHelper.attachToRecyclerView(switchRecyclerView)
            centerLayoutManager = CenterLayoutManager(horizontalMoveView.context, LinearLayoutManager.HORIZONTAL, false)
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

            var currentPosition = switchRecyclerView.adapter!!.itemCount / 2
            Log.d("currentPosition"," : $currentPosition")
            val offset = spaceItemDecoration.sideVisibleWidth

            centerLayoutManager.scrollToPositionWithOffset(currentPosition,offset)

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
                galleryScrollerListener.setItemAnim(switchRecyclerView,currentPosition,0f)
                galleryScrollerListener.updatePosition(currentPosition)
            }


            return horizontalMoveView
        }

    }

}