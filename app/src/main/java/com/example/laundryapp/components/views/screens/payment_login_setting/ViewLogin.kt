package com.example.laundryapp.components.views.screens.payment_login_setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.R

@Composable
fun ViewLogin() {

    var text_user_name by remember { mutableStateOf(TextFieldValue("")) }
    var text_password by remember { mutableStateOf(TextFieldValue("")) }

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp)
        .fillMaxWidth()) {

        val (Content) = createRefs()

        ConstraintLayout(modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .constrainAs(Content) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }) {

            val (ImageAvatar, Information) = createRefs()

            Surface(color = Color.White,
                shadowElevation = 16.dp,
                tonalElevation = 16.dp,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(132.dp)
                    .constrainAs(ImageAvatar) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_user
                        ),
                        contentDescription = "Store Icon",
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(104.dp)
                    )
                }
            }

            Surface(color = Color.White ,shape = RoundedCornerShape(8), modifier = Modifier.constrainAs(Information) {
                end.linkTo(parent.end)
                top.linkTo(ImageAvatar.bottom, 32.dp)
                start.linkTo(parent.start)
            }) {
                ConstraintLayout(modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 52.dp)
                ) {

                    val (TitleLogin,inputFieldUsername, inputFieldPassword) = createRefs()

                    Text(
                        text = "Login",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        modifier = Modifier.wrapContentHeight().constrainAs(TitleLogin){
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top, 16.dp)
                        }
                    )

                    OutlinedTextField(
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(inputFieldUsername) {
                                top.linkTo(TitleLogin.bottom, 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            textColor = MaterialTheme.colorScheme.onSurface,
                            cursorColor = MaterialTheme.colorScheme.onSurface
                        ),
                        value = text_user_name,
                        label = { Text(text = "Username") },
                        onValueChange = {
                            text_user_name = it
                        }
                    )

                    OutlinedTextField(
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(inputFieldPassword) {
                                top.linkTo(inputFieldUsername.bottom, 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            textColor = MaterialTheme.colorScheme.onSurface,
                            cursorColor = MaterialTheme.colorScheme.onSurface
                        ),
                        value = text_password,
                        label = { Text(text = "Password") },
                        onValueChange = {
                            text_password = it
                        }
                    )
                }
            }
        }
    }
}