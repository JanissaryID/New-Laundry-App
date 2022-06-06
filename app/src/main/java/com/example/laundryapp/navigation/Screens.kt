package com.example.laundryapp.navigation

sealed class Screens(val route: String){
    object Home: Screens(route = "home_screen")
//    object AddStore: Screens(route = "add_store_screen")
//    object Menu: Screens(route = "menu_screen")
//    object MenuPrice: Screens(route = "menu_price_screen")
//    object AddEditMenuPrice: Screens(route = "add_menu_price_screen")
//    object Price: Screens(route = "price_screen")
//    object AddEditPrice: Screens(route = "add_price_screen")
//    object Qris: Screens(route = "qris_screen")
//    object ListTransactions: Screens(route = "list_transactions_screen")
    object Machine: Screens(route = "machine_screen")
//    object AddEditMachine: Screens(route = "add_machine_screen")
    object Setting: Screens(route = "setting_screen")
}