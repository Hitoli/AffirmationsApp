package com.example.myaffirmations.AffirmationsScreen.Utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface AlarmScheduler{
    fun Schedule(context: Context, Message:String)
    fun cancel()
}

class AlarmSchedule @Inject constructor(@ApplicationContext context: Context): AlarmScheduler {

    private val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun Schedule(context: Context,Message: String) {
        val intent= Intent(context, AlarmReceiver::class.java).apply {
            putExtra("QUOTES",Message)
            Log.e("MESSAGE",Message)
        }
        val notificationIntent: PendingIntent = PendingIntent.getBroadcast(
            context,1971,intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val interval = 1000 * 60*10
       alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC,interval.toLong(),notificationIntent)

    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}