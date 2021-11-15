package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapp.MainActivity
import com.example.myapp.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(



            R.layout.activity_result)

        val username = intent.getStringExtra(Constants.u_name)
        val tv_name = findViewById<TextView>(R.id.tv_name)
        tv_name.text = username

        val total_questions = intent.getIntExtra(Constants.total_questions,0)
        val correct_answers = intent.getIntExtra(Constants.correct_answers,0)

        val tv_score = findViewById<TextView>(R.id.tv_score)
        tv_score.text = "Your score is ${correct_answers}/${total_questions}"

        val btn_finish = findViewById<Button>(R.id.btn_finish)
        btn_finish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}