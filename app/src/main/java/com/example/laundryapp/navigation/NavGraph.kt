package com.example.laundryapp.navigation

import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.laundryapp.*
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.menu.MenuViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.api.qris.QrisViewModel
import com.example.laundryapp.api.store.StoreViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.proto.ProtoViewModel
import com.example.laundryapp.screens.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    priceViewModel: PriceViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel,
    protoViewModel: ProtoViewModel,
    customerViewModel: CustomerViewModel
) {
    val context = LocalContext.current

    val errorGetData = 3

    NavHost(navController = navController, startDestination = Screens.Home.route){

        composable(
            route = Screens.Home.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                priceViewModel.priceListResponse.clear()
                priceViewModel.statePrice = 0

                CHECK_PAYMENT = false

                EDIT_CUSTOMER = false

                CUSTOMER_CITY = ""
                CUSTOMER_ID = ""
                CUSTOMER_PHONE = ""
                CUSTOMER_NAME = ""
            }
//            menuViewModel.getMenu()
            ScreenHome(
                navController = navController,
            )
        }

        composable(
            route = Screens.Menu.route,
        ){
            if (!STORE_ID.isNullOrEmpty()){
                LaunchedEffect(key1 = STORE_ID){
                    priceViewModel.getPrice(classPrice = if(CLASS_MACHINE == 0) false else true)

                    CHECK_PAYMENT = false

                    CUSTOMER_CITY = ""
                    CUSTOMER_ID = ""
                    CUSTOMER_PHONE = ""
                    CUSTOMER_NAME = ""

                    EDIT_CUSTOMER = false
                }
            }
            else{
                priceViewModel.statePrice = errorGetData
            }
            ScreenMenu(navController = navController, priceViewModel = priceViewModel)
        }

        composable(
            route = Screens.PaymentLoginSetting.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                Log.d("debug", "Payment Login Setting NavGraph")
            }
            ScreenPaymentLoginSetting(navController = navController, protoViewModel = protoViewModel)
        }

        composable(
            route = Screens.Customer.route,
        ){
            SEARCH_TEXT = ""
            LaunchedEffect(key1 = STORE_ID){
                customerViewModel.getCustomer()
                CUSTOMER_CITY = ""
                CUSTOMER_NAME = ""
                CUSTOMER_ID = ""
                CUSTOMER_PHONE = ""

                EDIT_CUSTOMER = false
            }
            ScreenCustomer(navController = navController, customerViewModel = customerViewModel)
        }

        composable(
            route = Screens.AddCustomer.route,
        ){
            SEARCH_TEXT = ""
            LaunchedEffect(key1 = STORE_ID){
//                customerViewModel.getCustomer()
            }
            ScreenAddCustomer(navController = navController, customerViewModel = customerViewModel)
        }
        
        composable(
            route = Screens.TransactionActive.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                Log.d("debug", "Date : ${DATE_PICK}")
                if (DATE_PICK != ""){
                    transactionViewModel.getTransaction(isFinish = false)
                }
            }
            ScreenTransactionActive(navController = navController, transactionViewModel = transactionViewModel)
        }

        composable(
            route = Screens.TransactionFinish.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                Log.d("debug", "Date : ${DATE_PICK}")
                if (DATE_PICK != ""){
                    transactionViewModel.getTransaction(isFinish = true)
                }
            }
            ScreenTransactionFinish(navController = navController, transactionViewModel = transactionViewModel)
        }

        composable(
            route = Screens.Machine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                machineViewModel.getMachine()
//                Log.d("debug", "Store ID : ${STORE_ID}")
            }
            ScreenMachine(
                navController = navController,
                machineViewModel = machineViewModel,
                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.Setting.route,
        ){
            ScreenSetting(
                protoViewModel = protoViewModel,
                navController = navController,
            )
        }

    }
}