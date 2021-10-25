package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<TextView>(R.id.editTextName)

        val showButton = findViewById<Button>(R.id.btnClickHere)

        val textName = findViewById<TextView>(R.id.textName)

    }
}