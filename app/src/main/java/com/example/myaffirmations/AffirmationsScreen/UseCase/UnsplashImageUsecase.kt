package com.example.myaffirmations.AffirmationsScreen.UseCase

import com.example.myaffirmations.AffirmationsScreen.Repository.AffirmationsRepository
import com.example.myaffirmations.AffirmationsScreen.Repository.UnsplashRespository
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import javax.inject.Inject

interface IgetImagefromUnsplash{
    suspend fun invoke():UnsplashResponse
}
class UnsplashImageUsecase @Inject constructor(val repo: UnsplashRespository):IgetImagefromUnsplash {
    override suspend fun invoke(): UnsplashResponse {
        val responseIMG = repo.getAllUnsplashImages()
        return responseIMG
    }
}