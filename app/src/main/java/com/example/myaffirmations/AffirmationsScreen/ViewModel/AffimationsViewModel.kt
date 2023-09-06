package com.example.myaffirmations.AffirmationsScreen.ViewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaffirmations.AffirmationsScreen.Response.Links
import com.example.myaffirmations.AffirmationsScreen.Response.Result
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.UseCase.AffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AffimationsViewModel @Inject constructor(val Usecase:AffirmationsUsecase, val notificationBuilder:NotificationCompat.Builder,
                                               val notificationManager:NotificationManagerCompat, val alarmSchedule: AlarmScheduler,
                                               @ApplicationContext context: Context
):ViewModel(){
     val _AffirmationStringList:MutableState<String> = mutableStateOf(String())
    val _UnsplashImages:MutableState<List<Result>> = mutableStateOf(emptyList())

    init {
        getAffirmationString(context = context)
        getAllUnsplashImages()
    }

    fun getAllUnsplashImages(){
        viewModelScope.launch {
            async {
                _UnsplashImages.value = Usecase.invokeimg().results

            }.await()
        }
    }
    fun getAffirmationString(context:Context){
        viewModelScope.launch {
             async {
                val AffirmationStringResponse = Usecase.invoke().quote
                _AffirmationStringList.value = AffirmationStringResponse
                 alarmSchedule.Schedule(context = context,_AffirmationStringList.value)
            }.await()
        }
        Log.e("QUOTES_",_AffirmationStringList.value)
    }






}