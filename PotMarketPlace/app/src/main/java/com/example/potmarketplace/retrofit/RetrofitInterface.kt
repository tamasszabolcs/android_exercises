package com.example.marketplace.retrofit

import com.example.marketplace.retrofit.models.RegisterModel
import com.example.marketplace.retrofit.models.RegisterResponse
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.retrofit.models.LoginResponse
import com.example.potmarketplace.retrofit.models.ResetModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @POST("user/login")
    fun login(@Body loginModel: LoginModel): Call<LoginResponse>

    @POST("user/register")
    fun register(@Body registerModel: RegisterModel): Call<RegisterResponse>

    @POST("user/reset")
    fun reset(@Body resetModel: ResetModel): Call<RegisterResponse>
}