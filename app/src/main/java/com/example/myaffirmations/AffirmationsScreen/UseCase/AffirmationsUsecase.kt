package com.example.myaffirmations.AffirmationsScreen.UseCase

import com.example.myaffirmations.AffirmationsScreen.Repository.AffirmationsRepository
import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import javax.inject.Inject

interface IGetAllAffirmationsUsecase{
    suspend fun invoke():AffirmationsResponse
    suspend fun invokeimg():UnsplashResponse
}

class AffirmationsUsecase @Inject constructor(val repo:AffirmationsRepository):IGetAllAffirmationsUsecase {
    override suspend fun invoke(): AffirmationsResponse {
        val response = repo.getAllAffirmations()
        return response
    }

    override suspend fun invokeimg(): UnsplashResponse {
        val responseIMG = repo.getAllUnsplashImages()
        return responseIMG
    }
}