package com.example.clothessuggester.util

import android.content.Context
import android.content.SharedPreferences

object UtilSharedPrefs {
    private var sharedPrefs:SharedPreferences?=null
    private const val SHAREDPREFNAME="weather"
    private const val WEATHER="suggested_clothes"
    fun initPrefsUtil(context: Context)
    {
        sharedPrefs=context.getSharedPreferences(SHAREDPREFNAME,Context.MODE_PRIVATE)
    }
    var user:MutableSet<String>?
    get() = sharedPrefs?.getStringSet(WEATHER,null)
    set(value) {
        sharedPrefs?.edit()?.putStringSet(WEATHER,value)?.apply()
    }

}