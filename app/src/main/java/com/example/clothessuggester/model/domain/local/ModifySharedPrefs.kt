package com.example.clothessuggester.model.domain.local

import android.util.Log
import com.example.clothessuggester.model.domain.Clothes
import com.example.clothessuggester.util.ListOfClothes
import com.example.clothessuggester.util.UtilSharedPrefs

class ModifySharedPrefs {
    private var shirt: String? = null
    private var jeanz: String? = null
    private var shoe: String? = null
    private fun loadData(): MutableSet<String>? {
        return UtilSharedPrefs.user
    }

    fun saveData(data: Double?) {


        val summerClothes = ListOfClothes.getHotWeatherClothes().random()
        val winterClothes = ListOfClothes.getColdWeatherClothes().random()
        data?.let { temperature ->
            if (temperature > TEMPERATURE_THRESHOLD) {
                bindClothesDependOnTemperature(summerClothes)
            } else {
                bindClothesDependOnTemperature(winterClothes)
            }
        }

        val setOfClothes = mutableSetOf<String>()
        setOfClothes.add(shirt!!)
        setOfClothes.add(jeanz!!)
        setOfClothes.add(shoe!!)
        Log.i("meme", setOfClothes.toString())
        UtilSharedPrefs.user = setOfClothes
    }

    fun getDataFromSharedPrefs(showImagePresenter: (MutableList<String>) -> Unit) {
        val imagesFromLocal = mutableListOf<String>()
        ModifySharedPrefs().loadData()?.forEach {
            imagesFromLocal.add(it)
        }

        showImagePresenter(imagesFromLocal)
    }

    private fun bindClothesDependOnTemperature(season: Clothes) {
        shirt = season.shirt.random()
        jeanz = season.jeans.random()
        shoe = season.shoe
    }

    companion object {
        const val TEMPERATURE_THRESHOLD = 10.0
    }

}