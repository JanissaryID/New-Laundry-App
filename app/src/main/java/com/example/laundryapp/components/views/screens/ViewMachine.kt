package com.example.laundryapp.components.views.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.machine.MachineLoadData
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewMachine(
    machineViewModel: MachineViewModel,
    paymentViewModel: PaymentViewModel,
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMachine = machineViewModel.stateMachine
    val machine = machineViewModel.machineListResponse

    paymentViewModel.reffID = 0L
    PAYMENT_SUCCESS = true

    val stateCash = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = BUTTON_VISIBLE
        }
    }

    val stateQris = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize()
    ) {
        val (Content, ButtonCash, ButtonQris) = createRefs()
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

        AnimatedVisibility(visibleState = stateCash, modifier = modifier.constrainAs(ButtonCash) {
            bottom.linkTo(ButtonQris.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            ButtonView(title = "Pay With Cash", enable =
            if (MACHINE_SELECTED){
                true
            }
            else{
                false
            }
            ){
                MACHINE_SELECTED = false
                machineViewModel.updateMachine(
                    navController = navController,
                    idMachine = MACHINE_ID,
                    timeMachine = MACHINE_TIME,
                    isPacket = PRICE_PACKET,
                    typePayment = false,
                    transactionViewModel = transactionViewModel
                )
                Log.d("debug", "Number $MACHINE_NUMBER $MACHINE_ID $MACHINE_TIME")
//            Log.d("debug", "value ${MENU_PACKET}")
//            Toast.makeText(context, "Value Menu $selectedPrice", Toast.LENGTH_SHORT).show()
            }
        }

        AnimatedVisibility(visibleState = stateQris, modifier = modifier.constrainAs(ButtonQris) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            ButtonView(title = if (!BUTTON_VISIBLE) "Turn On Machine" else "Pay With Qris", enable =
            if (MACHINE_SELECTED){
                true
            }
            else{
                false
            }
            ){
                if (!BUTTON_VISIBLE){
                    MACHINE_SELECTED = false
                    machineViewModel.updateMachine(
                        navController = navController,
                        idMachine = MACHINE_ID,
                        timeMachine = MACHINE_TIME,
                        isPacket = PRICE_PACKET,
                        typePayment = false,
                        transactionViewModel = transactionViewModel
                    )
                    Log.d("debug", "Number $MACHINE_NUMBER $MACHINE_ID $MACHINE_TIME")
                }
                else{
                    PAYMENT_SUCCESS = false
                    navController.navigate(route = Screens.Qris.route)
                    Log.d("debug", "Number $MACHINE_NUMBER $MACHINE_ID $MACHINE_TIME")
//            Log.d("debug", "value ${MENU_PACKET}")
//            Toast.makeText(context, "Value Menu $selectedPrice", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}