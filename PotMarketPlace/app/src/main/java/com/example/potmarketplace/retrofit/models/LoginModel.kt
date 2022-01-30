package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("username")
    val userName: String,
    @SerializedName("password")
    val password: String
)
