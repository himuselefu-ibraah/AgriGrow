package com.ibrahim.agrigrow.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibrahim.agrigrow.repository.CropRepository

class CropCalendarViewModelFactory(private val repository: CropRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CropCalendarViewModel(repository) as T
    }
}
