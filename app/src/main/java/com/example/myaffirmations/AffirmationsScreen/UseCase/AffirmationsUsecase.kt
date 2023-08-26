package com.example.myaffirmations.AffirmationsScreen.UseCase

import androidx.compose.runtime.rememberCoroutineScope
import com.example.myaffirmations.AffirmationsScreen.Repository.AffirmationsRepository
import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse
import javax.inject.Inject

interface IGetAllAffirmationsUsecase{
    suspend fun invoke():AffirmationsResponse
}

class AffirmationsUsecase @Inject constructor(val repo:AffirmationsRepository):IGetAllAffirmationsUsecase {
    override suspend fun invoke(): AffirmationsResponse {
        val response = repo.getAllAffirmations()
        return response
    }
}