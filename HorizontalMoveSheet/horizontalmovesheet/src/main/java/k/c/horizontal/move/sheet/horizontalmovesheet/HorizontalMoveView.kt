package k.c.horizontal.move.sheet.horizontalmovesheet

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
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

class HorizontalMoveView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private lateinit var centerLayoutManager : CenterLayoutManager

    lateinit var bottomBehavior: BottomSheetBehavior<View>

    private var builder: Builder


    init{

        LayoutInflater.from(context).inflate(R.layout.partial_bottom_card, this, true)

//        initRecyclerView()
//        bottomBehavior = BottomSheetBehavior.from(frame_bottom_sheet)
        builder = Builder(this).create()
        builder.setSwitchViewText(listOf("testst","dsddfasdfa"))
        builder.show()
    }

    fun setText(textList: List<String>) {

        builder.setSwitchViewText(textList).show()
    }


    open class Builder(private var horizontalMoveView: HorizontalMoveView){

        private var centerLayoutManager : CenterLayoutManager

        private val spaceItemDecoration = SpaceItemDecoration(horizontalMoveView.context, 9)
        private val switchRecyclerView = horizontalMoveView.findViewById<RecyclerView>(R.id.switchView)
        private val imageIconView = horizontalMoveView.findViewById<ImageView>(R.id.image_icon)
        private val btnConfirm = horizontalMoveView.findViewById<Button>(R.id.btn_confirm)


        private val switchRecyclerViewAdapter =
            object : SwitchRecyclerViewAdapter() {
                override fun onItemViewClick(position: Int, itemView: View) {

//                    centerLayoutManager.smoothScrollToPosition(switchView, RecyclerView.State(), position)
//                    centerLayoutManager.scrollToPositionWithOffset(position,spaceItemDecoration.sideVisibleWidth)

//                    switchRecyclerViewAdapter.notifyItemChanged(position, 1)
//                    testLoadView(position,switchViewList)
                }
            }


        private val snapHelper = PagerSnapHelper()

        lateinit var listOfText: List<String>

        init {
            snapHelper.attachToRecyclerView(switchRecyclerView)
            centerLayoutManager = CenterLayoutManager(horizontalMoveView.context, LinearLayoutManager.HORIZONTAL, false)
            switchRecyclerView.adapter = switchRecyclerViewAdapter
            switchRecyclerView.layoutManager = centerLayoutManager
            switchRecyclerView.addItemDecoration(spaceItemDecoration)

        }

        @NonNull
        fun create(): Builder = this



        @NonNull
        fun setSwitchViewText(@NonNull listOfText: List<String>): Builder {

            this.listOfText = listOfText
            return this
        }



        @NonNull
        fun show():HorizontalMoveView{

            val defaultValueList = mutableListOf<SwitchViewModel>()

            defaultValueList.add(SwitchViewModel(listOfText[0], "輸入下車點", R.drawable.google_plus_100))
            defaultValueList.add(SwitchViewModel(listOfText[1], "輸入乘客資訊", R.drawable.gradient_line_100))
            switchRecyclerViewAdapter.reset(defaultValueList)




            val currentPosition = switchRecyclerView.adapter!!.itemCount / 2
            val offset = spaceItemDecoration.sideVisibleWidth



            centerLayoutManager.scrollToPositionWithOffset(currentPosition,offset)


            switchRecyclerView.post{

                val galleryScrollerListener = object : SwitchRecyclerScrollerListener(spaceItemDecoration.mItemConsumeX) {
                    override fun changeView(position: Int) {

                        Glide.with(horizontalMoveView.context).load(defaultValueList[position].imageIcon).into(imageIconView)
                        btnConfirm.text = defaultValueList[position].buttonText

                    }

                }


                switchRecyclerView.addOnScrollListener(galleryScrollerListener)

                galleryScrollerListener.setItemAnim(switchRecyclerView,currentPosition,0f)

                galleryScrollerListener.updatePosition(currentPosition)

            }


            btnConfirm.setOnClickListener {

            }

            return horizontalMoveView
        }


    }

}