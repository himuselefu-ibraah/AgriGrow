package com.ibrahim.agrigrow.data


data class WeatherResponse(
    val name: String,
    val main: Main,
    val rain: Rain?,
)

data class Main(
    val temp: Float,
    val humidity: Int
)

data class Rain(
    val `1h`: Float? // Rainfall in the last hour (nullable since not all data will have this)
)



