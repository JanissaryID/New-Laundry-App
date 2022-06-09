package com.example.laundryapp.components.views.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.transaction.active.TransactionActiveLoadData
import com.example.laundryapp.components.transaction.list.TransactionFinishLoadData

@Composable
fun ViewTransactionFinish(
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    val stateTransaction = transactionViewModel.stateTransactionFinish
    val transaction = transactionViewModel.transactionListResponseFinish

    Box{
        TransactionFinishLoadData(
            transactionState = stateTransaction,
            is_list = false,
            transaction = transaction,
            navController = navController
        )
    }
}