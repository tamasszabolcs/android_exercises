package com.example.marketplace.retrofit

import com.example.marketplace.retrofit.models.RegisterModel
import com.example.marketplace.retrofit.models.RegisterResponse
import com.example.potmarketplace.models.ProductsResult
import com.example.potmarketplace.retrofit.models.*
import com.example.potmarketplace.utils.Constants
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface RetrofitInterface {
    @POST(Constants.LOGIN)
    fun login(@Body loginModel: LoginModel): Call<LoginResponse>

    @POST(Constants.REGISTRATION)
    fun register(@Body registerModel: RegisterModel): Call<RegisterResponse>

    @POST(Constants.RESET_PASSWORD)
    fun reset(@Body resetModel: ResetModel): Call<RegisterResponse>

    @POST(Constants.UPDATE_USERDATA)
    fun updateProfile(@Header("token") token: String,@Body updateProfileModel: UpdateProfileModel): Call<RegisterResponse>

    @GET(Constants.GET_PRODUCTS)
    fun getProducts(@Header("token") token: String, @Header ("limit")limit:Int): Call<ProductsResponse<List<ProductsResult>>>

    @GET(Constants.GET_PRODUCTS)
    fun getMyProducts(@Header("token") token: String, @Header ("limit")limit:Int ): Call<ProductsResponse<List<ProductsResult>>>

    @GET("user/reset")
    fun changePassword(@Header("token") token: String, @Header("new_password")password: String):Call<RegisterResponse>
}