package com.example.myaffirmations.AffirmationsScreen.ViewModel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
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
import com.example.myaffirmations.AffirmationsScreen.Utils.ViewQuotesStates
import com.example.myaffirmations.AffirmationsScreen.Utils.ViewStates
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
    private val _AffirmationStringList:MutableState<ViewQuotesStates> = mutableStateOf(ViewQuotesStates.Loading)
    val AffirmationStringList:MutableState<ViewQuotesStates> = _AffirmationStringList

    private val _UnsplashImages:MutableState<ViewStates> = mutableStateOf( ViewStates.Loading)
    val UnsplashImages:MutableState<ViewStates> = _UnsplashImages


    init {
        getAllUnsplashImages()
        getAffirmationString(context = context)


    }

    fun getAllUnsplashImages(){
        viewModelScope.launch {
            try {
                async {
                    val imag:MutableList<String> = mutableListOf()
                    UnsplashImageUsecase.invoke().results.forEach {
                         imag.add(it.links.download)
                    }
                    _UnsplashImages.value= ViewStates.Success(imag)
                }.await()
            }catch (e:Exception){
            Log.e("Error articleviewmodel",e.message.toString())
                _UnsplashImages.value= ViewStates.Failure(e.message?:"Unknown Error Occured :(")
            //_listofDishes.value= DishesViewModel.ViewStates.Failure(e.message?:"Unknown Error Occured :(")
        }


        }
    }
    fun getAffirmationString(context:Context) {
        viewModelScope.launch {
            try {
                async {
                    val quotte: MutableList<String> = mutableListOf()
                    Usecase.invoke().forEach {
                        quotte.add(it.q)
                        _AffirmationStringList.value = ViewQuotesStates.Success(quotte)
                        alarmSchedule.Schedule(context = context, it.q)

                        Log.e("Affirmationlist", quotte.toString())
                        Log.e(" ", AffirmationStringList.toString())

                    }
                }.await()
            }catch (e:Exception) {
                Log.e("Error articleviewmodel", e.message.toString())
                _AffirmationStringList.value = ViewQuotesStates.Failure(e.message ?: "Unknown Error Occured :(")
            }

        }
    }

}