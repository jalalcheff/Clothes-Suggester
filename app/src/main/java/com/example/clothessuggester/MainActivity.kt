package com.example.clothessuggester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.clothessuggester.data.api.WeatherStatusInfo
import com.example.clothessuggester.databinding.ActivityMainBinding
import com.example.clothessuggester.model.domain.WeatherStatus
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(),Callback{
     lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
            val weather=WeatherStatusInfo()
            weather.getDegree(this)
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.sheet)
        bottomSheetBehavior.peekHeight = 220
        setContentView(binding.root)
    }

    override fun onFailure(call: Call, e: IOException) {
        Log.i("mainActivity",e.message.toString())
    }

    override fun onResponse(call: Call, response: Response) {
        val result= Gson().fromJson(response.body?.string().toString(), WeatherStatus::class.java)

        result.let {
            Log.i("mainActivity",it.toString())
            runOnUiThread {
                binding.textView.text=it.main.temp.toString()
            }
        }
    }


}