package com.example.laundryapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.machine.MachineLoadData
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.components.views.ViewTopBarHome
import com.example.laundryapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMachine(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[8],
            screenBack = Screens.Home.route
        ) }
    ){
        WallMachine(
            navController = navController,
            machineViewModel = machineViewModel,
            transactionViewModel = transactionViewModel
        )
    }

}

@Composable
fun WallMachine(
    transactionViewModel: TransactionViewModel,
    navController: NavController,
    machineViewModel: MachineViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMachine = machineViewModel.stateMachine
    val machine = machineViewModel.machineListResponse

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize()
    ) {
        val (Content, ButtonPick) = createRefs()
        val modifier = Modifier

        Box(modifier = modifier.constrainAs(Content) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            MachineLoadData(
                machineState = stateMachine,
                selectedIndex = selectedIndex,
                machine = machine,
                navController = navController,
                onItemClick = onItemClick)
        }

        ButtonView(title = "Pick Machine", modifier.constrainAs(ButtonPick) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable =
        if (MACHINE_SELECTED){
            true
        }
        else{
            false
        }
        ){
            machineViewModel.updateMachine(
                navController = navController,
                idMachine = MACHINE_ID,
                timeMachine = MACHINE_TIME,
                isPacket = MACHINE_PACKET,
                transactionViewModel = transactionViewModel
            )
            Log.d("debug", "Number ${MACHINE_NUMBER} ${MACHINE_ID} ${MACHINE_TIME}")
//            Log.d("debug", "value ${MENU_PACKET}")
//            Toast.makeText(context, "Value Menu $selectedPrice", Toast.LENGTH_SHORT).show()
        }
    }
}