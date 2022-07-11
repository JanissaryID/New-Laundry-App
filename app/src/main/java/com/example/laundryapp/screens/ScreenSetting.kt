package com.example.laundryapp.screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.views.ViewHome
import com.example.laundryapp.components.views.ViewSetting
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.navigation.Screens
import com.example.laundryapp.proto.ProtoViewModel

//@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
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
            title = TITLE_SCREEN[3],
            screenBack = Screens.Home.route
        ) },
    ){
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(it)
                .fillMaxSize()
        ) {
            WallSetting(
                protoViewModel = protoViewModel,
                navController = navController,
//            storeViewModel = storeViewModel
//            componentActivity = componentActivity
            )
        }
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

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        ViewSetting(protoViewModel = protoViewModel)
    }


}