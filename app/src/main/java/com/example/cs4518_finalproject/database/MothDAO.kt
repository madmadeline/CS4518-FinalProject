package com.example.cs4518_finalproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cs4518_finalproject.Moth

@Dao
interface MothDAO {
    @Query("SELECT * FROM moths")
    fun getMoths(): LiveData<List<Moth>>

    @Query("SELECT * FROM moths WHERE username=(:username)")
    fun getMoth(username: String): LiveData<Moth?>

    @Update
    fun updateMoth(moth: Moth)

    @Insert
    fun addMoth(moth: Moth)
}