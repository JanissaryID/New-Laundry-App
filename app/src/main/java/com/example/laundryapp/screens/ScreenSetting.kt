package com.example.laundryapp.screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.navigation.Screens
import com.example.laundryapp.proto.ProtoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenSetting(
    protoViewModel: ProtoViewModel,
    navController: NavController,
//    storeViewModel: StoreViewModel
//    componentActivity: ComponentActivity
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[10],
            screenBack = Screens.Home.route
        ) },
    ){
        WallSetting(
            protoViewModel = protoViewModel,
            navController = navController,
//            storeViewModel = storeViewModel
//            componentActivity = componentActivity
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallSetting(
    protoViewModel: ProtoViewModel,
    navController: NavController,
//    storeViewModel: StoreViewModel
//    componentActivity: ComponentActivity
) {
    val context = LocalContext.current

    var text_key_setting by remember { mutableStateOf(TextFieldValue(KEY_URL)) }
    var text_city_setting by remember { mutableStateOf(TextFieldValue(STORE_CITY)) }
    var text_password_setting by remember { mutableStateOf(TextFieldValue(STORE_PASSWORD)) }

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Card(shape = RoundedCornerShape(20.dp)) {
            ConstraintLayout(modifier = Modifier
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {

                val (inputFieldKey, inputFieldCity, inputFieldPassword, button) = createRefs()

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(inputFieldKey) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.onSurface
                    ),
                    value = text_key_setting,
                    label = { Text(text = "Key") },
                    onValueChange = {
                        text_key_setting = it
                    }
                )

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(inputFieldCity) {
                            top.linkTo(inputFieldKey.bottom, 10.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.onSurface
                    ),
                    value = text_city_setting,
                    label = { Text(text = "City") },
                    onValueChange = {
                        text_city_setting = it
                    }
                )

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(inputFieldPassword) {
                            top.linkTo(inputFieldCity.bottom, 10.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.onSurface
                    ),
                    value = text_password_setting,
                    label = { Text(text = "Password") },
                    onValueChange = {
                        text_password_setting = it
                    }
                )

                ButtonView(
                    title = "Save",
                    enable = true,
                    modifier = Modifier.constrainAs(button){
                        top.linkTo(inputFieldPassword.bottom, 10.dp)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                ) {
                    protoViewModel.updateData(keyUrl = text_key_setting.text)
                    protoViewModel.updateCityStore(cityStore = text_city_setting.text)
                    protoViewModel.updatePasswordStore(passwordStore = text_password_setting.text)
//                    Toast.makeText(context, "Save Key ${KEY_URL} ${STORE_CITY} ${STORE_PASSWORD}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}