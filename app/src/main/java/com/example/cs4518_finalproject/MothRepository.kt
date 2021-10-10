package com.example.cs4518_finalproject

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.cs4518_finalproject.database.MothDatabase
import java.util.concurrent.Executors

private const val DATABASE_NAME = "moth-database"
private const val TAG = "GameRepo"

class MothRepository private constructor(context: Context) {
    private val database : MothDatabase = Room.databaseBuilder(
        context.applicationContext,
        MothDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val mothDAO = database.mothDAO()
    private val executor = Executors.newSingleThreadExecutor()

    fun getMoths(): LiveData<List<Moth>> = mothDAO.getMoths()
    fun getMoth(username: String): LiveData<Moth?> = mothDAO.getMoth(username)

    fun updateMoth(moth: Moth) {
        executor.execute {
            mothDAO.updateMoth(moth)
        }
    }
    fun addMoth(moth: Moth) {
        val existingMoth = getMoth(moth.username)
        Log.d(TAG, "exists " + existingMoth.value)

        if (existingMoth.value == null) {
            executor.execute {
                mothDAO.addMoth(moth)
            }
        }
    }

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