package com.example.laundryapp.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.data.Transaction

@Composable
fun transactionFinishItem(transaction: Transaction) {
    ConstraintLayout {
        val (TransactionNameMenu, TransactionMenu, TransactionPaymentType, TransactionDate, TransactionPrice, TransactionAdmin, Divider) = createRefs()
        val space = 1
        val alfaText = 0.7f

        Text(
            text = transaction.transactionMenuName,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            modifier = Modifier.constrainAs(TransactionNameMenu){
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )

        Text(
            text = transaction.transactionMenu,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alfaText),
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.constrainAs(TransactionMenu){
                start.linkTo(parent.start)
                top.linkTo(TransactionNameMenu.bottom, space.dp)
            }
        )

        Text(
            text = transaction.transactionPaymentType,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alfaText),
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.constrainAs(TransactionPaymentType){
                start.linkTo(parent.start)
                top.linkTo(TransactionMenu.bottom, space.dp)
            }
        )

        Text(
            text = transaction.transactionDate,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alfaText),
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.constrainAs(TransactionDate){
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        Text(
            text = transaction.transactionPrice,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alfaText),
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.constrainAs(TransactionPrice){
                end.linkTo(parent.end)
                top.linkTo(TransactionDate.bottom, space.dp)
            }
        )

        Text(
            text = transaction.transactionAdmin,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alfaText),
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.constrainAs(TransactionAdmin){
                end.linkTo(parent.end)
                top.linkTo(TransactionPrice.bottom, space.dp)
            }
        )

        Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 1f), modifier = Modifier.constrainAs(Divider){
            end.linkTo(parent.end)
            top.linkTo(TransactionPaymentType.bottom, 2.dp)
        })


    }
}