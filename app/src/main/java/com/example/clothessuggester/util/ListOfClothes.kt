package com.example.clothessuggester.util

import com.example.clothessuggester.R
import com.example.clothessuggester.model.domain.Clothes

object ListOfClothes {
    fun getHotWeatherClothes(): List<Clothes> {
        val clothes = mutableListOf<Clothes>()
        val shirts = listOf(
            R.drawable.shirt1.toString(),
            R.drawable.shirt2.toString(),
            (R.drawable.shirt3.toString())
        )
        val jeanz = listOf(
            R.drawable.jeans1.toString(),
            R.drawable.jeans2.toString(),
            (R.drawable.jeans3.toString())
        )
        val shoe = listOf(
            R.drawable.shoe1.toString(),
            R.drawable.shoe2.toString(),
            (R.drawable.shoe3.toString())
        )
        clothes.add(Clothes(shirts, jeanz, shoe))
        return clothes
    }

    fun getColdWeatherClothes(): List<Clothes> {
        val clothes = mutableListOf<Clothes>()
        val shirts = listOf(
            R.drawable.jacket1.toString(),
            R.drawable.jacket2.toString(),
            (R.drawable.jacket3.toString())
        )
        val jeanz = listOf(
            R.drawable.jeans1.toString(),
            R.drawable.jeans2.toString(),
            (R.drawable.jeans3.toString())
        )
        val shoe = listOf(
            R.drawable.shoe1.toString(),
            R.drawable.shoe2.toString(),
            (R.drawable.shoe3.toString())
        )
        clothes.add(Clothes(shirts, jeanz, shoe))
        return clothes

    }

}