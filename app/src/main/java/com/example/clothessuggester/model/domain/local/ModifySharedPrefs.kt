package com.example.clothessuggester.model.domain.local

import android.util.Log
import com.example.clothessuggester.util.ListOfClothes
import com.example.clothessuggester.util.UtilSharedPrefs

class ModifySharedPrefs {
    private fun loadData(): MutableSet<String>? {
        return UtilSharedPrefs.user
    }

    fun saveData(data: Double?) {
        var shirt: String? = null
        var jeanz: String? = null
        var shoe: String? = null
        val summerClothes = ListOfClothes.getHotWeatherClothes().random()
        val winterClothes = ListOfClothes.getColdWeatherClothes().random()
        data?.let {
            when (it) {
                in 21.0..70.0 -> {
                    shirt = summerClothes.shirt.random()
                    jeanz = summerClothes.jeans.random()
                    shoe = summerClothes.shoe
                }
                in 0.0..20.0 -> {
                    shirt = winterClothes.shirt.random()
                    jeanz = winterClothes.jeans.random()
                    shoe = winterClothes.shoe

                }
                else -> {
                    throw java.lang.Exception("no such value")
                }
            }
        }

        val setOfClothes = mutableSetOf<String>()
        setOfClothes.add(shirt!!)
        setOfClothes.add(jeanz!!)
        setOfClothes.add(shoe!!)
        Log.i("meme",setOfClothes.toString())
        UtilSharedPrefs.user = setOfClothes
    }

    fun getDataFromSharedPrefs(showImagePresenter: (MutableList<String>) -> Unit) {
        val imagesFromLocal = mutableListOf<String>()
        ModifySharedPrefs().loadData()?.forEach {
            imagesFromLocal.add(it)
        }

        showImagePresenter(imagesFromLocal)
    }

}