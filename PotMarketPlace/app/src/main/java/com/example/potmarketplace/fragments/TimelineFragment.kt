package com.example.potmarketplace.fragments

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.retrofit.UserAccessLayer
import com.example.potmarketplace.R
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.adapters.ProductsAdapter
import com.example.potmarketplace.models.Product
import com.example.potmarketplace.retrofit.ProductAccessLayer
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class TimelineFragment : Fragment() {

    private lateinit var profileTextView : TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var productsDisposable: Disposable
    private lateinit var searchView: SearchView
    private lateinit var productAdapter: ProductsAdapter
    private var currentList = mutableListOf<Product>()
    private lateinit var fullList: MutableList<Product>

    private lateinit var myMarketItem: ClipData.Item
    private lateinit var bottomNavigation: BottomNavigationView

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

        searchView = view.findViewById(R.id.search_app_bar)

        bottomNavigation = view.findViewById(R.id.bottom_navigation)


        profileTextView.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,ProfileFragment()).addToBackStack(null).commit()
        }



        getProductsObserver()

        updateList()




        return view
    }

    private fun updateList(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                currentList = fullList.filter {
                    it.title.contains(newText,true)
                }.toMutableList()
                productAdapter.productsList = currentList
                productAdapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }

        })
    }




    private fun getProductsObserver(){
        productsDisposable = ProductAccessLayer.getProductsObservable(sharedPref.getString(Constants.TOKEN,"")!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    productAdapter = ProductsAdapter(it)
                    recyclerView.adapter = productAdapter
                    fullList = it
                    currentList.addAll(it)
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