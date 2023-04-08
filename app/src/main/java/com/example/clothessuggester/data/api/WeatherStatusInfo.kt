package com.example.clothessuggester.data.api

import android.util.Log
import com.example.clothessuggester.model.domain.WeatherStatus
import com.example.clothessuggester.model.domain.WeatherStatusConnection
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

 class WeatherStatusInfo:WeatherStatusConnection {
    private val client= OkHttpClient()
    override fun getDegree(callback: Callback) {
        val url= HttpUrl.Builder().
        scheme("https").
        host("api.openweathermap.org").
        addPathSegment("data/2.5/weather").
        addQueryParameter("lat","37.7749").
        addQueryParameter("lon","-122.4194").
        addQueryParameter("units","metric").
        addQueryParameter("appid","5591e9a22ae1fe0c591a03b968c6e3ff").build()
        val request= Request.Builder().url(url).build()
        client.newCall(request).enqueue(callback)
    }
}