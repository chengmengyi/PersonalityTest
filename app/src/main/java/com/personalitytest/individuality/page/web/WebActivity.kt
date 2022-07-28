package com.personalitytest.individuality.page.web

import android.webkit.WebView
import android.webkit.WebViewClient
import com.personalitytest.individuality.R
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.helper.Config
import kotlinx.android.synthetic.main.web_layout.*

class WebActivity:BaseActivity() {

    override fun layoutId(): Int = R.layout.web_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()
        web_view.loadUrl(Config.URL)
        web_view.webViewClient=webViewClient

        iv_back.setOnClickListener { finish() }
    }

    private val webViewClient=object : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            web_view.loadUrl(url?:"")
            return true
        }
    }
}