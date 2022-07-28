package com.personalitytest.individuality.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity:AppCompatActivity() {
    protected var immersionBar:ImmersionBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        density()
        setContentView(layoutId())
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
}