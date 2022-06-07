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
import com.example.laundryapp.KEY_URL
import com.example.laundryapp.STORE_ID
import com.example.laundryapp.STORE_NAME
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.menu.MenuViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.api.qris.QrisViewModel
import com.example.laundryapp.api.store.StoreViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.proto.ProtoViewModel
import com.example.laundryapp.screens.ScreenHome
import com.example.laundryapp.screens.ScreenMachine
import com.example.laundryapp.screens.ScreenSetting

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    storeViewModel: StoreViewModel,
    qrisViewModel: QrisViewModel,
    menuViewModel: MenuViewModel,
    priceViewModel: PriceViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel,
    paymentViewModel: PaymentViewModel,
    protoViewModel: ProtoViewModel,
//    excelViewModel: ExcelViewModel,
    componentActivity: ComponentActivity
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screens.Home.route){

        composable(
            route = Screens.Home.route,
        ){
            menuViewModel.getMenu()
            ScreenHome(
                storeViewModel = storeViewModel,
                navController = navController,
                menuViewModel = menuViewModel,
                priceViewModel = priceViewModel
//                priceViewModel = priceViewModel,
//                settingViewModel = settingViewModel,
//                transactionViewModel = transactionViewModel,
//                machineViewModel = machineViewModel
            )
        }
//
//        composable(
//            route = Screens.AddStore.route,
//        ){
//            ScreenAddStore(navController = navController, storeViewModel = storeViewModel)
//        }
//
//        composable(
//            route = Screens.Menu.route,
//        ){
//            ScreenMenu(
//                navController = navController,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
//        composable(
//            route = Screens.Qris.route,
//        ){
//            LaunchedEffect(key1 = STORE_ID){
//                qrisViewModel.getQris()
////                Log.d("debug", "Store ID : ${STORE_ID}")
//            }
//            ScreenQris(
//                navController = navController,
//                qrisViewModel = qrisViewModel,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
//        composable(
//            route = Screens.MenuPrice.route,
//        ){
//            LaunchedEffect(key1 = STORE_ID){
//                menuViewModel.getMenu()
////                Log.d("debug", "Store ID : ${STORE_ID}")
//            }
//            ScreenMenuPrice(
//                navController = navController,
//                menuViewModel = menuViewModel
////                qrisViewModel = qrisViewModel,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
//        composable(
//            route = Screens.AddEditMenuPrice.route,
//        ){
//            ScreenMenuPriceAddEdit(
//                navController = navController,
//                menuViewModel = menuViewModel
////                qrisViewModel = qrisViewModel,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
//        composable(
//            route = Screens.Price.route,
//        ){
//            LaunchedEffect(key1 = STORE_ID){
//                priceViewModel.getPrice()
////                Log.d("debug", "Store ID : ${STORE_ID}")
//            }
//            ScreenPrice(
//                navController = navController,
//                priceViewModel = priceViewModel
////                qrisViewModel = qrisViewModel,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
//        composable(
//            route = Screens.AddEditPrice.route,
//        ){
//            menuViewModel.getMenu()
//            ScreenPriceAddEdit(
//                navController = navController,
//                menuViewModel = menuViewModel,
//                priceViewModel = priceViewModel
////                qrisViewModel = qrisViewModel,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
//        composable(
//            route = Screens.ListTransactions.route,
//        ){
//            if (DATE_PICK != ""){
//                transactionViewModel.getTransaction()
//            }
//            transactionViewModel.getTransaction()
//            ScreenTransaction(
//                navController = navController,
//                transactionViewModel = transactionViewModel,
//                excelViewModel = excelViewModel
////                priceViewModel = priceViewModel
//            )
//        }
//
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
//
//        composable(
//            route = Screens.AddEditMachine.route,
//        ){
////            LaunchedEffect(key1 = STORE_ID){
////                machineViewModel.getMachine()
//////                Log.d("debug", "Store ID : ${STORE_ID}")
////            }
//            ScreenMachineAddEdit(
//                navController = navController,
//                machineViewModel = machineViewModel
////                qrisViewModel = qrisViewModel,
////                machineViewModel = machineViewModel,
////                transactionViewModel = transactionViewModel
//            )
//        }
//
        composable(
            route = Screens.Setting.route,
        ){
            ScreenSetting(
                protoViewModel = protoViewModel,
                navController = navController,
//                storeViewModel = storeViewModel
            )
        }

    }
}