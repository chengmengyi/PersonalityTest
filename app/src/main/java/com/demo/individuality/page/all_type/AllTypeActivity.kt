package com.demo.individuality.page.all_type

import androidx.recyclerview.widget.GridLayoutManager
import com.demo.individuality.R
import com.demo.individuality.adapter.AllTypeAdapter
import com.demo.individuality.admob.AdmobImplManager
import com.demo.individuality.admob.NativeAdHelper
import com.demo.individuality.base.BaseActivity
import com.demo.individuality.helper.AllTypeHelper
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