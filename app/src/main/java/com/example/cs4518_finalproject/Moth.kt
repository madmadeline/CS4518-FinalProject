package com.example.cs4518_finalproject

import java.util.*
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_game")
data class Moth (@PrimaryKey val username: String = "",
                 var location: String = "",
                 var message: Message
                 ) {
    fun updateMessage(new_message: Message) {
        this.message = new_message
    }
}