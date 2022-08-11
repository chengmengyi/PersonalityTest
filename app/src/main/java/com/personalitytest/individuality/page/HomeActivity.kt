package com.personalitytest.individuality.page

import android.content.Intent
import android.net.Uri
import android.view.Gravity
import com.personalitytest.individuality.R
import com.personalitytest.individuality.admob.AdmobImplManager
import com.personalitytest.individuality.admob.NativeAdHelper
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.helper.Config
import com.personalitytest.individuality.helper.a
import com.personalitytest.individuality.helper.showToast
import com.personalitytest.individuality.page.all_type.AllTypeActivity
import com.personalitytest.individuality.page.question.QuestionActivity
import com.personalitytest.individuality.page.web.WebActivity
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.android.synthetic.main.home_layout.top_view

class HomeActivity:BaseActivity() {
    private val homeNativeAdHelper by lazy { NativeAdHelper(AdmobImplManager.getHomeNativeImpl()) }

    override fun layoutId(): Int = R.layout.home_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()
        setOnClickListener()
    }

    private fun setOnClickListener(){
        cl_test.setOnClickListener {
            if (!drawer_layout.isOpen){
                startActivity(Intent(this,QuestionActivity::class.java))
            }
        }

        cl_type.setOnClickListener {
            if (!drawer_layout.isOpen){
                startActivity(Intent(this,AllTypeActivity::class.java))
            }
        }

        iv_set.setOnClickListener {

            drawer_layout.openDrawer(Gravity.LEFT)
        }

        ll_privacy.setOnClickListener { startActivity(Intent(this,WebActivity::class.java)) }

        ll_contact.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data= Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, Config.EMAIL)
                startActivity(intent)
            }catch (e:Exception){
                showToast("Contact us by email：${Config.EMAIL}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (refreshNativeAd){
            homeNativeAdHelper.onLoadAd(this)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        homeNativeAdHelper.onDestroy()
    }
}