package com.example.cs4518_finalproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cs4518_finalproject.Moth

@Database(entities = [ Moth::class ], version=1)
abstract class MothDatabase : RoomDatabase() {
    abstract fun mothDAO(): MothDAO
}