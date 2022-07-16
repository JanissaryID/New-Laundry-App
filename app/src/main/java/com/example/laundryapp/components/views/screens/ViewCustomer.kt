package com.example.laundryapp.components.views.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.*
import com.example.laundryapp.api.customer.CustomerModel
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.components.ViewTopComponent
import com.example.laundryapp.components.costumer.CustomerLoadData
import com.example.laundryapp.components.menu.MenuLoadData
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

    ConstraintLayout(modifier = Modifier
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .fillMaxSize()
    ) {
        val (TopBar, SearchBar, CustomerContainer) = createRefs()
        val modifier = Modifier

        Surface(color = Color.Transparent,modifier = modifier
            .wrapContentSize()
            .constrainAs(TopBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {
            ViewTopComponent(
                title = "Customer",
                navController = navController,
                screenBack = if (BACK_CUSTOMER) Screens.PaymentLoginSetting.route else Screens.Home.route
            )
        }

        Surface(modifier = Modifier.fillMaxWidth()
            .clip(CircleShape)
            .constrainAs(SearchBar){
            top.linkTo(parent.top, 52.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }){
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

        Surface(
            color = Color.Transparent,
            modifier = modifier
                .constrainAs(CustomerContainer) {
                    top.linkTo(SearchBar.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        {
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
                        ))
                    }
                }
                CustomerLoadData(customerState = customerState, customer = resultList, navController = navController)
            }
        }
    }
}