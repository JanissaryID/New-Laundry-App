package com.example.laundryapp.components.views.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.CLASS_MACHINE
import com.example.laundryapp.STORE_ID
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.components.ViewTopComponent
import com.example.laundryapp.components.menu.MenuLoadData
import com.example.laundryapp.components.views.ViewButtonMenu
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewMenuNew(
    priceViewModel: PriceViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val selectionMenu = listOf("Giant", "Titan")

    var selected_index_class by remember { mutableStateOf(0) }
    val on_click_index_class = { index: Int -> selected_index_class = index}

//    Log.d("debug", "Selected Class 1 : ${selected_index_class}")

    val menuPriceState = priceViewModel.statePrice
    val menuPrice = priceViewModel.priceListResponse

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize()
    ) {
        val (TopBar, ClassMachine, MenuContainer) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent,modifier = modifier
            .wrapContentSize()
            .constrainAs(TopBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {
            ViewTopComponent(
                title = "New Transaction",
                navController = navController,
                screenBack = Screens.Home.route
            )
        }

        Box(modifier = modifier
            .constrainAs(ClassMachine) {
                top.linkTo(parent.top, 52.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentAlignment = Alignment.Center) {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(50)
            ) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    modifier = modifier.wrapContentWidth()
                ){
                    itemsIndexed(items = selectionMenu){index, menu ->
                        ViewButtonMenu(
                            title = menu,
                            index = index,
                            selected = if(selected_index_class == index) false else true,
                            onClick = on_click_index_class,
                            color = Color.Transparent,
                            priceViewModel = priceViewModel
                        )
                    }
                }
            }
        }

        Surface(
            color = Color.Transparent,
            modifier = modifier
            .constrainAs(MenuContainer) {
                top.linkTo(ClassMachine.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        {
            MenuLoadData(
                menuState = menuPriceState,
                menuPrice = menuPrice,
                navController = navController
            )
        }
    }
}