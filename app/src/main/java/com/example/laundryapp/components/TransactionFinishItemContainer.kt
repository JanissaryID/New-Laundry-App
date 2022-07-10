package com.example.laundryapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.laundryapp.components.views.ViewTransactionFinishItem
import com.example.laundryapp.data.DataTransaction

@Composable
fun transactionFinishItemContainer() {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = DataTransaction.listTransaction) { index, transaction ->
            Surface(color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                transactionFinishItem(transaction = transaction)
            }
        }
    }
}