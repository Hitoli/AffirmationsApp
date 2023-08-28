package com.example.myaffirmations.AffirmationsScreen.DI

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myaffirmations.AffirmationsScreen.Repository.AffirmationsRepository
import com.example.myaffirmations.AffirmationsScreen.Repository.IGetAllAffirmationsResponseRepo
import com.example.myaffirmations.AffirmationsScreen.Services.IGetRetrofitCalls
import com.example.myaffirmations.AffirmationsScreen.UseCase.AffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.UseCase.IGetAllAffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.ViewModel.AffimationsViewModel
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmReceiver
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmSchedule
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AffirmationsModule {
    @Provides
    @Singleton
    fun provideAffirmatiosNetworkCall(): Retrofit {
        return Retrofit.Builder().baseUrl(" https://api.themotivate365.com/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun getAffirmationsResponse(retrofit: Retrofit):IGetRetrofitCalls{
        return retrofit.create(IGetRetrofitCalls::class.java)
    }
    @Provides
    @Singleton
    fun notificationCall(@ApplicationContext context: Context):NotificationCompat.Builder{
        return NotificationCompat.Builder(context,"Main Channel ID")
            .setContentTitle("Quote")
            .setContentText("Quotes_Inspiration")
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }
    @Provides
    @Singleton
    fun notificationChannelBuilder(@ApplicationContext context: Context):NotificationManagerCompat{
        val notificaitonManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannel("Main Channel ID","Main Channel",NotificationManager.IMPORTANCE_HIGH)
        notificaitonManager.createNotificationChannel(channel)
        return notificaitonManager
    }
@Module
@InstallIn(SingletonComponent::class)
interface AppModuleBinds{
    @Binds
    @Singleton
    fun provideAlarmSchedular(alarmSchedule: AlarmSchedule): AlarmScheduler
    @Binds
    @Singleton
    fun provideRepositoryQuotes(repo: AffirmationsRepository): IGetAllAffirmationsResponseRepo
    @Binds
    @Singleton
    fun provideUsecaeQuotes( usecase: AffirmationsUsecase): IGetAllAffirmationsUsecase
}




}