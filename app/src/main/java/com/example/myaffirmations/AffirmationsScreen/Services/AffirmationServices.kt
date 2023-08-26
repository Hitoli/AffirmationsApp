package com.example.myaffirmations.AffirmationsScreen.Services

import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse
import retrofit2.http.GET

interface IGetRetrofitCalls{
    @GET("stoic-quote")
    suspend fun getAllAffirmations():AffirmationsResponse
}