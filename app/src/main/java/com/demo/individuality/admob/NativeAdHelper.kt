package com.demo.individuality.admob

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.blankj.utilcode.util.SizeUtils.dp2px
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.demo.individuality.R
import com.demo.individuality.base.BaseActivity
import com.demo.individuality.bean.AdmobBean
import com.demo.individuality.helper.show
import com.demo.individuality.interfaces.ILoadAdCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NativeAdHelper(
    private val loadAdmob: LoadAdmob,
    private var loadAdCover:Boolean=true
) {
    private var nativeAd:Any?=null

    fun onLoadAd(ctx:BaseActivity){
        GlobalScope.launch(Dispatchers.Main) {
            delay(300L)
            if (ctx.onResume){
                val iLoadAdCallback=object :ILoadAdCallback{
                    override fun loadAdCallback(admobBean: AdmobBean) {
                        loadAdmob.removeCallback()
                        loadAdmob.destroyNativeAd(nativeAd)
                        nativeAd=admobBean.ad
                        nativeAd?.let {
                            if (it is NativeAd){
                                showNativeAd(ctx,it)
                            }
                        }
                    }
                }
                loadAdmob.call(iLoadAdCallback)
            }
        }
    }

    private fun showNativeAd(ctx:BaseActivity,ad: NativeAd) {
        val nativeAdView=ctx.findViewById<NativeAdView>(R.id.nv_native_view)
        if (loadAdCover){
            nativeAdView.mediaView=ctx.findViewById(R.id.mv_ad_cover)
            nativeAdView.mediaView?.run {
                if (null!=ad.mediaContent){
                    setMediaContent(ad.mediaContent)
                    setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                }
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View?, outline: Outline?) {
                        if (view == null || outline == null) return
                        outline.setRoundRect(
                            0,
                            0,
                            view.width,
                            view.height,
                            dp2px(10F).toFloat()
                        )
                        view.clipToOutline = true
                    }
                }
            }
        }
        nativeAdView.headlineView=ctx.findViewById(R.id.tv_ad_title)
        (nativeAdView.headlineView as AppCompatTextView).text=ad.headline

        nativeAdView.bodyView=ctx.findViewById(R.id.tv_ad_desc)
        (nativeAdView.bodyView as AppCompatTextView).text=ad.body

        nativeAdView.iconView=ctx.findViewById(R.id.iv_ad_logo)
        (nativeAdView.iconView as ImageFilterView).setImageDrawable(ad.icon?.drawable)

        nativeAdView.callToActionView=ctx.findViewById(R.id.tv_ad_action)
        (nativeAdView.callToActionView as AppCompatTextView).text=ad.callToAction

        nativeAdView.setNativeAd(ad)
        ctx.findViewById<AppCompatImageView>(R.id.iv_ad_default).show(false)

        loadAdmob.clearAdCache()
        loadAdmob.call()

        ctx.refreshNativeAd=false
    }

    fun onDestroy(){
        loadAdmob.removeCallback()
        nativeAd=null
    }
}