package com.example.laundryapp.api.customer

import retrofit2.Call
import retrofit2.http.*

interface CustomerService {
    @GET("Customer")
    fun fetchCustomer(): Call<ArrayList<CustomerModel>>

    @POST("Customer")
    fun insertMenu(@Body statusData: CustomerModel): Call<CustomerModel>

    @PATCH("Customer/{id}")
    fun updateCustomer(
        @Path("id") id: String?, @Body updateData : CustomerModel
    ): Call<CustomerModel>

    @DELETE("Customer/{id}")
    fun deleteCustomer( @Path("id") id: String? ): Call<CustomerModel>
}