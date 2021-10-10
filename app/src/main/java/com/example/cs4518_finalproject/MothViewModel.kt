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
        Message(Color.rgb(233, 30, 99), R.string.love, R.string.love.toString()),
        Message(Color.rgb(139, 195, 74), R.string.peace, R.string.peace.toString()),
        Message(Color.rgb(3, 169, 244), R.string.support, R.string.support.toString()),
        Message(Color.rgb(255, 152, 0), R.string.hope, R.string.hope.toString())
    )

    var mothsDatabase = listOf(
        Moth("name", "password","location",
            Message(Color.rgb(255, 255, 255), R.string.message_question, "random")),
        Moth("name", "password","location",
            Message(Color.rgb(255, 255, 255), R.string.message_question, "random"))
    )

    fun saveMoth(moth: Moth) {
        Log.d(TAG, "saving moth $moth")
        mothRepository.updateMoth(moth)
    }

    fun addMoth(moth: Moth) {
        Log.d(TAG, "adding moth $moth")
        mothRepository.addMoth(moth)
    }

}