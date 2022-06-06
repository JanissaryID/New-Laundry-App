package com.example.laundryapp.api.price

import retrofit2.Call
import retrofit2.http.*

interface PriceService {
    @GET("Price")
    fun fetchPrice(
        @Query(value = "\$lookup", encoded = true) lookup: String?,
        @Query(value = "Menu[0]", encoded = true) menu: String?,
        @Query(value="is_packet", encoded=true) packet: Boolean?,
        @Query(value="price_store", encoded=true) store: String?,
        @Query(value="price_class_machine", encoded=true) classPrice: Boolean?
    ): Call<ArrayList<PriceModel>>
}