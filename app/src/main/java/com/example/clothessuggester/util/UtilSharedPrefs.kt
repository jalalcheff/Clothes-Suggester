package com.example.clothessuggester.util

import android.content.Context
import android.content.SharedPreferences

object UtilSharedPrefs {
    private var sharedPrefs:SharedPreferences?=null
    private const val SHAREDPREFNAME="weather"
    private const val WEATHER_COLD="weatherCold"
    fun initPrefsUtil(context: Context)
    {
        sharedPrefs=context.getSharedPreferences(SHAREDPREFNAME,Context.MODE_PRIVATE)
    }
    var user:String?
    get() = sharedPrefs?.getString(WEATHER_COLD,null)
    set(value) {
        sharedPrefs?.edit()?.putString(WEATHER_COLD,value)?.apply()
    }

}