package com.example.clothessuggester.ui

import okio.IOException

interface IMainView {
    fun onSuccessWeatherTemperature(temperature: Double)
    fun onFailureWeatherTemperature(e: IOException)
    fun onSuccessCityName(cityName: String)
    fun onFailureCityName(e: IOException)
    fun onSuccessImageDrawable(imageDrawables: List<String>)
}