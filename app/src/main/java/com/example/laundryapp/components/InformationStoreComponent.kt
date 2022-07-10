package com.example.laundryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.R

@Composable
fun informationStore() {
    ConstraintLayout() {
        val (StoreName, StoreCity, StoreAddress) = createRefs()
        val modifier = Modifier

        Text(
            text = "Alfa Omega Laundry",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = modifier.wrapContentHeight().constrainAs(StoreName){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        Text(
            text = "Salatiga",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = modifier.wrapContentHeight().constrainAs(StoreCity){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(StoreName.top, 32.dp)
            }
        )

        Text(
            text = "Jl. Pemuda Kemerdekaan",
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = modifier.wrapContentHeight().constrainAs(StoreAddress){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(StoreCity.top, 32.dp)
            }
        )
    }
}