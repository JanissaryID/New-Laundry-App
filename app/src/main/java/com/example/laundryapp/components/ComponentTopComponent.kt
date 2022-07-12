package com.example.laundryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.laundryapp.R
import com.example.laundryapp.SCREEN_TYPE
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewAdminComponent(navController: NavController) {
    ConstraintLayout() {
        val (Avatar, Name) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent,modifier = Modifier.size(48.dp).clip(CircleShape).constrainAs(Avatar){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }) {
            Image(painter = painterResource(
                id = R.drawable.ic_user),
                contentDescription = "Avatar user",
                modifier = modifier
                    .wrapContentHeight()
                    .size(48.dp)
                    .clickable {
                        SCREEN_TYPE = 1
                        navController.navigate(route = Screens.PaymentLoginSetting.route)
                    }
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
                    bottom.linkTo(Avatar.bottom)
                }
        )
    }
}

@Composable
fun ViewTopComponent(
    title: String,
    screenBack: String,
    navController: NavController
) {
    ConstraintLayout() {
        val (Avatar, Name) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent, modifier = modifier
            .clip(CircleShape)
            .size(24.dp)
            .constrainAs(Avatar) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(
                    id = R.drawable.ic_arrow_back),
                    contentDescription = "Back Arrow",
                    modifier = modifier
                        .wrapContentHeight()
                        .size(24.dp)
                        .clickable {
                            navController.navigate(route = screenBack) {
                                popUpTo(screenBack) {
                                    inclusive = true
                                }
                            }
                        }

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
    }
}