package com.example.cs4518_finalproject

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moths")
data class Moth (@PrimaryKey var username: String,
                 var password: String,
                 var location: String,
                 var message: Message
                 ) {

    fun updateMessage(new_message: Message) {
        this.message = new_message
    }
}