package com.example.laundryapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.machine.MachineLoadData
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.components.views.ViewTopBarHome
import com.example.laundryapp.components.views.screens.ViewMachine
import com.example.laundryapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMachine(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[2],
            screenBack = Screens.Home.route
        ) }
    ){
        WallMachine(
            navController = navController,
            machineViewModel = machineViewModel,
            transactionViewModel = transactionViewModel,
        )
    }

}

@Composable
fun WallMachine(
    transactionViewModel: TransactionViewModel,
    navController: NavController,
    machineViewModel: MachineViewModel,
    paymentViewModel: PaymentViewModel = PaymentViewModel()
) {
    ViewMachine(
        machineViewModel = machineViewModel,
        paymentViewModel = paymentViewModel,
        navController = navController,
        transactionViewModel = transactionViewModel
    )
}