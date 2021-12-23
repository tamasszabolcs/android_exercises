package com.example.marketplace.firestore
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.R
import com.example.marketplace.activities.*
import com.example.marketplace.retrofit.models.UserModel
import com.example.marketplace.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirestoreClass: BaseActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity,userInfo:UserModel){
        //user a collection neve, ha a collection letezik nem lesz uj
        mFireStore.collection(Constants.users)
            .document(userInfo.id)
                //store data
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user!",
                    e
                )
            }
    }

    fun getCurrentUserId():String{
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId = ""
        if(currentUser != null){
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

    fun getUserDetails(activity: Activity){
        //collection name
        mFireStore.collection(Constants.users)
        //document id
            .document(getCurrentUserId()).get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName,document.toString())
                //new user based on document to object to UserModel
                val user = document.toObject(UserModel::class.java)

                val sharedPreferences = activity.getSharedPreferences(Constants.project_preferences,Context.MODE_PRIVATE)

                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                //key: logged_in_username
                //value : user.userName

                if (user != null) {
                    editor.putString(
                        Constants.logged_in_username,
                        "${user.userName}"
                    )
                }

                when(activity){
                    is LoginActivity ->{
                        if (user != null) {
                            activity.userLoggedInSuccess(user)
                        }
                    }
                }
            }
    }

    fun updateUserProfileData(activity: Activity,userHashMap: HashMap<String,Any>){
        mFireStore.collection(Constants.users).document(getCurrentUserId()).update(userHashMap)
            .addOnSuccessListener {
                when(activity){
                    is ProfileActivity ->{
                        activity.userprofileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while updating the user details",
                    e
                )
            }
    }

    fun uploadImageToStorage(activity: Activity, imageFileUri: Uri?){
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            Constants.user_profile_image + System.currentTimeMillis() + "."
            + Constants.getFileExtension(
                activity,imageFileUri
            )
        )
        sRef.putFile(imageFileUri!!).addOnSuccessListener {
            taskSnapshot ->
            //image upload success
            Log.e(
                "Firebase_Image_URL",
                taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
            )

            taskSnapshot.metadata!!.reference!!.downloadUrl
                .addOnSuccessListener { uri ->
                    Log.e("Image URL",uri.toString())
                    when(activity){
                        is ProfileActivity ->{
                            activity.imageUploadSuccess(uri.toString())
                        }
                    }
                }
        }
            .addOnFailureListener{ exception ->
                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }

}