package com.demo.personalitytest.page.all_type

import androidx.recyclerview.widget.GridLayoutManager
import com.demo.personalitytest.R
import com.demo.personalitytest.adapter.AllTypeAdapter
import com.demo.personalitytest.admob.AdmobImplManager
import com.demo.personalitytest.admob.NativeAdHelper
import com.demo.personalitytest.base.BaseActivity
import com.demo.personalitytest.helper.AllTypeHelper
import kotlinx.android.synthetic.main.all_type_layout.*

class AllTypeActivity:BaseActivity() {
    private val allTypeHelper by lazy { NativeAdHelper(AdmobImplManager.getAllTypeNativeImpl(),loadAdCover = false) }

    override fun layoutId(): Int = R.layout.all_type_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()
        setAdapter()
    }

    private fun setAdapter(){
        recycler_view.apply {
            layoutManager=GridLayoutManager(this@AllTypeActivity,3)
            adapter=AllTypeAdapter(this@AllTypeActivity,AllTypeHelper.getAllTypeList())
        }
    }

    override fun onResume() {
        super.onResume()
        allTypeHelper.onLoadAd(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        allTypeHelper.onDestroy()
    }
}