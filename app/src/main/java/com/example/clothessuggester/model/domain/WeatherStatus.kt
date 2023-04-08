package com.example.clothessuggester.model.domain

data class WeatherStatus(
    val coord:Coordinate,
    val weather:List<Weather>,
    val base:String,
    val main:Main,
    val visibility:Int,
    val wind:Wind,
    val clouds:Clouds,
    val dt:Int,
    val sis:Sys,
    val timeZone:Int,
    val id:Int,
    val name:String,
    val cod:Int
)
