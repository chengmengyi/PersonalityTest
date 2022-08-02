package com.demo.personalitytest.bean

class ConfigAdmobBean(
    val source:String,
    val id:String,
    val type:String,
    val sort:Int,
) {
    override fun toString(): String {
        return "ConfigAdmobBean(source='$source', id='$id', type='$type', sort=$sort)"
    }
}