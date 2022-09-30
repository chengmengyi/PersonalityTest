package com.demo.individuality.upload_log

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.TelephonyManager
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import android.provider.Settings
import org.json.JSONObject


class CreateBaseData {
    companion object{

        fun createBaseData(context: Context,ip:String):JSONObject{
            val jsonObject = JSONObject()
            jsonObject.put("highland",highland(context))
            jsonObject.put("absurd",absurd(context,ip))
            jsonObject.put("burnt",burnt(context))
            return jsonObject
        }

        private fun highland(context: Context):JSONObject{
            val jsonObject = JSONObject()
            jsonObject.put("angora",angora(context))
            jsonObject.put("condone",condone(context))
            jsonObject.put("clone",0)
            jsonObject.put("shagging",shagging(context))
            jsonObject.put("sweetish",sweetish())
            jsonObject.put("practise",practise())
            jsonObject.put("liberty",liberty())
            jsonObject.put("creep",creep())
            jsonObject.put("begotten",begotten())
            jsonObject.put("sockeye",sockeye())
            return jsonObject
        }

        private fun absurd(context: Context,ip:String):JSONObject{
            val jsonObject = JSONObject()
            jsonObject.put("leech",leech(context))
            jsonObject.put("tuskegee",100)
            jsonObject.put("lithium",ip)
            jsonObject.put("isaacson",isaacson(context))
            jsonObject.put("sparling",sparling(context))
            return jsonObject
        }

        private fun burnt(context: Context):JSONObject{
            val jsonObject = JSONObject()
            jsonObject.put("cant",cant())
            jsonObject.put("shmuel",shmuel(context))
            jsonObject.put("scapular","enormous")
            jsonObject.put("chelate",chelate())
            jsonObject.put("ken",ken())
            return jsonObject
        }

        private fun shmuel(context: Context)=try {
            AdvertisingIdClient.getAdvertisingIdInfo(context).id
        }catch (e:Exception){
            ""
        }

        private fun ken()= UUID.randomUUID().toString()

        private fun practise()= android.os.Build.BRAND

        fun liberty():String{
            val default = Locale.getDefault()
            return "${default.language}_${default.country}"
        }

        private fun sockeye()=Locale.getDefault().country

        private fun shagging(context: Context): String {
            var id=""
            try {
                id= Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID
                )
            }catch (e:Exception){

            }
            if (id.isEmpty()){
                id= ken()
            }
            return id
        }

        fun begotten()=TimeZone.getDefault().rawOffset/3600/1000

        fun sparling(context: Context):String{
            try {
                val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                return telephonyManager.networkOperator
            }catch (e:Exception){

            }
            return ""
        }

        private fun leech(context: Context):String{
            try {
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected){
                    if (activeNetworkInfo.type==ConnectivityManager.TYPE_WIFI){
                        return "wifi"
                    }else if (activeNetworkInfo.type==ConnectivityManager.TYPE_MOBILE){
                        return "mobile"
                    }
                }
            }catch (ex:Exception){

            }
            return "no"
        }

        fun cant()= Build.VERSION.RELEASE

        private fun creep()=Build.MODEL

        private fun sweetish()=Build.MANUFACTURER

        private fun condone(context: Context)= encrypt(shagging(context))

        fun edt(context: Context):String{
            val displayMetrics = context.resources.displayMetrics
            return "${displayMetrics.widthPixels}${displayMetrics.heightPixels}"
        }

        private fun angora(context: Context)=context.packageName

        fun chelate()=System.currentTimeMillis()

        private fun isaacson(context: Context)=context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_META_DATA).versionName

        private fun encrypt(raw: String): String {
            var md5Str = raw
            try {
                val md = MessageDigest.getInstance("MD5")
                md.update(raw.toByteArray())
                val encryContext = md.digest()
                var i: Int
                val buf = StringBuffer("")
                for (offset in encryContext.indices) {
                    i = encryContext[offset].toInt()
                    if (i < 0) {
                        i += 256
                    }
                    if (i < 16) {
                        buf.append("0")
                    }
                    buf.append(Integer.toHexString(i))
                }
                md5Str = buf.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return md5Str
        }
    }


}