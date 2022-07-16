package com.example.laundryapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.components.views.screens.ViewCustomer

@Composable
fun ScreenCustomer(
    navController: NavController,
    customerViewModel: CustomerViewModel
) {
    WallCustomer(customerViewModel = customerViewModel, navController = navController)
}

@Composable
fun WallCustomer(
    customerViewModel: CustomerViewModel,
    navController: NavController
) {
    Box {
        ViewCustomer(navController = navController, customerViewModel = customerViewModel)
    }
}