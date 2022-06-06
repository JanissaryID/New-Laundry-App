package com.example.laundryapp.proto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProtoRepository(application)

    val keyUrl = repository.readProto.asLiveData()

    fun updateData(keyUrl: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateKey(keyUrl = keyUrl)
    }

    fun updateCityStore(cityStore: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateCityStore(cityStore = cityStore)
    }

    fun updatePasswordStore(passwordStore: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updatePasswordStore(passwordStore = passwordStore)
    }
}