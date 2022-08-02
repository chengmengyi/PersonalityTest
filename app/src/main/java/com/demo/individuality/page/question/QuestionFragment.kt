package com.demo.individuality.page.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.individuality.R
import com.demo.individuality.adapter.QuestionListAdapter
import kotlinx.android.synthetic.main.question_fragment_layout.*

class QuestionFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.question_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=QuestionListAdapter(context,arguments?.getStringArrayList("list")?: arrayListOf())
        }
    }
}