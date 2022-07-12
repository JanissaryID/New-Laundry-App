package com.example.laundryapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.CHECK_PAYMENT
import com.example.laundryapp.PAYMENT_METHOD
import com.example.laundryapp.STORE_CITY
import com.example.laundryapp.TYPE_SERVICE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentCheckBox() {
//    val isChecked = remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (CheckBox, label) = createRefs()

        Checkbox(
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.primary
            ),
            checked = CHECK_PAYMENT,
            onCheckedChange = { CHECK_PAYMENT = it },
            enabled = if (TYPE_SERVICE != "" && PAYMENT_METHOD != "") true else false,
            modifier = Modifier.constrainAs(CheckBox){
                start.linkTo(parent.start)
                top.linkTo(parent.top)
        })

        Text(
            text = "Customer already do payment",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(label) {
                    start.linkTo(CheckBox.end, 0.dp)
                    top.linkTo(CheckBox.top)
                    bottom.linkTo(CheckBox.bottom)
                }
        )
    }
}