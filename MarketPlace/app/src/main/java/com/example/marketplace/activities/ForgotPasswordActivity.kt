package com.example.marketplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.marketplace.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val btn_submit = findViewById<Button>(R.id.btn_submit)
        val et_email = findViewById<EditText>(R.id.et_email)
        btn_submit.setOnClickListener{
            val email: String = et_email.text.toString().trim{ it <= ' '}
            if(email.isEmpty()){
                showErrorSnackBar(resources.getString(R.string.email_empty),true)

            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                    task -> if(task.isSuccessful){
                        Toast.makeText(this,resources.getString(R.string.email_sent_success),Toast.LENGTH_LONG).show()
                        finish()
                }
                    else{
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                }
                }
            }
        }
    }


}