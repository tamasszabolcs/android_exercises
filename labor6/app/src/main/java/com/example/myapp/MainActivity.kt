package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapp.Models.SharedDatas
import com.example.myapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val listOfQuestionsFragment = ListOfQuestionsFragment()
    private val newQuestionFragment = NewQuestionFragment()
    private val profileFragment = ProfileFragment()
    private val quizTimeFragment = QuizTimeFragment()

    val viewModel = SharedDatas()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        nav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_list -> replaceFragment(listOfQuestionsFragment)
                R.id.ic_new -> replaceFragment(newQuestionFragment)
                R.id.ic_profile -> replaceFragment(profileFragment)
                R.id.ic_quiz -> replaceFragment(quizTimeFragment)
            }

        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!= null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_cont,fragment)
            transaction.commit()
        }
    }
}