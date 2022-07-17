package com.example.laundryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewAdminComponent(navController: NavController) {
    ConstraintLayout() {
        val (Avatar, Name, Logout, SettingIcon) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent,modifier = Modifier.size(48.dp).clip(CircleShape).constrainAs(Avatar){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }) {
            Icon(tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(
                id = R.drawable.ic_user),
                contentDescription = "Avatar user",
                modifier = modifier
                    .wrapContentHeight()
                    .size(48.dp)
            )
        }

        Text(
            text = "Putri Sabila",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = modifier
                .wrapContentHeight()
                .constrainAs(Name) {
                    start.linkTo(Avatar.end, 8.dp)
                    top.linkTo(Avatar.top)
                }
        )

        Surface(color = Color.Transparent, modifier = modifier
            .constrainAs(Logout) {
                start.linkTo(Avatar.end, 1.dp)
                bottom.linkTo(Avatar.bottom)
            }
            .clip(CircleShape)
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .clickable {
                        SCREEN_TYPE = 1
                        navController.navigate(route = Screens.PaymentLoginSetting.route)
                    }
            ) {
                Text(
                    text = "Logout",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    modifier = modifier
                        .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
                        .wrapContentHeight()
                )
            }
        }

        Surface(color = Color.Transparent, modifier = modifier
            .constrainAs(SettingIcon) {
                end.linkTo(parent.end)
                top.linkTo(Avatar.top)
                bottom.linkTo(Avatar.bottom)
            }
            .clip(CircleShape)
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.size(40.dp)
                    .clickable {
                        SCREEN_TYPE = 2
                        navController.navigate(route = Screens.PaymentLoginSetting.route)
                    }
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(
                        id = R.drawable.ic_gear),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(32.dp)
                )
            }
        }
    }
}

@Composable
fun ViewTopComponent(
    title: String,
    screenBack: String,
    customerViewModel: CustomerViewModel = CustomerViewModel(),
    navController: NavController
) {
    ConstraintLayout() {
        val (Avatar, Name, DeleteUser) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent, modifier = modifier
            .constrainAs(Avatar) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
            .clip(CircleShape)
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.size(40.dp)
                    .clickable {
                        navController.navigate(route = screenBack) {
                            popUpTo(screenBack) {
                                inclusive = true
                            }
                        }
                    }
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(
                        id = R.drawable.ic_arrow_back),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(24.dp)
                )
            }
        }

        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = modifier
                .wrapContentHeight()
                .constrainAs(Name) {
                    start.linkTo(Avatar.end, 8.dp)
                    top.linkTo(Avatar.top)
                    bottom.linkTo(Avatar.bottom)
                }
        )

        if (EDIT_CUSTOMER){
            Surface(color = Color.Transparent, modifier = modifier
                .constrainAs(DeleteUser) {
                    end.linkTo(parent.end)
                    top.linkTo(Avatar.top)
                    bottom.linkTo(Avatar.bottom)
                }
                .clip(CircleShape)
            ) {
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier.size(40.dp)
                        .clickable {
                            IS_DIALOG_OPEN.value = true
                            customerViewModel.deleteCustomer(CUSTOMER_ID, navController = navController)
                        }
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        painter = painterResource(
                            id = R.drawable.ic_trash),
                        contentDescription = "Add Icon",
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(32.dp)
                    )
                }
            }
        }
    }
}