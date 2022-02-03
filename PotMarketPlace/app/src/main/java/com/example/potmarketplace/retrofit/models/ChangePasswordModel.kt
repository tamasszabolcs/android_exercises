package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

data class ChangePasswordModel (
    @SerializedName("token")
    val token: String,

    @SerializedName("new_password")
    val password: String
)