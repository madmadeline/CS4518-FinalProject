package com.example.cs4518_finalproject.api

import com.example.cs4518_finalproject.InspirationResponse
import retrofit2.Call
import retrofit2.http.GET

interface InspirationAPI {
    @GET("/")
    fun fetchQuote(): Call<InspirationResponse>
}