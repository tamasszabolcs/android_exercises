package com.example.myapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.Models.SharedDatas
import com.example.myapp.R





/**
 * A simple [Fragment] subclass.
 * Use the [QuestionAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionAddFragment : Fragment() {
    private lateinit var viewModel : SharedDatas
    private lateinit var questionText  : EditText
    private lateinit var correctAnsw : EditText
    private lateinit var answ2 : EditText
    private lateinit var answ3 : EditText
    private lateinit var answ4 : EditText
    private lateinit var addBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_new_question, container, false)
        view.apply {
            initializeView(this)
            add_question(this)

        }
        return view
    }



    private fun initializeView(view: View){
        questionText = view.findViewById(R.id.new_question)
        correctAnsw = view.findViewById(R.id.answ_1)
        answ2 = view.findViewById(R.id.answ_2)
        answ3 = view.findViewById(R.id.answ_3)
        answ4 = view.findViewById(R.id.answ_4)
        addBtn = view.findViewById(R.id.button_submit)
    }


    private fun add_question(view:View){
        addBtn.setOnClickListener {
            var answers = mutableListOf<String>()
            viewModel = ViewModelProvider(requireActivity()).get(SharedDatas::class.java)
            answers.add(correctAnsw.text.toString())
            answers.add(answ2.text.toString())
            answers.add(answ3.text.toString())
            answers.add(answ4.text.toString())

        }
    }

}