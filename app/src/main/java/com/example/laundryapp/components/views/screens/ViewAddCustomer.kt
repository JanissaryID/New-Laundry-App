package com.example.laundryapp.components.views.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.ViewTopComponent
import com.example.laundryapp.components.views.ViewDialogLoading
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewAddCustomer(
    navController: NavController,
    customerViewModel: CustomerViewModel
) {
    var text_name by remember { mutableStateOf(TextFieldValue(CUSTOMER_NAME)) }
    var text_city by remember { mutableStateOf(TextFieldValue(CUSTOMER_CITY)) }
    var text_phone by remember { mutableStateOf(TextFieldValue(CUSTOMER_PHONE)) }

    var enable_button by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize()
    ) {
        val (TopBar, Name, City, Phone, Button) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent, modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
            .height(64.dp)
            .constrainAs(TopBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {
            ViewTopComponent(
                title = if (EDIT_CUSTOMER) "Edit Customer" else "Add Customer",
                navController = navController,
                screenBack = Screens.Customer.route
            )
        }

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(Name) {
                    top.linkTo(TopBar.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
            ),
            value = text_name,
            label = { Text(text = "Name") },
            onValueChange = {
                text_name = it
            }
        )

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(City) {
                    top.linkTo(Name.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
            ),
            value = text_city,
            label = { Text(text = "City") },
            onValueChange = {
                text_city = it
            }
        )

        OutlinedTextField(
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(Phone) {
                    top.linkTo(City.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
            ),
            value = text_phone,
            label = { Text(text = "Phone") },
            onValueChange = {
                text_phone = it
            }
        )

        if (!text_name.text.isNullOrEmpty() && !text_city.text.isNullOrEmpty() && !text_phone.text.isNullOrEmpty()){
            enable_button = true
        }
        else{
            enable_button = false
        }

        ButtonView(
            title = if (EDIT_CUSTOMER) "Save Edit Customer" else "Save New Customer",
            modifier = Modifier.constrainAs(Button) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = enable_button
        ){
            if (EDIT_CUSTOMER){
                IS_DIALOG_OPEN.value = true
                customerViewModel.updateCustomer(
                    name = text_name.text,
                    city = text_city.text,
                    phone = text_phone.text,
                    navController = navController
                )
            }
            else{
                IS_DIALOG_OPEN.value = true
                customerViewModel.insertCustomer(
                    name = text_name.text,
                    city = text_city.text,
                    phone = text_phone.text,
                    navController = navController
                )
            }

        }

        if (IS_DIALOG_OPEN.value){
            ViewDialogLoading()
        }
    }
}