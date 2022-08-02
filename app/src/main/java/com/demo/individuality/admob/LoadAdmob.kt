package com.demo.individuality.admob

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.demo.individuality.app.myApp
import com.demo.individuality.bean.AdmobBean
import com.demo.individuality.bean.ConfigAdmobBean
import com.demo.individuality.helper.ReadFirebaseConfig
import com.demo.individuality.helper.printLog
import com.demo.individuality.interfaces.ILoadAdCallback
import org.json.JSONObject

class LoadAdmob(private val type:String) {
    private var loadingAd=false
    private var cacheAdmobBean:AdmobBean?=null
    private var iLoadAdCallback:ILoadAdCallback?=null
    private val adList= arrayListOf<ConfigAdmobBean>()

    fun call(iLoadAdCallback: ILoadAdCallback?=null){
        if (null!=iLoadAdCallback){
            this.iLoadAdCallback=iLoadAdCallback
        }
        if (loadingAd){
            printLog("loadingAd,$type")
            return
        }

        if (null!=cacheAdmobBean){
            if (cacheAdmobBean!!.isExpired()){
                clearAdCache()
            }else{
                printLog("ad has cache,use:${null!=iLoadAdCallback}")
                callbackAd(cacheAdmobBean!!)
                return
            }
        }

        initAdList()
        if (adList.isNullOrEmpty()){
            printLog("adList.isNullOrEmpty()")
            return
        }
        loadingAd=true

        val finialCallback=object :ILoadAdCallback{
            override fun loadAdCallback(admobBean: AdmobBean) {
                loadingAd=false
                if (null!=admobBean.ad){
                    cacheAdmobBean=admobBean
                }
                callbackAd(admobBean)
            }
        }

        if (type==AdmobType.OPEN){
            var loadNum=0
            preLoadAd(iLoadAdCallback = object :ILoadAdCallback{
                override fun loadAdCallback(admobBean: AdmobBean) {
                    if (null==admobBean.ad&&loadNum==0){
                        loadNum++
                        preLoadAd(iLoadAdCallback = finialCallback)
                    }else{
                        finialCallback.loadAdCallback(admobBean)
                    }
                }
            })
        }else{
            preLoadAd(iLoadAdCallback = finialCallback)
        }
    }

    private fun preLoadAd(index:Int=0,iLoadAdCallback: ILoadAdCallback){
        if (index>=adList.size){
            iLoadAdCallback.loadAdCallback(AdmobBean())
            return
        }
        loadAd(adList[index],object :ILoadAdCallback{
            override fun loadAdCallback(admobBean: AdmobBean) {
                if (null==admobBean.ad){
                    preLoadAd(index=index+1,iLoadAdCallback)
                }else{
                    iLoadAdCallback.loadAdCallback(admobBean)
                }
            }
        })
    }

    private fun loadAd(configAdmobBean: ConfigAdmobBean,iLoadAdCallback: ILoadAdCallback){
        printLog("start load ${type} ad ,${configAdmobBean.toString()}")
        when(configAdmobBean.type){
            "kp"->{
                AppOpenAd.load(
                    myApp,
                    configAdmobBean.id,
                    AdRequest.Builder().build(),
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                    object : AppOpenAd.AppOpenAdLoadCallback(){
                        override fun onAdLoaded(p0: AppOpenAd) {
                            super.onAdLoaded(p0)
                            printLog("load ${type} success")
                            iLoadAdCallback.loadAdCallback(AdmobBean(ad = p0,time = System.currentTimeMillis()))
                        }

                        override fun onAdFailedToLoad(p0: LoadAdError) {
                            super.onAdFailedToLoad(p0)
                            printLog("load ${type} fail ,${p0.message}")
                            iLoadAdCallback.loadAdCallback(AdmobBean())
                        }
                    }
                )
            }
            "cp"->{
                InterstitialAd.load(
                    myApp,
                    configAdmobBean.id,
                    AdRequest.Builder().build(),
                    object : InterstitialAdLoadCallback(){
                        override fun onAdFailedToLoad(p0: LoadAdError) {
                            printLog("load ${type} fail ,${p0.message}")
                            iLoadAdCallback.loadAdCallback(AdmobBean())
                        }

                        override fun onAdLoaded(p0: InterstitialAd) {
                            printLog("load ${type} success")
                            iLoadAdCallback.loadAdCallback(AdmobBean(ad = p0,time = System.currentTimeMillis()))
                        }
                    }
                )
            }
            "ys"->{
                AdLoader.Builder(myApp,configAdmobBean.id)
                    .forNativeAd {
                        printLog("load ${type} success")
                        iLoadAdCallback.loadAdCallback(AdmobBean(ad = it,time = System.currentTimeMillis()))
                    }
                    .withAdListener(object : AdListener(){
                        override fun onAdFailedToLoad(p0: LoadAdError) {
                            super.onAdFailedToLoad(p0)
                            printLog("load ${type} fail ,${p0.message}")
                            iLoadAdCallback.loadAdCallback(AdmobBean())
                        }
                    })
                    .withNativeAdOptions(
                        NativeAdOptions.Builder()
                            .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_LEFT)
                            .build()
                    )
                    .build()
                    .loadAd(AdRequest.Builder().build())
            }
        }
    }

    private fun initAdList(){
        try {
            val jsonArray = JSONObject(ReadFirebaseConfig.getLocalAdJson()).getJSONArray(type)
            val list= arrayListOf<ConfigAdmobBean>()
            for (index in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(index)
                list.add(
                    ConfigAdmobBean(
                        jsonObject.optString("per_source"),
                        jsonObject.optString("per_id"),
                        jsonObject.optString("per_type"),
                        jsonObject.optInt("per_sort"),
                    )
                )
            }
            val sortedByDescending = list.filter { it.source == "admob" }.sortedByDescending { it.sort }
            adList.clear()
            adList.addAll(sortedByDescending)
        }catch (e:Exception){}
    }

    private fun callbackAd(admobBean: AdmobBean){
        iLoadAdCallback?.loadAdCallback(admobBean)
    }

    fun clearAdCache(){
        cacheAdmobBean=null
    }

    fun removeCallback(){
        iLoadAdCallback=null
    }

    fun destroyNativeAd(ad:Any?){
        ad?.let {
            if (it is NativeAd){
                it.destroy()
            }
        }
    }
}