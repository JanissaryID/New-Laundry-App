package com.example.laundryapp.api.qris

import com.google.gson.annotations.SerializedName

data class QrisModel(

	@field:SerializedName("client_key")
	val clientKey: String? = null,

	@field:SerializedName("merchant_id")
	val merchantId: String? = null,

	@field:SerializedName("client_id")
	val clientId: String? = null,

	@field:SerializedName("qris_store")
	val qrisStore: String? = null
)
