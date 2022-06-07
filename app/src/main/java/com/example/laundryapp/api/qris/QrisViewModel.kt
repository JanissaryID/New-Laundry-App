package com.example.laundryapp.api.qris

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.laundryapp.QRIS_CLIENT_ID
import com.example.laundryapp.QRIS_CLIENT_KEY
import com.example.laundryapp.QRIS_MERCHANT_ID
import com.example.laundryapp.STORE_ID
import com.example.laundryapp.api.machine.MachineApp
import com.example.laundryapp.api.machine.MachineModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class QrisViewModel: ViewModel() {

    var qrisListResponse: ArrayList<QrisModel> by mutableStateOf(arrayListOf())
    var stateQris: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getQris(){
        try {
            QrisApp.CreateInstance().fetchQris(store = STORE_ID).enqueue(object :
                Callback<ArrayList<QrisModel>> {
                override fun onResponse(call: Call<ArrayList<QrisModel>>, response: Response<ArrayList<QrisModel>>) {
                    Log.d("debug", "url Qris : ${response}")
//                    Log.d("debug", "Code Machine : ${response.code().toString()}")
                    stateQris = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            if (!response.body().isNullOrEmpty()){
                                qrisListResponse = response.body()!!
                                QRIS_CLIENT_KEY = response.body()!![0].clientKey!!
                                QRIS_CLIENT_ID = response.body()!![0].clientId!!
                                QRIS_MERCHANT_ID = response.body()!![0].merchantId!!
                                stateQris = 1
                            }
                        }
                        if (qrisListResponse.isNullOrEmpty()){
                            stateQris = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<QrisModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateQris = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}