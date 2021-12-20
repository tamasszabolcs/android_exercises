//package com.example.marketplace.retrofit
//
//import com.example.marketplace.retrofit.models.RegisterModel
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.create
//import java.net.URL
//
//object Proxy {
//    private const val URL = "https://pure-gorge-51703.herokuapp.com/"
//
//    private val retrofit by lazy {
//        Retrofit .Builder()
//            .baseUrl(URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(OkHttpClient())
//            .build()
//    }
//    private val service: RetrofitInterface by lazy {
//        retrofit.create(RetrofitInterface::class.java)
//    }
//
//    fun register(userName: String, email: String, password: String, phoneNumber: String, firebaseToken: String) = service.register(
//        RegisterModel( userName, email, password, phoneNumber, firebaseToken)).execute().body()
//}