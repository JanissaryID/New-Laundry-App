package com.example.laundryapp.components.views

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ViewQris(
    paymentViewModel: PaymentViewModel,
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val statePayment = paymentViewModel.stateQR
    val qris = paymentViewModel.rawString
    var buttonClick:  Boolean = false
    var buttonCheckClick:  Boolean = false

    PAYMENT_SUCCESS = false

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)) {
        val (title,number, content, buttonOK, buttonCheck) = createRefs()
        val modifier: Modifier = Modifier

        Log.d("debug", "State QR $statePayment $PAYMENT_SUCCESS")

        Text(
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
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
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
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
            PaymentLoadData(paymentState = statePayment, rawQR = qris, paymentViewModel = paymentViewModel)
            if (paymentViewModel.reffID != 0L){
                paymentViewModel.getResponsePayment()
            }
        }

        ButtonView(title = "Check", modifier.constrainAs(buttonCheck) {
            bottom.linkTo(buttonOK.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = if(statePayment == 1 && !buttonCheckClick) true else false){
            PAYMENT_SUCCESS = true
            buttonClick = true
            buttonCheckClick = true
        }

        ButtonView(title = "Turn On Machine", modifier.constrainAs(buttonOK) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = if(PAYMENT_SUCCESS && buttonClick) true else false){

            buttonClick = false

            machineViewModel.updateMachine(
                navController = navController,
                idMachine = MACHINE_ID,
                timeMachine = MACHINE_TIME,
                isPacket = PRICE_PACKET,
                typePayment = true,
                transactionViewModel = transactionViewModel
            )
            paymentViewModel.reffID = 0L
            Log.d("debug", "Number $MACHINE_NUMBER $MACHINE_ID $MACHINE_TIME")
        }
    }
}