package com.personalitytest.individuality.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personalitytest.individuality.R
import com.personalitytest.individuality.bean.TypeBean
import com.personalitytest.individuality.page.answer_result.AnswerResultActivity
import kotlinx.android.synthetic.main.all_type_item_layout.view.*

class AllTypeAdapter(
    private val ctx:Context,
    private val list:ArrayList<TypeBean>
):RecyclerView.Adapter<AllTypeAdapter.MyView>() {
    inner class MyView(view:View):RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                val intent = Intent(ctx, AnswerResultActivity::class.java)
                intent.putExtra("bean",list[layoutPosition])
                (ctx as Activity).startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView =
        MyView(LayoutInflater.from(ctx).inflate(R.layout.all_type_item_layout,parent,false))

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val typeBean = list[position]
        with(holder.itemView){
            iv_cover.setImageResource(typeBean.coverResId)
            iv_title.setImageResource(typeBean.titleResId)
        }
    }

    override fun getItemCount(): Int = list.size
}