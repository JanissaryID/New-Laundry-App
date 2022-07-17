package com.example.laundryapp.components.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.R
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewCustomerItem(
    phone: String,
    name: String,
    city: String,
    idCustomer: String,
    navController: NavController
) {
    Surface(modifier = Modifier
        .padding(0.dp)
        .fillMaxSize()
        .clickable {
            if (BACK_CUSTOMER){
                CUSTOMER_CITY = city
                CUSTOMER_NAME = name
                CUSTOMER_ID = idCustomer
                CUSTOMER_PHONE = phone
                navController.navigate(route = Screens.PaymentLoginSetting.route) {
                    popUpTo(Screens.PaymentLoginSetting.route) {
                        inclusive = true
                    }
                }
            }
            else{
                EDIT_CUSTOMER = true
                CUSTOMER_CITY = city
                CUSTOMER_NAME = name
                CUSTOMER_ID = idCustomer
                CUSTOMER_PHONE = phone
                navController.navigate(route = Screens.AddCustomer.route) {
                    popUpTo(Screens.PaymentLoginSetting.route) {
                        inclusive = true
                    }
                }
            }
        }) {
        ConstraintLayout(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)) {
            val (Avatar, Name, City) = createRefs()

            Icon(
                tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(
                id = R.drawable.ic_user),
                contentDescription = "Avatar user",
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(Avatar){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = "$name",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .constrainAs(Name) {
                        start.linkTo(Avatar.end, 16.dp)
                        top.linkTo(Avatar.top)
                    }
            )

            Text(
                text = city,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                modifier = Modifier.constrainAs(City) {
                    start.linkTo(Avatar.end, 16.dp)
                    bottom.linkTo(Avatar.bottom)
                }
            )
        }
    }
}