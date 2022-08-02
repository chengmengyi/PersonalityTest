package com.demo.individuality.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AnswerResultViewpagerAdapter(
    private val titleList: Array<String>,
    private val fragmentList:ArrayList<Fragment>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = titleList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getPageTitle(position: Int): CharSequence = titleList[position]
}