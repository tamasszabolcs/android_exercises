package com.example.marketplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.marketplace.R
import com.example.marketplace.firestore.FirestoreClass
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
    private fun getUserDetails(){
        FirestoreClass().getUserDetails(this)
    }
    fun userDetailsSuccess(user: User) {

    }
}