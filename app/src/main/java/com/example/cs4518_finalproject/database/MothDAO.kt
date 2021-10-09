package com.example.cs4518_finalproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.cs4518_finalproject.Moth

@Dao
interface MothDAO {
    @Query("SELECT * FROM moth")
    fun getMoths(): LiveData<List<Moth>>

    @Query("SELECT * FROM moth WHERE username=(:username)")
    fun getMoth(username: Int): LiveData<Moth?>
}