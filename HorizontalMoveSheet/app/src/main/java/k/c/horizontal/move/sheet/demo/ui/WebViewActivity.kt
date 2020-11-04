/**
 * Created by Elegant Access's KC on 11/4/20 12:14 PM.
 * Copyright (c) 2020 All rights reserved.
 * Your support is my biggest motivation , please follow my Instagram  : https://www.instagram.com/eleg.aces.kc/
 * See more project on github : https://github.com/KuanChunChen
 * See tutorial on my site : https://medium.com/@elegant-access-kc
 */

package k.c.horizontal.move.sheet.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import k.c.horizontal.move.sheet.demo.R
import k.c.horizontal.move.sheet.horizontalmovesheet.wrapper.WebViewClientWrapper
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val startURL = intent.getStringExtra("START_URL")
        val startType = intent.getStringExtra("START_TYPE")

        supportActionBar?.title = startType
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        WebViewClientWrapper(web_view)
        web_view.loadUrl(startURL!!)
    }

    override fun onSupportNavigateUp(): Boolean {

        if (web_view.canGoBack()) {
            web_view.goBack()
            return false
        }
        finish()
        return true
    }




}