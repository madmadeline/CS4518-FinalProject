package com.example.cs4518_finalproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "LoginFragment"
private const val USERNAME = "Username"
private const val PASSWORD = "Password"

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button


    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private var names = arrayOf("Annie", "Bjorn", "Choi", "Dmitriy", "Eiko", "Filio")
    private var passwords = arrayOf("secure", "password", "1234")

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_screen, container, false)

        /*
         UPDATE LOGIN INFORMATION
         */
//        val user = names[(0..5).random()]
//        val pass = passwords[(0..2).random()]
        val user = savedInstanceState?.getString(USERNAME, "") ?: ""
        val pass = savedInstanceState?.getString(PASSWORD, "") ?: ""
        loginViewModel.username = user
        loginViewModel.password = pass

        Log.d(TAG, "username set: " + loginViewModel.username)
        Log.d(TAG, "password set: " + loginViewModel.password)


        /*
         GET THE VIEWMODEL
         */
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val loginViewModel = provider.get(LoginViewModel::class.java)
        Log.d(TAG, "Got a LoginViewModel: $loginViewModel")

        /*
         WIRING UP WIDGETS
         */
        usernameEditText = view.findViewById(R.id.username_input)
        passwordEditText = view.findViewById(R.id.password_input)
        signInButton = view.findViewById(R.id.button_allow)
        signUpButton = view.findViewById(R.id.button_signUp)

        /*
         LISTENERS
         */
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // todo store this as string
                loginViewModel.username = s.toString()
//                Log.d(TAG, "username changed: " + s.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // todo store this as string
                loginViewModel.password = s.toString()
//                Log.d(TAG, "password changed: " + s.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        signInButton.setOnClickListener { view: View ->
            Log.d(TAG, "signing in...")
            Log.d(TAG, "username: " + loginViewModel.username)
            Log.d(TAG, "password: " + loginViewModel.password)
            // todo check if correct password
            // todo go to main activity screen
        }

        signUpButton.setOnClickListener { view: View ->
            Log.d(TAG, "signing up...")
            Log.d(TAG, "username: " + loginViewModel.username)
            Log.d(TAG, "password: " + loginViewModel.password)
            // todo add username and password to database
            // todo go to main activity screen
        }
        return view
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle){
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putString(USERNAME, loginViewModel.username)
        savedInstanceState.putString(PASSWORD, loginViewModel.password)
    }
}