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
        val url = HttpUrl.Builder()
            .scheme(HTTPS_SCHEME)
            .host(HOST_NAME)
            .addPathSegment(PATH_SEGMENT)
            .addQueryParameter("lat", LATITUDE)
            .addQueryParameter("lon", LONGITUDE)
            .addQueryParameter("units", UNIT)
            .addQueryParameter("appid", APPID).build()
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

    companion object {
        const val HTTPS_SCHEME = "https"
        const val HOST_NAME = "api.openweathermap.org"
        const val PATH_SEGMENT = "data/2.5/weather"
        const val LONGITUDE = "44.482458267104164"
        const val LATITUDE = "33.397001631478176"
        const val UNIT = "metric"
        const val APPID = "5591e9a22ae1fe0c591a03b968c6e3ff"
    }

}