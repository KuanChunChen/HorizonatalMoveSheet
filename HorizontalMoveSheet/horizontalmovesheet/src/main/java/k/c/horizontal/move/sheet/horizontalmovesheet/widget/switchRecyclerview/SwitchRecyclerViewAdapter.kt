/**
 * Created by Elegant Access's KC on 11/4/20 12:13 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.c.horizontal.move.sheet.horizontalmovesheet.R
import k.c.horizontal.move.sheet.horizontalmovesheet.base.BaseListAdapter
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.model.SwitchViewModel


abstract class SwitchRecyclerViewAdapter : BaseListAdapter<SwitchViewModel, SwitchRecyclerViewAdapter.ViewHolder>() {




    override val itemViewLayout: Int get() = R.layout.item_text


    override fun getItemViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.bind(position)
    }

    inner class ViewHolder(var mItemView: View) : BaseViewHolder(mItemView) {

        private var textTitle : TextView = mItemView.findViewById(R.id.text_title)

        override fun bind(position: Int) {

            textTitle.text = getItem(position % contentItemCount).textTitle

            mItemView.setOnClickListener {
                onItemViewClick(position, mItemView)
            }


        }


    }

    abstract fun onItemViewClick(position: Int, itemView: View)
}
