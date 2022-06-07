package com.example.laundryapp.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineViewModel
import com.example.laundryapp.api.payment.PaymentViewModel
import com.example.laundryapp.api.transaction.TransactionViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.components.PaymentLoadData
import com.example.laundryapp.components.machine.MachineLoadData
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenQris(
    navController: NavController,
    paymentViewModel: PaymentViewModel,
//    stateQR: Int,
//    payment: String,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[2],
            screenBack = Screens.Home.route
        ) }
    ){
        WallQris(
            navController = navController,
            paymentViewModel = paymentViewModel,
//            stateQR = stateQR,
//            payment = payment
//            machineViewModel = machineViewModel,
//            transactionViewModel = transactionViewModel
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WallQris(
    paymentViewModel: PaymentViewModel,
//    stateQR: Int,
//    payment: String,
//    transactionViewModel: TransactionViewModel,
    navController: NavController,
//    machineViewModel: MachineViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val statePayment = paymentViewModel.stateQR
    val qris = paymentViewModel.rawString

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)) {
        val (title,number, content, buttonOK, buttonCheck) = createRefs()
        val modifier: Modifier = Modifier

//        Log.d("debug", "Price ${PRICE_VALUE[0].price}")

        Text(
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            text = "Payment Qris",
            modifier = modifier.constrainAs(title){
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            text = "Machine Number $MACHINE_NUMBER",
            modifier = modifier.constrainAs(number){
                top.linkTo(title.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Box(modifier = modifier.constrainAs(content) {
            bottom.linkTo(buttonCheck.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }){
            PaymentLoadData(paymentState = statePayment, rawQR = qris)
            if (paymentViewModel.reffID != 0L){
                paymentViewModel.getResponsePayment()
            }
        }

        ButtonView(title = "Check", modifier.constrainAs(buttonCheck) {
            bottom.linkTo(buttonOK.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = if(statePayment == 1) true else false){
//            CHECK_PAYMENT_OK = true
            PAYMENT_SUCCESS = true
//            STOP_GET_PAYMENT_STATUS = true
        }

        ButtonView(title = "Turn On Machine", modifier.constrainAs(buttonOK) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = if(PAYMENT_SUCCESS) true else false){

//            Toast.makeText(context, "MENU $MENU_VALUE", Toast.LENGTH_SHORT).show()
//            machineViewModel.updateMachine(idMachine = MACHINE_ID, isPacket = PRICE_VALUE[0].isPacket!!)
//            transactionViewModel.insertTransaction(
//                classmachine = if(INDEX_CLASS_MACHINE == 0) false else true,
//                idmachine = MACHINE_ID,
//                price = PRICE_VALUE[0].price!!,
//                typetransaction = MENU_VALUE,
//                typePaymentTransaction = true,
//                navController = navController,
//                transactionMenuMachine = MENU_VALUE_MACHINE,
//                numbermachine = MACHINE_NUMBER
//            )
            paymentViewModel.reffID = 0L
        }
    }
}