package com.example.clothessuggester.util

import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior

object SetUpBottomSheet {
    fun prepare(sheet: LinearLayoutCompat)
    {
        val bottomSheetBehavior = BottomSheetBehavior.from(sheet)
        bottomSheetBehavior.peekHeight = 220
    }
}