package com.example.laundryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.laundryapp.R
import com.example.laundryapp.data.Menu

@Composable
fun menuItem(
    menu: Menu
) {
    ConstraintLayout() {
        val (MenuIcon, MenuName, Notification) = createRefs()

        Surface(color = Color.White, shadowElevation = 16.dp, tonalElevation = 16.dp , modifier = Modifier
            .clip(CircleShape)
            .size(62.dp)
            .constrainAs(MenuIcon) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            Image(painter = painterResource(
                id = menu.menuIcon),
                contentDescription = "Store Icon",
                modifier = Modifier
                    .wrapContentHeight()
                    .size(32.dp)

            )
        }

        if(menu.menuName == "Active"){
            Surface(color = MaterialTheme.colorScheme.primary, shadowElevation = 16.dp, tonalElevation = 16.dp , modifier = Modifier
                .clip(CircleShape)
                .size(16.dp)
                .constrainAs(Notification) {
                    top.linkTo(MenuIcon.top)
                    end.linkTo(MenuIcon.end)
                }) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "5",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    )
                }
            }

        }

        Text(
            text = menu.menuName,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.constrainAs(MenuName){
                start.linkTo(parent.start)
                top.linkTo(MenuIcon.bottom, 8.dp)
                end.linkTo(parent.end)
            }
        )
    }
}