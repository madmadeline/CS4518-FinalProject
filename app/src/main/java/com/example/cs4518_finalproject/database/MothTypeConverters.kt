package com.example.cs4518_finalproject.database

import android.graphics.Color
import androidx.room.TypeConverter
import com.example.cs4518_finalproject.Message
import com.example.cs4518_finalproject.R

class MothTypeConverters {
    @TypeConverter
    fun toMessage(message: String?): Message? {
        val messageObject = when (message) {
            "Love" -> Message(Color.rgb(233, 30, 99), R.string.love, R.string.love.toString())
            "Peace" -> Message(Color.rgb(139, 195, 74), R.string.peace, R.string.peace.toString())
            "Support" -> Message(Color.rgb(3, 169, 244), R.string.support, R.string.support.toString())
            "Hope" -> Message(Color.rgb(255, 152, 0), R.string.hope, R.string.hope.toString())
            else -> Message(Color.rgb(255, 255, 255), R.string.message_question, "random")
        }
        return messageObject
    }

    @TypeConverter
    fun fromMessage(message: Message?): String? {
        return message?.textString
    }
}