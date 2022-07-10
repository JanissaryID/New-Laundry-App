package com.example.laundryapp.components.machine

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.R
import com.example.laundryapp.api.machine.MachineModel

@Composable
fun MachineLoadData(
    machineState: Int,
    selectedIndex: Int,
    machine: List<MachineModel>,
    navController: NavController,
    onItemClick: (Int) -> Unit
) {
    val context = LocalContext.current
//    Log.d("debug", "Get $GET_DATA_MACHINE_STAT")
    when (machineState) {
        0 -> {
//            Log.d("debug", "Loading")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
//            Log.d("debug", "Success")
            if (!machine.isNullOrEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    MachineColumn(
                        machineModel = machine,
                        selectedIndex = selectedIndex,
                        onItemClick = onItemClick,
                        navController = navController
                    )
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
//            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
        3 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    val (StoreImage, TextEmpty) = createRefs()

//                    Image(painter = painterResource(
//                        id = R.drawable.ic_machine),
//                        contentDescription = "Machine Empty",
//                        modifier = Modifier
//                            .wrapContentHeight()
//                            .size(200.dp)
//                            .constrainAs(StoreImage)
//                            {
//                                top.linkTo(parent.top)
//                                start.linkTo(parent.start)
//                                bottom.linkTo(parent.bottom)
//                                end.linkTo(parent.end)
//                            }
//                    )

                    Text(
                        text = "Machine Empty",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .wrapContentHeight()
                            .constrainAs(TextEmpty)
                            {
                                top.linkTo(StoreImage.bottom, 8.dp)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }
//            Log.d("debug", "Error")
//            Toast.makeText(context, "No Transaction", Toast.LENGTH_SHORT).show()
        }
        4 -> {
//            Log.d("debug", "Error")
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}