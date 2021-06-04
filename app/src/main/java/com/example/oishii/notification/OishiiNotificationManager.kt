package com.example.oishii.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.oishii.OishiiApplication

private lateinit var notificationsManager: NotificationManager
private var channelId = "com.example.oishii.id.notificationFoodFinish"

object OishiiNotificationManager {

    init {
        notificationsManager =
            OishiiApplication.application.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, "Order confirmations", importance)

        channel.description = "Makes a sound and appears as a heads-up notification"
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 400)
        channel.setShowBadge(true)
        notificationsManager.createNotificationChannel(channel)
    }


    fun createAndSendNotification(title: String, content: String) {

        val notificationId = 65465
        val notification =
            Notification.Builder(OishiiApplication.application.applicationContext, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build()

        notificationsManager.notify(notificationId, notification)

    }
}