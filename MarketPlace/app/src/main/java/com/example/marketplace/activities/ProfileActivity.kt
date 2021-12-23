package com.example.marketplace.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.marketplace.R
import com.example.marketplace.firestore.FirestoreClass
import com.example.marketplace.retrofit.models.UserModel
import com.example.marketplace.utils.Constants
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.IOException

import java.util.jar.Manifest

class ProfileActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mUserDetails: UserModel
    private var mSelectedImageFileUri: Uri?=null
    private var mUserProfileImageURL: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        if(intent.hasExtra(Constants.extra_user_details)){
            //get user detail from intent
            mUserDetails = intent.getParcelableExtra(Constants.extra_user_details)!!
        }

        val et_username = findViewById<EditText>(R.id.et_username)
        et_username.setText(mUserDetails.userName)

        val et_email = findViewById<EditText>(R.id.et_email)
        et_email.isEnabled = false; //user nem valtoztathatja meg
        et_email.setText(mUserDetails.email)

        //lehessen klikkelni a kepre
        val iv_user_photo = findViewById<ImageView>(R.id.iv_user_photo)
        iv_user_photo.setOnClickListener(this)
        val btn_save = findViewById<Button>(R.id.btn_save)
        btn_save.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(v!=null){
            when(v.id){
                R.id.iv_user_photo ->{
                    //permission allowed or need to request
                    //read_external_storage permission
                    if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
//                        showErrorSnackBar("You already have the storage permission!",false)
                        Constants.showImageChooser(this)
                    }
                    else{
                        //request permission
                        //manifestbe kell legyenek nem az appba

                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.read_storage_permission_code

                        )
                    }
                }
                R.id.btn_save ->{



                    if(validateUserProfileDetails()){
                        if(mSelectedImageFileUri !=null){
                            FirestoreClass().uploadImageToStorage(this,mSelectedImageFileUri)
                        }
                        else{
                            updateUserProfileDetails()
                        }
                    }
                }
            }
        }
    }

    private fun updateUserProfileDetails(){
        //HashMap = collection which contains pairs of object(key,value)
        val userHashMap = HashMap<String,Any>()

        val userName = et_username.text.toString().trim(){ it <= ' '}
        val phoneNumber = et_mobile_number.text.toString().trim{ it <= ' '}

        if(userName.isNotEmpty()){
            userHashMap[Constants.userName] = userName.toString()
        }

        if(phoneNumber.isNotEmpty()){
            userHashMap[Constants.phoneNumber] = phoneNumber.toLong()
        }

        if(mUserProfileImageURL.isNotEmpty()){
            userHashMap[Constants.image] = mUserProfileImageURL
        }

        userHashMap[Constants.complete_profile] = 1

        FirestoreClass().updateUserProfileData(this,userHashMap)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == Constants.read_storage_permission_code){
            if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                showErrorSnackBar("Permission is granted!",false)
                Constants.showImageChooser(this)
            }
            else{
                Toast.makeText(
                    this,
                    resources.getString(R.string.read_storage_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode == Constants.pick_image_request_code){
                if(data != null){
                    try{
                        mSelectedImageFileUri = data.data

                        iv_user_photo.setImageURI(mSelectedImageFileUri)
                    }
                    catch (e: IOException){
                        e.printStackTrace()
                        Toast.makeText(
                            this,
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun validateUserProfileDetails():Boolean{
        return when{
            TextUtils.isEmpty(et_mobile_number.text.toString().trim { it <= ' ' }) ->{
                showErrorSnackBar(resources.getString(R.string.mobile_number_empty),true)
                false
            }
            TextUtils.isEmpty(et_username.text.toString().trim { it <= ' ' }) ->{
                showErrorSnackBar(resources.getString(R.string.username_empty),true)
                false
            }
            else ->{
                true
            }
        }
    }
    fun userprofileUpdateSuccess(){
        Toast.makeText(
            this,
            resources.getString(R.string.profile_updated_success),
            Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    fun imageUploadSuccess(imageURL: String){
//        Toast.makeText(
//            this,
//            resources.getString(R.string.image_uploaded_success),
//            Toast.LENGTH_SHORT
//        ).show()

        mUserProfileImageURL = imageURL
        updateUserProfileDetails()
    }
}

