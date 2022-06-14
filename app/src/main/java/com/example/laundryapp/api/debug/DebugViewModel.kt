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

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertDebug(
        responseCode: Int,
        responseBody: String,
        responseLog: String,
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val date = current.format(formatDay)
        val timeNow = current.format(formatTime)

//        Log.d("debug", "Type Transaction is in ViewModel $typetransaction")

        val bodyUpdate = DebugModel(
            date = date,
            time = timeNow,
            responseCode = responseCode,
            responseBody = responseBody,
            responseLog = responseLog
        )

        DebugApp.CreateInstance().insertDebug(bodyUpdate).enqueue(object :
            Callback<DebugModel> {
            override fun onResponse(call: Call<DebugModel>, response: Response<DebugModel>) {
                Log.d("debug", "Response Debug ${response}")
                if(response.code() == 201){
//                    navController.navigate(route = Screens.Home.route){
//                        popUpTo(Screens.Home.route) {
//                            inclusive = true
//                        }
//                    }
//                    BUTTON_VISIBLE = true
                }
            }

            override fun onFailure(call: Call<DebugModel>, t: Throwable) {
                Log.d("error", t.message.toString())
                if (t.message == t.message){

//                    BtnOnMachine.isEnabled =true

//                    Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}