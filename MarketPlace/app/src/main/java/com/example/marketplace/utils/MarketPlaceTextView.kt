package com.example.marketplace.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.marketplace.R
import java.util.jar.Attributes

class MarketPlaceTextView(context: Context, attributeSet: AttributeSet):AppCompatTextView(context,attributeSet) {
    init {
        applyFont()
    }

    private fun applyFont() {
        //fajlt kap az assets-bol es textViewba helyezzuk
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"FtyStrategycideNcv-elGl.ttf")
        setTypeface(typeface)
    }
}