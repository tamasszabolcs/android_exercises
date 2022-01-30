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
import android.widget.Toast
import com.example.marketplace.retrofit.UserAccessLayer
import com.example.potmarketplace.R
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.retrofit.models.ResetModel
import com.example.potmarketplace.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ForgotPasswordFragment : Fragment() {

    private lateinit var userNameField: EditText
    private lateinit var emailField: EditText
    private lateinit var resetButton: Button
    private var disposable: Disposable?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        userNameField = view.findViewById(R.id.et_username_fp)
        emailField = view.findViewById(R.id.et_email_fp)
        resetButton = view.findViewById(R.id.btn_submit_fp)

        resetButton.isEnabled = false

        setListenersForTextInputs()

        setOnClickListenerForChangePasswordButton()


        return view
    }

    private fun setListenersForTextInputs() {


        emailField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                resetButton.isEnabled = !emailField.text.isEmpty() && !userNameField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                resetButton.isEnabled = !emailField.text.isEmpty() && !userNameField.text.isEmpty()
            }
        })

        userNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                resetButton.isEnabled = !emailField.text.isEmpty() && !userNameField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                resetButton.isEnabled = !emailField.text.isEmpty() && !userNameField.text.isEmpty()
            }
        })

    }

    private fun setOnClickListenerForChangePasswordButton(){
        resetButton.setOnClickListener{
            getChangePasswordObserver()
        }
    }

    private fun getChangePasswordObserver(){
        disposable = UserAccessLayer.getResetObservable(ResetModel(userNameField.text.toString(), emailField.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Toast.makeText(requireContext(),"Email was sent successfully!", Toast.LENGTH_LONG).show()
                    val fragmentManager = requireActivity().supportFragmentManager
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,LoginFragment()).addToBackStack(null).commit()
                },
                {
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_LONG).show()
                }
            )

    }

    override fun onDestroyView() {
        if (disposable != null) {
            if (!disposable!!.isDisposed)
                disposable!!.dispose()
        }
        super.onDestroyView()
    }
}