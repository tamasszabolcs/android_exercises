package com.example.marketplace.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class MarketPlaceButton(context: Context,attributeSet: AttributeSet):AppCompatButton(context,attributeSet) {
    init {
        applyFont()
    }

    private fun applyFont() {
        //fajlt kap az assets-bol es Buttonba helyezzuk
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"FtyStrategycideNcv-elGl.ttf")
        setTypeface(typeface)
    }
}