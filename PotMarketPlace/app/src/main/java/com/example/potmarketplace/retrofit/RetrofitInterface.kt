package com.example.marketplace.retrofit

import com.example.marketplace.retrofit.models.RegisterModel
import com.example.marketplace.retrofit.models.RegisterResponse
import com.example.potmarketplace.models.ProductsResult
import com.example.potmarketplace.retrofit.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @POST("user/login")
    fun login(@Body loginModel: LoginModel): Call<LoginResponse>

    @POST("user/register")
    fun register(@Body registerModel: RegisterModel): Call<RegisterResponse>

    @POST("user/reset")
    fun reset(@Body resetModel: ResetModel): Call<RegisterResponse>

    @POST("user/update")
    fun updateProfile(@Header("token") token: String,@Body updateProfileModel: UpdateProfileModel): Call<RegisterResponse>

    @GET("products")
    fun getProducts(@Header("token") token: String, @Header ("limit")limit:Int): Call<ProductsResponse<List<ProductsResult>>>
}