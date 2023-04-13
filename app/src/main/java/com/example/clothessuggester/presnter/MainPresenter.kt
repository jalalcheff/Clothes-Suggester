package com.example.clothessuggester.presnter

import com.example.clothessuggester.model.domain.WeatherStatus
import com.example.clothessuggester.model.domain.api.WeatherStatusInfo
import com.example.clothessuggester.model.domain.local.ModifySharedPrefs
import com.example.clothessuggester.ui.IMainView
import okio.IOException

class MainPresenter {
    lateinit var iMainView: IMainView
    private var temperature: Double? = null
    fun presentData() {
        WeatherStatusInfo().getWeatherStatusResponse(
            ::onGetWeatherStatusTemperatureSuccess,
            ::onGetWeatherStatusTemperatureFailure
        )
        ModifySharedPrefs().getDataFromSharedPrefs(::onGetClothesFromSharedPreferences)
    }

    private fun onGetWeatherStatusTemperatureSuccess(weatherStatus: WeatherStatus) {
        temperature = weatherStatus.main.temp
        iMainView.onSuccessWeatherTemperature(weatherStatus.main.temp)
        iMainView.onSuccessCityName(weatherStatus.name)
    }

    private fun onGetWeatherStatusTemperatureFailure(exception: IOException) {
        iMainView.onFailureWeatherTemperature(exception)
        iMainView.onFailureCityName(exception)
    }

    private fun onGetClothesFromSharedPreferences(clothesImages: List<String>) {
        iMainView.onSuccessImageDrawable(clothesImages)
    }

    fun rePresentData() {
        ModifySharedPrefs().saveData(temperature)
        ModifySharedPrefs().getDataFromSharedPrefs { clothesImages ->
            iMainView.onSuccessImageDrawable(clothesImages)
        }
    }
}