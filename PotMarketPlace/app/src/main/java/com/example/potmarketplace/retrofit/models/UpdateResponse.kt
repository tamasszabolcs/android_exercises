package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

open class UpdateResponse<T>(
    @SerializedName("code")
    val code: Int

)