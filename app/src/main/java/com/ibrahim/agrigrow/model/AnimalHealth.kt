package com.ibrahim.agrigrow.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal_health")
data class AnimalHealth(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val animalName: String,
    val healthStatus: String,
    val date: String,
    val treatment: String
)




