package com.example.laundryapp.data

import com.example.laundryapp.R

object DataMenu {
    private val menuIcon = intArrayOf(
        R.drawable.ic_plus,
        R.drawable.ic_bill,
        R.drawable.ic_user_add,
        R.drawable.ic_printer
    )

    private val menuname = arrayOf(
        "Make",
        "Active",
        "Customer",
        "Printer"
    )

    val listMenu: ArrayList<Menu>
        get() {
            val list = arrayListOf<Menu>()
            for (position in menuIcon.indices) {
                val menu = Menu()
                menu.menuIcon = menuIcon[position]
                menu.menuName = menuname[position]
                list.add(menu)
            }
            return list
        }
}
