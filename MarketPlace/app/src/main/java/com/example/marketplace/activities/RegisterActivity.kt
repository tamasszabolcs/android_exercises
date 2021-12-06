package com.example.marketplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.marketplace.R
import com.example.marketplace.retrofit.UserAccessLayer
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterActivity : AppCompatActivity() {

    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        val tv_login = findViewById<TextView>(R.id.tv_login_now)
        //onClickListenert hasznalunk hogy elerjuk id szerint
        tv_login.setOnClickListener {
            //click eseten dobjon at a login oldalra
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val btn_register = findViewById<Button>(R.id.btn_register)
        btn_register.setOnClickListener {
            validateRegisterDetails()
        }
    }

    private fun validateRegisterDetails(): Boolean {
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_confirm_password = findViewById<EditText>(R.id.et_confirm_password)
        return when {
            TextUtils.isEmpty(et_username.text.toString().trim {
                it <= ' '
            }) -> {
                showErrorSnackBar(resources.getString(R.string.username_empty),true)
                false
            }
            TextUtils.isEmpty(et_email.text.toString().trim {
                it <= ' '
            }) -> {
                showErrorSnackBar(resources.getString(R.string.email_empty),true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim {
                it <= ' '
            }) -> {
                showErrorSnackBar(resources.getString(R.string.password_empty),true)
                false
            }
            TextUtils.isEmpty(et_confirm_password.text.toString().trim {
                it <= ' '
            }) -> {
                showErrorSnackBar(resources.getString(R.string.confirm_password_empty),true)
                false
            }
            et_password.text.toString().trim {
                it <= ' '
            } != et_confirm_password.text.toString().trim {
                it <= ' '
            } -> {
                showErrorSnackBar(resources.getString(R.string.confirm_password_mismatch),true)
                false
            }
            else -> {
                showErrorSnackBar("Your registration was successful!",false)
                disposable = UserAccessLayer.getRegisterObservable(
                    et_username.toString(),
                    et_email.toString(),
                    et_password.toString(),
                    "01010101",
                    "token"
                ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                    {
                        val toast =
                            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    },
                    {
                        val toast =
                            Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    })
                true
            }


        }
    }

    fun showErrorSnackBar(message:String,errorMessage: Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if(errorMessage){
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,R.color.colorSnackBarError
                )
            )
        }
        else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    override fun onDestroy() {
        if(!disposable.isDisposed)
            disposable.dispose()
        super.onDestroy()
    }
}
