package com.example.laundryapp.api.price

import com.google.gson.annotations.SerializedName

data class PriceModel(

    @field:SerializedName("dryer_normal")
    val dryerNormal: Boolean? = null,

    @field:SerializedName("is_packet")
    val isPacket: Boolean? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("price_time")
    val priceTime: Int? = null,

    @field:SerializedName("Menu")
    val menu: List<MenuItem?>? = null,

//	@field:SerializedName("Menu")
//	val menu: List<String>? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("price_class_machine")
    val priceClassMachine: Boolean? = null,

    @field:SerializedName("price_title")
    val priceTitle: String? = null,

    @field:SerializedName("price_store")
    val priceStore: String? = null,
)

data class MenuItem(

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