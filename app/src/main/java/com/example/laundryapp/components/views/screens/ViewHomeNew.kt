package com.example.laundryapp.components.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.R
import com.example.laundryapp.SCREEN_TYPE
import com.example.laundryapp.components.ViewAdminComponent
import com.example.laundryapp.components.informationStore
import com.example.laundryapp.components.menuContainer
import com.example.laundryapp.components.transactionFinishContainer
import com.example.laundryapp.data.DataMenu
import com.example.laundryapp.navigation.Screens

@Composable
fun ViewHomeNew(
    navController: NavController
) {
    Column(modifier = Modifier
        .padding(0.dp)
        .fillMaxSize()
    ){
        ConstraintLayout(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp)
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            val (AdminComponent, SettingIcon, StoreImage, InformationStore, MenuContainer) = createRefs()
            val modifier = Modifier

            Surface(color = Color.Transparent,modifier = modifier
                .wrapContentSize()
                .constrainAs(AdminComponent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }) {
                ViewAdminComponent(navController = navController)
            }


            Surface(color = Color.Transparent, modifier = modifier
                .clip(CircleShape)
                .size(40.dp)
                .constrainAs(SettingIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(AdminComponent.top)
                    bottom.linkTo(AdminComponent.bottom)
                }) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                    Image(painter = painterResource(
                        id = R.drawable.ic_gear),
                        contentDescription = "Setting",
                        modifier = Modifier
                            .clickable {
                                SCREEN_TYPE = 2
                                navController.navigate(route = Screens.PaymentLoginSetting.route)
                            }
                            .wrapContentHeight()
                            .size(40.dp)
                    )
                }
            }


            Surface(color = Color.White, shadowElevation = 16.dp, tonalElevation = 16.dp , modifier = modifier
                .clip(CircleShape)
                .size(132.dp)
                .constrainAs(StoreImage) {
                    end.linkTo(parent.end)
                    top.linkTo(AdminComponent.bottom, 32.dp)
                    start.linkTo(parent.start)
                }) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                    Image(painter = painterResource(
                        id = R.drawable.ic_store),
                        contentDescription = "Store Icon",
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(72.dp)
                    )
                }
            }

            Surface(color = Color.Transparent,modifier = modifier
                .fillMaxWidth()
                .constrainAs(InformationStore) {
                    top.linkTo(StoreImage.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                informationStore()
            }

            // Menu Container
            Surface(color = Color.Transparent,modifier = modifier
                .fillMaxWidth()
                .constrainAs(MenuContainer) {
                    top.linkTo(InformationStore.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                menuContainer(dataMenu = DataMenu, navController = navController)
            }
        }
        transactionFinishContainer()
    }
}