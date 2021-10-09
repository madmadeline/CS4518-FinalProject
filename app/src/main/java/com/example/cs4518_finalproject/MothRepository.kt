package com.example.cs4518_finalproject

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.cs4518_finalproject.database.MothDatabase

private const val DATABASE_NAME = "moth-database"

class MothRepository private constructor(context: Context) {
    private val database : MothDatabase = Room.databaseBuilder(
        context.applicationContext,
        MothDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val mothDAO = database.mothDAO()

    fun getMoths(): LiveData<List<Moth>> = mothDAO.getMoths()
    fun getMoth(username: Int): LiveData<Moth?> = mothDAO.getMoth(username)

    companion object {
        private var INSTANCE: MothRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MothRepository(context)
            }
        }
        fun get(): MothRepository {
            return INSTANCE ?:
            throw IllegalStateException("MothRepository must be initialized")
        }
    }
}