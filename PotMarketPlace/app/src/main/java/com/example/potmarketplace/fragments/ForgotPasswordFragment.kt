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
import android.widget.Toast
import com.example.potmarketplace.retrofit.UserAccessLayer
import com.example.potmarketplace.R
import com.example.potmarketplace.activities.MainActivity
import com.example.potmarketplace.retrofit.models.ChangePasswordModel
import com.example.potmarketplace.retrofit.models.LoginModel
import com.example.potmarketplace.retrofit.models.ResetModel
import com.example.potmarketplace.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : Fragment() {

    private lateinit var newPasswordField: EditText
    private lateinit var resetButton: Button
    private var disposable: Disposable?=null
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = requireContext().getSharedPreferences(Constants.SHAREDPREF, Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)


        newPasswordField = view.findViewById(R.id.new_password)
        resetButton = view.findViewById(R.id.btn_submit_fp)

        resetButton.isEnabled = false

        setListenersForTextInputs()

        setOnClickListenerForChangePasswordButton()


        return view
    }

    private fun setListenersForTextInputs() {




        newPasswordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                resetButton.isEnabled = !newPasswordField.text.isEmpty()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                resetButton.isEnabled = !newPasswordField.text.isEmpty()
            }
        })

    }

    private fun setOnClickListenerForChangePasswordButton(){
        resetButton.setOnClickListener{
            getChangePasswordObserver()
        }
    }

    private fun getChangePasswordObserver(){
        disposable = UserAccessLayer.getChangePasswordObservable(sharedPref.getString(Constants.TOKEN,"")!!,newPasswordField.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    sharedPref.edit().remove(Constants.PASSWORD).apply()
                    sharedPref.edit().putString(Constants.PASSWORD,newPasswordField.text.toString()).apply()
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