package com.example.laundryapp.components.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.laundryapp.SCREEN_TYPE
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewMenuItem(
    menu_title: String,
    menu_price_title: String,
    menu_price: String,
    menu_time: String,
    menu_packet: Boolean,
    menu_service: Boolean,
    navController: NavController
) {

    val alfa = 0.8f

    Surface(shape = RoundedCornerShape(20)) {
        Column(modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .clickable {
                SCREEN_TYPE = 0
                navController.navigate(route = Screens.PaymentLoginSetting.route)
                Log.d("debug", "Selected Menu : ${menu_title}")
            }
        ){
            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            ) {

                val (MenuTitle, MenuPriceTitle, MenuPrice, MenuTime) = createRefs()

                Text(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
                    text = menu_title,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(MenuTitle)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
//                        end.linkTo(parent.end)
                        }
                )

                Text(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = alfa),
                    text = menu_price_title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(MenuPriceTitle)
                        {
                            top.linkTo(MenuTitle.bottom, 4.dp)
                            start.linkTo(parent.start)
                        }
                )
                Text(
                    text = menu_price,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = alfa),
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(MenuPrice)
                        {
                            bottom.linkTo(MenuTitle.bottom)
                            end.linkTo(parent.end)
                        }
                )
//            Log.d("debug", "Menu Time : ${menu_time}")
                Text(
                    text = if(menu_time != "0") menu_time + " Minute" else "" ,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = alfa),
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(MenuTime)
                        {
                            bottom.linkTo(MenuPriceTitle.bottom)
                            end.linkTo(parent.end)
                        }
                )
            }
            Divider(color = MaterialTheme.colorScheme.primary.copy(alpha = 1f), modifier = Modifier.padding(top = 4.dp))
            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {

                val (TypeMenu, PacketType, ServiceType) = createRefs()

                Text(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = alfa),
                    text = "Type Menu",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(TypeMenu)
                        {
                            top.linkTo(parent.top,4.dp)
                            start.linkTo(parent.start)
                        }
                )

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.constrainAs(PacketType)
                    {
                        top.linkTo(TypeMenu.bottom, 4.dp)
                        start.linkTo(parent.start)
                    }
                ) {
                    Text(
                        color = Color.White,
                        text = if (menu_packet) "Packet Menu" else "Not Packet Menu",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.constrainAs(ServiceType)
                    {
                        top.linkTo(TypeMenu.bottom, 4.dp)
                        start.linkTo(PacketType.end, 4.dp)
                    }
                ) {
                    Text(
                        color = Color.White,
                        text = if (menu_service) "Service Menu" else "Not Service Menu",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
        }
    }
}