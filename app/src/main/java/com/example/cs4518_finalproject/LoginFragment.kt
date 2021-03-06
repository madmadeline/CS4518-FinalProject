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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cs4518_finalproject.api.InspirationAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "LoginFragment"
private const val USERNAME = "Username"
private const val PASSWORD = "Password"
private const val USER_COUNTRY = "Narnia"

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button

    private lateinit var quoteTextView: TextView

    private val userIndex = 0
    private val otherUserIndex = 1


    private val mothViewModel: MothViewModel by lazy {
        ViewModelProviders.of(this).get(MothViewModel::class.java)
    }

    private var names = arrayOf("Annie", "Bjorn", "Choi", "Dmitriy", "Eiko", "Filio")
    private var passwords = arrayOf("secure", "password", "1234")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inspirationLiveData: LiveData<Quote> = InspirationFetchr().fetchQuote()
        inspirationLiveData.observe(
            this,
            Observer { quote ->
                Log.d(TAG, "Response received: $quote")
                var quoteText = "\"" + quote.quote + "\" – " + quote.author
                quoteTextView.setText(quoteText)
            })
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_screen, container, false)
        var location = ""
        if (getArguments() != null) {
            location = getArguments()?.getString("location").toString()
        }
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
        quoteTextView = view.findViewById(R.id.quoteTextView)

        /*
         LISTENERS
         */
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                loginViewModel.username = s.toString()
//                Log.d(TAG, "username changed: " + s.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
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
                                    // go to main activity screen
                                    var fr = getFragmentManager()?.beginTransaction()
                                    fr?.replace(R.id.fragment_container, MothFragment())
                                    fr?.commit()
                                    return@forEach
                                } else {
                                    // warn user incorrect pass
                                    Log.d(TAG, "incorrect password")
                                    Toast.makeText(activity?.applicationContext,
                                        "Incorrect Password", Toast.LENGTH_LONG).show()
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
                                // warn user username already exists
                                Toast.makeText(activity?.applicationContext,
                                    "Username already exists!", Toast.LENGTH_LONG).show()
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
                    location,
                    Message(Color.rgb(255, 255, 255), R.string.message_question, "random"))
                mothViewModel.addMoth(newMoth)

                // go to main activity screen
                var fr = getFragmentManager()?.beginTransaction()
                fr?.replace(R.id.fragment_container, MothFragment())
                fr?.commit()
            }
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