package com.demo.personalitytest.upload_log

import android.content.Context
import android.util.Log
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.demo.personalitytest.helper.printLog
import org.json.JSONArray

object UploadHelper {

    fun uploadLog(context: Context){
        AssembleLogDataHelper.assembleJson(context)
    }

    fun sendGet(url:String,result:(json:String)-> Unit={}){
        OkGo.get<String>(url).execute(object :StringCallback(){
            override fun onSuccess(response: Response<String>?) {
                result(response?.body().toString())
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                printLog("=onError===${response?.message()}")
            }
        })
    }

    fun sendPost(body:String){
        val jsonArray = JSONArray()
        jsonArray.put(body)
        OkGo.post<String>("https://aabrw.com/zimcki/?airatters=com.personalitytest.individuality&palaze=sailor")
            .retryCount(2)
            .headers("hasards","KVITXWQT")
            .headers("suments","circle")
            .headers("palaze","sailor")
            .headers("lectives","FYEZEO")
            .headers("doegations","787699")
            .headers("ororizations","android")
            .upJson(jsonArray)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    Log.e("qwer","=onSuccess==${response?.code()}===${response?.message()}===${response?.body()}==")
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    Log.e("qwer","=onError==${response?.code()}==")
                }
            })
    }
}