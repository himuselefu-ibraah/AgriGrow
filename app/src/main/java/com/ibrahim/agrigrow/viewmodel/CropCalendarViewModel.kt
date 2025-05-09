package com.ibrahim.agrigrow.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.agrigrow.model.Crop
import com.ibrahim.agrigrow.repository.CropRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CropCalendarViewModel(private val repository: CropRepository) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _cropList = MutableStateFlow<List<Crop>>(emptyList())
    val cropList: StateFlow<List<Crop>> = _cropList

    init {
        observeCrops()
    }

    private fun observeCrops() {
        viewModelScope.launch {
            query
                .debounce(300)
                .flatMapLatest {
                    if (it.isEmpty()) repository.getAllCrops() else repository.searchCrops(it)
                }
                .collect {
                    _cropList.value = it
                }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    fun addSampleCrop(crop: Crop) {
        viewModelScope.launch {
            repository.insertCrop(crop)
        }
    }
    init {
        insertSampleCrops()
        observeCrops()
    }

    private fun insertSampleCrops() {
        viewModelScope.launch {
            repository.insertCrop(Crop(name = "Wheat", region = "Rift Valley", plantingMonth = "August", harvestingMonth = "September"))
            repository.insertCrop(Crop(name = "Rice", region = "Nairobi", plantingMonth = "February", harvestingMonth = "November"))
            repository.insertCrop(Crop(name = "Barley", region = "Western", plantingMonth = "June", harvestingMonth = "March"))
            repository.insertCrop(Crop(name = "Sorghum", region = "Central", plantingMonth = "May", harvestingMonth = "September"))
            repository.insertCrop(Crop(name = "Millet", region = "Nyanza", plantingMonth = "March", harvestingMonth = "August"))
            repository.insertCrop(Crop(name = "Cassava", region = "Coast", plantingMonth = "July", harvestingMonth = "January"))
            repository.insertCrop(Crop(name = "Sweet Potato", region = "Rift Valley", plantingMonth = "April", harvestingMonth = "October"))
            repository.insertCrop(Crop(name = "Irish Potato", region = "Western", plantingMonth = "May", harvestingMonth = "August"))
            repository.insertCrop(Crop(name = "Tomato", region = "Eastern", plantingMonth = "March", harvestingMonth = "June"))
            repository.insertCrop(Crop(name = "Onion", region = "Nairobi", plantingMonth = "February", harvestingMonth = "May"))
            repository.insertCrop(Crop(name = "Cabbage", region = "Central", plantingMonth = "January", harvestingMonth = "April"))
            repository.insertCrop(Crop(name = "Carrot", region = "Nyanza", plantingMonth = "March", harvestingMonth = "July"))
            repository.insertCrop(Crop(name = "Spinach", region = "Western", plantingMonth = "October", harvestingMonth = "December"))
            repository.insertCrop(Crop(name = "Kale", region = "Eastern", plantingMonth = "November", harvestingMonth = "January"))
            repository.insertCrop(Crop(name = "Pumpkin", region = "Coast", plantingMonth = "June", harvestingMonth = "September"))
            repository.insertCrop(Crop(name = "Groundnut", region = "Rift Valley", plantingMonth = "May", harvestingMonth = "October"))
            repository.insertCrop(Crop(name = "Sunflower", region = "Central", plantingMonth = "July", harvestingMonth = "November"))
            repository.insertCrop(Crop(name = "Soybean", region = "Western", plantingMonth = "April", harvestingMonth = "August"))
            repository.insertCrop(Crop(name = "Tea", region = "Nyanza", plantingMonth = "January", harvestingMonth = "December"))
            repository.insertCrop(Crop(name = "Coffee", region = "Central", plantingMonth = "February", harvestingMonth = "October"))
            repository.insertCrop(Crop(name = "Sugarcane", region = "Coast", plantingMonth = "March", harvestingMonth = "February"))
            repository.insertCrop(Crop(name = "Cotton", region = "Eastern", plantingMonth = "May", harvestingMonth = "September"))
            repository.insertCrop(Crop(name = "Avocado", region = "Rift Valley", plantingMonth = "March", harvestingMonth = "June"))
            repository.insertCrop(Crop(name = "Banana", region = "Western", plantingMonth = "April", harvestingMonth = "October"))
            repository.insertCrop(Crop(name = "Mango", region = "Eastern", plantingMonth = "November", harvestingMonth = "March"))
            repository.insertCrop(Crop(name = "Orange", region = "Nairobi", plantingMonth = "July", harvestingMonth = "December"))
            repository.insertCrop(Crop(name = "Pineapple", region = "Coast", plantingMonth = "June", harvestingMonth = "October"))
            repository.insertCrop(Crop(name = "Passion Fruit", region = "Central", plantingMonth = "April", harvestingMonth = "August"))
            repository.insertCrop(Crop(name = "Pawpaw", region = "Nyanza", plantingMonth = "February", harvestingMonth = "July"))
            repository.insertCrop(Crop(name = "Peas", region = "North Eastern", plantingMonth = "January", harvestingMonth = "May"))
            repository.insertCrop(Crop(name = "Lettuce", region = "Nairobi", plantingMonth = "March", harvestingMonth = "May"))
        }
    }

}

