package com.ibrahim.agrigrow.repository


import com.ibrahim.agrigrow.data.CropDao
import com.ibrahim.agrigrow.model.Crop
import kotlinx.coroutines.flow.Flow

class CropRepository(private val cropDao: CropDao) {

    fun searchCrops(query: String): Flow<List<Crop>> {
        return cropDao.searchCrops(query)
    }

    fun getAllCrops(): Flow<List<Crop>> {
        return cropDao.getAllCrops()
    }

    suspend fun insertCrop(crop: Crop) {
        cropDao.insertCrop(crop)
    }

    suspend fun deleteCrop(crop: Crop) {
        cropDao.deleteCrop(crop)
    }
}
