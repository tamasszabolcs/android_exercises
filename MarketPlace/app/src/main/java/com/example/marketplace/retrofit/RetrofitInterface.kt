package com.example.marketplace.retrofit

import com.example.marketplace.retrofit.models.RegisterModel
import com.example.marketplace.retrofit.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterface {
    @POST("user/register")
    fun register(@Body registerModel: RegisterModel): Call<RegisterResponse>

}