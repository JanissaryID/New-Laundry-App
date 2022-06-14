package com.example.laundryapp.api.debug

import com.google.gson.annotations.SerializedName

data class DebugModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("response_code")
	val responseCode: Int? = null,

	@field:SerializedName("response_body")
	val responseBody: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("response_log")
	val responseLog: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)
