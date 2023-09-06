package com.example.myaffirmations.AffirmationsScreen.Repository

import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.Services.IGetRetrofitCalls
import com.example.myaffirmations.AffirmationsScreen.Services.IGetUnplashRetrofitCalls
import javax.inject.Inject

interface  IGetAllAffirmationsResponseRepo{
    suspend fun getAllAffirmations():AffirmationsResponse
    suspend fun getAllUnsplashImages():UnsplashResponse
}
class AffirmationsRepository @Inject constructor(val Services:IGetRetrofitCalls, val ServicesImg:IGetUnplashRetrofitCalls):IGetAllAffirmationsResponseRepo {
    override suspend fun getAllAffirmations(): AffirmationsResponse {
        val response = Services.getAllAffirmations()
        return response
    }

    override suspend fun getAllUnsplashImages(): UnsplashResponse {
        val responseImg = ServicesImg.getAllPhotos("Stoic")
        return responseImg
    }
}