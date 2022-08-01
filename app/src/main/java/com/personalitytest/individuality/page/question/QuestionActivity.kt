package com.personalitytest.individuality.page.question

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.personalitytest.individuality.R
import com.personalitytest.individuality.adapter.QuestionFragmentAdapter
import com.personalitytest.individuality.admob.AdmobImplManager
import com.personalitytest.individuality.admob.FullAdHelper
import com.personalitytest.individuality.admob.NativeAdHelper
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.eventbus.EventbusBean
import com.personalitytest.individuality.eventbus.EventbusCode
import com.personalitytest.individuality.helper.AllTypeHelper
import com.personalitytest.individuality.helper.show
import com.personalitytest.individuality.page.answer_result.AnswerResultActivity
import kotlinx.android.synthetic.main.question_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

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