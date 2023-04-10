package com.example.clothessuggester.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.clothessuggester.R
import com.example.clothessuggester.databinding.ActivityMainBinding
import com.example.clothessuggester.presnter.MainPresenter
import com.example.clothessuggester.util.SetUpBottomSheet
import com.example.clothessuggester.util.UtilSharedPrefs

class MainActivity : AppCompatActivity(),IMainView{
     private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        UtilSharedPrefs.initPrefsUtil(this)
        SetUpBottomSheet.prepare(binding.sheet)
        runOnUiThread{
            showOnUi()
        }
        setContentView(binding.root)
    }


    private fun showOnUi() {

        val presenter=MainPresenter()
        presenter.itemperautre=this
        presenter.presentData()

    }

    override fun onSuccessWeatherTempurutre(tempruture: Double) {
           runOnUiThread {binding.textView.text = tempruture.toString()}
    }

    override fun onSuccessImageDrawable(imageDrawables: List<String>) {
        val upperClothes:ImageView= binding.sheet.findViewById(R.id.lower_clothes)
        upperClothes.setImageDrawable(ContextCompat.getDrawable(this,imageDrawables[0].toInt()))
        val lowerClothes:ImageView= binding.sheet.findViewById(R.id.upper_clothes)
        lowerClothes.setImageDrawable(ContextCompat.getDrawable(this,imageDrawables[1].toInt()))
        val shoe:ImageView= binding.sheet.findViewById(R.id.shoe)
        shoe.setImageDrawable(ContextCompat.getDrawable(this,imageDrawables[2].toInt()))
    }

}