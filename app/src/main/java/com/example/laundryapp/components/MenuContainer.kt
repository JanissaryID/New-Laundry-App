package com.example.laundryapp.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laundryapp.data.DataMenu

@Composable
fun menuContainer(dataMenu: DataMenu, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(0.dp),
    ) {
        items(items = dataMenu.listMenu) { menu ->
            menuItem(menu = menu, navController = navController)
        }
    }
}