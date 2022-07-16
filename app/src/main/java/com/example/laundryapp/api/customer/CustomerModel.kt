package com.example.laundryapp.api.customer

import com.google.gson.annotations.SerializedName

data class CustomerModel(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

//	@field:SerializedName("transaction")
//	val transaction: List<Any?>? = null
)
