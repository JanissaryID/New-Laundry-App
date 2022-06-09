package com.example.laundryapp.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.PaymentLoadData
import com.example.laundryapp.components.machine.MachineLoadData
import com.example.laundryapp.components.views.ViewQris
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenQris(
    navController: NavController,
    paymentViewModel: PaymentViewModel,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[0],
            screenBack = Screens.Machine.route
        ) }
    ){
        WallQris(
            navController = navController,
            paymentViewModel = paymentViewModel,
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WallQris(
    paymentViewModel: PaymentViewModel,
    navController: NavController,
    machineViewModel: MachineViewModel = MachineViewModel(),
    transactionViewModel: TransactionViewModel = TransactionViewModel()
) {
    ViewQris(
        paymentViewModel = paymentViewModel,
        navController = navController,
        machineViewModel = machineViewModel,
        transactionViewModel = transactionViewModel
    )
}