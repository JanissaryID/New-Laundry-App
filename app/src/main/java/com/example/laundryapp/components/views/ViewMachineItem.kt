package com.example.laundryapp.components.views

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laundryapp.*
import com.example.laundryapp.api.machine.MachineModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewMachineItem(
    machineModel: MachineModel,
    usedMachine: Boolean,
    color: Color = MaterialTheme.colorScheme.surface,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp),
        shape = RoundedCornerShape(25),
        containerColor = Color.Transparent,
        border = if (usedMachine) BorderStroke(6.dp, MaterialTheme.colorScheme.secondary) else BorderStroke(6.dp, MaterialTheme.colorScheme.primary)
    ) {
        Surface(
            modifier = Modifier
                .padding(7.dp)
                .clickable {
                    if(!usedMachine){
                        Log.d("debug", "Selected Machine $selected")
                        MACHINE_SELECTED = selected
                        MACHINE_ID = machineModel.id!!
                        MACHINE_NUMBER = machineModel.machineNumber!!
//                        MACHINE_TIME = machineModel.priceTime!!
//                        MACHINE_PACKET = machineModel.isPacket!!
                        onClick.invoke(index)
                    }
                },
            color = if (usedMachine){ MaterialTheme.colorScheme.secondary }
            else{ if (selected) MaterialTheme.colorScheme.primary else color },
            shape = RoundedCornerShape(22),
            contentColor = if (selected) color else MaterialTheme.colorScheme.primary,
        ){
            Text(
                text = machineModel.machineNumber!!.toString(),
                fontSize = 42.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(24.dp).fillMaxSize()
            )
        }
    }
}
