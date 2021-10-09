package com.example.cs4518_finalproject

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "MothViewModel"

class MothViewModel : ViewModel() {
    private val mothRepository = MothRepository.get()
    val mothListLiveData = mothRepository.getMoths()

//    init {
//        Log.d(TAG, "moth viewmodel instance created.")
//    }




    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed.")
    }

    var messagesDatabase = listOf(
        Message(Color.rgb(233, 30, 99), R.string.love),
        Message(Color.rgb(139, 195, 74), R.string.peace),
        Message(Color.rgb(3, 169, 244), R.string.support),
        Message(Color.rgb(255, 152, 0), R.string.hope)
    )

    var mothsDatabase = listOf(
        Moth(R.string.username, R.string.sign_in_passw, R.string.user_location,
            Message(Color.rgb(255, 255, 255), R.string.message_question)),
        Moth(R.string.other_username, R.string.sign_in_passw, R.string.other_location,
            Message(Color.rgb(255, 255, 255), R.string.message_question))
    )

}