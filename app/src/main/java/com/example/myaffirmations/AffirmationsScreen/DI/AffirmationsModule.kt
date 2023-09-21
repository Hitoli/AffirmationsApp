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
import com.example.myaffirmations.AffirmationsScreen.Repository.IgetUnsplashRepository
import com.example.myaffirmations.AffirmationsScreen.Repository.UnsplashRespository
import com.example.myaffirmations.AffirmationsScreen.Services.IGetRetrofitCalls
import com.example.myaffirmations.AffirmationsScreen.Services.IGetUnplashRetrofitCalls
import com.example.myaffirmations.AffirmationsScreen.UseCase.AffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.UseCase.IGetAllAffirmationsUsecase
import com.example.myaffirmations.AffirmationsScreen.UseCase.IgetImagefromUnsplash
import com.example.myaffirmations.AffirmationsScreen.UseCase.UnsplashImageUsecase
import com.example.myaffirmations.AffirmationsScreen.ViewModel.AffimationsViewModel
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmReceiver
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmSchedule
import com.example.myaffirmations.AffirmationsScreen.Utils.AlarmScheduler
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AffirmationsModule {
    @Provides
    @Singleton
    @QuotesRetrofit
    fun provideAffirmatiosNetworkCall(): Retrofit {
        return Retrofit.Builder().baseUrl("https://zenquotes.io/api/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun getAffirmationsResponse(@QuotesRetrofit retrofit: Retrofit):IGetRetrofitCalls{
        return retrofit.create(IGetRetrofitCalls::class.java)
    }
    @Provides
    @Singleton
    @UnsplashRetrofit
    fun provideUnsplashNetworkCall():Retrofit{
        return Retrofit.Builder().baseUrl("https://api.unsplash.com/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun getUnsplashResponse(@UnsplashRetrofit retrofit: Retrofit):IGetUnplashRetrofitCalls{
        return retrofit.create(IGetUnplashRetrofitCalls::class.java)
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
    fun provideRepositoryImages(repo: UnsplashRespository): IgetUnsplashRepository
    @Binds
    @Singleton
    fun provideUsecaeQuotes( usecase: AffirmationsUsecase): IGetAllAffirmationsUsecase

    @Binds
    @Singleton
    fun provideUsecaeImages( usecase: UnsplashImageUsecase): IgetImagefromUnsplash
}
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class QuotesRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UnsplashRetrofit

}