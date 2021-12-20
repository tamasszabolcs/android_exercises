package com.example.marketplace.firestore
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.R
import com.example.marketplace.activities.BaseActivity
import com.example.marketplace.activities.LoginActivity
import com.example.marketplace.activities.RegisterActivity
import com.example.marketplace.retrofit.models.UserModel
import com.example.marketplace.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

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

                when(activity){
                    is LoginActivity ->{
                        if (user != null) {
                            activity.userLoggedInSuccess(user)
                        }
                    }
                }
            }
    }

}