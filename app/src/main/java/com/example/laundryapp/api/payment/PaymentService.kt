package com.example.laundryapp.api.payment

import com.example.laundryapp.api.payment.request.PaymentQrisModel
import com.example.laundryapp.api.payment.request.PaymentQrisRawModel
import com.example.laundryapp.api.payment.response.ResponsePaymentQrisGetModel
import com.example.laundryapp.api.payment.response.ResponsePaymentQrisModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PaymentService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("generate/")
    fun Qris(@Header("Authorization") authHeader: String, @Body qrData: PaymentQrisModel) : Call<PaymentQrisRawModel>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("check-status/")
    fun CheckPayment(@Header("Authorization") authHeader: String, @Body statusData: ResponsePaymentQrisModel) : Call<ResponsePaymentQrisGetModel>
}