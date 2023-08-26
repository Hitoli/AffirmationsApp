package com.example.myaffirmations.AffirmationsScreen.ViewModel

import android.Manifest
import android.content.Context
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AffimationsViewModel @Inject constructor(val Usecase:AffirmationsUsecase, val notificationBuilder:NotificationCompat.Builder, val notificationManager:NotificationManagerCompat):ViewModel() {
     val _AffirmationStringList:MutableState<String> = mutableStateOf(String())

    init {
        getAffirmationString()
    }
    fun getAffirmationString(){
        viewModelScope.launch {
            val AffirmationStringResponse = Usecase.invoke().quote
            _AffirmationStringList.value = AffirmationStringResponse
        }
        Log.e("QUOTES_",_AffirmationStringList.value)
    }

    fun buildingNotification(context: Context){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        notificationManager.notify(1971,notificationBuilder.setContentText(_AffirmationStringList.value).build())
    }

}