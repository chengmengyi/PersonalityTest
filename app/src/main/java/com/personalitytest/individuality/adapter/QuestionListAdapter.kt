package com.personalitytest.individuality.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personalitytest.individuality.R
import com.personalitytest.individuality.eventbus.EventbusBean
import com.personalitytest.individuality.eventbus.EventbusCode
import kotlinx.android.synthetic.main.question_item_layout.view.*

class QuestionListAdapter(private val context:Context,private val list:ArrayList<String>):RecyclerView.Adapter<QuestionListAdapter.MyView>() {
    private var chooseIndex=-1

    inner class MyView(view:View):RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                chooseIndex=layoutPosition
                notifyDataSetChanged()
                EventbusBean(EventbusCode.CHOOSE_ANSWER_FINISH,str = if (chooseIndex==0) "A" else "B").send()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView =
        MyView(LayoutInflater.from(context).inflate(R.layout.question_item_layout,parent,false))

    override fun onBindViewHolder(holder: MyView, position: Int) {
        with(holder.itemView){
            val select = chooseIndex == position
            item_layout.isSelected=select
            tv_question_num.isSelected=select
            tv_question_content.isSelected=select

            tv_question_content.text=list[position]
            tv_question_num.text=if (position==0) "A" else "B"
        }
    }

    override fun getItemCount(): Int = list.size
}