package com.example.clothessuggester.model

import com.example.clothessuggester.model.domain.WeatherStatus
import com.example.clothessuggester.model.domain.local.ModifySharedPrefs

object DataManager {
    private var weather:WeatherStatus?=null

    fun setWeather(weatherDegree:WeatherStatus)
    {
        weather=weatherDegree
    }


}