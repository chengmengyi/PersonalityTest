package com.demo.personalitytest.helper

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.gms.ads.AdActivity
import com.demo.personalitytest.admob.AdmobImplManager
import com.demo.personalitytest.eventbus.EventbusBean
import com.demo.personalitytest.eventbus.EventbusCode
import com.demo.personalitytest.page.HomeActivity
import com.demo.personalitytest.page.LaunchActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ActivityCallback {

    var appIsFront=true
    private var reloadApp=false
    private var job: Job?=null

    fun register(application: Application){
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            var page=0
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityStarted(activity: Activity) {
                page++
                job?.cancel()
                job=null
                if (page==1){
                    appIsFront=true
                    if (reloadApp){
                        EventbusBean(EventbusCode.RELOAD_APP).send()
                        if (ActivityUtils.isActivityExistsInStack(HomeActivity::class.java)){
                            activity.startActivity(Intent(activity,LaunchActivity::class.java))
                        }
                    }
                    reloadApp=false
                }
            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {
                page--
                if (page<=0){
                    appIsFront=false
                    job= GlobalScope.launch {
                        delay(3000L)
                        reloadApp=true
                        AdmobImplManager.isShowingFullAd=false
                        ActivityUtils.finishActivity(LaunchActivity::class.java)
                        ActivityUtils.finishActivity(AdActivity::class.java)
                    }
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })
    }
}