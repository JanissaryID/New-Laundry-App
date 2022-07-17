package com.example.laundryapp.components.costumer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.laundryapp.R
import com.example.laundryapp.api.customer.CustomerModel
import com.example.laundryapp.api.customer.CustomerViewModel
import com.example.laundryapp.api.price.PriceModel
import com.example.laundryapp.components.menu.MenuColumn

@Composable
fun CustomerLoadData(
    customerState: Int,
    customer: List<CustomerModel>,
    navController: NavController,
) {
    val context = LocalContext.current
    when (customerState) {
        0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
            if (!customer.isNullOrEmpty()){
                CustomerColumn(customerModel = customer, navController = navController)
            }
        }
        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Can't load data",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
//            Log.d("debug", "Error")
//            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
        3 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    val (StoreImage, TextEmpty) = createRefs()

                    Image(painter = painterResource(
                        id = R.drawable.ic_user),
                        contentDescription = "Customer Empty",
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(200.dp)
                            .constrainAs(StoreImage)
                            {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = "Customer Empty",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .wrapContentHeight()
                            .constrainAs(TextEmpty)
                            {
                                top.linkTo(StoreImage.bottom, 8.dp)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }
        }
        4 -> {
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}