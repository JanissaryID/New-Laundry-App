package com.example.laundryapp.api.payment.request

import com.google.gson.annotations.SerializedName

data class PaymentQrisModel(

	@field:SerializedName("ref")
	val ref: String? = null,

	@field:SerializedName("nominal")
	val nominal: Int? = null,

	@field:SerializedName("merchantid")
	val merchantid: String? = null,

	@field:SerializedName("expire")
	val expire: Int? = null,

	@field:SerializedName("callback")
	val callback: String? = null,

	@field:SerializedName("tip")
	val tip: Int? = null
)
