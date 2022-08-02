package com.demo.personalitytest.helper

import com.demo.personalitytest.R
import com.demo.personalitytest.bean.TypeBean
import org.json.JSONArray
import java.lang.Exception

object AllTypeHelper {
    val questionList= arrayListOf<ArrayList<String>>()
    private val allTypeList= arrayListOf<TypeBean>()

    fun readQuestionConf(){
        try {
            questionList.clear()
            val jsonArray = JSONArray(Config.QUESTION_JSON)
            for (index in 0 until jsonArray.length()){
                val array = jsonArray.getJSONArray(index)
                val list= arrayListOf<String>()
                for (i in 0 until  array.length()){
                    list.add(array.optString(i))
                }
                questionList.add(list)
            }
        }catch (e:Exception){}
    }


    fun getAllTypeList():ArrayList<TypeBean>{
        initAllTypeList()
        return allTypeList
    }

    private fun initAllTypeList(){
        if (allTypeList.isEmpty()){
            allTypeList.add(TypeBean("The Reformer",R.drawable.icon_type_reformer,R.drawable.icon_type_reformer_text,R.drawable.icon_type_reformer_top,1))
            allTypeList.add(TypeBean("The Helper",R.drawable.icon_type_helper,R.drawable.icon_type_helper_text,R.drawable.icon_type_helper_top,2))
            allTypeList.add(TypeBean("The Achiever",R.drawable.icon_type_achiever,R.drawable.icon_type_achiever_text,R.drawable.icon_type_achiever_top,3))
            allTypeList.add(TypeBean("The Investigator",R.drawable.icon_type_investigator,R.drawable.icon_type_investigator_text,R.drawable.icon_type_investigator_top,4))
            allTypeList.add(TypeBean("The Loyalist",R.drawable.icon_type_loyalist,R.drawable.icon_type_loyalist_text,R.drawable.icon_type_loyalist_top,5))
            allTypeList.add(TypeBean("The Individualist",R.drawable.icon_type_individualist,R.drawable.icon_type_individualist_text,R.drawable.icon_type_individualist_top,6))
            allTypeList.add(TypeBean("The Enthusiast",R.drawable.icon_type_enthusiast,R.drawable.icon_type_enthusiast_text,R.drawable.icon_type_enthusiast_top,7))
            allTypeList.add(TypeBean("The Challenger",R.drawable.icon_type_challenger,R.drawable.icon_type_challenger_text,R.drawable.icon_type_challenger_top,8))
            allTypeList.add(TypeBean("The Peacemaker",R.drawable.icon_type_peacemaker,R.drawable.icon_type_peacemaker_text,R.drawable.icon_type_peacemaker_top,9))
            allTypeList.forEach {
                it.characteristics=assembleCharacteristicsByType(it.type)
                it.integration= integration
                it.something= assembleSomethingByType(it.type)
            }
        }
    }

