package com.demo.personalitytest.bean

import java.io.Serializable

data class TypeBean(
    val title:String,
    val coverResId:Int,
    val titleResId:Int,
    var topResId:Int,
    var type:Int,
    var characteristics:String="",
    var integration:String="",
    var something:String="",
):Serializable {
}