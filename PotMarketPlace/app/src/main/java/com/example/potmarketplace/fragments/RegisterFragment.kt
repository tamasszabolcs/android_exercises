package com.example.potmarketplace.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.marketplace.retrofit.UserAccessLayer
import com.example.marketplace.retrofit.models.RegisterModel
import com.example.potmarketplace.R
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterFragment : Fragment() {

    private lateinit var userNameField : EditText
    private lateinit var emailField : EditText
    private lateinit var passwordField : EditText
    private lateinit var buttonRegister: Button

    private var registerDisposable: Disposable?=null
    private lateinit var backToLogin: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_register, container, false)
        userNameField = view.findViewById(R.id.et_username)
        emailField = view.findViewById(R.id.et_email_register)
        passwordField = view.findViewById(R.id.et_password_register)
        buttonRegister = view.findViewById(R.id.btn_register)
        backToLogin = view.findViewById(R.id.tv_login_now)

        buttonRegister.isEnabled = false

        setOnClickListenerForRegisterButton()
        setListenersForTextInputs()
        setListenerForBackToLoginTextView()

        return view
    }

    private fun setOnClickListenerForRegisterButton(){
        buttonRegister.setOnClickListener{
            getRegisterObserver()
        }
    }

    private fun getRegisterObserver(){
        registerDisposable = UserAccessLayer.getRegisterObservable(RegisterModel(userNameField.text.toString(), emailField.text.toString(),passwordField.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Toast.makeText(requireContext(),"Successful user registration", Toast.LENGTH_LONG).show()
                },
                {
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_LONG).show()
                }
            )

    }

    private fun setListenerForBackToLoginTextView(){
        backToLogin.setOnClickListener{
            //click eseten dobjon at a register oldalra
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,LoginFragment()).addToBackStack(null).commit()
        }
    }

    private fun setListenersForTextInputs() {


        emailField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                buttonRegister.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty() && !userNameField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                buttonRegister.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty() && !userNameField.text.isEmpty()
            }
        })

        passwordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                buttonRegister.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty() && !userNameField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                buttonRegister.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty() && !userNameField.text.isEmpty()
            }
        })

        userNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                buttonRegister.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty() && !userNameField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                buttonRegister.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty() && !userNameField.text.isEmpty()
            }
        })

    }

    override fun onDestroyView() {
        if (registerDisposable != null) {
            if (!registerDisposable!!.isDisposed)
                registerDisposable!!.dispose()
        }
        super.onDestroyView()
    }

}