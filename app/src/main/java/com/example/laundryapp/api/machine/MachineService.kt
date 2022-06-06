package com.example.laundryapp.api.machine

import retrofit2.Call
import retrofit2.http.*

interface MachineService {
    @GET("Machine")
    fun fetchMachine(
        @Query(value="machine_store", encoded=true) store: String?,
    ): Call<ArrayList<MachineModel>>

    @PATCH("Machine/{id}")
    fun updateMachine(
        @Path("id") id: String?, @Body updateData : MachineModel
    ): Call<MachineModel>
}