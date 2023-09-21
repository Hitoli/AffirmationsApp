package com.example.myaffirmations.AffirmationsScreen.ViewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaffirmations.AffirmationsScreen.Response.Links
import com.example.myaffirmations.AffirmationsScreen.Response.Result
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.UseCase.AffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.UseCase.UnsplashImageUsecase
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AffimationsViewModel @Inject constructor(val Usecase:AffirmationsUsecase, val UnsplashImageUsecase:UnsplashImageUsecase,val notificationBuilder:NotificationCompat.Builder,
                                               val notificationManager:NotificationManagerCompat, val alarmSchedule: AlarmScheduler,
                                               @ApplicationContext context: Context
):ViewModel(){
     val _AffirmationStringList:MutableList<String> = mutableListOf(String())
    val _UnsplashImages:MutableList<String> = mutableListOf(String())

    init {
        getAffirmationString(context = context)
        getAllUnsplashImages()
    }

    fun getAllUnsplashImages(){
        viewModelScope.launch {
            async {
                    UnsplashImageUsecase.invoke().results.forEach {
                        _UnsplashImages.add(it.links.download)

                }
                _UnsplashImages.forEach {
                    Log.e("_UnsplashImages",it)
                }


            }.await()

        }
    }
    fun getAffirmationString(context:Context){
        viewModelScope.launch {
             async {
                 Usecase.invoke().forEach {
                     _AffirmationStringList.add(it.q)
                     alarmSchedule.Schedule(context = context,it.q)
                 }
            }.await()
        }

    }






}