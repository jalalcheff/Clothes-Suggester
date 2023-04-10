package com.example.clothessuggester.model.domain

import okhttp3.Callback

interface WeatherStatusConnection {
    fun getDegree(onGetCurrentResponse:(WeatherStatus)->Unit)
}