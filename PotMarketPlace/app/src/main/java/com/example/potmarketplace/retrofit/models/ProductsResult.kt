package com.example.potmarketplace.retrofit.models

import com.google.gson.annotations.SerializedName

data class ProductsResult(
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("amount_type")
    val amountType: String,
    @SerializedName("price_type")
    val priceType: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("username")
    val ownerName: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("price_per_unit")
    val pricePerUnit : String,
    @SerializedName("units")
    val units: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String)
