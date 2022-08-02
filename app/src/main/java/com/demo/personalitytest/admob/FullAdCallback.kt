package com.demo.personalitytest.admob

import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.demo.personalitytest.interfaces.IAdShowResultCallback

class FullAdCallback(private val iAdShowResultCallback: IAdShowResultCallback): FullScreenContentCallback() {
    override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        AdmobImplManager.isShowingFullAd=false
        iAdShowResultCallback.showAdFinish()
    }

    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
        super.onAdFailedToShowFullScreenContent(p0)
        AdmobImplManager.isShowingFullAd=false
        iAdShowResultCallback.showAdFinish()
        iAdShowResultCallback.clearAd()
    }

    override fun onAdShowedFullScreenContent() {
        super.onAdShowedFullScreenContent()
        AdmobImplManager.isShowingFullAd=true
        iAdShowResultCallback.clearAd()
    }
}
