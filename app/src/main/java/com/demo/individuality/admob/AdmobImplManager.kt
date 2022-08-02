package com.demo.individuality.admob

object AdmobImplManager {
    var isShowingFullAd=false
    private var openAd:LoadAdmob?=null
    private var homeNativeAd:LoadAdmob?=null
    private var allTypeNativeAd:LoadAdmob?=null
    private var answerResultNativeAd:LoadAdmob?=null
    private var questionNativeAd:LoadAdmob?=null
    private var questionFinishCpAd:LoadAdmob?=null

    fun getOpenAdImpl():LoadAdmob{
        if (null== openAd){
            openAd = LoadAdmob(AdmobType.OPEN)
        }
        return openAd!!
    }

    fun getHomeNativeImpl():LoadAdmob{
        if (null== homeNativeAd){
            homeNativeAd=LoadAdmob(AdmobType.HOME)
        }
        return homeNativeAd!!
    }

    fun getAllTypeNativeImpl():LoadAdmob{
        if (null== allTypeNativeAd){
            allTypeNativeAd=LoadAdmob(AdmobType.ALL_TYPE)
        }
        return allTypeNativeAd!!
    }

    fun getAnswerResultNativeImpl():LoadAdmob{
        if (null== answerResultNativeAd){
            answerResultNativeAd=LoadAdmob(AdmobType.ANSWER_RESULT)
        }
        return answerResultNativeAd!!
    }

    fun getQuestionNativeImpl():LoadAdmob{
        if (null== questionNativeAd){
            questionNativeAd=LoadAdmob(AdmobType.QUESTION)
        }
        return questionNativeAd!!
    }

    fun getQuestionFinishCpNativeImpl():LoadAdmob{
        if (null== questionFinishCpAd){
            questionFinishCpAd=LoadAdmob(AdmobType.QUESTION_FINISH)
        }
        return questionFinishCpAd!!
    }
}