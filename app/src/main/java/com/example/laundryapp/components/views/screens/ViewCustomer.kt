package com.example.laundryapp.components.views.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.R
import com.example.laundryapp.api.customer.CustomerModel
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.api.price.PriceViewModel
import com.example.laundryapp.components.ViewTopComponent
import com.example.laundryapp.components.costumer.CustomerLoadData
import com.example.laundryapp.components.menu.MenuLoadData
import com.example.laundryapp.components.views.ViewButtonMenu
import com.example.laundryapp.components.views.ViewCustomerItem
import com.example.laundryapp.navigation.Screens
import java.util.*

@Composable
fun ViewCustomer(
    navController: NavController,
    customerViewModel: CustomerViewModel
) {
    val context = LocalContext.current

    val customerState = customerViewModel.stateCustomer
    val customer = customerViewModel.customerListResponse

    var text_search by remember { mutableStateOf(TextFieldValue(SEARCH_TEXT)) }

    Box(contentAlignment = Alignment.BottomEnd) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
            Surface(color = Color.Transparent,modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
            ) {
                ViewTopComponent(
                    title = "Customer",
                    navController = navController,
                    screenBack = if (BACK_CUSTOMER) Screens.PaymentLoginSetting.route else Screens.Home.route
                )
            }

            Surface(modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
            ){
                TextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        textColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.White,
                    ),
                    value = text_search,
                    onValueChange = {
                        text_search = it
                        SEARCH_TEXT = text_search.text
//                    Toast.makeText(context, "Search ${text_search.text}", Toast.LENGTH_SHORT).show()
//                    Log.d("debug", "Search ${text_search.text}")
                    },
                    placeholder = {
                        Text(
                            color = Color.Gray,
                            text = "Search",
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(color = Color.Transparent,) {
                val resultList = ArrayList<CustomerModel>()
                if (SEARCH_TEXT.isNullOrEmpty()){
                    CustomerLoadData(customerState = customerState, customer = customer, navController = navController)
                }
                else{
                    for (customers in customer){
                        if (customers.name.toString().lowercase(Locale.getDefault()).contains(SEARCH_TEXT.lowercase(
                                Locale.getDefault()))){
                            resultList.add(CustomerModel(
                                id = customers.id.toString(),
                                name = customers.name.toString(),
                                city = customers.city.toString(),
                                phone = customers.phone.toString()
                            ))
                        }
                    }
                    CustomerLoadData(customerState = customerState, customer = resultList, navController = navController)
                }
            }
        }
        Surface(
            modifier = Modifier.padding(end = 36.dp, bottom = 36.dp),
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 4.dp,
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(16)
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.size(56.dp)
                    .clickable { navController.navigate(route = Screens.AddCustomer.route) }
            ) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(
                        id = R.drawable.ic_plus),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(24.dp)
                )
            }
        }
    }

//    ConstraintLayout(modifier = Modifier
//        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
//        .fillMaxSize()
//    ) {
//        val (TopBar, SearchBar, CustomerContainer, FAB) = createRefs()
//

//
//
//



//    }
}