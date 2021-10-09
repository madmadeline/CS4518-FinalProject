package com.example.cs4518_finalproject

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "MothViewModel"

class MothViewModel : ViewModel() {
    val myIndex = 0
    val otherIndex = 1

    init {
        Log.d(TAG, "ViewModel instance created.")
    }

    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed.")
    }

    var messagesDatabase = listOf(
        Message("\uD83D\uDC96", "LOVE"),
        Message("\uD83D\uDD4A️", "PEACE"),
        Message("\uD83D\uDC4D", "SUPPORT"),
        Message("\uD83E\uDD17", "HUGS")

    )

    var mothsDatabase = listOf(
        Moth(R.string.username, R.string.user_location, messagesDatabase[0]),
        Moth(R.string.other_username, R.string.other_location, messagesDatabase[0])
    )

}