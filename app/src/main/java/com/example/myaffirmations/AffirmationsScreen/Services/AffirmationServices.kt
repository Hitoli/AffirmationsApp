package com.example.myaffirmations.AffirmationsScreen.Services

import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse
import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponseItem
import retrofit2.http.GET

interface IGetRetrofitCalls{
    @GET("quotes")
    suspend fun getAllAffirmations(): AffirmationsResponse
}