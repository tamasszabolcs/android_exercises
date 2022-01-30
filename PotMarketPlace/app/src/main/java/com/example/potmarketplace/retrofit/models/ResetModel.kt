package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

class ResetModel (
    //ha nem ugyanaz az adattag neve akkor ugyanazza teszi mint az apiba
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
)
