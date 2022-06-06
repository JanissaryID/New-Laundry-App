package com.example.laundryapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val TITLE_SCREEN = listOf("Home", "Add Store", "Qris", "Menu", "Add Menu Price", "Price", "Add Price", "Transaction", "Machine", "Add Machine", "Setting")


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
var MACHINE_PACKET: Boolean by mutableStateOf(false)