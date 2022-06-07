package com.example.laundryapp.api.payment.response

import com.google.gson.annotations.SerializedName

data class ResponsePaymentQrisModel(

    @field:SerializedName("refid")
    val refid: Long? = null
)