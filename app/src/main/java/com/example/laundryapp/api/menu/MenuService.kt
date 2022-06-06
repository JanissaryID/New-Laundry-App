package com.example.laundryapp.api.menu

import retrofit2.Call
import retrofit2.http.*

interface MenuService {
    @GET("Menu")
    fun fetchMenu(
        @Query(value="menu_store", encoded=true) store: String?,
    ): Call<ArrayList<MenuModel>>
}