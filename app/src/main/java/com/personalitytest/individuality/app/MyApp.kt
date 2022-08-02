package com.personalitytest.individuality.app

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.personalitytest.individuality.helper.AllTypeHelper

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        AllTypeHelper.readQuestionConf()
        Firebase.initialize(this)
    }
}