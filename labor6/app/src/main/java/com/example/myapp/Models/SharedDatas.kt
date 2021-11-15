package com.example.myapp.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Constants
import com.example.myapplication.Question


class SharedDatas: ViewModel() {
    lateinit var selectedName : String
    lateinit var questions : MutableList<Question>
    lateinit var highScore: String




















    fun get_questions(): MutableList<Question> {
        return questions
    }

}
