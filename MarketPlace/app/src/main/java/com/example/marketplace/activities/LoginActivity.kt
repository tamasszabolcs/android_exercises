package com.example.marketplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.example.marketplace.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        val tv_register = findViewById<TextView>(R.id.tv_register_now)
        //onClickListenert hasznalunk hogy elerjuk id szerint
        tv_register.setOnClickListener{
            //click eseten dobjon at a register oldalra
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}