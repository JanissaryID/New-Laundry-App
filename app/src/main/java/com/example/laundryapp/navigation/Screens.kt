package com.example.laundryapp.navigation

sealed class Screens(val route: String){
    object Home: Screens(route = "home_screen")
    object Menu: Screens(route = "menu_screen")
    object PaymentLoginSetting: Screens(route = "payment_login_setting_screen")
    object Customer: Screens(route = "customer_screen")
    object AddCustomer: Screens(route = "add_customer_screen")
    object Qris: Screens(route = "qris_screen")
    object TransactionActive: Screens(route = "transaction_active_screen")
    object TransactionFinish: Screens(route = "transaction_finish_screen")
    object Machine: Screens(route = "machine_screen")
    object Setting: Screens(route = "setting_screen")
}