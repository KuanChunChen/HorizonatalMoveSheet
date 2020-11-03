package k.c.horizontal.move.sheet.horizontalmovesheet.wrapper

import android.content.Intent
import android.webkit.URLUtil
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClientWrapper(webView: WebView) : WebViewClient(){

    init {

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        webSettings.loadWithOverviewMode = true
        webView.webViewClient =  this
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        view?.scrollTo(0,0);
        view?.clearHistory()


    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

        if (URLUtil.isNetworkUrl(url)) {
            return false
        }

        if (url.startsWith("intent")) {

            val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
            view.context.startActivity(intent)
            return true

        }

        return true

    }


}