    private fun assembleCharacteristicsByType(type:Int):String{
        var characteristics=""
        when(type){
            1->characteristics="Characteristic role:Reformer, Perfectionist\nEgo fixation:Resentment\nHoly idea:Perfection\nTrap:Perfection" +
                    "\nBasic fear:Corruptness, imbalance, being bad\nBasic desire:Goodness, integrity, balance" +
                    "\nTemptation:Hypocrisy, hypercriticism\nVice/Passion:Anger\nVirtue:Serenity" +
                    "\nStress/ Disintegration:4\nSecurity/ Integration:7"
            2->characteristics="Characteristic role:Helper, Giver\nEgo fixation:Flattery\nHoly idea:Freedom, Will\nTrap:Freedom" +
                    "\nBasic fear:Being unlovable\nBasic desire:To feel worthy of love" +
                    "\nTemptation:Deny own needs, manipulation\nVice/Passion:Pride\nVirtue:Humility" +
                    "\nStress/ Disintegration:8\nSecurity/ Integration:4"
            3->characteristics="Characteristic role:Achiever, Performer\nEgo fixation:Vanity\nHoly idea:Hope, Law\nTrap:Efficiency" +
                    "\nBasic fear:Worthlessness\nBasic desire:To feel valuable" +
                    "\nTemptation:Pushing self to always be \"the best\"\nVice/Passion:Deceit\nVirtue:Truthfulness" +
                    "\nStress/ Disintegration:9\nSecurity/ Integration:6"
            4->characteristics="Characteristic role:Individualist, Romantic\nEgo fixation:Melancholy\nHoly idea:Origin\nTrap:Authenticity" +
                    "\nBasic fear:Having no identity or significance\nBasic desire:To be uniquely themselves" +
                    "\nTemptation:To overuse imagination in search of self\nVice/Passion:Envy\nVirtue:Equanimity (Emotional Balance)" +
                    "\nStress/ Disintegration:2\nSecurity/ Integration:1"
            5->characteristics="Characteristic role:Investigator, Observer\nEgo fixation:Stinginess\nHoly idea:Omniscience, Transparency\nTrap:Observer" +
                    "\nBasic fear:Helplessness, incapability, incompetence\nBasic desire:Mastery, understanding" +
                    "\nTemptation:Replacing direct experience with concepts\nVice/Passion:Avarice\nVirtue:Detachment" +
                    "\nStress/ Disintegration:7\nSecurity/ Integration:8"
            6->characteristics="Characteristic role:Loyalist, Loyal Skeptic\nEgo fixation:Cowardice\nHoly idea:Faith\nTrap:Security" +
                    "\nBasic fear:Being without support or guidance\nBasic desire:To have support and guidance" +
                    "\nTemptation:Indecision, doubt, seeking reassurance\nVice/Passion:Fear\nVirtue:Courage" +
                    "\nStress/ Disintegration:3\nSecurity/ Integration:9"
            7->characteristics="Characteristic role:Enthusiast, Epicure\nEgo fixation:Planning\nHoly idea:Plan, Work, Wisdom\nTrap:Idealism" +
                    "\nBasic fear:Being unfulfilled, trapped, deprived\nBasic desire:To be satisfied and content" +
                    "\nTemptation:Thinking fulfillment is somewhere else\nVice/Passion:Gluttony\nVirtue:Sobriety" +
                    "\nStress/ Disintegration:1\nSecurity/ Integration:5"
            8->characteristics="Characteristic role:Challenger, Protector\nEgo fixation:Vengeance\nHoly idea:Truth\nTrap:Justice" +
                    "\nBasic fear:Being controlled, harmed, violated\nBasic desire:To gain influence and be self-sufficient" +
                    "\nTemptation:Thinking they are completely self-sufficient\nVice/Passion:Lust\nVirtue:Innocence" +
                    "\nStress/ Disintegration:5\nSecurity/ Integration:2"
            9->characteristics="Characteristic role:Peacemaker, Mediator\nEgo fixation:Indolence\nHoly idea:Love\nTrap:Seeker" +
                    "\nBasic fear:Loss, fragmentation, separation\nBasic desire:Wholeness, peace of mind" +
                    "\nTemptation:Avoiding conflicts, avoiding self-assertion\nVice/Passion:Sloth\nVirtue:Action" +
                    "\nStress/ Disintegration:6\nSecurity/ Integration:3"
        }
        return characteristics
    }

    private val integration="The \"stress\" and \"security\" points (sometimes referred to as the \"disintegration\" and \"integration\" points) are the types connected by the lines of the enneagram figure and are believed by some to influence a person in more adverse or relaxed circumstances. According to this theory, someone with a primary One type, for example, may begin to think, feel, and act more like someone with a Four type when stressed or a Seven type when relaxed.\n" +
            "\n" +
            "\n" +
            "1 → 7: Let go of restraint, be tolerant and optimistic, dare to try, and get \"cheerful\";\n" +
            "7 → 5: Reduce impulsiveness, act calmly, think deeply, and gain \"reason\";\n" +
            "5 → 8: Be strong and courageous, be decisive and confident, do what you say, and gain \"authority\";\n" +
            "8 → 2: Warm and friendly, helpful, open-minded, and \"innocent\";\n" +
            "2 → 4: Persevere in your wishes, enjoy yourself, love others and yourself, and gain \"humility\";\n" +
            "4 → 1: Be calm and rational, distinguish right from wrong, deal with things objectively, and obtain \"balance\";\n" +
            "3 → 6: Be responsible and careful, think twice, be loyal, and gain \"loyalty\";\n" +
            "6 → 9: Go with the flow, let go of anxiety, convince others, and gain \"trust\";\n" +
            "9 → 3: Clear goals, diligent and positive, self-challenge, and gain \"decisiveness\"."

