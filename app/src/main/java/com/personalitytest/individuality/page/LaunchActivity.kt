package com.personalitytest.individuality.page

import android.animation.ValueAnimator
import android.content.Intent
import android.view.KeyEvent
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import com.personalitytest.individuality.R
import com.personalitytest.individuality.base.BaseActivity
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : BaseActivity() {
    private var valueAnimator:ValueAnimator?=null

    override fun layoutId(): Int = R.layout.activity_launch

    override fun initView() {
        valueAnimator = ValueAnimator.ofInt(0, 100).apply {
            duration = 2000L
            interpolator = LinearInterpolator()
            addUpdateListener {
                val pro = it.animatedValue as Int
                progress_bar.progress = pro
            }
            doOnEnd {
                jumpHome()
            }
            start()
        }
    }

    private fun jumpHome(){
        startActivity(Intent(this,HomeActivity::class.java))
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
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            return true
        }
        return false
    }
}