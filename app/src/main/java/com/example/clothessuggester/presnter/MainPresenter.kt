package com.example.clothessuggester.presnter

import com.example.clothessuggester.model.domain.api.WeatherStatusInfo
import com.example.clothessuggester.model.domain.local.ModifySharedPrefs
import com.example.clothessuggester.ui.IMainView

class MainPresenter {
    lateinit var iMainView: IMainView
    private var tempreature:Double?=null
    fun presentData() {
       /* WeatherStatusInfo().getDegree { weatherStatus ->
            itemperautre.onSuccessWeatherTemperature(weatherStatus.main.temp)
        }*/
        WeatherStatusInfo().getWeatherStatusResponse(
            {weatherStatus->
                tempreature=weatherStatus.main.temp
                iMainView.onSuccessWeatherTemperature(weatherStatus.main.temp)
                iMainView.onSuccessCityName(weatherStatus.name)
            }
        ,
            {e->
                iMainView.onFailureWeatherTemperature(e)
                iMainView.onFailureCityName(e)
            }
        )

        ModifySharedPrefs().getDataFromSharedPrefs { clothesImages ->
            iMainView.onSuccessImageDrawable(clothesImages)
        }

    }
    fun rePresentData()
    {
        ModifySharedPrefs().saveData(tempreature)
        ModifySharedPrefs().getDataFromSharedPrefs { clothesImages->
            iMainView.onSuccessImageDrawable(clothesImages)
        }
    }
}