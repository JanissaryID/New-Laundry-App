package com.example.laundryapp.components.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laundryapp.CLASS_MACHINE
import com.example.laundryapp.api.price.PriceViewModel

@Composable
fun ViewButtonMenu(
    title: String,
    color: Color,
    index: Int,
    selected: Boolean,
    priceViewModel: PriceViewModel,
    onClick: (Int) -> Unit
) {
    Surface(modifier = Modifier
        .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        color = Color.Transparent,
        shape = RoundedCornerShape(50),
    ) {
        Surface(
            modifier = Modifier
                .height(34.dp)
                .fillMaxWidth()
                .clickable {
                    onClick.invoke(index)
                    CLASS_MACHINE = index
//                    priceViewModel.priceListResponse.clear()
//                    Log.d("debug", "Selected Class : ${index}")
                    priceViewModel.getPrice(classPrice = if(CLASS_MACHINE == 0) false else true)
                },
            color = if (!selected) MaterialTheme.colorScheme.primary else color,
            shape = RoundedCornerShape(50),
            contentColor = if (!selected) color else MaterialTheme.colorScheme.primary,
        ){
            Text(
                color = if (selected) MaterialTheme.colorScheme.primary else Color.White,
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight().padding(
                    start = 24.dp,
                    end = 24.dp
                )
            )
        }
    }
}