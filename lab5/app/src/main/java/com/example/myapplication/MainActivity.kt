package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity;
import android.content.Intent
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText
import android.widget.TextView;
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start = findViewById<Button>(R.id.btnStart)
        btn_start.setOnClickListener{
            val etName = findViewById<EditText>(R.id.et_name)
            if(etName.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter your name!",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.u_name, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}