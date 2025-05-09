package com.ibrahim.agrigrow.data


import androidx.room.*
import com.ibrahim.agrigrow.model.Crop
import kotlinx.coroutines.flow.Flow

@Dao
interface CropDao {
    @Query("SELECT * FROM crops WHERE name LIKE '%' || :query || '%' OR region LIKE '%' || :query || '%'")
    fun searchCrops(query: String): Flow<List<Crop>>

    @Query("SELECT * FROM crops")
    fun getAllCrops(): Flow<List<Crop>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrop(crop: Crop)

    @Delete
    suspend fun deleteCrop(crop: Crop)

    @Query("SELECT * FROM crops WHERE id = :cropId")
    suspend fun getCropById(cropId: Int): Crop?

}

