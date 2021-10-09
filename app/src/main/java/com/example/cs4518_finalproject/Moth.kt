package com.example.cs4518_finalproject

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moth (@PrimaryKey @StringRes var username: Int,
                 @StringRes var password: Int,
                 @StringRes var location: Int,
                 var message: Message
                 ) {

    fun updateMessage(new_message: Message) {
        this.message = new_message
    }
}