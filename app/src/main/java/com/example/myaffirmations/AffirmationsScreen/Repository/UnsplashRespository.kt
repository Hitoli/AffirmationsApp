package com.example.myaffirmations.AffirmationsScreen.Repository

import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.Services.IGetUnplashRetrofitCalls
import javax.inject.Inject

interface IgetUnsplashRepository{
    suspend fun getAllUnsplashImages(): UnsplashResponse
}
class UnsplashRespository @Inject constructor(val ServicesImg: IGetUnplashRetrofitCalls):IgetUnsplashRepository {
    override suspend fun getAllUnsplashImages(): UnsplashResponse {
        val responseImg = ServicesImg.getAllPhotos("50")
        return responseImg
    }
}