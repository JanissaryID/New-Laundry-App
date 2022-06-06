package com.example.laundryapp.api.price

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.STORE_ID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PriceViewModel: ViewModel() {
    var priceListResponse: ArrayList<PriceModel> by mutableStateOf(arrayListOf())
    var statePrice: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getPrice(
        classPrice: Boolean,
        idMenu: String,
        isPacket:Boolean
    ){
        try {
            PriceApp.CreateInstance().fetchPrice(
                lookup = "*",
                store = STORE_ID,
                classPrice = classPrice,
                menu = idMenu,
                packet = isPacket
            ).enqueue(object :
                Callback<ArrayList<PriceModel>> {
                override fun onResponse(call: Call<ArrayList<PriceModel>>, response: Response<ArrayList<PriceModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    statePrice = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            priceListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Price Response : ${priceListResponse}")
                            statePrice = 1
                        }
                        if (priceListResponse.isNullOrEmpty()){
                            statePrice = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<PriceModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        statePrice = 2
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