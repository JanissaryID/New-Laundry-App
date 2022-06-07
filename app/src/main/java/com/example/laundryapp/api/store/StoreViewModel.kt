package com.example.laundryapp.api.store

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineModel
import com.example.laundryapp.api.qris.QrisViewModel
import com.example.laundryapp.proto.ProtoViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class StoreViewModel: ViewModel() {
    var storeListResponse: ArrayList<StoreModel> by mutableStateOf(arrayListOf())
    var stateStore: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getStore(protoViewModel: ProtoViewModel, qrisViewModel: QrisViewModel){
        try {
            stateStore = 0
            var viewModelJob = Job()
            val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

            uiScope.launch {
                withContext(Dispatchers.IO) {
                    while (true){
                        if (!KEY_URL.isNullOrEmpty()){
                            var response = StoreApp.CreateInstance().fetchStore(
                                city = STORE_CITY,
                                password = STORE_PASSWORD
                            ).execute()
//                            Log.d("debug", "Response ${response}")
                            if(response.code() == 200){
                                if (!response.body().isNullOrEmpty()){
                                    STORE_NAME = response.body()!![0].storeName!!
                                    STORE_ID = response.body()!![0].id!!
                                    storeListResponse = response.body()!!
                                    if (!STORE_ID.isNullOrEmpty()){
                                        if (QRIS_CLIENT_KEY.isNullOrEmpty() && QRIS_CLIENT_ID.isNullOrEmpty() && QRIS_MERCHANT_ID.isNullOrEmpty()){
                                            qrisViewModel.getQris()
                                        }
                                    }
//                                    Log.d("debug", "Store Name $STORE_NAME")
                                    stateStore = 1
                                }
                            }
                            else if(response.code() == 400)
                            {
                                stateStore = 4
                            }
                            else if(response.body().isNullOrEmpty()){
                                stateStore = 3
                            }
                            else{
                                stateStore = 2
                            }
                            break
                        }
                    }
                    delay(100)
                }
            }
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
        }
    }
}