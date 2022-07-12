package com.example.laundryapp.components.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laundryapp.TITLE_SCREEN
import com.example.laundryapp.api.menu.MenuModel
import com.example.laundryapp.api.price.PriceModel
import com.example.laundryapp.components.views.ViewMenuItem
import com.example.laundryapp.components.views.ViewTopBar
import com.example.laundryapp.components.views.ViewTransactionActiveItem
import com.example.laundryapp.navigation.Screens
import com.example.laundryapp.screens.WallSetting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuColumn(
    menuPriceModel: List<PriceModel>,
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(containerColor = Color.Transparent){ innerPadding ->
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.navigationBarsPadding(),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(items = menuPriceModel) { index, menuPrice ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    ViewMenuItem(
                        menu_title = menuPrice.menu!![0]!!.priceMenu.toString(),
                        menu_price_title = menuPrice.priceTitle.toString(),
                        menu_price = menuPrice.price.toString(),
                        menu_time = menuPrice.priceTime.toString(),
                        menu_packet = menuPrice.isPacket!!,
                        menu_service = menuPrice.isService!!,
                        navController = navController
                    )
                }
            }
        }
    }


}