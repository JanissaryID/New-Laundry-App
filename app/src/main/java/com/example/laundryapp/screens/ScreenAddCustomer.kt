package com.example.laundryapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.components.views.screens.ViewAddCustomer
import com.example.laundryapp.components.views.screens.ViewCustomer

@Composable
fun ScreenAddCustomer(
    navController: NavController,
    customerViewModel: CustomerViewModel
) {
    WallAddCustomer(customerViewModel = customerViewModel, navController = navController)
}

@Composable
fun WallAddCustomer(
    customerViewModel: CustomerViewModel,
    navController: NavController
) {
    Box {
        ViewAddCustomer(navController = navController, customerViewModel = customerViewModel)
    }
}