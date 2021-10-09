package com.example.cs4518_finalproject

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "table_moth")
data class Moth (@StringRes var username: Int,
                 @StringRes var location: Int,
                 var message: Message
                 ) {

    fun updateMessage(new_message: Message) {
        this.message = new_message
    }
}