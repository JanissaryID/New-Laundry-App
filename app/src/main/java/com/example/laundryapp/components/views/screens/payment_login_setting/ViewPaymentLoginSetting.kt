package com.example.laundryapp.components.views.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.CHECK_PAYMENT
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.ComponentCheckBox
import com.example.laundryapp.components.ViewTopComponent
import com.example.laundryapp.components.views.ViewSetting
import com.example.laundryapp.components.views.screens.payment_login_setting.ViewLogin
import com.example.laundryapp.components.views.screens.payment_login_setting.ViewPayment
import com.example.laundryapp.navigation.Screens
import com.example.laundryapp.proto.ProtoViewModel

@Composable
fun ViewPaymentLoginSetting(screenType: Int, protoViewModel: ProtoViewModel, navController: NavController) {
    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize()
    ) {
        val (TopBar, ContentLayout, Button, CheckBox) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent, modifier = modifier
            .wrapContentSize()
            .constrainAs(TopBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {
            if (screenType == 1){
//                ViewTopComponent(
//                    title = if (screenType == 0) "Payment" else if (screenType == 1 ) "Information" else "Setting",
//                    navController = navController,
//                    screenBack = Screens.Home.route
//                )
            }
            else{
                ViewTopComponent(
                    title = if (screenType == 0) "Payment" else if (screenType == 1 ) "Login" else if (screenType == 3 ) "Information" else "Setting",
                    navController = navController,
                    screenBack = Screens.Home.route
                )
            }
        }

        Surface(color = Color.Transparent, modifier = modifier.constrainAs(ContentLayout) {
            top.linkTo(TopBar.bottom, 42.dp)
            start.linkTo(parent.start)
        }) {
            when (screenType) {
                0 -> {
                    ViewPayment()
                }
                1 -> {
                    ViewLogin()
                }
                2 -> {
                    ViewSetting(protoViewModel = protoViewModel)
                }
                3 -> {

                }
            }
        }

        if (screenType == 0){
            Surface(color = Color.Transparent,modifier = Modifier.constrainAs(CheckBox){
                bottom.linkTo(Button.top, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                ComponentCheckBox()
            }
            ButtonView(title = "Print bill", modifier = Modifier.constrainAs(Button) {
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, enable = CHECK_PAYMENT
            ){
                Log.d("debug", "Print Bill")
            }
        }

        if (screenType == 1){
            ButtonView(title = "Login", modifier = Modifier.constrainAs(Button) {
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, enable = true
            ){
                Log.d("debug", "Login")
            }
        }
    }
}