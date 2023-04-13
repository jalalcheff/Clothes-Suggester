package com.example.clothessuggester.model.domain

import okio.IOException

interface WeatherStatusConnection {
    fun getWeatherStatusResponse(onGetCurrentWeatherResponse: (WeatherStatus) -> Unit,
                                 onGetWeatherFailure:(IOException)->Unit)
}