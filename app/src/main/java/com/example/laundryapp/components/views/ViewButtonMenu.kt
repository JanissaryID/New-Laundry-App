package com.example.laundryapp.components.views

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewButtonMenu(
    title: String,
    color: Color,
    index: Int,
    selected: Boolean,
//    priceViewModel: PriceViewModel,
    onClick: (Int) -> Unit
) {
//    TEMP_SELECTED_INDEX = index
//    val context = LocalContext.current
//    TEMP_SELECTED_INDEX = INDEX_CLASS_MACHINE
    Card(
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
        shape = RoundedCornerShape(40),
        containerColor = Color.Transparent
    ) {
        Surface(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .clickable {
                    onClick.invoke(index)
                },
            color = if (!selected) MaterialTheme.colorScheme.primary else color,
            shape = RoundedCornerShape(40),
            contentColor = if (!selected) color else MaterialTheme.colorScheme.primary,
        ){
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight().padding(
                    start = 24.dp,
                    end = 24.dp
                )
            )
        }
    }
}