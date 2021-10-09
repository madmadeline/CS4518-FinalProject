package com.example.cs4518_finalproject

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "LoginViewModel"

class LoginViewModel : ViewModel() {
    init {
        Log.d(TAG, "login viewmodel instance created.")
    }

    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "login viewmodel instance about to be destroyed.")
    }

    // todo replace this with database
    var username : String = ""
    var password : String = ""

}