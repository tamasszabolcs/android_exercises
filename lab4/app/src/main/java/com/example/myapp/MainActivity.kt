package com.example.myapp

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val ed_name = findViewById<EditText>(R.id.name)
    val started_button = findViewById<Button>(R.id.btnGetStarted)

    private lateinit var phone: TextView
    private lateinit var select: Button
    companion object{
        const val result = 1
    }
    private var cursor: Cursor? = null
    private var phoneNum: String? = null
    private lateinit var uri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        started_button.setOnClickListener(this)

        phone = findViewById(R.id.name)
        select = findViewById(R.id.btnChoose)

        select.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val username = ed_name.text.toString()

        if(ed_name.text.toString().isEmpty()){
            Toast.makeText(this,"Please enter your name!", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("keyname",username)
            startActivity(intent)
            finish()
        }

        val it = Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        startActivityForResult(it, result)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (resultCode == RESULT_OK){

                contactSelected(data)


        }
        else{
            Toast.makeText(this,"Failed to select",Toast.LENGTH_SHORT).show()
        }
    }

    fun contactSelected(data: Intent?){

        try {
           val uri = data!!.data
           val cursor = contentResolver.query(uri!!,null,null,null,null)
            if (cursor != null) {
                cursor.moveToFirst()
            }

            val index = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val phoneNum = cursor.getString(index)

            phone.setText(phoneNum)


        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}