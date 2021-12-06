package com.example.marketplace.retrofit

import android.util.Log
import com.example.marketplace.retrofit.models.RegisterResponse
import io.reactivex.Single

object UserAccessLayer {

    fun getRegisterObservable(
        username: String,
        email: String,
        password: String,
        phoneNumber: String,
        firebaseToken: String
    ): Single<RegisterResponse> {
        val registerResponse = Proxy.register(username, email, password, phoneNumber, firebaseToken)
        return Single.create { emitter ->
            if (registerResponse == null || registerResponse.code != 200) {
                emitter.onError(Exception("Hiba"))
            } else {
                emitter.onSuccess(registerResponse)
            }
            Log.d("asd", registerResponse.toString())
        }
    }
}