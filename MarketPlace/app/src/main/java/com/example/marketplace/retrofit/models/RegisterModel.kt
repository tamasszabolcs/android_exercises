package com.example.marketplace.retrofit.models

import com.google.gson.annotations.SerializedName

class RegisterModel (
    //ha nem ugyanaz az adattag neve akkor ugyanazza teszi mint az apiba
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("firebase_token")
    val firebaseToken: String
)