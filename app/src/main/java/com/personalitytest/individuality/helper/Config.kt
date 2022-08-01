package com.personalitytest.individuality.helper

import com.personalitytest.individuality.admob.AdmobType

class Config {
    companion object{
        const val URL=""
        const val EMAIL=""

        const val AD="""{
    "${AdmobType.OPEN}": [
        {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/3419835294",
            "per_type": "kp",
            "per_sort": 2
        }, {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/1033173712",
            "per_type": "cp",
            "per_sort": 1
        },
        {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/3419835294A",
            "per_type": "kp",
            "per_sort": 3
        }
    ],
    "${AdmobType.HOME}": [
        {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110",
            "per_type": "ys",
            "per_sort": 1
        }, {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110X",
            "per_type": "ys",
            "per_sort": 2
        }
    ],
      "${AdmobType.ALL_TYPE}": [
        {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110",
            "per_type": "ys",
            "per_sort": 1
        }, {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110X",
            "per_type": "ys",
            "per_sort": 2
        }
    ],
     "${AdmobType.ANSWER_RESULT}": [
        {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110X",
            "per_type": "ys",
            "per_sort": 2
        }, {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110",
            "per_type": "ys",
            "per_sort": 1
        }
    ],
       "${AdmobType.QUESTION}": [
        {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110",
            "per_type": "ys",
            "per_sort": 1
        }, {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/2247696110X",
            "per_type": "ys",
            "per_sort": 2
        }
    ],
    "${AdmobType.QUESTION_FINISH}": [
     {
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/8691691433",
            "per_type": "cp",
            "per_sort": 2
        },{
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/1033173712X",
            "per_type": "cp",
            "per_sort": 3
        },{
            "per_source": "admob",
            "per_id": "ca-app-pub-3940256099942544/1033173712",
            "per_type": "cp",
            "per_sort": 1
        }
    ]
}"""


        const val QUESTION_JSON="""[
    [
        "Serving others and responding to their needs is important to me.",
        "Seeking ways to see and do things is important to me. "
    ],
    [
        "When faced with trouble, I get stuck in it. ",
        "When faced with troubles, I try to find ways to relax. "
    ],
    [
        "I have always thought of myself as a calm and easy-going person.",
        "I have always considered myself a serious, self-disciplined person."
    ],
    [
        "I like social life and like to meet all kinds of friends.",
        "I am not interested in social life and am afraid of socializing with people."
    ],
    [
        "Decision making is often difficult for me.",
        "Decision making is rarely difficult for me."
    ],
    [
        "I have always been willing to support others, pay for others, and like to be accompanied by others.",
        "I have always been serious, restrained, and like to discuss issues. "
    ],
    [
        "When faced with a new experience, I usually ask myself if it is wonderful and useful.",
        "When faced with a new experience, I usually ask myself if it was fun and interesting."
    ],
    [
        "One of my main strengths is the ability to dominate situations.",
        "One of my main strengths is the ability to describe inner states."
    ],
    [
        "In general, I am too open and naive.",
        "In general, I am too alert and cautious."
    ],
    [
        "I think there are many ways to do things.",
        "I think there is only one way to do things."
    ],
    [
        "One of my main strengths is utilizing resources and implementing plans.",
        "One of my main strengths is coming up with new ideas and getting people excited about them."
    ],
    [
        "My health and well-being suffer from my strong desire to help others.",
        "My relationships suffer from my strong ego."
    ],
    [
        "I have always had trouble sleeping.",
        "I have always been able to fall asleep easily."
    ],
    [
        "I have always been indecisive and lack of confidence in doing things.",
        "I have always acted with confidence and decisiveness."
    ],
    [
        "People trust me because I am confident and able to think about them.",
        "People trust me because I am fair and do the right thing."
    ],
    [
        "I usually focus on my emotions and like to keep them going.",
        "I usually downplay my emotions and don't pay too much attention to it."
    ],
    [
        "I may have been too passive and not engaged enough.",
        "I may have always been too dominant and manipulative."
    ],
    [
        "In general, I am methodical and prudent.",
        "In general, I am very exciting and willing to take risks."
    ],
    [
        "I tend to help others and find that they are making mistakes.",
        "I tend to keep a certain distance from others."
    ],
    [
        "I have cared and nurtured many people.",
        "I have given many people directions and incentives."
    ],
    [
        "I am very proud of my perseverance and common sense.",
        "I am very proud of my innovation and creativity."
    ],
    [
        "In general, I am an outgoing, social person.",
        "In general, I am a serious, self-disciplined person."
    ],
    [
        "Although the other party did not ask for it, as long as I felt that they needed something, I would not hesitate and take the initiative to help them.",
        "If the other party does not ask, I will not take the initiative to help them."
    ],
    [
        "I am usually attracted to situations that cause violent emotional shocks.",
        "Usually situations that make me feel calm and comfortable attract me. "
    ],
    [
        "My ideas have always been adventurous due to imagination and curiosity.",
        "My thinking has always been practical, just trying to push things."
    ],
    [
        "Controlling and dominating others is more important to me.",
        "Being valued and recognized by others is more important to me."
    ],
    [
        "When faced with trouble, I have the ability to solve it.",
        "When in trouble, I console myself with what I love."
    ],
    [
        "Overall, I have always been intuitive and individualistic.",
        "Overall, I have been very organized and responsible."
    ],
    [
        "I have trouble with interpersonal relationships due to being too intrusive and disturbing others.",
        "I have trouble with interpersonal relationships due to being too evasive and silent."
    ],
    [
        "I am always confident and like to compare myself.",
        "I have always been humble and liked to move at a slower pace."
    ],
    [
        "I like to live in my own little world.",
        "I like to let the whole world know that I exist."
    ],
    [
        "I suffer from nervousness, insecurities and doubts.",
        "I was plagued by anger, idealism, and impatience."
    ],
    [
        "I have always been proud of the important role I play in the lives of others.",
        "I have always prided myself on being funny and open to new things."
    ],
    [
        "I am optimistic and easy to recover from setbacks.",
        "I am often emotional and sentimental."
    ],
    [
        "In many situations, I like to allow myself to take the lead.",
        "In many cases, I would rather let others take the lead."
    ],
    [
        "I have always been focused and highly enthusiastic.",
        "I have always been spontaneous and fun-loving."
    ]
]"""
    }
}