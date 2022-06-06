package com.example.laundryapp.api.store

import retrofit2.Call
import retrofit2.http.*

interface StoreService {
    @GET("Store")
    fun fetchStore(
        @Query(value="store_city", encoded=true) city: String?,
        @Query(value="password_owner", encoded=true) password: String?,
    ): Call<ArrayList<StoreModel>>
}