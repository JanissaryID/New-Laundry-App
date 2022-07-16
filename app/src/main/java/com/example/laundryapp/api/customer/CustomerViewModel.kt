package com.example.laundryapp.api.customer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CustomerViewModel: ViewModel() {

    var customerListResponse: ArrayList<CustomerModel> by mutableStateOf(arrayListOf())
    var stateCustomer: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getCustomer(){
        try {
            CustomerApp.CreateInstance().fetchCustomer().enqueue(object :
                Callback<ArrayList<CustomerModel>> {
                override fun onResponse(call: Call<ArrayList<CustomerModel>>, response: Response<ArrayList<CustomerModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code Machine : ${response.code().toString()}")
                    stateCustomer = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            customerListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${menuListResponse}")
                            stateCustomer = 1
                        }
                        if (customerListResponse.isNullOrEmpty()){
                            stateCustomer = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<CustomerModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateCustomer = 2
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