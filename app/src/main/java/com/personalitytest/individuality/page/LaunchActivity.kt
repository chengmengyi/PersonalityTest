package com.personalitytest.individuality.page

import android.animation.ValueAnimator
import android.content.Intent
import android.view.KeyEvent
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import com.blankj.utilcode.util.ActivityUtils
import com.personalitytest.individuality.R
import com.personalitytest.individuality.admob.AdmobImplManager
import com.personalitytest.individuality.admob.FullAdHelper
import com.personalitytest.individuality.base.BaseActivity
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : BaseActivity() {
    private var valueAnimator:ValueAnimator?=null
    private val fullAdHelper by lazy { FullAdHelper(AdmobImplManager.getOpenAdImpl()){ jumpHome() } }

    override fun layoutId(): Int = R.layout.activity_launch

    override fun initView() {
        fullAdHelper.callAd()
        AdmobImplManager.getHomeNativeImpl().call()
        AdmobImplManager.getAllTypeNativeImpl().call()
        AdmobImplManager.getAnswerResultNativeImpl().call()
        AdmobImplManager.getQuestionNativeImpl().call()
        AdmobImplManager.getQuestionFinishCpNativeImpl().call()

        valueAnimator = ValueAnimator.ofInt(0, 100).apply {
            duration = 10000L
            interpolator = LinearInterpolator()
            addUpdateListener {
                val pro = it.animatedValue as Int
                progress_bar.progress = pro
                val duration = (10 * (pro / 100.0F)).toInt()
                if (duration in 2..9){
                    fullAdHelper.preShowAd(this@LaunchActivity)
                }else if (duration>=10){
                    jumpHome()
                }
            }
            start()
        }
    }

    private fun jumpHome(){
        if (!ActivityUtils.isActivityExistsInStack(HomeActivity::class.java)){
            startActivity(Intent(this,HomeActivity::class.java))
        }
        finish()
    }

    override fun onResume() {
        super.onResume()
        valueAnimator?.resume()
    }

    override fun onPause() {
        super.onPause()
        valueAnimator?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        valueAnimator?.removeAllUpdateListeners()
        valueAnimator?.cancel()
        valueAnimator=null
        fullAdHelper.onDestroy()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            return true
        }
        return false
    }
}