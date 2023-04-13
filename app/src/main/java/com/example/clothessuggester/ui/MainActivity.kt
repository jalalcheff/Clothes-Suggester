package com.example.clothessuggester.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.clothessuggester.R
import com.example.clothessuggester.databinding.ActivityMainBinding
import com.example.clothessuggester.presnter.MainPresenter
import com.example.clothessuggester.util.SetUpBottomSheet
import com.example.clothessuggester.util.UtilSharedPrefs
import okio.IOException

class MainActivity : AppCompatActivity(), IMainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        presenter.iMainView = this
        UtilSharedPrefs.initPrefsUtil(this)
        SetUpBottomSheet.prepare(binding.sheet)
        runOnUiThread {
            showOnUi()
        }
        binding.sheet[0].findViewById<Button>(R.id.suggest_button).setOnClickListener {
            it as Button
            presenter.rePresentData()
        }
        setContentView(binding.root)
    }


    private fun showOnUi() {
        presenter.presentData()
    }

    override fun onSuccessWeatherTemperature(temperature: Double) {
        runOnUiThread { binding.textView.text = temperature.toString() }
    }

    override fun onFailureWeatherTemperature(e: IOException) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccessCityName(cityName: String) {
        binding.cityName.text = cityName
    }

    override fun onFailureCityName(e: IOException) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccessImageDrawable(imageDrawables: List<String>) {
        bindData(imageDrawables)
    }

    override fun onSuggestNewClothes(imageDrawables: List<String>) {
        bindData(imageDrawables)
    }

    private fun bindData(imageDrawables: List<String>) {
        binding.sheet.let {
            val upperClothes: ImageView = it.findViewById(R.id.lower_clothes)
            val lowerClothes: ImageView = it.findViewById(R.id.upper_clothes)
            val shoe: ImageView = it.findViewById(R.id.shoe)
            upperClothes.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    imageDrawables[0].toInt()
                )
            )
            lowerClothes.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    imageDrawables[1].toInt()
                )
            )
            shoe.setImageDrawable(ContextCompat.getDrawable(this, imageDrawables[2].toInt()))
        }
        /*    val upperClothes: ImageView = binding.sheet.findViewById(R.id.lower_clothes)
            val lowerClothes: ImageView = binding.sheet.findViewById(R.id.upper_clothes)
            val shoe: ImageView = binding.sheet.findViewById(R.id.shoe)*/
        /*  upperClothes.setImageDrawable(ContextCompat.getDrawable(this, imageDrawables[0].toInt()))
          lowerClothes.setImageDrawable(ContextCompat.getDrawable(this, imageDrawables[1].toInt()))
          shoe.setImageDrawable(ContextCompat.getDrawable(this, imageDrawables[2].toInt()))*/
    }

}