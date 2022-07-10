package com.example.laundryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun transactionFinishContainer() {
    Surface(color = Color.White, shadowElevation = 16.dp, tonalElevation = 16.dp , modifier = Modifier
        .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        .fillMaxSize()
    ) {
        ConstraintLayout(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            val (TitleTodayTransaction, SeeAll, TransactionItem) = createRefs()
            val modifier = Modifier

            Text(
                text = "Transaction Finish Today",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                modifier = modifier.constrainAs(TitleTodayTransaction){
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(parent.top, 16.dp)
                }
            )

            Text(
                text = "See all",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                modifier = modifier.constrainAs(SeeAll){
                    top.linkTo(parent.top, 16.dp)
                    end.linkTo(parent.end, 8.dp)
                }
            )

            Surface(color = Color.White, modifier = modifier.constrainAs(TransactionItem){
                top.linkTo(TitleTodayTransaction.bottom, 4.dp)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }) {
                transactionFinishItemContainer()
            }

        }
    }
}