package com.example.clothessuggester.ui

interface IMainView {
    fun onSuccessWeatherTempurutre(tempruture:Double)
    fun onSuccessImageDrawable(imageDrawables:List<String>)
}