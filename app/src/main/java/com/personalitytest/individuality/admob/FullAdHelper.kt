package com.personalitytest.individuality.admob

import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.personalitytest.individuality.base.BaseActivity
import com.personalitytest.individuality.bean.AdmobBean
import com.personalitytest.individuality.helper.printLog
import com.personalitytest.individuality.interfaces.IAdShowResultCallback
import com.personalitytest.individuality.interfaces.ILoadAdCallback

class FullAdHelper(
    private val loadAdmob: LoadAdmob,
    private val isDoQuestion:Boolean=false,
    private val onNext:(() -> Unit)? = null
    ):ILoadAdCallback {
    private var admobBean:AdmobBean?=null

    fun callAd(){
        loadAdmob.call(this)
    }

    override fun loadAdCallback(admobBean: AdmobBean) {
        this.admobBean=admobBean
        loadAdmob.removeCallback()
    }

    fun preShowAd(ctx:BaseActivity){
        if (null!=admobBean){
            if (null==admobBean?.ad){
                onNext?.invoke()
                return
            }
            if (!canShowFullAd(ctx)){
                printLog("can not show full ad,isShowingFullAd:${AdmobImplManager.isShowingFullAd},onresume:${ctx.onResume}")
                onNext?.invoke()
                return
            }
            if (admobBean?.ad is AppOpenAd){
                AdmobImplManager.isShowingFullAd=true
                val appOpenAd = admobBean?.ad as AppOpenAd
                appOpenAd.fullScreenContentCallback=FullAdCallback(object :IAdShowResultCallback{
                    override fun showAdFinish() {
                        onNext?.invoke()
                    }

                    override fun clearAd() {
                        admobBean=null
                        loadAdmob.removeCallback()
                        loadAdmob.clearAdCache()
                    }
                })
                appOpenAd.show(ctx)
            }
            if (admobBean?.ad is InterstitialAd){
                AdmobImplManager.isShowingFullAd=true
                val interstitialAd = admobBean?.ad as InterstitialAd
                interstitialAd.fullScreenContentCallback=FullAdCallback(object :IAdShowResultCallback{
                    override fun showAdFinish() {
                        onNext?.invoke()
                    }

                    override fun clearAd() {
                        admobBean=null
                        loadAdmob.removeCallback()
                        loadAdmob.clearAdCache()
                    }
                })
                interstitialAd.show(ctx)
            }
        }else{
            if (isDoQuestion){
                onNext?.invoke()
            }
        }
    }

    private fun canShowFullAd(ctx:BaseActivity)=!AdmobImplManager.isShowingFullAd&&ctx.onResume


    fun onDestroy(){
        admobBean=null
        loadAdmob.removeCallback()
    }
}