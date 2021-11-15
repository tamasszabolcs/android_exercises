package com.example.myapplication

import com.example.myapp.R

object Constants {

    const val u_name: String = "user_name"
    const val total_questions: String = "total_question"
    const val correct_answers: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val question1 = Question(1,
                                "Melyik ország zászlaja látható?",
                                  R.drawable.ic_flag_of_germany,
                                  "Ausztrália",
                                  "India",
                                  "Németország",
                                  "Románia",
                                  3)

        questionsList.add(question1)

        val question2 = Question(2,
            "Melyik ország zászlaja látható?",
            R.drawable.ic_flag_of_hungary,
            "Magyarország",
            "Olaszország",
            "Lengyelország",
            "Svédország",
            1)

        questionsList.add(question2)

        return questionsList
    }
}