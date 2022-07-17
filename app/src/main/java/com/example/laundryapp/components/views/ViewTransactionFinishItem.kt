package com.example.laundryapp.components.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.laundryapp.BUTTON_VISIBLE
import com.example.laundryapp.CLASS_MACHINE
import com.example.laundryapp.MENU_TRANSACTION
import com.example.laundryapp.TRANSACTION_ID_FOR_DRYER
import com.example.laundryapp.components.ButtonView
import com.example.laundryapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTransactionFinishItem(
    title_Menu: String,
    menu_machine: String,
    class_machine: String,
    date: String,
    price: String,
    index: String,
    payment: String,
    is_packet: Boolean,
    is_list_transaction: Boolean,
    step_one: Boolean,
    navController: NavController,
    number_machine: Int,
    onClick: (String) -> Unit
) {
    val context = LocalContext.current

    var expandedState by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = (
                if (expandedState){
                    180f
                } else{
                    0f
                })
    )

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable {
            expandedState = !expandedState
            onClick.invoke(index)
        }
    ) {
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 4.dp)
            .fillMaxWidth()) {

            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                val (NumberTitle, TitleMenu, MenuMachine, ClassMachine, Date, Price, Payment) = createRefs()

                Text(
                    text = number_machine.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(NumberTitle)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = title_Menu,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(TitleMenu)
                        {
                            top.linkTo(NumberTitle.bottom)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
                Text(
                    text = class_machine,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(ClassMachine)
                        {
                            top.linkTo(TitleMenu.bottom)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
                Text(
                    text = menu_machine,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(MenuMachine)
                        {
                            top.linkTo(ClassMachine.bottom)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
                Text(
                    text = date,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Date)
                        {
                            top.linkTo(NumberTitle.bottom, 4.dp)
                            end.linkTo(parent.end, 4.dp)
                        }
                )
                Text(
                    text = "$payment",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = Color("#228B22".toColorInt()),
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Payment)
                        {
                            bottom.linkTo(Price.top)
                            end.linkTo(parent.end, 4.dp)
                        }
                )
                Text(
                    text = "IDR $price",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = Color("#228B22".toColorInt()),
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Price)
                        {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end, 4.dp)
                        }
                )
            }
        }
    }
}