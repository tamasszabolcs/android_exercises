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
import androidx.core.text.isDigitsOnly
import com.example.marketplace.retrofit.UserAccessLayer
import com.example.potmarketplace.R
import com.example.potmarketplace.activities.LoginActivity
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.retrofit.models.UpdateProfileModel
import com.example.potmarketplace.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileFragment : Fragment() {
    private lateinit var emailField: EditText
    private lateinit var userNameField: EditText
    private lateinit var phoneNumberField: EditText

    private lateinit var saveButton: Button
    private lateinit var logoutButton: Button

    private var disposable: Disposable? = null
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        emailField = view.findViewById(R.id.et_email_profile)
        userNameField = view.findViewById(R.id.et_username_profile)
        phoneNumberField = view.findViewById(R.id.et_mobile_number_profile)

        saveButton = view.findViewById(R.id.btn_save_profile)
        logoutButton = view.findViewById(R.id.btn_logout_profile)

        emailField.setText(sharedPref.getString(Constants.EMAIL,"No email"))
        userNameField.setText(sharedPref.getString(Constants.USERNAME,"No username"))

        emailField.isEnabled = false
        userNameField.isEnabled = false

        saveButton.setOnClickListener{
            getUpdateProfileObserver()
        }

        logoutButton.setOnClickListener{
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }

        saveButton.isEnabled = false
        phoneNumberField.setText(sharedPref.getString(Constants.PHONENUMBER,"No phone number"))

        phoneNumberField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                saveButton.isEnabled = phoneNumberField.text.isDigitsOnly() && phoneNumberField.text.isNotEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                saveButton.isEnabled = phoneNumberField.text.isDigitsOnly() && phoneNumberField.text.isNotEmpty()
            }
        })

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = requireContext().getSharedPreferences(Constants.SHAREDPREF, Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
    }

    private fun getUpdateProfileObserver(){
        disposable = UserAccessLayer.getUpdateProfileObservable(sharedPref.getString(Constants.TOKEN,"").toString(),
            UpdateProfileModel(userNameField.text.toString(), emailField.text.toString(), phoneNumberField.text.toString())
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    val edit = sharedPref.edit()
                    edit.putString(Constants.PHONENUMBER, phoneNumberField.text.toString()).apply()
                    val fragmentManager = requireActivity().supportFragmentManager
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,ProfileFragment()).commit()
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