package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    val progressBar = findViewById<ProgressBar>(R.id.progressBar)
    val tv_progress = findViewById<TextView>(R.id.tv_progress)
    val tv_question = findViewById<TextView>(R.id.tv_question)
    val iv_image = findViewById<ImageView>(R.id.iv_image)
    val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
    val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
    val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
    val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
    val btn_submit = findViewById<Button>(R.id.btn_submit)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.u_name)

         mQuestionsList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)

        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion(){
        val act_question = mQuestionsList!!.get(mCurrentPosition - 1)

        defaultOptionsView()
//        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = mCurrentPosition
//        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max
//        val tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = act_question!!.question
//        val iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(act_question.image)
//        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        tv_option_one.text = act_question.optionOne
//        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        tv_option_two.text = act_question.optionTwo
//        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        tv_option_three.text = act_question.optionThree
//        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        tv_option_four.text = act_question.optionFour

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "FINISH"
        }
        else{
            btn_submit.text = "SUBMIT"
        }

    }

    //kivalasztas utan a tobbi maradjon default
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border)
        }
    }

    private fun selectedOptionView(tv:TextView,selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <=mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.u_name, mUserName)
                            intent.putExtra(Constants.correct_answers, mCorrectAnswers)
                            intent.putExtra(Constants.total_questions, mQuestionsList!!.size)
                            startActivity(intent)
                        }
                    }
                }
                else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.incorrect_option_border)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btn_submit.text = "FINISH"
                    }
                    else{
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                tv_option_one.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 ->{
                tv_option_two.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 ->{
                tv_option_three.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 ->{
                tv_option_four.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

}