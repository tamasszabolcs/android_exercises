package com.example.marketplace.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.marketplace.R
import com.example.marketplace.R.id.tv_main
import com.example.marketplace.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(Constants.project_preferences,Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.logged_in_username,"")!!
        val tv_main = findViewById<TextView>(R.id.tv_main)
        tv_main.text = "Hello $username"
    }
}