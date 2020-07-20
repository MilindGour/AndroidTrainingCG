package com.example.androidtrainingcg

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MyService : Service() {

    lateinit var mediaPlayer: MediaPlayer
    private val notificationChannelID: String = "com.example.androidtrainingcg.test"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
        }

        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        // Pending intent
        val mainNavIntent: Intent = Intent(this, MainActivity::class.java)
        val pIntent: PendingIntent = PendingIntent.getActivity(this, 0, mainNavIntent, 0)

        // start foreground notification
        val notification: Notification =
            NotificationCompat.Builder(this, notificationChannelID)
                .setContentTitle("Alarm sound test")
                .setContentText("Testing testing..... 1..2...3")
                .setSmallIcon(R.drawable.ic_add_black_24dp)
                    .setContentIntent(pIntent)
                .build()
        startForeground(2, notification)

        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(notificationChannelID, "Test Channel", importance)

            channel.description = "This is a test channel for notification testing."
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }


    override fun onDestroy() {
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
        }
        super.onDestroy()
    }
}
