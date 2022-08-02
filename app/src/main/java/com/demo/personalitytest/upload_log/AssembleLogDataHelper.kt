package com.demo.personalitytest.upload_log

import android.content.Context
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Base64
import android.webkit.WebSettings
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.demo.personalitytest.R

import com.demo.personalitytest.app.myApp
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.StringBuilder
import java.util.*

object AssembleLogDataHelper {
    fun assembleJson(context: Context){
        GlobalScope.launch(Dispatchers.IO) {
            val adLimit = getAdLimit(context)
            try {
                UploadHelper.sendGet("https://ip.seeip.org/geoip/"){
                    val finalJson= JSONObject()

                    val jigs=JSONObject()
                    jigs.put("maripe","recoercoat")
                    jigs.put("tyunications", getCountryCode(it))
                    jigs.put("buunk", "")
                    jigs.put("turremony", adLimit)

                    val lreas=JSONObject()
                    lreas.put("jigs",jigs)

                    finalJson.put("lreas",lreas)
                    finalJson.put("sickle",getSystemModel())
                    finalJson.put("enlgine", getVersionName())
                    finalJson.put("perear", getIp(it))
                    finalJson.put("houlltop", getUUID())
                    finalJson.put("aughs", getSystemVersion())
                    finalJson.put("plicants", System.currentTimeMillis())
                    finalJson.put("suments", "circle")
                    finalJson.put("tosts", context.getString(R.string.admob_id))
                    finalJson.put("peilians", getDefaultUserAgent(context))
                    finalJson.put("clails","${getLanguage()}-${getOsCountry()}")
                    finalJson.put("hssion",getOsCountry())
                    finalJson.put("fther", getAndroidID())
                    finalJson.put("diods", UUID.randomUUID().toString())
                    finalJson.put("prane", getReferrerJson())

                    val body = base64Json(finalJson.toString())
                    UploadHelper.sendPost(body)
                }

            }catch (e:Exception){}
        }
    }

    private fun base64Json(json: String):String{
        try {
            var strBase64 = Base64.encodeToString(json.toByteArray(), Base64.NO_WRAP)
            if (strBase64.endsWith("=")){
                strBase64=strBase64.substring(0,strBase64.length-1)
            }
            var stringBuilder = StringBuilder(strBase64)
            stringBuilder.insert(6,"EDfx2VEWYMyogV2132")
            stringBuilder.append("dOzU0MN0D0Gg638f00")
            stringBuilder.reverse()
            val substring = stringBuilder.substring(61, 78)
            stringBuilder.delete(61, 78)
            stringBuilder.insert(23,substring)
            return stringBuilder.toString()
        }catch (e:Exception){}
        return ""
    }

    private fun getReferrerJson():JSONObject{
        val raduy=JSONObject()
        raduy.put("raduy", getLocalRefer())
        return raduy
    }

    private fun getDefaultUserAgent(context: Context) = WebSettings.getDefaultUserAgent(context)

    private fun getCountryCode(json:String):String{
        try {
            val jsonObject=JSONObject(json)
            return jsonObject.optString("country_code")
        }catch (e:Exception) {

        }
        return ""
    }

    private fun getAdLimit(context: Context):Int{
        try {
            val limitAdTrackingEnabled = AdvertisingIdClient.getAdvertisingIdInfo(context.applicationContext).isLimitAdTrackingEnabled
            return if (limitAdTrackingEnabled) 1 else 0
        }catch (e:Exception){}
        return 0
    }

    private fun getLanguage():String{
        val locale = myApp.resources.configuration.locale
        return locale.language
    }

    private fun getOsCountry(): String {
        val tm = myApp.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.networkCountryIso.toUpperCase()
    }

    fun getVersionName():String{
        val packageInfo = myApp.packageManager.getPackageInfo(myApp.packageName, 0)
        return packageInfo.versionName?:""
    }

    fun getMobileSystemVersion()=android.os.Build.VERSION.SDK_INT

    private fun getSystemModel() = android.os.Build.BRAND

    private fun getSystemVersion() = android.os.Build.VERSION.RELEASE

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

    private fun getCountry(json:String):String{
        try {
            if (json.isNullOrEmpty()){
                return ""
            }
            val jsonObject=JSONObject(json)
            return jsonObject.optString("country")
        }catch (e:Exception){}
        return ""
    }

    private fun getUUID():String{
        val uuid = MMKV.defaultMMKV().decodeString("uuid")
        if (uuid.isNullOrEmpty()){
            val id= UUID.randomUUID().toString()
            MMKV.defaultMMKV().encode("uuid",id)
            return id
        }
        return uuid
    }

    private fun getAndroidID(): String? {
        val id: String = Settings.Secure.getString(
            myApp.getContentResolver(),
            Settings.Secure.ANDROID_ID
        )
        return if ("9774d56d682e549c" == id) "" else id ?: ""
    }

    fun getReferrer() {
        val r = MMKV.defaultMMKV().decodeString("raduy")?:""
        if (TextUtils.isEmpty(r)) {
            val referrerClient = InstallReferrerClient.newBuilder(myApp).build()
            referrerClient.startConnection(object : InstallReferrerStateListener {

                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                    try {
                        when (responseCode) {
                            InstallReferrerClient.InstallReferrerResponse.OK -> {
                                // Connection established.
                                val response: ReferrerDetails = referrerClient.installReferrer
                                val referrerUrl: String = response.installReferrer
                                MMKV.defaultMMKV().encode("raduy",referrerUrl)

                                referrerClient.endConnection()
                            }
                            else -> {
                                referrerClient.endConnection()
                            }
                        }
                    } catch (e: Exception) {


                    }
                }



                override fun onInstallReferrerServiceDisconnected() {
                }
            })
        }
    }

    private fun getLocalRefer() = MMKV.defaultMMKV().decodeString("raduy")?:""
}