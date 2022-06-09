package com.example.laundryapp.components.views.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.machine.MachineLoadData
import com.example.laundryapp.components.transaction.active.TransactionActiveLoadData
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewTransactionActive(
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    val stateTransaction = transactionViewModel.stateTransactionActive
    val transaction = transactionViewModel.transactionListResponseActive

    Box{
        TransactionActiveLoadData(
            transactionState = stateTransaction,
            is_list = false,
            transaction = transaction,
            navController = navController
        )
    }
}