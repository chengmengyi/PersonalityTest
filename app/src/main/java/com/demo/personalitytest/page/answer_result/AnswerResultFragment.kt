package com.demo.personalitytest.page.answer_result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.personalitytest.R
import com.demo.personalitytest.bean.TypeBean
import kotlinx.android.synthetic.main.answer_result_fragment_layout.*

class AnswerResultFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.answer_result_fragment_layout,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeBean = arguments?.getSerializable("bean") as TypeBean
        iv_title.text=typeBean.title
        iv_logo.setImageResource(typeBean.coverResId)
        tv_result_content.text=getContentByIndex(typeBean)
        ll_read_open.setOnClickListener {
            val intent = Intent(context, OpenResultActivity::class.java)
            intent.putExtra("bean",typeBean)
            intent.putExtra("tab_title",arguments?.getString("tab_title"))
            intent.putExtra("content",tv_result_content.text.toString())
            startActivity(intent)
        }
    }

    private fun getContentByIndex(typeBean: TypeBean):String{
        var content=""
        when(arguments?.getInt("index", 0)){
            0->content=typeBean.characteristics
            1->content=typeBean.integration
            2->content=typeBean.something
        }
        return content
    }

}