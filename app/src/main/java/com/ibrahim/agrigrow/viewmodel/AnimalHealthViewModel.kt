package com.ibrahim.agrigrow.viewmodel

import androidx.lifecycle.*
import com.ibrahim.agrigrow.model.AnimalHealth
import com.ibrahim.agrigrow.repository.AnimalHealthRepository
import kotlinx.coroutines.launch

class AnimalHealthViewModel(private val repository: AnimalHealthRepository) : ViewModel() {
    val healthRecords = repository.allHealthRecords

    fun addRecord(record: AnimalHealth) {
        viewModelScope.launch {
            repository.insert(record)
        }
    }

    fun deleteRecord(record: AnimalHealth) {
        viewModelScope.launch {
            repository.delete(record)
        }
    }
}

class AnimalHealthViewModelFactory(
    private val repository: AnimalHealthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalHealthViewModel::class.java)) {
            return AnimalHealthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}








