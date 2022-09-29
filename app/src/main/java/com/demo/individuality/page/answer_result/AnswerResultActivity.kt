package com.demo.individuality.page.answer_result

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.demo.individuality.R
import com.demo.individuality.adapter.AnswerResultViewpagerAdapter
import com.demo.individuality.admob.AdmobImplManager
import com.demo.individuality.admob.NativeAdHelper
import com.demo.individuality.base.BaseActivity
import com.demo.individuality.bean.TypeBean
import com.demo.individuality.helper.CheckTestResultHelper
import com.demo.individuality.helper.AllTypeHelper
import com.demo.individuality.helper.show
import com.demo.individuality.page.all_type.AllTypeActivity
import kotlinx.android.synthetic.main.answer_result_layout.*

class AnswerResultActivity:BaseActivity() {
    private val fragmentList= arrayListOf<Fragment>()
    private val titleList= arrayOf("Characteristics","Integration","Something You Like to Do")

    private val answerResultHelper by lazy { NativeAdHelper(AdmobImplManager.getAnswerResultNativeImpl()) }

    override fun layoutId(): Int = R.layout.answer_result_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()
        getIntentData()

        tv_view_all.setOnClickListener {
            startActivity(Intent(this,AllTypeActivity::class.java))
        }
    }

    private fun getIntentData(){
        val choose = intent.getBooleanExtra("choose", false)
        lateinit var typeBean:TypeBean
        if (choose){
            val resultMap = intent.getSerializableExtra("result") as HashMap<Int, String>
            val check = CheckTestResultHelper.check(resultMap)
            typeBean=AllTypeHelper.getAllTypeList()[check-1]
        }else{
            typeBean=intent.getSerializableExtra("bean") as TypeBean
        }
        iv_logo.setImageResource(typeBean.coverResId)
        tv_title.text=typeBean.title
        tv_view_all.show(choose)

        for (index in titleList.indices){
            val bundle = Bundle()
            bundle.putInt("index",index)
            bundle.putSerializable("bean",typeBean)
            bundle.putString("tab_title",titleList[index])
            val answerResultFragment = AnswerResultFragment()
            answerResultFragment.arguments=bundle
            fragmentList.add(answerResultFragment)
        }

        viewpager.adapter=AnswerResultViewpagerAdapter(titleList,fragmentList,supportFragmentManager)
        tab_layout.setViewPager(viewpager)
    }

    override fun onResume() {
        super.onResume()
        answerResultHelper.onLoadAd(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        answerResultHelper.onDestroy()
    }
}