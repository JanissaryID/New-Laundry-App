package com.example.laundryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.R

@Composable
fun AdminComponent() {
    ConstraintLayout() {
        val (Avatar, Name) = createRefs()
        val modifier = Modifier

        Image(painter = painterResource(
            id = R.drawable.ic_user),
            contentDescription = "Avatar user",
            modifier = modifier
                .wrapContentHeight()
                .size(48.dp)
                .constrainAs(Avatar){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = "Putri Sabila",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = modifier.wrapContentHeight().constrainAs(Name){
                start.linkTo(Avatar.end, 8.dp)
                top.linkTo(Avatar.top)
                bottom.linkTo(Avatar.bottom)
            }
        )
    }
}