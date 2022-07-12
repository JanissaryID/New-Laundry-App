package com.example.laundryapp.api.store

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineModel
import com.example.laundryapp.api.price.PriceApp
import com.example.laundryapp.api.price.PriceModel
import com.example.laundryapp.api.qris.QrisViewModel
import com.example.laundryapp.proto.ProtoViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class StoreViewModel: ViewModel() {
    var stateStore: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getStore(
        qrisViewModel: QrisViewModel
    ){
        try {
            StoreApp.CreateInstance().fetchStore(
                city = "Semarang",
                password = "semarang"
            ).enqueue(object :
                Callback<ArrayList<StoreModel>> {
                override fun onResponse(call: Call<ArrayList<StoreModel>>, response: Response<ArrayList<StoreModel>>) {
                    Log.d("debug", "Response Store ${response}")
                    Log.d("debug", "Response Store ${response.code()}")
                    if(response.code() == 200){
                        val storeResponse = response.body()!!
                        if (!storeResponse[0].storeName.isNullOrEmpty()){
                            STORE_NAME = storeResponse[0].storeName.toString()
                            STORE_ID = storeResponse[0].id.toString()
                            STORE_ADDRESS = storeResponse[0].storeAddress.toString()
                            if (!STORE_ID.isNullOrEmpty()){
                                if (QRIS_CLIENT_KEY.isNullOrEmpty() && QRIS_CLIENT_ID.isNullOrEmpty() && QRIS_MERCHANT_ID.isNullOrEmpty()){
                                    qrisViewModel.getQris()
                                }
                            }
                            stateStore = 1
                        }
                        else if(storeResponse[0].storeName.isNullOrEmpty()){
                            stateStore = 3
                        }
                    }
                    else if(response.code() == 400)
                    {
                        stateStore = 4
                    }
                }

                override fun onFailure(call: Call<ArrayList<StoreModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateStore = 2
                        getStore(qrisViewModel = qrisViewModel)
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