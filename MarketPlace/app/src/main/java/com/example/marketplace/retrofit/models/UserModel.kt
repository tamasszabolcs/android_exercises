package com.example.marketplace.retrofit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel (
    val id: String = "",
    val userName:String = "",
    val email:String = "",
    val image:String = "",
    val phoneNumber:Long = 0,
    val profileCompleted:Int = 0
        ): Parcelable