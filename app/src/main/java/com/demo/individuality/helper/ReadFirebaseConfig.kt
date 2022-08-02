package com.demo.individuality.helper

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.demo.individuality.BuildConfig
import com.tencent.mmkv.MMKV

object ReadFirebaseConfig {

    fun readConf(){
        if (BuildConfig.DEBUG){
            saveAdToLocal(Config.AD)
        }else{
            val remoteConfig = Firebase.remoteConfig
            remoteConfig.fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful){
                    saveAdToLocal(remoteConfig.getString("per_ad"))
                }
            }
        }
    }

    private fun saveAdToLocal(string:String){
        MMKV.defaultMMKV().encode("ad",string)
    }

    fun getLocalAdJson():String{
        val decodeString = MMKV.defaultMMKV().decodeString("ad")
        return if (decodeString.isNullOrEmpty()) Config.AD else decodeString
    }
}