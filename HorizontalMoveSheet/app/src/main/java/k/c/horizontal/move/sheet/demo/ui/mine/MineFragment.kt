/**
 * Created by Elegant Access's KC on 11/4/20 12:14 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.demo.ui.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import k.c.horizontal.move.sheet.demo.R
import k.c.horizontal.move.sheet.demo.constants.UrlType
import k.c.horizontal.move.sheet.demo.ui.WebViewActivity
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.partial_mine_top.*


class MineFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
        layout_ig.setOnClickListener(this)
        layout_github.setOnClickListener(this)
        layout_donate.setOnClickListener(this)
        button_home.setOnClickListener(this)
    }

    private fun startWebActivity(urlType: UrlType) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("START_TYPE", urlType.name)
        intent.putExtra("START_URL", urlType.value)
        requireActivity().startActivity(intent)

    }

    override fun onClick(view: View?)  {
        when (view?.id) {
            R.id.layout_ig -> startWebActivity(UrlType.A)
            R.id.layout_github -> startWebActivity(UrlType.Github)
            R.id.layout_donate -> startWebActivity(UrlType.Paypal)
            R.id.button_home ->  Navigation.findNavController(view).navigateUp()

        }
    }

}