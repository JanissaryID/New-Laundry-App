package com.example.laundryapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.components.views.screens.ViewMenuNew
import com.example.laundryapp.navigation.Screens

@Composable
fun ScreenMenu(
    navController: NavController,
    priceViewModel: PriceViewModel
) {
    WallMenu(priceViewModel = priceViewModel, navController = navController)
}

@Composable
fun WallMenu(
    priceViewModel: PriceViewModel,
    navController: NavController
) {
    Box {
        ViewMenuNew(priceViewModel = priceViewModel, navController = navController)
    }
}