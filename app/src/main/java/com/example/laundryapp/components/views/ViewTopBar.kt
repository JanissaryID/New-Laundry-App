package com.example.laundryapp.components.views

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laundryapp.PAYMENT_SUCCESS
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewTopBarHome(
    navController: NavController,
    title: String,
) {
    val context = LocalContext.current

    TopAppBarDefaults

    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = colorScheme.primary
            )
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.Setting.route)
                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = colorScheme.primary
                )
            }
        }
    )
}

@Composable
fun ViewTopBar(
    navController: NavController,
    title: String,
    screenBack: String
) {
    val context = LocalContext.current
    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = colorScheme.primary,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}