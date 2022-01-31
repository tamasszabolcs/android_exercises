package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

open class ProductsResponse<T>(
    @SerializedName("item_count")
    val itemCount: Int,
    @SerializedName("products")
    val products: T)