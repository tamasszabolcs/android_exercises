package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

data class UpdateProfileModel(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)