    private fun assembleSomethingByType(type:Int):String{
        var something=""
        when(type){
            1->something="Willing to try to correct his shortcomings.\n" +
                    "Feeling restless if things are not in order.\n" +
                    "Want to avoid wasting time or networking.\n" +
                    "Whether it's yourself or those around you, you often blame yourself for thinking you could do better.\n" +
                    "Even a small mistake or a small flaw will linger.\n" +
                    "Clumsy to relax, can not easily joke or gossip.\n" +
                    "Judge others by your own yardstick in your head.\n" +
                    "Worry and worry more easily than others.\n" +
                    "I would rather be frank and honest about everything.\n" +
                    "Unwilling to do things that are against human relations, including lying and deceit."
            2->something="I feel that many people depend on me.\n" +
                    "Think giving to others is important.\n" +
                    "Often want to \"be useful to all people\".\n" +
                    "When I see others in distress, or in a painful position, I want to lend a hand.\n" +
                    "Whether you like it or not, take care of whoever is in front of you.\n" +
                    "Hope everyone comes to me for comfort and my advice.\n" +
                    "Finally, consider your own interests.\n" +
                    "I often feel that I do my best for others, but get no thanks.\n" +
                    "When you think you will be thanked but you are not, you will feel like a victim.\n" +
                    "Strongly feel that \"loving and being loved is the most important thing in life\"."
            3->something="Like to do things.\n" +
                    "Like to work with partners and feel like they want to be good partners.\n" +
                    "In order to complete things, pay attention to organization and efficiency, and refuse to waste anything.\n" +
                    "Often think that you will be successful.\n" +
                    "Clearly set goals and lock in what you need to do in order to get results.\n" +
                    "I like to use progress tables, scores, etc. to express my achievements.\n" +
                    "Want to impress others with their own success.\n" +
                    "Although they like to make their own decisions, they will change their opinions on the fly.\n" +
                    "In order to achieve goals, sometimes compromise with opponents.\n" +
                    "I hate hearing people say I'm not doing well."
            4->something="I feel that many people do not realize the true beauty and meaning of life.\n" +
                    "There is intense sadness about his past.\n" +
                    "I often want to keep myself, but it's not easy.\n" +
                    "The mind is attracted to symbolic things.\n" +
                    "Feeling that others don't have the same deep feeling for things as I do.\n" +
                    "Think it's hard for others to understand how I feel.\n" +
                    "For me, the atmosphere around me feels important.\n" +
                    "I think that life is like a play, and I am performing on the stage.\n" +
                    "I don't want to admit that I'm an ordinary person.\n" +
                    "Thinking about loss, death, pain, inevitably brings deep thought."
            5->something="Clumsy to express their feelings.\n" +
                    "Have a habit of collecting items, thinking it will come in handy one day.\n" +
                    "It's the toughest conversations that don't make sense.\n" +
                    "Good at comprehensive observation, or synthesis of various opinions.\n" +
                    "When asked \"how do you feel\", there is no answer.\n" +
                    "In daily life, want to have a private time and place to relax.\n" +
                    "I think it's better to appoint someone else to do it than to take the lead.\n" +
                    "There is a tendency to observe the actions of others before directly engaging with them.\n" +
                    "Likes alone time away from others.\n" +
                    "Like to think to solve problems."
            6->something="In the presence of some kind of authority, it becomes neurotic.\n" +
                    "Often suffering from doubts.\n" +
                    "I want to have clear indicators and be familiar with my position.\n" +
                    "Always be vigilant and dare not take the danger lightly.\n" +
                    "Things are taken too seriously.\n" +
                    "Always ask yourself if you are doing something wrong.\n" +
                    "I often feel that criticism from others is an attack.\n" +
                    "Often indecisive and very concerned about what his spouse or partner thinks.\n" +
                    "As long as you have the will, even if you are smashed to pieces for work, you will be willing to do so.\n" +
                    "Friends think that I am honest, willing to help, encourage and considerate others."
            7->something="Like anything happy, childlike innocence.\n" +
                    "Not much sense of crisis.\n" +
                    "I regret that others can't have the same cheerful mood as me.\n" +
                    "Often feel that they are happy, and ignore the other person's feelings.\n" +
                    "Always look at the bright side of things and don't want to look at the dark side.\n" +
                    "Not very hostile towards people they come into contact with.\n" +
                    "Likes jokes or cheerful conversations, dislikes bleak remarks.\n" +
                    "Likes to draw attention at parties.\n" +
                    "\"Seeing the tree but not the forest\" is really troublesome, thinking that things should be grasped from a broader perspective.\n" +
                    "Think sad things should be forgotten early."
            8->something="Willingness to fight for one's own needs, and clinging to what is necessary.\n" +
                    "You can immediately find out the weakness of others, and once the opponent challenges it, attack this weakness immediately.\n" +
                    "Not afraid of confrontation with others, in fact often confrontation.\n" +
                    "It feels good to exercise power.\n" +
                    "A person who considers himself to be aggressive and self-assertive.\n" +
                    "Can neither tolerate nor show their noble and gentle \"feminine side\".\n" +
                    "Disdain to retreat, and like to attack.\n" +
                    "For me, it is very important to implement benevolence and reason.\n" +
                    "I will protect all those under my authority.\n" +
                    "Generally speaking, I don't care much about self-reflection, self-analysis."
            9->something="I think that life is full of green hills, and it is rare to encounter embarrassing phenomena.\n" +
                    "Most of the time, it's smooth and quiet.\n" +
                    "I like the most comfortable life.\n" +
                    "Consider yourself an extremely optimist.\n" +
                    "Can't remember when was the last time I had insomnia.\n" +
                    ".Think that almost all people are more or less different, but roughly the same.\n" +
                    "Usually not too excited about things.\n" +
                    "Never feel impatient, never have the feeling of not being able to wait until tomorrow.\n" +
                    "I hate wasting energy for everything, and I will consider saving energy when doing things.\n" +
                    "Relationships are the most important thing in my opinion."
        }
        return something
    }
}