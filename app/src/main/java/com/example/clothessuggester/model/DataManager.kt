package com.example.clothessuggester.model

import com.example.clothessuggester.data.local.ModifySharedPrefs
import com.example.clothessuggester.model.domain.WeatherStatus

object DataManager {
    private var weather:WeatherStatus?=null
fun getWeatherTemperature():Double?
{

 return weather?.main?.temp
}
    fun getWeatherDescription(): String {
        return weather!!.weather[0].description
    }
    fun setWeather(weatherDegree:WeatherStatus)
    {
        weather=weatherDegree
    }
    fun setDataIntoSharedPrefs()
    {
        val sharedPrefs=ModifySharedPrefs()
        sharedPrefs.saveData(weather?.main?.temp)
    }
    fun getDataFromSharedPrefs():MutableList<String>
    {
        val imagesFromLocal= mutableListOf<String>()
        ModifySharedPrefs().loadData()?.forEach {
            imagesFromLocal.add(it)
        }

        return imagesFromLocal
    }

}