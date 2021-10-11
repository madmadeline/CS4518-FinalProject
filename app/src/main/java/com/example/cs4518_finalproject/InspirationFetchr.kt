package com.example.cs4518_finalproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cs4518_finalproject.api.InspirationAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "InspirationFetchr"
class InspirationFetchr {
    private val inspirationAPI: InspirationAPI
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://inspiration.goprogram.ai")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        inspirationAPI = retrofit.create(InspirationAPI::class.java)
    }

    fun fetchQuote(): LiveData<Quote> {
        val responseLiveData: MutableLiveData<Quote> = MutableLiveData()
        val inspirationRequest: Call<InspirationResponse> = inspirationAPI.fetchQuote()
        inspirationRequest.enqueue(object : Callback<InspirationResponse> {
            override fun onFailure(call: Call<InspirationResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch quote", t)
            }
            override fun onResponse(
                call: Call<InspirationResponse>,
                response: Response<InspirationResponse>
            ) {
                Log.d(TAG, "Response received")
                val inspirationResponse: InspirationResponse? = response.body()
                val text : String? = inspirationResponse?.quote
                val author: String? = inspirationResponse?.author
                var quote : Quote? = Quote(text, author)
                responseLiveData.value = quote
            }
        })
        return responseLiveData
    }
}