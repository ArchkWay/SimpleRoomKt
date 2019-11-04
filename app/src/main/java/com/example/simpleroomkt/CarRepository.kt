package com.example.simpleroomkt

import androidx.lifecycle.LiveData

class CarRepository(private val carDao: CarDao) {

    val allCars: LiveData<List<CarNote>> = carDao.fetchAll()

    suspend fun insert(arg: CarNote) {
        carDao.insert(arg)
    }

    suspend fun update(arg: CarNote) {
        carDao.update(arg)
    }

    suspend fun delete(arg: CarNote) {
        carDao.delete(arg)
    }
}
