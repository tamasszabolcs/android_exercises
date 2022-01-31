package com.example.potmarketplace.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.retrofit.UserAccessLayer
import com.example.potmarketplace.R
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.adapters.ProductsAdapter
import com.example.potmarketplace.retrofit.ProductAccessLayer
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TimelineFragment : Fragment() {

    private lateinit var profileTextView : TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var productsDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireContext().getSharedPreferences(Constants.SHAREDPREF, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_timeline, container, false)

        profileTextView = view.findViewById(R.id.profile_textview)

        recyclerView = view.findViewById(R.id.recycler_view)

        profileTextView.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,ProfileFragment()).addToBackStack(null).commit()
        }

        getProductsObserver()

        return view
    }

    private fun getProductsObserver(){
        productsDisposable = ProductAccessLayer.getProductsObservable(sharedPref.getString(Constants.TOKEN,"")!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recyclerView.adapter = ProductsAdapter(it)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                },
                {
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_LONG).show()
                }
            )

    }

    override fun onDestroyView() {

        if (!productsDisposable!!.isDisposed)
            productsDisposable!!.dispose()

        super.onDestroyView()
    }

}