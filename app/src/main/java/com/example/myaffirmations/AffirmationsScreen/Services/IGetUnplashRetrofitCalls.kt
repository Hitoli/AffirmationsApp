package com.example.myaffirmations.AffirmationsScreen.Services

import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IGetUnplashRetrofitCalls {
    @Headers("Authorization: Client-ID IGd8zMa606y3CftqdkQLo4PC7_qPyrV18tK_a8Rva9Q")
    @GET("search/photos")
    suspend fun getAllPhotos(@Query("query")query: String):UnsplashResponse
}