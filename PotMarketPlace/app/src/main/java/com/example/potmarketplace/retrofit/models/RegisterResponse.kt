package com.example.marketplace.retrofit.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("code")
    val code: Int
)