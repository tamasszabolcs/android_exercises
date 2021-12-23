package com.example.marketplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.marketplace.R
import com.example.marketplace.firestore.FirestoreClass
import com.example.marketplace.retrofit.models.UserModel
import com.example.marketplace.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        val tv_register_now = findViewById<TextView>(R.id.tv_register_now)
        tv_register_now.setOnClickListener(this)
        val tv_forgot_password = findViewById<TextView>(R.id.tv_forgot_password)
        tv_forgot_password.setOnClickListener(this)
        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener(this)
        //onClickListenert hasznalunk hogy elerjuk id szerint
//        tv_register.setOnClickListener{
//            //click eseten dobjon at a register oldalra
//            val intent = Intent(this,RegisterActivity::class.java)
//            startActivity(intent)
//        }

    }

    override fun onClick(view: View?) {
        if(view != null){
            when(view.id){
                R.id.tv_forgot_password ->{
                    val intent = Intent(this,ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_login ->{

                    loginRegisteredUser()
                }
                R.id.tv_register_now ->{

                        //click eseten dobjon at a register oldalra
                        val intent = Intent(this,RegisterActivity::class.java)
                        startActivity(intent)

                }
            }
        }
    }


    private fun validateLoginDetails():Boolean{
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_confirm_password = findViewById<EditText>(R.id.et_confirm_password)

        return when{
            TextUtils.isEmpty(et_email.text.toString().trim{
                it <= ' '
            }) ->{
                showErrorSnackBar(resources.getString(R.string.email_empty),true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim{
                it <= ' '
            }) ->{
                showErrorSnackBar(resources.getString(R.string.password_empty),true)
                false
            }
            else ->{
                //showErrorSnackBar("Your details are valid!",false)
                true
            }
        }
    }

    private fun loginRegisteredUser(){
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        if(validateLoginDetails()){
            //szoveg megkapasa es space levagasa
            val email = et_email.text.toString().trim{it <= ' '}
            val password = et_password.text.toString().trim{it <= ' '}

            //login
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    showErrorSnackBar("You are logged in successfully!",false)
                    FirestoreClass().getUserDetails(this)
                }
                else{
                    showErrorSnackBar(task.exception!!.message.toString(),true)
                }
            }
        }
    }
    fun userLoggedInSuccess(user:UserModel){
        //ha nincs befejezve a profil
        if(user.profileCompleted == 0){
           val intent = Intent(this,ProfileActivity::class.java)
            //can be loaded as a string
            intent.putExtra(Constants.extra_user_details,user)
            startActivity(intent)

        }
        else{
            //open the Main Screen
            startActivity(Intent(this,DashboardActivity::class.java))
        }
    finish()
    }
}