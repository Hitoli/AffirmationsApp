package com.example.myaffirmations.AffirmationsScreen.Utils

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myaffirmations.AffirmationsScreen.Services.IGetRetrofitCalls
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class AlarmReceiver() :BroadcastReceiver() {

    @Inject
    lateinit var notificationManagerCompat: NotificationManagerCompat
    @Inject
    lateinit var notificationCompat: NotificationCompat.Builder
    @Inject
    lateinit var Services:IGetRetrofitCalls
    override fun onReceive(context: Context, intent: Intent?) {
        var Quotes = intent?.getStringExtra("QUOTES")

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
        notificationManagerCompat.notify(1971,notificationCompat.setContentTitle("Quotes").setContentText(Quotes).build())

    }

}