package com.ibrahim.agrigrow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.agrigrow.data.WeatherApiClient
import com.ibrahim.agrigrow.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = WeatherApiClient.api.getWeather(city, apiKey)
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Failed to fetch weather: ${e.localizedMessage}"
                _weather.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}
