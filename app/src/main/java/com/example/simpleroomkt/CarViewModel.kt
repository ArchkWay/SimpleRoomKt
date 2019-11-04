package com.example.simpleroomkt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarRepository

    val allCars: LiveData<List<CarNote>>

    init {
        val carDao = CarDatabase.getDatabase(application).appCarDao()
        repository = CarRepository(carDao)
        allCars = repository.allCars
    }

    fun insert(arg: CarNote) = viewModelScope.launch {
        repository.insert(arg)
    }

    fun update(arg: CarNote) = viewModelScope.launch {
        repository.update(arg)
    }

    fun delete(arg: CarNote) = viewModelScope.launch {
        repository.delete(arg)
    }
}
