package com.example.potmarketplace.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.potmarketplace.R
import com.example.potmarketplace.fragments.*
import com.example.potmarketplace.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref : SharedPreferences
    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var topAppBar: MaterialToolbar
    lateinit var searchView : SearchView

    lateinit var profileIcon: MenuItem
    lateinit var filterIcon: MenuItem
    lateinit var searchIcon: MenuItem

    lateinit var spinnerFilter: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getSharedPreferences(Constants.SHAREDPREF, Context.MODE_PRIVATE)

        val token = sharedPref.getString(Constants.TOKEN,"semmi")

        bottomNavigation = findViewById(R.id.bottom_navigation)


        initBottomNavigation()

    }

    fun replaceFragment(fragment: Fragment, containerId: Int, addToBackStack:Boolean = false, withAnimation:Boolean = false){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        when(addToBackStack){
            true -> {
                transaction.addToBackStack(null)
            }
        }
        transaction.commit()
    }

    private fun initBottomNavigation(){
        bottomNavigation.setOnItemSelectedListener {item ->
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2)
            when(item.itemId) {
                R.id.timeline -> {

                    if(fragment !is TimelineFragment)
                        replaceFragment(TimelineFragment(), R.id.fragmentContainerView2)
                    true
                }
                R.id.my_market -> {

                    if(fragment !is MyMarketFragment)
                        replaceFragment(MyMarketFragment(), R.id.fragmentContainerView2)
                    true
                }
                R.id.my_fares -> {

                    if(fragment !is MyFaresFragment)
                        replaceFragment(MyFaresFragment(), R.id.fragmentContainerView2)
                    true
                }
                else -> false
            }
        }
    }

}