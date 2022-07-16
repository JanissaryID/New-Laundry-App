package com.example.laundryapp.api.customer

import retrofit2.Call
import retrofit2.http.GET

interface CustomerService {
    @GET("Customer")
    fun fetchCustomer(): Call<ArrayList<CustomerModel>>
}