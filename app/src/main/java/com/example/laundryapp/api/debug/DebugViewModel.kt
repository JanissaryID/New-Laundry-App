package com.example.laundryapp.api.debug

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.BUTTON_VISIBLE
import com.example.laundryapp.api.machine.MachineModel
import com.example.laundryapp.api.transaction.TransactionApp
import com.example.laundryapp.api.transaction.TransactionModel
import com.example.laundryapp.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DebugViewModel: ViewModel() {

    var machineListResponse: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
    var stateMachine: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

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
                    BUTTON_VISIBLE = true
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