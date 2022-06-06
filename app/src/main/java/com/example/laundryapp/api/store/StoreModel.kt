package com.example.laundryapp.api.store

import com.google.gson.annotations.SerializedName

data class StoreModel(
    @field:SerializedName("store_city")
    val storeCity: String? = null,

    @field:SerializedName("password_owner")
    val passwordOwner: String? = null,

    @field:SerializedName("store_address")
    val storeAddress: String? = null,

    @field:SerializedName("store_name")
    val storeName: String? = null,

    @field:SerializedName("_id")
    val id: String? = null
)