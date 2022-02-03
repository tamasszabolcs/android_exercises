package com.example.potmarketplace.retrofit

import android.util.Log
import com.example.marketplace.retrofit.Proxy
import com.example.marketplace.retrofit.models.RegisterResponse
import com.example.potmarketplace.models.Product
import com.example.potmarketplace.retrofit.models.UpdateProfileModel
import io.reactivex.Single

object ProductAccessLayer {

    fun getProductsObservable(
        token: String
    ): Single<MutableList<Product>> {
        val getProductsResponse = Proxy.getProducts(token)
        return Single.create { emitter ->
            if (getProductsResponse == null) {
                emitter.onError(Exception("Hiba"))
            } else {
                val productsList = mutableListOf<Product>()
                getProductsResponse.products.forEach{
                    productsList.add(Product(it.rating,it.amountType,it.priceType,it.productId,it.ownerName,it.isActive,it.pricePerUnit,it.units,it.description,it.title))
                }
                emitter.onSuccess(productsList)
            }

        }
    }

    fun getMyProductsObservable(
        token: String,
        filter: String?
    ): Single<MutableList<Product>> {
        val getMyProductsResponse = Proxy.getMyProducts(token,filter)
        return Single.create { emitter ->
            Log.d("asdasd",getMyProductsResponse.toString())
            if (getMyProductsResponse == null) {
                emitter.onError(Exception("Hiba"))
            } else {
                val productsList = mutableListOf<Product>()
                getMyProductsResponse.products.forEach{
                    productsList.add(Product(it.rating,it.amountType,it.priceType,it.productId,it.ownerName,it.isActive,it.pricePerUnit,it.units,it.description,it.title))
                }
                emitter.onSuccess(productsList)
            }

        }
    }
}