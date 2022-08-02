package com.demo.personalitytest.app

import android.app.Application
import com.demo.personalitytest.helper.ActivityCallback
import com.demo.personalitytest.helper.AllTypeHelper
import com.demo.personalitytest.helper.ReadFirebaseConfig
import com.demo.personalitytest.upload_log.AssembleLogDataHelper
import com.demo.personalitytest.upload_log.UploadHelper
import com.tencent.mmkv.MMKV


lateinit var myApp: MyApp
class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        myApp=this
        MMKV.initialize(this)
        AssembleLogDataHelper.getReferrer()
        AllTypeHelper.readQuestionConf()
        ReadFirebaseConfig.readConf()
        ActivityCallback.register(this)
        UploadHelper.uploadLog(this)
    }
}