package com.demo.individuality.eventbus

import org.greenrobot.eventbus.EventBus

class EventbusBean(
    val code:Int,
    var str:String=""
) {

    fun send(){
        EventBus.getDefault().post(this)
    }
}