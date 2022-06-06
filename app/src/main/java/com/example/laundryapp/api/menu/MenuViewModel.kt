package com.example.laundryapp.api.menu

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

class MenuViewModel: ViewModel() {
    var menuListResponse: ArrayList<MenuModel> by mutableStateOf(arrayListOf())
    var stateMenu: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getMenu(){
        try {
            MenuApp.CreateInstance().fetchMenu(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMenu = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            menuListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${menuListResponse}")
                            stateMenu = 1
                        }
                        if (menuListResponse.isNullOrEmpty()){
                            stateMenu = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMenu = 2
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