package com.example.laundryapp.components

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.laundryapp.PAYMENT_SUCCESS
import com.example.laundryapp.R
import com.example.laundryapp.api.payment.PaymentViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentLoadData(
    paymentState: Int,
    rawQR: String,
    paymentViewModel: PaymentViewModel
) {
    val context = LocalContext.current
//    Log.d("debug", "Get $GET_DATA_MACHINE_STAT")
    when (paymentState) {
        0 -> {
            Log.d("debug", "Loading")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
//                paymentViewModel.reffID = 0L
//                paymentViewModel.getQR()
////                Log.d("debug", "Error")
//                Toast.makeText(context, "Regenerate", Toast.LENGTH_SHORT).show()
                CircularProgressIndicator()
            }
        }
        1 -> {
            Log.d("debug", "Success")
            if(rawQR != ""){
                val imageBit = QrGenerator(code = rawQR)

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
//                    ColumnMachine(machineModel = machine, selectedIndex = selectedIndex, onItemClick = onItemClick)
                    Image(
                        bitmap = imageBit.asImageBitmap(),
                        contentDescription = "Image QRcode"
                    )
                    if(PAYMENT_SUCCESS){
                        Image(
                            painterResource(id = R.drawable.ic_baseline_check_circle_24),
                            contentDescription = "Check Payment OK",
                            modifier = Modifier.size(300.dp)
                        )
                    }
                }
            }
        }
        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Can't load data",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
        3 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                paymentViewModel.reffID = 0L
                paymentViewModel.getQR()
//                Log.d("debug", "Error")
//                Toast.makeText(context, "Regenerate", Toast.LENGTH_SHORT).show()
                CircularProgressIndicator()
            }
        }
    }
}