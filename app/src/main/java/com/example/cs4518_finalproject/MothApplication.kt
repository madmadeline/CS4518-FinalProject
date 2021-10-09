package com.example.cs4518_finalproject

import android.app.Application

class MothApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MothRepository.initialize(this)
    }
}