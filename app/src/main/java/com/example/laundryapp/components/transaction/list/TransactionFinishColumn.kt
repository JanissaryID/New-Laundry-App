package com.example.laundryapp.components.transaction.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laundryapp.api.transaction.TransactionModel
import com.example.laundryapp.components.views.ViewTransactionActiveItem
import com.example.laundryapp.components.views.ViewTransactionFinishItem

@Composable
fun TransactionFinishColumn(
    navController: NavController,
    is_list: Boolean,
    transactionModel: List<TransactionModel>
){
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = transactionModel) { index, transaction ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewTransactionFinishItem(
                    title_Menu = transaction.transactionTypeMenu.toString(),
                    menu_machine = transaction.transactionMenuMachine.toString(),
                    class_machine = transaction.transactionClassMachine.toString(),
                    date = transaction.transactionDate.toString(),
                    price = transaction.transactionPrice.toString(),
                    index = transaction.id!!.toString(),
                    payment = transaction.transactionTypePayment.toString(),
                    is_packet = transaction.isPacket!!,
                    is_list_transaction = is_list,
                    step_one = transaction.stepOne!!,
                    navController = navController,
                    number_machine = transaction.transactionNumberMachine!!,
                ){}
            }
        }
    }
}