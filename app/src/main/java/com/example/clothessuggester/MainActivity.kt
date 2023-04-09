package com.example.clothessuggester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.clothessuggester.data.api.WeatherStatusInfo
import com.example.clothessuggester.databinding.ActivityMainBinding
import com.example.clothessuggester.model.DataManager
import com.example.clothessuggester.model.domain.WeatherStatus
import com.example.clothessuggester.util.SetUpBottomSheet
import com.example.clothessuggester.util.UtilSharedPrefs
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(),Callback{
     private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
            val weather=WeatherStatusInfo()
            weather.getDegree(this)
        UtilSharedPrefs.initPrefsUtil(this)
        SetUpBottomSheet.prepare(binding.sheet)
        setContentView(binding.root)
    }

    override fun onFailure(call: Call, e: IOException) {
        throw Exception(e.message)
    }

    override fun onResponse(call: Call, response: Response) {
        val result= Gson().fromJson(response.body?.string().toString(), WeatherStatus::class.java)
        result.let {
                DataManager.setWeather(it)

        }
        runOnUiThread {
            showOnUi()
        }
    }

    private fun showOnUi() {
        binding.textView.text =DataManager.getWeatherTemperature().toString()
        DataManager.setDataIntoSharedPrefs()
        val upperClothes:ImageView= binding.sheet.findViewById(R.id.lower_clothes)
        upperClothes.setImageDrawable(ContextCompat.getDrawable(this,DataManager.getDataFromSharedPrefs()[0].toInt()))
        val lowerClothes:ImageView= binding.sheet.findViewById(R.id.upper_clothes)
        lowerClothes.setImageDrawable(ContextCompat.getDrawable(this,DataManager.getDataFromSharedPrefs()[1].toInt()))
        val shoe:ImageView= binding.sheet.findViewById(R.id.shoe)
        shoe.setImageDrawable(ContextCompat.getDrawable(this,DataManager.getDataFromSharedPrefs()[2].toInt()))
    }

}