package com.example.potmarketplace.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.potmarketplace.R
import com.example.potmarketplace.fragments.LoginFragment
import com.example.potmarketplace.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getSharedPreferences(Constants.SHAREDPREF, Context.MODE_PRIVATE)

        val token = sharedPref.getString(Constants.TOKEN,"semmi")

        //Toast.makeText(this,token, Toast.LENGTH_LONG).show()

    }
}