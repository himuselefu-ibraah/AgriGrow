package com.ibrahim.agrigrow.repository


import com.ibrahim.agrigrow.data.AnimalHealthDao
import com.ibrahim.agrigrow.model.AnimalHealth
import kotlinx.coroutines.flow.Flow

class AnimalHealthRepository(private val dao: AnimalHealthDao) {
    val allHealthRecords: Flow<List<AnimalHealth>> = dao.getAllHealth()

    suspend fun insert(animalHealth: AnimalHealth) = dao.insertHealth(animalHealth)
    suspend fun delete(animalHealth: AnimalHealth) = dao.deleteHealth(animalHealth)
}











