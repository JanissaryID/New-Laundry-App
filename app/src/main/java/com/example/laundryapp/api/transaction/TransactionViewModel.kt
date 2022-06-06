package com.example.laundryapp.api.transaction

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.laundryapp.DATE_PICK
import com.example.laundryapp.STORE_ID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class TransactionViewModel: ViewModel() {
    var transactionListResponse: ArrayList<TransactionModel> by mutableStateOf(arrayListOf())
    var stateTransaction: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getTransaction(){
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
}