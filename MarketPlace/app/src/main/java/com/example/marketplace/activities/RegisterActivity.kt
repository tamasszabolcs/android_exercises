package com.example.marketplace.activities

import com.example.marketplace.retrofit.models.UserModel
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.marketplace.R
import com.example.marketplace.firestore.FirestoreClass
import com.google.android.gms.tasks.OnCompleteListener
//import com.example.marketplace.retrofit.UserAccessLayer
//import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class RegisterActivity : AppCompatActivity() {
//    private lateinit var mProgressDialog:Dialog
//    lateinit var disposable: Disposable
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
            //onBackPressed fuggvenyt hasznaltam mert a user a login oldalrol jott s oda akar visszamenni
            onBackPressed()
        }
        val btn_register = findViewById<Button>(R.id.btn_register)
        btn_register.setOnClickListener {
            registerUser()
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
                //showErrorSnackBar("Your registration was successful!",false)
//                disposable = UserAccessLayer.getRegisterObservable(
//                    et_username.toString(),
//                    et_email.toString(),
//                    et_password.toString(),
//                    "01010101",
//                    "token"
//                ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
//                    {
//                        val toast =
//                            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG)
//                        toast.setGravity(Gravity.TOP, 0, 0)
//                        toast.show()
//                    },
//                    {
//                        val toast =
//                            Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG)
//                        toast.setGravity(Gravity.TOP, 0, 0)
//                        toast.show()
//                    })
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

//    override fun onDestroy() {
//        if(!disposable.isDisposed)
//            disposable.dispose()
//        super.onDestroy()
//    }
    private fun registerUser(){
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_confirm_password = findViewById<EditText>(R.id.et_confirm_password)
        //adatok valosak e vagy nem
        if(validateRegisterDetails()){
//            showProgressDialog(resources.getString(R.string.please_wait))
            val email: String = et_email.text.toString().trim(){ it <= ' '}
            val password: String = et_password.text.toString().trim(){ it <= ' '}
            //instance letrehozasa es user letrehozasa email passworddal
            //listener called after a task completes
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                OnCompleteListener { task ->
//                    hideProgressDialog()

                    //ha sikeres a regisztracio
                    if(task.isSuccessful){
                        //firebaseba bekerul a user
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        val user = UserModel(
                            firebaseUser.uid,
                            et_username.text.toString().trim { it <= ' ' },
                            et_email.text.toString().trim { it <= ' ' }
                            )
                        //store the datas
                        FirestoreClass().registerUser(this,user)

                        //showErrorSnackBar("You are registered successfully!",false)


//                        FirebaseAuth.getInstance().signOut()
//                        finish()
                    }
                    else{
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
            )
        }


    }


    fun userRegistrationSuccess(){
        showErrorSnackBar(resources.getString(R.string.register_success),false)
    }

}
