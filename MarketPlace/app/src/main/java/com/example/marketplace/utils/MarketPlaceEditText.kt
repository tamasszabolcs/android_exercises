package com.example.marketplace.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MarketPlaceEditText(context: Context, attributeSet: AttributeSet):AppCompatEditText(context,attributeSet) {
    init {
        applyFont()
    }

    private fun applyFont() {
        //fajlt kap az assets-bol es editTextbe helyezzuk
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"FtyStrategycideNcv-elGl.ttf")
        setTypeface(typeface)
    }
}