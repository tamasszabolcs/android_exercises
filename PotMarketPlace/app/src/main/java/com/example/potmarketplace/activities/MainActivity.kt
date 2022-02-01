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
import com.example.potmarketplace.fragments.LoginFragment
import com.example.potmarketplace.fragments.MyMarketFragment
import com.example.potmarketplace.fragments.ProfileFragment
import com.example.potmarketplace.fragments.TimelineFragment
import com.example.potmarketplace.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref : SharedPreferences
    lateinit var bottomNavigation: BottomNavigationView
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

        //Toast.makeText(this,token, Toast.LENGTH_LONG).show()



    }
//    fun replaceFragment(fragment: Fragment, containerId: Int, addToBackStack:Boolean = false, withAnimation:Boolean = false){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(containerId, fragment)
//        when(addToBackStack){
//            true -> {
//                transaction.addToBackStack(null)
//            }
//        }
//        transaction.commit()
//    }
//    private fun initBottomNavigation(){
//        bottomNavigation.setOnItemSelectedListener {item ->
//            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//            when(item.itemId) {
//                R.id.timeline -> {
//
//                    if(fragment !is TimelineFragment)
//                        replaceFragment(TimelineFragment(), R.id.fragment_container)
//                    true
//                }
//                R.id.my_market -> {
//
//                    if(fragment !is MyMarketFragment)
//                        replaceFragment(MyMarketFragment(), R.id.fragment_container)
//                    true
//                }
////                R.id.my_fares -> {
////
////                    if(fragment !is MyFaresFragment)
////                        replaceFragment(MyFaresFragment(), R.id.fragment_container)
////                    true
////                }
//                else -> false
//            }
//        }
//    }
//
//    private fun initTopBar(){
//        profileIcon.setOnMenuItemClickListener {
//            replaceFragment(ProfileFragment(), R.id.fragment_container, true)
//            true
//        }
//        filterIcon.setOnMenuItemClickListener {
//            //TODO
//            true
//        }
//        searchIcon.setOnMenuItemClickListener {
//            //TODO
//            true
//        }
//    }
}