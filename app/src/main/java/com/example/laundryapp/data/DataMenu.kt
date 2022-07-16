package com.example.laundryapp.data

import com.example.laundryapp.R
import com.example.laundryapp.navigation.Screens

object DataMenu {

    private val menuIcon = intArrayOf(
        R.drawable.ic_plus,
        R.drawable.ic_bill,
        R.drawable.ic_user_add,
        R.drawable.ic_printer
    )

    private val menuName = arrayOf(
        "Make",
        "Active",
        "Customer",
        "Printer"
    )

    private val menuNav = arrayOf(
        Screens.Menu.route,
        Screens.TransactionActive.route,
        Screens.Customer.route,
        Screens.Setting.route
    )

    val listMenu: ArrayList<Menu>
        get() {
            val list = arrayListOf<Menu>()
            for (position in menuIcon.indices) {
                val menu = Menu()
                menu.menuIcon = menuIcon[position]
                menu.menuName = menuName[position]
                menu.menuNav = menuNav[position]
                list.add(menu)
            }
            return list
        }
}
