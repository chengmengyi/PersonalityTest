package com.personalitytest.individuality.page.answer_result

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.personalitytest.individuality.R
import com.personalitytest.individuality.adapter.AnswerResultViewpagerAdapter
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.bean.TypeBean
import com.personalitytest.individuality.helper.CheckTestResultHelper
import com.personalitytest.individuality.helper.AllTypeHelper
import com.personalitytest.individuality.helper.show
import com.personalitytest.individuality.page.all_type.AllTypeActivity
import kotlinx.android.synthetic.main.answer_result_layout.*

class AnswerResultActivity:BaseActivity() {
    private val fragmentList= arrayListOf<Fragment>()
    private val titleList= arrayOf("Characteristics","Integration","Something You Like to Do")

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
}