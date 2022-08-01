package com.personalitytest.individuality.app

import android.app.Application
import com.personalitytest.individuality.helper.ActivityCallback
import com.personalitytest.individuality.helper.AllTypeHelper
import com.personalitytest.individuality.helper.ReadFirebaseConfig
import com.personalitytest.individuality.upload_log.AssembleLogDataHelper
import com.personalitytest.individuality.upload_log.UploadHelper
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