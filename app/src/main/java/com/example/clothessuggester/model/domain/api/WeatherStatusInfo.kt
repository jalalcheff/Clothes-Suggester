package com.example.clothessuggester.model.domain.api

import com.example.clothessuggester.model.domain.WeatherStatus
import com.example.clothessuggester.model.domain.WeatherStatusConnection
import com.example.clothessuggester.model.domain.local.ModifySharedPrefs
import com.google.gson.Gson
import okhttp3.*
import okio.IOException

class WeatherStatusInfo : WeatherStatusConnection {
    private val client = OkHttpClient()
    override fun getWeatherStatusResponse(
        onGetCurrentWeatherResponse: (WeatherStatus) -> Unit,
        onGetWeatherFailure: (IOException) -> Unit,
    ) {
        val url = HttpUrl.Builder().scheme("https").host("api.openweathermap.org")
            .addPathSegment("data/2.5/weather").addQueryParameter("lat", "37.7749")
            .addQueryParameter("lon", "-122.4194").addQueryParameter("units", "metric")
            .addQueryParameter("appid", "5591e9a22ae1fe0c591a03b968c6e3ff").build()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetWeatherFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val result =
                    Gson().fromJson(response.body?.string().toString(), WeatherStatus::class.java)
                ModifySharedPrefs().saveData(result.main.temp)
                onGetCurrentWeatherResponse(result)
            }

        })
    }


}