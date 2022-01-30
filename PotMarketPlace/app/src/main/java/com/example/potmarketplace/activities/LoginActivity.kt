package com.example.potmarketplace.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.potmarketplace.R
import com.example.potmarketplace.fragments.LoginFragment


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }



//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//
//        )
//        val tv_register_now = findViewById<TextView>(R.id.tv_register_now)
//        //tv_register_now.setOnClickListener(this)
////        val tv_forgot_password = findViewById<TextView>(R.id.tv_forgot_password)
////        tv_forgot_password.setOnClickListener(this)
//        //onClickListenert hasznalunk hogy elerjuk id szerint
//        tv_register_now.setOnClickListener{
//            //click eseten dobjon at a register oldalra
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }

    }



    }
