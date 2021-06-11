package com.example.oishii.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.oishii.MainActivity
import com.example.oishii.OishiiApplication
import com.example.oishii.R
import com.example.oishii.utils.CHANNEL_ID

private lateinit var notificationsManager: NotificationManager

object OishiiNotificationManager {

    init {
        notificationsManager =
            OishiiApplication.application.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            CHANNEL_ID,
            OishiiApplication.application.applicationContext.getString(R.string.channel_name_text),
            importance
        )

        channel.description =
            OishiiApplication.application.applicationContext.getString(R.string.channel_description_text)
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 400)
        channel.setShowBadge(true)
        notificationsManager.createNotificationChannel(channel)
    }


    fun notificationChannel1(title: String, content: String, notificationId: Int) {

        val notification =
            Notification.Builder(OishiiApplication.application.applicationContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build()

        notificationsManager.notify(notificationId, notification)
    }

    fun notificationChannel2(title: String, content: String, notificationId: Int) {
        val notification =
            Notification.Builder(OishiiApplication.application.applicationContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build()

        notificationsManager.notify(notificationId, notification)
    }
}