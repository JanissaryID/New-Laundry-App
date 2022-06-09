package com.example.laundryapp.api.transaction

import com.example.laundryapp.api.machine.MachineModel
import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("Transaction")
    fun fetchTransaction(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value="transaction_store", encoded=true) store: String?,
        @Query(value="transaction_date", encoded=true) date: String?,
        @Query(value="transaction_finish", encoded=true) finish: Boolean?
    ): Call<ArrayList<TransactionModel>>

    @POST("Transaction")
    fun insertTransactions(@Body statusData: TransactionModel) : Call<TransactionModel>

    @PATCH("Transaction/{id}")
    fun updateTransaction(
        @Path("id") id: String?, @Body updateData : TransactionModel
    ): Call<TransactionModel>

}