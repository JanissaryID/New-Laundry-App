package com.example.laundryapp.api.debug

import com.google.gson.annotations.SerializedName

data class DebugModel(

	@field:SerializedName("response_code")
	val responseCode: String? = null,

	@field:SerializedName("log_response")
	val logResponse: String? = null,

	@field:SerializedName("response_body")
	val responseBody: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)
