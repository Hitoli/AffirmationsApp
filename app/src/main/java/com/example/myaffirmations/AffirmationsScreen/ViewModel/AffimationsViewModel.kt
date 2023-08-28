package com.example.myaffirmations.AffirmationsScreen.ViewModel

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myaffirmations.AffirmationsScreen.UseCase.AffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmReceiver
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmSchedule
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AffimationsViewModel @Inject constructor(val Usecase:AffirmationsUsecase, val notificationBuilder:NotificationCompat.Builder,
                                               val notificationManager:NotificationManagerCompat, val alarmSchedule: AlarmScheduler, @ApplicationContext context: Context
):ViewModel(){
     val _AffirmationStringList:MutableState<String> = mutableStateOf(String())

    init {
        getAffirmationString(context = context)

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