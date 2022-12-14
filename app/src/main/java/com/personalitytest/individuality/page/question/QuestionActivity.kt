package com.personalitytest.individuality.page.question

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.personalitytest.individuality.R
import com.personalitytest.individuality.adapter.QuestionFragmentAdapter
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.helper.AllTypeHelper
import com.personalitytest.individuality.helper.show
import com.personalitytest.individuality.page.answer_result.AnswerResultActivity
import kotlinx.android.synthetic.main.question_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class QuestionActivity:BaseActivity() {
    private val answerMap= hashMapOf<Int,String>()

    private val fragmentList= arrayListOf<Fragment>()

    override fun layoutId(): Int = R.layout.question_layout

    override fun initView() {
        immersionBar?.statusBarView(top_view)?.init()
        EventBus.getDefault().register(this)

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
                val intent = Intent(this, AnswerResultActivity::class.java)
                intent.putExtra("choose",true)
                intent.putExtra("result",answerMap)
                startActivity(intent)
                finish()
            }else{
                viewpager.currentItem+=1
            }
        }
    }

    @Subscribe
    fun onEvent(answer:String) {
        answerMap[viewpager.currentItem] = answer
        showOrHideNextBtn(viewpager.currentItem)
    }

    private fun showOrHideNextBtn(position:Int){
        answerMap[position].isNullOrEmpty().let { tv_next.show(!it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}