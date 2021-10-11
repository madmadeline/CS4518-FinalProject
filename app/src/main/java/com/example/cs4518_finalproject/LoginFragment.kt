package com.example.cs4518_finalproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Observer
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

    private val userIndex = 0
    private val otherUserIndex = 1


    private val mothViewModel: MothViewModel by lazy {
        ViewModelProviders.of(this).get(MothViewModel::class.java)
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
        mothViewModel.mothsDatabase[userIndex].username = user
        mothViewModel.mothsDatabase[userIndex].password = pass
//        loginViewModel.password = pass

        Log.d(TAG, "username set: " + mothViewModel.mothsDatabase[userIndex].username)
        Log.d(TAG, "password set: " + mothViewModel.mothsDatabase[userIndex].password)


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
            Log.d(TAG, "checking password...")
            mothViewModel.mothListLiveData.observe(
                viewLifecycleOwner,
                Observer { moths ->
                    moths?.let {
                        var moths = it
                        moths.forEach {
                            if (it.username.equals(loginViewModel.username)) {
                                if (it.password.equals(loginViewModel.password)) {
                                    Log.d(TAG, "correct password")
                                    // todo go to main activity screen
                                    return@forEach
                                } else {
                                    // todo warn user incorrect pass
                                    Log.d(TAG, "incorrect password")
                                    return@forEach
                                }
                            }
                        }
                }
            })
        }

        signUpButton.setOnClickListener { view: View ->
            Log.d(TAG, "signing up...")
            Log.d(TAG, "username: " + loginViewModel.username)
            Log.d(TAG, "password: " + loginViewModel.password)

            var userExists = false
            mothViewModel.mothListLiveData.observe(
                viewLifecycleOwner,
                Observer { moths ->
                    moths?.let {
                        var moths = it
                        moths.forEach {
                            if (it.username.equals(loginViewModel.username)) {
                                Log.d(TAG, "user already exists")
                                // todo warn user username already exists
                                userExists = true
                                return@forEach
                            }
                        }
                    }
                })
            if (!userExists) {
                Log.d(TAG, "adding user to database...")
                var newMoth = Moth(loginViewModel.username,
                    loginViewModel.password,
                    // todo get location
                    "random location",
                    Message(Color.rgb(255, 255, 255), R.string.message_question, "random"))
                mothViewModel.addMoth(newMoth)
            }
            // todo go to main activity screen
        }
        return view
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle){
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putString(USERNAME, mothViewModel.mothsDatabase[userIndex].username)
        savedInstanceState.putString(PASSWORD, mothViewModel.mothsDatabase[userIndex].password)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mothViewModel.mothListLiveData.observe(
            viewLifecycleOwner,
            Observer { moths ->
                moths?.let {
                    Log.i(TAG, "Got moths ${moths.size}")
                }
            })
    }
}