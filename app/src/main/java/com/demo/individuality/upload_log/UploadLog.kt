package com.demo.individuality.upload_log

import android.content.Context
import android.util.Log
import com.demo.individuality.helper.printLog
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

object UploadLog {
//    const val url="http://rooftreefor-49fe3d29ea784ffc.elb.us-east-1.amazonaws.com/rooftree/for/kamikaze"
    const val url="https://mainland.aabrw.com/woodrow/knack/lens"

//   https://mainland.aabrw.com/woodrow/knack/lens

    fun init(context: Context){
        requestGet("https://ip.seeip.org/geoip/") {
            GlobalScope.launch(Dispatchers.IO) {
                val ip = getIp(it)
                CreateInstallData.installReferrerClient(context,ip)
                uploadSession(context,ip)
            }
        }
    }

    private fun uploadSession(context: Context,ip:String){
        val jsonObject = CreateBaseData.createBaseData(context,ip)
        jsonObject.put("compost",JSONObject())
        upload(context,ip,jsonObject,false)
    }

    private fun requestGet(url:String,result:(json:String)-> Unit){
        OkGo.get<String>(url).execute(object : StringCallback(){
            override fun onSuccess(response: Response<String>?) {
                result(response?.body().toString())
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }
        })
    }

    fun upload(context: Context, ip:String,jsonObject: JSONObject, install:Boolean){
        val path = "$url?lithium=$ip&cant=${CreateBaseData.cant()}"

        OkGo.post<String>(path)
            .retryCount(2)
            .headers("content-type","application/json")
            .headers("cant", CreateBaseData.cant())
            .headers("sparling", CreateBaseData.sparling(context))
            .headers("lithium",ip)
            .upJson(jsonObject)
            .execute(object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    if (install&&jsonObject.optString("ordinate").isNotEmpty()){
                        MMKV.defaultMMKV().encode("install",true)
                    }
//                    Log.e("qweraaa","=onSuccess==${response?.code()}===${response?.message()}===${response?.body()}==")
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
//                    Log.e("qweraaa","=onError==${response?.code()}===${response?.message()}=")
                }
            })
    }

    private fun getIp(json:String):String{
        try {
            if (json.isNullOrEmpty()){
                return ""
            }
            val jsonObject=JSONObject(json)
            return jsonObject.optString("ip")
        }catch (e:Exception){}
        return ""
    }
}