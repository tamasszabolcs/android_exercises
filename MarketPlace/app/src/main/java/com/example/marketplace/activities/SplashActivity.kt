package com.example.marketplace.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import com.example.marketplace.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN

    )

        val handler = Handler()
        handler.postDelayed({
            // do something after 2000ms
                            startActivity(Intent(this,DashboardActivity::class.java))
                            finish()
        }, 2000)

//        val typeface: Typeface = Typeface.createFromAsset(assets,"FtyStrategycideNcv-elGl.ttf")
//        val tv_app_name = findViewById<TextView>(R.id.tv_app_name)
//        tv_app_name.typeface = typeface
    }


}


