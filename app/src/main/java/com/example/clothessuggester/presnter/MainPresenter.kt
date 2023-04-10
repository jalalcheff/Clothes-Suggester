package com.example.clothessuggester.presnter

import com.example.clothessuggester.model.domain.api.WeatherStatusInfo
import com.example.clothessuggester.model.domain.local.ModifySharedPrefs
import com.example.clothessuggester.ui.IMainView

class MainPresenter {
    lateinit var itemperautre:IMainView
    fun presentData(){
        WeatherStatusInfo().getDegree {weatherStatus ->
            itemperautre.onSuccessWeatherTempurutre(weatherStatus.main.temp)
        }
        ModifySharedPrefs().getDataFromSharedPrefs {clothesImages->
            itemperautre.onSuccessImageDrawable(clothesImages)
        }

    }
}