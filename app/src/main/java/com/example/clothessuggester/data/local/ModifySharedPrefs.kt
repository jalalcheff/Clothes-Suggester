package com.example.clothessuggester.data.local

import com.example.clothessuggester.util.UtilSharedPrefs

class ModifySharedPrefs {
    private fun loadData():String {
        return UtilSharedPrefs.user.toString()
    }

    private fun saveData(data:String) {
        UtilSharedPrefs.user=data
    }
}