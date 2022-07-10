package com.example.laundryapp.data

import com.example.laundryapp.R

object DataTransaction {
    private val TransactionMenuName = arrayOf(
        "Wash",
        "Wash-Dry",
        "Wash-Dry-Iron",
        "Iron-Fold"
    )

    private val TransactionMenu = arrayOf(
        "Giant 8 Kg",
        "Giant 8 Kg",
        "Titan 12 Kg",
        "Titan 12 Kg"
    )

    private val TransactionTypePayment = arrayOf(
        "Cash",
        "Cash",
        "Cash",
        "Cash"
    )

    private val TransactionDate = arrayOf(
        "8 Mei 2022",
        "8 Mei 2022",
        "8 Mei 2022",
        "8 Mei 2022"
    )

    private val TransactionPrice = arrayOf(
        "IDR 34.000",
        "IDR 55.000",
        "IDR 80.000",
        "IDR 25.000"
    )

    private val TransactionAdmin = arrayOf(
        "Putri Sabila",
        "Putri Sabila",
        "Putri Sabila",
        "Putri Sabila"
    )

    val listTransaction: ArrayList<Transaction>
        get() {
            val list = arrayListOf<Transaction>()
            for (position in TransactionMenuName.indices) {
                val transaction = Transaction()
                transaction.transactionMenuName = TransactionMenuName[position]
                transaction.transactionMenu = TransactionMenu[position]
                transaction.transactionPaymentType = TransactionTypePayment[position]
                transaction.transactionDate = TransactionDate[position]
                transaction.transactionPrice = TransactionPrice[position]
                transaction.transactionAdmin = TransactionAdmin[position]
                list.add(transaction)
            }
            return list
        }
}