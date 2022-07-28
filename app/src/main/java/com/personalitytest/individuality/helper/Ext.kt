package com.personalitytest.individuality.helper

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show(show:Boolean){
    visibility=if (show) View.VISIBLE else View.GONE
}

fun Context.showToast(string: String){
    Toast.makeText(this,string, Toast.LENGTH_LONG).show()
}