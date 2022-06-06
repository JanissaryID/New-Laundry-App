package com.example.laundryapp.api.menu

import com.google.gson.annotations.SerializedName

data class MenuModel(

    @field:SerializedName("is_packet")
    val isPacket: Boolean? = null,

    @field:SerializedName("price_menu")
    val priceMenu: String? = null,

    @field:SerializedName("menu_store")
    val menuStore: String? = null,

    @field:SerializedName("is_dryer")
    val isDryer: Boolean? = null,

    @field:SerializedName("_id")
    val id: String? = null
)