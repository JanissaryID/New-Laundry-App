package com.example.laundryapp.api.qris

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QrisService{

    @GET("Qris")
    fun fetchQris(
        @Query(value="qris_store", encoded=true) store: String?,
    ): Call<ArrayList<QrisModel>>
}