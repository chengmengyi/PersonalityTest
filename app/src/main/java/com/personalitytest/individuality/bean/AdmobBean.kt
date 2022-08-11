package com.personalitytest.individuality.bean

class AdmobBean(
    val ad:Any?=null,
    val time:Long=0L
) {

    fun isExpired() = (System.currentTimeMillis() - time) >= 1000L * 60L * 60L
}