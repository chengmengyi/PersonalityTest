package com.demo.personalitytest.page.question

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.demo.personalitytest.R
import com.demo.personalitytest.adapter.QuestionFragmentAdapter
import com.demo.personalitytest.admob.AdmobImplManager
import com.demo.personalitytest.admob.FullAdHelper
import com.demo.personalitytest.admob.NativeAdHelper
import com.demo.personalitytest.base.BaseActivity
import com.demo.personalitytest.eventbus.EventbusBean
import com.demo.personalitytest.eventbus.EventbusCode
import com.demo.personalitytest.helper.AllTypeHelper
import com.demo.personalitytest.helper.show
import com.demo.personalitytest.page.answer_result.AnswerResultActivity
import kotlinx.android.synthetic.main.question_layout.*

class QuestionActivity:BaseActivity() {
    private val answerMap= hashMapOf<Int,String>()
    private val fragmentList= arrayListOf<Fragment>()
    private val questionNativeAdHelper by lazy { NativeAdHelper(AdmobImplManager.getQuestionNativeImpl()) }
    private val questionFinishCpAdHelper by lazy {
        FullAdHelper(AdmobImplManager.getQuestionFinishCpNativeImpl(),isDoQuestion = true){
            jumpResult()
        }
    }

    override fun layoutId(): Int = R.layout.question_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()

        questionFinishCpAdHelper.callAd()

        AllTypeHelper.questionList.forEach {
            val bundle = Bundle()
            bundle.putStringArrayList("list",it)
            val questionFragment = QuestionFragment()
            questionFragment.arguments=bundle
            fragmentList.add(questionFragment)
        }

        viewpager.isUserInputEnabled=false
        viewpager.adapter = QuestionFragmentAdapter(fragmentList,this)
        viewpager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tv_question_index.text="${position+1}/36"
                showOrHideNextBtn(position)
            }
        })

        tv_next.setOnClickListener {
            if (viewpager.currentItem>=fragmentList.size-1){
                questionFinishCpAdHelper.preShowAd(this)
            }else{
                viewpager.currentItem+=1
            }
        }
    }

    private fun jumpResult(){
        val intent = Intent(this, AnswerResultActivity::class.java)
        intent.putExtra("choose",true)
        intent.putExtra("result",answerMap)
        startActivity(intent)
        finish()
    }

    override fun onEventMsg(bean: EventbusBean) {
        super.onEventMsg(bean)
        if (bean.code==EventbusCode.CHOOSE_ANSWER_FINISH){
            answerMap[viewpager.currentItem] = bean.str
            showOrHideNextBtn(viewpager.currentItem)
        }
    }

    private fun showOrHideNextBtn(position:Int){
        answerMap[position].isNullOrEmpty().let { tv_next.show(!it) }
    }

    override fun onResume() {
        super.onResume()
        questionNativeAdHelper.onLoadAd(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        questionNativeAdHelper.onDestroy()
        questionFinishCpAdHelper.onDestroy()
    }
}