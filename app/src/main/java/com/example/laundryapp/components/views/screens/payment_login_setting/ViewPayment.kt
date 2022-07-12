package com.example.laundryapp.components.views.screens.payment_login_setting

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.*
import com.example.laundryapp.R
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.MultipleRadioButtons
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewPayment() {
    Surface(
        color = Color.White ,
        shape = RoundedCornerShape(8)) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {

            val (TitleCustomer, Customer, TitlePaymentMethod, PaymentMethod, TitleService, Service) = createRefs()

            Text(
                text = "Customer",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(TitleCustomer) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            Surface(
                color = MaterialTheme.colorScheme.primary ,shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(Customer) {
                        start.linkTo(parent.start)
                        top.linkTo(TitleCustomer.bottom, 4.dp)
                    })
            {
                ConstraintLayout(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { Log.d("debug", "Clicked") }
                    .padding(start = 8.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
                ) {

                    val (NameCustomer, ArrowRight) = createRefs()

                    Text(
                        text = "Chose Customer",
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier.constrainAs(NameCustomer){
                            start.linkTo(parent.start, 2.dp)
                            top.linkTo(parent.top)
                        }
                    )

                    Image(painter = painterResource(
                        id = R.drawable.ic_arrow_right),
                        contentDescription = "Back Arrow",
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(20.dp)
                            .constrainAs(ArrowRight) {
                                end.linkTo(parent.end, 4.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
            }

            Text(
                text = "Payment Method",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(TitlePaymentMethod) {
                        start.linkTo(parent.start)
                        top.linkTo(Customer.bottom, 16.dp)
                    }
            )

            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(PaymentMethod) {
                        start.linkTo(parent.start)
                        top.linkTo(TitlePaymentMethod.bottom, 0.dp)
                    }
            ) {
                val items = listOf("Cash", "Qris")
                PAYMENT_METHOD = MultipleRadioButtons(items = items)
            }

            Text(
                text = "Service Type",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(TitleService) {
                        start.linkTo(parent.start)
                        top.linkTo(PaymentMethod.bottom, 16.dp)
                    }
            )

            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(Service) {
                        start.linkTo(parent.start)
                        top.linkTo(TitleService.bottom, 0.dp)
                    }
            ) {
                val items = listOf("Be Awaited", "Be Left")
                TYPE_SERVICE = MultipleRadioButtons(items = items)
            }
        }
    }
}