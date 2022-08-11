package com.personalitytest.individuality.bean

data class QuestionBean(
    val question1:String="",
    val question2:String="",
    var answerA:Int=0,
    var answerB: Int=0,
    var myAnswer:String=""
) {
}