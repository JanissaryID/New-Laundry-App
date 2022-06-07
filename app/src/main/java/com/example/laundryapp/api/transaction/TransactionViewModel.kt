package com.example.laundryapp.api.transaction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.DATE_PICK
import com.example.laundryapp.MACHINE_PACKET
import com.example.laundryapp.STORE_ID
import com.example.laundryapp.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionViewModel: ViewModel() {
    var transactionListResponse: ArrayList<TransactionModel> by mutableStateOf(arrayListOf())
    var stateTransaction: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getTransactionActive(){
        try {
            Log.d("debug", "Date Transaction : ${DATE_PICK}")
            TransactionApp.CreateInstance().fetchTransaction(lookup = "*",store = STORE_ID, date = "$DATE_PICK", finish = true).enqueue(object :
                Callback<ArrayList<TransactionModel>> {
                override fun onResponse(call: Call<ArrayList<TransactionModel>>, response: Response<ArrayList<TransactionModel>>) {
//                    Log.d("debug", "url : ${response}")
                    Log.d("debug", "Code : ${response.code()}")
                    stateTransaction = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            transactionListResponse = response.body()!!
//                            EXCEL_VALUE = transactionListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
                            Log.d("debug", "Transaction Data : ${transactionListResponse}")
                            stateTransaction = 1
                        }
                        if (transactionListResponse.isNullOrEmpty()){
                            stateTransaction = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<TransactionModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateTransaction = 2
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTransaction(
        transactionClassmachine: Boolean,
        classMachine: Int,
        numbermachine: Int,
        price: String,
        typetransaction: String,
        typePaymentTransaction: Boolean,
        navController: NavController,
        transactionMenuMachine: String,
        storeID: String,
        machinePacket: Boolean,
        machineID: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)

//        Log.d("debug", "Type Transaction is in ViewModel $typetransaction")

        val bodyUpdate = TransactionModel(
            transactionStore = storeID,
            transactionTypePayment = if(typePaymentTransaction) "Qris" else "Cash",
            transactionPrice = price,
            transactionNumberMachine = numbermachine,
            transactionClassMachine = if(transactionClassmachine) "Titan" else "Giant",
            transactionDate = date,
            transactionTypeMenu = typetransaction,
            transactionMenuMachine = transactionMenuMachine,
            isPacket = machinePacket,
            stepOne = false,
            transactionFinish = false,
            transactionIdMachine = machineID,
            classMachine = classMachine
        )

        TransactionApp.CreateInstance().insertTransactions(bodyUpdate).enqueue(object :
            Callback<TransactionModel> {
            override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {
                Log.d("debug", "Code Insert Transaction ${response}")
                if(response.code() == 201){
                    navController.navigate(route = Screens.Home.route){
                        popUpTo(Screens.Home.route) {
                            inclusive = true
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
                Log.d("error", t.message.toString())
                if (t.message == t.message){

//                    BtnOnMachine.isEnabled =true

//                    Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}