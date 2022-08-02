package com.demo.individuality.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.demo.individuality.eventbus.EventbusBean
import com.demo.individuality.eventbus.EventbusCode
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseActivity:AppCompatActivity() {
    var onResume=false
    var refreshNativeAd=true
    protected var immersionBar:ImmersionBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        density()
        setContentView(layoutId())
        EventBus.getDefault().register(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        immersionBar=ImmersionBar.with(this).apply {
            statusBarAlpha(0f)
            autoDarkModeEnable(true)
            statusBarDarkFont(true)
            init()
        }
        initView()
    }

    abstract fun layoutId():Int

    abstract fun initView()

    private fun density(){
        val metrics: DisplayMetrics = resources.displayMetrics
        val td = metrics.heightPixels / 760f
        val dpi = (160 * td).toInt()
        metrics.density = td
        metrics.scaledDensity = td
        metrics.densityDpi = dpi
    }

    override fun onResume() {
        super.onResume()
        onResume=true
    }

    override fun onPause() {
        super.onPause()
        onResume=false
    }

    override fun onStop() {
        super.onStop()
        onResume=false
    }

    @Subscribe
    fun onEvent(bean: EventbusBean) {
        if (bean.code== EventbusCode.RELOAD_APP){
            refreshNativeAd=true
        }else{
            onEventMsg(bean)
        }
    }

    protected open fun onEventMsg(bean: EventbusBean){}

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}