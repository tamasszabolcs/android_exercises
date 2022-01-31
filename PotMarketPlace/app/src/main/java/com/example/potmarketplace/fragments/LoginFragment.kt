package com.example.potmarketplace.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.R
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*


class LoginFragment : Fragment() {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var noAccountTextView: TextView
    private var loginDisposable: Disposable? = null
    private lateinit var sharedPref: SharedPreferences
    private lateinit var resetTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireContext().getSharedPreferences(Constants.SHAREDPREF,Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        emailField = view.findViewById(R.id.et_email)
        passwordField = view.findViewById(R.id.et_password)
        loginButton = view.findViewById(R.id.btn_login)
        noAccountTextView = view.findViewById(R.id.tv_register_now)
        resetTextView = view.findViewById(R.id.tv_forgot_password)
        loginButton.isEnabled = false



        setListenersForTextInputs()

        setOnClickListenerForLoginButton()

        setListenerForNoAccountTextView()

        resetTextView.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ForgotPasswordFragment()).addToBackStack(null).commit()
        }

        return view


    }

    private fun setListenerForNoAccountTextView(){
        noAccountTextView.setOnClickListener{
            //click eseten dobjon at a register oldalra
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,RegisterFragment()).addToBackStack(null).commit()
        }
    }

    private fun setListenersForTextInputs() {


        emailField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                loginButton.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                loginButton.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty()
            }
        })

        passwordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                loginButton.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                loginButton.isEnabled = !emailField.text.isEmpty() && !passwordField.text.isEmpty()
            }
        })

    }

    private fun setOnClickListenerForLoginButton(){
        loginButton.setOnClickListener{
            getLoginObserver()
        }
    }

    private fun getLoginObserver(){
        loginDisposable = UserAccessLayer.getLoginObservable(LoginModel(emailField.text.toString(), passwordField.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    val edit = sharedPref.edit()
                    edit.putString(Constants.TOKEN, it.token).apply()
                    edit.putString(Constants.EMAIL, it.email).apply()
                    edit.putString(Constants.PHONENUMBER, it.phoneNumber).apply()
                    edit.putString(Constants.USERNAME, emailField.text.toString()).apply()
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()
                },
                {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
            )

    }

    override fun onDestroyView() {
        if (loginDisposable != null) {
            if (!loginDisposable!!.isDisposed)
                loginDisposable!!.dispose()
        }
        super.onDestroyView()
    }

}