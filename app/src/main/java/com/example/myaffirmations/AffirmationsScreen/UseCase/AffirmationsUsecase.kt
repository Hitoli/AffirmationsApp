package com.example.myaffirmations.AffirmationsScreen.UseCase

import com.example.myaffirmations.AffirmationsScreen.Repository.AffirmationsRepository
import javax.inject.Inject
import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse


interface IGetAllAffirmationsUsecase{
    suspend fun invoke():AffirmationsResponse
}

class AffirmationsUsecase @Inject constructor(val repo:AffirmationsRepository):IGetAllAffirmationsUsecase {
    override suspend fun invoke(): AffirmationsResponse {
        val response = repo.getAllAffirmations()
        return response
    }


}