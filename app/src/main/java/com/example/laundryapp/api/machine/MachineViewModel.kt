package com.example.laundryapp.api.machine

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.transaction.TransactionViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MachineViewModel: ViewModel() {
    var machineListResponse: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
    var stateMachine: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getMachine(){
        try {
            MachineApp.CreateInstance().fetchMachine(
                store = STORE_ID,
                classes = if(CLASS_MACHINE == 0) false else true,
                type = if (MENU_TRANSACTION != "Dryer") false else true
            ).enqueue(object :
                Callback<ArrayList<MachineModel>> {
                override fun onResponse(call: Call<ArrayList<MachineModel>>, response: Response<ArrayList<MachineModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code Machine : ${response.code().toString()}")
                    stateMachine = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            machineListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${menuListResponse}")
                            stateMachine = 1
                        }
                        if (machineListResponse.isNullOrEmpty()){
                            stateMachine = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MachineModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMachine = 2
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

    fun updateMachine(
        idMachine: String,
        isPacket: Boolean,
        timeMachine: Int,
        transactionViewModel: TransactionViewModel,
        typePayment: Boolean,
        navController: NavController
    ){
        val bodyDataUpdate = MachineModel(machineStatus = true, isPacket = isPacket, priceTime = timeMachine)

        try {
            MachineApp.CreateInstance().updateMachine(id = idMachine, bodyDataUpdate).enqueue(object :
                Callback<MachineModel> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
                    Log.d("debug", "Code Update Machine ${response}")
                    if(response.code() == 200){
                        if (!BUTTON_VISIBLE){
                            transactionViewModel.updateTransaction(
                                navController = navController,
                                machineID = MACHINE_ID,
                                numbermachine = MACHINE_NUMBER
                            )
                        }
                        else{
                            transactionViewModel.insertTransaction(
                                classMachine = CLASS_MACHINE,
                                machineID = MACHINE_ID,
                                machinePacket = PRICE_PACKET,
                                navController = navController,
                                numbermachine = MACHINE_NUMBER,
                                price = PRICE,
                                storeID = STORE_ID,
                                transactionMenuMachine = MENU_MACHINE,
                                transactionClassmachine = if(CLASS_MACHINE == 1) true else false,
                                typePaymentTransaction = typePayment, //False For Cash
                                typetransaction = MENU_TRANSACTION
                            )
                        }

//                        navController.navigate(route = Screens.Machine.route){
//                            popUpTo(Screens.Machine.route) {
//                                inclusive = true
//                            }
//                        }
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
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