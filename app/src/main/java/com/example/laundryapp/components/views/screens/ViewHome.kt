package com.example.laundryapp.components.views

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.menu.MenuModel
import com.example.laundryapp.api.menu.MenuViewModel
import com.example.laundryapp.api.price.PriceModel
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewHome(
    menuViewModel: MenuViewModel,
    priceViewModel: PriceViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val selectionMenu = listOf("Giant", "Titan")

    var menu by remember { mutableStateOf("") }
    var menutemp = ""

    var selectedMenu by remember { mutableStateOf("") }
    var selectedIdMenu by remember { mutableStateOf("") }
    var selectedPacket by remember { mutableStateOf(false) }
    var selectedPrice by remember { mutableStateOf("") }
    var selectedPriceNominal by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    var expandedMenu by remember { mutableStateOf(false) }
    var expandedPrice by remember { mutableStateOf(false) }
    var selected_index_class by remember {mutableStateOf(-1)}
    val on_click_index_class = { index: Int -> selected_index_class = index}

    var enablePrice by remember { mutableStateOf(false) }

    var buttonOn by remember { mutableStateOf(false) }

    if(!selectedMenu.isNullOrEmpty() && selected_index_class != -1){
        enablePrice = true
        priceViewModel.getPrice(
            classPrice = if (selected_index_class == 0) false else true,
            isPacket = selectedPacket,
            idMenu = selectedIdMenu
        )
    }
    else{
        enablePrice = false
        expandedPrice = false
    }

    if(selectedMenu.isNullOrEmpty()){
        selected_index_class = -1
    }

    if(selected_index_class == -1){
        selectedPrice = ""
    }



    val rotationStateMenu by animateFloatAsState(
        targetValue = (
            if (expandedMenu){
                180f
            } else{
                0f
            }
        )
    )
    val rotationStatePrice by animateFloatAsState(
        targetValue = (
            if (expandedPrice){
                180f
            } else{
                0f
            }
        )
    )

    var menuPrice: List<MenuModel> by remember {
        mutableStateOf(listOf())
    }

    var listPrice: List<PriceModel> by remember {
        mutableStateOf(listOf())
    }

    menuPrice = menuViewModel.menuListResponse
    listPrice = priceViewModel.priceListResponse

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {
        val (TransactionFinish, TransactionActive, PricePick, ClassMachine, MenuPick, ButtonPick) = createRefs()
        val modifier = Modifier

        Column(modifier = modifier.constrainAs(MenuPick) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            OutlinedTextField(
                label = { Text(text = "Menu") },
                value = selectedMenu,
                onValueChange = {
                    selectedMenu = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
                trailingIcon = {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "contentDescription",
                        Modifier
                            .clickable { expandedMenu = !expandedMenu }
                            .rotate(rotationStateMenu))
                },
                readOnly = true,
                enabled = true
            )
            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false },
                modifier = modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
            ) {
                menuPrice.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedMenu = label.priceMenu.toString()
//                        MACHINE_TIME = label.priceMenu
//                        MENU = label.id.toString()
//                        MENU_TITLE = label.priceMenu.toString()
                        selectedPacket = label.isPacket!!
                        selectedIdMenu = label.id.toString()
                        expandedMenu = false
//                        Log.d("debug", "Value : ${selectedMenu} ${selectedMenuTemp}")
                    }, text = {
                        Text(text = label.priceMenu.toString())
                    })
                }
            }
        }

        Box(modifier = modifier
            .constrainAs(ClassMachine) {
                top.linkTo(MenuPick.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentAlignment = Alignment.Center) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                modifier = modifier.wrapContentWidth()
            ){
                itemsIndexed(items = selectionMenu){index, menu ->
                    ViewButtonMenu(
                        title = menu,
                        index = if(selected_index_class != index){
                            index
                        }  else -1,
                        selected = if(selected_index_class == index) false else true,
                        onClick = on_click_index_class,
//                        priceViewModel = priceViewModel,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        Column(modifier = modifier.constrainAs(PricePick) {
            top.linkTo(ClassMachine.bottom, 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            OutlinedTextField(
                label = { Text(text = "Price") },
                value = selectedPrice,
                onValueChange = {
                    selectedPrice = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
                trailingIcon = {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "contentDescription",
                        Modifier
                            .clickable { expandedPrice = !expandedPrice }
                            .rotate(rotationStatePrice))
                },
                readOnly = true,
                enabled = enablePrice
            )
            DropdownMenu(
                expanded = expandedPrice,
                onDismissRequest = { expandedPrice = false },
                modifier = modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
            ) {
                listPrice.forEach { label ->
                    DropdownMenuItem(onClick = {
                        MACHINE_TIME = label.priceTime!!
                        selectedPrice = "${label.priceTitle.toString()} - ${label.price.toString()}"
                        selectedPriceNominal = label.price.toString()
                        MENU_MACHINE = label.priceTitle.toString()
                        PRICE = label.price.toString()
                        PRICE_PACKET = label.isPacket!!
                        expandedPrice = false
                    }, text = {
                        Text(text = "${label.priceTitle.toString()} - ${label.price.toString()}")
                    })
                }
            }
        }

        ButtonView(title = "Transaction Active", modifier.constrainAs(TransactionActive) {
            top.linkTo(PricePick.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = true
        ){
            navController.navigate(route = Screens.TransactionActive.route)
        }

        ButtonView(title = "Transaction Finish", modifier.constrainAs(TransactionFinish) {
            top.linkTo(TransactionActive.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = true
        ){
            navController.navigate(route = Screens.TransactionFinish.route)
        }

        if(!selectedIdMenu.isNullOrEmpty() && !selectedPrice.isNullOrEmpty() && selected_index_class != -1) buttonOn = true else buttonOn = false

        ButtonView(title = "Pick Machine", modifier.constrainAs(ButtonPick) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable =
        if (buttonOn){
            true
        }
        else{
            false
        }
        ){
            buttonOn = false
            CLASS_MACHINE = selected_index_class
            MENU_TRANSACTION = selectedMenu
            navController.navigate(route = Screens.Machine.route)
            Log.d("debug", "price Menu ${selectedPriceNominal}")
//            Log.d("debug", "value ${MENU_PACKET}")
//            Toast.makeText(context, "Value Menu $selectedPrice", Toast.LENGTH_SHORT).show()
        }
    }

    menutemp = menu
    menu = selectedIdMenu

    if(menu != menutemp){
        selected_index_class = -1
    }
}