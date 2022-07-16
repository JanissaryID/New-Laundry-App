package com.example.laundryapp.components.costumer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laundryapp.SEARCH_TEXT
import com.example.laundryapp.api.customer.CustomerModel
import com.example.laundryapp.api.price.PriceModel
import com.example.laundryapp.components.views.ViewCustomerItem
import com.example.laundryapp.components.views.ViewTransactionActiveItem
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun CustomerColumn(
    navController: NavController,
    customerModel: List<CustomerModel>
) {
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = customerModel) { index, customer ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewCustomerItem(
                    name = customer.name.toString(),
                    city = customer.city.toString(),
                    idCustomer = customer.id.toString(),
                    phone = customer.phone.toString(),
                    navController = navController
                )
            }
        }
    }
}