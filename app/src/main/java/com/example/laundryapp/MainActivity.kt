package com.example.laundryapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.menu.MenuViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.api.qris.QrisViewModel
import com.example.laundryapp.api.store.StoreViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.navigation.NavGraphSetup
import com.example.laundryapp.proto.ProtoViewModel
import com.example.laundryapp.ui.theme.LaundryAppTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    val storeViewModel by viewModels<StoreViewModel>()
    val menuViewModel by viewModels<MenuViewModel>()
    val priceViewModel by viewModels<PriceViewModel>()
    val machineViewModel by viewModels<MachineViewModel>()
    val transactionViewModel by viewModels<TransactionViewModel>()
    val paymentViewModel by viewModels<PaymentViewModel>()
    val qrisViewModel by viewModels<QrisViewModel>()

    private lateinit var protoViewModel: ProtoViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
        protoViewModel.keyUrl.observe(this, {
            KEY_URL = it.keyUrl
            STORE_CITY = it.storeCity
            STORE_PASSWORD = it.storePassword
            Log.d("debug", "url $KEY_URL")
            Log.d("debug", "city $STORE_CITY")
            Log.d("debug", "pass $STORE_PASSWORD")
            if (STORE_NAME.isNullOrEmpty() && STORE_ID.isNullOrEmpty()){
                storeViewModel.getStore(qrisViewModel = qrisViewModel)
            }
        })



        setContent {
            LaundryAppTheme(darkTheme = false, dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraphSetup(
                        navController = navController,
                        storeViewModel = storeViewModel,
                        qrisViewModel = qrisViewModel,
                        menuViewModel = menuViewModel,
                        priceViewModel = priceViewModel,
                        transactionViewModel = transactionViewModel,
                        machineViewModel = machineViewModel,
                        paymentViewModel = paymentViewModel,
//                    excelViewModel = excelViewModel,
                        protoViewModel = protoViewModel,
                        componentActivity = this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LaundryAppTheme {
        Greeting("Android")
    }
}