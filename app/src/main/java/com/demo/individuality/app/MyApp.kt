package com.demo.individuality.app

import android.app.Application
import com.demo.individuality.helper.ActivityCallback
import com.demo.individuality.helper.AllTypeHelper
import com.demo.individuality.helper.ReadFirebaseConfig
import com.demo.individuality.upload_log.AssembleLogDataHelper
import com.demo.individuality.upload_log.UploadHelper
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