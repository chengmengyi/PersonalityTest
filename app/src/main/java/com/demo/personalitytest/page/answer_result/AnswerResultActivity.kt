package com.demo.personalitytest.page.answer_result

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.demo.personalitytest.R
import com.demo.personalitytest.adapter.AnswerResultViewpagerAdapter
import com.demo.personalitytest.admob.AdmobImplManager
import com.demo.personalitytest.admob.NativeAdHelper
import com.demo.personalitytest.base.BaseActivity
import com.demo.personalitytest.bean.TypeBean
import com.demo.personalitytest.helper.CheckTestResultHelper
import com.demo.personalitytest.helper.AllTypeHelper
import com.demo.personalitytest.helper.show
import com.demo.personalitytest.page.all_type.AllTypeActivity
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