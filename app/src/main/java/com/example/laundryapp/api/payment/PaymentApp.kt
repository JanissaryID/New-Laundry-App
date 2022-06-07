package com.example.laundryapp.api.payment

import com.example.laundryapp.KEY_URL
import com.example.laundryapp.api.menu.MenuService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PaymentApp {

    //    private var BASE_URL = "http://${LIST_VALUE_ROOM_SETTING_GET[0].valueSetting}:8000"
    private const val BASE_URL = "https://api.paydia.id"

    var INSTANCE: PaymentService? = null

    fun CreateInstance(): PaymentService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        INSTANCE = retrofit.create(PaymentService::class.java)
        return INSTANCE as PaymentService
    }

    fun DestroyInstance(){
        INSTANCE = null
    }
}