package com.example.laundryapp.api.payment.response

import com.google.gson.annotations.SerializedName

data class ResponsePaymentQrisGetModel(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("time")
    val time: Time? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Data(

    @field:SerializedName("ref")
    val ref: String? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("callback_response")
    val callbackResponse: String? = null,

    @field:SerializedName("merchant_trxid")
    val merchantTrxid: String? = null,

    @field:SerializedName("callback")
    val callback: String? = null,

    @field:SerializedName("tip")
    val tip: Int? = null
)

data class Time(

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("paid")
    val paid: String? = null
)