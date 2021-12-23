package com.example.marketplace.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    const val users: String = "users" // "users" key
    const val project_preferences: String = "projectPreferences"
    const val logged_in_username: String = "loggedIn_username"
    const val extra_user_details: String = "extra_user_details"
    const val read_storage_permission_code = 2
    const val pick_image_request_code = 1
    const val userName: String = "userName"
    const val phoneNumber: String = "phoneNumber"
    const val user_profile_image: String= "userProfileImage"
    const val image: String = "image"
    const val complete_profile: String = "profileCompleted"

    fun showImageChooser(activity: Activity){
        //intent for launching the image selection
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            //pick from mediastore
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        //launching the image selection using the image request code
        activity.startActivityForResult(galleryIntent, pick_image_request_code)
    }

    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        //MimeTypeTap :type to file
        //getSingleton(): singleton instance of MimeTypeMap

        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}