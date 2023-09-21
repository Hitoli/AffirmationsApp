package com.example.myaffirmations.AffirmationsScreen.Repository

import com.example.myaffirmations.AffirmationsScreen.Response.AffirmationsResponse
import com.example.myaffirmations.AffirmationsScreen.Services.IGetRetrofitCalls
import javax.inject.Inject

interface  IGetAllAffirmationsResponseRepo{
    suspend fun getAllAffirmations():AffirmationsResponse

}
class AffirmationsRepository @Inject constructor(val Services:IGetRetrofitCalls):IGetAllAffirmationsResponseRepo {
    override suspend fun getAllAffirmations(): AffirmationsResponse {
        val response = Services.getAllAffirmations()
        return response
    }


}