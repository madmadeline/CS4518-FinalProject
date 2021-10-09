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
        Message("#E91E63", R.string.love),
        Message("#8BC34A️", R.string.peace),
        Message("#03A9F4", R.string.support),
        Message("#FF9800", R.string.hope)
    )

    var mothsDatabase = listOf(
        Moth(R.string.username, R.string.user_location,
            Message("#FFFFFF", R.string.message_question)),
        Moth(R.string.other_username, R.string.other_location,
            Message("#FFFFFF", R.string.message_question))
    )

}