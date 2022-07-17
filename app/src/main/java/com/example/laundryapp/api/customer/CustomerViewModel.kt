package com.example.laundryapp.api.customer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.CUSTOMER_ID
import com.example.laundryapp.IS_DIALOG_OPEN
import com.example.laundryapp.navigation.Screens
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
                    stateCustomer = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            customerListResponse = response.body()!!
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
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
        }
    }

    fun insertCustomer(
        name: String,
        city: String,
        phone: String,
        navController: NavController
    ){
        val bodyDataInsert = CustomerModel(
            name = name,
            city = city,
            phone = phone
        )
        try {
            CustomerApp.CreateInstance().insertMenu(statusData = bodyDataInsert).enqueue(object :
                Callback<CustomerModel> {
                override fun onResponse(call: Call<CustomerModel>, response: Response<CustomerModel>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateCustomer = 0
                    if(response.code() == 201){
                        navController.navigate(route = Screens.Customer.route){
                            popUpTo(Screens.Customer.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                    if(response.code() == 400){
                        stateCustomer = 4
                    }
                }

                override fun onFailure(call: Call<CustomerModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateCustomer = 2
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
        }
    }

    fun updateCustomer(
        name: String,
        city: String,
        phone: String,
        navController: NavController
    ){
        val bodyDataUpdate = CustomerModel(
            name = name,
            city = city,
            phone = phone
        )
//        val bodyUpdate = MachineModelUpdate(machineStatus = true, isPacket = isPacket, priceTime = timeMachine)

        try {
            CustomerApp.CreateInstance().updateCustomer(id = CUSTOMER_ID, bodyDataUpdate).enqueue(object : Callback<CustomerModel> {
                override fun onResponse(call: Call<CustomerModel>, response: Response<CustomerModel>) {
//                    Log.d("debug", "Code Update ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Customer.route){
                            popUpTo(Screens.Customer.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                }

                override fun onFailure(call: Call<CustomerModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
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

    fun deleteCustomer(idMenu: String, navController: NavController){
        try {
            CustomerApp.CreateInstance().deleteCustomer(id = idMenu).enqueue(object :
                Callback<CustomerModel> {
                override fun onResponse(call: Call<CustomerModel>, response: Response<CustomerModel>) {
//                    Log.d("debug", "Code Delete Menu ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Customer.route){
                            popUpTo(Screens.Customer.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                }

                override fun onFailure(call: Call<CustomerModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
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