package com.example.potmarketplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.potmarketplace.R

class DetailsFragment : Fragment() {

    private lateinit var titleTextVIew: TextView
    private lateinit var descriptionTextVIew: TextView
    private lateinit var priceTextVIew: TextView
    private lateinit var amountTextVIew: TextView
    private lateinit var unitsTextVIew: TextView
    private lateinit var priceUnitsTextVIew: TextView
    private lateinit var ratingTextVIew: TextView
    private lateinit var isActiveTextVIew: TextView
    private lateinit var ownerTextVIew: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_details, container, false)
        titleTextVIew = view.findViewById(R.id.product_name_text_view)
        descriptionTextVIew = view.findViewById(R.id.product_description_text_view)
        unitsTextVIew = view.findViewById(R.id.total_items_circle_text_view)
        priceUnitsTextVIew = view.findViewById(R.id.price_item_circle_text_view)
        ratingTextVIew = view.findViewById(R.id.rating_circle_text_view)
        isActiveTextVIew = view.findViewById(R.id.active_inactive_text_view)
        ownerTextVIew = view.findViewById(R.id.owner_name_text_view)


        titleTextVIew.text = arguments!!.getString("title")
        descriptionTextVIew.text = arguments!!.getString("description")
        unitsTextVIew.text = arguments!!.getString("units")
        priceUnitsTextVIew.text = arguments!!.getString("price_per_unit")
        ratingTextVIew.text = arguments!!.getString("rating")
        isActiveTextVIew.text = arguments!!.getString("is_active")
        ownerTextVIew.text = arguments!!.getString("owner_name")

        activity?.findViewById<SearchView>(R.id.search_app_bar)?.isVisible = false

        return view
    }

}