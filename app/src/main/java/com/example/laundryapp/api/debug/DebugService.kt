package com.example.laundryapp.api.debug

import com.example.laundryapp.api.transaction.TransactionModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DebugService {

    @POST("Debug")
    fun insertDebug(@Body statusData: DebugModel) : Call<DebugModel>
}