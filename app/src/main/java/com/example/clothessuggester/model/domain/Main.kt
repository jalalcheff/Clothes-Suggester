package com.example.clothessuggester.model.domain

data class Main(
    var temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
)
