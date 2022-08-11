package com.personalitytest.individuality.page.answer_result

import com.personalitytest.individuality.R
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.bean.TypeBean
import kotlinx.android.synthetic.main.result_open_fragment_layout.*

class OpenResultActivity:BaseActivity(){

    override fun layoutId(): Int = R.layout.result_open_fragment_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()
        setInfo()

        iv_back.setOnClickListener { finish() }
    }

    private fun setInfo(){
        val typeBean = intent.getSerializableExtra("bean") as TypeBean
        val content = intent.getStringExtra("content")
        iv_tab_title.text=intent.getStringExtra("tab_title")
        tv_title.text=typeBean.title
        tv_result_content.text=content
        iv_top_bg.setImageResource(typeBean.topResId)
    }
}