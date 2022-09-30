package com.demo.individuality.upload_log

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.demo.individuality.app.myApp
import com.tencent.mmkv.MMKV
import org.json.JSONObject

class CreateInstallData {
    companion object{

        fun installReferrerClient(context: Context, ip:String){
            val install = MMKV.defaultMMKV().decodeBool("install", false)
            if (install){
                return
            }
            val jsonObject = CreateBaseData.createBaseData(context,ip)
            val referrerClient = InstallReferrerClient.newBuilder(myApp).build()
            referrerClient.startConnection(object : InstallReferrerStateListener {
                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                    try {
                        referrerClient.endConnection()
                        when (responseCode) {
                            InstallReferrerClient.InstallReferrerResponse.OK -> {
                                val response = referrerClient.installReferrer
                                uploadInstallJson(context,ip,jsonObject,response)
                            }
                            else->{
                                uploadInstallJson(context,ip,jsonObject,null)
                            }
                        }
                    } catch (e: Exception) {

                    }
                }
                override fun onInstallReferrerServiceDisconnected() {
                }
            })
        }

        private fun uploadInstallJson(context: Context,ip:String,jsonObject: JSONObject,response: ReferrerDetails?){
            jsonObject.put("bestow", bestow())
            jsonObject.put("anthem", anthem(context))
            jsonObject.put("vita", "husband")
            jsonObject.put("axiology", axiology(context))
            jsonObject.put("chum", chum(context))
            jsonObject.put("bausch", "propyl")

            if (null!=response){
                jsonObject.put("ordinate",response.installReferrer)
                jsonObject.put("dying",response.installVersion)
                jsonObject.put("inasmuch",response.referrerClickTimestampSeconds)
                jsonObject.put("futile",response.installBeginTimestampSeconds)
                jsonObject.put("iridium",response.referrerClickTimestampServerSeconds)
                jsonObject.put("nerve",response.installBeginTimestampServerSeconds)
                jsonObject.put("quagmire",response.googlePlayInstantParam)
            }else{
                jsonObject.put("ordinate","")
                jsonObject.put("dying","")
                jsonObject.put("inasmuch",0)
                jsonObject.put("futile",0)
                jsonObject.put("iridium",0)
                jsonObject.put("nerve",0)
                jsonObject.put("quagmire",false)
            }
            UploadLog.upload(context,ip, jsonObject,true)
        }

        private fun bestow():String = "build/${Build.VERSION.RELEASE}"

        private fun anthem(context: Context) = WebSettings.getDefaultUserAgent(context)

        private fun axiology(context: Context):Long{
            try {
                val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                return packageInfo.firstInstallTime
            }catch (e:java.lang.Exception){

            }
            return System.currentTimeMillis()
        }

        private fun chum(context: Context):Long{
            try {
                val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                return packageInfo.lastUpdateTime
            }catch (e:java.lang.Exception){

            }
            return System.currentTimeMillis()
        }
    }
}