package com.example.laundryapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val TITLE_SCREEN = listOf("Qris","Transaction Active", "Machine", "Setting", "Transaction")


var KEY_URL: String by mutableStateOf("")

//Store Variable
var STORE_ID: String by mutableStateOf("")
var STORE_NAME: String by mutableStateOf("")
var STORE_CITY: String by mutableStateOf("")
var STORE_PASSWORD: String by mutableStateOf("")

//Date Variable
var DATE_PICK: String by mutableStateOf("")

//Machine Variable
var MACHINE_SELECTED: Boolean by mutableStateOf(false)
var MACHINE_ID: String by mutableStateOf("")
var MACHINE_NUMBER: Int by mutableStateOf(0)
var MACHINE_TIME: Int by mutableStateOf(0)


//Transaction Variable
//var CLASS_MACHINE: Int by mutableStateOf(-1)
var PRICE: String by mutableStateOf("")
var MENU_MACHINE: String by mutableStateOf("")
var MENU_TRANSACTION: String by mutableStateOf("")
var PRICE_PACKET: Boolean by mutableStateOf(false)

//Qris Variable
var QRIS_CLIENT_KEY: String by mutableStateOf("")
var QRIS_CLIENT_ID: String by mutableStateOf("")
var QRIS_MERCHANT_ID: String by mutableStateOf("")
var PAYMENT_SUCCESS: Boolean by mutableStateOf(false)

//Packet Transaction Dryer

var TRANSACTION_ID_FOR_DRYER: String by mutableStateOf("")
var BUTTON_VISIBLE: Boolean by mutableStateOf(true)

//Menu Variable
var CLASS_MACHINE: Int by mutableStateOf(0)
