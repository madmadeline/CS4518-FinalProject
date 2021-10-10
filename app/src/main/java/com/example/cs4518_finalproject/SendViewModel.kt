package com.example.cs4518_finalproject

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "SendViewModel"

class SendViewModel : ViewModel() {
    init {
        Log.d(TAG, "send viewmodel instance created.")
    }

    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "sendViewModel instance about to be destroyed.")
    }
}