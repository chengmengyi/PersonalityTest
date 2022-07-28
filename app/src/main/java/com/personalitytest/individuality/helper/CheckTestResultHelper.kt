package com.personalitytest.individuality.helper

import java.lang.Exception

object CheckTestResultHelper {
    private val answerA= arrayOf(2,4,9,3,6,2,3,8,9,3,8,2,5,6,8,4,9,6,1,2,6,7,2,4,5,8,9,4,2,3,5,6,2,3,8,5)
    private val answerB= arrayOf(5,7,1,5,8,1,7,4,6,1,7,4,9,3,1,5,2,7,5,8,4,1,3,9,6,3,7,1,6,9,8,1,7,4,9,7)

    fun check(map:HashMap<Int,String>):Int{
        try {
            val result= LinkedHashMap<Int,Int>()

            map.forEach { (i, s) ->
                val type = if (s=="A"){
                    answerA[i]
                }else{
                    answerB[i]
                }
                result[type]=(result[type]?:0)+1
            }
            val map = result.toList()
                .sortedByDescending { (key, value) -> value }
                .toMap()
            var resultType=0
            for (entry in map) {
                resultType=entry.key
                if (resultType!=0){
                    break
                }
            }
            if (resultType in 1..9){
                return resultType
            }
        }catch (e:Exception){}
        return 1
    }
}