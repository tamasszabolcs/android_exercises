package com.example.marketplace.retrofit

import com.example.marketplace.retrofit.models.RegisterModel
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.retrofit.models.ResetModel
import com.example.potmarketplace.retrofit.models.UpdateProfileModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.URL

object Proxy {
    private const val URL = "https://pure-gorge-51703.herokuapp.com/"

    private val retrofit by lazy {
        Retrofit .Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }
    private val service: RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }

    fun login(loginModel: LoginModel) = service.login(loginModel).execute().body()

    fun register(registerModel: RegisterModel) = service.register(registerModel).execute().body()

    fun reset(resetModel: ResetModel) = service.reset(resetModel).execute().body()

    fun updateProfile(token: String, updateProfileModel: UpdateProfileModel) = service.updateProfile(token, updateProfileModel).execute().body()

    fun getProducts(token: String) = service.getProducts(token,10000).execute().body()

    fun getMyProducts(token: String,filter: String?) = service.getMyProducts(token, 10000).execute().body()

    fun changePassword(token: String, password: String) = service.changePassword(token, password).execute().body()

}
