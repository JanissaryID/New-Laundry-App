package com.example.laundryapp.components.machine

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laundryapp.api.machine.MachineModel
import com.example.laundryapp.components.views.ViewMachineItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MachineColumn(
    machineModel: List<MachineModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
){
    val context = LocalContext.current

    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp),
    ) {
        itemsIndexed(items = machineModel) { index, machine ->
            AnimatedVisibility(visibleState = state) {
                ViewMachineItem(
                    index = if(selectedIndex != index) index else -1,
                    usedMachine = machine.machineStatus!!,
                    machineModel = machine,
                    onClick = onItemClick,
                    selected = if(selectedIndex == index) false else true
                )
            }
        }
    }
}