package com.demo.individuality.helper

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.demo.individuality.BuildConfig

fun printLog(s:String){
    if (BuildConfig.DEBUG){
        Log.e("qwer",s)
    }
}

fun View.show(show:Boolean){
    visibility=if (show) View.VISIBLE else View.GONE
}

fun Context.showToast(string: String){
    Toast.makeText(this,string, Toast.LENGTH_LONG).show()
}