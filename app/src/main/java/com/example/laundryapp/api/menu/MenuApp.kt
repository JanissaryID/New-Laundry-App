package com.example.laundryapp.api.menu

import com.example.laundryapp.KEY_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MenuApp {
    //    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/a61eb959-29ce-4c54-b5ed-72c525faf455/"
    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/$KEY_URL/"

    fun CreateInstance(): MenuService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MenuService::class.java)
    }
}