package com.example.potmarketplace.retrofit

import android.util.Log
import com.example.marketplace.retrofit.Proxy
import com.example.marketplace.retrofit.models.RegisterModel
import com.example.marketplace.retrofit.models.RegisterResponse
import com.example.potmarketplace.retrofit.models.*
import io.reactivex.Single

object UserAccessLayer {

    fun getLoginObservable(
        loginModel: LoginModel
    ): Single<LoginResponse> {
        val loginResponse = Proxy.login(loginModel)
        return Single.create { emitter ->
            if (loginResponse?.token == null) {
                emitter.onError(Exception("Hiba"))
            } else {
                emitter.onSuccess(loginResponse)
            }
            Log.d("asd", loginResponse.toString())
        }
    }

    fun getRegisterObservable(
        registerModel: RegisterModel
    ): Single<RegisterResponse> {
        val registerResponse = Proxy.register(registerModel)
        return Single.create { emitter ->
            if (registerResponse?.code !=200) {
                emitter.onError(Exception("Hiba"))
            } else {
                emitter.onSuccess(registerResponse)
            }
            Log.d("asd", registerResponse.toString())
        }
    }

    fun getResetObservable(
        resetModel: ResetModel
    ): Single<RegisterResponse> {
        val resetResponse = Proxy.reset(resetModel)
        return Single.create { emitter ->
            if (resetResponse?.code !=200) {
                emitter.onError(Exception("Hiba"))
            } else {
                emitter.onSuccess(resetResponse)
            }
            Log.d("asd", resetResponse.toString())
        }
    }

    fun getUpdateProfileObservable(
        token: String,
        updateProfileModel: UpdateProfileModel
    ): Single<RegisterResponse> {
        val updateResponse = Proxy.updateProfile(token, updateProfileModel)
        return Single.create { emitter ->
            if (updateResponse?.code !=200) {
                emitter.onError(Exception("Hiba"))
            } else {
                emitter.onSuccess(updateResponse)
            }
            Log.d("asd", updateResponse.toString())
        }
    }

    fun getChangePasswordObservable(
        token: String,
        password: String
    ): Single<RegisterResponse> {
        val changeResponse = Proxy.changePassword(token, password)
        return Single.create { emitter ->
            if (changeResponse?.code !=200) {
                emitter.onError(Exception("Hiba"))
            } else {
                emitter.onSuccess(changeResponse)
            }
            Log.d("asd",changeResponse.toString())
        }
    }
}