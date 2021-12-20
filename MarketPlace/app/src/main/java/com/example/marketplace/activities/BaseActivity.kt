package com.example.marketplace.activities

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.marketplace.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    private lateinit var mProgressDialog: Dialog
    fun showErrorSnackBar(message:String,errorMessage: Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
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


//    fun showProgressDialog(text: String){
//        mProgressDialog = Dialog(this)
//        //meghivjuk a layoutra
//        mProgressDialog.setContentView(R.layout.progress)
//
//        val tv_progress_text = findViewById<TextView>(R.id.tv_progress_text)
//        tv_progress_text.text = text
//
//        mProgressDialog.setCancelable(false)
//        mProgressDialog.setCanceledOnTouchOutside(false)
//
//        //display
//        mProgressDialog.show()
//
//    }
//
//    fun hideProgressDialog(){
//        mProgressDialog.dismiss()
//    }
}