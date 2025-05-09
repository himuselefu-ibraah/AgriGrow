package com.ibrahim.agrigrow.data

import androidx.room.*
import com.ibrahim.agrigrow.model.AnimalHealth
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalHealthDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealth(animalHealth: AnimalHealth)

    @Query("SELECT * FROM animal_health ORDER BY id DESC")
    fun getAllHealth(): Flow<List<AnimalHealth>>

    @Delete
    suspend fun deleteHealth(animalHealth: AnimalHealth)
}



